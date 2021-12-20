package com.example.proyecto_bahiadelingles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

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

    SwitchCompat swAgua,swLuz,swGas;
    EditText edtCambiarEstado,edtNombreLoft;
    Button btnEnviarDatos;
    Loft loft;
    String id = "";
    int Agua = 0;
    int Luz = 0;
    int Gas = 0;
    boolean correcto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_cliente);
        edtNombreLoft= findViewById(R.id.edtLoftUsVer2);
        edtCambiarEstado = findViewById(R.id.edtCambiarEstado);
        btnEnviarDatos = findViewById(R.id.btnEnviarDatos);
        swAgua = findViewById(R.id.swAgua);
        swLuz = findViewById(R.id.swLuz);
        swGas = findViewById(R.id.swTv);
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
        loft = dbLofts.seleccionarLoftNumero(id);


       if(loft != null)
               {
                   edtNombreLoft.setText(loft.getNombre());
                   edtCambiarEstado.setText(loft.getEstado());
                   Luz = loft.getLuz();
                   Gas = loft.getGas();
                   Agua = loft.getAgua();
                   if(Luz == 1){swLuz.setChecked(true);} else{ swLuz.setChecked(false);}
                   if(Gas == 1){swGas.setChecked(true);} else{ swGas.setChecked(false);}
                   if(Agua == 1){swAgua.setChecked(true);} else{ swAgua.setChecked(false);}

               }

        btnEnviarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtCambiarEstado.getText().toString().equals(""))
                {
                    if(swAgua.isChecked()){Agua = 1;} else {Agua = 0;}
                    if(swGas.isChecked()){Gas = 1;} else {Gas = 0;}
                    if(swLuz.isChecked()){Luz = 1;} else {Luz = 0;}

                    correcto = dbLofts.editarLoftcliente(id, edtCambiarEstado.getText().toString(),Luz,Agua,Gas);
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