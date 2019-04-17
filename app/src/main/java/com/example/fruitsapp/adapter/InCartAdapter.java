package com.example.fruitsapp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.fruitsapp.R;
import com.example.fruitsapp.interfaces.IInCartListener;
import com.example.fruitsapp.model.Fruit;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InCartAdapter extends RecyclerView.Adapter<InCartAdapter.InCartHolder> {

    private Context context;
    private List<Fruit> fruitList;
    IInCartListener iInCartListener;

    public InCartAdapter(Context context, List<Fruit> fruitList, IInCartListener iInCartListener) {
        this.context = context;
        this.fruitList = fruitList;
        this.iInCartListener = iInCartListener;
    }

    @NonNull
    @Override
    public InCartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.items_in_cart,
                        parent,
                        false);

        return new InCartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InCartHolder holder, int position) {
        final Fruit fruit = fruitList.get(position);
        holder.tvFruitName.setText(fruit.getName());
        holder.tvFruitCount.setText(String.valueOf(fruit.getCartCount()));

        holder.ibRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iInCartListener.deleteFromCart(fruit);
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

    class InCartHolder extends RecyclerView.ViewHolder {

        private TextView tvFruitName;
        private TextView tvFruitCount;
        private ImageButton ibRemoveButton;

        public InCartHolder(@NonNull View itemView) {
            super(itemView);
            tvFruitName = itemView.findViewById(R.id.tvFruit);
            tvFruitCount = itemView.findViewById(R.id.tvFruitCount);
            ibRemoveButton = itemView.findViewById(R.id.ibRemoveItem);

        }
    }


}

