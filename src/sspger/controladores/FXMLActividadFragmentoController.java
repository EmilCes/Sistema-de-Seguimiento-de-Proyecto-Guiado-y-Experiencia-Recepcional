package sspger.controladores;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import sspger.modelos.pojo.Actividad;
import sspger.modelos.pojo.Usuario;
import sspger.utils.Constantes;
import sspger.utils.UsuarioSingleton;
import sspger.utils.Utilidades;

public class FXMLActividadFragmentoController implements Initializable {

    @FXML
    private Pane pnActividad;
    @FXML
    private Label lbNombreAnteproyecto;
    @FXML
    private Label lbFechaEntrega;
    @FXML
    private Label lbFechaIncio;
    @FXML
    private Label lbCalificacion;
    @FXML
    private Button btnVerActividad;
    @FXML
    private Button btnModificarActividad;
    @FXML
    private Label lbCantidad;
    @FXML
    private Button btnCalificar;
    @FXML
    private Button btnEntregar;
    @FXML
    private Button btnModificarEntrega;

    private AnchorPane apPadre;
    private int idActividad;
    private int idAnteproyecto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ocultarBotones();
    }

    @FXML
    private void clicBtnVerActividad(ActionEvent event) {
    }

    @FXML
    private void clicBtnModificarActividad(ActionEvent event) {
        FXMLModificarActividadController modificarActividadController = Utilidades.cambiarPaneObtenerControlador(apPadre, "/sspger/vistas/FXMLModificarActividad.fxml");
        modificarActividadController.cargarInformacionActividad(idActividad, idAnteproyecto);
    }

    @FXML
    private void clicBtnCalificar(ActionEvent event) {
        FXMLCalificarActividadController calificarActividadController = Utilidades.cambiarPaneObtenerControlador(apPadre, "/sspger/vistas/FXMLCalificarActividad.fxml");
        calificarActividadController.cargarInformacionEntrega(idActividad, idAnteproyecto);
    }

    @FXML
    private void clicBtnEntregar(ActionEvent event) {
        FXMLEntregarActividadFormularioController entregarActividadFormularioController = Utilidades.cambiarPaneObtenerControlador(apPadre, "/sspger/vistas/FXMLEntregarActividadFormulario.fxml");
        entregarActividadFormularioController.cargarInformacionActividad(idActividad, idAnteproyecto);
    }

    @FXML
    private void clicBtnModificarEntrega(ActionEvent event) {
        FXMLEntregarActividadFormularioController entregarActividadFormularioController = Utilidades.cambiarPaneObtenerControlador(apPadre, "/sspger/vistas/FXMLEntregarActividadFormulario.fxml");
        entregarActividadFormularioController.cargarInformacionEntrega(idActividad, idAnteproyecto);
    }

    public void configurarFragmentoActividad(Actividad actividad, AnchorPane apPadre) {
        this.idActividad = actividad.getIdActividad();
        this.apPadre = apPadre;
        this.idAnteproyecto = actividad.getIdAnteproyecto();
        lbNombreAnteproyecto.setText(actividad.getTitulo());
        lbFechaIncio.setText(actividad.getFechaInicio());
        lbFechaEntrega.setText(actividad.getFechaFin());

        Usuario usuario = UsuarioSingleton.getInstancia().getUsuario();

        if (usuario.getIdTipoUsuario() == Constantes.DIRECTOR) {
            switch (actividad.getIdEstadoActividad()) {
                case Constantes.SIN_ENTREGA:
                    btnModificarActividad.setVisible(true);
                    break;
                case Constantes.CON_ENTREGA:
                    btnCalificar.setVisible(true);
                    break;
                case Constantes.CALIFICADA:
                    lbCalificacion.setVisible(true);
                    lbCantidad.setVisible(true);
                    lbCantidad.setText(String.valueOf(actividad.getCalificacion()));
                    break;
            }
        }
        if (usuario.getIdTipoUsuario() == Constantes.ESTUDIANTE_CON_ANTEPROYECTO) {
            switch (actividad.getIdEstadoActividad()) {
                case Constantes.SIN_ENTREGA:
                    btnEntregar.setVisible(true);
                    break;
                case Constantes.CON_ENTREGA:
                    btnModificarEntrega.setVisible(true);
                    break;
                case Constantes.CALIFICADA:
                    lbCalificacion.setVisible(true);
                    lbCantidad.setVisible(true);
                    lbCantidad.setText(String.valueOf(actividad.getCalificacion()));
                    break;
            }
            String fechaFinActividad = actividad.getFechaFin();
            // Crear un formateador para convertir el string en LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaTerminoActividad = LocalDate.parse(fechaFinActividad, formatter);

            // Comparar la fecha actual con la fecha de finalización
            LocalDate fechaActual = LocalDate.now();
            if (fechaActual.isAfter(fechaTerminoActividad)) {
                // La fecha actual es posterior a la fecha de finalización,
                // por lo tanto, deshabilitar los botones de Entregar y Modificar Entrega
                btnEntregar.setDisable(true);
                btnModificarEntrega.setDisable(true);
            }
        }

    }

    private void ocultarBotones() {
        btnCalificar.setVisible(false);
        btnEntregar.setVisible(false);
        btnModificarActividad.setVisible(false);
        btnModificarEntrega.setVisible(false);
        lbCalificacion.setVisible(false);
        lbCantidad.setVisible(false);
        btnVerActividad.setVisible(false);
    }

}
