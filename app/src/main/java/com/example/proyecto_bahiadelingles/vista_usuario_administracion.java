package com.example.proyecto_bahiadelingles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class vista_usuario_administracion extends AppCompatActivity {
    Button btnAdd,btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_ad);

        btnAdd = (Button) findViewById(R.id.btn_agregar_ad);
        btnShow = (Button) findViewById(R.id.btn_mostrar_ad);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(vista_usuario_administracion.this, Insertar_Administracion.class);
                startActivity(intent);
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vista_usuario_administracion.this, Listar_Administracion.class);
                startActivity(intent);
            }
        });

    }
}