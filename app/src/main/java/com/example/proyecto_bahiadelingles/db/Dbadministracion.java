package com.example.proyecto_bahiadelingles.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.proyecto_bahiadelingles.model.Administracion;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class Dbadministracion extends DbHelper {

    Context context;

    public Dbadministracion(@Nullable Context context) {
        super(context);

        this.context = context;

    }

    public long insertarAdministracion(String usuario, String contraseña)
    {
        long id = 0;
        try
        {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("usuario", usuario);
            values.put("contraseña", contraseña);

            id = db.insert(TABLA_ADMINISTRACION, null, values);
        }
        catch(Exception ex)
        {
            ex.toString();
        }
        return id;

    }

    public boolean editarAdministracion(int id, String usuario, String contraseña)
    {
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try
        {

            db.execSQL(" UPDATE " + TABLA_ADMINISTRACION + " SET usuario = '" + usuario + "', contraseña = '" + contraseña + "' WHERE id = '" + id + "' ");
        }
        catch(Exception ex)
        {
            ex.toString();
            correcto = false;
        }
        finally
        {
            db.close();
        }
        return correcto;
    }

    public boolean eliminarAdministracion(int id)
    {
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try
        {

            db.execSQL(" DELETE FROM " + TABLA_ADMINISTRACION + " WHERE ID = '" + id + "' ");
            correcto = true;
        }
        catch(Exception ex)
        {
            ex.toString();
            correcto = false;
        }
        finally
        {
            db.close();
        }
        return correcto;
    }

    public ArrayList<Administracion> mostrarAdministracion()
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Administracion> listaAdministracion = new ArrayList<>();
        Administracion administracion = null;
        Cursor cursorAdministracion = null;

        cursorAdministracion = db.rawQuery("SELECT * FROM " + TABLA_ADMINISTRACION, null);

        if(cursorAdministracion.moveToFirst())
        {
            do{
                administracion = new Administracion();
                administracion.setId(cursorAdministracion.getInt(0));
                administracion.setUsuario(cursorAdministracion.getString(1));
                administracion.setContraseña(cursorAdministracion.getString(2));
                listaAdministracion.add(administracion);
            } while (cursorAdministracion.moveToNext());
        }
        cursorAdministracion.close();

        return listaAdministracion;
    }

    public Administracion seleccionarAdministracion(int id)
    {


                /*DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Administracion administracion = null;
        Cursor cursorAdministracion;

        cursorAdministracion = db.rawQuery("SELECT * FROM " + TABLA_ADMINISTRACION + " WHERE id = " + id + " LIMIT 1 ", null);

        if(cursorAdministracion.moveToFirst())
        {
            administracion = new Administracion();
            administracion.setId(cursorAdministracion.getInt(0));
            administracion.setUsuario(cursorAdministracion.getString(1));
            administracion.setContraseña(cursorAdministracion.getString(2));


        }
        cursorAdministracion.close();

        return administracion; */
        return null;
    }

    public int login (String usuario, String pass)
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int a=0;
        Cursor cr=db.rawQuery(" SELECT * FROM " + TABLA_ADMINISTRACION,null);
        if(cr != null && cr.moveToFirst())
        {
            do
                {
                if(cr.getString(1).equals(usuario)&&cr.getString(2).equals(pass))
                {
                    a++;
                }

            }
            while(cr.moveToNext());
        }
        return a;

    }



}
