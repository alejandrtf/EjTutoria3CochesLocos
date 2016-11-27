package com.android.alejandra.ejtutoria3cocheslocos.juego;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.android.alejandra.ejtutoria3cocheslocos.R;
import com.android.alejandra.ejtutoria3cocheslocos.graficos.CocheLocoGrafico;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * TODO: document your custom view class.
 */
public class VistaJuegoCochesLocos extends View {
    // //// COCHES LOCOS //////
    private List<CocheLocoGrafico> cochesJuego;  //array de coches
    // CONTEXTO
    private Context context;

    // //// THREAD Y TIEMPO //////
    // Thread encargado de procesar el juego
    private ThreadJuego thread;
    // Cada cuanto queremos procesar cambios (ms)
    private static int PERIODO_PROCESO = 50;
    // Cuando se realizó el último proceso
    private long ultimoProceso = 0;
    //imagen de todos los coches
    Bitmap imagenCoche;


    public VistaJuegoCochesLocos(Context context) {
        super(context);
        this.setFocusable(true);
        this.context = context;
        //instancio la lista de coches
        this.cochesJuego = new ArrayList<>();

        // ángulo aleatorio, velocidad y dirección aleatoria
        Random r = new Random();


        //obtengo la imagen del coche de los recursos
        imagenCoche = BitmapFactory.decodeResource(context.getResources(), R.drawable.coche_choque);
        //creo el coche
        CocheLocoGrafico cocheGrafico = new CocheLocoGrafico(this, imagenCoche);
        cocheGrafico.configurar();


        //añado el coche a la lista de coches
        cochesJuego.add(cocheGrafico);

        //creo el hilo del juego
        thread = new ThreadJuego();


    }


    @Override
    protected void onSizeChanged(int ancho, int alto,
                                 int ancho_anter, int alto_anter) {

        //asigno la posición al primer coche en el centro de la pantalla
        cochesJuego.get(0).setPosition(ancho / 2, alto / 2);

        //arranco el hilo
        ultimoProceso = System.currentTimeMillis();
        thread.start();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //dibujo el fondo
        this.setBackground(ContextCompat.getDrawable(context, R.drawable.fondo_carretera));
        //dibujo los coches
        for (CocheLocoGrafico coche : cochesJuego) {
            coche.dibujaGrafico(canvas);
        }

    }


    protected void actualizaFisica() {
        long ahora = System.currentTimeMillis();
        // No hagas nada si el período de proceso no se ha cumplido.
        if (ultimoProceso + PERIODO_PROCESO > ahora) {
            return;
        }
        // Para una ejecución en tiempo real calculamos retardo
        double retardo = (ahora - ultimoProceso) / PERIODO_PROCESO;
        ultimoProceso = ahora; // Para la próxima vez
        // Actualizamos posición de los coches
        for (int i = 0; i < cochesJuego.size(); i++) {
            cochesJuego.get(i).incrementaPos(retardo);
            for (int j = i + 1; j < cochesJuego.size(); j++) {
                if (cochesJuego.get(i).verificarColision(cochesJuego.get(j))) {
                    cochesJuego.get(i).rebota(cochesJuego.get(j));

                }


            }
        }
    }

//////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Clase que representa un hilo que ejecutará los cálculos del movimiento
     * del juego
     *
     * @author sandra
     */
    class ThreadJuego extends Thread {
        private boolean pausa, corriendo;

        public synchronized void pausar() {
            pausa = true;
        }

        public synchronized void reanudar() {

            pausa = false;
            notify();
        }

        public void detener() {

            corriendo = false;

            if (pausa)
                reanudar();

        }

        @Override
        public void run() {

            corriendo = true;

            while (corriendo) {

                actualizaFisica();

                synchronized (this) {

                    while (pausa)
                        try {

                            wait();

                        } catch (Exception e) {

                        }
                }

            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (Configuracion.getNumCoches() > cochesJuego.size()) {
                    CocheLocoGrafico coche = new CocheLocoGrafico(this, imagenCoche);
                    //asigno posición
                    coche.setPosition(x, y);
                    //configuro velocidad, angulo y dirección
                    coche.configurar();
                    //añado el coche a la lista de coches
                    cochesJuego.add(coche);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}


