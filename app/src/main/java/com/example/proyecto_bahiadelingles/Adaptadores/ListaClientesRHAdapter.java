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

import com.example.proyecto_bahiadelingles.model.Cliente;
import com.example.proyecto_bahiadelingles.ver_clienteRH;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaClientesRHAdapter extends RecyclerView.Adapter<ListaClientesRHAdapter.ClienteViewHolder1>{

    ArrayList<Cliente> listaClienteRH;
    ArrayList<Cliente> listaOriginal;

    public ListaClientesRHAdapter(ArrayList<Cliente>listaClienteRH)
    {
        this.listaClienteRH = listaClienteRH;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaClienteRH);
    }


    @NonNull
    @Override
    public ClienteViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_clienterh, null,false);
        return new ClienteViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder1 holder, int position) {
        holder.tvNombreClienteRH.setText(listaClienteRH.get(position).getNombre());
        holder.tvApellidoClienteRH.setText(listaClienteRH.get(position).getApellido());
        holder.tvNumLoftClienteRH.setText(listaClienteRH.get(position).getNumeroLoft());
    }

    @Override
    public int getItemCount() {
        return listaClienteRH.size();

    }

    public class ClienteViewHolder1 extends RecyclerView.ViewHolder {

        TextView tvNombreClienteRH, tvApellidoClienteRH, tvNumLoftClienteRH;

        public ClienteViewHolder1(@NonNull View itemView) {
            super(itemView);

            tvNombreClienteRH = itemView.findViewById(R.id.tvNombreClienteRH);
            tvApellidoClienteRH = itemView.findViewById(R.id.tvApellidoClienteRH);
            tvNumLoftClienteRH = itemView.findViewById(R.id.tvNumLoftClienteRH);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ver_clienteRH.class);
                    intent.putExtra("ID", listaClienteRH.get(getAdapterPosition()).getId());
                    context.startActivity(intent);

                }
            });

        }
    }
    public void filtrado (String txtBuscar)
    {
        int longuitud = txtBuscar.length();
        if(longuitud==0)
        {
            listaClienteRH.clear();
            listaClienteRH.addAll(listaOriginal);
        }
        else
        {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
            {
                List<Cliente> collecion = listaClienteRH.stream().filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                listaClienteRH.clear();
                listaClienteRH.addAll(collecion);
            }
            else
            {
                for (Cliente c:listaOriginal)
                {
                    if(c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                    {
                        listaClienteRH.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

}


