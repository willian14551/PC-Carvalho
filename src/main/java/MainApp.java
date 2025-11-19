import java.io.IOException;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {
    private ObservableList<Pokemon> pokedexList;
    private ListView<Pokemon> pokedexListView;
    private ListView<PokemonBase> boxListView;
    private TextArea detailsArea;
    private Box currentBox;
    private Stage primaryStage;
    private Trainer trainer; // Agora usando apenas o Trainer

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Carrega a Pokédex
        try {
            List<Pokemon> pokemonData = PokemonCsvLoader.carregarDeCSV("pokedex.csv");
            this.pokedexList = FXCollections.observableArrayList(pokemonData);
        } catch (IOException e) {
            this.showError("Erro ao carregar Pokédex: " + e.getMessage());
            this.pokedexList = FXCollections.observableArrayList();
        }

        // Mostra a tela de perfil primeiro
        mostrarTelaPerfil();
    }

    private void mostrarTelaPerfil() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #f0f0f0;");

        Label titulo = new Label("🎮 PC Carvalho - Pokébox System");
        titulo.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label subtitulo = new Label("Bem-vindo, Treinador!");
        subtitulo.setStyle("-fx-font-size: 18px; -fx-text-fill: #7f8c8d;");

        // Painel de entrada de dados
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));

        Label nomeLabel = new Label("Nome do Treinador:");
        nomeLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        TextField nomeField = new TextField();
        nomeField.setPromptText("Digite seu nome");
        nomeField.setPrefWidth(250);

        Label nicknameLabel = new Label("Nickname do Treinador:");
        nicknameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        TextField nicknameField = new TextField();
        nicknameField.setPromptText("Digite seu nickname");
        nicknameField.setPrefWidth(250);

        grid.add(nomeLabel, 0, 0);
        grid.add(nomeField, 0, 1);
        grid.add(nicknameLabel, 0, 2);
        grid.add(nicknameField, 0, 3);

        // Botão que vai para a tela da box
        Button continuarButton = new Button("Começar Aventura");
        continuarButton.setStyle("-fx-font-size: 16px; -fx-background-color: #3498db; -fx-text-fill: white; " +
                "-fx-padding: 10 30; -fx-border-radius: 5; -fx-background-radius: 5;");
        continuarButton.setOnAction(e -> {
            String nome = nomeField.getText().trim();
            String nickname = nicknameField.getText().trim();

            if (nome.isEmpty()) {
                showError("Por favor, digite seu nome!");
                return;
            }

            if (nickname.isEmpty()) {
                showError("Por favor, digite seu nickname!");
                return;
            }

            carregar(nome, nickname);

            mostrarTelaBox();
        });

        root.getChildren().addAll(titulo, subtitulo, grid, continuarButton);

        Scene scene = new Scene(root, 600, 500);
        primaryStage.setTitle("PC Carvalho - Perfil");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void mostrarTelaBox() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Título com nome do treinador
        Label title = new Label("🎮 PC Carvalho - " + trainer.getNome() + " (" + trainer.getNickname() + ")");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        BorderPane.setAlignment(title, Pos.CENTER);
        BorderPane.setMargin(title, new Insets(0, 0, 10, 0));
        root.setTop(title);

        HBox centerPanel = new HBox(10);
        centerPanel.getChildren().addAll(
                createPokedexPanel(),
                createBoxPanel(),
                createDetailsPanel()
        );
        root.setCenter(centerPanel);
        root.setBottom(createButtonPanel());

        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setTitle("PC Carvalho - Pokémon Storage System");
        primaryStage.setScene(scene);
    }

    private VBox createPokedexPanel() {
        VBox panel = new VBox(5);
        panel.setPadding(new Insets(5));
        panel.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5;");

        Label label = new Label("📚 Pokédex (Gen 1)");
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        this.pokedexListView = new ListView<>(this.pokedexList);
        this.pokedexListView.setPrefWidth(280);
        this.pokedexListView.setPrefHeight(450);
        this.pokedexListView.setCellFactory(lv -> new ListCell<Pokemon>() {
            @Override
            protected void updateItem(Pokemon pokemon, boolean empty) {
                super.updateItem(pokemon, empty);
                if (!empty && pokemon != null) {
                    setText(String.format("#%03d - %s [%s]",
                            pokemon.getNationalNumber(),
                            pokemon.getSpeciesName(),
                            pokemon.getType1()));
                } else {
                    setText(null);
                }
            }
        });

        panel.getChildren().addAll(label, this.pokedexListView);
        return panel;
    }

    private VBox createBoxPanel() {
        VBox panel = new VBox(5);
        panel.setPadding(new Insets(5));
        panel.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5;");

        Label label = new Label();
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        label.setId("boxLabel");

        this.boxListView = new ListView<>();
        this.boxListView.setPrefWidth(280);
        this.boxListView.setPrefHeight(450);
        this.boxListView.setCellFactory(lv -> new ListCell<PokemonBase>() {
            @Override
            protected void updateItem(PokemonBase pokemon, boolean empty) {
                super.updateItem(pokemon, empty);
                if (!empty && pokemon != null) {
                    setText(pokemon.displaySummary());
                } else {
                    setText(null);
                }
            }
        });

        this.boxListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    if (newVal != null) {
                        showPokemonDetails(newVal);
                    }
                }
        );

        panel.getChildren().addAll(label, this.boxListView);
        return panel;
    }

    private VBox createDetailsPanel() {
        VBox panel = new VBox(5);
        panel.setPadding(new Insets(5));
        panel.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5;");

        Label label = new Label("📋 Detalhes");
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        this.detailsArea = new TextArea();
        this.detailsArea.setEditable(false);
        this.detailsArea.setPrefWidth(280);
        this.detailsArea.setPrefHeight(450);
        this.detailsArea.setWrapText(true);
        this.detailsArea.setText("Selecione um Pokémon para ver os detalhes");

        panel.getChildren().addAll(label, this.detailsArea);
        return panel;
    }

    private HBox createButtonPanel() {
        HBox panel = new HBox(10);
        panel.setPadding(new Insets(10, 0, 0, 0));
        panel.setAlignment(Pos.CENTER);

        Button addButton = new Button("➕ Adicionar à Box");
        addButton.setOnAction(e -> addPokemonToBox());

        Button removeButton = new Button("➖ Remover da Box");
        removeButton.setOnAction(e -> removePokemonFromBox());

        Button saveButton = new Button("💾 Salvar");
        saveButton.setOnAction(e -> saveStorage());

        Button profileButton = new Button("🚪 Sair da Box");
        profileButton.setOnAction(e -> mostrarTelaPerfil());

        panel.getChildren().addAll(addButton, removeButton, saveButton, profileButton);
        return panel;
    }

    private void addPokemonToBox() {
        Pokemon selected = this.pokedexListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Selecione um Pokémon da Pokédex primeiro!");
        } else {
            showAddPokemonDialog(selected);
        }
    }

    private void showAddPokemonDialog(Pokemon species) {
        Dialog<Pokemon> dialog = new Dialog<>();
        dialog.setTitle("Adicionar " + species.getSpeciesName());
        dialog.setHeaderText("Configure seu " + species.getSpeciesName());

        ButtonType addButtonType = new ButtonType("Adicionar", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField levelField = new TextField("5");
        ComboBox<String> genderBox = new ComboBox<>(
                FXCollections.observableArrayList("M", "F")
        );
        genderBox.setValue("M");
        CheckBox shinyBox = new CheckBox("Shiny");

        grid.add(new Label("Level:"), 0, 0);
        grid.add(levelField, 1, 0);
        grid.add(new Label("Gênero:"), 0, 1);
        grid.add(genderBox, 1, 1);
        grid.add(new Label(""), 0, 2);
        grid.add(shinyBox, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                try {
                    int level = Integer.parseInt(levelField.getText());
                    return new Pokemon(
                            species.getNationalNumber(),
                            species.getSpeciesName(),
                            level,
                            genderBox.getValue(),
                            shinyBox.isSelected(),
                            species.getType1(),
                            species.getType2()
                    );
                } catch (NumberFormatException ex) {
                    showError("Level deve ser um número!");
                    return null;
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(newPokemon -> {
            if (newPokemon != null) {
                try {
                    this.currentBox.addPokemon(newPokemon);
                    refreshBoxList();
                    showInfo(newPokemon.getSpeciesName() + " capturado!");
                } catch (BoxFullException e) {
                    showError(e.getMessage());
                }
            }
        });
    }

    private void removePokemonFromBox() {
        int selectedIndex = this.boxListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            showError("Selecione um Pokémon da Box primeiro!");
        } else {
            PokemonBase pokemon = this.currentBox.getPokemonInBox().get(selectedIndex);
            this.currentBox.removePokemon(selectedIndex);
            refreshBoxList();
            this.detailsArea.clear();
            showInfo(pokemon.getSpeciesName() + " removido da Box!");
        }
    }

    private void showPokemonDetails(PokemonBase pokemon) {
        StringBuilder details = new StringBuilder();
        details.append("═══════════════════════\n");
        details.append(pokemon.displaySummary()).append("\n");
        details.append("═══════════════════════\n\n");
        details.append("───────────────────────\n");
        details.append("INFORMAÇÕES BÁSICAS\n");
        details.append("───────────────────────\n");
        details.append("Nº Nacional: #").append(String.format("%03d", pokemon.getNationalNumber())).append("\n");
        details.append("Espécie: ").append(pokemon.getSpeciesName()).append("\n");
        details.append("Level: ").append(pokemon.getLevel()).append("\n");
        details.append("Gênero: ").append(pokemon.getGender()).append("\n");
        details.append("Treinador: ").append(trainer.getNome()).append(" (").append(trainer.getNickname()).append(")\n");

        if (pokemon instanceof Pokemon) {
            Pokemon p = (Pokemon) pokemon;
            details.append("\n───────────────────────\n");
            details.append("TIPOS\n");
            details.append("───────────────────────\n");
            details.append("Tipo 1: ").append(p.getType1()).append("\n");
            if (p.getType2() != null && !p.getType2().isEmpty()) {
                details.append("Tipo 2: ").append(p.getType2()).append("\n");
            }
        }

        if (!pokemon.getStats().isEmpty()) {
            details.append("\nSTATS\n");
            details.append("───────────────────────\n");
            details.append("HP: ").append(pokemon.getStats().getOrDefault("HP", 0)).append("\n");
            details.append("Attack: ").append(pokemon.getStats().getOrDefault("Attack", 0)).append("\n");
            details.append("Defense: ").append(pokemon.getStats().getOrDefault("Defense", 0)).append("\n");
            details.append("Sp. Attack: ").append(pokemon.getStats().getOrDefault("Sp. Attack", 0)).append("\n");
            details.append("Sp. Defense: ").append(pokemon.getStats().getOrDefault("Sp. Defense", 0)).append("\n");
            details.append("Speed: ").append(pokemon.getStats().getOrDefault("Speed", 0)).append("\n");
        }

        this.detailsArea.setText(details.toString());
    }

    private void refreshBoxList() {
        this.boxListView.getItems().clear();
        this.boxListView.getItems().addAll(this.currentBox.getPokemonInBox());

        Label boxLabel = (Label) this.boxListView.getParent().lookup("#boxLabel");
        if (boxLabel != null) {
            boxLabel.setText(String.format("📦 %s's PokeBox (%d/30)",
                    trainer.getNickname(),
                    this.currentBox.getPokemonCount()));
        }
    }
    private void carregar(String nome, String nickname) {
        String filename = "pokebox_save_" + nickname + ".dat";
        StorageSystem storageCarregado = StorageSystem.carregarStorage(filename);

        this.trainer = new Trainer(nome, nickname);
        if (storageCarregado != null) {
            this.trainer.setStorage(storageCarregado);

            if (storageCarregado.getBoxList().isEmpty()) {
                this.currentBox = new Box(nickname + "'s PokeBox");
                storageCarregado.addBox(this.currentBox);
            } else {
                this.currentBox = storageCarregado.getBoxList().get(0);
            }
        } else {

            this.currentBox = new Box(nickname + "'s PokeBox");
            this.trainer.getStorage().addBox(this.currentBox);
        }
    }
    private void saveStorage() {
        boolean success = trainer.getStorage().salvarStorage("pokebox_save_" + trainer.getNickname() + ".dat");
        if (success) {
            showInfo("Box de " + trainer.getNickname() + " salva com sucesso!");
        } else {
            showError("Erro ao salvar a Box!");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}