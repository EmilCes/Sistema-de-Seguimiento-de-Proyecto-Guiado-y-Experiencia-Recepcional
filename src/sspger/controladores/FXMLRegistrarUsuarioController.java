
package sspger.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author kirbithalbertocubillashernandez
 */
public class FXMLRegistrarUsuarioController implements Initializable {

    @FXML
    private AnchorPane apCrearAnteproyecto;
    @FXML
    private ComboBox<?> cbTipoUsuario;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfNumeroTelefonico;
    @FXML
    private Button btnSeleccionarFoto;
    @FXML
    private Button btnGuardarUsuario;
    @FXML
    private TextField tfContraseña;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicBtnSeleccionarFoto(ActionEvent event) {
    }

    @FXML
    private void clicBtnGuardarUsuario(ActionEvent event) {
    }
    
}
