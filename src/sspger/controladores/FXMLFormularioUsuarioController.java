package sspger.controladores;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.scene.image.Image;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import sspger.modelos.dao.AlumnoDAO;
import sspger.modelos.dao.ProfesorDAO;
import sspger.modelos.dao.TipoUsuarioDAO;
import sspger.modelos.dao.UsuarioDAO;
import sspger.modelos.pojo.TipoUsuario;
import sspger.modelos.pojo.TipoUsuarioRespuesta;
import sspger.modelos.pojo.Usuario;
import sspger.utils.Constantes;
import sspger.utils.Utilidades;

public class FXMLFormularioUsuarioController implements Initializable {

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
    private TextField tfContraseña;
    @FXML
    private ImageView imgImagenPerfil;

    private ObservableList<TipoUsuario> tipoUsuarios;

    @FXML
    private Label lbInformacion;
    @FXML
    private Label lbNombreUsuario;
    @FXML
    private Label lbEncabezado;
    @FXML
    private AnchorPane apRegistrarUsuario;
    @FXML
    private Button btnGuardarUsuario;

    private File archivoFoto;
    private Image imagen;
    @FXML
    private Button btnSeleccionarFoto;

    public void initialize(URL url, ResourceBundle rb) {
        configurarCbTipoUsuario();
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
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    private void clicBtnGuardarUsuario(ActionEvent event) {
        validarCampos();
        resetearEstilos();
    }

    private void guardarUsuario() throws IOException {
        String nombreUsuario = tfNombre.getText() + "_" + tfApellidoPaterno.getText();
        nombreUsuario = nombreUsuario.replaceAll("\\s", "");
        int tipoUsuario = 0;
        TipoUsuario opcionSeleccionada = cbTipoUsuario.getValue();

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
        usuario.setImagen(Files.readAllBytes(archivoFoto.toPath()));

        UsuarioDAO.guardarUsuario(usuario);
        Usuario ultimoRegistro = UsuarioDAO.obtenerUltimoRegistro();

        if (opcionSeleccionada.getIdTipoUsuario() == Constantes.PROFESOR) {
            ProfesorDAO.registrarProfesor(ultimoRegistro.getIdUsuario());
        }

        if (opcionSeleccionada.getIdTipoUsuario() == Constantes.ESTUDIANTE) {
            String nombre = tfNombre.getText().substring(0, 2);
            String apellidoPaterno = tfApellidoPaterno.getText().substring(0, 2);
            String apellidoMaterno = tfApellidoMaterno.getText().substring(0, 2);

            String matricula = ("ZS" + nombre + apellidoPaterno + apellidoMaterno + tipoUsuario).toUpperCase();
            AlumnoDAO.registrarAlumno(ultimoRegistro.getIdUsuario(), matricula);
        }

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

        if (archivoFoto == null) {
            camposValidos = false;
            Utilidades.mostrarDialogoSimple("Selecciona una imagen",
                    "Para guardar el registro del alumno debes seleccionar su foto desde la opción Seleccionar Foto.",
                    Alert.AlertType.WARNING);
        }

        if (camposValidos) {
            try {
                guardarUsuario();
            } catch (IOException ex) {
                Utilidades.mostrarDialogoSimple("Selecciona una imagen",
                        "Para guardar el registro del alumno debes seleccionar su foto desde la opción Seleccionar Foto.",
                        Alert.AlertType.WARNING);
            }
        }
    }

    private void configurarCbTipoUsuario() {
        tipoUsuarios = FXCollections.observableArrayList();
        TipoUsuarioRespuesta tipoUsuarioBD = TipoUsuarioDAO.obtenerTipoUsuarioRespuesta();
        switch (tipoUsuarioBD.getCodigoRespuesta()) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión", "Error en la conexción", Alert.AlertType.ERROR);
                break;
            case (Constantes.ERROR_CONSULTA):
                Utilidades.mostrarDialogoSimple("Error de Consulta", "Error en la consulta", Alert.AlertType.WARNING);
                break;
            case (Constantes.OPERACION_EXITOSA):
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
        imagen = new Image("/Recursos/456212.png");
        tfNombre.setText("");
        tfApellidoPaterno.setText("");
        tfApellidoMaterno.setText("");
        tfCorreo.setText("");
        tfContraseña.setText("");
        tfNumeroTelefonico.setText("");
        cbTipoUsuario.setValue(null);
        imgImagenPerfil.setImage(imagen);
    }

}
