package com.example.fishingapp.views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fishingapp.R;
import com.example.fishingapp.models.EntityFish;

import java.util.ArrayList;


public class FishAdapter extends RecyclerView.Adapter<FishAdapter.FishViewHolder> implements View.OnClickListener{

    private ArrayList<EntityFish> items;
    private View.OnClickListener listener;

    // Clase interna:
    // Se implementa el ViewHolder que se encargará
    // de almacenar la vista del elemento y sus datos
    public static class FishViewHolder
            extends RecyclerView.ViewHolder {


        private ImageView photo;
        private TextView TextView_fish;
        private TextView TextView_fisher;

        public FishViewHolder(View itemView) {
            super(itemView);
            TextView_fish = (TextView) itemView.findViewById(R.id.Name);
            TextView_fisher = (TextView) itemView.findViewById(R.id.fishCaptured);
            photo=(ImageView) itemView.findViewById(R.id.photo);
        }

        public void FishBind(EntityFish item) {
            TextView_fish.setText(item.getFish());
            TextView_fisher.setText(item.getFisher());
            if(item.getImage() == null){
                photo.setBackground(MyApplication.getContext().getDrawable(R.drawable.ic_pesca));
            }else{
                byte[] decodedString = Base64.decode(item.getImage(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                photo.setImageBitmap(decodedByte);
            }
        }
    }

    // Contruye el objeto adaptador recibiendo la lista de datos
    public FishAdapter(@NonNull ArrayList<EntityFish> items) {
        this.items = items;
    }

    // Se encarga de crear los nuevos objetos ViewHolder necesarios
    // para los elementos de la colección.
    // Infla la vista del layout, crea y devuelve el objeto ViewHolder
    @Override
    public FishViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_row, parent, false);
        row.setOnClickListener(this);

        FishViewHolder fishViewHolder = new FishViewHolder(row);
        return fishViewHolder;
    }

    // Se encarga de actualizar los datos de un ViewHolder ya existente.
    @Override
    public void onBindViewHolder(FishViewHolder viewHolder, int position) {
        EntityFish item = items.get(position);
        viewHolder.FishBind(item);
    }

    // Indica el número de elementos de la colección de datos.
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Asigna un listener al elemento
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }


}
