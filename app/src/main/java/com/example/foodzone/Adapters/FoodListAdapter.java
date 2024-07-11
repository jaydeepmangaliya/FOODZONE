package com.example.foodzone.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.foodzone.Activitys.DetailActivity;
import com.example.foodzone.Model.Foods;
import com.example.foodzone.R;

import java.util.ArrayList;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.Listviewholder> {

ArrayList<Foods> item;
Context context;

    public FoodListAdapter(ArrayList<Foods> item) {
        this.item = item;
    }

    @NonNull
    @Override
    public Listviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        return new Listviewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Listviewholder holder, @SuppressLint("RecyclerView") int position) {
     holder.Ltiteltxt.setText(item.get(position).getTitle());
     holder.Ltimetxt.setText(item.get(position).getTimeValue()+" min");
     holder.Lpricetxt.setText("RS."+item.get(position).getPrice());
     holder.Lratetxt.setText(""+item.get(position).getStart());


        Glide.with(context)
                .load(item.get(position).getImagePath())
                .transform(new CenterCrop(),new RoundedCorners(50))
                .into(holder.Limg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("object",item.get(position));
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return item.size();
    }

    public class Listviewholder extends RecyclerView.ViewHolder{
        TextView Ltiteltxt,Lpricetxt,Lratetxt,Ltimetxt;
        ImageView Limg;
        public Listviewholder(@NonNull View itemView) {


            super(itemView);
            Ltiteltxt = itemView.findViewById(R.id.Ltitle);
            Lpricetxt = itemView.findViewById(R.id.Lprice);
            Lratetxt = itemView.findViewById(R.id.Lrating);
            Ltimetxt=itemView.findViewById(R.id.Ltime);
            Limg = itemView.findViewById(R.id.LImg);
        }
    }
}
