package com.example.foodzone.Activitys;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodzone.Adapters.FoodListAdapter;
import com.example.foodzone.Model.Foods;
import com.example.foodzone.databinding.ActivityFoodListBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FoodListActivity extends databaseActivity {
ActivityFoodListBinding binding;
private RecyclerView.Adapter adapter;
private  int categoryId;
private  String categoryName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityFoodListBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
             getIntentExtra();
             itinList();

    }

    private void itinList() {

        DatabaseReference reference =database.getReference("Foods");
        binding.LAProgressbar.setVisibility(View.VISIBLE);
        ArrayList<Foods> list = new ArrayList<>();
        Query query = reference.orderByChild("CategoryId").equalTo(categoryId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               if(snapshot.exists()){
                   for(DataSnapshot issue : snapshot.getChildren()){
                       list.add(issue.getValue(Foods.class));
                   }
                   if(list.size()>0){
                       binding.listView.setLayoutManager(new LinearLayoutManager(FoodListActivity.this, LinearLayoutManager.VERTICAL,false));
                 binding.listView.setAdapter(new FoodListAdapter(list));
                   }
                   binding.LAProgressbar.setVisibility(View.GONE);
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getIntentExtra() {
        categoryId  = getIntent().getIntExtra("CategoryId",0);
        categoryName = getIntent().getStringExtra("CategoryName");

        binding.LAtitle.setText(categoryName);
        binding.Lbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}