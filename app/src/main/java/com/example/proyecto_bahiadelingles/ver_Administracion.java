package com.example.proyecto_bahiadelingles;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyecto_bahiadelingles.model.Administracion;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ver_Administracion extends AppCompatActivity {

    EditText edtUsuariover, edtContraseñaVer;
    Button btnGuardarAd;
    String iduser1;
    FloatingActionButton fbEditarAd, fbEliminarAd;
    Administracion administracion;
    int id = 0;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_ad);

        edtUsuariover = findViewById(R.id.edtUsuarioAdVer);
        edtContraseñaVer = findViewById(R.id.edtContraseñaAdVer);
        requestQueue = Volley.newRequestQueue(this);
        btnGuardarAd = findViewById(R.id.btnGuardarAd);
        fbEditarAd = findViewById(R.id.fbEditarAd);
        fbEliminarAd = findViewById(R.id.fbEliminarAd);

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



        //Dbadministracion dbadministracion = new Dbadministracion(ver_Administracion.this);
        //administracion = dbadministracion.seleccionarAdministracion(id);

        if (administracion != null)
        {
            edtUsuariover.setText(administracion.getUsuario());
            edtContraseñaVer.setText(administracion.getContraseña());

            btnGuardarAd.setVisibility(View.INVISIBLE);

            edtUsuariover.setInputType(InputType.TYPE_NULL);
            edtContraseñaVer.setInputType(InputType.TYPE_NULL);

        }
        fbEditarAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ver_Administracion.this, editar_administrador.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fbEliminarAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ver_Administracion.this);
                builder.setMessage("¿Desea eliminar este registro?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which)
                    {
                        String idu = iduser1;
                        borrarusuario(idu);
                        lista();

                    }
                })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                            }
                        }).show();
            }
        });
    }

    private void borrarusuario(final String id) {
        String url= ("http://192.168.10.14/bahia/delete.php");
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ver_Administracion.this, "Eliminado exitosamente", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ver_Administracion.this, "Error al eliminar", Toast.LENGTH_SHORT).show();
            }
        }
        ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("id",id);
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
                       String iduser;
                        String usuario,contraseña;
                        try {
                            iduser = response.getString("id");
                            usuario = response.getString("usuario");
                            contraseña = response.getString("contraseña");
                            iduser1 = iduser;
                            edtUsuariover.setText(usuario);
                            edtContraseñaVer.setText(contraseña);

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
    private void lista()
    {
        Intent intent = new Intent(this, MenuPrincipalUsuario.class);
        startActivity(intent);
    }
}
