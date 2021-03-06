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
import com.example.proyecto_bahiadelingles.Ver_cliente;
import com.example.proyecto_bahiadelingles.model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaClienteAdapter extends RecyclerView.Adapter<ListaClienteAdapter.ClienteViewHolder> {

    ArrayList<Cliente> listaCliente;
    ArrayList<Cliente> listaOriginal;

    public ListaClienteAdapter(ArrayList<Cliente>listaCliente)
    {
        this.listaCliente = listaCliente;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaCliente);
    }



    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_cliente, null,false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {

        holder.tvNombreCliente.setText(listaCliente.get(position).getNombre());
        holder.tvApellidoCliente.setText(listaCliente.get(position).getApellido());
        holder.tvNumLoftCliente.setText(listaCliente.get(position).getNumeroLoft());
    }

    @Override
    public int getItemCount()
    {

        return listaCliente.size();
    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombreCliente, tvApellidoCliente, tvNumLoftCliente;

        public ClienteViewHolder(@NonNull View itemView)
        {
            super(itemView);

            tvNombreCliente = itemView.findViewById(R.id.tvNombreCliente);
            tvApellidoCliente = itemView.findViewById(R.id.tvApellidoCliente);
            tvNumLoftCliente = itemView.findViewById(R.id.tvNumLoftCliente);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, Ver_cliente.class);
                    intent.putExtra("ID", listaCliente.get(getAdapterPosition()).getId());
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
            listaCliente.clear();
            listaCliente.addAll(listaOriginal);
        }
        else
            {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
            {
                List<Cliente> collecion = listaCliente.stream().filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                listaCliente.clear();
                listaCliente.addAll(collecion);
            }
            else
                {
                for (Cliente c:listaOriginal)
                {
                    if(c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                    {
                        listaCliente.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

}
