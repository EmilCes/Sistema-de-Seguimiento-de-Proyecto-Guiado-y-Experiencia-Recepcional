package sspger.modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sspger.modelos.ConexionBD;
import sspger.modelos.pojo.Anteproyecto;
import sspger.modelos.pojo.AnteproyectoRespuesta;
import sspger.utils.Constantes;



public class AnteproyectoDAO {

    public static int guardarAnteproyecto(Anteproyecto anteproyectoNuevo){
        
        int respuesta;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try{
                String sentencia = "INSERT INTO Anteproyecto (nombreProyectoInvestigacion, nombreTrabajoRecepcional, " +
                                   "requisitos, resultadosEsperados, numEstudiantes, duracionAproximada, " +
                                   "descripcionTrabajoRecepcional, descripcionProyectoInvestigacion, bibliografiaRecomendada, " +
                                   "lineaInvestigacion, idLAGC, idTipoAnteproyecto, idCuerpoAcademico, idEstado) " +
                                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setString(1, anteproyectoNuevo.getNombreProyectoInvestigacion());
                prepararSentencia.setString(2, anteproyectoNuevo.getNombreTrabajoRecepcional());
                prepararSentencia.setString(3, anteproyectoNuevo.getRequisitos());
                prepararSentencia.setString(4, anteproyectoNuevo.getResultadosEsperados());
                prepararSentencia.setInt(5, anteproyectoNuevo.getNumeroEstudiantes());
                prepararSentencia.setString(6, anteproyectoNuevo.getDuracionAproximada());
                prepararSentencia.setString(7, anteproyectoNuevo.getDescripcionTrabajoRecepcional());
                prepararSentencia.setString(8, anteproyectoNuevo.getDescripcionProyectoInvestigacion());
                prepararSentencia.setString(9, anteproyectoNuevo.getBibliografiaRecomendada());
                prepararSentencia.setString(10, anteproyectoNuevo.getLineaInvestigacion());
                prepararSentencia.setInt(11, anteproyectoNuevo.getIdLAGC());
                prepararSentencia.setInt(12, anteproyectoNuevo.getIdTipoAnteproyecto());
                prepararSentencia.setInt(13, anteproyectoNuevo.getIdCuerpoAcademico());
                //prepararSentencia.setInt(14, anteproyectoNuevo.getIdProfesor());
                prepararSentencia.setInt(14, anteproyectoNuevo.getIdEstado());
                int filasAfectadas = prepararSentencia.executeUpdate();
                respuesta = (filasAfectadas == 1) ? Constantes.OPERACION_EXITOSA : Constantes.ERROR_CONSULTA;
                conexionBD.close();

            } catch(SQLException e){
                System.out.println(e.getMessage());
                respuesta = Constantes.ERROR_CONSULTA;
            }
        } else{
            respuesta = Constantes.ERROR_CONEXION;
        }     
        return respuesta;  
    }
    
    public static AnteproyectoRespuesta obtenerAnteproyectosPorDirector(int idDirector){
        AnteproyectoRespuesta anteproyectoRespuesta = new AnteproyectoRespuesta();
        anteproyectoRespuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try{
                String consulta = "SELECT idAnteproyecto, nombreProyectoInvestigacion, CONCAT(u.nombre, ' ', apellidoPaterno, " +
                                  "' ', apellidoMaterno) AS nombreCompleto  FROM Anteproyecto " +
                                  "INNER JOIN Profesores p ON Anteproyecto.idProfesor = p.idProfesor " +
                                  "INNER JOIN Usuarios u ON p.idUsuario = u.idUsuario " +
                                  "WHERE p.idProfesor = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idDirector);
                ResultSet resultado = prepararSentencia.executeQuery();
                ArrayList<Anteproyecto> anteproyectosConsulta = new ArrayList();
                while(resultado.next()){
                    Anteproyecto anteproyecto = new Anteproyecto();
                    anteproyecto.setIdAnteproyecto(resultado.getInt("idAnteproyecto"));
                    anteproyecto.setNombreProyectoInvestigacion(resultado.getString("nombreProyectoInvestigacion"));
                    anteproyecto.setNombreProfesor(resultado.getString("nombreCompleto"));
                    anteproyectosConsulta.add(anteproyecto);
                }
                anteproyectoRespuesta.setAnteproyectos(anteproyectosConsulta);
                conexionBD.close();
            } catch(SQLException e){
                anteproyectoRespuesta.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else{
            anteproyectoRespuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return anteproyectoRespuesta;
    } 
    
    public static AnteproyectoRespuesta obtenerAnteproyectosPorEstado(int idEstadoAnteproyecto){
        AnteproyectoRespuesta anteproyectoRespuesta = new AnteproyectoRespuesta();
        anteproyectoRespuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try{
                String consulta = "SELECT idAnteproyecto, nombreProyectoInvestigacion, CONCAT(u.nombre, ' ', apellidoPaterno, " +
                                  "' ', apellidoMaterno) AS nombreCompleto  FROM Anteproyecto " +
                                  "INNER JOIN Profesores p ON Anteproyecto.idProfesor = p.idProfesor " +
                                  "INNER JOIN Usuarios u ON p.idUsuario = u.idUsuario " +
                                  "WHERE idEstado = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idEstadoAnteproyecto);
                ResultSet resultado = prepararSentencia.executeQuery();
                ArrayList<Anteproyecto> anteproyectosConsulta = new ArrayList();
                while(resultado.next()){
                    Anteproyecto anteproyecto = new Anteproyecto();
                    anteproyecto.setIdAnteproyecto(resultado.getInt("idAnteproyecto"));
                    anteproyecto.setNombreProyectoInvestigacion(resultado.getString("nombreProyectoInvestigacion"));
                    anteproyecto.setNombreProfesor(resultado.getString("nombreCompleto"));
                    anteproyectosConsulta.add(anteproyecto);
                }
                anteproyectoRespuesta.setAnteproyectos(anteproyectosConsulta);
                conexionBD.close();
            } catch(SQLException e){
                anteproyectoRespuesta.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else{
            anteproyectoRespuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return anteproyectoRespuesta;
    }
    
}
