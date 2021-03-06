package com.example.proyecto_bahiadelingles;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.proyecto_bahiadelingles.db.DbLofts;
import com.example.proyecto_bahiadelingles.model.Loft;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Ver_loft extends AppCompatActivity
{
    SwitchCompat switchCompat;
    SwitchCompat swAgua,swLuz,swGas;
    EditText edtNombreLoft, edtComentarioLoft,edtnumLoft;
    Button btnGuardarLoft;
    FloatingActionButton fbEditarLoft, fbEliminarLoft;
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
        setContentView(R.layout.activity_ver_loft);
        switchCompat = findViewById(R.id.swEstadoLoft);
        edtnumLoft = findViewById(R.id.edtnumeroLoft);
        edtNombreLoft = findViewById(R.id.edtNombreLoftVer);
        edtComentarioLoft = findViewById(R.id.edtComentarioLoftVer);
        btnGuardarLoft = findViewById(R.id.btnGuardarLoft);
        fbEditarLoft = findViewById(R.id.fbEditarLoft);
        fbEliminarLoft = findViewById(R.id.fbEliminarLoft);
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

        DbLofts dbLofts = new DbLofts(Ver_loft.this);
        loft = dbLofts.seleccionarLoft(id);

        if(loft != null)
        {
            //edtId.setText(loft.getId());
            edtnumLoft.setText(loft.getNum());
            edtNombreLoft.setText(loft.getNombre());
            edtComentarioLoft.setText(loft.getEstado());
            btnGuardarLoft.setVisibility(View.INVISIBLE);
            edtNombreLoft.setInputType(InputType.TYPE_NULL);
            edtComentarioLoft.setInputType(InputType.TYPE_NULL);
            edtnumLoft.setInputType(InputType.TYPE_NULL);
            reservado = loft.getReservado();
            if(reservado == 1){switchCompat.setChecked(true);} else{ switchCompat.setChecked(false);}
            Luz = loft.getLuz();
            Gas = loft.getGas();
            Agua = loft.getAgua();

            if(Luz == 1){swLuz.setChecked(true);} else{ swLuz.setChecked(false);}
            if(Gas == 1){swGas.setChecked(true);} else{ swGas.setChecked(false);}
            if(Agua == 1){swAgua.setChecked(true);} else{ swAgua.setChecked(false);}

        }

        fbEditarLoft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Ver_loft.this, editar_loft.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fbEliminarLoft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(Ver_loft.this);
                builder.setMessage("??Desea eliminar este registro?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                        if(dbLofts.eliminarLoft(id))
                        {
                            lista();
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