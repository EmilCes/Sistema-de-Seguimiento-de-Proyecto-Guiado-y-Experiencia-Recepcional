
package sspger.modelos.pojo;
<<<<<<< HEAD
import java.io.File;
import java.time.LocalDate;


public class Actividad {
    private String titulo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFIn;
    private File archivo;
    private int idAnteproyecto;
    private int codigoRespuesta;
    
    public Actividad() {
    }

    public Actividad(String titulo, String descripcion, LocalDate fechaInicio, LocalDate fechaFIn, File archivo, int idAnteproyecto) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFIn = fechaFIn;
        this.archivo = archivo;
        this.idAnteproyecto = idAnteproyecto;
    }

=======


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

>>>>>>> main
    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

<<<<<<< HEAD
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFIn() {
        return fechaFIn;
    }

    public File getArchivo() {
        return archivo;
=======
    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
>>>>>>> main
    }

    public int getIdAnteproyecto() {
        return idAnteproyecto;
    }

<<<<<<< HEAD
=======
    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

>>>>>>> main
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

<<<<<<< HEAD
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFIn(LocalDate fechaFIn) {
        this.fechaFIn = fechaFIn;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
=======
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
>>>>>>> main
    }

    public void setIdAnteproyecto(int idAnteproyecto) {
        this.idAnteproyecto = idAnteproyecto;
    }
<<<<<<< HEAD

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }
    
    
    
    
=======
       
>>>>>>> main
}
