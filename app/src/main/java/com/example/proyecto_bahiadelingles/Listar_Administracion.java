package com.example.proyecto_bahiadelingles;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_bahiadelingles.Adaptadores.ListaAdministracionAdapter;
import com.example.proyecto_bahiadelingles.db.Dbadministracion;
import com.example.proyecto_bahiadelingles.model.Administracion;

import java.util.ArrayList;

public class Listar_Administracion extends AppCompatActivity {

    RecyclerView listaAdministracion;
    ArrayList<Administracion> listaArrayAdministracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ad_re);

        listaAdministracion = findViewById(R.id.listaAdministracion);
        listaAdministracion.setLayoutManager(new LinearLayoutManager(Listar_Administracion.this));

        Dbadministracion dbadministracion = new Dbadministracion(Listar_Administracion.this);

        listaArrayAdministracion = new ArrayList<>();

        ListaAdministracionAdapter adapter = new ListaAdministracionAdapter(dbadministracion.mostrarAdministracion());
        listaAdministracion.setAdapter(adapter);

    }
}