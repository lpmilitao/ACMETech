package dados;

import entidades.Fornecedor;
import entidades.Tecnologia;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DadosTecnologias {
    private final LinkedList<Tecnologia> tecnologias;
    private final DadosFornecedores fornecedores;

    public DadosTecnologias() {
        this.tecnologias = new LinkedList<>();
        this.fornecedores = new DadosFornecedores();
    }

    public void lerArquivoTecnologia() {
        Path path = Paths.get("TECNOLOGIASENTRADA.csv");
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String linha;
            br.readLine();

            while ((linha = br.readLine()) != null) {
                try {
                    Scanner sc = new Scanner(linha).useDelimiter(";");
                    long Id = Long.parseLong(sc.next());
                    String modelo = sc.next();
                    String descricao = sc.next();
                    double valorBase = Double.parseDouble(sc.next());
                    double peso = Double.parseDouble(sc.next());
                    double temperatura = Double.parseDouble(sc.next());
                    long codigoFornecedor = Integer.parseInt(sc.next());

                    cadastrarTecnologia(Id, modelo, descricao, valorBase, peso, temperatura, codigoFornecedor);

                } catch (NumberFormatException e) {
                    System.out.println("ERRO:formato invalido");
                } catch (NoSuchElementException e) {
                    System.out.println("ERRO:formato invalido");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo");
        } catch (NumberFormatException e) {
            System.out.println("ERRO:formato invalido");
        }
    }

    public void cadastrarTecnologia(long Id, String modelo, String descricao, double valorBase, double peso, double temperatura, long codigoFornecedor) {
        Fornecedor fornecedor = fornecedores.consultaFornecedorPorCodigo(codigoFornecedor);

        for (Tecnologia tec : tecnologias) {
            if (Id == tec.getId()) {
                System.out.println("Id" + Id + "já cadastrado");
                return;
            }
        }

        if (fornecedor == null) {
            System.out.println("O fornecedor" + codigoFornecedor + "não existe");
            return;
        }

        Tecnologia novaTecnologia = new Tecnologia(Id, modelo, descricao, valorBase, peso, temperatura);
        tecnologias.add(novaTecnologia);
        novaTecnologia.defineFornecedor(fornecedor);
    }
}
