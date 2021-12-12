package com.example.proyecto_bahiadelingles;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_bahiadelingles.db.DbClientes;
import com.example.proyecto_bahiadelingles.db.DbLofts;
import com.example.proyecto_bahiadelingles.model.Loft;

public class LoginUs extends AppCompatActivity {

    EditText edtLoft,edtLoftUsVer;
    String loftnum;
    Button btnIngresar;
    Loft loft;
    int id = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_login_user);
        edtLoft = findViewById(R.id.edtUsuarioLoft);
        edtLoftUsVer = findViewById(R.id.edtLoftUsVer);
        btnIngresar = findViewById(R.id.btnLoginUs);
        DbClientes dbClientes = new DbClientes(LoginUs.this);




        DbLofts dbLofts = new DbLofts(LoginUs.this);
        loft = dbLofts.seleccionarLoftNombre(loftnum);
        if(loft != null)
        {

            edtLoftUsVer.setText(loft.getNombre());

        }
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loftnum = edtLoft.getText().toString();
                String lofts = edtLoft.getText().toString();

                if(lofts.equals(""))
                {
                    Toast.makeText(LoginUs.this, "Campos vacios", Toast.LENGTH_SHORT).show();
                }
                else if (dbClientes.login(lofts)==0)
                {
                    Toast.makeText(LoginUs.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                }
                else if (dbClientes.login(lofts)==1)
                {

                    DbLofts dbLofts = new DbLofts(LoginUs.this);
                    loft = dbLofts.seleccionarLoftNombre(loftnum);
                    if(loft != null)
                    {
                        Toast.makeText(LoginUs.this, "correcto",Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(LoginUs.this,"Datos Correctos",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginUs.this, Vista_cliente.class);
                    intent.putExtra("ID", loftnum);
                    startActivity(intent);
                }
            }
        });



    }


}
