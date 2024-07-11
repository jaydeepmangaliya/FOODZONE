package com.example.foodzone.Activitys;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.foodzone.Helper.ManagmentCart;
import com.example.foodzone.Model.Foods;
import com.example.foodzone.databinding.ActivityDetailBinding;

public class DetailActivity extends databaseActivity {
    ActivityDetailBinding binding;
   private Foods object;
   private ManagmentCart managmentCart;
   private int num=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        getintentExtra();
        setVariable();


    }

    private void setVariable() {
        managmentCart = new ManagmentCart(this);
        binding.DBACKIMG.setOnClickListener(view -> finish());
        Glide.with(this)
                .load(object.getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(60))
                .into(binding.pic);
        binding.pricetext.setText("RS."+object.getPrice());
        binding.titletext.setText(object.getTitle());
        binding.descriptiontext.setText(object.getDescription());
        binding.ratingtxt.setText(object.getStart()+"Rating");
        binding.ratingBar.setRating((float) object.getStart());
        binding.Tpricetxt.setText(("RS."+num*object.getPrice()));

        binding.plusbtn.setOnClickListener(view -> {
            num = num +1;
            binding.numtxt.setText(num+"");
            binding.Tpricetxt.setText("RS."+(num*object.getPrice()));
        });

        binding.subtractionbtn.setOnClickListener(view -> {

            if(num>1){
                num = num-1;
                binding.numtxt.setText(num+"");
                binding.Tpricetxt.setText("RS."+(num*object.getPrice()));
            }

        });
        binding.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            object.setNumberInCart(num);
            managmentCart.insertFood(object);
                //Toast.makeText(DetailActivity.this, "ADDED", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getintentExtra() {
        object = (Foods) getIntent().getSerializableExtra("object");
    }
}