package com.example.foodzone.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.foodzone.Adapters.CategoryAdapter;
import com.example.foodzone.Adapters.ImageSliderAdapter;
import com.example.foodzone.Model.Category;
import com.example.foodzone.Model.ImageSlider;
import com.example.foodzone.R;
import com.example.foodzone.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;

public class MainActivity extends databaseActivity {
ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initcategory();
        setvariable();
        //initbanner();

        ArrayList<ImageSlider>itemss = new ArrayList<>();
        itemss.add(new ImageSlider(R.drawable.banner));
        itemss.add(new ImageSlider(R.drawable.bbenner));
        itemss.add(new ImageSlider(R.drawable.banner2));
        ImageSliderAdapter adapter = new ImageSliderAdapter(itemss);
        binding.viewPager22.setAdapter(adapter);
        if (this instanceof MainActivity) {

           binding.BUTTOMMENU.setItemSelected(R.id.home,true);

        }

    }


    private void setvariable() {
        binding.BUTTOMMENU.setItemSelected(R.id.home,true);
        binding.BUTTOMMENU.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                if(i == R.id.cart){
                    startActivity(new Intent(MainActivity.this,cartActivity.class));

                }
                if (i == R.id.profile){
                    startActivity(new Intent(MainActivity.this,ProfileActivity.class));

                }


            }


        });



    }


    private void initcategory() {

        DatabaseReference databaseReference = database.getReference("Category");
        binding.CATEGORYPROSSES.setVisibility(View.VISIBLE);
        ArrayList<Category> list  = new ArrayList<>();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue : snapshot.getChildren()){
                        list.add(issue.getValue(Category.class));
                    }
                    if(list.size()>0){
                        binding.categoryrecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,3));
                        binding.categoryrecyclerView.setAdapter(new CategoryAdapter(list));
                    }
                    binding.CATEGORYPROSSES.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.BUTTOMMENU.setItemSelected(R.id.home, true);
    }
}