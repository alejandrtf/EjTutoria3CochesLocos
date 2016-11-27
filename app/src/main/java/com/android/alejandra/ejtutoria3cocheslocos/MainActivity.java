package com.android.alejandra.ejtutoria3cocheslocos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.alejandra.ejtutoria3cocheslocos.juego.JugarActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * Método llamado al pulsar el botón ACERCA DE...
     *
     * @param v vista pulsada
     */
    public void lanzarAcercaDe(View v) {
        Toast.makeText(this, "AUTORA: Mª Alejandra Tomás", Toast.LENGTH_SHORT).show();

    }


    /**
     * Método llamado al pulsar el botón CONFIGURAR
     *
     * @param v vista pulsada
     */
    public void lanzarConfigurar(View v) {
        Intent iConfigurar = new Intent(this, ConfigurarActivity.class);
        startActivity(iConfigurar);
    }


    /**
     * Método llamado al pulsar el botón JUGAR
     *
     * @param v vista pulsada
     */
    public void lanzarJugar(View v) {
        Intent iJugar = new Intent(this, JugarActivity.class);
        startActivity(iJugar);

    }
}
