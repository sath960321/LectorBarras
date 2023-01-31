package com.example.lectorbarras.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lectorbarras.R;
import com.example.lectorbarras.VerActivity;
import com.example.lectorbarras.clases.Medios;

import java.util.ArrayList;

public class ListaMediosAdapter extends RecyclerView.Adapter<ListaMediosAdapter.MediosViewHolder> implements Filterable {
    Context context;
    int resourceLayout;
    ArrayList<Medios> listaMedios;
    ArrayList<Medios> getListaMedios = new ArrayList<>();


    public ListaMediosAdapter(Context context, int resourceLayout, ArrayList<Medios> listaMedios) {
        this.context = context;
        this.resourceLayout = resourceLayout;
        this.listaMedios = listaMedios;
        this.getListaMedios = listaMedios;
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
        holder.viewTypeMB.setText(listaMedios.get(position).getType());
        holder.viewLocationMB.setText(listaMedios.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return listaMedios.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length() == 0){
                    filterResults.values = getListaMedios;
                    filterResults.count = getListaMedios.size();
                } else {
                    String searchStr = constraint.toString().toLowerCase();
                    ArrayList<Medios> medios = new ArrayList<>();
                    for (Medios medios1: getListaMedios){
                        if (medios1.getIdenMB().toLowerCase().contains(searchStr) || medios1.getType().toLowerCase().contains(searchStr) || medios1.getDescription().toLowerCase().contains(searchStr) || medios1.getLocation().toLowerCase().contains(searchStr)){
                            medios.add(medios1);
                        }
                    }
                    filterResults.values = medios;
                    filterResults.count = medios.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listaMedios = (ArrayList<Medios>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

    public class MediosViewHolder extends RecyclerView.ViewHolder {

        TextView viewIdenMB, viewTypeMB, viewLocationMB;


        public MediosViewHolder(@NonNull View itemView) {
            super(itemView);

            viewIdenMB = itemView.findViewById(R.id.viewIdenMB);
            viewTypeMB = itemView.findViewById(R.id.viewType);
            viewLocationMB = itemView.findViewById(R.id.viewLocation);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaMedios.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
