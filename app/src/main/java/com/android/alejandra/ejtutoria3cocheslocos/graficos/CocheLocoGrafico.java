package com.android.alejandra.ejtutoria3cocheslocos.graficos;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import com.android.alejandra.ejtutoria3cocheslocos.juego.Configuracion;

import java.util.Random;

/**
 * Created by Sandra on 23/11/2016.
 */

public class CocheLocoGrafico {
    private final int BITMAP_SIZE = Configuracion.getTamanioMaxCoche();
    public static final int MAX_VELOCIDAD = Configuracion.getMaxVelocidad();
    public static final int MAX_ANGULO = Configuracion.getMaxAngulo();
    private int mScaledBitmapWidth;
    private Bitmap mScaledBitmap;

    // posición del coche (esquina izda y centro)
    private float xPos, yPos; //posición x e y del gráfico
    //No es su centro. Es esquina izda superior
    private float cenX, cenY;   //Posición del centro del gráfico
    private int ancho, alto;     //Dimensiones de la imagen
    //velocidad
    private double incX, incY;   //Velocidad desplazamiento
    //ángulo
    private double angulo;//Ángulo y velocidad rotación
    //dirección de la marcha (en función del ángulo). Más bien es el sentido de la marcha
    private int direccionX, direccionY; //vale -1,0 o 1 (izda/dcha o arriba/abajo o sin direccion en ese eje)

    private Bitmap imagenBitmap; //imagen a dibujar asociada en el constructor sin escalar
    private View view; //vista para dibujar

    private float radio; //para calcular las colisiones

    private final Paint pincel = new Paint();


    public CocheLocoGrafico(View view, Bitmap imagen) {
        this.view = view;
        this.imagenBitmap = imagen; //imagen asociada



        // Crea un nuevo generador de aleatorios para
        // tamaño aleatorio, ángulo aleatorio, velocidad y dirección aleatoria
        Random r = new Random();

        // Creates el bitmap para esta coche y adaptado al tamaño aleatorio generado y actualiza mScaleBitmapWidth
        createScaledBitmap(r);

        //ancho y alto del gráfico
        ancho = mScaledBitmapWidth;
        alto = mScaledBitmapWidth;

        this.radio=(ancho+alto)/5;

        pincel.setAntiAlias(true);

    }


    public void setPosition(float xPulsada, float yPulsada) {
        //centro gráfico
        cenX = xPulsada;
        cenY = yPulsada;
        //actualizo esquina izda gráfico
        xPos = cenX - ancho / 2;
        yPos = cenY - alto / 2;
    }


    public double getIncX() {
        return incX;
    }

    public void setIncX(double incX) {
        this.incX = incX;
    }

    public double getIncY() {
        return incY;
    }

    public void setIncY(double incY) {
        this.incY = incY;
    }

    public double getAngulo() {
        return angulo;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }

    public int getDireccionX() {
        return direccionX;
    }

    public void setDireccionX(int direccionX) {
        this.direccionX = direccionX;
    }

    public int getDireccionY() {
        return direccionY;
    }

    public void setDireccionY(int direccionY) {
        this.direccionY = direccionY;
    }

    public float getxPos() {
        return xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public float getCenX() {
        return cenX;
    }

    public float getCenY() {
        return cenY;
    }

    private void createScaledBitmap(Random r) {

        //genera un ancho aleatorio en el rango de [1..3] * BITMAP_SIZE
        mScaledBitmapWidth = (r.nextInt(3) + 1) * BITMAP_SIZE;

        // crea el bitmap escalado usando el ancho de arriba
        mScaledBitmap = Bitmap.createScaledBitmap(imagenBitmap, mScaledBitmapWidth, mScaledBitmapWidth, false);

    }

    public void dibujaGrafico(Canvas canvas) {
        canvas.save();
        canvas.rotate((float) angulo, cenX, cenY);
        canvas.drawBitmap(mScaledBitmap, xPos, yPos, pincel);
        canvas.restore();
        view.invalidate();
    }


    public void incrementaPos(double factor) {
        cenX += incX * factor * direccionX;  //si es negativo resta y si no, suma
        cenY += incY * factor * direccionY;
        xPos = cenX - ancho / 2;
        yPos = cenY - alto / 2;

        // Si salimos de la pantalla, rebotamos
        if ((cenX - ancho / 2) < 0 || (cenX + ancho / 2) > view.getWidth()) {
            direccionX = -direccionX;
        }

        if ((cenY - ancho / 2) < 0 || (cenY + ancho / 2) > view.getHeight()) {
            direccionY = -direccionY;
        }
    }

    /**
     * Mëtodo que recibe un angulo y averigua que dirección de la marcha corresponde
     * para este coche, teniendo en cuenta que en angulo 0 el coche mira hacia la izda y el
     * ángulo 90 es mirando hacia arriba el coche
     *
     * @param angulo
     */
    public void setDireccionXFromAngulo(double angulo) {
        if ((angulo >= 0 && angulo < 90) || (angulo > 270 && angulo <= 360)) {
            //estamos en el cuadrante arriba izda
            //o cuadrante abajo izda
            direccionX = -1;
        }
        if ((angulo > 90 && angulo < 270)) {
            //estamos en la mitad derecha del eje Y
            direccionX = 1;
        }
        if (angulo == 90 || angulo == 270) {
            //estamos sobre el eje Y
            direccionX = 0;
        }

    }


    /**
     * Mëtodo que recibe un angulo y averigua que dirección de la marcha corresponde
     * para este coche, teniendo en cuenta que en angulo 0 el coche mira hacia la izda y el
     * ángulo 90 es mirando hacia arriba el coche
     *
     * @param angulo
     */
    public void setDireccionYFromAngulo(double angulo) {
        if (angulo > 0 && angulo < 180) {
            //estamos en la parte superior del eje X
            direccionY = -1; //restamos a la Y
        }
        if ((angulo > 180 && angulo < 360)) {
            //estamos en la parte inferior del eje X
            direccionY = 1;
        }
        if (angulo == 0 || angulo == 180) {
            //estamos sobre el eje X
            direccionY = 0;
        }


    }

    /**
     * Método que configura la velocidad aleatoria, el ángulo aleatorio y la dirección en función del ángulo
     */
    public void configurar() {
        Random r = new Random();
        //asigno velocidad aleatoria entre 1 y MAX_VELOCIDAD
        setIncY(r.nextInt(CocheLocoGrafico.MAX_VELOCIDAD) + 1);
        setIncX(r.nextInt(CocheLocoGrafico.MAX_VELOCIDAD) + 1);
        //asigno ángulo aleatorio entre 0 y 360
        setAngulo(r.nextInt(CocheLocoGrafico.MAX_ANGULO + 1));
        //asigno dirección de la marcha en función del ángulo
        setDireccionXFromAngulo(this.getAngulo());
        setDireccionYFromAngulo(this.getAngulo());
    }

    /**
     * Método que comprueba si dos coches de choque han chocado.
     *
     * @param coche contra el que se puede chocar
     * @return true si colisiona; false en otro caso
     */
    public boolean verificarColision(CocheLocoGrafico coche) {
        float distX=getCenX()-coche.getCenX();
        float distY=getCenY()-coche.getCenY();

        double distancia=Math.sqrt(distX*distX+distY*distY);
        if(distancia<=(radio+coche.radio))
        {

            //chocan
            return true;
        }
        return false;
    }


    public void rebota(CocheLocoGrafico coche) {

            //rebotan los 2
            direccionX = -direccionX;
            coche.setDireccionX(-direccionX);


            direccionY = -direccionY;
            coche.setDireccionY(-direccionY);


    }
}





