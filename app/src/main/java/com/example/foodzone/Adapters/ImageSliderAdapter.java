package com.example.foodzone.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodzone.Model.ImageSlider;
import com.example.foodzone.R;

import java.util.List;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.sliderviewholder> {
 private List<ImageSlider>slideritem;
 private Context context;
 private ViewPager2 viewPager2;
  private  Runnable runnable = new Runnable() {
      @Override
      public void run() {
          slideritem.addAll(slideritem);
          notifyDataSetChanged();

      }
  };

    public ImageSliderAdapter(List<ImageSlider> slideritem) {
        this.slideritem = slideritem;
        this.context = context;
    }

    @NonNull
    @Override
    public sliderviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sliderimage,parent,false);
        return new sliderviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull sliderviewholder holder, int position) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transform(new CenterCrop(), new RoundedCorners(60));
        Glide.with(context).load(slideritem.get(position).getSimage())
                .apply(requestOptions)
                .into(holder.SIMG);



    }


    @Override
    public int getItemCount() {
        return slideritem.size();
    }

    public  class sliderviewholder extends  RecyclerView.ViewHolder{
       private ImageView SIMG;

        public sliderviewholder(@NonNull View itemView) {
            super(itemView);
            SIMG = itemView.findViewById(R.id.IMAGESLIDER);
        }
    }
}
