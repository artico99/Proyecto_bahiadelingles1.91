package com.example.proyecto_bahiadelingles;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_bahiadelingles.db.Dbadministracion;
import com.example.proyecto_bahiadelingles.model.Administracion;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ver_Administracion extends AppCompatActivity {

    EditText edtUsuariover, edtContraseñaVer;
    Button btnGuardarAd;
    FloatingActionButton fbEditarAd, fbEliminarAd;
    Administracion administracion;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_ad);

        edtUsuariover = findViewById(R.id.edtUsuarioAdVer);
        edtContraseñaVer = findViewById(R.id.edtContraseñaAdVer);

        btnGuardarAd = findViewById(R.id.btnGuardarAd);
        fbEditarAd = findViewById(R.id.fbEditarAd);
        fbEliminarAd = findViewById(R.id.fbEliminarAd);

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

        Dbadministracion dbadministracion = new Dbadministracion(ver_Administracion.this);
        administracion = dbadministracion.seleccionarAdministracion(id);

        if (administracion != null)
        {
            edtUsuariover.setText(administracion.getUsuario());
            edtContraseñaVer.setText(administracion.getContraseña());

            btnGuardarAd.setVisibility(View.INVISIBLE);

            edtUsuariover.setInputType(InputType.TYPE_NULL);
            edtContraseñaVer.setInputType(InputType.TYPE_NULL);

        }
        fbEditarAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ver_Administracion.this, editar_administrador.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fbEliminarAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ver_Administracion.this);
                builder.setMessage("¿Desea eliminar este registro?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which)
                    {
                        if(dbadministracion.eliminarAdministracion(id))
                        {
                            lista();
                            Toast.makeText(ver_Administracion.this, "bien", Toast.LENGTH_SHORT).show();
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
