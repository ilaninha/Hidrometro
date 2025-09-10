package com.meu_pacote.ui;

import com.meu_pacote.model.DadosHidrometro;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture; // <-- 1. ADICIONE ESTA IMPORTAÇÃO
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Display {
    private final Stage stage;
    private final Text consumoText = new Text();
    private final Text pressaoText = new Text();
    private final Text estadoText = new Text();

    public Display(Stage stage) {
        this.stage = stage;
        initUI();
    }

    private void initUI() {
        stage.setTitle("Simulador de Hidrômetro");

        VBox root = new VBox(20);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: #F0F0F0;");

        Text title = new Text("Monitor do Hidrômetro");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        consumoText.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        pressaoText.setFont(Font.font("Arial", 16));

        // 2. LINHA CORRIGIDA: Troquei FontWeight.ITALIC por FontPosture.ITALIC
        // e adicionei um peso normal (FontWeight.NORMAL).
        estadoText.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.ITALIC, 16));

        root.getChildren().addAll(title, consumoText, pressaoText, estadoText);

        Scene scene = new Scene(root, 400, 250);
        stage.setScene(scene);
        stage.show();
    }

    // Este é o método do padrão Observer
    public void update(DadosHidrometro dados) {
        // A atualização da UI deve ocorrer na thread de aplicação do JavaFX
        Platform.runLater(() -> {
            consumoText.setText(String.format("Consumo: %.3f m³", dados.getConsumoTotalM3()));
            pressaoText.setText(String.format("Pressão: %.1f kPa", dados.getPressaoAtualKpa()));
            estadoText.setText("Estado: " + dados.getEstado());
        });
    }
}