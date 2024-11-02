    package com.trabalhojava.sistemarpg.controller;

    import javafx.animation.KeyFrame;
    import javafx.animation.Timeline;
    import javafx.application.Application;
    import javafx.geometry.Insets;
    import javafx.scene.Scene;
    import javafx.scene.control.*;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.scene.layout.*;
    import javafx.stage.Stage;
    import javafx.scene.text.Font;
    import javafx.scene.text.FontWeight;
    import javafx.scene.paint.Color;
    import javafx.util.Duration;

    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.List;

    import com.trabalhojava.sistemarpg.dao.ClasseDBDAO;
    import com.trabalhojava.sistemarpg.dao.PersonagemDBDAO;
    import com.trabalhojava.sistemarpg.dao.PersonagemSistemaDBDAO;
    import com.trabalhojava.sistemarpg.model.Personagem;
    import com.trabalhojava.sistemarpg.model.PersonagemSistema;
    import com.trabalhojava.sistemarpg.model.Classe;
    import com.trabalhojava.sistemarpg.model.Raca;
    import com.trabalhojava.sistemarpg.model.Sistema;
    import com.trabalhojava.sistemarpg.dao.RacaDBDAO;
    import com.trabalhojava.sistemarpg.dao.SistemaDBDAO;

    public class MenusController extends Application {
        List<Personagem> personagens = new ArrayList<>();

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Gerenciador de Personagens");

            BorderPane root = new BorderPane();
            root.setStyle("-fx-background-color: #1E1E1E; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0.5, 0.0, 0.0);");

            ImageView gifView = new ImageView(new Image("file:D:\\Documentos\\codes\\java\\rpgarhtur\\POOIIrpg\\ninjaBranco.gif"));

            gifView.setFitWidth(250);
            gifView.setFitHeight(350);
            gifView.setPreserveRatio(true);
            gifView.setStyle("-fx-border-color: #2F2F2F; -fx-border-width: 2; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 10, 0.5, 0.0, 0.0);");

            HBox barraTopo = new HBox();
            barraTopo.setSpacing(15);
            barraTopo.setPadding(new Insets(10));
            barraTopo.setStyle("-fx-background-color: #2B2B2B;");

            Button btnAdicionar = new Button("+ Adicionar");
            btnAdicionar.setFont(Font.font("Arial", 16));
            btnAdicionar.setTextFill(Color.WHITE);
            btnAdicionar.setStyle("-fx-background-color: #007ACC; -fx-background-radius: 8;");

            Button btnVer = new Button("👁 Ver Personagens");
            btnVer.setFont(Font.font("Arial", 16));
            btnVer.setTextFill(Color.WHITE);
            btnVer.setStyle("-fx-background-color: #007ACC; -fx-background-radius: 8;");

            Button btnEditar = new Button("✏ Editar Personagens");
            btnEditar.setFont(Font.font("Arial", 16));
            btnEditar.setTextFill(Color.WHITE);
            btnEditar.setStyle("-fx-background-color: #007ACC; -fx-background-radius: 8;");

            btnAdicionar.setOnAction(e -> exibirTelaDefinirCaracteristicas());
            btnVer.setOnAction(e -> exibirTelaVerPersonagens());
            btnEditar.setOnAction(e -> exibirTelaEditarPersonagens());

            barraTopo.getChildren().addAll(btnAdicionar, btnVer, btnEditar);
            root.setTop(barraTopo);

            VBox centro = new VBox();
            centro.setSpacing(15);
            centro.setPadding(new Insets(15));
            centro.setStyle("-fx-alignment: center; -fx-background-color: #1E1E1E;");
            
            Label titulo = new Label("Bem vindo");
            titulo.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
            titulo.setTextFill(Color.WHITE);
            
            centro.getChildren().addAll(titulo, gifView);
            root.setCenter(centro);

            HBox barraRGB = new HBox();
            barraRGB.setPrefHeight(9); 
            root.setBottom(barraRGB);

            Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(30), e -> { 
                    double offset = (System.currentTimeMillis() % 3000) / 3000.0; 
                    barraRGB.setStyle(String.format("-fx-background-color: linear-gradient(to right, "
                            + "hsb(%f, 100%%, 100%%), "
                            + "hsb(%f, 100%%, 100%%), "
                            + "hsb(%f, 100%%, 100%%), "
                            + "hsb(%f, 100%%, 100%%), "
                            + "hsb(%f, 100%%, 100%%), "
                            + "hsb(%f, 100%%, 100%%), "
                            + "hsb(%f, 100%%, 100%%));",
                            360 * (offset + 0) % 360,
                            360 * (offset + 0.1) % 360,
                            360 * (offset + 0.2) % 360,
                            360 * (offset + 0.3) % 360,
                            360 * (offset + 0.4) % 360,
                            360 * (offset + 0.5) % 360,
                            360 * (offset + 0.6) % 360));
                })
            );
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();

            Scene cena = new Scene(root, 600, 500);
            primaryStage.setScene(cena);
            primaryStage.show();
        }

        private void exibirAlerta(String titulo, String mensagem) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle(titulo);
            alerta.setHeaderText(null);
            alerta.setContentText(mensagem);
            alerta.showAndWait();
        }

        private void exibirTelaDefinirCaracteristicas() {
            Stage stageCaracteristicas = new Stage();
            stageCaracteristicas.setTitle("Definir Características");

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(20));
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setStyle("-fx-background-color: #2B2B2B;");

            Label titulo = new Label("Definir Características do Personagem");
            titulo.setFont(Font.font("Arial", 18));
            titulo.setTextFill(Color.WHITE);
            GridPane.setColumnSpan(titulo, 2);

            Label lblPersonagemId = new Label("ID do Personagem:");
            lblPersonagemId.setStyle("-fx-text-fill: #D3D3D3;");
            TextField campoPersonagemId = new TextField();

            Label lblNome = new Label("Nome:");
            lblNome.setStyle("-fx-text-fill: #D3D3D3;");
            TextField campoNome = new TextField();

            Label lblClasse = new Label("Classe:");
            lblClasse.setStyle("-fx-text-fill: #D3D3D3;");
            ComboBox<String> campoClasse = new ComboBox<>();  
            campoClasse.getItems().addAll("Guerreiro", "Mago", "Arqueiro", "Ladino");

            Label lblRaca = new Label("Raça:");
            lblRaca.setStyle("-fx-text-fill: #D3D3D3;");
            ComboBox<String> campoRaca = new ComboBox<>();
            campoRaca.getItems().addAll("Humano", "Elfo", "Anão", "Orc"); 

            Label lblDescricao = new Label("Descrição:");
            lblDescricao.setStyle("-fx-text-fill: #D3D3D3;");
            TextField campoInfo = new TextField();

            Label lblImagem = new Label("Caminho da Imagem:");
            lblImagem.setStyle("-fx-text-fill: #D3D3D3;");
            TextField campoImagem = new TextField();

            Button btnProximo = new Button("Próximo: Definir Atributos");
            btnProximo.setFont(Font.font("Arial", 16));
            btnProximo.setTextFill(Color.WHITE);
            btnProximo.setStyle("-fx-background-color: #007ACC; -fx-background-radius: 8;");
            btnProximo.setOnAction(e -> exibirTelaDefinirAtributos(
                Integer.parseInt(campoPersonagemId.getText()), 
                campoNome.getText(), 
                campoClasse.getValue(), 
                campoRaca.getValue(), 
                campoInfo.getText(), 
                campoImagem.getText(), 
                stageCaracteristicas
            ));

            grid.add(titulo, 0, 0);
            grid.add(lblPersonagemId, 0, 1);
            grid.add(campoPersonagemId, 1, 1);
            grid.add(lblNome, 0, 2);
            grid.add(campoNome, 1, 2);
            grid.add(lblClasse, 0, 3);
            grid.add(campoClasse, 1, 3);
            grid.add(lblRaca, 0, 4);
            grid.add(campoRaca, 1, 4);
            grid.add(lblDescricao, 0, 5);
            grid.add(campoInfo, 1, 5);
            grid.add(lblImagem, 0, 6);
            grid.add(campoImagem, 1, 6);
            grid.add(btnProximo, 1, 7);

            Scene cena = new Scene(grid, 400, 350);
            stageCaracteristicas.setScene(cena);
            stageCaracteristicas.show();
        }

        private void exibirTelaDefinirAtributos(int personagemId, String nome, String classe, String raca, String info, String caminhoImagem, Stage telaAnterior) {
            telaAnterior.close();
            Stage stageAtributos = new Stage();
            stageAtributos.setTitle("Definir Atributos");

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(20));
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setStyle("-fx-background-color: #2B2B2B;");

            Label titulo = new Label("Definir Atributos do Personagem");
            titulo.setFont(Font.font("Arial", 18));
            titulo.setTextFill(Color.WHITE);
            GridPane.setColumnSpan(titulo, 2);

            Label lblForca = new Label("Força:");
            lblForca.setStyle("-fx-text-fill: #D3D3D3;");
            TextField campoForca = new TextField();

            Label lblDestreza = new Label("Destreza:");
            lblDestreza.setStyle("-fx-text-fill: #D3D3D3;");
            TextField campoDestreza = new TextField();

            Label lblConstituicao = new Label("Constituição:");
            lblConstituicao.setStyle("-fx-text-fill: #D3D3D3;");
            TextField campoConstituicao = new TextField();

            Label lblInteligencia = new Label("Inteligência:");
            lblInteligencia.setStyle("-fx-text-fill: #D3D3D3;");
            TextField campoInteligencia = new TextField();

            Label lblSabedoria = new Label("Sabedoria:");
            lblSabedoria.setStyle("-fx-text-fill: #D3D3D3;");
            TextField campoSabedoria = new TextField();

            Label lblCarisma = new Label("Carisma:");
            lblCarisma.setStyle("-fx-text-fill: #D3D3D3;");
            TextField campoCarisma = new TextField();

            Button btnSalvar = new Button("Salvar Personagem");
            btnSalvar.setFont(Font.font("Arial", 16));
            btnSalvar.setTextFill(Color.WHITE);
            btnSalvar.setStyle("-fx-background-color: #007ACC; -fx-background-radius: 8;");
            btnSalvar.setOnAction(e -> {
                try {
                    if (campoForca.getText().isEmpty() || campoDestreza.getText().isEmpty() || 
                        campoInteligencia.getText().isEmpty() || campoConstituicao.getText().isEmpty() || campoSabedoria.getText().isEmpty() || campoCarisma.getText().isEmpty()) {
                        throw new IllegalArgumentException("Todos os campos de atributos devem ser preenchidos.");
                    }

                    Personagem personagem = new Personagem(personagemId, nome, info, caminhoImagem, 1,
                            Integer.parseInt(campoForca.getText()), Integer.parseInt(campoDestreza.getText()),
                            Integer.parseInt(campoInteligencia.getText()), Integer.parseInt(campoConstituicao.getText()), Integer.parseInt(campoSabedoria.getText()), Integer.parseInt(campoCarisma.getText()));
                    personagens.add(personagem);
                    PersonagemDBDAO personagemDB = new PersonagemDBDAO();
                    SistemaDBDAO sistemaDB = new SistemaDBDAO();
                    Sistema sistema;
                    try {
                        sistema = sistemaDB.buscaPorCodigo(1);
                    } catch (SQLException ex) {
                        exibirAlerta("Erro de Banco de Dados", "Erro ao buscar sistema no banco de dados.");
                        return;
                    }
                    ClasseDBDAO classeDB = new ClasseDBDAO();
                    Classe classet;
                    try {
                        classet = classeDB.buscaPorNome(classe);
                    } catch (SQLException ex) {
                        exibirAlerta("Erro de Banco de Dados", "Erro ao buscar classe no banco de dados.");
                        return;
                    }

                    RacaDBDAO racaDB = new RacaDBDAO();
                    Raca racat;
                    try {
                        racat = racaDB.buscaPorNome(raca);
                    } catch (SQLException ex) {
                        exibirAlerta("Erro de Banco de Dados", "Erro ao buscar raça no banco de dados.");
                        return;
                    }
                    PersonagemSistemaDBDAO personagemSistemaDB = new PersonagemSistemaDBDAO();
                    PersonagemSistema personagemSistema = new PersonagemSistema(personagem, sistema, racat, classet);

                    try {
                        personagemDB.insere(personagem);
                        personagemSistemaDB.insere(personagemSistema);
                    } catch (SQLException ex) {
                        exibirAlerta("Erro de Banco de Dados", "Erro ao salvar personagem no banco de dados.");
                    }
                    stageAtributos.close();
                } catch (NumberFormatException ex) {
                    exibirAlerta("Erro de Formato", "Os atributos devem ser números inteiros.");
                } catch (IllegalArgumentException ex) {
                    exibirAlerta("Campos Vazios", ex.getMessage());
                }
            });

            
            // lbl ou new label (?)
            grid.add(titulo, 0, 0);
            grid.add(lblForca, 0, 1);
            grid.add(campoForca, 1, 1);
            grid.add(lblDestreza, 0, 2);
            grid.add(campoDestreza, 1, 2);
            grid.add(lblConstituicao, 0, 3);
            grid.add(campoConstituicao, 1, 3);
            grid.add(lblInteligencia, 0, 4);
            grid.add(campoInteligencia, 1, 4);
            grid.add(lblSabedoria, 0, 5);
            grid.add(campoSabedoria, 1, 5);
            grid.add(lblCarisma, 0, 6);
            grid.add(campoCarisma, 1, 6);
            grid.add(btnSalvar, 1, 7);

            Scene cena = new Scene(grid, 400, 300);
            stageAtributos.setScene(cena);
            stageAtributos.show();
        }

        private void exibirTelaVerPersonagens() {
            Stage stageVisualizar = new Stage();
            stageVisualizar.setTitle("Personagens Cadastrados");

            VBox vbox = new VBox();
            vbox.setSpacing(10);
            vbox.setPadding(new Insets(15));
            vbox.setStyle("-fx-background-color: #2B2B2B;");

            Label titulo = new Label("Personagens Cadastrados");
            titulo.setFont(Font.font("Arial", 18));
            titulo.setTextFill(Color.WHITE);
            vbox.getChildren().add(titulo);

            PersonagemSistemaDBDAO personagemSistemaDB = new PersonagemSistemaDBDAO();
            List<PersonagemSistema> personagensSistema;
            try {
                personagensSistema = personagemSistemaDB.listar();
            } catch (SQLException ex) {
                exibirAlerta("Erro de Banco de Dados", "Erro ao buscar personagens no banco de dados.");
                return;
            }

            if (personagensSistema.isEmpty()) {
                Label vazio = new Label("Nenhum personagem cadastrado.");
                vazio.setFont(Font.font("Arial", 18));
                vazio.setTextFill(Color.WHITE);
                vbox.getChildren().add(vazio);
            } else {
                //Label taai = new Label("Personagens cadastrados:"); (?)
                for (PersonagemSistema personagemSistema : personagensSistema) {
                    Label personagemLabel = new Label(personagemSistema.toString());
                    personagemLabel.setFont(Font.font("Arial", 16));
                    personagemLabel.setTextFill(Color.WHITE);
                    vbox.getChildren().add(personagemLabel);
                }
            }

            Scene cena = new Scene(vbox, 400, 300);
            stageVisualizar.setScene(cena);
            stageVisualizar.show();
        }

        private void exibirTelaEditarPersonagens() {
            Stage stageEditar = new Stage();
            stageEditar.setTitle("Editar Personagens");

            VBox vbox = new VBox();
            vbox.setSpacing(10);
            vbox.setPadding(new Insets(15));
            vbox.setStyle("-fx-background-color: #2B2B2B;");

            PersonagemSistemaDBDAO personagemSistemaDB = new PersonagemSistemaDBDAO();
            List<PersonagemSistema> personagensSistema;
            try {
                personagensSistema = personagemSistemaDB.listar();
            } catch (SQLException ex) {
                exibirAlerta("Erro de Banco de Dados", "Erro ao buscar personagens no banco de dados.");
                return;
            }

            for (PersonagemSistema personagemSistema : personagensSistema) {
                Label personagemLabel = new Label(personagemSistema.toString());
                personagemLabel.setFont(Font.font("Arial", 16));
                personagemLabel.setTextFill(Color.WHITE);

                Button btnEditar = new Button("Editar");
                btnEditar.setFont(Font.font("Arial", 16));
                btnEditar.setTextFill(Color.WHITE);
                btnEditar.setStyle("-fx-background-color: #007ACC; -fx-background-radius: 8;");
                btnEditar.setOnAction(e -> {
                    Stage stageEditarPersonagem = new Stage();
                    stageEditarPersonagem.setTitle("Editar Personagem");

                    GridPane grid = new GridPane();
                    grid.setPadding(new Insets(20));
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setStyle("-fx-background-color: #2B2B2B;");

                    Label titulo = new Label("Editar Personagem");
                    titulo.setFont(Font.font("Arial", 18));
                    titulo.setTextFill(Color.WHITE);
                    GridPane.setColumnSpan(titulo, 2);

                    TextField campoNome = new TextField(personagemSistema.getPersonagem().getNome());
                    ComboBox<String> campoClasse = new ComboBox<>();
                    ComboBox<String> campoRaca = new ComboBox<>();
                    campoRaca.getItems().addAll("Humano", "Elfo", "Anão", "Orc");
                    campoClasse.getItems().addAll("Guerreiro", "Mago", "Arqueiro", "Ladino");
                    campoRaca.setValue(personagemSistema.getRaca().getNomeRaca());
                    campoClasse.setValue(personagemSistema.getClasse().getNomeClasse());
                    TextField campoInfo = new TextField(personagemSistema.getPersonagem().getDescricao());
                    TextField campoImagem = new TextField(personagemSistema.getPersonagem().getUrlImg());
                    TextField campoForca = new TextField(String.valueOf(personagemSistema.getPersonagem().getForca()));
                    TextField campoDestreza = new TextField(String.valueOf(personagemSistema.getPersonagem().getDestreza()));
                    TextField campoInteligencia = new TextField(String.valueOf(personagemSistema.getPersonagem().getInteligencia()));
                    TextField campoConstituicao = new TextField(String.valueOf(personagemSistema.getPersonagem().getConstituicao()));
                    TextField campoSabedoria = new TextField(String.valueOf(personagemSistema.getPersonagem().getSabedoria()));
                    TextField campoCarisma = new TextField(String.valueOf(personagemSistema.getPersonagem().getCarisma()));

                    Button btnSalvar = new Button("Salvar Alterações");
                    btnSalvar.setFont(Font.font("Arial", 16));
                    btnSalvar.setTextFill(Color.WHITE);
                    btnSalvar.setStyle("-fx-background-color: #007ACC; -fx-background-radius: 8;");
                    btnSalvar.setOnAction(ev -> {
                        try {

                            Personagem personagemNovo = new Personagem(
                                personagemSistema.getPersonagem().getPersonagemId(),
                                campoNome.getText(),
                                campoInfo.getText(),
                                campoImagem.getText(),
                                personagemSistema.getPersonagem().getNivel(),
                                Integer.parseInt(campoForca.getText()),
                                Integer.parseInt(campoDestreza.getText()),
                                Integer.parseInt(campoInteligencia.getText()),
                                Integer.parseInt(campoConstituicao.getText()),
                                Integer.parseInt(campoSabedoria.getText()),
                                Integer.parseInt(campoCarisma.getText())
                            );

                            ClasseDBDAO classeDB = new ClasseDBDAO();
                            RacaDBDAO racaDB = new RacaDBDAO();
                            Classe classeNova = classeDB.buscaPorNome(campoClasse.getValue());
                            Raca racaNova = racaDB.buscaPorNome(campoRaca.getValue());

                            PersonagemSistema personagemSistemaNovo = new PersonagemSistema(
                                personagemNovo,
                                personagemSistema.getSistema(),
                                racaNova,
                                classeNova
                            );
                            personagemSistemaDB.atualizar(personagemSistema, personagemSistemaNovo);
                            stageEditarPersonagem.close();
                            stageEditar.close();
                            exibirTelaEditarPersonagens();
                        } catch (NumberFormatException ex) {
                            exibirAlerta("Erro de Formato", "Os atributos devem ser números inteiros.");
                        } 
                        catch (SQLException ex) {
                            exibirAlerta("Erro de Banco de Dados", "Erro ao atualizar personagem no banco de dados.");
                        }
                    });

                    grid.add(titulo, 0, 0);
                    grid.add(new Label("Nome:"), 0, 1);
                    grid.add(campoNome, 1, 1);
                    grid.add(new Label("Classe:"), 0, 2);
                    grid.add(campoClasse, 1, 2);
                    grid.add(new Label("Raça:"), 0, 3);
                    grid.add(campoRaca, 1, 3);
                    grid.add(new Label("Descrição:"), 0, 4);
                    grid.add(campoInfo, 1, 4);
                    grid.add(new Label("Caminho da Imagem:"), 0, 5);
                    grid.add(campoImagem, 1, 5);
                    grid.add(new Label("Força:"), 0, 6);
                    grid.add(campoForca, 1, 6);
                    grid.add(new Label("Destreza:"), 0, 7);
                    grid.add(campoDestreza, 1, 7);
                    grid.add(new Label("Constituição:"), 0, 8);
                    grid.add(campoConstituicao, 1, 8);
                    grid.add(new Label("Inteligência:"), 0, 9);
                    grid.add(campoInteligencia, 1, 9);
                    grid.add(new Label("Sabedoria:"), 0, 10);
                    grid.add(campoSabedoria, 1, 10);
                    grid.add(new Label("Carisma:"), 0, 11);
                    grid.add(campoCarisma, 1, 11);
                    grid.add(btnSalvar, 1, 12);

                    Scene cena = new Scene(grid, 400, 500);
                    stageEditarPersonagem.setScene(cena);
                    stageEditarPersonagem.show();
                });

                HBox linha = new HBox(10, personagemLabel, btnEditar);
                vbox.getChildren().add(linha);
            }

            Scene cena = new Scene(vbox, 400, 300);
            stageEditar.setScene(cena);
            stageEditar.show();
        }
    }
