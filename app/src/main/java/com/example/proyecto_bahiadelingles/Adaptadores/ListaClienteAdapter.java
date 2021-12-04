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

public class ListaClienteAdapter extends RecyclerView.Adapter<ListaClienteAdapter.ClienteViewHolder> {

    ArrayList<Cliente> listaCliente;

    public ListaClienteAdapter(ArrayList<Cliente>listaCliente)
    {
        this.listaCliente = listaCliente;
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
        holder.tvRutCliente.setText(listaCliente.get(position).getRut());
        holder.tvTelefonoCliente.setText(listaCliente.get(position).getTelefono());
        holder.tvCorreoCliente.setText(listaCliente.get(position).getCorreo());
        holder.tvNumLoftCliente.setText(listaCliente.get(position).getNumeroLoft());
        holder.tvComentarioCliente.setText(listaCliente.get(position).getComentario());
    }

    @Override
    public int getItemCount()
    {

        return listaCliente.size();
    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombreCliente, tvApellidoCliente, tvRutCliente, tvTelefonoCliente, tvCorreoCliente, tvNumLoftCliente, tvComentarioCliente;

        public ClienteViewHolder(@NonNull View itemView)
        {
            super(itemView);

            tvNombreCliente = itemView.findViewById(R.id.tvNombreCliente);
            tvApellidoCliente = itemView.findViewById(R.id.tvApellidoCliente);
            tvRutCliente = itemView.findViewById(R.id.tvRutCliente);
            tvNumLoftCliente = itemView.findViewById(R.id.tvNumLoftCliente);
            tvComentarioCliente = itemView.findViewById(R.id.tvComentarioCliente);

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
}
