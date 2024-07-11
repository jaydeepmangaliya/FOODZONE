package com.example.foodzone.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.foodzone.Helper.ChangeNumberItemsListener;
import com.example.foodzone.Helper.ManagmentCart;
import com.example.foodzone.Model.Foods;
import com.example.foodzone.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.cartviewholder>{
   ArrayList<Foods> list;
   private ManagmentCart managmentCart;
   ChangeNumberItemsListener changeNumberItemsListener;

    public CartAdapter(ArrayList<Foods> list, ManagmentCart managmentCart, ChangeNumberItemsListener changeNumberItemsListener) {
        this.list = list;
        this.managmentCart = managmentCart;
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public cartviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartitems,parent,false);

        return new cartviewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull cartviewholder holder, @SuppressLint("RecyclerView") int position) {

        holder.Ctitle.setText(list.get(position).getTitle());
        holder.Cprice.setText("RS."+list.get(position).getNumberInCart()*list.get(position).getPrice());
        holder.Cnums .setText(list.get(position).getNumberInCart()+"");

        Glide.with(holder.itemView.getContext())
                .load(list.get(position).getImagePath())
                .transform(new CenterCrop(),new RoundedCorners(30))
                        .into(holder.Cimg);

        holder.Cpluse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
       managmentCart.plusNumberItem(list, position, new ChangeNumberItemsListener() {
           @Override
           public void change() {
               notifyDataSetChanged();
               changeNumberItemsListener.change();
           }
       });
            }
        });

        holder.Csubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managmentCart.minusNumberItem(list, position, new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.change();
                    }
                });
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managmentCart.removeItem(list, position, new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.change();
                    }
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class cartviewholder extends RecyclerView.ViewHolder {

        TextView Ctitle,Cprice,Cpluse,Csubtraction,Cnums;
        ConstraintLayout delete;
        ImageView Cimg;
        public cartviewholder(@NonNull View itemView) {
            super(itemView);
            Cimg = itemView.findViewById(R.id.CitemIMG);
            Cnums = itemView.findViewById(R.id.CARTitemnums);
            Cpluse =itemView.findViewById(R.id.CARTitempluse);
            Cprice = itemView.findViewById(R.id.CARTitemprice);
            Csubtraction = itemView.findViewById(R.id.CARTitemsubtarction);
            Ctitle = itemView.findViewById(R.id.CARTitemTitle);
            delete = itemView.findViewById(R.id.DELETE);
        }
    }
}
