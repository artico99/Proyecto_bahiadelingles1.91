package com.example.proyecto_bahiadelingles;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyecto_bahiadelingles.db.Dbadministracion;

import java.util.HashMap;
import java.util.Map;

public class Insertar_Administracion extends AppCompatActivity {


    EditText txtUser, txtpass;
    boolean sw = true;

    RequestQueue requestQueue;

    private static final String url1 = "http://192.168.10.14/bahia/savead.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_registrar_ad);
        requestQueue = Volley.newRequestQueue(this);

        Button btnRegistrar = findViewById(R.id.btnRegistrar);




            btnRegistrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    txtUser = findViewById(R.id.edtUser);
                    txtpass = findViewById(R.id.edtPass);
                    String usuario = txtUser.getText().toString();
                    String contraseña = txtpass.getText().toString();

                    if (usuario.equals("") || contraseña.equals("")) {
                        Toast.makeText(Insertar_Administracion.this, "Debe rellenar ambos campos", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        createuser(usuario,contraseña);
                        Toast.makeText(Insertar_Administracion.this, "Administrador creado exitosamente", Toast.LENGTH_SHORT).show();
                    }

                   /* txtUser = findViewById(R.id.edtUser);
                    txtpass = findViewById(R.id.edtPass);
                    String user = txtUser.getText().toString();
                    String pass = txtpass.getText().toString();
                    if (user.equals("") || pass.equals("")) {
                        Toast.makeText(Insertar_Administracion.this, "Debe rellenar ambos campos", Toast.LENGTH_SHORT).show();
                    } else {

                        Dbadministracion dbadministracion = new Dbadministracion(Insertar_Administracion.this);
                        long id = dbadministracion.insertarAdministracion(txtUser.getText().toString(), txtpass.getText().toString());

                        if (id > 0) {
                            Toast.makeText(Insertar_Administracion.this, "Usuario agregado", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Insertar_Administracion.this, "Error al guardar registro", Toast.LENGTH_SHORT).show();
                        }
                    }
                */
                }

            });





    }

    private void createuser(final String usuario,final String contraseña) {


        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Insertar_Administracion.this,"Correcto",Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Insertar_Administracion.this,"Error",Toast.LENGTH_SHORT).show();
                    }
                }


        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();

                params.put("usuario",usuario);
                params.put("contraseña",contraseña);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}