package com.example.proyecto_bahiadelingles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Crear_perfil_cliente extends AppCompatActivity
{

    EditText edtNombrePerfil, edtApellidoPerfil, edtNumLoftPerfil, edtComentarioPerfil;
    Button btnCrearPerfil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_crear_perfil_cliente);

        edtNombrePerfil = findViewById(R.id.edtNombreCrearPerfil);
        edtApellidoPerfil = findViewById(R.id.edtApellidoCrearPerfil);
        edtNumLoftPerfil = findViewById(R.id.edtNumLoftCrearPerfil);
        edtComentarioPerfil = findViewById(R.id.edtComentarioCrearPerfil);

        btnCrearPerfil = findViewById(R.id.btnCrearPerfil);

        btnCrearPerfil.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!edtNombrePerfil.getText().toString().equals("") && !edtApellidoPerfil.getText().toString().equals("") && !edtNumLoftPerfil.getText().toString().equals(""))
                {
                    Intent intent = new Intent(Crear_perfil_cliente.this,Vista_cliente.class);
                    startActivity(intent);

                }
                else
                    {
                        Toast.makeText(Crear_perfil_cliente.this, "Error, nombre, apellido y número de loft son obligatorios", Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }
}
