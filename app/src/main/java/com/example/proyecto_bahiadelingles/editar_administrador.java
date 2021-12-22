package com.example.proyecto_bahiadelingles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_bahiadelingles.db.DbLofts;
import com.example.proyecto_bahiadelingles.db.Dbadministracion;
import com.example.proyecto_bahiadelingles.model.Administracion;
import com.example.proyecto_bahiadelingles.model.Loft;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class editar_administrador extends AppCompatActivity
{
    EditText edtUsuarioAdVer, edtContraseñaAdVer;
    Button btnGuardarAd;
    FloatingActionButton fbEditarAd, fbEliminarAd;

    boolean correcto = false;

    Administracion administracion;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_ad);

        edtUsuarioAdVer = findViewById(R.id.edtUsuarioAdVer);
        edtContraseñaAdVer = findViewById(R.id.edtContraseñaAdVer);
        btnGuardarAd = findViewById(R.id.btnGuardarAd);
        fbEditarAd = findViewById(R.id.fbEditarAd);
        fbEditarAd.setVisibility(View.INVISIBLE);
        fbEliminarAd = findViewById(R.id.fbEliminarAd);
        fbEliminarAd.setVisibility(View.INVISIBLE);

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

        Dbadministracion dbadministracion = new Dbadministracion(editar_administrador.this);
        administracion = dbadministracion.seleccionarAdministracion(id);

        if(administracion != null)
        {
            edtUsuarioAdVer.setText(administracion.getUsuario());
            edtContraseñaAdVer.setText(administracion.getContraseña());
        }

        btnGuardarAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(edtUsuarioAdVer.getText().toString().equals("")||edtContraseñaAdVer.getText().toString().equals(""))
                {

                    Toast.makeText(editar_administrador.this, "Debe rellenar ambos campos", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    correcto = dbadministracion.editarAdministracion(id, edtUsuarioAdVer.getText().toString(), edtContraseñaAdVer.getText().toString());
                    if(!correcto)
                    {
                        Toast.makeText(editar_administrador.this, "administrador editado", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(editar_administrador.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    private void verRegistroLoft()
    {
        Intent intent = new Intent(this, Ver_loft.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }

}
