
package sspger.controladores;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sspger.modelos.dao.ActividadDAO;
import sspger.modelos.dao.AnteproyectoDAO;
import sspger.modelos.pojo.Actividad;
import sspger.modelos.pojo.Anteproyecto;
import sspger.modelos.pojo.AnteproyectoRespuesta;
import sspger.modelos.pojo.Usuario;
import sspger.utils.Constantes;
import sspger.utils.UsuarioSingleton;
import sspger.utils.Utilidades;


public class FXMLCrearActividadController implements Initializable {


    @FXML
    private TextArea taDescripcion;
    @FXML
    private ComboBox<Anteproyecto> cbAnteproyecto;
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
    
    private ObservableList<Anteproyecto> anteproyectos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarCbAnteproyectos();
    }    

    @FXML
    private void clicBtnCrearActividad(ActionEvent event) {
        resetearEstilos();
        validarCampos();
    }
    

    private boolean validarCampos() {
        boolean camposValidos = true;

        String titulo = tfTitulo.getText();
        String descripcion = taDescripcion.getText();
        LocalDate fechaInicio = dpFechaInicio.getValue();
        LocalDate fechaFin = dpFechaInicio.getValue();
        Anteproyecto opcionSeleccionada = cbAnteproyecto.getValue();
        

        if (titulo.isEmpty()) {
            tfTitulo.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (descripcion.isEmpty()) {
            taDescripcion.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (fechaInicio == null) {
            dpFechaInicio.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (fechaFin == null) {
            dpFechaFin.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

       /* if (opcionSeleccionada == null) {
            cbAnteproyecto.setStyle("-fx-border-color: red");
            camposValidos = false;
        }*/

        if (camposValidos){
            guardarActividad();
        }
        return camposValidos;
    }
    
     private void resetearEstilos() {
        tfTitulo.setStyle("");
        taDescripcion.setStyle("");
        dpFechaInicio.setStyle("");
        dpFechaFin.setStyle("");
        cbAnteproyecto.setStyle("");
    }
    
    private void limpiarCampos() {
        tfTitulo.setText("");
        taDescripcion.setText("");
        dpFechaInicio.setValue(null);
        dpFechaFin.setValue(null);
        cbAnteproyecto.setValue(null);
    }

    
   
    private void guardarActividad() {
        String titulo = tfTitulo.getText();
        String descripcion = taDescripcion.getText();
        LocalDate fechaInicio = dpFechaInicio.getValue();
        LocalDate fechaFin = dpFechaFin.getValue();
        Anteproyecto opcionSeleccionada = cbAnteproyecto.getValue();
        int idAnteproyecto = 0;

        if (opcionSeleccionada != null) {
            idAnteproyecto = opcionSeleccionada.getIdAnteproyecto();
        }

        Actividad actividad = new Actividad(titulo, descripcion, descripcion, descripcion, idAnteproyecto);
        int codigoRespuesta = ActividadDAO.guardarActividad(actividad);

        switch (codigoRespuesta) {
            case(Constantes.ERROR_CONEXION):
                Utilidades.mostrarDialogoSimple("Error de Conexión", "Error en la conexción", Alert.AlertType.ERROR);
                break;
            case(Constantes.ERROR_CONSULTA):
                Utilidades.mostrarDialogoSimple("Error de Consulta", "Error en la consulta", Alert.AlertType.WARNING);
                break;
            case(Constantes.OPERACION_EXITOSA):
                Utilidades.mostrarDialogoSimple("Actividad Creada", "La actividad ha sido creada con exito", Alert.AlertType.INFORMATION);
        }
       
        limpiarCampos();
    }
    
    private void configurarCbAnteproyectos(){
        Usuario usuario = UsuarioSingleton.getInstancia().getUsuario();
        int idProfesor = usuario.getIdUsuario();
         anteproyectos = FXCollections.observableArrayList();
         AnteproyectoRespuesta anteproyectosBD = AnteproyectoDAO.obtenerAnteproyectosPorDirector(idProfesor);
         switch (anteproyectosBD.getCodigoRespuesta()) {
            case(Constantes.ERROR_CONEXION):
                Utilidades.mostrarDialogoSimple("Error de Conexión", "Error en la conexción", Alert.AlertType.ERROR);
                break;
            case(Constantes.ERROR_CONSULTA):
                Utilidades.mostrarDialogoSimple("Error de Consulta", "Error en la consulta", Alert.AlertType.WARNING);
                break;
            case(Constantes.OPERACION_EXITOSA):
                anteproyectos.addAll(anteproyectosBD.getAnteproyectos());
                cbAnteproyecto.setItems(anteproyectos);
        }
    }
   
    
}
