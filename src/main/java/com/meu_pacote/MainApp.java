package com.meu_pacote;

import com.meu_pacote.api.ControladorAPI;
import com.meu_pacote.model.Hidrometro;
import com.meu_pacote.ui.Display;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainApp extends Application {

    private Hidrometro hidrometro;
    private ControladorAPI controladorAPI;
    private ScheduledExecutorService simulationExecutor;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // 1. Criar o Modelo (o hidrômetro)
        hidrometro = new Hidrometro();

        // 2. Criar a View (a interface gráfica) e registrá-la como observer
        Display display = new Display(primaryStage);
        hidrometro.adicionarObserver(display);

        // 3. Criar e iniciar a API REST
        controladorAPI = new ControladorAPI(hidrometro);
        controladorAPI.iniciar();

        // 4. Iniciar o loop de simulação em uma thread separada para não travar a UI
        simulationExecutor = Executors.newSingleThreadScheduledExecutor();
        simulationExecutor.scheduleAtFixedRate(() -> {
            try {
                hidrometro.simularPassagemDeTempo(0.1); // Simula um passo de 0.1 segundos
                hidrometro.notificarObservers();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 100, TimeUnit.MILLISECONDS); // Executa a cada 100ms (10x por segundo)
    }

    @Override
    public void stop() {
        // Garante que a API e o loop de simulação sejam encerrados corretamente
        System.out.println("Encerrando a aplicação...");
        if (controladorAPI != null) {
            controladorAPI.parar();
        }
        if (simulationExecutor != null) {
            simulationExecutor.shutdownNow();
        }
    }
}