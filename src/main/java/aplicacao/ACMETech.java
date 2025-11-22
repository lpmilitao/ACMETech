package aplicacao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dados.*;
import entidades.*;
import ui.Menu;
import ui.TelaBase;
import ui.Telas;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;
import java.util.List;

public class ACMETech extends JFrame {
    private CatalogoFornecedores fornecedores;
    private CatalogoCompradores compradores;
    private CatalogoTecnologias tecnologias;
    private CatalogoVendas vendas;
    private ObjectMapper mapper = new ObjectMapper();
    private HashMap<Telas, TelaBase> telas;

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

        inicializarLayout();
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

    public <T> boolean salvarDados(List<T> lista, String arquivo) {

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setVisibility(com.fasterxml.jackson.annotation.PropertyAccessor.FIELD,
                com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY);

        try {
            mapper.writeValue(new File("persistencia", arquivo), lista);
            return true;

        } catch (IOException e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
            return false;
        }
    }

    private void inicializarLayout() {
        telas = new HashMap<>();
        telas.put(Telas.MENU, new Menu(this));

        setBackground(new Color(226, 239, 222));
        setTitle("ACMETech");
        setSize(1300, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        mudarTela(Telas.MENU);
        setVisible(true);
    }

    public void mudarTela(Telas tela) {
        this.setContentPane(telas.get(tela).getPanel());
        this.pack();
        setSize(1300, 800);
    }
}
