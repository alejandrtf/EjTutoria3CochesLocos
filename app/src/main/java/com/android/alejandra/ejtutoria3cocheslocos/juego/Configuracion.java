package com.android.alejandra.ejtutoria3cocheslocos.juego;

/**
 * Created by Sandra on 23/11/2016.
 */

public class Configuracion {
    private static int numCoches;
    private static int tamanioMaxCoche;
    private static boolean direccionCocheAleatoria;
    private static boolean anguloCocheAleatorio;
    //apartado 2 del juego
    private static int maxAngulo; //será 360 y el ángulo será entre 0 y 360
    private static int maxVelocidad; //será 5 porque la velocidad será entre 1 y 5

    //usada para saber cuando entré a configurar
    private static boolean configurado=false;

    //CONSTRUCTOR QUE INICIALIZA A VALORES POR DEFECTO
    public Configuracion() {
        numCoches=1;
        tamanioMaxCoche=64;
        direccionCocheAleatoria=true;
        anguloCocheAleatorio=true;
        //apartado 2
        maxAngulo=360;
        maxVelocidad=5;
    }

    public static int getNumCoches() {
        return numCoches;
    }

    public static void setNumCoches(int numCoches) {
        Configuracion.numCoches = numCoches;
    }

    public static int getTamanioMaxCoche() {
        return tamanioMaxCoche;
    }

    public static void setTamanioMaxCoche(int tamanioMaxCoche) {
        Configuracion.tamanioMaxCoche = tamanioMaxCoche;
    }

    public static boolean isDireccionCocheAleatoria() {
        return direccionCocheAleatoria;
    }

    public static void setDireccionCocheAleatoria(boolean direccionCocheAleatoria) {
        Configuracion.direccionCocheAleatoria = direccionCocheAleatoria;
    }

    public static boolean isAnguloCocheAleatorio() {
        return anguloCocheAleatorio;
    }

    public static void setAnguloCocheAleatorio(boolean anguloCocheAleatorio) {
        Configuracion.anguloCocheAleatorio = anguloCocheAleatorio;
    }

    public static int getMaxAngulo() {
        return maxAngulo;
    }

    public static void setMaxAngulo(int maxAngulo) {
        Configuracion.maxAngulo = maxAngulo;
    }

    public static int getMaxVelocidad() {
        return maxVelocidad;
    }

    public static void setMaxVelocidad(int maxVelocidad) {
        Configuracion.maxVelocidad = maxVelocidad;
    }

    public static boolean isConfigurado() {
        return configurado;
    }

    public static void setConfigurado(boolean configuardo) {
        Configuracion.configurado = configuardo;
    }
}
