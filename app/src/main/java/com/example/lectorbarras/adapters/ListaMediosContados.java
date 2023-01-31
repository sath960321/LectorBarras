package com.example.lectorbarras.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lectorbarras.R;
import com.example.lectorbarras.clases.ConteoMensual;

import java.util.ArrayList;

public class ListaMediosContados extends RecyclerView.Adapter<ListaMediosContados.MediosViewHolder>{
    Context context;
    int resourceLayout;
    ArrayList<ConteoMensual> listaMedios;

    public ListaMediosContados(Context context, int resourceLayout, ArrayList<ConteoMensual> listaMedios) {
        this.context = context;
        this.resourceLayout = resourceLayout;
        this.listaMedios = listaMedios;
    }
    @NonNull
    @Override
    public MediosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resourceLayout, null, false);
        return new MediosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediosViewHolder holder, int position) {
        holder.viewIdenMB.setText(listaMedios.get(position).getIdenMB());
    }

    @Override
    public int getItemCount() { return listaMedios.size();}

    public class MediosViewHolder extends RecyclerView.ViewHolder{
        TextView viewIdenMB;

        public MediosViewHolder(@NonNull View itemView) {
            super(itemView);
            viewIdenMB = itemView.findViewById(R.id.viewIdenMB);
        }
    }
}
