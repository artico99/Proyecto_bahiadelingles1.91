package com.example.proyecto_bahiadelingles;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_bahiadelingles.db.DbClientes;

import java.util.Calendar;

public class Insertar_cliente extends AppCompatActivity {
    EditText edtNombre, edtApellido, edtRut, edtTelefono, edtCorreo, edtNumLoft, edtComentario;
    EditText tv,tv1;
    ListView listViewCliente;
    String i ="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_insertar_cliente);
        tv = findViewById(R.id.edt1);
        tv1 = findViewById(R.id.edt2);
        edtNombre = findViewById(R.id.edtNombreClienteInsertar);
        edtApellido = findViewById(R.id.edtApellidoClienteInsertar);
        edtRut = findViewById(R.id.edtRutClienteInsertar);
        edtTelefono = findViewById(R.id.edtTelefonoClienteInsertar);
        edtCorreo = findViewById(R.id.edtCorreoClienteInsertar);
        edtNumLoft = findViewById(R.id.edtNumeroClienteInsertar);
        edtComentario = findViewById(R.id.edtComentarioClienteInsertar);
        Button btnRegistrarCliente = findViewById(R.id.btnIngresarCliente);
        Button btnVolver = findViewById(R.id.btnVolver);

        //BOTON para agregar cliente

        btnRegistrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbClientes dbClientes = new DbClientes(Insertar_cliente.this);
                long id = dbClientes.insertarCliente(edtNombre.getText().toString(),
                        edtApellido.getText().toString(),
                        edtRut.getText().toString(),
                        tv.getText().toString(),
                        tv1.getText().toString(),
                        edtTelefono.getText().toString(),
                        edtCorreo.getText().toString(),
                        edtNumLoft.getText().toString(),
                        edtComentario.getText().toString()

                );

                if (id > 0) {
                    Toast.makeText(Insertar_cliente.this, "Cliente Ingresado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Insertar_cliente.this, "Error al guardar cliente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void limpiar() {
        edtNombre.setText("");
        edtApellido.setText("");
        edtRut.setText("");
        edtTelefono.setText("");
        edtCorreo.setText("");
        edtNumLoft.setText("");
        edtComentario.setText("");
    }

    public void cale1(View view) {
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);
        int mes= cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(Insertar_cliente.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                String fecha = dayOfMonth + "/" + month + "/" + year;
                tv.setText(fecha);
            }
        }, dia,mes,anio);
        dpd.show();

    }

    public void cale(View view) {

            Calendar cal = Calendar.getInstance();
            int anio = cal.get(Calendar.YEAR);
            int mes= cal.get(Calendar.MONTH);
            int dia = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(Insertar_cliente.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    String fecha = dayOfMonth + "/" + month + "/" + year;
                    tv1.setText(fecha);
                }
            }, dia,mes,anio);
            dpd.show();

        }



    }


