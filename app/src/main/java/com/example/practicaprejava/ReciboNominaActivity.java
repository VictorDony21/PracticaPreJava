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

public class ReciboNominaActivity extends AppCompatActivity{

    private EditText txtNumRecibo, txtNombre, txtHorasT, txtHorasE, txtSubtotal, txtImpuesto, txtTotal;
    private Button btnCalcular, btnLimpiar, btnRegresar;
    private RadioGroup RadioGroup;
    private RadioButton radioAuxiliar, radioAlbañil, radioIngObra;
    private TextView lblUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo_nomina);

        txtNumRecibo = findViewById(R.id.txtNumRecibo);
        txtNombre = findViewById(R.id.txtNombre);
        txtHorasT = findViewById(R.id.txtHorasT);
        txtHorasE = findViewById(R.id.txtHorasE);
        txtSubtotal = findViewById(R.id.txtSubtotal);
        txtImpuesto = findViewById(R.id.txtImpuesto);
        txtTotal = findViewById(R.id.txtTotal);

        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        btnRegresar = findViewById(R.id.btnRegresar);

        RadioGroup = findViewById(R.id.radioGroup);

        radioAuxiliar = findViewById(R.id.radioAuxiliar);
        radioAlbañil = findViewById(R.id.radioAlbañil);
        radioIngObra = findViewById(R.id.radioIngObra);

        lblUsuario = findViewById(R.id.lblUsuario);





        //Boton para calcular
        btnCalcular.setOnClickListener(new View.OnClickListener() {

            int selectedId = RadioGroup.getCheckedRadioButtonId();
            @Override
            public void onClick(View v) {
                if(txtNumRecibo.getText().toString().matches("")){
                    //Falta capturar el numero de recibo
                    Toast.makeText(ReciboNominaActivity.this,
                            "Favor de llenar los campos",Toast.LENGTH_SHORT).show();
                }else if(txtNombre.getText().toString().matches("")) {
                    //Falta capturar el nombre
                    Toast.makeText(ReciboNominaActivity.this,
                            "Favor de llenar los campos",Toast.LENGTH_SHORT).show();
                }else if(txtHorasT.getText().toString().matches("")) {
                    //Falta capturar las horas trabajadas
                    Toast.makeText(ReciboNominaActivity.this,
                            "Favor de llenar los campos",Toast.LENGTH_SHORT).show();
                }else if(txtHorasE.getText().toString().matches("")) {
                    //Falta capturar las horas extras
                    Toast.makeText(ReciboNominaActivity.this,
                            "Favor de llenar los campos",Toast.LENGTH_SHORT).show();
                }else if (selectedId == -1) {
                    // Ningún RadioButton está seleccionado, muestra un mensaje de error
                    Toast.makeText(getApplicationContext(), "Por favor, selecciona una opción", Toast.LENGTH_SHORT).show();
                } else {
                    calcularNomina();
                }
            }
        });



        //Boton de limpiar
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNumRecibo.setText("");
                txtNombre.setText("");
                txtHorasT.setText("");
                txtHorasE.setText("");
                txtSubtotal.setText("");
                txtImpuesto.setText("");
                txtTotal.setText("");
                RadioGroup.clearCheck();
            }
        });


        //Boton para salir al login
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

    }

    private void calcularNomina(){

        calcularSubtotal();


    }

    //Metodo para calcular subtotal
    private void calcularSubtotal(){

        int HorasT = Integer.parseInt(txtHorasT.getText().toString());
        int HorasE = Integer.parseInt(txtHorasE.getText().toString());
        int puesto = obtenerPuestoSeleccionado();

        // Calcular el pago base según el puesto
        double pagoBase = 200.0;
        double incrementoPago = 0.0;

        if (puesto == 1) {
            incrementoPago = pagoBase * 0.2;
        } else if (puesto == 2) {
            incrementoPago = pagoBase * 0.5;
        } else if (puesto == 3) {
            incrementoPago = pagoBase;
        }

        // Calcular el subtotal
        double pagoHorasT = pagoBase + incrementoPago;
        double pagoHorasE = pagoBase * 2.0;

        double subtotal = (pagoHorasT * HorasT) + (pagoHorasE * HorasE);



    }

    // Método para obtener el puesto seleccionado del radio
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




}
