package sspger.controladores;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sspger.modelos.dao.ActividadDAO;
import sspger.modelos.dao.EntregaActividadDAO;
import sspger.modelos.pojo.Actividad;
import sspger.modelos.pojo.EntregaActividad;
import sspger.utils.Constantes;
import sspger.utils.Utilidades;

public class FXMLEntregarActividadFormularioController implements Initializable {

    @FXML
    private AnchorPane apEntregarActividadFormulario;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbFechaInicio;
    @FXML
    private Label lbFechaFin;
    @FXML
    private Button btnSubirArchivo;
    @FXML
    private Pane paneArchivoConfirmacion;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnEntregar;
    @FXML
    private TextArea taComentarios;
    @FXML
    private Text txDescripcion;

    private int idActividad;
    private int idAnteproyecto;
    private File archivoSeleccionado;

    @FXML
    private Label lbNombreArchivo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void clicBtnSubirArchivo(ActionEvent event) {
        FileChooser dialogoArchivo = new FileChooser();
        dialogoArchivo.setTitle("Selecciona un archivo");
        FileChooser.ExtensionFilter filtroPDF = new FileChooser.ExtensionFilter("Archivos PDF (*.pdf)", "*.pdf");
        dialogoArchivo.getExtensionFilters().add(filtroPDF);
        Stage escenarioActual = (Stage) taComentarios.getScene().getWindow();
        archivoSeleccionado = dialogoArchivo.showOpenDialog(escenarioActual);

        if (archivoSeleccionado != null) {
            paneArchivoConfirmacion.setVisible(true);
            lbNombreArchivo.setText(archivoSeleccionado.getName());
        }
    }

    @FXML
    private void clicBtnVolver(ActionEvent event) {
        FXMLVerActividadesController verActividadesController = Utilidades.cambiarPaneObtenerControlador(apEntregarActividadFormulario, "/sspger/vistas/FXMLVerActividades.fxml");
        verActividadesController.setIdAnteproyecto(idAnteproyecto);
    }

    @FXML
    private void clicBtnEntregar(ActionEvent event) {
        resetearEstilos();
        try {
            validarCampos();
        } catch (IOException ex) {
            Utilidades.mostrarDialogoSimple("Error", "Error en la conexión", Alert.AlertType.ERROR);
        }
    }

    public void cargarInformacionActividad(int idActividad, int idAnteproyecto) {
        this.idActividad = idActividad;
        this.idAnteproyecto = idAnteproyecto;
        Actividad actividad = ActividadDAO.obtenerInformacionActividaPorIdActividad(idActividad);
        lbTitulo.setText(actividad.getTitulo());
        lbFechaFin.setText(actividad.getFechaFin());
        lbFechaInicio.setText(actividad.getFechaInicio());
        txDescripcion.setText(actividad.getDescripcion());
    }

    private void validarCampos() throws IOException {
        boolean camposValidos = true;

        if (taComentarios.getText().isEmpty()) {
            taComentarios.setStyle("-fx-border-color: red");
            camposValidos = false;
        }

        if (paneArchivoConfirmacion.isVisible() == false) {
            Utilidades.mostrarDialogoSimple("Archivo Necesario", "Es necesario que subas un archivo para que pueda ser evaluado", Alert.AlertType.WARNING);
            camposValidos = false;
        }

        if (camposValidos) {
            EntregaActividad entregaActividad = new EntregaActividad();
            entregaActividad.setIdEstado(Constantes.CON_ENTREGA);
            entregaActividad.setIdActividad(idActividad);
            entregaActividad.setComentarios(taComentarios.getText());
            entregaActividad.setArchivo(Files.readAllBytes(archivoSeleccionado.toPath()));
            guardarEntregaActividad(entregaActividad);
        }

    }

    private void guardarEntregaActividad(EntregaActividad entregaActividad) {
        int codigoRespuesta = EntregaActividadDAO.guardarEntregaActividad(entregaActividad);
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
                Utilidades.mostrarDialogoSimple("Su entrega ha sido guardada en el sistema",
                        "Entrega guardada correctamente",
                        Alert.AlertType.INFORMATION);
                FXMLVerActividadesController verActividadesController = Utilidades.cambiarPaneObtenerControlador(apEntregarActividadFormulario, "/sspger/vistas/FXMLVerActividades.fxml");
                verActividadesController.setIdAnteproyecto(idAnteproyecto);
                break;
        }
    }

    private void resetearEstilos() {
        taComentarios.setStyle("");
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
                        "La información de la entrega no puede ser recuperada, intentelo más tarde",
                        Alert.AlertType.WARNING);
                break;
            case Constantes.OPERACION_EXITOSA:
                taComentarios.setText(entregaActividad.getComentarios());
                paneArchivoConfirmacion.setVisible(true);
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
}

