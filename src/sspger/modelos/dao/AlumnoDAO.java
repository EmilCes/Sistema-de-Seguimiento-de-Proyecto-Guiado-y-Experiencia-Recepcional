package sspger.modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sspger.modelos.ConexionBD;
import sspger.modelos.pojo.Alumno;
import sspger.modelos.pojo.AlumnoRespuesta;
import sspger.utils.Constantes;

public class AlumnoDAO {

    public static Alumno obtenerInformacionAlumnoPorMatricula(String matricula) {
        Alumno alumno = new Alumno();
        Connection conexion = ConexionBD.abrirConexionBD();
        if (conexion != null) {
            try {
                String consulta = "SELECT a.idAlumno, a.idUsuario, a.idAnteproyecto, CONCAT(u.nombre, ' ', "
                        + "apellidoPaterno, ' ', apellidoMaterno) AS nombreCompleto, u.imagenUsuario FROM Alumnos a "
                        + "INNER JOIN Usuarios u ON a.idUsuario = u.idUsuario WHERE matricula = ?";
                PreparedStatement prepararSentencia = conexion.prepareStatement(consulta);
                prepararSentencia.setString(1, matricula);
                ResultSet resultado = prepararSentencia.executeQuery();
                alumno.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
                if (resultado.next()) {
                    alumno.setIdAlumno(resultado.getInt("idAlumno"));
                    alumno.setIdUsuario(resultado.getInt("idUsuario"));
                    alumno.setIdAnteproyecto(resultado.getInt("idAnteproyecto"));
                    alumno.setNombreCompleto(resultado.getString("nombreCompleto"));
                    alumno.setImagenUsuario(resultado.getBytes("imagenUsuario"));
                }
                conexion.close();
            } catch (SQLException ex) {
                alumno.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else {
            alumno.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return alumno;
    }

    public static int asignarAnteproyectoAlumno(int idAnteproyecto, int idAlumno) {
        int respuesta;
        Connection conexion = ConexionBD.abrirConexionBD();
        if (conexion != null) {
            try {
                String sentencia = "UPDATE Alumnos SET idAnteproyecto = ? WHERE idAlumno = ?";
                PreparedStatement prepararSentencia = conexion.prepareStatement(sentencia);
                prepararSentencia.setInt(1, idAnteproyecto);
                prepararSentencia.setInt(2, idAlumno);
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
    
    public static AlumnoRespuesta obtenerAlumnosPorIdAnteproyetco(int idAnteproyecto) {
        AlumnoRespuesta alumnoRespuesta = new AlumnoRespuesta();
        alumnoRespuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        Connection conexion = ConexionBD.abrirConexionBD();
        if (conexion != null) {
            try {
                String sentencia = "SELECT CONCAT(u.nombre, ' ', u.apellidoPaterno, ' ', u.apellidoMaterno) AS " +
                                   "nombreCompleto FROM Alumnos a " +
                                   "INNER JOIN Usuarios u ON u.idUsuario = a.idUsuario " +
                                   "WHERE a.idAnteproyecto = ?";
                PreparedStatement prepararSentencia = conexion.prepareStatement(sentencia);
                prepararSentencia.setInt(1, idAnteproyecto);
                ResultSet respuestaBaseDatos = prepararSentencia.executeQuery();
                ArrayList<Alumno> alumnosConsulta = new ArrayList();
                while(respuestaBaseDatos.next()){
                    Alumno alumno = new Alumno();
                    alumno.setNombreCompleto(respuestaBaseDatos.getString("nombreCompleto"));
                    alumnosConsulta.add(alumno);
                }
                alumnoRespuesta.setAlumnos(alumnosConsulta);
                conexion.close();
            } catch(SQLException e){
                alumnoRespuesta.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else{
            alumnoRespuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return alumnoRespuesta;
    }
    
    public static int registrarAlumno(int idUsuario, String matricula) {
        int respuesta;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if (conexionBD != null) {
            try {
                String sentencia = "INSERT INTO Alumnos (idUsuario, matricula) VALUES (?, ?)";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setInt(1, idUsuario);
                prepararSentencia.setString(2, matricula);
                int filasAfectadas = prepararSentencia.executeUpdate();
                respuesta = (filasAfectadas == 1) ? Constantes.OPERACION_EXITOSA : Constantes.ERROR_CONSULTA;
                conexionBD.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                respuesta = Constantes.ERROR_CONSULTA;
            }
        } else {
            respuesta = Constantes.ERROR_CONEXION;
        }
        return respuesta;
    }
    
}