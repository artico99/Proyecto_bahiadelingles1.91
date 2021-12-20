package com.example.proyecto_bahiadelingles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.proyecto_bahiadelingles.db.DbLofts;
import com.example.proyecto_bahiadelingles.model.Loft;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class editar_loft extends AppCompatActivity
{
    EditText edtnumLoft,edtNombreLoft, edtComentarioLoft;
    Button btnGuardarLoft;
    FloatingActionButton fbEditarLoft, fbEliminarLoft;
    SwitchCompat switchCompat;
    boolean correcto = false;
    String edtPrueba;
    int reservado = 0;
    int Agua = 0;
    int Luz = 0;
    int Gas = 0;
    Loft loft;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        SwitchCompat swAgua,swLuz,swGas;
        setContentView(R.layout.activity_ver_loft);
        switchCompat = findViewById(R.id.swEstadoLoft);
        edtNombreLoft = findViewById(R.id.edtNombreLoftVer);
        edtnumLoft = findViewById(R.id.edtnumeroLoft);
        edtComentarioLoft = findViewById(R.id.edtComentarioLoftVer);
        btnGuardarLoft = findViewById(R.id.btnGuardarLoft);
        fbEditarLoft = findViewById(R.id.fbEditarLoft);
        fbEditarLoft.setVisibility(View.INVISIBLE);
        fbEliminarLoft = findViewById(R.id.fbEliminarLoft);
        fbEliminarLoft.setVisibility(View.INVISIBLE);
        swAgua = findViewById(R.id.swAguaEd);
        swLuz = findViewById(R.id.swLuzEd);
        swGas = findViewById(R.id.swGasEd);
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
            edtnumLoft.setText(loft.getNum());
            edtNombreLoft.setText(loft.getNombre());
            edtComentarioLoft.setText(loft.getEstado());
            reservado = loft.getReservado();
            if(reservado == 1){switchCompat.setChecked(true);} else{switchCompat.setChecked(false);}
            Luz = loft.getLuz();
            Gas = loft.getGas();
            Agua = loft.getAgua();
            if(Luz == 1){swLuz.setChecked(true);} else{ swLuz.setChecked(false);}
            if(Gas == 1){swGas.setChecked(true);} else{ swGas.setChecked(false);}
            if(Agua == 1){swAgua.setChecked(true);} else{ swAgua.setChecked(false);}
        }

        btnGuardarLoft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!edtNombreLoft.getText().toString().equals(""))
                {
                    if(switchCompat.isChecked()){reservado = 1;} else {reservado = 0;}
                    if(swAgua.isChecked()){Agua = 1;} else {Agua = 0;}
                    if(swGas.isChecked()){Gas = 1;} else {Gas = 0;}
                    if(swLuz.isChecked()){Luz = 1;} else {Luz = 0;}
                    correcto = dbLofts.editarLoft(id,edtnumLoft.getText().toString(), edtNombreLoft.getText().toString(), edtComentarioLoft.getText().toString(),Luz,Agua,Gas,reservado);
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