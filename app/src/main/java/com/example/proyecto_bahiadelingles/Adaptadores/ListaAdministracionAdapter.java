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
import com.example.proyecto_bahiadelingles.ver_Administracion;

import java.util.ArrayList;

public class ListaAdministracionAdapter extends RecyclerView.Adapter<ListaAdministracionAdapter.AdministracionViewHolder> {

    ArrayList<Administracion> listaAdministracion;
    public ListaAdministracionAdapter(ArrayList<Administracion> listaAdministracion)
    {
        this.listaAdministracion = listaAdministracion;
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

}
