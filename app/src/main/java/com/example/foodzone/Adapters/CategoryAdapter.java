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
import com.example.foodzone.Activitys.FoodListActivity;
import com.example.foodzone.Model.Category;
import com.example.foodzone.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewholder> {


    ArrayList<Category> items;
    Context context;

    public CategoryAdapter(ArrayList<Category> items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new viewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position) {

      holder.titletext.setText(items.get(position).getName());
      int drawableResourceId = context.getResources().getIdentifier(items.get(position).getImagePath(),"drawable", holder.itemView.getContext().getPackageName());
        Glide.with(context)
                .load(items.get(position).getImagePath())
                .into(holder.categorypic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, FoodListActivity.class);
                intent.putExtra("CategoryId",items.get(position).getId());
                intent.putExtra("CategoryName",items.get(position).getName());
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public  class  viewholder  extends  RecyclerView.ViewHolder{
        TextView titletext;
        ImageView categorypic;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            titletext = itemView.findViewById(R.id.TITLE_TEXT);
            categorypic =itemView.findViewById(R.id.CATEGORY_IMG);

        }
    }
}
