package sspger.controladores;

import javafx.scene.image.Image;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sspger.modelos.dao.TipoUsuarioDAO;
import sspger.modelos.dao.UsuarioDAO;
import sspger.modelos.dao.UsuarioDAO;
import sspger.modelos.pojo.TipoUsuario;
import sspger.modelos.pojo.TipoUsuarioRespuesta;
import sspger.modelos.pojo.Usuario;
import sspger.utils.Constantes;
import sspger.utils.Utilidades;

public class FXMLFormularioUsuarioController implements Initializable {

    private ComboBox<TipoUsuario> cbTipoUsuario;
    private TextField tfNombre;
    private TextField tfApellidoPaterno;
    private TextField tfApellidoMaterno;
    private TextField tfCorreo;
    private TextField tfNumeroTelefonico;
    private TextField tfContraseña;
    private ImageView imgImagenPerfil;
    
    private ObservableList<TipoUsuario> tipoUsuarios;
    
    
    public void initialize(URL url, ResourceBundle rb) {
        configurarCbTipoUsuario();
    }


    private void clicBtnGuardarUsuario(ActionEvent event) {
        resetearEstilos();
        validarCampos();
    }

    private void guardarUsuario() {
        String nombreUsuario = tfNombre.getText() + "_" + tfApellidoPaterno.getText();
        nombreUsuario = nombreUsuario.replaceAll("\\s", "");
        int tipoUsuario = 0;
        TipoUsuario opcionSeleccionada = cbTipoUsuario.getValue();
        Image imagenPerfil = imgImagenPerfil.getImage();

        if (opcionSeleccionada != null) {
            tipoUsuario = opcionSeleccionada.getIdTipoUsuario();
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
        TipoUsuario opcionSeleccionada = cbTipoUsuario.getValue();

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
         tipoUsuarios = FXCollections.observableArrayList();
         TipoUsuarioRespuesta tipoUsuarioBD = TipoUsuarioDAO.obtenerTipoUsuarioRespuesta();
         switch (tipoUsuarioBD.getCodigoRespuesta()) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión", "Error en la conexción", Alert.AlertType.ERROR);
                break;
            case(Constantes.ERROR_CONSULTA):
                Utilidades.mostrarDialogoSimple("Error de Consulta", "Error en la consulta", Alert.AlertType.WARNING);
                break;
            case(Constantes.OPERACION_EXITOSA):
                tipoUsuarios.addAll(tipoUsuarioBD.getTiposUsuarios());
                cbTipoUsuario.setItems(tipoUsuarios);
        
         }
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
