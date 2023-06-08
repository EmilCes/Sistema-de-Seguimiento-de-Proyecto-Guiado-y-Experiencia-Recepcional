
package sspger.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public class FXMLAnteproyectoFragmentoController implements Initializable {

    @FXML
    private Pane pnAnteproyecto;
    @FXML
    private Label lbNombreAnteproyecto;
    @FXML
    private Label lbNombreDirector;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void cargarInformacionAnteproyecto(String nombreAnteproyecto, String nombreDirector){
        lbNombreAnteproyecto.setText(nombreAnteproyecto);
        lbNombreDirector.setText(nombreDirector);
    }
    
}
