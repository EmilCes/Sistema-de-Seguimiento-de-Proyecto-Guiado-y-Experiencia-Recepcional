package sspger.controladores;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import sspger.modelos.dao.TipoUsuarioDAO;
import sspger.modelos.dao.UsuarioDAO;
import sspger.modelos.pojo.TipoUsuario;
import sspger.modelos.pojo.Usuario;
import sspger.utils.Constantes;
import sspger.utils.UsuarioSingleton;
import sspger.utils.Utilidades;

public class FXMLModificarUsuarioController implements Initializable {

    @FXML
    private AnchorPane apModificarUsuario;
    @FXML
    private ComboBox<TipoUsuario> cbTipoUsuario;
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
    private ImageView imgImagenPerfil;
    @FXML
    private Button btnSeleccionarFoto;
    @FXML
    private Button btnModificarUsuario;
    @FXML
    private TextField tfContraseña;
    @FXML
    private Label lbInformacion;
    @FXML
    private Label lbEncabezado;
    @FXML
    private TextField tfNombreUsuario;

    private File archivoFoto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Usuario usuario = UsuarioSingleton.getInstancia().getUsuario();
        cargarInformacionUsuario(usuario);
    }

    public void cargarInformacionUsuario(Usuario usuario) {
        tfNombreUsuario.setText(usuario.getNombreUsuario());
        TipoUsuario tipoUsuario = TipoUsuarioDAO.obtenerTipoUsuarioPorId(usuario.getIdTipoUsuario());
        cbTipoUsuario.setValue(tipoUsuario);

        cbTipoUsuario.setDisable(true);
        tfNombre.setText(usuario.getNombre());
        tfApellidoPaterno.setText(usuario.getApellidoPaterno());
        tfApellidoMaterno.setText(usuario.getApellidoMaterno());
        tfCorreo.setText(usuario.getCorreoInstitucional());
        tfNumeroTelefonico.setText(usuario.getNumeroTelefonico());
        tfContraseña.setText(usuario.getPassword());

        if (usuario.getImagen() != null && usuario.getImagen().length > 0) {
            try {
                ByteArrayInputStream inputFoto = new ByteArrayInputStream(usuario.getImagen());
                Image imgFotoAlumno = new Image(inputFoto);
                imgImagenPerfil.setImage(imgFotoAlumno);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void validarCampos() throws IOException {
        boolean camposValidos = true;
        String nombreUsuario = tfNombreUsuario.getText();
        String nombre = tfNombre.getText();
        String apellidoPaterno = tfApellidoPaterno.getText();
        String apellidoMaterno = tfApellidoMaterno.getText();
        String correoInstitucional = tfCorreo.getText();
        String numeroTelefonico = tfNumeroTelefonico.getText();
        String password = tfContraseña.getText();
        TipoUsuario opcionSeleccionada = cbTipoUsuario.getValue();

        Usuario usuario = UsuarioSingleton.getInstancia().getUsuario();
        Usuario usuarioNuevo = new Usuario(usuario.getIdUsuario(), nombre, apellidoPaterno, apellidoMaterno, correoInstitucional, numeroTelefonico, nombreUsuario, password, Files.readAllBytes(archivoFoto.toPath()), nombreUsuario, usuario.getIdTipoUsuario(), usuario.getCodigoRespuesta());
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
            modificarUsuario(usuarioNuevo);
        }
    }

    private void modificarUsuario(Usuario usuario) {
        int codigoRespuesta = UsuarioDAO.modificarUsuario(usuario);
        switch (codigoRespuesta) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión", "Error en la conexción", Alert.AlertType.ERROR);
                break;
            case (Constantes.ERROR_CONSULTA):
                Utilidades.mostrarDialogoSimple("Error de Consulta", "Error en la consulta", Alert.AlertType.WARNING);
                break;
            case (Constantes.OPERACION_EXITOSA):
                Utilidades.mostrarDialogoSimple("Usuario Modificado", "El usuario ha sido modificado exitosamente", Alert.AlertType.INFORMATION);
                UsuarioSingleton.getInstancia().setUsuario(usuario);
        }                
    }

    @FXML
    private void clicBtnSeleccionarFoto(ActionEvent event) {
        FileChooser dialogoImagen = new FileChooser();
        dialogoImagen.setTitle("Selecciona una foto");
        FileChooser.ExtensionFilter filtroImg = new FileChooser.ExtensionFilter("Archivos JPG (*.jpg)", "*.JPG", "*.JPEG");
        dialogoImagen.getExtensionFilters().add(filtroImg);
        Stage escenarioActual = (Stage) tfNombre.getScene().getWindow();
        archivoFoto = dialogoImagen.showOpenDialog(escenarioActual);

        if (archivoFoto != null) {
            try {
                BufferedImage bufferImg = ImageIO.read(archivoFoto);
                Image imagenFoto = SwingFXUtils.toFXImage(bufferImg, null);
                imgImagenPerfil.setImage(imagenFoto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void clicBtnModificarUsuario(ActionEvent event) {
        resetearEstilos();
        try {
            validarCampos();
        } catch (IOException ex) {
            Utilidades.mostrarDialogoSimple("Selecciona una imagen",
                    "Para guardar el registro del alumno debes seleccionar su foto desde la opción Seleccionar Foto.",
                    Alert.AlertType.WARNING);
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
        tfNombreUsuario.setStyle("");
    }
}
