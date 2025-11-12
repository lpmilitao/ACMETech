package aplicacao;

import dados.CatalogoCompradores;
import dados.CatalogoFornecedores;
import entidades.Comprador;
import entidades.Fornecedor;

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

    public ACMETech() {
        this.fornecedores = new CatalogoFornecedores();
        this.compradores = new CatalogoCompradores();
        inicializar();
    }

    public void executar() {
        // TODO
    }


    public void inicializar() {
        cadastrarParticipantes();

//        System.out.println("Fornecedores:");
//        for (Fornecedor fornecedor : fornecedores.getFornecedores()) {
//            System.out.println(fornecedor.getNome());
//        }
//
//        System.out.println("\nCompradores:");
//        for (Comprador comprador : compradores.getCompradores()) {
//            System.out.println(comprador.getNome());
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


}
