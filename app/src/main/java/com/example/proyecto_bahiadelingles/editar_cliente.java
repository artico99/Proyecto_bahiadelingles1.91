package com.example.proyecto_bahiadelingles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_bahiadelingles.db.DbClientes;
import com.example.proyecto_bahiadelingles.db.DbLofts;
import com.example.proyecto_bahiadelingles.model.Cliente;
import com.example.proyecto_bahiadelingles.model.Loft;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class editar_cliente extends AppCompatActivity {
    EditText edtNombreClienteVer, edtApellidoClienteVer, edtRutClienteVer, edtTelefonoClienteVer, edtCorreoClienteVer, edtNumLoftClienteVer, edtComentarioClienteVer,edtfechaentrada,edtfechasalida;
    Button btnGuardarClienteVer;
    FloatingActionButton fbEditarCliente, fbEliminarCliente;

    boolean correcto = false;

    Cliente cliente;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_cliente);
        edtfechaentrada = findViewById(R.id.edtFechaEntrada);
        edtfechasalida = findViewById(R.id.edtFechaSalida);
        edtNombreClienteVer = findViewById(R.id.edtNombreClienteVer);
        edtApellidoClienteVer = findViewById(R.id.edtApellidoClienteVer);
        edtRutClienteVer = findViewById(R.id.edtRutClienteVer);
        edtCorreoClienteVer = findViewById(R.id.edtCorreoClienteVer);
        edtTelefonoClienteVer = findViewById(R.id.edtTelefonoClienteVer);
        edtNumLoftClienteVer = findViewById(R.id.edtNumLoftClienteVer);
        edtComentarioClienteVer = findViewById(R.id.edtComentarioClienteVer);

        btnGuardarClienteVer = findViewById(R.id.btnGuardarClienteVer);

        fbEditarCliente = findViewById(R.id.fbEditarCliente);
        fbEditarCliente.setVisibility(View.INVISIBLE);
        fbEliminarCliente = findViewById(R.id.fbEliminarCliente);
        fbEliminarCliente.setVisibility(View.INVISIBLE);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbClientes dbClientes = new DbClientes(editar_cliente.this);
        cliente = dbClientes.seleccionarCliente(id);

        if (cliente != null) {
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

        btnGuardarClienteVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtNombreClienteVer.getText().toString().equals("")) {
                    correcto = dbClientes.editarCliente(id, edtNombreClienteVer.getText().toString(), edtApellidoClienteVer.getText().toString(),
                            edtRutClienteVer.getText().toString(),edtfechaentrada.getText().toString(),edtfechasalida.getText().toString(), edtTelefonoClienteVer.getText().toString(), edtCorreoClienteVer.getText().toString(),
                            edtNumLoftClienteVer.getText().toString(), edtComentarioClienteVer.getText().toString());
                    if (!correcto) {
                        Toast.makeText(editar_cliente.this, "CLIENTE MODIFICADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(editar_cliente.this, "ERROR AL MODIFICAR CLIENTE", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(editar_cliente.this, "DEBE ESCRIBIR EL NOMBRE", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}