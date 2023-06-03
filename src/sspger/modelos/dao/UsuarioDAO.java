
package sspger.modelos.dao;

import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import sspger.modelos.ConexionBD;
import sspger.modelos.pojo.Usuario;
import sspger.utils.Constantes;

public class UsuarioDAO {
    
    
    public static int guardarUsuario(Usuario usuario){
        int respuesta;
          Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try{
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
                
                /*
                BufferedImage bufferedImage = ImageIO.read(usuario.getImagen());
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", outputStream);
                byte[] imagenBytes = outputStream.toByteArray();
*/
                
                prepararSentencia.setBytes(8, null);
                prepararSentencia.setInt(9, usuario.getIdTipoUsuario());
                int filasAfectadas = prepararSentencia.executeUpdate();
                respuesta = (filasAfectadas == 1) ? Constantes.OPERACION_EXITOSA : Constantes.ERROR_CONSULTA; //Operador ternario
                conexionBD.close();
            } catch(SQLException e){
                System.out.print(e.getMessage());
                respuesta = Constantes.ERROR_CONSULTA;
            }
        } else{
            respuesta = Constantes.ERROR_CONEXION;
        }
        
        return respuesta;
    }
}
