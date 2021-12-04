package com.example.proyecto_bahiadelingles;

import android.os.Bundle;

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

public class Listar_cliente extends AppCompatActivity {

    RecyclerView listaCliente;
    ArrayList<Cliente> listaArrayCliente;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_cliente);

        listaCliente = findViewById(R.id.ListaCliente);
        listaCliente.setLayoutManager(new LinearLayoutManager(Listar_cliente.this));

        DbClientes dbClientes = new DbClientes(Listar_cliente.this);

        listaArrayCliente = new ArrayList<>();

        ListaClienteAdapter adapter = new ListaClienteAdapter(dbClientes.mostrarCliente());
        listaCliente.setAdapter(adapter);
    }
}
