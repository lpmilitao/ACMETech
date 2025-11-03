package aplicacao;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ACMETech extends Application {
    private Scene menuPrincial;

    public ACMETech() {
        inicializar();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label titulo = new Label("Escolha uma das opções disponíveis.");
        Button btnCadastrar = new Button("Cadastrar Tecnologia");
        Button btnSair = new Button("Sair");

        VBox layoutMenu = new VBox(10);
        layoutMenu.setPadding(new Insets(20));
        layoutMenu.getChildren().addAll(titulo, btnCadastrar, btnSair);

        menuPrincial = new Scene(layoutMenu, 400, 250);

        Scene formulario = formulario(stage, menuPrincial);

       //Eventos
        btnSair.setOnAction(event -> stage.close());
        btnCadastrar.setOnAction(event -> {
            stage.setScene(formulario);
        });

        stage.setTitle("ACMETech");
        stage.setScene(menuPrincial);
        stage.show();
    }
    private Scene formulario(Stage stage, Scene voltarMenuPrincipal) {
        Label tituloForm = new Label("Cadastrar nova tecnologia");

        Label inputId = new Label("Id");
        TextField inputTecnologia = new TextField();
        inputTecnologia.setMaxWidth(200);

        Label inputModelo = new Label("Modelo");
        TextField input2 = new TextField();
        input2.setMaxWidth(200);

        Button btnSalvar = new Button("Salvar");
        Button btnVoltar = new Button("Voltar");

        VBox layoutForm = new VBox(10);
        layoutForm.setPadding(new Insets(20));
        HBox layoutBotao = new HBox(10);
        layoutBotao.getChildren().addAll(btnSalvar, btnVoltar);
        layoutForm.getChildren().addAll(tituloForm, inputId, inputTecnologia,inputModelo, input2, layoutBotao);

        //Eventos
        btnVoltar.setOnAction(event -> {
            stage.setScene(voltarMenuPrincipal);
        });

        btnSalvar.setOnAction(event -> {
            String Id = inputTecnologia.getText();
            String modelo = input2.getText();
            System.out.println("Tecnologia cadastrada: \n" + "Id: " + Id + "\nModelo: " + modelo);
            inputTecnologia.clear();
            stage.setScene(voltarMenuPrincipal);
        });
        return new Scene(layoutForm, 400, 250);
    }

    public void inicializar(){
        // TODO
    }

    public void executar(){
        // TODO
    }
}
