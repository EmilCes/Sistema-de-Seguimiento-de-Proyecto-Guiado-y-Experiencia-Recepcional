package sspger.modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sspger.modelos.ConexionBD;
import sspger.modelos.pojo.EstadoAnteproyecto;
import sspger.modelos.pojo.EstadoAnteproyectoRespuesta;
import sspger.utils.Constantes;

public class EstadoAnteproyectoDAO {

    public static EstadoAnteproyecto obtenerEstadoAnteproyectoPorId(int idEstadoAnteproyecto) {
        EstadoAnteproyecto estadoAnteproyecto = new EstadoAnteproyecto();
        Connection conexion = ConexionBD.abrirConexionBD();
        if (conexion != null) {
            try {
                String consulta = "SELECT idEstado, descripcion FROM EstadoAnteproyecto WHERE idEstado = ?";
                PreparedStatement prepararSentencia = conexion.prepareStatement(consulta);
                prepararSentencia.setInt(1, idEstadoAnteproyecto);
                ResultSet resultado = prepararSentencia.executeQuery();
                estadoAnteproyecto.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
                if (resultado.next()) {
                    estadoAnteproyecto.setIdEstado(resultado.getInt("idEstado"));
                    estadoAnteproyecto.setDescripcion(resultado.getString("descripcion"));
                }
                conexion.close();
            } catch (SQLException e) {
                estadoAnteproyecto.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else {
            estadoAnteproyecto.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return estadoAnteproyecto;
    }
    
     public static EstadoAnteproyectoRespuesta obtenerEstadosAnteproyecto(){
        EstadoAnteproyectoRespuesta estadoAnteproyectoRespuesta = new EstadoAnteproyectoRespuesta();
        estadoAnteproyectoRespuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try {
                String consulta = "SELECT idEstado, descripcion FROM EstadoAnteproyecto;";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                ArrayList<EstadoAnteproyecto> estadoAnteproyectoConsulta = new ArrayList();
                while(resultado.next()){
                    EstadoAnteproyecto estadoAnteproyecto = new EstadoAnteproyecto();
                    estadoAnteproyecto.setIdEstado(resultado.getInt("idEstado"));
                    estadoAnteproyecto.setDescripcion(resultado.getString("descripcion"));
                    estadoAnteproyectoConsulta.add(estadoAnteproyecto);
                }
                estadoAnteproyectoRespuesta.setEstadosAnteproyecto(estadoAnteproyectoConsulta);
            } catch (SQLException e) {
                estadoAnteproyectoRespuesta.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else{
            estadoAnteproyectoRespuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return estadoAnteproyectoRespuesta;
    }
    
}
