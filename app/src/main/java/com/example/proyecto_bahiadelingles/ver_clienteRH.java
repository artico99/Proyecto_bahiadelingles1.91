package com.example.proyecto_bahiadelingles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.example.proyecto_bahiadelingles.db.DbClientes;
import com.example.proyecto_bahiadelingles.model.Cliente;

public class ver_clienteRH extends AppCompatActivity {
    EditText edtNombreClienteVer, edtApellidoClienteVer, edtRutClienteVer, edtTelefonoClienteVer, edtCorreoClienteVer, edtNumLoftClienteVer, edtComentarioClienteVer,edtfechaentrada,edtfechasalida,edtComportamiento;

    Cliente cliente;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_cliente_rh);

        edtfechaentrada = findViewById(R.id.edtFechaEntradaRH);
        edtfechasalida = findViewById(R.id.edtFechaSalidaRH);
        edtNombreClienteVer = findViewById(R.id.edtNombreClienteVerRH);
        edtApellidoClienteVer= findViewById(R.id.edtApellidoClienteVerRH);
        edtRutClienteVer = findViewById(R.id.edtRutClienteVerRH);
        edtTelefonoClienteVer = findViewById(R.id.edtTelefonoClienteVerRH);
        edtCorreoClienteVer = findViewById(R.id.edtCorreoClienteVerRH);
        edtNumLoftClienteVer = findViewById(R.id.edtNumLoftClienteVerRH);
        edtComentarioClienteVer = findViewById(R.id.edtComentarioClienteVerRH);
        edtComportamiento = findViewById(R.id.edtComportamientoRH);

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

        DbClientes dbClientes = new DbClientes(ver_clienteRH.this);
        cliente = dbClientes.seleccionarClienteRH(id);
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

            edtComportamiento.setText(cliente.getComportamiento());
            edtComentarioClienteVer.setText(cliente.getComentario());
        }

    }



}