package sspger.utils;



public class Constantes {
    
    //Base Datos
    public static final int OPERACION_EXITOSA = 200;
    public static final int ERROR_CONEXION = 500;
    public static final int ERROR_CONSULTA = 501;
    
    //Estado Anteproyecto
    public static final int BORRADOR = 1;
    public static final int VALIDADO = 2;
    public static final int POR_VALIDAR = 3;
    public static final int DISPONILE = 4;
    public static final int ASIGNADO = 5;
    
    //Tipo Usuario 
    public static final int ADMINISTRADOR = 1;
    public static final int PROFESOR = 2;
    public static final int ESTUDIANTE = 3;
    public static final int ESTUDIANTE_CON_ANTEPROYECTO = 4;
    public static final int ENCARGADO_CA = 5;
    public static final int DIRECTOR = 6;
}
