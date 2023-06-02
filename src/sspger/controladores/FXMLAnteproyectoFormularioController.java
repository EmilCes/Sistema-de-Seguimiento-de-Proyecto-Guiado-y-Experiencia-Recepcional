package sspger.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;



public class FXMLAnteproyectoFormularioController implements Initializable {

    @FXML
    private AnchorPane apCrearAnteproyecto;
    @FXML
    private ScrollPane spAnteproyectoFormulario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        spAnteproyectoFormulario.setFocusTraversable(false);
    }    
    
}
