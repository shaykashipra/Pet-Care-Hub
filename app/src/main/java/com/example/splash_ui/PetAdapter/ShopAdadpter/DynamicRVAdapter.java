package com.example.splash_ui.PetAdapter.ShopAdadpter;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splash_ui.R;
import com.example.splash_ui.confirm.UpdateSelectedItems;
import com.example.splash_ui.confirm.implementInterface_UpdateSelectedItems;

import java.util.ArrayList;

//class LoadingViewHolder extends RecyclerView.ViewHolder{
//   public ProgressBar progressBar;
//
//    public LoadingViewHolder(@NonNull View itemView) {
//        super(itemView);
//
//        progressBar=itemView.findViewById(R.id.progress_bar);
//    }
//}

//class ItemViewHolder extends RecyclerView.ViewHolder {
//    public TextView tv1, tv2, tv3;
//
//    public ItemViewHolder(@NonNull View itemView) {
//        super(itemView);
//        tv1 = itemView.findViewById(R.id.food_title);
//        tv2 = itemView.findViewById(R.id.food_weight);
//        tv3 = itemView.findViewById(R.id.food_price);
//    }
//}
public class DynamicRVAdapter extends RecyclerView.Adapter<DynamicRVAdapter.DynamicViewHolder>{
    Application activity2;
    Context context;
    String name,price_val;
    int image;
    double price;

    UpdateSelectedItems updateSelectedItems;

    private final int VIEW_TYPE_ITEM=0,VIEW_TYPE_LOADING=1;

    int row_idx=-1;

    /*boolean isLoading;
    Activity activity;*/
   // private final LoadMoreDynamicRVInterface loadMoreDynamicRVInterface;

    ArrayList<DynamicRVModel> items;
    int VisibleThreshold=5;
    int lastVisibleItem,totalItemCount;

    //creating constructor


    public DynamicRVAdapter( ArrayList<DynamicRVModel> items,Context context,UpdateSelectedItems updateSelectedItems){
        this.items = items;
        this.context=context;
        this.updateSelectedItems=updateSelectedItems;
    //    this.loadMoreDynamicRVInterface=loadMoreDynamicRVInterface;

      /*  final LinearLayoutManager linearLayoutManager=(LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount=linearLayoutManager.getItemCount();
                lastVisibleItem=linearLayoutManager.findLastVisibleItemPosition();

                if(!isLoading&& totalItemCount<=(lastVisibleItem+VisibleThreshold)){
                    if(loadMoreDynamicRVInterface!=null){
                        loadMoreDynamicRVInterface.onLoadMore();
                        isLoading=true;
                    }
                }
            }
        });*/
    }

   /* @Override
    public int getItemViewType(int position) {
        return items.get(position)==null? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }*/

    /*public void setLoadMoreDynamicRVInterface(LoadMoreDynamicRVInterface loadMoreDynamicRVInterface){
        this.loadMoreDynamicRVInterface=loadMoreDynamicRVInterface;
    }*/

    @NonNull
    @Override
    public DynamicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_rv_items_accessories,parent,false);
            return new DynamicViewHolder(v);



    }

    @Override
    public void onBindViewHolder(@NonNull DynamicViewHolder holder, int position) {
 /* if(holder instanceof ItemViewHolder){
      DynamicRVModel item=items.get(position);
      ItemViewHolder viewholder=(ItemViewHolder) holder;
      viewholder.tv1.setText(items.get(position).getName());
  }

  else if(holder instanceof LoadingViewHolder){
      LoadingViewHolder loadingViewHolder=(LoadingViewHolder)   holder;

  }
*/


        DynamicRVModel dynamicRVModel=items.get(position);
        holder.iv1.setImageResource(dynamicRVModel.getImage());
        holder.tv1.setText(dynamicRVModel.getName());
        holder.tv2.setText(dynamicRVModel.getWeight());
        holder.tv3.setText(String.valueOf(dynamicRVModel.getPrice()));
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inside the click listener it will get the name,price
                //it will push variables to rv of confirm order activity

                name=dynamicRVModel.getName();
                price=dynamicRVModel.getPrice();
                image=dynamicRVModel.getImage();


               // updateSelectedItems.additems(image,name,price);


               if (updateSelectedItems!= null)
               ((UpdateSelectedItems) implementInterface_UpdateSelectedItems.getCurrentContext() ).additems(image,name,price);

                holder.add.setVisibility(View.INVISIBLE);
                holder.check.setVisibility(View.VISIBLE);

            }
        });





    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /*public void setLoaded(){
        isLoading=false;
    }*/

    public class DynamicViewHolder extends RecyclerView.ViewHolder{
          ImageView iv1;
         public TextView tv1,tv2,tv3;
          ImageView add,check;

          ConstraintLayout constraintLayout;
        public DynamicViewHolder(@NonNull View itemView) {
            super(itemView);

            iv1=itemView.findViewById((R.id.food_image));
            tv1=itemView.findViewById((R.id.food_title));
            tv2=itemView.findViewById((R.id.food_weight));
            tv3=itemView.findViewById((R.id.food_price));
            constraintLayout=itemView.findViewById((R.id.accesories_constraintlayout));

            add=itemView.findViewById(R.id.add_cart);
            check=itemView.findViewById(R.id.done);

        }
    }
}
