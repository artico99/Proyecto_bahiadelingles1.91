package com.example.proyecto_bahiadelingles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import com.example.proyecto_bahiadelingles.Adaptadores.ListaClienteAdapter;
import com.example.proyecto_bahiadelingles.Adaptadores.ListaClientesRHAdapter;
import com.example.proyecto_bahiadelingles.db.DbClientes;
import com.example.proyecto_bahiadelingles.model.Cliente;

import java.util.ArrayList;

public class Listar_clienteRH extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView txtBuscarClHr1;
    RecyclerView listaCliente1;
    ArrayList<Cliente> listaArrayCliente1;
    ListaClientesRHAdapter adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_clienterh);
        txtBuscarClHr1 = findViewById(R.id.txtBuscarClRH);
        listaCliente1 = findViewById(R.id.ListaClienteRH);
        listaCliente1.setLayoutManager(new LinearLayoutManager(Listar_clienteRH.this));

        DbClientes dbClientes = new DbClientes(Listar_clienteRH.this);
        listaArrayCliente1 = new ArrayList<>();
        adapter1 = new ListaClientesRHAdapter(dbClientes.mostrarClienteRH());
        listaCliente1.setAdapter(adapter1);
        txtBuscarClHr1.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter1.filtrado(s);
        return false;

    }
}