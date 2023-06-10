package sspger.modelos.pojo;


public class Profesor {

    private int idProfesor;
    private int idUsuario;
    private int idCuerpoAcademico;
    private int codigoRespuesta;

    public Profesor() {
    }

    public Profesor(int idProfesor, int idUsuario, int idCuerpoAcademico, int codigoRespuesta) {
        this.idProfesor = idProfesor;
        this.idUsuario = idUsuario;
        this.idCuerpoAcademico = idCuerpoAcademico;
        this.codigoRespuesta = codigoRespuesta;
    }
    
    public int getIdProfesor() {
        return idProfesor;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdCuerpoAcademico() {
        return idCuerpoAcademico;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }
    
    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdCuerpoAcademico(int idCuerpoAcademico) {
        this.idCuerpoAcademico = idCuerpoAcademico;
    }  

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }
}
