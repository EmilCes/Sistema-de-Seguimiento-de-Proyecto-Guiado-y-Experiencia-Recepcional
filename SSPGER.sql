/*
 Navicat MySQL Data Transfer

 Source Server         : prueba2
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30)
 Source Host           : localhost:3306
 Source Schema         : SSPGER

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30)
 File Encoding         : 65001

 Date: 01/06/2023 08:28:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Actividades
-- ----------------------------
DROP TABLE IF EXISTS `Actividades`;
CREATE TABLE `Actividades` (
  `idActividad` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `descripcion` text,
  `fechaInicio` datetime DEFAULT NULL,
  `fechaFin` datetime DEFAULT NULL,
  `idAnteproyecto` int NOT NULL,
  PRIMARY KEY (`idActividad`),
  KEY `FK_ActividadesAnteproyecto_Actividades` (`idAnteproyecto`),
  CONSTRAINT `FK_ActividadesAnteproyecto_Actividades` FOREIGN KEY (`idAnteproyecto`) REFERENCES `Anteproyecto` (`idAnteproyecto`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Actividades
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for Alumnos
-- ----------------------------
DROP TABLE IF EXISTS `Alumnos`;
CREATE TABLE `Alumnos` (
  `idAlumno` int NOT NULL AUTO_INCREMENT,
  `idUsuario` int NOT NULL,
  `idAnteproyecto` int NOT NULL,
  PRIMARY KEY (`idAlumno`),
  KEY `FK_AlumnosUsuarios_Alumnos` (`idUsuario`),
  KEY `FK_AlumnosAnteproyectos_Alumnos` (`idAnteproyecto`),
  CONSTRAINT `FK_AlumnosAnteproyectos_Alumnos` FOREIGN KEY (`idAnteproyecto`) REFERENCES `Anteproyecto` (`idAnteproyecto`) ON DELETE CASCADE,
  CONSTRAINT `FK_AlumnosUsuarios_Alumnos` FOREIGN KEY (`idUsuario`) REFERENCES `Usuarios` (`idUsuario`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Alumnos
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for AlumnosCursos
-- ----------------------------
DROP TABLE IF EXISTS `AlumnosCursos`;
CREATE TABLE `AlumnosCursos` (
  `idAlumnosCursos` int NOT NULL AUTO_INCREMENT,
  `idAlumno` int NOT NULL,
  `idCurso` int NOT NULL,
  PRIMARY KEY (`idAlumnosCursos`),
  KEY `FK_AlumnosCursosAlumno_AlumnosCursos` (`idAlumno`),
  KEY `FK_AlumnosCursosCursos_AlumnosCursos` (`idCurso`),
  CONSTRAINT `FK_AlumnosCursosAlumno_AlumnosCursos` FOREIGN KEY (`idAlumno`) REFERENCES `Alumnos` (`idAlumno`) ON DELETE CASCADE,
  CONSTRAINT `FK_AlumnosCursosCursos_AlumnosCursos` FOREIGN KEY (`idCurso`) REFERENCES `Cursos` (`idCurso`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of AlumnosCursos
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for Anteproyecto
-- ----------------------------
DROP TABLE IF EXISTS `Anteproyecto`;
CREATE TABLE `Anteproyecto` (
  `idAnteproyecto` int NOT NULL AUTO_INCREMENT,
  `nombreProyectoInvestigacion` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `nombreTrabajoRecepcional` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `requisitos` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `resultadosEsperados` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `numEstudiantes` int DEFAULT NULL,
  `fechaPublicacion` date DEFAULT NULL,
  `duracionAproximada` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `descripcionTrabajoRecepcional` text,
  `descripcionProyectoInvestigacion` text,
  `bibliografiaRecomendada` text,
  `lineaInvestigacion` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `firmas` blob,
  `notas` varchar(300) DEFAULT NULL,
  `idLAGC` int NOT NULL,
  `idTipoAnteproyecto` int NOT NULL,
  `idCuerpoAcademico` int NOT NULL,
  `idProfesor` int NOT NULL,
  `idEstado` int NOT NULL,
  PRIMARY KEY (`idAnteproyecto`),
  KEY `FK_AnteproyectoLGAC_Anteproyecto` (`idLAGC`),
  KEY `FK_AnteproyectoTipoAnteproyecto_Anteproyecto` (`idTipoAnteproyecto`),
  KEY `FK_AnteproyectoCuerpoAcademico_Anteproyecto` (`idCuerpoAcademico`),
  KEY `FK_AnteproyectoProfesores_Anteproyecto` (`idProfesor`),
  KEY `FK_AnteproyectoEstado_Anteproyecto` (`idEstado`),
  CONSTRAINT `FK_AnteproyectoCuerpoAcademico_Anteproyecto` FOREIGN KEY (`idCuerpoAcademico`) REFERENCES `CuerposAcademicos` (`idCuerpoAcademico`) ON DELETE CASCADE,
  CONSTRAINT `FK_AnteproyectoEstado_Anteproyecto` FOREIGN KEY (`idEstado`) REFERENCES `EstadoAnteproyecto` (`idEstado`) ON DELETE CASCADE,
  CONSTRAINT `FK_AnteproyectoLGAC_Anteproyecto` FOREIGN KEY (`idLAGC`) REFERENCES `LGAC` (`idLGAC`) ON DELETE CASCADE,
  CONSTRAINT `FK_AnteproyectoProfesores_Anteproyecto` FOREIGN KEY (`idProfesor`) REFERENCES `Profesores` (`idProfesor`) ON DELETE CASCADE,
  CONSTRAINT `FK_AnteproyectoTipoAnteproyecto_Anteproyecto` FOREIGN KEY (`idTipoAnteproyecto`) REFERENCES `TipoAnteproyecto` (`idTipoProyecto`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Anteproyecto
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for AnteproyectosCodirectores
-- ----------------------------
DROP TABLE IF EXISTS `AnteproyectosCodirectores`;
CREATE TABLE `AnteproyectosCodirectores` (
  `idAnteproyectosCodirectores` int NOT NULL AUTO_INCREMENT,
  `idAnteproyecto` int NOT NULL,
  `idProfesor` int NOT NULL,
  PRIMARY KEY (`idAnteproyectosCodirectores`),
  KEY `FK_AnteproyectosCodirectores_AnteCod` (`idAnteproyecto`),
  KEY `FK_AnteproyectosCodirectoresAnteproyecto_Profesores` (`idProfesor`),
  CONSTRAINT `FK_AnteproyectosCodirectores_AnteCod` FOREIGN KEY (`idAnteproyecto`) REFERENCES `Anteproyecto` (`idAnteproyecto`) ON DELETE CASCADE,
  CONSTRAINT `FK_AnteproyectosCodirectoresAnteproyecto_Profesores` FOREIGN KEY (`idProfesor`) REFERENCES `Profesores` (`idProfesor`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of AnteproyectosCodirectores
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for Criterios
-- ----------------------------
DROP TABLE IF EXISTS `Criterios`;
CREATE TABLE `Criterios` (
  `idCriterio` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`idCriterio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Criterios
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for CuerposAcademicos
-- ----------------------------
DROP TABLE IF EXISTS `CuerposAcademicos`;
CREATE TABLE `CuerposAcademicos` (
  `idCuerpoAcademico` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `descripcion` text,
  `idProfesor` int NOT NULL,
  PRIMARY KEY (`idCuerpoAcademico`),
  KEY `FK_CuerposAcademicosProfesores_CuerposAcademicos` (`idProfesor`),
  CONSTRAINT `FK_CuerposAcademicosProfesores_CuerposAcademicos` FOREIGN KEY (`idProfesor`) REFERENCES `Profesores` (`idProfesor`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of CuerposAcademicos
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for Cursos
-- ----------------------------
DROP TABLE IF EXISTS `Cursos`;
CREATE TABLE `Cursos` (
  `idCurso` int NOT NULL AUTO_INCREMENT,
  `bloque` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `fechaInicio` date DEFAULT NULL,
  `fechaFin` date DEFAULT NULL,
  `nombre` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `nrc` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `idProfesor` int NOT NULL,
  PRIMARY KEY (`idCurso`),
  KEY `FK_CursosProfesores_Cursos` (`idProfesor`),
  CONSTRAINT `FK_CursosProfesores_Cursos` FOREIGN KEY (`idProfesor`) REFERENCES `Profesores` (`idProfesor`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Cursos
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for Entregables
-- ----------------------------
DROP TABLE IF EXISTS `Entregables`;
CREATE TABLE `Entregables` (
  `idEntregable` int NOT NULL AUTO_INCREMENT,
  `nombreEntregable` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `descripcionEntregable` text,
  `archivoEntregable` blob,
  `idEvaluacion` int NOT NULL,
  `idAnteproyecto` int NOT NULL,
  PRIMARY KEY (`idEntregable`),
  KEY `FK_EntregablesEvaluaciones_Entregables` (`idEvaluacion`),
  KEY `FK_EntregablesAnteproyecto_Entregables` (`idAnteproyecto`),
  CONSTRAINT `FK_EntregablesAnteproyecto_Entregables` FOREIGN KEY (`idAnteproyecto`) REFERENCES `Anteproyecto` (`idAnteproyecto`) ON DELETE CASCADE,
  CONSTRAINT `FK_EntregablesEvaluaciones_Entregables` FOREIGN KEY (`idEvaluacion`) REFERENCES `Evaluaciones` (`idEvaluacion`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Entregables
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for EstadoAnteproyecto
-- ----------------------------
DROP TABLE IF EXISTS `EstadoAnteproyecto`;
CREATE TABLE `EstadoAnteproyecto` (
  `idEstado` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`idEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of EstadoAnteproyecto
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for Evaluaciones
-- ----------------------------
DROP TABLE IF EXISTS `Evaluaciones`;
CREATE TABLE `Evaluaciones` (
  `idEvaluacion` int NOT NULL AUTO_INCREMENT,
  `avance` decimal(10,0) DEFAULT NULL,
  `calificacaion` decimal(10,0) DEFAULT NULL,
  `idRubrica` int NOT NULL,
  PRIMARY KEY (`idEvaluacion`),
  KEY `FK_EvaluacionesRubricas_Evaluaciones` (`idRubrica`),
  CONSTRAINT `FK_EvaluacionesRubricas_Evaluaciones` FOREIGN KEY (`idRubrica`) REFERENCES `Rubricas` (`idRubrica`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Evaluaciones
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for LGAC
-- ----------------------------
DROP TABLE IF EXISTS `LGAC`;
CREATE TABLE `LGAC` (
  `idLGAC` int NOT NULL AUTO_INCREMENT,
  `nombreLGAC` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `descripcionLGAC` text,
  PRIMARY KEY (`idLGAC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of LGAC
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for NivelesDesempeno
-- ----------------------------
DROP TABLE IF EXISTS `NivelesDesempeno`;
CREATE TABLE `NivelesDesempeno` (
  `idNivelDesempeno` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `idValorNivelDesempeno` int NOT NULL,
  PRIMARY KEY (`idNivelDesempeno`),
  KEY `FK_NivelesDesempenoValoresNivelesDesemeno_NivelesDesempeno` (`idValorNivelDesempeno`),
  CONSTRAINT `FK_NivelesDesempenoValoresNivelesDesemeno_NivelesDesempeno` FOREIGN KEY (`idValorNivelDesempeno`) REFERENCES `ValoresNivelesDesempenos` (`idValorNivelDesempeno`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of NivelesDesempeno
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for Profesores
-- ----------------------------
DROP TABLE IF EXISTS `Profesores`;
CREATE TABLE `Profesores` (
  `idProfesor` int NOT NULL AUTO_INCREMENT,
  `idUsuario` int NOT NULL,
  `idCuerpoAcademico` int DEFAULT NULL,
  `idTipoProfesor` int NOT NULL,
  PRIMARY KEY (`idProfesor`),
  KEY `FK_ProfesoresUsuario_Profesores` (`idUsuario`),
  KEY `FK_ProfesoresCuerposAcademicos_Profesores` (`idCuerpoAcademico`),
  KEY `FK_ProfesoresTipoProfesor_Profesores` (`idTipoProfesor`),
  CONSTRAINT `FK_ProfesoresCuerposAcademicos_Profesores` FOREIGN KEY (`idCuerpoAcademico`) REFERENCES `CuerposAcademicos` (`idCuerpoAcademico`) ON DELETE CASCADE,
  CONSTRAINT `FK_ProfesoresTipoProfesor_Profesores` FOREIGN KEY (`idTipoProfesor`) REFERENCES `TipoProfesor` (`idTipoProfesor`) ON DELETE CASCADE,
  CONSTRAINT `FK_ProfesoresUsuario_Profesores` FOREIGN KEY (`idUsuario`) REFERENCES `Usuarios` (`idUsuario`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Profesores
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for Rubricas
-- ----------------------------
DROP TABLE IF EXISTS `Rubricas`;
CREATE TABLE `Rubricas` (
  `idRubrica` int NOT NULL AUTO_INCREMENT,
  `calificacaion` decimal(10,0) DEFAULT NULL,
  `idCriterio` int NOT NULL,
  `idNivelDesempeno` int NOT NULL,
  PRIMARY KEY (`idRubrica`),
  KEY `FK_RubricasCriterios_Rubricas` (`idCriterio`),
  KEY `FK_RubricasNivelesDesempeno_Rubricas` (`idNivelDesempeno`),
  CONSTRAINT `FK_RubricasCriterios_Rubricas` FOREIGN KEY (`idCriterio`) REFERENCES `Criterios` (`idCriterio`) ON DELETE CASCADE,
  CONSTRAINT `FK_RubricasNivelesDesempeno_Rubricas` FOREIGN KEY (`idNivelDesempeno`) REFERENCES `NivelesDesempeno` (`idNivelDesempeno`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Rubricas
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for TipoAnteproyecto
-- ----------------------------
DROP TABLE IF EXISTS `TipoAnteproyecto`;
CREATE TABLE `TipoAnteproyecto` (
  `idTipoProyecto` int NOT NULL AUTO_INCREMENT,
  `nombreTipoProyecto` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`idTipoProyecto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of TipoAnteproyecto
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for TipoProfesor
-- ----------------------------
DROP TABLE IF EXISTS `TipoProfesor`;
CREATE TABLE `TipoProfesor` (
  `idTipoProfesor` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`idTipoProfesor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of TipoProfesor
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for TipoUsuario
-- ----------------------------
DROP TABLE IF EXISTS `TipoUsuario`;
CREATE TABLE `TipoUsuario` (
  `idTipoUsuario` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`idTipoUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of TipoUsuario
-- ----------------------------
BEGIN;
INSERT INTO `TipoUsuario` (`idTipoUsuario`, `descripcion`) VALUES (1, 'Administrador');
INSERT INTO `TipoUsuario` (`idTipoUsuario`, `descripcion`) VALUES (2, 'Profesor');
INSERT INTO `TipoUsuario` (`idTipoUsuario`, `descripcion`) VALUES (3, 'Estudiante');
COMMIT;

-- ----------------------------
-- Table structure for Usuarios
-- ----------------------------
DROP TABLE IF EXISTS `Usuarios`;
CREATE TABLE `Usuarios` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `apellidoPaterno` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `apellidoMaterno` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `correoInstitucional` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `numeroTelefonico` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `nombreUsuario` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(25) NOT NULL,
  `imagenUsuario` blob,
  `idTipoUsuario` int NOT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `FK_UsuariosTiposUsuarios_Usuario` (`idTipoUsuario`),
  CONSTRAINT `FK_UsuariosTiposUsuarios_Usuario` FOREIGN KEY (`idTipoUsuario`) REFERENCES `TipoUsuario` (`idTipoUsuario`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Usuarios
-- ----------------------------
BEGIN;
INSERT INTO `Usuarios` (`idUsuario`, `nombre`, `apellidoPaterno`, `apellidoMaterno`, `correoInstitucional`, `numeroTelefonico`, `nombreUsuario`, `password`, `imagenUsuario`, `idTipoUsuario`) VALUES (1, NULL, NULL, NULL, NULL, NULL, 'admin', '123456', NULL, 1);
INSERT INTO `Usuarios` (`idUsuario`, `nombre`, `apellidoPaterno`, `apellidoMaterno`, `correoInstitucional`, `numeroTelefonico`, `nombreUsuario`, `password`, `imagenUsuario`, `idTipoUsuario`) VALUES (2, 'Cesar Emiliano', 'Lezama', 'López', 'zs21013857@estudiantes.uv.mx', '2281627337', 'cesarele23', '123456', NULL, 3);
COMMIT;

-- ----------------------------
-- Table structure for ValoresNivelesDesempenos
-- ----------------------------
DROP TABLE IF EXISTS `ValoresNivelesDesempenos`;
CREATE TABLE `ValoresNivelesDesempenos` (
  `idValorNivelDesempeno` int NOT NULL AUTO_INCREMENT,
  `valor` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`idValorNivelDesempeno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of ValoresNivelesDesempenos
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
