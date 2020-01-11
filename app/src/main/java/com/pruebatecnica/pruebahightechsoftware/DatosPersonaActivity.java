package com.pruebatecnica.pruebahightechsoftware;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

public class DatosPersonaActivity extends AppCompatActivity {

    EditText NumeroDocumento, PrimerNombre, SegundoNombre, PrimerApellido, SegundoApellido, Celular, Direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_persona);

        NumeroDocumento = findViewById(R.id.txtNumeroDocumento);
        PrimerNombre = findViewById(R.id.txtPrimerNombre);
        SegundoNombre = findViewById(R.id.txtSegundoNombre);
        PrimerApellido = findViewById(R.id.txtPrimerApellido);
        SegundoApellido = findViewById(R.id.txtSegundoApellido);
        Celular = findViewById(R.id.txtCelular);
        Direccion = findViewById(R.id.txtDireccion);

        Consumo api = new Consumo();

        api.newData();

    }

    public void GuardarDatos(View view) {

        String Ndocumento = NumeroDocumento.getText().toString();
        String Pnombre = PrimerNombre.getText().toString();
        String Snombre = SegundoNombre.getText().toString();
        String Papellido = PrimerApellido.getText().toString();
        String Sapellido = SegundoApellido.getText().toString();
        String celular = Celular.getText().toString();
        String direccion = Direccion.getText().toString();

        if (Ndocumento.equals("")){
            NumeroDocumento.setError("Campo requerido");
            return;
        }
        if (Pnombre.equals("")){
            PrimerNombre.setError("Campo requerido");
            return;
        }
        if (Papellido.equals("")){
            PrimerApellido.setError("Campo requerido");
            return;
        }
        if (celular.equals("")){
            Celular.setError("Campo requerido");
            return;
        }
        if (direccion.equals("")){
            Direccion.setError("Campo requerido");
            return;
        }

        Toast.makeText(this,"Datos registrados",Toast.LENGTH_LONG).show();

        NumeroDocumento.setText("");
        PrimerNombre.setText("");
        SegundoNombre.setText("");
        PrimerApellido.setText("");
        SegundoApellido.setText("");
        Celular.setText("");
        Direccion.setText("");

        Intent miIntent = new Intent(getBaseContext(),MainActivity.class);
        startActivity(miIntent);

    }
}
