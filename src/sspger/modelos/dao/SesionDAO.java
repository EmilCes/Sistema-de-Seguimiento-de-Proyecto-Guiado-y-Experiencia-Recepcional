package sspger.modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sspger.modelos.ConexionBD;
import sspger.modelos.pojo.Usuario;
import sspger.utils.Constantes;



public class SesionDAO {

    public static Usuario verificarUsuarioSesion(String usuario, String password){
        Usuario usuarioVerificado = new Usuario();
        Connection conexion = ConexionBD.abrirConexionBD();
        if(conexion != null){
            try{
                String consulta = "SELECT idUsuario, nombre, apellidoPaterno, apellidoMaterno, correoInstitucional, " +
                                  "numeroTelefonico, nombreUsuario, password, imagenUsuario, idTipoUsuario FROM Usuarios " +
                                  "WHERE nombreUsuario = ? AND password = ?";
                PreparedStatement prepararSentencia = conexion.prepareStatement(consulta);
                prepararSentencia.setString(1, usuario);
                prepararSentencia.setString(2, password);
                ResultSet resultado = prepararSentencia.executeQuery();
                usuarioVerificado.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
                if(resultado.next()){
                    usuarioVerificado.setIdUsuario(resultado.getInt("idUsuario"));
                    usuarioVerificado.setNombre(resultado.getString("nombre"));
                    usuarioVerificado.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                    usuarioVerificado.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                    usuarioVerificado.setCorreoInstitucional(resultado.getString("correoInstitucional"));
                    usuarioVerificado.setNumeroTelefonico(resultado.getString("numeroTelefonico"));
                    usuarioVerificado.setNombreUsuario(resultado.getString("nombreUsuario"));
                    usuarioVerificado.setPassword(resultado.getString("password"));
                    usuarioVerificado.setIdTipoUsuario(resultado.getInt("idTipoUsuario"));
                }
                conexion.close();
            } catch(SQLException ex){
                System.err.println(ex.getMessage());
                usuarioVerificado.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else{
            usuarioVerificado.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return usuarioVerificado;
    }    
}
