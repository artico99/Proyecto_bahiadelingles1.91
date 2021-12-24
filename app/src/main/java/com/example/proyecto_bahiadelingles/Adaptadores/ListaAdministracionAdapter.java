package com.example.proyecto_bahiadelingles.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_bahiadelingles.R;
import com.example.proyecto_bahiadelingles.model.Administracion;
import com.example.proyecto_bahiadelingles.model.Cliente;
import com.example.proyecto_bahiadelingles.ver_Administracion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaAdministracionAdapter extends RecyclerView.Adapter<ListaAdministracionAdapter.AdministracionViewHolder> {

    ArrayList<Administracion> listaAdministracion;
    ArrayList<Administracion> listaOriginal;
    public ListaAdministracionAdapter(ArrayList<Administracion> listaAdministracion, Context applicationContext)
    {
        this.listaAdministracion = listaAdministracion;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaAdministracion);
    }

    @NonNull
    @Override
    public AdministracionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_administracion,null,false);
        return new AdministracionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdministracionViewHolder holder, int position) {

        holder.viewNombre.setText(listaAdministracion.get(position).getUsuario());
        holder.viewContraseña.setText("*****");
    }

    @Override
    public int getItemCount() {
        return listaAdministracion.size();
    }

    public class AdministracionViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre, viewContraseña;

        public AdministracionViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewContraseña = itemView.findViewById(R.id.viewContraseña);


            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ver_Administracion.class);
                    intent.putExtra("ID", listaAdministracion.get(getAdapterPosition()).getId());
                    context.startActivity(intent);

                }
            });

        }

    }
    public void filtradoAd (String txtBuscar)
    {
        int longuitud = txtBuscar.length();
        if(longuitud==0)
        {
            listaAdministracion.clear();
            listaAdministracion.addAll(listaOriginal);
        }
        else
        {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
            {
                List<Administracion> collecion = listaAdministracion.stream().filter(i -> i.getUsuario().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                listaAdministracion.clear();
                listaAdministracion.addAll(collecion);
            }
            else
            {
                for (Administracion c:listaOriginal)
                {
                    if(c.getUsuario().toLowerCase().contains(txtBuscar.toLowerCase()))
                    {
                        listaAdministracion.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

}
