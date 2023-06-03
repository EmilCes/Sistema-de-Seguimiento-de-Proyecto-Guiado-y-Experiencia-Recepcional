
package sspger.controladores;

import javafx.scene.image.Image;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sspger.modelos.dao.UsuarioDAO;
import sspger.modelos.pojo.Usuario;
import sspger.utils.Utilidades;

public class FXMLRegistrarUsuarioController implements Initializable {

    @FXML
    private AnchorPane apCrearAnteproyecto;
    @FXML
    private ComboBox<String> cbTipoUsuario;
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
    @FXML
    private ImageView imgImagenPerfil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarCbTipoUsuario();
    }

    @FXML
    private void clicBtnSeleccionarFoto(ActionEvent event) {
        // Lógica para seleccionar una foto
    }

    @FXML
    private void clicBtnGuardarUsuario(ActionEvent event) {
        resetearEstilos();
        validarCampos();
    }

    private void guardarUsuario() {
        String nombreUsuario = tfNombre.getText() + "_" + tfApellidoPaterno.getText();
        nombreUsuario = nombreUsuario.replaceAll("\\s", "");
        int tipoUsuario = 0;
        String opcionSeleccionada = cbTipoUsuario.getValue();
        Image imagenPerfil = imgImagenPerfil.getImage();

        if (opcionSeleccionada.equals("Estudiante")) {
            tipoUsuario = 1;
        } else if (opcionSeleccionada.equals("Profesor")) {
            tipoUsuario = 2;
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(tfNombre.getText());
        usuario.setApellidoPaterno(tfApellidoPaterno.getText());
        usuario.setApellidoMaterno(tfApellidoMaterno.getText());
        usuario.setCorreoInstitucional(tfCorreo.getText());
        usuario.setNumeroTelefonico(tfNumeroTelefonico.getText());
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setPassword(tfContraseña.getText());
        usuario.setIdTipoUsuario(tipoUsuario);
        usuario.setImagen(null);

        UsuarioDAO.guardarUsuario(usuario);

        limpiarCampos();
        Utilidades.mostrarDialogoSimple("Usuario Guardado", "El usuario se guardó exitosamente", Alert.AlertType.INFORMATION);
    }

    private void validarCampos() {
        boolean camposValidos = true;

        String nombre = tfNombre.getText();
        String apellidoPaterno = tfApellidoPaterno.getText();
        String apellidoMaterno = tfApellidoMaterno.getText();
        String correoInstitucional = tfCorreo.getText();
        String numeroTelefonico = tfNumeroTelefonico.getText();
        String password = tfContraseña.getText();
        String opcionSeleccionada = cbTipoUsuario.getValue();

        if (nombre.isEmpty()) {
            tfNombre.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (apellidoPaterno.isEmpty()) {
            tfApellidoPaterno.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (apellidoMaterno.isEmpty()) {
            tfApellidoMaterno.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (correoInstitucional.isEmpty()) {
            tfCorreo.setStyle("-fx-border-color: red");
            camposValidos = false;
        } else {
            String regexCorreo = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b";
            if (!correoInstitucional.matches(regexCorreo)) {
                tfCorreo.setStyle("-fx-border-color: red");
                camposValidos = false;
            }
        }

        if (numeroTelefonico.isEmpty()) {
            tfNumeroTelefonico.setStyle("-fx-border-color: red");
            camposValidos = false;
        } else {
            String regexNumeroTelefonico = "^228\\d{7}$";
            if (!numeroTelefonico.matches(regexNumeroTelefonico)) {
                tfNumeroTelefonico.setStyle("-fx-border-color: red");
                camposValidos = false;
            }
        }

        if (password.isEmpty()) {
            tfContraseña.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (opcionSeleccionada == null) {
            cbTipoUsuario.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (camposValidos) {
            guardarUsuario();
        }
    }

    private void configurarCbTipoUsuario() {
        cbTipoUsuario.getItems().addAll("Estudiante", "Profesor");
    }

    private void resetearEstilos() {
        tfNombre.setStyle("");
        tfApellidoPaterno.setStyle("");
        tfApellidoMaterno.setStyle("");
        tfCorreo.setStyle("");
        tfNumeroTelefonico.setStyle("");
        tfContraseña.setStyle("");
        cbTipoUsuario.setStyle("");
    }

    private void limpiarCampos() {
        tfNombre.setText("");
        tfApellidoPaterno.setText("");
        tfApellidoMaterno.setText("");
        tfCorreo.setText("");
        tfContraseña.setText("");
        tfNumeroTelefonico.setText("");
        cbTipoUsuario.setValue(null);
    }
}
