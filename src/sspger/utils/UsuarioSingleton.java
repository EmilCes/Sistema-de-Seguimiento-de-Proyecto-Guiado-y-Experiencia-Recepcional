package sspger.utils;

import sspger.modelos.pojo.Usuario;

public class UsuarioSingleton {

    private static UsuarioSingleton instancia;
    
    private Usuario usuario;
    
    private UsuarioSingleton(){
    }
    
    public static UsuarioSingleton getInstancia(){
        if(instancia == null){
            instancia = new UsuarioSingleton();
        }
        return instancia;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
}


