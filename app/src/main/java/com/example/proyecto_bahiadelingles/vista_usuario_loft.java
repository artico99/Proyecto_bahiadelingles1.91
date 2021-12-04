package com.example.proyecto_bahiadelingles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class vista_usuario_loft extends AppCompatActivity
{

    Button btnAgregarLoft,btnMostrarLofts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_usuario_loft);

        btnAgregarLoft = (Button)findViewById(R.id.btn_agregar_loft);
        btnMostrarLofts = (Button)findViewById(R.id.btn_mostrar_loft);

        btnAgregarLoft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent =  new Intent(vista_usuario_loft.this, Insertar_loft.class);
                startActivity(intent);
            }
        });

        btnMostrarLofts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent =  new Intent(vista_usuario_loft.this, Listar_loft.class);
                startActivity(intent);
            }
        });

    }
}
