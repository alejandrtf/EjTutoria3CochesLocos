package com.android.alejandra.ejtutoria3cocheslocos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.android.alejandra.ejtutoria3cocheslocos.juego.Configuracion;

public class ConfigurarActivity extends AppCompatActivity {
    EditText etNumCoches,etTamanioCoche;
    CheckBox cbDirAleatoria, cbAnguloAleatorio;
    Button btGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar);

        //obtengo referencias objetos xml
        init();

        //registro oyente boton guardar configuración
        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Configuracion(); //para asignar otros valores por defecto y no cambiables, como MAX VELOCIDAD,...
                //anoto todas las configuraciones elegidas en la clase Configuración
                //Dichas configuraciones serán comunes para toda al app pues contiene miembros estáticos
                Configuracion.setNumCoches(Integer.parseInt(etNumCoches.getText().toString()));
                Configuracion.setTamanioMaxCoche(Integer.parseInt(etTamanioCoche.getText().toString()));
                Configuracion.setAnguloCocheAleatorio(cbAnguloAleatorio.isChecked());
                Configuracion.setDireccionCocheAleatoria(cbDirAleatoria.isChecked());
                Configuracion.setConfigurado(true);
                //cierro la activity
                finish();
            }
        });

    }

    /**Método que obtiene las referencias de los objetos y los inicializa
     *
     */
    private void init() {
        etNumCoches=(EditText)findViewById(R.id.etNumeroCoches);
        etTamanioCoche=(EditText)findViewById(R.id.etMaxTamanioCoche);
        cbAnguloAleatorio=(CheckBox)findViewById(R.id.cbAnguloAleatorio);
        cbDirAleatoria=(CheckBox)findViewById(R.id.cbDireccionAleatoria);
        btGuardar=(Button) findViewById(R.id.btGuardarConfiguracion);
        if(Configuracion.isConfigurado()){
            etNumCoches.setText(String.valueOf(Configuracion.getNumCoches()));
            etTamanioCoche.setText(String.valueOf(Configuracion.getTamanioMaxCoche()));
            cbAnguloAleatorio.setChecked(Configuracion.isAnguloCocheAleatorio());
            cbDirAleatoria.setChecked(Configuracion.isDireccionCocheAleatoria());
        }

    }
}
