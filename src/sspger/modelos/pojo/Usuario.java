package sspger.modelos.pojo;

import java.awt.Image;



public class Usuario {

    private int idUsuario;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correoInstitucional;
    private String numeroTelefonico;
    private String nombreUsuario;
    private String password;
    private Image imagen;
    private String nombreCompleto;
    private int idTipoUsuario;
    private int codigoRespuesta;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno, String correoInstitucional, String numeroTelefonico, String nombreUsuario, String password, Image imagen, String nombreCompleto, int idTipoUsuario, int codigoRespuesta) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correoInstitucional = correoInstitucional;
        this.numeroTelefonico = numeroTelefonico;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.imagen = imagen;
        this.nombreCompleto = nombreCompleto;
        this.idTipoUsuario = idTipoUsuario;
        this.codigoRespuesta = codigoRespuesta;
    }
    
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public Image getImagen() {
        return imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    @Override
    public String toString() {
        return nombreCompleto;
    }
    
    
    
}
