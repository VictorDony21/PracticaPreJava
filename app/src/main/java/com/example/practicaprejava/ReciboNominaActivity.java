package com.example.practicaprejava;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ReciboNominaActivity extends AppCompatActivity {

    private EditText txtNombre, txtHorasT, txtHorasE;
    private Button btnCalcular, btnLimpiar, btnRegresar;
    private RadioGroup radioGroup;
    private RadioButton radioAuxiliar, radioAlbañil, radioIngObra;
    private TextView txtNumRecibo, lblUsuario, txtSubtotal, txtImpuesto, txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo_nomina);

        // Asignar las referencias a los elementos de la interfaz
        txtNumRecibo = findViewById(R.id.txtNumRecibo);
        txtNombre = findViewById(R.id.txtNombre);
        txtHorasT = findViewById(R.id.txtHorasT);
        txtHorasE = findViewById(R.id.txtHorasE);

        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        btnRegresar = findViewById(R.id.btnRegresar);

        radioGroup = findViewById(R.id.radioGroup);
        radioAuxiliar = findViewById(R.id.radioAuxiliar);
        radioAlbañil = findViewById(R.id.radioAlbañil);
        radioIngObra = findViewById(R.id.radioIngObra);

        lblUsuario = findViewById(R.id.lblUsuario);
        txtSubtotal = findViewById(R.id.txtSubtotal);
        txtImpuesto = findViewById(R.id.txtImpuesto);
        txtTotal = findViewById(R.id.txtTotal);

        // Obtener el usuario de la actividad anterior
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String usuario = bundle.getString("user");
            lblUsuario.setText("Bienvenido " + usuario);
        }



        // Botón para calcular
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    numRandom();
                    calcularNomina();
                } else {
                    Toast.makeText(ReciboNominaActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Botón de limpiar
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCampos();
            }
        });

        // Botón para salir al login
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarSalida();
            }
        });
    }

    private boolean validarCampos() {
        boolean camposCompletos = true;
        if (txtNombre.getText().toString().isEmpty()) {
            txtNombre.setError("Campo obligatorio");
            camposCompletos = false;
        }
        if (txtHorasT.getText().toString().isEmpty()) {
            txtHorasT.setError("Campo obligatorio");
            camposCompletos = false;
        }
        if (txtHorasE.getText().toString().isEmpty()) {
            txtHorasE.setError("Campo obligatorio");
            camposCompletos = false;
        }
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Por favor, selecciona una opción", Toast.LENGTH_SHORT).show();
            camposCompletos = false;
        }
        return camposCompletos;
    }

    private void calcularNomina() {
        int horasT = Integer.parseInt(txtHorasT.getText().toString());
        int horasE = Integer.parseInt(txtHorasE.getText().toString());
        int puesto = obtenerPuestoSeleccionado();

        double subtotal = ReciboNomina.calcularSubtotal(horasT, horasE, puesto);
        double impuesto = ReciboNomina.calcularImpuesto(subtotal);
        double total = ReciboNomina.calcularTotal(subtotal, impuesto);

        txtSubtotal.setText(String.valueOf(subtotal));
        txtImpuesto.setText(String.valueOf(impuesto));
        txtTotal.setText(String.valueOf(total));
    }

    public void numRandom() {
        // Crear una instancia de la clase Random
        Random random = new Random();

        // Generar un número aleatorio entre 1 y 99999 (5 dígitos)
        int numeroAleatorio = random.nextInt(99999) + 1;

        txtNumRecibo.setText("" + numeroAleatorio);
    }

    private int obtenerPuestoSeleccionado() {
        int puestoSeleccionado = 0;
        if (radioAuxiliar.isChecked()) {
            puestoSeleccionado = 1;
        } else if (radioAlbañil.isChecked()) {
            puestoSeleccionado = 2;
        } else if (radioIngObra.isChecked()) {
            puestoSeleccionado = 3;
        }
        return puestoSeleccionado;
    }

    private void limpiarCampos() {
        txtNumRecibo.setText("");
        txtNombre.setText("");
        txtHorasT.setText("");
        txtHorasE.setText("");
        txtSubtotal.setText("");
        txtImpuesto.setText("");
        txtTotal.setText("");
        radioGroup.clearCheck();
    }

    private void confirmarSalida() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ReciboNominaActivity.this);
        builder.setTitle("Regresar");
        builder.setMessage("¿Estás seguro de que quieres regresar?");

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
}

