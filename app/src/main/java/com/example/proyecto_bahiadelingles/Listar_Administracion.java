package com.example.proyecto_bahiadelingles;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_bahiadelingles.Adaptadores.ListaAdministracionAdapter;
import com.example.proyecto_bahiadelingles.db.Dbadministracion;
import com.example.proyecto_bahiadelingles.model.Administracion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Listar_Administracion extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView txtBuscarad;
    RecyclerView listaAdministracion;
    ArrayList<Administracion> listaArrayAdministracion;
    ListaAdministracionAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ad_re);

        txtBuscarad = findViewById(R.id.txtBuscarAd);

        listaAdministracion = findViewById(R.id.listaAdministracion);
        listaAdministracion.setLayoutManager(new LinearLayoutManager(Listar_Administracion.this));

        listaArrayAdministracion = new ArrayList<Administracion>();

        JsonFetch jsonFetch = new JsonFetch();
        jsonFetch.execute();

       /* txtBuscarad = findViewById(R.id.txtBuscarAd);
        listaAdministracion = findViewById(R.id.listaAdministracion);
        listaAdministracion.setLayoutManager(new LinearLayoutManager(Listar_Administracion.this));

        Dbadministracion dbadministracion = new Dbadministracion(Listar_Administracion.this);

        listaArrayAdministracion = new ArrayList<>();

        adapter = new ListaAdministracionAdapter(dbadministracion.mostrarAdministracion());
        listaAdministracion.setAdapter(adapter);
        txtBuscarad.setOnQueryTextListener(this);*/
    }

    public class JsonFetch extends AsyncTask<String,String, String>
    {
        HttpURLConnection httpURLConnection = null;
String mainfile;
        @Override
        protected String doInBackground(String... strings) {
            URL url = null;
            try {
                url = new URL("http://192.168.10.14/bahia/listadAd.php");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String line="";


                while((line=bufferedReader.readLine())!=null)
                {
                    stringBuffer.append(line);
                }

                mainfile = stringBuffer.toString();

                JSONArray parent = new JSONArray(mainfile);
                int i =0;

                while(i<= parent.length()){
                    JSONObject admin = parent.getJSONObject(i);
                    int id = admin.getInt("id");
                    String name = admin.getString("usuario");
                    String contraseña = admin.getString("contraseña");
                    listaArrayAdministracion.add(new Administracion(id,name,contraseña));
                    i++;
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            ListaAdministracionAdapter listaAdministracionAdapter = new ListaAdministracionAdapter(listaArrayAdministracion,getApplicationContext());
            listaAdministracion.setAdapter(listaAdministracionAdapter);
        }
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtradoAd(s);
        return false;
    }
}