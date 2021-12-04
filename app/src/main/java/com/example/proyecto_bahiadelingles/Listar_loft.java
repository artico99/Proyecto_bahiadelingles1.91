package com.example.proyecto_bahiadelingles;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_bahiadelingles.Adaptadores.ListaLoftAdapter;
import com.example.proyecto_bahiadelingles.db.DbLofts;
import com.example.proyecto_bahiadelingles.model.Loft;

import java.util.ArrayList;

public class Listar_loft extends AppCompatActivity {

    RecyclerView listaLofts;
    ArrayList<Loft> listaArrayLofts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_loft);

        listaLofts = findViewById(R.id.ListaLofts);
        listaLofts.setLayoutManager(new LinearLayoutManager(Listar_loft.this));

        DbLofts dbLofts = new DbLofts(Listar_loft.this);

        listaArrayLofts = new ArrayList<>();

        ListaLoftAdapter adapter = new ListaLoftAdapter(dbLofts.mostrarLofts());
        listaLofts.setAdapter(adapter);
    }
}
