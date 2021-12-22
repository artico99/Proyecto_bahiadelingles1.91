package com.example.proyecto_bahiadelingles;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_bahiadelingles.db.Dbadministracion;

public class Insertar_Administracion extends AppCompatActivity {


    EditText txtUser, txtpass;
    boolean sw = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_registrar_ad);


        Button btnRegistrar = findViewById(R.id.btnRegistrar);




            btnRegistrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    txtUser = findViewById(R.id.edtUser);
                    txtpass = findViewById(R.id.edtPass);
                    String user = txtUser.getText().toString();
                    String pass = txtpass.getText().toString();
                    if (user.equals("") || pass.equals("")) {
                        Toast.makeText(Insertar_Administracion.this, "Debe rellenar ambos campos", Toast.LENGTH_SHORT).show();
                    } else {

                        Dbadministracion dbadministracion = new Dbadministracion(Insertar_Administracion.this);
                        long id = dbadministracion.insertarAdministracion(txtUser.getText().toString(), txtpass.getText().toString());

                        if (id > 0) {
                            Toast.makeText(Insertar_Administracion.this, "Usuario agregado", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Insertar_Administracion.this, "Error al guardar registro", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

            });





    }
}