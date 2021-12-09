package com.example.proyecto_bahiadelingles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipalUsuario extends AppCompatActivity
{

    Button btnVistaLoft, btnVistaClientes, btnVistaAdministracion,btnVistaCrearPerfil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_usuario_principal);

        btnVistaLoft = (Button)findViewById(R.id.btnVistaLoft);
        btnVistaClientes = (Button)findViewById(R.id.btnVistaClientes);
        btnVistaAdministracion = (Button)findViewById(R.id.btnVistaAdministrador);
        btnVistaCrearPerfil = (Button)findViewById(R.id.btnVistaCrearPerfil);

        btnVistaLoft.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent =  new Intent(MenuPrincipalUsuario.this, vista_usuario_loft.class);
                startActivity(intent);

            }
        });

        btnVistaClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MenuPrincipalUsuario.this, Vista_usuario_cliente.class);
                startActivity(intent);
            }
        });

        btnVistaAdministracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalUsuario.this, vista_usuario_administracion.class);
                startActivity(intent);
            }
        });
        btnVistaCrearPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalUsuario.this, Crear_perfil_cliente.class);
                startActivity(intent);
            }
        });
    }
}
