package com.pruebatecnica.pruebahightechsoftware;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class DatosPersonaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_persona);

    }

    public void GuardarDatos(View view) {
        Intent miIntent = new Intent(getBaseContext(),MainActivity.class);
        startActivity(miIntent);
    }
}
