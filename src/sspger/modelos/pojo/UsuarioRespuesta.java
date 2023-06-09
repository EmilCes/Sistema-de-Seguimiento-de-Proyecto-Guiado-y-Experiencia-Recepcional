package sspger.modelos.pojo;

import java.util.ArrayList;


public class UsuarioRespuesta {
    
    private int codigoRespuesta;
    private ArrayList<Usuario> usuarios;

    public UsuarioRespuesta() {
    }

    public UsuarioRespuesta(int codigoRespuesta, ArrayList<Usuario> usuarios) {
        this.codigoRespuesta = codigoRespuesta;
        this.usuarios = usuarios;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    
    
}
