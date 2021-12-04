package com.example.proyecto_bahiadelingles;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_bahiadelingles.db.DbHelper;

public class iniciarDB extends AppCompatActivity
{
    Button btnIniciarBd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciar_db);

        btnIniciarBd = findViewById(R.id.btnInciarDb);

        btnIniciarBd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper dbHelper = new DbHelper(iniciarDB.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                if(db != null)
                {
                    Toast.makeText(iniciarDB.this, "Base de datos creada correctamente", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(iniciarDB.this, "Error al crear Base de datos", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
