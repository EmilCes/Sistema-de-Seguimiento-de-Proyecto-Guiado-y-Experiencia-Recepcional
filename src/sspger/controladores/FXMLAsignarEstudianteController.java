package sspger.controladores;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sspger.modelos.dao.AlumnoDAO;
import sspger.modelos.dao.AnteproyectoDAO;
import sspger.modelos.dao.UsuarioDAO;
import sspger.modelos.pojo.Alumno;
import sspger.utils.Constantes;
import sspger.utils.Utilidades;

public class FXMLAsignarEstudianteController implements Initializable {

    @FXML
    private AnchorPane apAsignarEstudiante;
    @FXML
    private ImageView ivImagenUsuario;
    @FXML
    private FontAwesomeIcon faUsuario;
    @FXML
    private Label lbNombreEstudiante;
    @FXML
    private TextField tfMatriculaEstudiante;
    @FXML
    private Label lbNumeroEstudiantes;

    private Alumno alumno;
    private int idAnteproyecto;
    private int numeroEstudiantes;
    private String matricula;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        enviarConEnter();
    }

    private void enviarConEnter() {
        tfMatriculaEstudiante.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                validarCampoMatricula();
            }
        });
    }

    public void cargarInformacionAsignacionEstudiantes(int idAnteproyecto, int numEstudiantes) {
        this.idAnteproyecto = idAnteproyecto;
        this.numeroEstudiantes = numEstudiantes;
        cargarInformacionNumeroEstudiantes();
    }

    private void cargarInformacionNumeroEstudiantes() {
        lbNumeroEstudiantes.setText(String.valueOf(numeroEstudiantes));
    }
    
    

    private void resetearEstilos() {
        tfMatriculaEstudiante.setStyle("");
    }

    private void resetearPantalla() {
        tfMatriculaEstudiante.setText("");
        lbNombreEstudiante.setText("");
        alumno = null;
        cargarInformacionNumeroEstudiantes();
        faUsuario.setVisible(true);
        ivImagenUsuario.setVisible(false);
    }

    private void validarCampoMatricula() {
        resetearEstilos();
        matricula = tfMatriculaEstudiante.getText();
        if (matricula.equals(null) || matricula.trim().equals("")) {
            tfMatriculaEstudiante.setStyle("-fx-border-color: red");
        } else {
            buscarAlumno();
        }
    }

    private void buscarAlumno() {
        alumno = AlumnoDAO.obtenerInformacionAlumnoPorMatricula(matricula);
        switch (alumno.getCodigoRespuesta()) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión", "Error en la conexción", Alert.AlertType.ERROR);
                break;
            case (Constantes.ERROR_CONSULTA):
                Utilidades.mostrarDialogoSimple("Error de Consulta", "Error en la consulta", Alert.AlertType.WARNING);
                break;
            case (Constantes.OPERACION_EXITOSA):
                if (alumno.getIdAlumno() != 0) {
                    lbNombreEstudiante.setText(alumno.getNombreCompleto());
                    ByteArrayInputStream inputFoto = new ByteArrayInputStream(alumno.getImagenUsuario());
                    Image imgFoto = new Image(inputFoto);
                    ivImagenUsuario.setImage(imgFoto);
                    faUsuario.setVisible(false);
                } else {
                    lbNombreEstudiante.setText("");
                    Utilidades.mostrarDialogoSimple("Estudiante no encontrado",
                            "Verifica que la matricula sea correcta", Alert.AlertType.WARNING);
                }
                break;
        }
    }

    private void asignarAnteproyectoEstudiante() {
        if (alumno != null && alumno.getIdAnteproyecto() == 0) {
            if (numeroEstudiantes > 0) {
                int codigoRespuesta = AlumnoDAO.asignarAnteproyectoAlumno(idAnteproyecto, alumno.getIdAlumno());
                switch (codigoRespuesta) {
                    case Constantes.ERROR_CONEXION:
                        Utilidades.mostrarDialogoSimple("Error de Conexión",
                                "Alumno no encontrado",
                                Alert.AlertType.ERROR);
                        break;
                    case Constantes.ERROR_CONSULTA:
                        Utilidades.mostrarDialogoSimple("Error en la información",
                                "Alumno no encontrado",
                                Alert.AlertType.WARNING);
                        break;
                    case Constantes.OPERACION_EXITOSA:
                        AnteproyectoDAO.actualizarEstadoAnteproyecto(Constantes.ASIGNADO, idAnteproyecto);
                        UsuarioDAO.actualizarTipoUsuario(Constantes.ESTUDIANTE_CON_ANTEPROYECTO, alumno.getIdUsuario());
                        Utilidades.mostrarDialogoSimple("Alumno asignado a anteproyecto",
                                "El alumno fue asignado al anteproyecto correctamente",
                                Alert.AlertType.INFORMATION);
                        UsuarioDAO.actualizarTipoUsuario(Constantes.ESTUDIANTE_CON_ANTEPROYECTO, alumno.getIdUsuario());
                        numeroEstudiantes = numeroEstudiantes - 1;
                        resetearPantalla();
                        break;
                }
            } else {
                Utilidades.mostrarDialogoSimple("Error de asignación",
                        "Se han agregado todos los estudiantes posibles", Alert.AlertType.WARNING);
            }
        } else {
            if(alumno == null){
                Utilidades.mostrarDialogoSimple("Error de busqueda", "Primero busca a un alumno", 
                        Alert.AlertType.WARNING);
            } else{
                Utilidades.mostrarDialogoSimple("Alumno con anteproyecto", "El alumno ya cuenta con un anteproyecto", 
                        Alert.AlertType.WARNING);
            }
        }

    }

    @FXML
    private void clicBtnBuscarEstudiante(MouseEvent event) {
        validarCampoMatricula();
    }

    @FXML
    private void clicBtnAsignarEstudiante(ActionEvent event) {
        asignarAnteproyectoEstudiante();
    }

    @FXML
    private void clicBtnCancelar(ActionEvent event) {
        resetearPantalla();
    }

}
