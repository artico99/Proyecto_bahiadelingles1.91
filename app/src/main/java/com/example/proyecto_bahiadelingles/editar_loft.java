package com.example.proyecto_bahiadelingles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_bahiadelingles.db.DbLofts;
import com.example.proyecto_bahiadelingles.model.Loft;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class editar_loft extends AppCompatActivity
{
    EditText edtNombreLoft, edtComentarioLoft;
    Button btnGuardarLoft;
    FloatingActionButton fbEditarLoft, fbEliminarLoft;

    boolean correcto = false;

    Loft loft;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_loft);

        edtNombreLoft = findViewById(R.id.edtNombreLoftVer);
        edtComentarioLoft = findViewById(R.id.edtComentarioLoftVer);
        btnGuardarLoft = findViewById(R.id.btnGuardarLoft);
        fbEditarLoft = findViewById(R.id.fbEditarLoft);
        fbEditarLoft.setVisibility(View.INVISIBLE);
        fbEliminarLoft = findViewById(R.id.fbEliminarLoft);
        fbEliminarLoft.setVisibility(View.INVISIBLE);

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

        DbLofts dbLofts = new DbLofts(editar_loft.this);
        loft = dbLofts.seleccionarLoft(id);

        if(loft != null)
        {
            edtNombreLoft.setText(loft.getNombre());
            edtComentarioLoft.setText(loft.getComentario());
        }

        btnGuardarLoft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!edtNombreLoft.getText().toString().equals(""))
                {
                    correcto = dbLofts.editarLoft(id, edtNombreLoft.getText().toString(), edtComentarioLoft.getText().toString());
                    if(!correcto)
                    {
                        Toast.makeText(editar_loft.this, "LOFT MODIFICADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                        verRegistroLoft();
                    }
                    else
                    {
                        Toast.makeText(editar_loft.this, "ERROR AL MODIFICAR LOFT", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                    {
                        Toast.makeText(editar_loft.this, "DEBE ESCRIBIR EL NOMBRE", Toast.LENGTH_SHORT).show();
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