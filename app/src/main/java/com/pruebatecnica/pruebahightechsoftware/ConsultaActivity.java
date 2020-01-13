package com.pruebatecnica.pruebahightechsoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class ConsultaActivity extends AppCompatActivity {

    ListView listV_personas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        listV_personas = findViewById(R.id.lv_datosPersonas);

        Consumo api = new Consumo();

        api.getData();

    }

    public void Volver(View view) {
        Intent miIntent = new Intent(getBaseContext(),MainActivity.class);
        startActivity(miIntent);
    }
}
