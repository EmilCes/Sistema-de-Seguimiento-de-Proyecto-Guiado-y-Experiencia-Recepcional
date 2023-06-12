
package sspger.controladores;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sspger.modelos.dao.ActividadDAO;
import sspger.modelos.pojo.Actividad;
import sspger.utils.Constantes;
import sspger.utils.Utilidades;

public class FXMLModificarActividadController implements Initializable {

    @FXML
    private AnchorPane apModificarActividad;
    @FXML
    private TextField tfNombreActividad;
    @FXML
    private TextArea taDescripcionActividad;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaEntrega;

    private String fechaInicio;
    private String fechaFin;
    private int idActividad;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicBtnGuardar(ActionEvent event) {
        validarCampos();
    }

    @FXML
    private void clicBtnCancelar(ActionEvent event) {
    }
    
    
    private void limpiarCampos() {
        tfNombreActividad.setText("");
        taDescripcionActividad.setText("");
        dpFechaEntrega.setValue(null);
        dpFechaInicio.setValue(null);
    }

    private void resetearEstilos() {
        tfNombreActividad.setStyle("");
        taDescripcionActividad.setStyle("");
        dpFechaEntrega.setStyle("");
        dpFechaInicio.setStyle("");
    }

    private void validarCampos() {
        resetearEstilos();
        boolean camposValidos = true;

        if (tfNombreActividad.getText().isEmpty()) {
            tfNombreActividad.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (taDescripcionActividad.getText().isEmpty()) {
            taDescripcionActividad.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (dpFechaInicio.getValue() == null) {
            dpFechaInicio.setStyle("-fx-border-color: red");
            camposValidos = false;

        }

        if (dpFechaEntrega.getValue() == null) {
            dpFechaEntrega.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (camposValidos && validarFechas()) {
            Actividad actividad = new Actividad();
            actividad.setTitulo(tfNombreActividad.getText());
            actividad.setDescripcion(taDescripcionActividad.getText());
            actividad.setFechaInicio(fechaInicio);
            actividad.setFechaFin(fechaFin);
            actividad.setIdActividad(idActividad);
            modificarActividad(actividad);
        }

    }

    private boolean validarFechas() {
        boolean fechasValidas = true;
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaInicioLD = dpFechaInicio.getValue();
        LocalDate fechaEntregaLD = dpFechaEntrega.getValue();

        if (!fechaInicioLD.isAfter(fechaActual) && !fechaInicioLD.isEqual(fechaActual)) {
            fechasValidas = false;
            dpFechaInicio.setStyle("-fx-border-color: red");
            Utilidades.mostrarDialogoSimple("Fecha Inicio",
                        "La fecha de inicio no puede ser antes que hoy.",
                        Alert.AlertType.WARNING);
        }
                
        if (!fechaEntregaLD.isAfter(fechaActual)) {

            fechasValidas = false;
            dpFechaEntrega.setStyle("-fx-border-color: red");
            Utilidades.mostrarDialogoSimple("Fecha Entrega",
                        "La fecha de entrea no puede ser antes que hoy.",
                        Alert.AlertType.WARNING);
        }
        
        if (!fechaEntregaLD.isAfter(fechaInicioLD)) {
            fechasValidas = false;       
            dpFechaEntrega.setStyle("-fx-border-color: red");
            Utilidades.mostrarDialogoSimple("Fecha Entrega",
                        "La fecha de entrega no puede ser antes que la fecha de inicio.",
                        Alert.AlertType.WARNING); 
        }
        
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        fechaInicio = fechaInicioLD.format(formateador);
        fechaFin = fechaEntregaLD.format(formateador);
        
        return fechasValidas;

        
    }
    
    public void cargarInformacionActividad(int idActividad){
        this.idActividad = idActividad;
        Actividad actividad = ActividadDAO.obtenerInformacionActividaPorIdActividad(idActividad);
        switch (actividad.getCodigoRespuesta()) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión",
                        "No se pudo conectar con la base de datos. Intente de nuevo o hágalo más tarde.",
                        Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error en la información",
                        "La información de la actividad no puede ser guardada, verifique su información",
                        Alert.AlertType.WARNING);
                break;
            case Constantes.OPERACION_EXITOSA:
                tfNombreActividad.setText(actividad.getTitulo());
                taDescripcionActividad.setText(actividad.getDescripcion());
                LocalDate fechaInicio = LocalDate.parse(actividad.getFechaInicio(), DateTimeFormatter.ISO_LOCAL_DATE);
                dpFechaInicio.setValue(fechaInicio);
                LocalDate fechaFin = LocalDate.parse(actividad.getFechaFin(), DateTimeFormatter.ISO_LOCAL_DATE);
                dpFechaEntrega.setValue(fechaFin);
                break;
        }
        
    }
    
        private void modificarActividad(Actividad actividad) {
        int codigoRespuesta = ActividadDAO.modificarActividad(actividad);
        switch (codigoRespuesta) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión",
                        "No se pudo conectar con la base de datos. Intente de nuevo o hágalo más tarde.",
                        Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error en la información",
                        "La información de la actividad no puede ser modificada, verifique su información",
                        Alert.AlertType.WARNING);
                break;
            case Constantes.OPERACION_EXITOSA:
                Utilidades.mostrarDialogoSimple("Actividad Modificada",
                        "La actividad fue modificada correctamente",
                        Alert.AlertType.INFORMATION);
                break;
        }
    }
}
