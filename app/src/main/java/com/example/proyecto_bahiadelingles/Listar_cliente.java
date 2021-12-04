package com.example.proyecto_bahiadelingles;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_bahiadelingles.Adaptadores.ListaClienteAdapter;
import com.example.proyecto_bahiadelingles.Adaptadores.ListaLoftAdapter;
import com.example.proyecto_bahiadelingles.db.DbClientes;
import com.example.proyecto_bahiadelingles.db.DbLofts;
import com.example.proyecto_bahiadelingles.model.Cliente;
import com.example.proyecto_bahiadelingles.model.Loft;

import java.util.ArrayList;

public class Listar_cliente extends AppCompatActivity implements SearchView.OnQueryTextListener{

    SearchView txtBuscarCl;
    RecyclerView listaCliente;
    ArrayList<Cliente> listaArrayCliente;
    ListaClienteAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_cliente);
        txtBuscarCl = findViewById(R.id.txtBuscarCl);
        listaCliente = findViewById(R.id.ListaCliente);
        listaCliente.setLayoutManager(new LinearLayoutManager(Listar_cliente.this));



        DbClientes dbClientes = new DbClientes(Listar_cliente.this);

        listaArrayCliente = new ArrayList<>();

        adapter = new ListaClienteAdapter(dbClientes.mostrarCliente());
        listaCliente.setAdapter(adapter);

        txtBuscarCl.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }
}
