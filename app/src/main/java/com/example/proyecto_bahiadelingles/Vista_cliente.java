package com.example.proyecto_bahiadelingles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto_bahiadelingles.Adaptadores.ListaClienteAdapter;
import com.example.proyecto_bahiadelingles.db.DbClientes;
import com.example.proyecto_bahiadelingles.db.DbLofts;
import com.example.proyecto_bahiadelingles.model.Cliente;
import com.example.proyecto_bahiadelingles.model.Loft;

import java.util.ArrayList;
import java.util.Iterator;

public class Vista_cliente extends AppCompatActivity
{
    Cliente cliente;
    TextView tvNombreLoft;
    EditText edtEnviarComentario;
    String comentario;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_cliente);

        tvNombreLoft = findViewById(R.id.tvNombreLoft);
        edtEnviarComentario = findViewById(R.id.edtEnviarComentario);


        DbClientes dbClientes = new DbClientes(Vista_cliente.this);
        SQLiteDatabase db=dbClientes.getWritableDatabase();

        Cursor cursor = db.rawQuery("select numeroLoft from cliente where numeroLoft == 1 ",null);

        if(cursor.moveToFirst())
        {

            tvNombreLoft.setText("Loft " + cursor.getString(0));
        }

    }
}