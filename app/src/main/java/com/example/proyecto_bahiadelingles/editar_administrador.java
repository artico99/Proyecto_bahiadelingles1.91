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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyecto_bahiadelingles.db.DbLofts;
import com.example.proyecto_bahiadelingles.db.Dbadministracion;
import com.example.proyecto_bahiadelingles.model.Administracion;
import com.example.proyecto_bahiadelingles.model.Loft;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class editar_administrador extends AppCompatActivity
{
    EditText edtUsuarioAdVer, edtContraseñaAdVer;
    Button btnGuardarAd;
    FloatingActionButton fbEditarAd, fbEliminarAd;
    RequestQueue requestQueue;
    boolean correcto = false;
    String iduser1;
    Administracion administracion;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_ad);

        edtUsuarioAdVer = findViewById(R.id.edtUsuarioAdVer);
        edtContraseñaAdVer = findViewById(R.id.edtContraseñaAdVer);
        requestQueue = Volley.newRequestQueue(this);
        btnGuardarAd = findViewById(R.id.btnGuardarAd);
        fbEditarAd = findViewById(R.id.fbEditarAd);
        fbEditarAd.setVisibility(View.INVISIBLE);
        fbEliminarAd = findViewById(R.id.fbEliminarAd);
        fbEliminarAd.setVisibility(View.INVISIBLE);

        if(savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();
            if(extras == null)
            {
                id = Integer.parseInt(null);
            }
            else
            {
                id = extras.getInt("ID");
            }
        }
        else
        {
            id = (int)savedInstanceState.getSerializable("ID");
        }
        mostrarusuario();
        //Dbadministracion dbadministracion = new Dbadministracion(editar_administrador.this);
        //administracion = dbadministracion.seleccionarAdministracion(id);

        if(administracion != null)
        {
            edtUsuarioAdVer.setText(administracion.getUsuario());
            edtContraseñaAdVer.setText(administracion.getContraseña());
        }

        btnGuardarAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(edtUsuarioAdVer.getText().toString().equals("")||edtContraseñaAdVer.getText().toString().equals(""))
                {

                    Toast.makeText(editar_administrador.this, "Debe rellenar ambos campos", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(editar_administrador.this, "Exito", Toast.LENGTH_SHORT).show();
                    String usuario = edtUsuarioAdVer.getText().toString().trim();
                    String contraseña = edtUsuarioAdVer.getText().toString().trim();

                    updateUser (iduser1,usuario,contraseña);

                }
            }
        });
    }

    private void updateUser(final String id,final String usuario, final String contraseña) {
        String url= ("http://192.168.10.14/bahia/editAd.php");
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        )
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id",id);
                params.put("usuario",usuario);
                params.put("contraseña",contraseña);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void mostrarusuario()
    {
        String url= ("http://192.168.10.14/bahia/fetchAD.php?id=" +id);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String usuario,contraseña,id;
                        try {
                            id = response.getString("id");
                            usuario = response.getString("usuario");
                            contraseña = response.getString("contraseña");
                            edtUsuarioAdVer.setText(usuario);
                            edtContraseñaAdVer.setText(contraseña);
                            iduser1 = id;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    private void verRegistroLoft()
    {
        Intent intent = new Intent(this, Ver_loft.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }

}
