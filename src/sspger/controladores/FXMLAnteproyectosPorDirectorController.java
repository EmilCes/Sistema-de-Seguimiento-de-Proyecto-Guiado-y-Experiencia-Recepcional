package sspger.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import sspger.modelos.dao.AnteproyectoDAO;
import sspger.modelos.pojo.Anteproyecto;
import sspger.modelos.pojo.AnteproyectoRespuesta;
import sspger.utils.Constantes;
import sspger.utils.Utilidades;

public class FXMLAnteproyectosPorDirectorController implements Initializable {

    @FXML
    private AnchorPane apListaAnteproyectosPorProfesor;
    @FXML
    private VBox vbAnteproyectos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            buscarAnteproyectos(1);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void buscarAnteproyectos(int idDirector) throws IOException {
        AnteproyectoRespuesta anteproyectoRespuesta = AnteproyectoDAO.obtenerAnteproyectosPorDirector(idDirector);
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

                for (Anteproyecto anteproyecto : anteproyectos) {
                    FXMLLoader cargadorAnteproyectoFragmento = new FXMLLoader(getClass().getResource("/sspger/vistas/FXMLAnteproyectoFragmento.fxml"));
                    Pane anteproyectoFragmento = cargadorAnteproyectoFragmento.load();
                    FXMLAnteproyectoFragmentoController anteproyectoFragmentoController = cargadorAnteproyectoFragmento.getController();
                    anteproyectoFragmentoController.cargarInformacionAnteproyecto(anteproyecto.getNombreProyectoInvestigacion(), 
                                                                                  anteproyecto.getNombreProfesor());
                    vbAnteproyectos.getChildren().add(anteproyectoFragmento);
                }
        }
    }

}
