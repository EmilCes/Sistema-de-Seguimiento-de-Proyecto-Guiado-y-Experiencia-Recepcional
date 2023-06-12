package sspger.modelos.pojo;


public class Alumno {
    
    private int codigoRespuesta;
    private int idAlumno;
    private int idUsuario;
    private int idAnteproyecto;
    private String matricula;
    private String nombreCompleto;
    private byte[] imagenUsuario;

    public Alumno() {
    }

    public Alumno(int codigoRespuesta, int idAlumno, int idUsuario, int idAnteproyecto, String matricula, String nombreCompleto, byte[] imagenUsuario) {
        this.codigoRespuesta = codigoRespuesta;
        this.idAlumno = idAlumno;
        this.idUsuario = idUsuario;
        this.idAnteproyecto = idAnteproyecto;
        this.matricula = matricula;
        this.nombreCompleto = nombreCompleto;
        this.imagenUsuario = imagenUsuario;
    }


    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdAnteproyecto() {
        return idAnteproyecto;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public byte[] getImagenUsuario() {
        return imagenUsuario;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdAnteproyecto(int idAnteproyecto) {
        this.idAnteproyecto = idAnteproyecto;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setImagenUsuario(byte[] imagenUsuario) {
        this.imagenUsuario = imagenUsuario;
    }
        
}