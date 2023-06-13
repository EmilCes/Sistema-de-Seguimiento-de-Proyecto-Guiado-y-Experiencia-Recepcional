package sspger.modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import sspger.modelos.ConexionBD;
import sspger.modelos.pojo.Anteproyecto;
import sspger.modelos.pojo.AnteproyectoRespuesta;
import sspger.modelos.pojo.AvanceAnteproyecto;
import sspger.utils.Constantes;

public class AnteproyectoDAO {

    public static int guardarAnteproyecto(Anteproyecto anteproyectoNuevo) {

        int respuesta;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if (conexionBD != null) {
            try {
                String sentencia = "INSERT INTO Anteproyecto (nombreProyectoInvestigacion, nombreTrabajoRecepcional, "
                        + "requisitos, resultadosEsperados, numEstudiantes, duracionAproximada, "
                        + "descripcionTrabajoRecepcional, descripcionProyectoInvestigacion, bibliografiaRecomendada, "
                        + "lineaInvestigacion, idLAGC, idTipoAnteproyecto, idCuerpoAcademico, idProfesor, idEstado) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);

                if (anteproyectoNuevo.getNombreProyectoInvestigacion() == null) {
                    prepararSentencia.setNull(1, Types.VARCHAR);
                } else {
                    prepararSentencia.setString(1, anteproyectoNuevo.getNombreProyectoInvestigacion());
                }

                if (anteproyectoNuevo.getNombreTrabajoRecepcional() == null) {
                    prepararSentencia.setNull(2, Types.VARCHAR);
                } else {
                    prepararSentencia.setString(2, anteproyectoNuevo.getNombreTrabajoRecepcional());
                }

                if (anteproyectoNuevo.getRequisitos() == null) {
                    prepararSentencia.setNull(3, Types.VARCHAR);
                } else {
                    prepararSentencia.setString(3, anteproyectoNuevo.getRequisitos());
                }

                if (anteproyectoNuevo.getResultadosEsperados() == null) {
                    prepararSentencia.setNull(4, Types.VARCHAR);
                } else {
                    prepararSentencia.setString(4, anteproyectoNuevo.getResultadosEsperados());
                }

                if (anteproyectoNuevo.getNumeroEstudiantes() == 0) {
                    prepararSentencia.setNull(5, Types.INTEGER);
                } else {
                    prepararSentencia.setInt(5, anteproyectoNuevo.getNumeroEstudiantes());
                }

                if (anteproyectoNuevo.getDuracionAproximada() == null) {
                    prepararSentencia.setNull(6, Types.VARCHAR);
                } else {
                    prepararSentencia.setString(6, anteproyectoNuevo.getDuracionAproximada());
                }

                if (anteproyectoNuevo.getDescripcionTrabajoRecepcional() == null) {
                    prepararSentencia.setNull(7, Types.NULL);
                } else {
                    prepararSentencia.setString(7, anteproyectoNuevo.getDescripcionTrabajoRecepcional());
                }

                if (anteproyectoNuevo.getDescripcionProyectoInvestigacion() == null) {
                    prepararSentencia.setNull(8, Types.NULL);
                } else {
                    prepararSentencia.setString(8, anteproyectoNuevo.getDescripcionProyectoInvestigacion());
                }

                if (anteproyectoNuevo.getBibliografiaRecomendada() == null) {
                    prepararSentencia.setNull(9, Types.NULL);
                } else {
                    prepararSentencia.setString(9, anteproyectoNuevo.getBibliografiaRecomendada());
                }

                if (anteproyectoNuevo.getLineaInvestigacion() == null) {
                    prepararSentencia.setNull(10, Types.NULL);
                } else {
                    prepararSentencia.setString(10, anteproyectoNuevo.getLineaInvestigacion());
                }

                if (anteproyectoNuevo.getIdLAGC() == 0) {
                    prepararSentencia.setNull(11, Types.INTEGER);
                } else {
                    prepararSentencia.setInt(11, anteproyectoNuevo.getIdLAGC());
                }

                if (anteproyectoNuevo.getIdTipoAnteproyecto() == 0) {
                    prepararSentencia.setNull(12, Types.INTEGER);
                } else {
                    prepararSentencia.setInt(12, anteproyectoNuevo.getIdTipoAnteproyecto());
                }

                if (anteproyectoNuevo.getIdCuerpoAcademico() == 0) {
                    prepararSentencia.setNull(13, Types.INTEGER);
                } else {
                    prepararSentencia.setInt(13, anteproyectoNuevo.getIdCuerpoAcademico());
                }

                if (anteproyectoNuevo.getIdProfesor() == 0) {
                    prepararSentencia.setNull(14, Types.INTEGER);
                } else {
                    prepararSentencia.setInt(14, anteproyectoNuevo.getIdProfesor());
                }

                if (anteproyectoNuevo.getIdEstado() == 0) {
                    prepararSentencia.setNull(15, Types.INTEGER);
                } else {
                    prepararSentencia.setInt(15, anteproyectoNuevo.getIdEstado());
                }

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

    public static int modificarAnteproyectoPorId(Anteproyecto anteproyectoNuevo) {

        int respuesta;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if (conexionBD != null) {
            try {
                String sentencia = "UPDATE Anteproyecto SET nombreProyectoInvestigacion = ?, nombreTrabajoRecepcional = ?, "
                        + "requisitos = ?, resultadosEsperados = ?, numEstudiantes = ?, duracionAproximada = ?, "
                        + "descripcionTrabajoRecepcional = ?, descripcionProyectoInvestigacion = ?, "
                        + "bibliografiaRecomendada = ?, lineaInvestigacion = ?, idLAGC = ?, idTipoAnteproyecto = ?, "
                        + "idCuerpoAcademico = ?, idEstado = ? WHERE idAnteproyecto = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);

                if (anteproyectoNuevo.getNombreProyectoInvestigacion() == null) {
                    prepararSentencia.setNull(1, Types.VARCHAR);
                } else {
                    prepararSentencia.setString(1, anteproyectoNuevo.getNombreProyectoInvestigacion());
                }

                if (anteproyectoNuevo.getNombreTrabajoRecepcional() == null) {
                    prepararSentencia.setNull(2, Types.VARCHAR);
                } else {
                    prepararSentencia.setString(2, anteproyectoNuevo.getNombreTrabajoRecepcional());
                }

                if (anteproyectoNuevo.getRequisitos() == null) {
                    prepararSentencia.setNull(3, Types.VARCHAR);
                } else {
                    prepararSentencia.setString(3, anteproyectoNuevo.getRequisitos());
                }

                if (anteproyectoNuevo.getResultadosEsperados() == null) {
                    prepararSentencia.setNull(4, Types.VARCHAR);
                } else {
                    prepararSentencia.setString(4, anteproyectoNuevo.getResultadosEsperados());
                }

                if (anteproyectoNuevo.getNumeroEstudiantes() == 0) {
                    prepararSentencia.setNull(5, Types.INTEGER);
                } else {
                    prepararSentencia.setInt(5, anteproyectoNuevo.getNumeroEstudiantes());
                }

                if (anteproyectoNuevo.getDuracionAproximada() == null) {
                    prepararSentencia.setNull(6, Types.VARCHAR);
                } else {
                    prepararSentencia.setString(6, anteproyectoNuevo.getDuracionAproximada());
                }

                if (anteproyectoNuevo.getDescripcionTrabajoRecepcional() == null) {
                    prepararSentencia.setNull(7, Types.NULL);
                } else {
                    prepararSentencia.setString(7, anteproyectoNuevo.getDescripcionTrabajoRecepcional());
                }

                if (anteproyectoNuevo.getDescripcionProyectoInvestigacion() == null) {
                    prepararSentencia.setNull(8, Types.NULL);
                } else {
                    prepararSentencia.setString(8, anteproyectoNuevo.getDescripcionProyectoInvestigacion());
                }

                if (anteproyectoNuevo.getBibliografiaRecomendada() == null) {
                    prepararSentencia.setNull(9, Types.NULL);
                } else {
                    prepararSentencia.setString(9, anteproyectoNuevo.getBibliografiaRecomendada());
                }

                if (anteproyectoNuevo.getLineaInvestigacion() == null) {
                    prepararSentencia.setNull(10, Types.NULL);
                } else {
                    prepararSentencia.setString(10, anteproyectoNuevo.getLineaInvestigacion());
                }

                if (anteproyectoNuevo.getIdLAGC() == 0) {
                    prepararSentencia.setNull(11, Types.INTEGER);
                } else {
                    prepararSentencia.setInt(11, anteproyectoNuevo.getIdLAGC());
                }

                if (anteproyectoNuevo.getIdTipoAnteproyecto() == 0) {
                    prepararSentencia.setNull(12, Types.INTEGER);
                } else {
                    prepararSentencia.setInt(12, anteproyectoNuevo.getIdTipoAnteproyecto());
                }

                if (anteproyectoNuevo.getIdCuerpoAcademico() == 0) {
                    prepararSentencia.setNull(13, Types.INTEGER);
                } else {
                    prepararSentencia.setInt(13, anteproyectoNuevo.getIdCuerpoAcademico());
                }

                if (anteproyectoNuevo.getIdEstado() == 0) {
                    prepararSentencia.setNull(14, Types.INTEGER);
                } else {
                    prepararSentencia.setInt(14, anteproyectoNuevo.getIdEstado());
                }

                prepararSentencia.setInt(15, anteproyectoNuevo.getIdAnteproyecto());

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

    public static AnteproyectoRespuesta obtenerAnteproyectosPorDirector(int idDirector) {
        AnteproyectoRespuesta anteproyectoRespuesta = new AnteproyectoRespuesta();
        anteproyectoRespuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if (conexionBD != null) {
            try {
                String consulta = "SELECT idAnteproyecto, nombreProyectoInvestigacion, CONCAT(u.nombre, ' ', apellidoPaterno, "
                        + "' ', apellidoMaterno) AS nombreCompleto, idEstado  FROM Anteproyecto "
                        + "INNER JOIN Profesores p ON Anteproyecto.idProfesor = p.idProfesor "
                        + "INNER JOIN Usuarios u ON p.idUsuario = u.idUsuario "
                        + "WHERE p.idProfesor = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idDirector);
                ResultSet resultado = prepararSentencia.executeQuery();
                ArrayList<Anteproyecto> anteproyectosConsulta = new ArrayList();
                while (resultado.next()) {
                    Anteproyecto anteproyecto = new Anteproyecto();
                    anteproyecto.setIdAnteproyecto(resultado.getInt("idAnteproyecto"));
                    anteproyecto.setNombreProyectoInvestigacion(resultado.getString("nombreProyectoInvestigacion"));
                    anteproyecto.setNombreProfesor(resultado.getString("nombreCompleto"));
                    anteproyecto.setIdEstado(resultado.getInt("idEstado"));
                    anteproyectosConsulta.add(anteproyecto);
                }
                anteproyectoRespuesta.setAnteproyectos(anteproyectosConsulta);
                conexionBD.close();
            } catch (SQLException e) {
                anteproyectoRespuesta.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else {
            anteproyectoRespuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return anteproyectoRespuesta;
    }

    public static AnteproyectoRespuesta obtenerAnteproyectosPorEstado(int idEstadoAnteproyecto) {
        AnteproyectoRespuesta anteproyectoRespuesta = new AnteproyectoRespuesta();
        anteproyectoRespuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if (conexionBD != null) {
            try {
                String consulta = "SELECT idAnteproyecto, nombreProyectoInvestigacion, CONCAT(u.nombre, ' ', apellidoPaterno, "
                        + "' ', apellidoMaterno) AS nombreCompleto, idEstado  FROM Anteproyecto "
                        + "INNER JOIN Profesores p ON Anteproyecto.idProfesor = p.idProfesor "
                        + "INNER JOIN Usuarios u ON p.idUsuario = u.idUsuario "
                        + "WHERE idEstado = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idEstadoAnteproyecto);
                ResultSet resultado = prepararSentencia.executeQuery();
                ArrayList<Anteproyecto> anteproyectosConsulta = new ArrayList();
                while (resultado.next()) {
                    Anteproyecto anteproyecto = new Anteproyecto();
                    anteproyecto.setIdAnteproyecto(resultado.getInt("idAnteproyecto"));
                    anteproyecto.setNombreProyectoInvestigacion(resultado.getString("nombreProyectoInvestigacion"));
                    anteproyecto.setNombreProfesor(resultado.getString("nombreCompleto"));
                    anteproyecto.setIdEstado(resultado.getInt("idEstado"));
                    anteproyectosConsulta.add(anteproyecto);
                }
                anteproyectoRespuesta.setAnteproyectos(anteproyectosConsulta);
                conexionBD.close();
            } catch (SQLException e) {
                anteproyectoRespuesta.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else {
            anteproyectoRespuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return anteproyectoRespuesta;
    }

    public static AnteproyectoRespuesta obtenerAnteproyectosPorDirectorYEstado(int idDirector, int idEstado) {
        AnteproyectoRespuesta anteproyectoRespuesta = new AnteproyectoRespuesta();
        anteproyectoRespuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if (conexionBD != null) {
            try {
                String consulta = "SELECT idAnteproyecto, nombreProyectoInvestigacion, CONCAT(u.nombre, ' ', apellidoPaterno, "
                        + "' ', apellidoMaterno) AS nombreCompleto, idEstado  FROM Anteproyecto "
                        + "INNER JOIN Profesores p ON Anteproyecto.idProfesor = p.idProfesor "
                        + "INNER JOIN Usuarios u ON p.idUsuario = u.idUsuario "
                        + "WHERE p.idProfesor = ? AND Anteproyecto.idEstado = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idDirector);
                prepararSentencia.setInt(2, idEstado);
                ResultSet resultado = prepararSentencia.executeQuery();
                ArrayList<Anteproyecto> anteproyectosConsulta = new ArrayList();
                while (resultado.next()) {
                    Anteproyecto anteproyecto = new Anteproyecto();
                    anteproyecto.setIdAnteproyecto(resultado.getInt("idAnteproyecto"));
                    anteproyecto.setNombreProyectoInvestigacion(resultado.getString("nombreProyectoInvestigacion"));
                    anteproyecto.setNombreProfesor(resultado.getString("nombreCompleto"));
                    anteproyecto.setIdEstado(resultado.getInt("idEstado"));
                    anteproyectosConsulta.add(anteproyecto);
                }
                anteproyectoRespuesta.setAnteproyectos(anteproyectosConsulta);
                conexionBD.close();
            } catch (SQLException e) {
                anteproyectoRespuesta.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else {
            anteproyectoRespuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return anteproyectoRespuesta;
    }

    public static Anteproyecto obtenerAnteproyectoPorId(int idAnteproyecto) {
        Anteproyecto anteproyecto = new Anteproyecto();
        Connection conexion = ConexionBD.abrirConexionBD();
        if (conexion != null) {
            try {
                String consulta = "SELECT idAnteproyecto, nombreProyectoInvestigacion, nombreTrabajoRecepcional, "
                        + "requisitos, resultadosEsperados, numEstudiantes, duracionAproximada, descripcionTrabajoRecepcional, "
                        + " descripcionProyectoInvestigacion, bibliografiaRecomendada, lineaInvestigacion, idLAGC, idTipoAnteproyecto, "
                        + " idCuerpoAcademico, idEstado, notas, idProfesor FROM Anteproyecto WHERE idAnteproyecto = ?";
                PreparedStatement prepararSentencia = conexion.prepareStatement(consulta);
                prepararSentencia.setInt(1, idAnteproyecto);
                ResultSet resultado = prepararSentencia.executeQuery();
                anteproyecto.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
                if (resultado.next()) {
                    anteproyecto.setIdAnteproyecto(resultado.getInt("idAnteproyecto"));
                    anteproyecto.setNombreProyectoInvestigacion(resultado.getString("nombreProyectoInvestigacion"));
                    anteproyecto.setNombreTrabajoRecepcional(resultado.getString("nombreTrabajoRecepcional"));
                    anteproyecto.setRequisitos(resultado.getString("requisitos"));
                    anteproyecto.setResultadosEsperados(resultado.getString("resultadosEsperados"));
                    anteproyecto.setNumeroEstudiantes(resultado.getInt("numEstudiantes"));
                    anteproyecto.setDuracionAproximada(resultado.getString("duracionAproximada"));
                    anteproyecto.setDescripcionTrabajoRecepcional(resultado.getString("descripcionTrabajoRecepcional"));
                    anteproyecto.setDescripcionProyectoInvestigacion(resultado.getString("descripcionProyectoInvestigacion"));
                    anteproyecto.setBibliografiaRecomendada(resultado.getString("bibliografiaRecomendada"));
                    anteproyecto.setLineaInvestigacion(resultado.getString("lineaInvestigacion"));
                    anteproyecto.setIdLAGC(resultado.getInt("idLAGC"));
                    anteproyecto.setIdTipoAnteproyecto(resultado.getInt("idTipoAnteproyecto"));
                    anteproyecto.setIdCuerpoAcademico(resultado.getInt("idCuerpoAcademico"));
                    anteproyecto.setIdEstado(resultado.getInt("idEstado"));
                    anteproyecto.setNotas(resultado.getString("notas"));
                    anteproyecto.setIdProfesor(resultado.getInt("idProfesor"));
                }
                conexion.close();
            } catch (SQLException e) {
                anteproyecto.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else {
            anteproyecto.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return anteproyecto;
    }

    public static int actualizarEstadoAnteproyecto(int idEstado, int idAnteproyecto) {
        int respuesta;
        Connection conexion = ConexionBD.abrirConexionBD();
        if (conexion != null) {
            try {
                String sentencia = "UPDATE Anteproyecto SET idEstado = ? WHERE idAnteproyecto = ?";
                PreparedStatement prepararSentencia = conexion.prepareStatement(sentencia);
                prepararSentencia.setInt(1, idEstado);
                prepararSentencia.setInt(2, idAnteproyecto);
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

    public static int actualizarAnteproyectoComoBorrador(Anteproyecto anteproyecto) {
        int respuesta;
        Connection conexion = ConexionBD.abrirConexionBD();
        if (conexion != null) {
            try {
                String sentencia = "UPDATE Anteproyecto SET idEstado = ?, notas = ? WHERE idAnteproyecto = ?";
                PreparedStatement prepararSentencia = conexion.prepareStatement(sentencia);
                prepararSentencia.setInt(1, anteproyecto.getIdEstado());
                prepararSentencia.setString(2, anteproyecto.getNotas());
                prepararSentencia.setInt(3, anteproyecto.getIdAnteproyecto());
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

   

    

}
