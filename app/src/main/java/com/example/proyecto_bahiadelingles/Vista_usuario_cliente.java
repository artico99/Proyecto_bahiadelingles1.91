package com.example.proyecto_bahiadelingles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Vista_usuario_cliente extends AppCompatActivity
{

    Button btnAgregarCliente,btnMostrarCliente;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_usuario_clientes);

        btnAgregarCliente = (Button)findViewById(R.id.btn_agregar_cliente);
        btnMostrarCliente = (Button)findViewById(R.id.btn_mostrar_clientee);

        btnAgregarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent =  new Intent(Vista_usuario_cliente.this, Insertar_cliente.class);
                startActivity(intent);
            }
        });

        btnMostrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent =  new Intent(Vista_usuario_cliente.this, Listar_cliente.class);
                startActivity(intent);
            }
        });

    }
}
