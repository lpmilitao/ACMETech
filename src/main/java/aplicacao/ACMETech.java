package aplicacao;

import dados.CatalogoCompradores;
import dados.CatalogoFornecedores;
import dados.CatalogoTecnologias;
import entidades.Comprador;
import entidades.Fornecedor;
import entidades.Tecnologia;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ACMETech {
    private CatalogoFornecedores fornecedores;
    private CatalogoCompradores compradores;
    private CatalogoTecnologias tecnologias;

    public ACMETech() {
        this.fornecedores = new CatalogoFornecedores();
        this.compradores = new CatalogoCompradores();
        this.tecnologias = new CatalogoTecnologias();
        inicializar();
    }

    public void executar() {
        // TODO
    }


    public void inicializar() {
        cadastrarParticipantes();

        cadastrarTecnologias();

//        System.out.println("Fornecedores:");
//        for (Fornecedor fornecedor : fornecedores.getFornecedores()) {
//            System.out.println(fornecedor.getNome());
//        }
//
//        System.out.println("\nCompradores:");
//        for (Comprador comprador : compradores.getCompradores()) {
//            System.out.println(comprador.getNome());
//        }
//
//        for (Tecnologia tecnologia : tecnologias.getTecnologias()) {
//            System.out.println(tecnologia.getDescricao());
//        }
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


}
