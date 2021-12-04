package com.example.proyecto_bahiadelingles;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_bahiadelingles.Adaptadores.ListaLoftAdapter;
import com.example.proyecto_bahiadelingles.db.DbHelper;
import com.example.proyecto_bahiadelingles.db.DbLofts;
import com.example.proyecto_bahiadelingles.model.Loft;

import java.util.ArrayList;

public class crearDB extends AppCompatActivity
{
    RecyclerView listaLofts;
    ArrayList<Loft>listaArrayLofts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_usuario_principal);

        listaLofts = findViewById(R.id.ListaLofts);
        listaLofts.setLayoutManager(new LinearLayoutManager(crearDB.this));

        DbHelper dbHelper = new DbHelper(crearDB.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if(db != null)
        {
            Toast.makeText(crearDB.this, "Base de datos creada correctamente", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(crearDB.this, "Error al crear Base de datos", Toast.LENGTH_SHORT).show();
        }
    }


    private void nuevoRegistro()
    {
        Intent intent =  new Intent(this, Insertar_loft.class);
        startActivity(intent);
    }

    private void listarRegistro()
    {
        Intent intent =  new Intent(this, Listar_loft.class);
        startActivity(intent);
    }

}
