CREATE DATABASE SSPGER;
USE SSPGER;

CREATE TABLE Anteproyecto(
  idAnteproyecto INT NOT NULL AUTO_INCREMENT,
  nombreProyectoInvestigacion NVARCHAR(100),
  nombreTrabajoRecepcional NVARCHAR(100),
  requisitos NVARCHAR(300),
  resultadosEsperados NVARCHAR(300),
  numEstudiantes INT,
  fechaPublicacion DATE,
  duracionAproximada INT,
  descripcionTrabajoRecepcional TEXT,
  descripcionProyectoInvestigacion TEXT,
  bibliografiaRecomendada TEXT,
  lineaInvestigacion NVARCHAR(50),
  firmas BLOB,
  notas VARCHAR(300),
  idLAGC INT NOT NULL,
  idTipoAnteproyecto INT NOT NULL,
  idCuerpoAcademico INT NOT NULL,
  idProfesor INT NOT NULL,
  PRIMARY KEY (idAnteproyecto)
);

CREATE TABLE Alumnos(
  idAlumno INT NOT NULL AUTO_INCREMENT,
  idUsuario INT NOT NULL,
  idAnteproyecto INT NOT NULL,
  PRIMARY KEY (idAlumno)
);

CREATE TABLE Usuarios(
  idUsuario INT NOT NULL AUTO_INCREMENT,
  nombre NVARCHAR(25),
  apellidoPaterno NVARCHAR(25),
  apellidoMaterno NVARCHAR(25),
  correoInstitucional NVARCHAR(25),
  numeroTelefonico INT,
  contraseña NVARCHAR(25),
  imagenUsuario BLOB,
  idTipoUsuario INT NOT NULL,
  PRIMARY KEY (idUsuario)
);

CREATE TABLE TipoUsuario(
  idTipoUsuario INT NOT NULL AUTO_INCREMENT,
  descripcion NVARCHAR(25),
  PRIMARY KEY (idTipoUsuario)
);


CREATE TABLE AlumnosCursos(
  idAlumnosCursos INT NOT NULL AUTO_INCREMENT,
  idAlumno INT NOT NULL,
  idCurso INT NOT NULL,
  PRIMARY KEY (idAlumnosCursos)
);


CREATE TABLE Cursos(
  idCurso INT NOT NULL AUTO_INCREMENT,
  bloque NVARCHAR(50),
  fechaInicio DATE,
  fechaFin DATE,
  nombre NVARCHAR(50),
  nrc NVARCHAR(50),
  idProfesor INT NOT NULL,
  PRIMARY KEY (idCurso)
);

CREATE TABLE Profesores(
  idProfesor INT NOT NULL AUTO_INCREMENT,
  idUsuario INT NOT NULL,
  idCuerpoAcademico INT,
  idTipoProfesor INT NOT NULL,
  PRIMARY KEY (idProfesor)
);

CREATE TABLE TipoProfesor(
  idTipoProfesor INT NOT NULL AUTO_INCREMENT,
  descripcion NVARCHAR(50),
  PRIMARY KEY (idTipoProfesor)
);

CREATE TABLE Actividades(
  idActividad INT NOT NULL AUTO_INCREMENT,
  titulo NVARCHAR(50),
  descripcion TEXT,
  fechaInicio DATETIME,
  fechaFin DATETIME,
  PRIMARY KEY (idActividad)
);

CREATE TABLE AnteproyectosCodirectores(
  idAnteproyectosCodirectores INT NOT NULL AUTO_INCREMENT,
  anteproyecto INT NOT NULL,
  idProfesor INT NOT NULL,
  PRIMARY KEY (idAnteproyectosCodirectores)
);

CREATE TABLE LAGC(
  idLGAC INT NOT NULL AUTO_INCREMENT,
  nombreLGAC NVARCHAR(25),
  descripcionLGAC TEXT,
  PRIMARY KEY (idLGAC)
);

CREATE TABLE TipoAnteproyecto(
  idTipoProyecto INT NOT NULL AUTO_INCREMENT,
  nombreTipoProyecto NVARCHAR(25),
  PRIMARY KEY (idTipoProyecto)
);

CREATE TABLE ValoresNivelesDesempenos(
  idValorNivelDesempeno INT NOT NULL AUTO_INCREMENT,
  valor DECIMAL,
  PRIMARY KEY (idValorNivelDesempeno)
);


CREATE TABLE CuerposAcademicos(
  idCuerpoAcademico INT NOT NULL AUTO_INCREMENT,
  nombre NVARCHAR(50),
  descripcion TEXT,
  idProfesor INT NOT NULL,
  PRIMARY KEY (idCuerpoAcademico)
);

CREATE TABLE Entregables(
  idEntregable INT NOT NULL AUTO_INCREMENT,
  nombreEntregable NVARCHAR(50),
  descripcionEntregable TEXT,
  archivoEntregable BLOB,
  idEvaluacion INT NOT NULL,
  idAnteproyecto INT NOT NULL,
  PRIMARY KEY (idEntregable)
);

CREATE TABLE Evaluaciones(            
  idEvaluación INT NOT NULL AUTO_INCREMENT,
  avance DECIMAL,
  calificacaion DECIMAL,
  idRubrica INT NOT NULL,
  PRIMARY KEY (idEvaluación)
);

CREATE TABLE Rubricas(
  idRubrica INT NOT NULL AUTO_INCREMENT,
  calificacaion DECIMAL,
  idCriterio INT NOT NULL,
  idNivelDesempeno INT NOT NULL,
  PRIMARY KEY (idRubrica)
);

CREATE TABLE Criterios(
  idCriterio INT NOT NULL AUTO_INCREMENT,
  descripcion NVARCHAR(50),
  PRIMARY KEY (idCriterio)
);


CREATE TABLE NivelesDesempeno(
  idNivelDesempeno INT NOT NULL AUTO_INCREMENT,
  descripcion NVARCHAR(50),
  idValorNivelDesempeno INT NOT NULL,
  PRIMARY KEY (idNivelDesempeno)
);

CREATE TABLE EstadoAnteproyecto(
  idEstado INT NOT NULL AUTO_INCREMENT,
  descripcion NVARCHAR(25),
  PRIMARY KEY (idEstado)
);


ALTER TABLE Anteproyecto ADD CONSTRAINT FK_AnteproyectoLGAC_Anteproyecto FOREIGN KEY (idLAGC)
REFERENCES LGAC(idLGAC) ON DELETE CASCADE;
ALTER TABLE Anteproyecto ADD CONSTRAINT FK_AnteproyectoTipoAnteproyecto_Anteproyecto FOREIGN KEY (idTipoAnteproyecto)
REFERENCES TipoAnteproyecto(idTipoProyecto) ON DELETE CASCADE;
ALTER TABLE Anteproyecto ADD CONSTRAINT FK_AnteproyectoCuerpoAcademico_Anteproyecto FOREIGN KEY (idCuerpoAcademico)
REFERENCES CuerposAcademicos(idCuerpoAcademico) ON DELETE CASCADE;
ALTER TABLE Anteproyecto ADD CONSTRAINT FK_AnteproyectoProfesores_Anteproyecto FOREIGN KEY (idProfesor)
REFERENCES Profesores(idProfesor) ON DELETE CASCADE;
ALTER TABLE Anteproyecto ADD CONSTRAINT FK_AnteproyectoEstado_Anteproyecto FOREIGN KEY (idEstado)
REFERENCES EstadoAnteproyecto(idEstado) ON DELETE CASCADE;

ALTER TABLE Entregables ADD CONSTRAINT FK_EntregablesEvaluaciones_Entregables FOREIGN KEY (idEvaluacion)
REFERENCES Evaluaciones(idEvaluacion) ON DELETE CASCADE;
ALTER TABLE Entregables ADD CONSTRAINT FK_EntregablesAnteproyecto_Entregables FOREIGN KEY (idAnteproyecto)
REFERENCES Anteproyecto(idAnteproyecto) ON DELETE CASCADE;

ALTER TABLE Evaluaciones ADD CONSTRAINT FK_EvaluacionesRubricas_Evaluaciones FOREIGN KEY (idRubrica)
REFERENCES Rubricas(idRubrica) ON DELETE CASCADE;

ALTER TABLE Rubricas ADD CONSTRAINT FK_RubricasCriterios_Rubricas FOREIGN KEY (idCriterio)
REFERENCES Criterios(idCriterio) ON DELETE CASCADE;
ALTER TABLE Rubricas ADD CONSTRAINT FK_RubricasNivelesDesempeno_Rubricas FOREIGN KEY (idNivelDesempeno)
REFERENCES NivelesDesempeno(idNivelDesempeno) ON DELETE CASCADE;

ALTER TABLE NivelesDesempeno ADD CONSTRAINT FK_NivelesDesempenoValoresNivelesDesemeno_NivelesDesempeno FOREIGN KEY (idValorNivelDesempeno)
REFERENCES ValoresNivelesDesempenos(idValorNivelDesempeno) ON DELETE CASCADE;

ALTER TABLE CuerposAcademicos ADD CONSTRAINT FK_CuerposAcademicosProfesores_CuerposAcademicos FOREIGN KEY (idProfesor)
REFERENCES Profesores(idProfesor) ON DELETE CASCADE;

ALTER TABLE Usuarios ADD CONSTRAINT FK_UsuariosTiposUsuarios_Usuario FOREIGN KEY (idTipoUsuario)
REFERENCES TipoUsuario(idTipoUsuario) ON DELETE CASCADE;

ALTER TABLE Alumnos ADD CONSTRAINT FK_AlumnosUsuarios_Alumnos FOREIGN KEY (idUsuario)
REFERENCES Usuarios(idUsuario) ON DELETE CASCADE;
ALTER TABLE Alumnos ADD CONSTRAINT FK_AlumnosAnteproyectos_Alumnos FOREIGN KEY (idAnteproyecto)
REFERENCES Anteproyecto(idAnteproyecto) ON DELETE CASCADE;

ALTER TABLE AlumnosCursos ADD CONSTRAINT FK_AlumnosCursosAlumno_AlumnosCursos FOREIGN KEY (idAlumno)
REFERENCES Alumnos(idAlumno) ON DELETE CASCADE;
ALTER TABLE AlumnosCursos ADD CONSTRAINT FK_AlumnosCursosCursos_AlumnosCursos FOREIGN KEY (idCurso)
REFERENCES Cursos(idCurso) ON DELETE CASCADE;

ALTER TABLE Cursos ADD CONSTRAINT FK_CursosProfesores_Cursos FOREIGN KEY (idProfesor)
REFERENCES Profesores(idProfesor) ON DELETE CASCADE;

ALTER TABLE Profesores ADD CONSTRAINT FK_ProfesoresUsuario_Profesores FOREIGN KEY (idUsuario)
REFERENCES Usuarios(idUsuario) ON DELETE CASCADE;
ALTER TABLE Profesores ADD CONSTRAINT FK_ProfesoresCuerposAcademicos_Profesores FOREIGN KEY (idCuerpoAcademico)
REFERENCES CuerposAcademicos(idCuerpoAcademico) ON DELETE CASCADE;
ALTER TABLE Profesores ADD CONSTRAINT FK_ProfesoresTipoProfesor_Profesores FOREIGN KEY (idTipoProfesor)
REFERENCES TipoProfesor(idTipoProfesor) ON DELETE CASCADE;

ALTER TABLE Actividades ADD CONSTRAINT FK_ActividadesAnteproyecto_Actividades FOREIGN KEY (idAnteproyecto)
REFERENCES Anteproyecto(idAnteproyecto) ON DELETE CASCADE;

ALTER TABLE AnteproyectosCodirectores ADD CONSTRAINT FK_AnteproyectosCodirectores_AnteCod FOREIGN KEY (idAnteproyecto)
REFERENCES Anteproyecto(idAnteproyecto) ON DELETE CASCADE;
ALTER TABLE AnteproyectosCodirectores ADD CONSTRAINT FK_AnteproyectosCodirectoresAnteproyecto_Profesores FOREIGN KEY (idProfesor)
REFERENCES Profesores(idProfesor) ON DELETE CASCADE;



