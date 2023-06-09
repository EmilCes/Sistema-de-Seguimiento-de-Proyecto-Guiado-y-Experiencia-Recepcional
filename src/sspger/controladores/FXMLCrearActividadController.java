
package sspger.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class FXMLCrearActividadController implements Initializable {


    @FXML
    private TextArea taDescripcion;
    @FXML
    private ComboBox<?> cbAnteproyecto;
    @FXML
    private Button btnCrearActividad;
    @FXML
    private DatePicker dpFechaFin;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private AnchorPane apCearActividad;
    @FXML
    private TextField tfTitulo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicBtnCrearActividad(ActionEvent event) {
    }
    
        private void clicBtnCrearActividad(ActionEvent event) {
        if (validarCampos()) {
            // Lógica para guardar la actividad
        }
    }

    private boolean validarCampos() {
        boolean camposValidos = true;

        String titulo = tfTitulo.getText();
        String descripcion = taDescripcion.getText();
        DatePicker fechaInicio = dpFechaInicio;
        DatePicker fechaFin = dpFechaFin;
        ComboBox<?> anteproyecto = cbAnteproyecto;

        if (titulo.isEmpty()) {
            tfTitulo.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (descripcion.isEmpty()) {
        }

        if (fechaInicio.getValue() == null) {
        }

        if (fechaFin.getValue() == null) {
        }

        if (anteproyecto.getValue() == null) {
        }

        return camposValidos;
    }
    
}
