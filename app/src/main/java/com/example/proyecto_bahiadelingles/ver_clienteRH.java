package com.example.proyecto_bahiadelingles;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.example.proyecto_bahiadelingles.db.DbClientes;
import com.example.proyecto_bahiadelingles.model.Cliente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ver_clienteRH extends AppCompatActivity {
    EditText edtNombreClienteVer, edtApellidoClienteVer, edtRutClienteVer, edtTelefonoClienteVer, edtCorreoClienteVer, edtNumLoftClienteVer, edtComentarioClienteVer,edtfechaentrada,edtfechasalida,edtComportamiento;

    Cliente cliente;
    int id = 0;
    FloatingActionButton fbEliminarClienteRH;
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
        fbEliminarClienteRH = findViewById(R.id.fbEliminarClienteHR);
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

        fbEliminarClienteRH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ver_clienteRH.this);

                builder.setMessage("¿Desea eliminar este registro?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                        if(dbClientes.eliminarClienteRH(id))
                        {
                            lista();
                        }

                    }
                })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                            }
                        }).show();
            }
        });
    }
    private void lista()
    {
        Intent intent = new Intent(this, MenuPrincipalUsuario.class);
        startActivity(intent);
    }


}