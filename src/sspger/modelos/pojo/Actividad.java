
package sspger.modelos.pojo;


public class Actividad {
    
    private int codigoRespuesta;
    private int idActividad;
    private String titulo;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;
    private int idAnteproyecto;

    public Actividad() {
    }

    public Actividad(int codigoRespuesta, int idActividad, String titulo, String descripcion, String fechaInicio, String fechaFin, int idAnteproyecto) {
        this.codigoRespuesta = codigoRespuesta;
        this.idActividad = idActividad;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idAnteproyecto = idAnteproyecto;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public int getIdAnteproyecto() {
        return idAnteproyecto;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setIdAnteproyecto(int idAnteproyecto) {
        this.idAnteproyecto = idAnteproyecto;
    }
       
}
