package sspger.modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sspger.modelos.ConexionBD;
import sspger.modelos.pojo.TipoAnteproyecto;
import sspger.modelos.pojo.TipoAnteproyectoRespuesta;
import sspger.utils.Constantes;


public class TipoAnteproyectoDAO {
    
     public static TipoAnteproyectoRespuesta obtenerTiposAnteproyectos(){
        TipoAnteproyectoRespuesta tipoAnteproyectoRespuesta = new TipoAnteproyectoRespuesta();
        tipoAnteproyectoRespuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try{
                String consulta = "SELECT idTipoProyecto, nombreTipoProyecto FROM TipoAnteproyecto;";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet respuestaBaseDatos = prepararSentencia.executeQuery();
                ArrayList<TipoAnteproyecto> tipoAnteproyectosConsulta = new ArrayList();
                while(respuestaBaseDatos.next()){
                    TipoAnteproyecto tipoAnteproyecto = new TipoAnteproyecto();
                    tipoAnteproyecto.setIdTipoProyecto(respuestaBaseDatos.getInt("idTipoProyecto"));
                    tipoAnteproyecto.setNombreTipoProyecto(respuestaBaseDatos.getString("nombreTipoProyecto"));
                    tipoAnteproyectosConsulta.add(tipoAnteproyecto);
                }
                tipoAnteproyectoRespuesta.setTipoAnteproyectos(tipoAnteproyectosConsulta);
                conexionBD.close();
            } catch(SQLException e){
                tipoAnteproyectoRespuesta.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else{
            tipoAnteproyectoRespuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return tipoAnteproyectoRespuesta;
    }
    
}
