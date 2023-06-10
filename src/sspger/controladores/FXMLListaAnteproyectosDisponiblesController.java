package sspger.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import sspger.modelos.dao.AnteproyectoDAO;
import sspger.modelos.pojo.Anteproyecto;
import sspger.modelos.pojo.AnteproyectoRespuesta;
import sspger.utils.Constantes;
import sspger.utils.Utilidades;

public class FXMLListaAnteproyectosDisponiblesController implements Initializable {

    @FXML
    private AnchorPane apListaAnteproyectos;
    @FXML
    private ScrollPane spListaAnteproyectos;
    @FXML
    private VBox vbAnteproyectos;
    @FXML
    private Pane pnAnteproyectosNoDisponibles;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            mostrarAnteproyectosDisponibles();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Utilidades.mostrarDialogoSimple("Error de Información", "Hubo un error con la información. "
                    + "Intente de nuevo o hágalo más tarde", Alert.AlertType.ERROR);
        }
    }

    private void mostrarAnteproyectosDisponibles() throws IOException {
        spListaAnteproyectos.setVisible(true);
        vbAnteproyectos.getChildren().clear();
        AnteproyectoRespuesta anteproyectoRespuesta = AnteproyectoDAO.obtenerAnteproyectosPorEstado(Constantes.DISPONILE);
        switch (anteproyectoRespuesta.getCodigoRespuesta()) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de Conexión", "No se pudo conectar con la base de datos. "
                        + "Intente de nuevo o hágalo más tarde", Alert.AlertType.ERROR);
                break;
            case (Constantes.ERROR_CONSULTA):
                Utilidades.mostrarDialogoSimple("Error de Consulta", "Error en la consulta", Alert.AlertType.WARNING);
                break;
            case (Constantes.OPERACION_EXITOSA):
                ArrayList<Anteproyecto> anteproyectos = anteproyectoRespuesta.getAnteproyectos();
                ObservableList<Anteproyecto> observableAnteproyectos = FXCollections.observableArrayList(anteproyectos);
                vbAnteproyectos.prefHeightProperty().bind(Bindings.size(observableAnteproyectos).multiply(150));
                vbAnteproyectos.minHeightProperty().bind(Bindings.createDoubleBinding(
                        () -> Math.max(vbAnteproyectos.getHeight(), 490),
                        vbAnteproyectos.heightProperty()
                ));
                if (anteproyectos.size() > 0) {
                    for (Anteproyecto anteproyecto : anteproyectos) {
                        FXMLLoader cargadorAnteproyectoFragmento = new FXMLLoader(getClass().getResource("/sspger/vistas/FXMLAnteproyectoDisponibleFragmento.fxml"));
                        Pane anteproyectoFragmento = cargadorAnteproyectoFragmento.load();
                        FXMLAnteproyectoDisponibleFragmentoController anteproyectoFragmentoController = cargadorAnteproyectoFragmento.getController();
                        anteproyectoFragmentoController.cargarInformacionAnteproyecto(anteproyecto, apListaAnteproyectos);
                        vbAnteproyectos.getChildren().add(anteproyectoFragmento);
                    }
                } else {
                    spListaAnteproyectos.setVisible(false);
                    pnAnteproyectosNoDisponibles.setVisible(true);
                }

        }
    }

}
