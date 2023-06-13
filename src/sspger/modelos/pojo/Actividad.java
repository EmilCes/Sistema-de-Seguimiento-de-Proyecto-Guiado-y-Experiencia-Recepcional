
package sspger.modelos.pojo;


public class Actividad {
    
    private int codigoRespuesta;
    private int idActividad;
    private String titulo;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;
    private int idEstadoActividad;
    private int idAnteproyecto;
    private float calificacion;

    public Actividad() {
    }

    public Actividad(int codigoRespuesta, int idActividad, String titulo, String descripcion, String fechaInicio, String fechaFin, int idEstadoActividad, int idAnteproyecto, float calificacion) {
        this.codigoRespuesta = codigoRespuesta;
        this.idActividad = idActividad;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idEstadoActividad = idEstadoActividad;
        this.idAnteproyecto = idAnteproyecto;
        this.calificacion = calificacion;
    }

   

    

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public int getIdEstadoActividad() {
        return idEstadoActividad;
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

    public void setIdEstadoActividad(int idEstadoActividad) {
        this.idEstadoActividad = idEstadoActividad;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    
    
    
    

}