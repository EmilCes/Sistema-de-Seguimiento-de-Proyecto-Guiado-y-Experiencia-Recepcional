
package sspger.controladores;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sspger.modelos.dao.AlumnoDAO;
import sspger.modelos.pojo.AvanceAnteproyecto;
import sspger.modelos.dao.AnteproyectoDAO;
import sspger.modelos.dao.AvanceAnteproyectoDAO;
import sspger.modelos.pojo.Alumno;
import sspger.modelos.pojo.Usuario;
import sspger.utils.Utilidades;


public class FXMLAnteproyectoAvancesController implements Initializable {

    @FXML
    private AnchorPane apAvancesAnteproyecto;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnVerActividades;
    @FXML
    private Label lbAnteproyecto;
    @FXML
    private Label lbDirector;
    @FXML
    private Label lbTotalActividades;
    @FXML
    private Label lbPromedioGeneral;
    @FXML
    private ComboBox<String> cbEstudiantes;
    
    private int idAnteproyecto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void clicBtnVolver(ActionEvent event) {
        Utilidades.cambiarPane(apAvancesAnteproyecto, "/sspger/vistas/FXMLListaAnteproyectosDelDirector.fxml");
    }

    @FXML
    private void clicBtnVerActividades(ActionEvent event) {
       //FXMLVerActividadesController verActividadesController = Utilidades.cambiarPaneObtenerControlador(apAvancesAnteproyecto, "/sspger/vistas/FXMLVerActividades.fxml");
      // verActividadesController.setIdAnteproyecto(idAnteproyecto);
        Utilidades.cambiarPane(apAvancesAnteproyecto, "/sspger/vistas/FXMLVerActividades.fxml");
    }
    
    public void cargarAvanceAnteproyecto(int idAnteproyecto){
        this.idAnteproyecto = idAnteproyecto;
        lbAnteproyecto.setText(AvanceAnteproyectoDAO.obtenerNombreAnteproyectoPorIdAnteproyecto(idAnteproyecto));
        lbDirector.setText(AvanceAnteproyectoDAO.obtenerNombreProfesorPorIdAnteproyecto(idAnteproyecto));
        lbTotalActividades.setText(String.valueOf(AvanceAnteproyectoDAO.obtenerCantidadActividadesPorAnteproyecto(idAnteproyecto)));
        
        ArrayList<String> nombresEstudiantes = AvanceAnteproyectoDAO.obtenerNombresCompletosPorIdAnteproyecto(idAnteproyecto);
        cbEstudiantes.getItems().addAll(nombresEstudiantes);
        
    }
    
}
