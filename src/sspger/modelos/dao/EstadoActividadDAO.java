
package sspger.modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sspger.modelos.ConexionBD;
import sspger.modelos.pojo.EstadoActividad;
import sspger.modelos.pojo.EstadoActividadRespuesta;
import sspger.utils.Constantes;

public class EstadoActividadDAO {
    
    public static EstadoActividadRespuesta obtenerEstadosActividad() {
        EstadoActividadRespuesta estadoActividadRespuesta = new EstadoActividadRespuesta();
        Connection conexion = ConexionBD.abrirConexionBD();
        if (conexion != null) {
            try {
                String consulta = "SELECT idEstado, estado FROM EstadoActividad";
                PreparedStatement prepararSentencia = conexion.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                ArrayList<EstadoActividad> estadoActividadConsulta = new ArrayList();
                estadoActividadRespuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
                while(resultado.next()){
                    EstadoActividad estadoActividad = new EstadoActividad();
                    estadoActividad.setIdEstado(resultado.getInt("idEstado"));
                    estadoActividad.setDescripcion(resultado.getString("estado"));                 
                    estadoActividadConsulta.add(estadoActividad);
                }
                estadoActividadRespuesta.setEstadosActividad(estadoActividadConsulta);
            } catch (SQLException e) {
                estadoActividadRespuesta.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else{
            estadoActividadRespuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return estadoActividadRespuesta;
    }
}
