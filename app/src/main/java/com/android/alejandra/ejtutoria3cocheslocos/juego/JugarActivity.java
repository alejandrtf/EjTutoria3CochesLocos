package com.android.alejandra.ejtutoria3cocheslocos.juego;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class JugarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!Configuracion.isConfigurado()) {
            //creo una configuración inicial, por si el usuario no entra en el botón Configuración
            Configuracion cInicial = new Configuracion();
        }

        //PONGO A PANTALLA COMPLETA
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //asigno pantalla
        setContentView(new VistaJuegoCochesLocos(this));


    }
}
