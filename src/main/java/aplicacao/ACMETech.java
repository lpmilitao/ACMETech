package aplicacao;

import dados.CatalogoCompradores;
import dados.CatalogoFornecedores;
import dados.CatalogoTecnologias;
import dados.CatalogoVendas;
import entidades.Comprador;
import entidades.Fornecedor;
import entidades.Tecnologia;
import entidades.Venda;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;

public class ACMETech {
    private CatalogoFornecedores fornecedores;
    private CatalogoCompradores compradores;
    private CatalogoTecnologias tecnologias;
    private CatalogoVendas vendas;

    public ACMETech() {
        this.fornecedores = new CatalogoFornecedores();
        this.compradores = new CatalogoCompradores();
        this.tecnologias = new CatalogoTecnologias();
        this.vendas = new CatalogoVendas();
        inicializar();
    }

    public void executar() {
        // TODO
    }


    public void inicializar() {
        cadastrarParticipantes();

        cadastrarTecnologias();

        cadastrarVendas();

        System.out.println("Fornecedores:");
        for (Fornecedor fornecedor : fornecedores.getFornecedores()) {
            System.out.println(fornecedor.getNome());
        }

        System.out.println("\nCompradores:");
        for (Comprador comprador : compradores.getCompradores()) {
            System.out.println(comprador.getNome());
        }

        System.out.println("\nTecnologias:");
        for (Tecnologia tecnologia : tecnologias.getTecnologias()) {
            System.out.println(tecnologia.getDescricao());
        }

        System.out.println("\nVendas:");
        for (Venda venda : vendas.getVendas()) {
            System.out.println(venda.toString());
        }
    }

    private void cadastrarParticipantes() {
        Path path = Paths.get("recursos", "PARTICIPANTESENTRADA.csv");

        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String linha;
            br.readLine();

            while ((linha = br.readLine()) != null) {
                try (Scanner sc = new Scanner(linha).useDelimiter(";")) {
                    String cod = sc.next();
                    String nome = sc.next();
                    String tipo = sc.next();
                    String fundacaoPais = sc.next();
                    String area_email = sc.next();

                    if (tipo.equals("1")) {
                        fornecedores.cadastrarFornecedor(cod, nome, fundacaoPais, area_email);
                        continue;
                    }

                    compradores.cadastrarComprador(cod, nome, fundacaoPais, area_email);

                } catch (NoSuchElementException | ParseException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.format("Erro ao ler o arquivo: %s%n", e);
        }
    }

    private void cadastrarTecnologias() {
        Path path = Paths.get("recursos", "TECNOLOGIASENTRADA.csv");

        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String linha;
            br.readLine();

            while ((linha = br.readLine()) != null) {
                try (Scanner sc = new Scanner(linha).useDelimiter(";")) {

                    String id = sc.next();
                    String modelo = sc.next();
                    String descricao = sc.next();
                    String valorBase = sc.next();
                    String peso = sc.next();
                    String temperatura = sc.next();
                    String fornecedor = sc.next();

                    this.tecnologias.cadastrarTecnologia(id, modelo, descricao, valorBase, peso, temperatura,
                            fornecedores.getFornecedorByCod(Long.parseLong(fornecedor)));

                } catch (NoSuchElementException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.format("Erro ao ler o arquivo: %s%n", e);
        }
    }

    private void cadastrarVendas() {
        Path path = Paths.get("recursos", "VENDASENTRADA.csv");
        Queue<Venda> vendas = new LinkedList<>();

        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String linha;
            br.readLine();

            while ((linha = br.readLine()) != null) {
                try (Scanner sc = new Scanner(linha).useDelimiter(";")) {

                    String num = sc.next();
                    String data = sc.next();
                    String cod = sc.next();
                    String id = sc.next();

                    Comprador comprador = this.compradores.getCompradorByCod(Long.parseLong(cod));
                    Tecnologia tecnologia = this.tecnologias.getTecnologiaById(Long.parseLong(id));

                    vendas.offer(
                            this.vendas.gerarVenda(num, data, tecnologia, comprador)
                    );

                } catch (NoSuchElementException e) {
                    System.out.println(e.getMessage());

                } catch (ParseException e) {
                    System.out.println("Tipo incorreto!\n " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.format("Erro ao ler o arquivo: %s%n", e);
        }

        while (!vendas.isEmpty()) {
            this.vendas.cadastrarVenda(vendas.poll());
        }
    }

}
