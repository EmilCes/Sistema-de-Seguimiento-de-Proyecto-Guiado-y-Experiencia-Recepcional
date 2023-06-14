
package sspger.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class FXMLCalificarActividadController implements Initializable {

    @FXML
    private AnchorPane apCalificarActividad;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbFechaInicio;
    @FXML
    private Label lbFechaFin;
    @FXML
    private Button btnDescargarArchivo;
    @FXML
    private Pane paneArchivoConfirmacion;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnCalificar;
    @FXML
    private TextArea taObservaciones;
    @FXML
    private Label lbNombreArchivo;
    @FXML
    private ComboBox<?> cbCalificacion;
    @FXML
    private TextArea taObservaciones1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicBtnDescargarArchivo(ActionEvent event) {
    }

    @FXML
    private void clicBtnVolver(ActionEvent event) {
    }

    @FXML
    private void clicBtnCalificar(ActionEvent event) {
    }
    
}
