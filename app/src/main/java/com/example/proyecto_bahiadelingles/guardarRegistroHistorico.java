package com.example.proyecto_bahiadelingles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyecto_bahiadelingles.db.DbClientes;
import com.example.proyecto_bahiadelingles.model.Cliente;

import java.util.ArrayList;

public class guardarRegistroHistorico extends AppCompatActivity {

    Spinner spinner1;
    Cliente cliente;
    int id = 0;
    EditText edtNombreClienteVer, edtApellidoClienteVer, edtRutClienteVer, edtTelefonoClienteVer, edtCorreoClienteVer, edtNumLoftClienteVer, edtComentarioClienteVer,edtfechaentrada,edtfechasalida;
    Button btnguardar;
    String comportamiento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar_registro_historico);

        edtfechaentrada = findViewById(R.id.edtFechaEntradaRH);
        edtfechasalida = findViewById(R.id.edtFechaSalidaRH);
        edtNombreClienteVer = findViewById(R.id.edtNombreClienteVerRH);
        edtApellidoClienteVer= findViewById(R.id.edtApellidoClienteVerRH);
        edtRutClienteVer = findViewById(R.id.edtRutClienteVerRH);
        edtTelefonoClienteVer = findViewById(R.id.edtTelefonoClienteVerRH);
        edtCorreoClienteVer = findViewById(R.id.edtCorreoClienteVerRH);
        edtNumLoftClienteVer = findViewById(R.id.edtNumLoftClienteVerRH);
        edtComentarioClienteVer = findViewById(R.id.edtComentarioClienteVerRH);
        spinner1 = findViewById(R.id.spinner);
        btnguardar = findViewById(R.id.btnGuardarClienteVerRH);
        String [] opciones = {"","Buen comportamiento","Comportamiento regular","Mal comportamiento"};

        ArrayAdapter <String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,opciones);
        spinner1.setAdapter(adapter);

        String seleccion = spinner1.getSelectedItem().toString();




        if(savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();
            if(extras == null)
            {
                id = Integer.parseInt(null);
            }
            else
            {
                id = extras.getInt("ID");
            }
        }
        else
        {
            id = (int)savedInstanceState.getSerializable("ID");
        }

        DbClientes dbClientes = new DbClientes(guardarRegistroHistorico.this);
        cliente = dbClientes.seleccionarCliente(id);

        if(cliente != null)
        {
            edtNombreClienteVer.setText(cliente.getNombre());
            edtApellidoClienteVer.setText(cliente.getApellido());
            edtRutClienteVer.setText(cliente.getRut());
            edtTelefonoClienteVer.setText(cliente.getTelefono());
            edtCorreoClienteVer.setText(cliente.getCorreo());
            edtNumLoftClienteVer.setText(cliente.getNumeroLoft());
            edtComentarioClienteVer.setText(cliente.getComentario());
            edtfechaentrada.setText(cliente.getFechaEntrada());
            edtfechasalida.setText(cliente.getFechaSalida());
        }
        if(seleccion.equals(""))
        {

        }
        else
        {
            if(seleccion.equals("Buen comportamiento"))
            {
                comportamiento = "Buen comportamiento";
            }
            else
            {
                if(seleccion.equals("Comportamiento regular"))
                {
                    comportamiento = "Comportamiento regular";
                }
                else
                {
                    if(seleccion.equals("Mal comportamiento"))
                    {
                        comportamiento = "Mal comportamiento";
                    }
                }
            }
        }
        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






                    DbClientes dbClientes = new DbClientes(guardarRegistroHistorico.this);
                    long id = dbClientes.insertarClienteRH(edtNombreClienteVer.getText().toString(),
                            edtApellidoClienteVer.getText().toString(),
                            edtRutClienteVer.getText().toString(),
                            edtfechaentrada.getText().toString(),
                            edtfechasalida.getText().toString(),
                            edtTelefonoClienteVer.getText().toString(),
                            edtCorreoClienteVer.getText().toString(),
                            edtNumLoftClienteVer.getText().toString(),comportamiento
                            ,
                            edtComentarioClienteVer.getText().toString()
                    );


                if (id > 0) {
                    Toast.makeText(guardarRegistroHistorico.this, "Cliente Ingresado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(guardarRegistroHistorico.this, "Error al guardar cliente", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}