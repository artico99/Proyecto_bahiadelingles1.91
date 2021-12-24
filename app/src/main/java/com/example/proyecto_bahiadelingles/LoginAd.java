package com.example.proyecto_bahiadelingles;

import android.content.Intent;
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

public class LoginAd extends AppCompatActivity {

    EditText edtUsuario,edtContraseña;
    Button btnIngresar,btnLoginUs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_login);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtContraseña = findViewById(R.id.edtContraseña);
        btnIngresar = (Button) findViewById(R.id.btnIngresarAd);
        btnLoginUs = (Button) findViewById(R.id.btnLoginUs);
        Dbadministracion dbadministracion = new Dbadministracion(LoginAd.this);

        btnLoginUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(LoginAd.this, LoginUs.class);
               startActivity(intent);
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String user = edtUsuario.getText().toString();
                String pass = edtContraseña.getText().toString();
                if(user.equals("") && pass.equals(""))
                {
                    Toast.makeText(LoginAd.this, "Campos vacios", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    validarUsuario("http://192.168.10.14/bahia/validar_usuario.php");
                }
            }
        });

    }
    private void validarUsuario(String URL)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty())
                {
                    Toast.makeText(LoginAd.this,"Datos Correctos",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginAd.this, MenuPrincipalUsuario.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(LoginAd.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("usuario",edtUsuario.getText().toString());
                parametros.put("contraseña",edtContraseña.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}