
package sspger.controladores;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import sspger.modelos.dao.ActividadDAO;
import sspger.modelos.dao.EntregaActividadDAO;
import sspger.modelos.pojo.Actividad;
import sspger.modelos.pojo.EntregaActividad;
import sspger.utils.Constantes;
import sspger.utils.Utilidades;

public class FXMLCalificarActividadController implements Initializable {

    @FXML
    private AnchorPane apCalificarActividad;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbFechaInicio;
    @FXML
    private Label lbFechaFin;
    @FXML
    private Button btnDescargarArchivo;
    @FXML
    private Pane paneArchivoConfirmacion;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnCalificar;
    @FXML
    private TextArea taObservaciones;
    @FXML
    private Label lbNombreArchivo;
    @FXML
    private ComboBox<Integer> cbCalificacion;
    @FXML
    private TextArea taDescripcion;

    private int idActividad;
    private int idAnteproyecto;
    private File archivoSeleccionado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarCbCalificacion();
        taDescripcion.setEditable(false);
    }

    private void resetearEstilos() {
        cbCalificacion.setStyle("");
        taObservaciones.setStyle("");
    }

    private void configurarCbCalificacion() {
        cbCalificacion.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    private void validarCampos() {
        resetearEstilos();
        boolean camposValidos = true;

        if (cbCalificacion.getValue() == null) {
            cbCalificacion.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (taObservaciones.getText().isEmpty()) {
            taObservaciones.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (camposValidos) {
            EntregaActividad entregaActividad = new EntregaActividad();
            entregaActividad.setIdEstado(Constantes.CALIFICADA);
            entregaActividad.setIdActividad(idActividad);
            entregaActividad.setObservaciones(taObservaciones.getText());
            entregaActividad.setCalificacion(cbCalificacion.getValue());
            calificarEntrega(entregaActividad);
        }

    }

    private void calificarEntrega(EntregaActividad entregaActividad) {
        int codigoRespuesta = EntregaActividadDAO.calificarEntregaActividad(entregaActividad);
        switch (codigoRespuesta) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión",
                        "No se pudo conectar con la base de datos. Intente de nuevo o hágalo más tarde.",
                        Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error en la información",
                        "La información de la entrega no puede ser guardada, verifique su información",
                        Alert.AlertType.WARNING);
                break;
            case Constantes.OPERACION_EXITOSA:
                Utilidades.mostrarDialogoSimple("Calificación asignada correctamente",
                        "La calificación ha sido asignada correctamente",
                        Alert.AlertType.INFORMATION);  
                regresar();
                break;
        }
    }

    public void cargarInformacionActividad(int idActividad, int idAnteproyecto) {
        this.idActividad = idActividad;
        this.idAnteproyecto = idAnteproyecto;
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
                lbTitulo.setText(actividad.getTitulo());
                taDescripcion.setText(actividad.getDescripcion());
                lbFechaInicio.setText(actividad.getFechaInicio());
                lbFechaFin.setText(actividad.getFechaFin());
                break;
        }

    }

    public void cargarInformacionEntrega(int idActividad, int idAnteproyecto) {
        EntregaActividad entregaActividad = EntregaActividadDAO.cargarInfomacionEntregaPorIdActividad(idActividad);
        switch (entregaActividad.getCodigoRespuesta()) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión",
                        "No se pudo conectar con la base de datos. Intente de nuevo o hágalo más tarde.",
                        Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error en la información",
                        "La información de la entrega no puede ser recuperada, intentelo mas tarde",
                        Alert.AlertType.WARNING);
                break;
            case Constantes.OPERACION_EXITOSA:
                cargarInformacionActividad(idActividad, idAnteproyecto);
                try {
                    File tempFile = File.createTempFile("temp", ".pdf");  // Crear un archivo temporal
                    Files.write(tempFile.toPath(), entregaActividad.getArchivo());  // Escribir los bytes en el archivo temporal
                    this.archivoSeleccionado = tempFile;  // Asignar el archivo temporal a archivoSeleccionado
                    lbNombreArchivo.setText(tempFile.getName());  // Mostrar el nombre del archivo en la etiqueta
                } catch (IOException ex) {
                    Utilidades.mostrarDialogoSimple("Error",
                            "Error al cargar el archivo: " + ex.getMessage(),
                            Alert.AlertType.ERROR);
                }
                break;
        }
    }

    private void visualizarArchivo() {
        if (archivoSeleccionado != null) {
            try {
                Desktop.getDesktop().open(archivoSeleccionado);
            } catch (IOException e) {
                e.printStackTrace();
                Utilidades.mostrarDialogoSimple("Error",
                        "Error al abrir el archivo PDF",
                        Alert.AlertType.ERROR);
            }
        } else {
            Utilidades.mostrarDialogoSimple("Error",
                    "El archivo PDF no está disponible",
                    Alert.AlertType.WARNING);
        }
    }
    
    private void regresar(){
        FXMLVerActividadesController verActividadesController = Utilidades.cambiarPaneObtenerControlador(apCalificarActividad, "/sspger/vistas/FXMLVerActividades.fxml");
        verActividadesController.setIdAnteproyecto(idAnteproyecto);
    }

    @FXML
    private void clicBtnDescargarArchivo(ActionEvent event) {
        visualizarArchivo();
    }

    @FXML
    private void clicBtnVolver(ActionEvent event) {
        regresar();
    }

    @FXML
    private void clicBtnCalificar(ActionEvent event) {
        validarCampos();
    }

}
