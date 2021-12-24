package com.example.proyecto_bahiadelingles;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_bahiadelingles.Adaptadores.ListaLoftAdapter;
import com.example.proyecto_bahiadelingles.db.DbLofts;
import com.example.proyecto_bahiadelingles.model.Loft;

import java.util.ArrayList;

public class Listar_loft extends AppCompatActivity implements SearchView.OnQueryTextListener {
    SearchView txtBuscarL;
    RecyclerView listaLofts;
    ArrayList<Loft> listaArrayLofts;
    ListaLoftAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_loft);

        listaLofts = findViewById(R.id.ListaLofts);
        listaLofts.setLayoutManager(new LinearLayoutManager(Listar_loft.this));
        txtBuscarL = findViewById(R.id.txtBuscarl);
        DbLofts dbLofts = new DbLofts(Listar_loft.this);

        listaArrayLofts = new ArrayList<>();

        adapter = new ListaLoftAdapter(dbLofts.mostrarLofts());
        listaLofts.setAdapter(adapter);
        txtBuscarL.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtradoL(s);
        return false;
    }
}
