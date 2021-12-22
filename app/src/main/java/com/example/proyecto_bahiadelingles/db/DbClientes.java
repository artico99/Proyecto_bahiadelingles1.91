package com.example.proyecto_bahiadelingles.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.proyecto_bahiadelingles.model.Cliente;

import java.util.ArrayList;

public class DbClientes extends DbHelper{

    Context context;

    public DbClientes(@Nullable Context context) {
        super(context);

        this.context = context;

    }

    public long insertarCliente(String nombre, String apellido, String rut,String fechaE,String FechaS, String telefono, String correo, String numeroLoft, String comentario)
    {
        long id = 0;
        try
        {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("apellido", apellido);
            values.put("rut", rut);
            values.put("fechaE", fechaE);
            values.put("fechaS", fechaE);
            values.put("telefono", telefono);
            values.put("correo", correo);
            values.put("numeroLoft", numeroLoft);
            values.put("comentario", comentario);

            id = db.insert(TABLA_CLIENTE, null, values);
        }
        catch(Exception ex)
        {
            ex.toString();
        }
        return id;

    }
    public long insertarClienteRH(String nombre, String apellido, String rut,String fechaE,String FechaS, String telefono, String correo, String numeroLoft,String comportamiento, String comentario)
    {
        long id = 0;
        try
        {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("apellido", apellido);
            values.put("rut", rut);
            values.put("fechaE", fechaE);
            values.put("fechaS", fechaE);
            values.put("telefono", telefono);
            values.put("correo", correo);
            values.put("numeroLoft", numeroLoft);
            values.put("comportamiento", comportamiento);
            values.put("comentario", comentario);

            id = db.insert(TABLA_CLIENTEH, null, values);
        }
        catch(Exception ex)
        {
            ex.toString();
        }
        return id;

    }
    //MOSTRAR DATOS
    public ArrayList<Cliente> mostrarCliente()
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Cliente> listaCliente = new ArrayList<>();
        Cliente cliente = null;
        Cursor cursorCliente = null;

        cursorCliente = db.rawQuery("SELECT * FROM " + TABLA_CLIENTE, null);

        if(cursorCliente.moveToFirst())
        {
            do{
                cliente = new Cliente();
                cliente.setId(cursorCliente.getInt(0));
                cliente.setNombre(cursorCliente.getString(1));
                cliente.setApellido(cursorCliente.getString(2));
                cliente.setNumeroLoft(cursorCliente.getString(8));
                listaCliente.add(cliente);
            } while (cursorCliente.moveToNext());
        }
        cursorCliente.close();

        return listaCliente;
    }

    public ArrayList<Cliente> mostrarClienteRH()
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Cliente> listaCliente = new ArrayList<>();
        Cliente cliente = null;
        Cursor cursorClienterh = null;

        cursorClienterh = db.rawQuery("SELECT * FROM " + TABLA_CLIENTEH, null);

        if(cursorClienterh.moveToFirst())
        {
            do{
                cliente = new Cliente();
                cliente.setId(cursorClienterh.getInt(0));
                cliente.setNombre(cursorClienterh.getString(1));
                cliente.setApellido(cursorClienterh.getString(2));
                cliente.setNumeroLoft(cursorClienterh.getString(8));
                listaCliente.add(cliente);
            } while (cursorClienterh.moveToNext());
        }
        cursorClienterh.close();
        return listaCliente;
    }

    public Cliente seleccionarCliente(int id)
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cliente cliente = null;
        Cursor cursorCliente;

        cursorCliente = db.rawQuery("SELECT * FROM " + TABLA_CLIENTE + " WHERE id = " + id + " LIMIT 1 ", null);

        if(cursorCliente.moveToFirst())
        {
            cliente = new Cliente();
            cliente.setId(cursorCliente.getInt(0));
            cliente.setNombre(cursorCliente.getString(1));
            cliente.setApellido(cursorCliente.getString(2));
            cliente.setRut(cursorCliente.getString(3));
            cliente.setFechaEntrada(cursorCliente.getString(4));
            cliente.setFechaSalida(cursorCliente.getString(5));
            cliente.setTelefono(cursorCliente.getString(6));
            cliente.setCorreo(cursorCliente.getString(7));
            cliente.setNumeroLoft(cursorCliente.getString(8));
            cliente.setComentario(cursorCliente.getString(9));


        }
        cursorCliente.close();

        return cliente;
    }
    public Cliente seleccionarClienteRH(int id)
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cliente cliente = null;
        Cursor cursorCliente;

        cursorCliente = db.rawQuery("SELECT * FROM " + TABLA_CLIENTEH + " WHERE id = " + id + " LIMIT 1 ", null);

        if(cursorCliente.moveToFirst())
        {
            cliente = new Cliente();
            cliente.setId(cursorCliente.getInt(0));
            cliente.setNombre(cursorCliente.getString(1));
            cliente.setApellido(cursorCliente.getString(2));
            cliente.setRut(cursorCliente.getString(3));
            cliente.setFechaEntrada(cursorCliente.getString(4));
            cliente.setFechaSalida(cursorCliente.getString(5));
            cliente.setTelefono(cursorCliente.getString(6));
            cliente.setCorreo(cursorCliente.getString(7));
            cliente.setNumeroLoft(cursorCliente.getString(8));
            cliente.setComportamiento(cursorCliente.getString(9));
            cliente.setComentario(cursorCliente.getString(10));


        }
        cursorCliente.close();

        return cliente;
    }
    public boolean editarCliente(int id, String nombre, String apellido, String rut,String fechaEntrada,String fechaSalida, String telefono, String correo, String numeroLoft, String comentario)
    {
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try
        {
            db.execSQL(" UPDATE " + TABLA_CLIENTE + " SET nombre = '" + nombre + "', " +
                    "apellido = '" + apellido + "', " +
                    "rut = '" + rut + "', " +
                    "fechaE= '" + fechaEntrada + "', " +
                    "fechaS = '" + fechaSalida + "', " +
                    "telefono = '" + telefono + "', " +
                    "correo = '" + correo + "', " +
                    "numeroLoft = '" + numeroLoft + "', " +
                    "comentario = '" + comentario + "' WHERE id = '" + id + "' ");
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

    public boolean eliminarCliente(int id)
    {
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try
        {

            db.execSQL(" DELETE FROM " + TABLA_CLIENTE + " WHERE id = '" + id + "' ");
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

    public boolean mostrarNombreLoftCliente(int id)
    {
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try
        {

            db.execSQL("SELECT numeroLoft FROM " + TABLA_CLIENTE + " WHERE id = " + id + " LIMIT 1 ", null);
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

    public int login (String loft)
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int a=0;
        Cursor cr=db.rawQuery(" SELECT * FROM " + TABLA_CLIENTE,null);
        if(cr != null && cr.moveToFirst())
        {
            do
            {
                if(cr.getString(6).equals(loft))
                {
                    a++;
                }

            }
            while(cr.moveToNext());
        }
        return a;

    }
}
