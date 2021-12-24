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
import com.example.proyecto_bahiadelingles.model.Loft;
import com.example.proyecto_bahiadelingles.Ver_loft;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaLoftAdapter extends RecyclerView.Adapter<ListaLoftAdapter.LoftViewHolder> {

    ArrayList<Loft>listaLofts;
    ArrayList<Loft> listaOriginal;
    public ListaLoftAdapter(ArrayList<Loft>listaLofts)
    {
        this.listaLofts = listaLofts;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaLofts);
    }

    @NonNull
    @Override
    public LoftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_lofts, null, false);
        return new LoftViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoftViewHolder holder, int position)
    {
        holder.tvNombreLoft.setText(listaLofts.get(position).getNombre());
        holder.tvComentarioLoft.setText(listaLofts.get(position).getEstado());
    }

    @Override
    public int getItemCount()
    {

        return listaLofts.size();
    }

    public class LoftViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombreLoft, tvComentarioLoft;

        public LoftViewHolder(@NonNull View itemView)
        {
            super(itemView);

            tvNombreLoft = itemView.findViewById(R.id.tvNombreLoft);
            tvComentarioLoft = itemView.findViewById(R.id.tvComentarioLoft);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, Ver_loft.class);
                    intent.putExtra("ID", listaLofts.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
    public void filtradoL (String txtBuscar)
    {
        int longuitud = txtBuscar.length();
        if(longuitud==0)
        {
            listaLofts.clear();
            listaLofts.addAll(listaOriginal);
        }
        else
        {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
            {
                List<Loft> collecion = listaLofts.stream().filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                listaLofts.clear();
                listaLofts.addAll(collecion);
            }
            else
            {
                for (Loft c:listaOriginal)
                {
                    if(c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                    {
                        listaLofts.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
}
