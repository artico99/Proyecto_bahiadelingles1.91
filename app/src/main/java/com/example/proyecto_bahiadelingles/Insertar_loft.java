package com.example.proyecto_bahiadelingles;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_bahiadelingles.db.DbLofts;

public class Insertar_loft extends AppCompatActivity
{
    EditText edtNumLoft,edtNombreLoft, edtComentarios;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_insertar_loft);

        edtNumLoft = findViewById(R.id.edtNumLoftInsert);
        edtNombreLoft = findViewById(R.id.edtNombreLoftInsert);
        edtComentarios = findViewById(R.id.edtComentarioLoftInsert);

        Button btnRegisL= findViewById(R.id.btnRegis);
        Button btnVolver= findViewById(R.id.btnVolver);

        //BOTON para agregar loft

        btnRegisL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DbLofts dbLofts = new DbLofts(Insertar_loft.this);
                long id = dbLofts.insertarLoft(edtNumLoft.getText().toString(),edtNombreLoft.getText().toString(), edtComentarios.getText().toString());

                if(id > 0)
                {
                    Toast.makeText(Insertar_loft.this, "Loft Guardado correctamente", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        Toast.makeText(Insertar_loft.this, "Error al guardar loft", Toast.LENGTH_SHORT).show();
                    }

            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void limpiar ()
    {
        edtNombreLoft.setText("");
        edtComentarios.setText("");
    }
}
