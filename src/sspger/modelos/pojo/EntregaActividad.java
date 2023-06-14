package sspger.modelos.pojo;


public class EntregaActividad {
 
    private int codigoRespuesta;
    private int idEntrega;
    private int idActividad;
    private String comentarios;
    private byte[] archivo;
    private String observaciones;
    private float calificacion;
    private int idEstado;
    
    public EntregaActividad() {
    }

    public EntregaActividad(int codigoRespuesta, int idEntrega, int idActividad, String comentarios, byte[] archivo, String observaciones, float calificacion) {
        this.codigoRespuesta = codigoRespuesta;
        this.idEntrega = idEntrega;
        this.idActividad = idActividad;
        this.comentarios = comentarios;
        this.archivo = archivo;
        this.observaciones = observaciones;
        this.calificacion = calificacion;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public int getIdEntrega() {
        return idEntrega;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public String getComentarios() {
        return comentarios;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }
      
    
    
}
