package com.example.proyecto_bahiadelingles;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_bahiadelingles.db.DbClientes;
import com.example.proyecto_bahiadelingles.model.Cliente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Ver_cliente extends AppCompatActivity
{

    EditText edtNombreClienteVer, edtApellidoClienteVer, edtRutClienteVer, edtTelefonoClienteVer, edtCorreoClienteVer, edtNumLoftClienteVer, edtComentarioClienteVer,edtfechaentrada,edtfechasalida;
    Button btnGuardarCliente,btnGuardarRH;
    FloatingActionButton fbEditarCliente, fbEliminarCliente;

    Cliente cliente;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_cliente);

        edtfechaentrada = findViewById(R.id.edtFechaEntrada);
        edtfechasalida = findViewById(R.id.edtFechaSalida);
        edtNombreClienteVer = findViewById(R.id.edtNombreClienteVer);
        edtApellidoClienteVer= findViewById(R.id.edtApellidoClienteVer);
        edtRutClienteVer = findViewById(R.id.edtRutClienteVer);
        edtTelefonoClienteVer = findViewById(R.id.edtTelefonoClienteVer);
        edtCorreoClienteVer = findViewById(R.id.edtCorreoClienteVer);
        edtNumLoftClienteVer = findViewById(R.id.edtNumLoftClienteVer);
        edtComentarioClienteVer = findViewById(R.id.edtComentarioClienteVer);

        btnGuardarRH = findViewById(R.id.btnRegistroh);
        btnGuardarCliente = findViewById(R.id.btnGuardarClienteVer);
        fbEditarCliente = findViewById(R.id.fbEditarCliente);
        fbEliminarCliente = findViewById(R.id.fbEliminarCliente);

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

        DbClientes dbClientes = new DbClientes(Ver_cliente.this);
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
            btnGuardarCliente.setVisibility(View.INVISIBLE);

            edtNombreClienteVer.setInputType(InputType.TYPE_NULL);
            edtApellidoClienteVer.setInputType(InputType.TYPE_NULL);
            edtRutClienteVer.setInputType(InputType.TYPE_NULL);
            edtTelefonoClienteVer.setInputType(InputType.TYPE_NULL);
            edtCorreoClienteVer.setInputType(InputType.TYPE_NULL);
            edtNumLoftClienteVer.setInputType(InputType.TYPE_NULL);
            edtComentarioClienteVer.setInputType(InputType.TYPE_NULL);
        }

        fbEditarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Ver_cliente.this, editar_cliente.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fbEliminarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(Ver_cliente.this);
                builder.setMessage("¿Desea eliminar este registro?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                        if(dbClientes.eliminarCliente(id))
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
        btnGuardarRH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ver_cliente.this, guardarRegistroHistorico.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });
    }

    private void lista()
    {
        Intent intent = new Intent(this, MenuPrincipalUsuario.class);
        startActivity(intent);
    }
}
