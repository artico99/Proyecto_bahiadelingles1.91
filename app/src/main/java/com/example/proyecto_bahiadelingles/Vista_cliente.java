package com.example.proyecto_bahiadelingles;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto_bahiadelingles.db.DbClientes;
import com.example.proyecto_bahiadelingles.db.DbLofts;
import com.example.proyecto_bahiadelingles.model.Loft;

public class Vista_cliente extends AppCompatActivity
{


    EditText edtCambiarEstado,edtNombreLoft;
    Button btnEnviarDatos;
    Loft loft;
    String id = "";
    boolean correcto = false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_cliente);
        edtNombreLoft= findViewById(R.id.edtLoftUsVer2);
        edtCambiarEstado = findViewById(R.id.edtCambiarEstado);
        btnEnviarDatos = findViewById(R.id.btnEnviarDatos);

        if(savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();
            if(extras == null)
            {
                id = (null);
            }
            else
            {
                id = extras.getString("ID");
            }
        }
        else
        {
            id = (String) savedInstanceState.getSerializable("ID");
        }




        DbLofts dbLofts = new DbLofts(Vista_cliente.this);
        loft = dbLofts.seleccionarLoftNombre(id);


       if(loft != null)
               {
                   edtNombreLoft.setText(loft.getNombre());
                   edtCambiarEstado.setText(loft.getComentario());

               }

        btnEnviarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtCambiarEstado.getText().toString().equals(""))
                {
                    correcto = dbLofts.editarLoftcliente(id, edtCambiarEstado.getText().toString());
                    if(!correcto)
                    {
                        Toast.makeText(Vista_cliente.this, "LOFT MODIFICADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(Vista_cliente.this, "ERROR AL MODIFICAR LOFT", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(Vista_cliente.this, "DEBE ESCRIBIR EL NOMBRE", Toast.LENGTH_SHORT).show();
                }
            }
        });






    }
}