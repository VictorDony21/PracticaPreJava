package com.example.practicaprejava;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre;
    private TextView lblNombre;
    private Button btnEntrar;
    private Button btnSalir;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = findViewById(R.id.txtNombre);
        lblNombre = findViewById(R.id.lblNombre);
        btnEntrar = findViewById(R.id.btnEntrar);
        btnSalir = findViewById(R.id.btnSalir);



        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuario = txtNombre.getText().toString();

                if (usuario.equals(getString(R.string.user)) ) {
                    // Credenciales válidas, redireccionar a otra actividad

                    Intent intent = new Intent(MainActivity.this, ReciboNominaActivity.class);
                    intent.putExtra("user", usuario); // Agregar el nombre de usuario al intent
                    startActivity(intent);


                } else {
                    // Credenciales inválidas, mostrar mensaje de error
                    txtNombre.setError("Credenciales inválidas");
                }
            }
        });

        //Boton para cerrar la app
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Cerrar aplicación");
                builder.setMessage("¿Estás seguro de que quieres cerrar la aplicación?");

                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish(); // Cerrar la actividad actual y salir de la aplicación
                    }
                });

                builder.setNegativeButton("No", null); // No hacer nada si se selecciona "No"

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
}