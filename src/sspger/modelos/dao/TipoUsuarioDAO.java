
package sspger.modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sspger.modelos.ConexionBD;
import sspger.modelos.pojo.TipoUsuario;
import sspger.modelos.pojo.TipoUsuarioRespuesta;
import sspger.utils.Constantes;

public class TipoUsuarioDAO {
     public static TipoUsuarioRespuesta obtenerTipoUsuarioRespuesta() {
        TipoUsuarioRespuesta tipoUsuarioRespuesta = new TipoUsuarioRespuesta();
        tipoUsuarioRespuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if (conexionBD != null) {
            try {
                String consulta = "SELECT idTipoUsuario, descripcion FROM TipoUsuario;";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet respuestaBaseDatos = prepararSentencia.executeQuery();
                ArrayList<TipoUsuario> tipoUsuariosConsulta = new ArrayList();
                while (respuestaBaseDatos.next()) {
                    TipoUsuario tipoUsuario = new TipoUsuario();
                    tipoUsuario.setIdTipoUsuario(respuestaBaseDatos.getInt("idTipoUsuario"));
                    tipoUsuario.setDescripcion(respuestaBaseDatos.getString("descripcion"));
                    tipoUsuariosConsulta.add(tipoUsuario);
                }
                tipoUsuarioRespuesta.setTiposUsuarios(tipoUsuariosConsulta);
                conexionBD.close();
            } catch (SQLException e) {
                tipoUsuarioRespuesta.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else {
            tipoUsuarioRespuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return tipoUsuarioRespuesta;
    }
    
   public static TipoUsuario obtenerTipoUsuarioPorId(int id) {
    TipoUsuario tipoUsuario = new TipoUsuario();
    tipoUsuario.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
    Connection conexionBD = ConexionBD.abrirConexionBD();
    if (conexionBD != null) {
        try {
            String consulta = "SELECT idTipoUsuario, descripcion FROM TipoUsuario WHERE idTipoUsuario = ?";
            PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
            prepararSentencia.setInt(1, id);
            ResultSet respuestaBaseDatos = prepararSentencia.executeQuery();
            if (respuestaBaseDatos.next()) {
                tipoUsuario.setIdTipoUsuario(respuestaBaseDatos.getInt("idTipoUsuario"));
                tipoUsuario.setDescripcion(respuestaBaseDatos.getString("descripcion"));
            }
            conexionBD.close();
        } catch (SQLException e) {
            tipoUsuario.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
        }
    } else {
        tipoUsuario.setCodigoRespuesta(Constantes.ERROR_CONEXION);
    }
    return tipoUsuario;
}

}

  
