package com.example.splash_ui;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.splash_ui.PetAdapter.ShopAdadpter.DynamicRVAdapter;
import com.example.splash_ui.PetAdapter.ShopAdadpter.DynamicRVModel;
import com.example.splash_ui.PetAdapter.ShopAdadpter.LoadMoreDynamicRVInterface;
import com.example.splash_ui.PetAdapter.ShopAdadpter.StaticRVAdapter;
import com.example.splash_ui.PetAdapter.ShopAdadpter.StaticRVModel;
import com.example.splash_ui.PetAdapter.ShopAdadpter.UpdateRecyclerView;
import com.example.splash_ui.confirm.ConfirmPurchase;
import com.example.splash_ui.confirm.UpdateSelectedItems;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroceryShop extends AppCompatActivity implements UpdateRecyclerView {



    ImageView back,cart;
    private RecyclerView recycleview,drv;

    //own created method

    private StaticRVAdapter staticRVAdapter;
    Activity activity;

    ArrayList<DynamicRVModel> items=new ArrayList<>();
    DynamicRVAdapter dynamicRVAdapter;

    Context context;
    UpdateSelectedItems updateSelectedItems;

    ArrayList<DynamicRVModel> selectedItems = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_shop);

        ArrayList<StaticRVModel> item=new ArrayList<>();



        back=findViewById(R.id.back);
        cart=findViewById(R.id.goto_cart);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GroceryShop.this,profile.class));

            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroceryShop.this, ConfirmPurchase.class);
                intent.putExtra("selectedItems", selectedItems);
                startActivity(intent);
            }
        });



        item.add(new StaticRVModel(R.drawable.pet_food_icon,"Food"));
        item.add(new StaticRVModel(R.drawable.pet_medicine_icon,"Medicine"));
        item.add(new StaticRVModel(R.drawable.pet_accessories_icon,"Accessories"));

        recycleview=findViewById(R.id.rv1);
        staticRVAdapter=new StaticRVAdapter(item,activity, this);
        recycleview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recycleview.setAdapter(staticRVAdapter);

        // items.add(new DynamicRVModel(()));
      items.add(new DynamicRVModel(R.drawable.food_dog_pedigree_small_dog,"PEDIGREE small dog","250 gm",100));
      items.add(new DynamicRVModel(R.drawable.food_cat_temptations_chicken_flavour,"Temptation Flavour(Cat food)","250 gm",100));
      items.add(new DynamicRVModel(R.drawable.food_bird_wild_bird,"Wild Bird","250 gm",100));


      drv=findViewById(R.id.rv2);


       drv.setLayoutManager((new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)));



     dynamicRVAdapter=new DynamicRVAdapter(items,context,updateSelectedItems);

    drv.setAdapter(dynamicRVAdapter);


//
//        dynamicRVAdapter.setLoadMoreDynamicRVInterface(new LoadMoreDynamicRVInterface() {
//            @Override
//            public void onLoadMore(){
//                //reresh after every 10 items
//
//                if(items.size()<=10){
//                    item.add(null);
//                    dynamicRVAdapter.notifyItemInserted(items.size()-1);
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                           items.remove(items.size()-1);
//                           dynamicRVAdapter.notifyItemRemoved(items.size());
//
//                           int index=items.size();
//                           int end=index+10;
//
//                           for(int i=index;i<end;i++){
//                               String title= UUID.randomUUID().toString();
////                               String price=UUID.randomUUID().toString();
////                               String weight=UUID.randomUUID().toString();
////                               int image=R.drawable.food_bird_wild_bird;
//                               DynamicRVModel item=new DynamicRVModel(title);
//                               items.add(item);
//
//                           }
//                           dynamicRVAdapter.notifyDataSetChanged();
//                           dynamicRVAdapter.setLoaded();
//
//                        }
//                    } , 4);
//                }
//
//                else{
//                    Toast.makeText(GroceryShop.this,"completed",Toast.LENGTH_SHORT);
//                }
//
//            }
//        });

    }

//    @Override
//    public void onLoadMore(int position) {
//
//    }

    @Override
    public void callback(int position, ArrayList<DynamicRVModel> items) {
  dynamicRVAdapter=new DynamicRVAdapter(items,context,updateSelectedItems);
  dynamicRVAdapter.notifyDataSetChanged();
   drv.setAdapter(dynamicRVAdapter);


    }
}