package com.example.fruitsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fruitsapp.interfaces.IFruitListener;
import com.example.fruitsapp.R;
import com.example.fruitsapp.model.Fruit;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.FruitHolder> {

    private Context context;
    private List<Fruit> fruitList;
    IFruitListener fruitListener;

    public FruitAdapter(Context context, List<Fruit> fruitList, IFruitListener iFruitListener) {
        this.context = context;
        this.fruitList = fruitList;
        this.fruitListener = iFruitListener;
    }

    @NonNull
    @Override
    public FruitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.product_item_list,
                        parent,
                        false);

        return new FruitHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitHolder holder, int position) {
        final Fruit fruit = fruitList.get(position);
        holder.tvFruitName.setText(fruit.getName());
        holder.tvFruitCount.setText(String.valueOf(fruit.getCount()));

        holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fruitListener.onAddToCart(fruit);
            }
        });

    }

    @Override
    public int getItemCount() {
        return fruitList.size();
    }

    public void setFruitsData(List<Fruit> fruitList) {
        this.fruitList = fruitList;
        notifyDataSetChanged();
    }

    class FruitHolder extends RecyclerView.ViewHolder {

        private TextView tvFruitName;
        private TextView tvFruitCount;
        private Button btnAddToCart;

        public FruitHolder(@NonNull View itemView) {
            super(itemView);
            tvFruitName = itemView.findViewById(R.id.tvFruit);
            tvFruitCount = itemView.findViewById(R.id.tvFruitsCounts);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);

        }
    }




}
