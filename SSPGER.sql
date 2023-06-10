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

 Date: 10/06/2023 09:51:19
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
  `fechaInicio` date DEFAULT NULL,
  `fechaFin` date DEFAULT NULL,
  `idAnteproyecto` int NOT NULL,
  PRIMARY KEY (`idActividad`),
  KEY `FK_ActividadesAnteproyecto_Actividades` (`idAnteproyecto`),
  CONSTRAINT `FK_ActividadesAnteproyecto_Actividades` FOREIGN KEY (`idAnteproyecto`) REFERENCES `Anteproyecto` (`idAnteproyecto`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Actividades
-- ----------------------------
BEGIN;
INSERT INTO `Actividades` (`idActividad`, `titulo`, `descripcion`, `fechaInicio`, `fechaFin`, `idAnteproyecto`) VALUES (1, 'Actividad 1', 'Haz popo', '2023-06-11', '2023-06-12', 2);
INSERT INTO `Actividades` (`idActividad`, `titulo`, `descripcion`, `fechaInicio`, `fechaFin`, `idAnteproyecto`) VALUES (2, 'a', 'a', '2023-06-10', '2023-06-13', 2);
INSERT INTO `Actividades` (`idActividad`, `titulo`, `descripcion`, `fechaInicio`, `fechaFin`, `idAnteproyecto`) VALUES (3, '3', '3', '2023-06-10', '2023-06-11', 2);
COMMIT;

-- ----------------------------
-- Table structure for Alumnos
-- ----------------------------
DROP TABLE IF EXISTS `Alumnos`;
CREATE TABLE `Alumnos` (
  `idAlumno` int NOT NULL AUTO_INCREMENT,
  `idUsuario` int NOT NULL,
  `idAnteproyecto` int DEFAULT NULL,
  `matricula` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idAlumno`),
  KEY `FK_AlumnosUsuarios_Alumnos` (`idUsuario`),
  KEY `FK_AlumnosAnteproyectos_Alumnos` (`idAnteproyecto`),
  CONSTRAINT `FK_AlumnosAnteproyectos_Alumnos` FOREIGN KEY (`idAnteproyecto`) REFERENCES `Anteproyecto` (`idAnteproyecto`) ON DELETE CASCADE,
  CONSTRAINT `FK_AlumnosUsuarios_Alumnos` FOREIGN KEY (`idUsuario`) REFERENCES `Usuarios` (`idUsuario`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Alumnos
-- ----------------------------
BEGIN;
INSERT INTO `Alumnos` (`idAlumno`, `idUsuario`, `idAnteproyecto`, `matricula`) VALUES (1, 3, 2, 'S21013857');
INSERT INTO `Alumnos` (`idAlumno`, `idUsuario`, `idAnteproyecto`, `matricula`) VALUES (2, 5, 2, 'S21013858');
INSERT INTO `Alumnos` (`idAlumno`, `idUsuario`, `idAnteproyecto`, `matricula`) VALUES (3, 6, NULL, 'S21013859');
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
  `lineaInvestigacion` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci,
  `firmas` blob,
  `notas` varchar(300) DEFAULT NULL,
  `idLAGC` int DEFAULT NULL,
  `idTipoAnteproyecto` int DEFAULT NULL,
  `idCuerpoAcademico` int DEFAULT NULL,
  `idProfesor` int DEFAULT NULL,
  `idEstado` int NOT NULL,
  PRIMARY KEY (`idAnteproyecto`),
  KEY `FK_AnteproyectoEstado_Anteproyecto` (`idEstado`),
  KEY `FK_AnteproyectoProfesores_Anteproyecto` (`idProfesor`),
  KEY `FK_AnteproyectoCuerpoAcademico_Anteproyecto` (`idCuerpoAcademico`),
  KEY `FK_AnteproyectoLGAC_Anteproyecto` (`idLAGC`),
  KEY `FK_AnteproyectoTipoAnteproyecto_Anteproyecto` (`idTipoAnteproyecto`),
  CONSTRAINT `FK_AnteproyectoCuerpoAcademico_Anteproyecto` FOREIGN KEY (`idCuerpoAcademico`) REFERENCES `CuerposAcademicos` (`idCuerpoAcademico`) ON DELETE CASCADE,
  CONSTRAINT `FK_AnteproyectoEstado_Anteproyecto` FOREIGN KEY (`idEstado`) REFERENCES `EstadoAnteproyecto` (`idEstado`) ON DELETE CASCADE,
  CONSTRAINT `FK_AnteproyectoLGAC_Anteproyecto` FOREIGN KEY (`idLAGC`) REFERENCES `LGAC` (`idLGAC`) ON DELETE CASCADE,
  CONSTRAINT `FK_AnteproyectoProfesores_Anteproyecto` FOREIGN KEY (`idProfesor`) REFERENCES `Profesores` (`idProfesor`) ON DELETE CASCADE,
  CONSTRAINT `FK_AnteproyectoTipoAnteproyecto_Anteproyecto` FOREIGN KEY (`idTipoAnteproyecto`) REFERENCES `TipoAnteproyecto` (`idTipoProyecto`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Anteproyecto
-- ----------------------------
BEGIN;
INSERT INTO `Anteproyecto` (`idAnteproyecto`, `nombreProyectoInvestigacion`, `nombreTrabajoRecepcional`, `requisitos`, `resultadosEsperados`, `numEstudiantes`, `fechaPublicacion`, `duracionAproximada`, `descripcionTrabajoRecepcional`, `descripcionProyectoInvestigacion`, `bibliografiaRecomendada`, `lineaInvestigacion`, `firmas`, `notas`, `idLAGC`, `idTipoAnteproyecto`, `idCuerpoAcademico`, `idProfesor`, `idEstado`) VALUES (1, 'VINCULACIÓN/PLADEA-FEI', 'Prácticas de Ciberseguridad en Ingeniería de Software', 'Tecnologías para la construcción de software, Principios de Construcción de Software, Diseño de Software, Procesos de Software, Administración de proyectos', 'Documento que contenga:\n• Reporte de la revisión sistemática de la literatura\n• Artículo para publicación en evento académico', 1, NULL, '12 meses', 'El presente trabajo tiene como finalidad, realizar un mapeo sistemático de la literatura sobre las prácticas de ciberseguridad identificadas en el proceso de desarrollo de software, así como reportar elementos como: tipo de práctica, fase en la que se lleva a cabo, evidencia de su utilidad, entre otros aspectos.', 'La ciberseguridad se ha vuelto un aspecto muy relevante debido al alto índice de brechas de seguridad reportadas en productos de software. En recientes años el término “shift left security” ha cobrado importancia, ya que pretende la incorporación de prácticas de seguridad en el desarrollo de software en etapas tempranas del proceso. Actualmente existen algunos retos derivados de\nconsiderar la seguridad en etapas tempranas en el proceso de desarrollo, entre dichos retos destacan: conocimiento de fallas de seguridad comunes, mejora de los procesos de colaboración con equipos de seguridad, diseminación de actividades enfocadas a la higiene del código para prevenir algún defecto que comprometa la seguridad del producto, entre otros aspectos. El considerar actividades de seguridad en el proceso de desarrollo de software permite que se desarrollen productos menos propensos a vulnerabilidades, propician que los programadores generen conocimiento a partir de la identificación de fallas conocidas, consolidad una cultura de higiene de código, minimizar los costos asociados a fallas que pudiera detectarse a tiempo.', 'J. Straub, \"Software Engineering: The First Line of Defense for Cybersecurity,\" 2020 IEEE 11th International Conference on Software Engineering and Service Science (ICSESS), 2020, pp. 1-5, doi: 10.1109/ICSESS49938.2020.9237715.\nJohnson, C. (2012). CyberSafety: CyberSecurity and Safety-Critical Software Engineering. In: Dale, C., Anderson, T. (eds) Achieving Systems Safety. Springer, London.\nhttps://doi.org/10.1007/978-1-4471-2494-8_8\nMaurice Dawson, Pedro Taveras, Danielle Taylor, Applying Software Assurance and Cybersecurity NICE Job Tasks through Secure Software Engineering Labs, Procedia Computer Science, Volume 164, 2019, Pages 301-312, ISSN 1877-0509, https://doi.org/10.1016/j.procs.2019.12.187.\nH. Gonzalez, R. Llamas-Contreras and O. Montaño-Rivas, \"When Software Engineering meets Cybersecurity at the classroom,\" 2019 7th International Conference in Software Engineering Research and Innovation (CONISOFT), 2019, pp. 49-54, doi: 10.1109/CONISOFT.2019.00017.\nFrederico Araujo and Teryl Taylor. 2020. Improving cybersecurity hygiene through JIT patching. In Proceedings of the 28th ACM Joint Meeting on European Software Engineering Conference and Symposium on the Foundations of Software Engineering (ESEC/FSE 2020). Association for Computing Machinery, New York, NY, USA, 1421–\n 1432. https://doi.org/10.1145/3368089.3417056', 'Estudio de los diversos métodos y enfoques para la gestión, modelado y desarrollo de software, de manera que se obtenga software de calidad. Gestión de las diversas etapas del proceso de desarrollo', NULL, NULL, 4, 9, 1, 1, 4);
INSERT INTO `Anteproyecto` (`idAnteproyecto`, `nombreProyectoInvestigacion`, `nombreTrabajoRecepcional`, `requisitos`, `resultadosEsperados`, `numEstudiantes`, `fechaPublicacion`, `duracionAproximada`, `descripcionTrabajoRecepcional`, `descripcionProyectoInvestigacion`, `bibliografiaRecomendada`, `lineaInvestigacion`, `firmas`, `notas`, `idLAGC`, `idTipoAnteproyecto`, `idCuerpoAcademico`, `idProfesor`, `idEstado`) VALUES (2, 'VINCULACIÓN/PLADEA-FEI', 'Prácticas de Ciberseguridad en Ingeniería de Software', 'Tecnologías para la construcción de software, Principios de Construcción de Software, Diseño de Software, Procesos de Software, Administración de proyectos', 'Documento que contenga:\n• Reporte de la revisión sistemática de la literatura\n• Artículo para publicación en evento académico', 2, NULL, '12 meses', 'El presente trabajo tiene como finalidad, realizar un mapeo sistemático de la literatura sobre las prácticas de ciberseguridad identificadas en el proceso de desarrollo de software, así como reportar elementos como: tipo de práctica, fase en la que se lleva a cabo, evidencia de su utilidad, entre otros aspectos.', 'La ciberseguridad se ha vuelto un aspecto muy relevante debido al alto índice de brechas de seguridad reportadas en productos de software. En recientes años el término “shift left security” ha cobrado importancia, ya que pretende la incorporación de prácticas de seguridad en el desarrollo de software en etapas tempranas del proceso. Actualmente existen algunos retos derivados de\nconsiderar la seguridad en etapas tempranas en el proceso de desarrollo, entre dichos retos destacan: conocimiento de fallas de seguridad comunes, mejora de los procesos de colaboración con equipos de seguridad, diseminación de actividades enfocadas a la higiene del código para prevenir algún defecto que comprometa la seguridad del producto, entre otros aspectos. El considerar actividades de seguridad en el proceso de desarrollo de software permite que se desarrollen productos menos propensos a vulnerabilidades, propician que los programadores generen conocimiento a partir de la identificación de fallas conocidas, consolidad una cultura de higiene de código, minimizar los costos asociados a fallas que pudiera detectarse a tiempo.', 'J. Straub, \"Software Engineering: The First Line of Defense for Cybersecurity,\" 2020 IEEE 11th International Conference on Software Engineering and Service Science (ICSESS), 2020, pp. 1-5, doi: 10.1109/ICSESS49938.2020.9237715.\nJohnson, C. (2012). CyberSafety: CyberSecurity and Safety-Critical Software Engineering. In: Dale, C., Anderson, T. (eds) Achieving Systems Safety. Springer, London.\nhttps://doi.org/10.1007/978-1-4471-2494-8_8\nMaurice Dawson, Pedro Taveras, Danielle Taylor, Applying Software Assurance and Cybersecurity NICE Job Tasks through Secure Software Engineering Labs, Procedia Computer Science, Volume 164, 2019, Pages 301-312, ISSN 1877-0509, https://doi.org/10.1016/j.procs.2019.12.187.\nH. Gonzalez, R. Llamas-Contreras and O. Montaño-Rivas, \"When Software Engineering meets Cybersecurity at the classroom,\" 2019 7th International Conference in Software Engineering Research and Innovation (CONISOFT), 2019, pp. 49-54, doi: 10.1109/CONISOFT.2019.00017.\nFrederico Araujo and Teryl Taylor. 2020. Improving cybersecurity hygiene through JIT patching. In Proceedings of the 28th ACM Joint Meeting on European Software Engineering Conference and Symposium on the Foundations of Software Engineering (ESEC/FSE 2020). Association for Computing Machinery, New York, NY, USA, 1421–\n 1432. https://doi.org/10.1145/3368089.3417056', 'Estudio de los diversos métodos y enfoques para la gestión, modelado y desarrollo de software, de manera que se obtenga software de calidad. Gestión de las diversas etapas del proceso de desarrollo', 0x3F, NULL, 4, 9, 1, 1, 5);
INSERT INTO `Anteproyecto` (`idAnteproyecto`, `nombreProyectoInvestigacion`, `nombreTrabajoRecepcional`, `requisitos`, `resultadosEsperados`, `numEstudiantes`, `fechaPublicacion`, `duracionAproximada`, `descripcionTrabajoRecepcional`, `descripcionProyectoInvestigacion`, `bibliografiaRecomendada`, `lineaInvestigacion`, `firmas`, `notas`, `idLAGC`, `idTipoAnteproyecto`, `idCuerpoAcademico`, `idProfesor`, `idEstado`) VALUES (3, 'VINCULACIÓN/PLADEA-FEI', 'Prácticas de Ciberseguridad en Ingeniería de Software', 'Tecnologías para la construcción de software, Principios de Construcción de Software, Diseño de Software, Procesos de Software, Administración de proyectos', 'Documento que contenga:\n• Reporte de la revisión sistemática de la literatura\n• Artículo para publicación en evento académico', 1, NULL, '12 meses', 'El presente trabajo tiene como finalidad, realizar un mapeo sistemático de la literatura sobre las prácticas de ciberseguridad identificadas en el proceso de desarrollo de software, así como reportar elementos como: tipo de práctica, fase en la que se lleva a cabo, evidencia de su utilidad, entre otros aspectos.', 'La ciberseguridad se ha vuelto un aspecto muy relevante debido al alto índice de brechas de seguridad reportadas en productos de software. En recientes años el término “shift left security” ha cobrado importancia, ya que pretende la incorporación de prácticas de seguridad en el desarrollo de software en etapas tempranas del proceso. Actualmente existen algunos retos derivados de\nconsiderar la seguridad en etapas tempranas en el proceso de desarrollo, entre dichos retos destacan: conocimiento de fallas de seguridad comunes, mejora de los procesos de colaboración con equipos de seguridad, diseminación de actividades enfocadas a la higiene del código para prevenir algún defecto que comprometa la seguridad del producto, entre otros aspectos. El considerar actividades de seguridad en el proceso de desarrollo de software permite que se desarrollen productos menos propensos a vulnerabilidades, propician que los programadores generen conocimiento a partir de la identificación de fallas conocidas, consolidad una cultura de higiene de código, minimizar los costos asociados a fallas que pudiera detectarse a tiempo.', 'J. Straub, \"Software Engineering: The First Line of Defense for Cybersecurity,\" 2020 IEEE 11th International Conference on Software Engineering and Service Science (ICSESS), 2020, pp. 1-5, doi: 10.1109/ICSESS49938.2020.9237715.\nJohnson, C. (2012). CyberSafety: CyberSecurity and Safety-Critical Software Engineering. In: Dale, C., Anderson, T. (eds) Achieving Systems Safety. Springer, London.\nhttps://doi.org/10.1007/978-1-4471-2494-8_8\nMaurice Dawson, Pedro Taveras, Danielle Taylor, Applying Software Assurance and Cybersecurity NICE Job Tasks through Secure Software Engineering Labs, Procedia Computer Science, Volume 164, 2019, Pages 301-312, ISSN 1877-0509, https://doi.org/10.1016/j.procs.2019.12.187.\nH. Gonzalez, R. Llamas-Contreras and O. Montaño-Rivas, \"When Software Engineering meets Cybersecurity at the classroom,\" 2019 7th International Conference in Software Engineering Research and Innovation (CONISOFT), 2019, pp. 49-54, doi: 10.1109/CONISOFT.2019.00017.\nFrederico Araujo and Teryl Taylor. 2020. Improving cybersecurity hygiene through JIT patching. In Proceedings of the 28th ACM Joint Meeting on European Software Engineering Conference and Symposium on the Foundations of Software Engineering (ESEC/FSE 2020). Association for Computing Machinery, New York, NY, USA, 1421–\n 1432. https://doi.org/10.1145/3368089.3417056', 'Estudio de los diversos métodos y enfoques para la gestión, modelado y desarrollo de software, de manera que se obtenga software de calidad. Gestión de las diversas etapas del proceso de desarrollo', 0x3F, NULL, 4, 9, 1, 1, 3);
INSERT INTO `Anteproyecto` (`idAnteproyecto`, `nombreProyectoInvestigacion`, `nombreTrabajoRecepcional`, `requisitos`, `resultadosEsperados`, `numEstudiantes`, `fechaPublicacion`, `duracionAproximada`, `descripcionTrabajoRecepcional`, `descripcionProyectoInvestigacion`, `bibliografiaRecomendada`, `lineaInvestigacion`, `firmas`, `notas`, `idLAGC`, `idTipoAnteproyecto`, `idCuerpoAcademico`, `idProfesor`, `idEstado`) VALUES (4, 'VINCULACIÓN/PLADEA-FEI', 'Prácticas de Ciberseguridad en Ingeniería de Software', 'Tecnologías para la construcción de software, Principios de Construcción de Software, Diseño de Software, Procesos de Software, Administración de proyectos', 'Documento que contenga:\n• Reporte de la revisión sistemática de la literatura\n• Artículo para publicación en evento académico', 1, NULL, '12 meses', 'El presente trabajo tiene como finalidad, realizar un mapeo sistemático de la literatura sobre las prácticas de ciberseguridad identificadas en el proceso de desarrollo de software, así como reportar elementos como: tipo de práctica, fase en la que se lleva a cabo, evidencia de su utilidad, entre otros aspectos.', 'La ciberseguridad se ha vuelto un aspecto muy relevante debido al alto índice de brechas de seguridad reportadas en productos de software. En recientes años el término “shift left security” ha cobrado importancia, ya que pretende la incorporación de prácticas de seguridad en el desarrollo de software en etapas tempranas del proceso. Actualmente existen algunos retos derivados de\nconsiderar la seguridad en etapas tempranas en el proceso de desarrollo, entre dichos retos destacan: conocimiento de fallas de seguridad comunes, mejora de los procesos de colaboración con equipos de seguridad, diseminación de actividades enfocadas a la higiene del código para prevenir algún defecto que comprometa la seguridad del producto, entre otros aspectos. El considerar actividades de seguridad en el proceso de desarrollo de software permite que se desarrollen productos menos propensos a vulnerabilidades, propician que los programadores generen conocimiento a partir de la identificación de fallas conocidas, consolidad una cultura de higiene de código, minimizar los costos asociados a fallas que pudiera detectarse a tiempo.', 'J. Straub, \"Software Engineering: The First Line of Defense for Cybersecurity,\" 2020 IEEE 11th International Conference on Software Engineering and Service Science (ICSESS), 2020, pp. 1-5, doi: 10.1109/ICSESS49938.2020.9237715.\nJohnson, C. (2012). CyberSafety: CyberSecurity and Safety-Critical Software Engineering. In: Dale, C., Anderson, T. (eds) Achieving Systems Safety. Springer, London.\nhttps://doi.org/10.1007/978-1-4471-2494-8_8\nMaurice Dawson, Pedro Taveras, Danielle Taylor, Applying Software Assurance and Cybersecurity NICE Job Tasks through Secure Software Engineering Labs, Procedia Computer Science, Volume 164, 2019, Pages 301-312, ISSN 1877-0509, https://doi.org/10.1016/j.procs.2019.12.187.\nH. Gonzalez, R. Llamas-Contreras and O. Montaño-Rivas, \"When Software Engineering meets Cybersecurity at the classroom,\" 2019 7th International Conference in Software Engineering Research and Innovation (CONISOFT), 2019, pp. 49-54, doi: 10.1109/CONISOFT.2019.00017.\nFrederico Araujo and Teryl Taylor. 2020. Improving cybersecurity hygiene through JIT patching. In Proceedings of the 28th ACM Joint Meeting on European Software Engineering Conference and Symposium on the Foundations of Software Engineering (ESEC/FSE 2020). Association for Computing Machinery, New York, NY, USA, 1421–\n 1432. https://doi.org/10.1145/3368089.3417056', 'Estudio de los diversos métodos y enfoques para la gestión, modelado y desarrollo de software, de manera que se obtenga software de calidad. Gestión de las diversas etapas del proceso de desarrollo', 0x3F, NULL, 4, 9, 1, 1, 3);
INSERT INTO `Anteproyecto` (`idAnteproyecto`, `nombreProyectoInvestigacion`, `nombreTrabajoRecepcional`, `requisitos`, `resultadosEsperados`, `numEstudiantes`, `fechaPublicacion`, `duracionAproximada`, `descripcionTrabajoRecepcional`, `descripcionProyectoInvestigacion`, `bibliografiaRecomendada`, `lineaInvestigacion`, `firmas`, `notas`, `idLAGC`, `idTipoAnteproyecto`, `idCuerpoAcademico`, `idProfesor`, `idEstado`) VALUES (5, 'VINCULACIÓN/PLADEA-FEI', 'Prácticas de Ciberseguridad en Ingeniería de Software', 'Tecnologías para la construcción de software, Principios de Construcción de Software, Diseño de Software, Procesos de Software, Administración de proyectos', 'Documento que contenga:\n• Reporte de la revisión sistemática de la literatura\n• Artículo para publicación en evento académico', 1, NULL, '12 meses', 'El presente trabajo tiene como finalidad, realizar un mapeo sistemático de la literatura sobre las prácticas de ciberseguridad identificadas en el proceso de desarrollo de software, así como reportar elementos como: tipo de práctica, fase en la que se lleva a cabo, evidencia de su utilidad, entre otros aspectos.', 'La ciberseguridad se ha vuelto un aspecto muy relevante debido al alto índice de brechas de seguridad reportadas en productos de software. En recientes años el término “shift left security” ha cobrado importancia, ya que pretende la incorporación de prácticas de seguridad en el desarrollo de software en etapas tempranas del proceso. Actualmente existen algunos retos derivados de\nconsiderar la seguridad en etapas tempranas en el proceso de desarrollo, entre dichos retos destacan: conocimiento de fallas de seguridad comunes, mejora de los procesos de colaboración con equipos de seguridad, diseminación de actividades enfocadas a la higiene del código para prevenir algún defecto que comprometa la seguridad del producto, entre otros aspectos. El considerar actividades de seguridad en el proceso de desarrollo de software permite que se desarrollen productos menos propensos a vulnerabilidades, propician que los programadores generen conocimiento a partir de la identificación de fallas conocidas, consolidad una cultura de higiene de código, minimizar los costos asociados a fallas que pudiera detectarse a tiempo.', 'J. Straub, \"Software Engineering: The First Line of Defense for Cybersecurity,\" 2020 IEEE 11th International Conference on Software Engineering and Service Science (ICSESS), 2020, pp. 1-5, doi: 10.1109/ICSESS49938.2020.9237715.\nJohnson, C. (2012). CyberSafety: CyberSecurity and Safety-Critical Software Engineering. In: Dale, C., Anderson, T. (eds) Achieving Systems Safety. Springer, London.\nhttps://doi.org/10.1007/978-1-4471-2494-8_8\nMaurice Dawson, Pedro Taveras, Danielle Taylor, Applying Software Assurance and Cybersecurity NICE Job Tasks through Secure Software Engineering Labs, Procedia Computer Science, Volume 164, 2019, Pages 301-312, ISSN 1877-0509, https://doi.org/10.1016/j.procs.2019.12.187.\nH. Gonzalez, R. Llamas-Contreras and O. Montaño-Rivas, \"When Software Engineering meets Cybersecurity at the classroom,\" 2019 7th International Conference in Software Engineering Research and Innovation (CONISOFT), 2019, pp. 49-54, doi: 10.1109/CONISOFT.2019.00017.\nFrederico Araujo and Teryl Taylor. 2020. Improving cybersecurity hygiene through JIT patching. In Proceedings of the 28th ACM Joint Meeting on European Software Engineering Conference and Symposium on the Foundations of Software Engineering (ESEC/FSE 2020). Association for Computing Machinery, New York, NY, USA, 1421–\n 1432. https://doi.org/10.1145/3368089.3417056', 'Estudio de los diversos métodos y enfoques para la gestión, modelado y desarrollo de software, de manera que se obtenga software de calidad. Gestión de las diversas etapas del proceso de desarrollo', 0x3F, NULL, 4, 9, 1, 1, 3);
INSERT INTO `Anteproyecto` (`idAnteproyecto`, `nombreProyectoInvestigacion`, `nombreTrabajoRecepcional`, `requisitos`, `resultadosEsperados`, `numEstudiantes`, `fechaPublicacion`, `duracionAproximada`, `descripcionTrabajoRecepcional`, `descripcionProyectoInvestigacion`, `bibliografiaRecomendada`, `lineaInvestigacion`, `firmas`, `notas`, `idLAGC`, `idTipoAnteproyecto`, `idCuerpoAcademico`, `idProfesor`, `idEstado`) VALUES (6, 'f', 'f', 'f', 'f', 1, NULL, 'f', 'f', 'f', 'f', 'f', NULL, NULL, 4, 10, 1, NULL, 3);
INSERT INTO `Anteproyecto` (`idAnteproyecto`, `nombreProyectoInvestigacion`, `nombreTrabajoRecepcional`, `requisitos`, `resultadosEsperados`, `numEstudiantes`, `fechaPublicacion`, `duracionAproximada`, `descripcionTrabajoRecepcional`, `descripcionProyectoInvestigacion`, `bibliografiaRecomendada`, `lineaInvestigacion`, `firmas`, `notas`, `idLAGC`, `idTipoAnteproyecto`, `idCuerpoAcademico`, `idProfesor`, `idEstado`) VALUES (7, 'f', 'f', 'f', 'f', 1, NULL, 'f', 'f', 'f', 'f', 'f', NULL, '¿Por que todo f?', 4, 9, 1, 1, 2);
INSERT INTO `Anteproyecto` (`idAnteproyecto`, `nombreProyectoInvestigacion`, `nombreTrabajoRecepcional`, `requisitos`, `resultadosEsperados`, `numEstudiantes`, `fechaPublicacion`, `duracionAproximada`, `descripcionTrabajoRecepcional`, `descripcionProyectoInvestigacion`, `bibliografiaRecomendada`, `lineaInvestigacion`, `firmas`, `notas`, `idLAGC`, `idTipoAnteproyecto`, `idCuerpoAcademico`, `idProfesor`, `idEstado`) VALUES (8, 'Prueba Borrador', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Hola buenas tardes', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `Anteproyecto` (`idAnteproyecto`, `nombreProyectoInvestigacion`, `nombreTrabajoRecepcional`, `requisitos`, `resultadosEsperados`, `numEstudiantes`, `fechaPublicacion`, `duracionAproximada`, `descripcionTrabajoRecepcional`, `descripcionProyectoInvestigacion`, `bibliografiaRecomendada`, `lineaInvestigacion`, `firmas`, `notas`, `idLAGC`, `idTipoAnteproyecto`, `idCuerpoAcademico`, `idProfesor`, `idEstado`) VALUES (9, 'VINCULACIÓN/PLADEA-FEI', 'Prácticas de Ciberseguridad en Ingeniería de Software', 'Tecnologías para la construcción de software, Principios de Construcción de Software, Diseño de Software, Procesos de Software, Administración de proyectos', 'Documento que contenga:\n• Reporte de la revisión sistemática de la literatura\n• Artículo para publicación en evento académico', 2, NULL, '12 meses', 'El presente trabajo tiene como finalidad, realizar un mapeo sistemático de la literatura sobre las prácticas de ciberseguridad identificadas en el proceso de desarrollo de software, así como reportar elementos como: tipo de práctica, fase en la que se lleva a cabo, evidencia de su utilidad, entre otros aspectos.', 'La ciberseguridad se ha vuelto un aspecto muy relevante debido al alto índice de brechas de seguridad reportadas en productos de software. En recientes años el término “shift left security” ha cobrado importancia, ya que pretende la incorporación de prácticas de seguridad en el desarrollo de software en etapas tempranas del proceso. Actualmente existen algunos retos derivados de\nconsiderar la seguridad en etapas tempranas en el proceso de desarrollo, entre dichos retos destacan: conocimiento de fallas de seguridad comunes, mejora de los procesos de colaboración con equipos de seguridad, diseminación de actividades enfocadas a la higiene del código para prevenir algún defecto que comprometa la seguridad del producto, entre otros aspectos. El considerar actividades de seguridad en el proceso de desarrollo de software permite que se desarrollen productos menos propensos a vulnerabilidades, propician que los programadores generen conocimiento a partir de la identificación de fallas conocidas, consolidad una cultura de higiene de código, minimizar los costos asociados a fallas que pudiera detectarse a tiempo.', 'J. Straub, \"Software Engineering: The First Line of Defense for Cybersecurity,\" 2020 IEEE 11th International Conference on Software Engineering and Service Science (ICSESS), 2020, pp. 1-5, doi: 10.1109/ICSESS49938.2020.9237715.\nJohnson, C. (2012). CyberSafety: CyberSecurity and Safety-Critical Software Engineering. In: Dale, C., Anderson, T. (eds) Achieving Systems Safety. Springer, London.\nhttps://doi.org/10.1007/978-1-4471-2494-8_8\nMaurice Dawson, Pedro Taveras, Danielle Taylor, Applying Software Assurance and Cybersecurity NICE Job Tasks through Secure Software Engineering Labs, Procedia Computer Science, Volume 164, 2019, Pages 301-312, ISSN 1877-0509, https://doi.org/10.1016/j.procs.2019.12.187.\nH. Gonzalez, R. Llamas-Contreras and O. Montaño-Rivas, \"When Software Engineering meets Cybersecurity at the classroom,\" 2019 7th International Conference in Software Engineering Research and Innovation (CONISOFT), 2019, pp. 49-54, doi: 10.1109/CONISOFT.2019.00017.\nFrederico Araujo and Teryl Taylor. 2020. Improving cybersecurity hygiene through JIT patching. In Proceedings of the 28th ACM Joint Meeting on European Software Engineering Conference and Symposium on the Foundations of Software Engineering (ESEC/FSE 2020). Association for Computing Machinery, New York, NY, USA, 1421–\n 1432. https://doi.org/10.1145/3368089.3417056', 'Estudio de los diversos métodos y enfoques para la gestión, modelado y desarrollo de software, de manera que se obtenga software de calidad. Gestión de las diversas etapas del proceso de desarrollo', 0x3F, NULL, 4, 9, 1, 1, 4);
INSERT INTO `Anteproyecto` (`idAnteproyecto`, `nombreProyectoInvestigacion`, `nombreTrabajoRecepcional`, `requisitos`, `resultadosEsperados`, `numEstudiantes`, `fechaPublicacion`, `duracionAproximada`, `descripcionTrabajoRecepcional`, `descripcionProyectoInvestigacion`, `bibliografiaRecomendada`, `lineaInvestigacion`, `firmas`, `notas`, `idLAGC`, `idTipoAnteproyecto`, `idCuerpoAcademico`, `idProfesor`, `idEstado`) VALUES (10, 'VINCULACIÓN/PLADEA-FEI', 'Prácticas de Ciberseguridad en Ingeniería de Software', 'Tecnologías para la construcción de software, Principios de Construcción de Software, Diseño de Software, Procesos de Software, Administración de proyectos', 'Documento que contenga:\n• Reporte de la revisión sistemática de la literatura\n• Artículo para publicación en evento académico', 2, NULL, '12 meses', 'El presente trabajo tiene como finalidad, realizar un mapeo sistemático de la literatura sobre las prácticas de ciberseguridad identificadas en el proceso de desarrollo de software, así como reportar elementos como: tipo de práctica, fase en la que se lleva a cabo, evidencia de su utilidad, entre otros aspectos.', 'La ciberseguridad se ha vuelto un aspecto muy relevante debido al alto índice de brechas de seguridad reportadas en productos de software. En recientes años el término “shift left security” ha cobrado importancia, ya que pretende la incorporación de prácticas de seguridad en el desarrollo de software en etapas tempranas del proceso. Actualmente existen algunos retos derivados de\nconsiderar la seguridad en etapas tempranas en el proceso de desarrollo, entre dichos retos destacan: conocimiento de fallas de seguridad comunes, mejora de los procesos de colaboración con equipos de seguridad, diseminación de actividades enfocadas a la higiene del código para prevenir algún defecto que comprometa la seguridad del producto, entre otros aspectos. El considerar actividades de seguridad en el proceso de desarrollo de software permite que se desarrollen productos menos propensos a vulnerabilidades, propician que los programadores generen conocimiento a partir de la identificación de fallas conocidas, consolidad una cultura de higiene de código, minimizar los costos asociados a fallas que pudiera detectarse a tiempo.', 'J. Straub, \"Software Engineering: The First Line of Defense for Cybersecurity,\" 2020 IEEE 11th International Conference on Software Engineering and Service Science (ICSESS), 2020, pp. 1-5, doi: 10.1109/ICSESS49938.2020.9237715.\nJohnson, C. (2012). CyberSafety: CyberSecurity and Safety-Critical Software Engineering. In: Dale, C., Anderson, T. (eds) Achieving Systems Safety. Springer, London.\nhttps://doi.org/10.1007/978-1-4471-2494-8_8\nMaurice Dawson, Pedro Taveras, Danielle Taylor, Applying Software Assurance and Cybersecurity NICE Job Tasks through Secure Software Engineering Labs, Procedia Computer Science, Volume 164, 2019, Pages 301-312, ISSN 1877-0509, https://doi.org/10.1016/j.procs.2019.12.187.\nH. Gonzalez, R. Llamas-Contreras and O. Montaño-Rivas, \"When Software Engineering meets Cybersecurity at the classroom,\" 2019 7th International Conference in Software Engineering Research and Innovation (CONISOFT), 2019, pp. 49-54, doi: 10.1109/CONISOFT.2019.00017.\nFrederico Araujo and Teryl Taylor. 2020. Improving cybersecurity hygiene through JIT patching. In Proceedings of the 28th ACM Joint Meeting on European Software Engineering Conference and Symposium on the Foundations of Software Engineering (ESEC/FSE 2020). Association for Computing Machinery, New York, NY, USA, 1421–\n 1432. https://doi.org/10.1145/3368089.3417056', 'Estudio de los diversos métodos y enfoques para la gestión, modelado y desarrollo de software, de manera que se obtenga software de calidad. Gestión de las diversas etapas del proceso de desarrollo', 0x3F, NULL, 4, 9, 1, 1, 4);
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
  `idProfesor` int DEFAULT NULL,
  PRIMARY KEY (`idCuerpoAcademico`),
  KEY `FK_CuerposAcademicosProfesores_CuerposAcademicos` (`idProfesor`),
  CONSTRAINT `FK_CuerposAcademicosProfesores_CuerposAcademicos` FOREIGN KEY (`idProfesor`) REFERENCES `Profesores` (`idProfesor`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of CuerposAcademicos
-- ----------------------------
BEGIN;
INSERT INTO `CuerposAcademicos` (`idCuerpoAcademico`, `nombre`, `descripcion`, `idProfesor`) VALUES (1, 'Ingenieria y Tecnología de Software', 'El trabajo del Grupo se orienta al estudio de los procesos de desarrollo, operación, gestión y mantenimiento de software, con vistas a analizar, diseñar, desarrollar, adaptar, evaluar y aplicar modelos, métodos y herramientas para la sistematización y cuantificación de tales procesos y sus productos, con énfasis en el logro de la calidad y en los aspectos humanos.\n\nEl trabajo del Grupo se orienta al estudio de los procesos de desarrollo, operación, gestión y mantenimiento de software, con vistas a analizar, diseñar, desarrollar, adaptar, evaluar y aplicar modelos, métodos y herramientas para la sistematización y cuantificación de tales procesos y sus productos, con énfasis en el logro de la calidad y en los aspectos humanos.\n\nEl trabajo del Grupo se orienta al estudio de los procesos de desarrollo, operación, gestión y mantenimiento de software, con vistas a analizar, diseñar, desarrollar, adaptar, evaluar y aplicar modelos, métodos y herramientas para la sistematización y cuantificación de tales procesos y sus productos, con énfasis en el logro de la calidad y en los aspectos humanos.\n\nEl trabajo del Grupo se orienta al estudio de los procesos de desarrollo, operación, gestión y mantenimiento de software, con vistas a analizar, diseñar, desarrollar, adaptar, evaluar y aplicar modelos, métodos y herramientas para la sistematización y cuantificación de tales procesos y sus productos, con énfasis en el logro de la calidad y en los aspectos humanos.\n\n', NULL);
INSERT INTO `CuerposAcademicos` (`idCuerpoAcademico`, `nombre`, `descripcion`, `idProfesor`) VALUES (2, 'Tecnología Computacional y Educativa', 'Analizar y evaluar software relacionado con las innovaciones educativas desde el enfoque pedagógico y técnico.', NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of EstadoAnteproyecto
-- ----------------------------
BEGIN;
INSERT INTO `EstadoAnteproyecto` (`idEstado`, `descripcion`) VALUES (1, 'Borrador');
INSERT INTO `EstadoAnteproyecto` (`idEstado`, `descripcion`) VALUES (2, 'Corregir');
INSERT INTO `EstadoAnteproyecto` (`idEstado`, `descripcion`) VALUES (3, 'Por Validar');
INSERT INTO `EstadoAnteproyecto` (`idEstado`, `descripcion`) VALUES (4, 'Disponible');
INSERT INTO `EstadoAnteproyecto` (`idEstado`, `descripcion`) VALUES (5, 'Asignado');
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
  `nombreLGAC` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `descripcionLGAC` text,
  `idCuerpoAcademico` int NOT NULL,
  PRIMARY KEY (`idLGAC`),
  KEY `FK_LGAC_CuerposAcademicos` (`idCuerpoAcademico`),
  CONSTRAINT `FK_LGAC_CuerposAcademicos` FOREIGN KEY (`idCuerpoAcademico`) REFERENCES `CuerposAcademicos` (`idCuerpoAcademico`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of LGAC
-- ----------------------------
BEGIN;
INSERT INTO `LGAC` (`idLGAC`, `nombreLGAC`, `descripcionLGAC`, `idCuerpoAcademico`) VALUES (4, 'Gestión, modelado y desarrollo de software', 'Se orienta al estudio de los diversos métodos y enfoques para la gestión, modelado y desarrollo de software, de manera que se obtenga software de calidad. Gestión de las diversas etapas del proceso de desarrollo, incluyendo hasta la medición del proceso y artefactos. Modelado de los diversos artefactos en las distintas etapas del proceso de desarrollo.', 1);
INSERT INTO `LGAC` (`idLGAC`, `nombreLGAC`, `descripcionLGAC`, `idCuerpoAcademico`) VALUES (5, 'Tecnologías de software', 'Se orienta al estudio de diversas propiedades, enfoques, métodos de modelado y herramientas que conforman cada una de las diversas tecnologías aplicables al desarrollo del software con vistas a su adaptación, mejora y sustitución en el medio nacional.', 1);
INSERT INTO `LGAC` (`idLGAC`, `nombreLGAC`, `descripcionLGAC`, `idCuerpoAcademico`) VALUES (6, 'Tecnología Computacional en la Educación y la Sociedad', 'Tendiente  a  lograr  un  enfoque  transdisciplinar  se  analizan  y  evalúan  fenómenos  complejos  que  mediante  una  variedad  de  técnicas  educativas,  sociales,  emotivas,  cognitivas,  y  computacionales  permitan  plantear  soluciones,  prototipos,  metodologías y servicios para el avance de la ciencia y la sociedad.', 2);
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
  `idTipoProfesor` int DEFAULT NULL,
  PRIMARY KEY (`idProfesor`),
  KEY `FK_ProfesoresUsuario_Profesores` (`idUsuario`),
  KEY `FK_ProfesoresCuerposAcademicos_Profesores` (`idCuerpoAcademico`),
  KEY `FK_ProfesoresTipoProfesor_Profesores` (`idTipoProfesor`),
  CONSTRAINT `FK_ProfesoresCuerposAcademicos_Profesores` FOREIGN KEY (`idCuerpoAcademico`) REFERENCES `CuerposAcademicos` (`idCuerpoAcademico`) ON DELETE CASCADE,
  CONSTRAINT `FK_ProfesoresTipoProfesor_Profesores` FOREIGN KEY (`idTipoProfesor`) REFERENCES `TipoProfesor` (`idTipoProfesor`) ON DELETE CASCADE,
  CONSTRAINT `FK_ProfesoresUsuario_Profesores` FOREIGN KEY (`idUsuario`) REFERENCES `Usuarios` (`idUsuario`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Profesores
-- ----------------------------
BEGIN;
INSERT INTO `Profesores` (`idProfesor`, `idUsuario`, `idCuerpoAcademico`, `idTipoProfesor`) VALUES (1, 2, 1, NULL);
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
  `nombreTipoProyecto` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`idTipoProyecto`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of TipoAnteproyecto
-- ----------------------------
BEGIN;
INSERT INTO `TipoAnteproyecto` (`idTipoProyecto`, `nombreTipoProyecto`) VALUES (7, 'Tesina');
INSERT INTO `TipoAnteproyecto` (`idTipoProyecto`, `nombreTipoProyecto`) VALUES (8, 'Tesis');
INSERT INTO `TipoAnteproyecto` (`idTipoProyecto`, `nombreTipoProyecto`) VALUES (9, 'Monografía');
INSERT INTO `TipoAnteproyecto` (`idTipoProyecto`, `nombreTipoProyecto`) VALUES (10, 'Memoria');
INSERT INTO `TipoAnteproyecto` (`idTipoProyecto`, `nombreTipoProyecto`) VALUES (11, 'Reporte');
INSERT INTO `TipoAnteproyecto` (`idTipoProyecto`, `nombreTipoProyecto`) VALUES (12, 'Trabajo Práctico-Técnico');
INSERT INTO `TipoAnteproyecto` (`idTipoProyecto`, `nombreTipoProyecto`) VALUES (13, 'Trabajo Práctico-Científico');
INSERT INTO `TipoAnteproyecto` (`idTipoProyecto`, `nombreTipoProyecto`) VALUES (14, 'Proyecto de Inversión');
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
  `descripcion` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`idTipoUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of TipoUsuario
-- ----------------------------
BEGIN;
INSERT INTO `TipoUsuario` (`idTipoUsuario`, `descripcion`) VALUES (1, 'Administrador');
INSERT INTO `TipoUsuario` (`idTipoUsuario`, `descripcion`) VALUES (2, 'Profesor');
INSERT INTO `TipoUsuario` (`idTipoUsuario`, `descripcion`) VALUES (3, 'Estudiante');
INSERT INTO `TipoUsuario` (`idTipoUsuario`, `descripcion`) VALUES (4, 'Estudiante Con Anteproyecto');
INSERT INTO `TipoUsuario` (`idTipoUsuario`, `descripcion`) VALUES (5, 'Encargado Cuerpo Academico');
INSERT INTO `TipoUsuario` (`idTipoUsuario`, `descripcion`) VALUES (6, 'Director');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Usuarios
-- ----------------------------
BEGIN;
INSERT INTO `Usuarios` (`idUsuario`, `nombre`, `apellidoPaterno`, `apellidoMaterno`, `correoInstitucional`, `numeroTelefonico`, `nombreUsuario`, `password`, `imagenUsuario`, `idTipoUsuario`) VALUES (1, 'Administrador', NULL, NULL, NULL, NULL, 'admin', '123456', NULL, 1);
INSERT INTO `Usuarios` (`idUsuario`, `nombre`, `apellidoPaterno`, `apellidoMaterno`, `correoInstitucional`, `numeroTelefonico`, `nombreUsuario`, `password`, `imagenUsuario`, `idTipoUsuario`) VALUES (2, 'Cesar Emiliano', 'Lezama', 'Lopez', 'zs21013857@estudiantes.uv.mx', '2281627337', 'cesarele23', '123456', NULL, 6);
INSERT INTO `Usuarios` (`idUsuario`, `nombre`, `apellidoPaterno`, `apellidoMaterno`, `correoInstitucional`, `numeroTelefonico`, `nombreUsuario`, `password`, `imagenUsuario`, `idTipoUsuario`) VALUES (3, 'Kirbith', 'Cubillas', 'Hernández', 'zs21013857@outlook.com', '2281627337', 'Kirbith_Cubillas', '123456', NULL, 4);
INSERT INTO `Usuarios` (`idUsuario`, `nombre`, `apellidoPaterno`, `apellidoMaterno`, `correoInstitucional`, `numeroTelefonico`, `nombreUsuario`, `password`, `imagenUsuario`, `idTipoUsuario`) VALUES (4, 'César', 'Lezama', 'Lopez', 'emilia@outlook.com', '2281627337', 'Yo_Lezama', '123456', NULL, 5);
INSERT INTO `Usuarios` (`idUsuario`, `nombre`, `apellidoPaterno`, `apellidoMaterno`, `correoInstitucional`, `numeroTelefonico`, `nombreUsuario`, `password`, `imagenUsuario`, `idTipoUsuario`) VALUES (5, 'Hola', 'Lezama', 'Lopez', 'emiliano@outloo.com', '2281627337', 'Hola_Lezama', '123456', NULL, 4);
INSERT INTO `Usuarios` (`idUsuario`, `nombre`, `apellidoPaterno`, `apellidoMaterno`, `correoInstitucional`, `numeroTelefonico`, `nombreUsuario`, `password`, `imagenUsuario`, `idTipoUsuario`) VALUES (6, 'Popo', 'Lezama', 'Lopez', 'emiliano@outloo.com', '2281627337', 'popo_lezama', '123456', 0x3F, 3);
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
