package sample;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private Canvas canvas;

    @FXML
    private ProgressBar progress;

    @FXML
    private Label progressLabel;

    @FXML
    private Button stopButton;

    @FXML
    private Button startButton;

    @FXML
    private TextField pointCountField;

    @FXML
    private Label nbOfPoints;

    @FXML
    private Label poleLabel;

    private AsyncTask task;

    @FXML
    private void startProcess(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int pt = Integer.parseInt( pointCountField.getText() );
        task = new AsyncTask(gc, Integer.parseInt(pointCountField.getText()) );
        progress.progressProperty().bind(task.progressProperty());
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                double var = (double) task.getValue();

                double pole = 16*7*var;
                //pole = Math.round(pole);
                poleLabel.setText("~ " + String.valueOf(pole));
                progressLabel.setText("Zakończono");
            }
        });

        progressLabel.setText("W trakcie obliczeń...");
        new Thread(task).start();
    }

    @FXML
    private void stopProcess(){
        task.cancel();
    }








}
