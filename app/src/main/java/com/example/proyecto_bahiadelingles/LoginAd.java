package com.example.proyecto_bahiadelingles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_bahiadelingles.db.Dbadministracion;

public class LoginAd extends AppCompatActivity {

    EditText edtUsuario,edtContraseña;
    Button btnIngresar,btnRegistro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_login);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtContraseña = findViewById(R.id.edtContraseña);
        btnIngresar = (Button) findViewById(R.id.btnIngresarAd);
        btnRegistro = (Button) findViewById(R.id.btnRegistro);
        Dbadministracion dbadministracion = new Dbadministracion(LoginAd.this);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginAd.this, Insertar_cliente.class);
                startActivity(intent);
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String user = edtUsuario.getText().toString();
                String pass = edtContraseña.getText().toString();
                if(user.equals("") && pass.equals(""))
                {
                    Toast.makeText(LoginAd.this, "Campos vacios", Toast.LENGTH_SHORT).show();
                }
                else if (dbadministracion.login(user,pass)==0)
                {
                    Toast.makeText(LoginAd.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                }
                else if (dbadministracion.login(user,pass)==1)
                {

                    Toast.makeText(LoginAd.this,"Datos Correctos",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginAd.this, MenuPrincipalUsuario.class);
                    startActivity(intent);
                }
            }
        });

    }
}