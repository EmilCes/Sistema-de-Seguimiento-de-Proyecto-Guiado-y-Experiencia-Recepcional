package sspger.modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sspger.modelos.ConexionBD;
import sspger.modelos.pojo.Usuario;
import sspger.modelos.pojo.UsuarioRespuesta;
import sspger.utils.Constantes;

public class UsuarioDAO {

    public static int guardarUsuario(Usuario usuario) {
        int respuesta;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if (conexionBD != null) {
            try {
                String sentencia = "INSERT INTO Usuarios (nombre, apellidoPaterno, apellidoMaterno, correoInstitucional, "
                        + "numeroTelefonico, nombreUsuario, password, imagenUsuario, idTipoUsuario) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setString(1, usuario.getNombre());
                prepararSentencia.setString(2, usuario.getApellidoPaterno());
                prepararSentencia.setString(3, usuario.getApellidoMaterno());
                prepararSentencia.setString(4, usuario.getCorreoInstitucional());
                prepararSentencia.setString(5, usuario.getNumeroTelefonico());
                prepararSentencia.setString(6, usuario.getNombreUsuario());
                prepararSentencia.setString(7, usuario.getPassword());
                prepararSentencia.setBytes(8, usuario.getImagen());
                prepararSentencia.setInt(9, usuario.getIdTipoUsuario());
                int filasAfectadas = prepararSentencia.executeUpdate();
                respuesta = (filasAfectadas == 1) ? Constantes.OPERACION_EXITOSA : Constantes.ERROR_CONSULTA; //Operador ternario
                conexionBD.close();
            } catch (SQLException e) {
                System.out.print(e.getMessage());
                respuesta = Constantes.ERROR_CONSULTA;
            }
        } else {
            respuesta = Constantes.ERROR_CONEXION;
        }

        return respuesta;
    }

    public static UsuarioRespuesta obtenerUsuarioPorTipoUsuario(int idTipoUsuario) {
        UsuarioRespuesta usuarioRespuesta = new UsuarioRespuesta();
        usuarioRespuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if (conexionBD != null) {
            try {
                String consulta = "SELECT idUsuario, CONCAT(nombre, ' ', apellidoPaterno, ' ', apellidoMaterno) AS "
                        + "nombreCompleto FROM Usuarios WHERE idTipoUsuario = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idTipoUsuario);
                ResultSet resultado = prepararSentencia.executeQuery();
                ArrayList<Usuario> usuarioConsulta = new ArrayList();
                while (resultado.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(resultado.getInt("idUsuario"));
                    usuario.setNombreCompleto(resultado.getString("nombreCompleto"));
                    usuarioConsulta.add(usuario);
                }
                usuarioRespuesta.setUsuarios(usuarioConsulta);
                conexionBD.close();
            } catch (SQLException e) {
                usuarioRespuesta.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else {
            usuarioRespuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return usuarioRespuesta;
    }

    public static int actualizarTipoUsuario(int idTipoUsuario, int idUsuario) {
        int respuesta;
        Connection conexion = ConexionBD.abrirConexionBD();
        if (conexion != null) {
            try {
                String sentencia = "UPDATE Usuarios SET idTipoUsuario = ? WHERE idUsuario = ?";
                PreparedStatement prepararSentencia = conexion.prepareStatement(sentencia);
                prepararSentencia.setInt(1, idTipoUsuario);
                prepararSentencia.setInt(2, idUsuario);
                int filasAfectadas = prepararSentencia.executeUpdate();
                respuesta = (filasAfectadas == 1) ? Constantes.OPERACION_EXITOSA : Constantes.ERROR_CONSULTA;
            } catch (SQLException e) {
                respuesta = Constantes.ERROR_CONSULTA;
            }
        } else {
            respuesta = Constantes.ERROR_CONEXION;
        }
        return respuesta;
    }
    
    
public static int modificarUsuario(Usuario usuario) {
    int respuesta;
    Connection conexionBD = ConexionBD.abrirConexionBD();
    if (conexionBD != null) {
        try {
            String sentencia = "UPDATE Usuarios SET nombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, " +
                    "correoInstitucional = ?, numeroTelefonico = ?, nombreUsuario = ?, password = ?, " +
                    "imagenUsuario = ?, idTipoUsuario = ? WHERE idUsuario = ?";

            PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
            prepararSentencia.setString(1, usuario.getNombre());
            prepararSentencia.setString(2, usuario.getApellidoPaterno());
            prepararSentencia.setString(3, usuario.getApellidoMaterno());
            prepararSentencia.setString(4, usuario.getCorreoInstitucional());
            prepararSentencia.setString(5, usuario.getNumeroTelefonico());
            prepararSentencia.setString(6, usuario.getNombreUsuario());
            prepararSentencia.setString(7, usuario.getPassword());
            prepararSentencia.setBytes(8, usuario.getImagen());
            prepararSentencia.setInt(9, usuario.getIdTipoUsuario());
            prepararSentencia.setInt(10, usuario.getIdUsuario());
            
            int filasAfectadas = prepararSentencia.executeUpdate();
            respuesta = (filasAfectadas == 1) ? Constantes.OPERACION_EXITOSA : Constantes.ERROR_CONSULTA;
            conexionBD.close();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            respuesta = Constantes.ERROR_CONSULTA;
        }
    } else {
        respuesta = Constantes.ERROR_CONEXION;
    }

    return respuesta;
}


}
