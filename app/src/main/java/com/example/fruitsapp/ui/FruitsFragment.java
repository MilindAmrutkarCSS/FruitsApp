package com.example.fruitsapp.ui;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fruitsapp.viewmodel.FruitViewModel;
import com.example.fruitsapp.interfaces.IFruitListener;
import com.example.fruitsapp.R;
import com.example.fruitsapp.adapter.FruitAdapter;
import com.example.fruitsapp.model.Fruit;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FruitsFragment extends Fragment implements IFruitListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private FruitViewModel fruitViewModel;
    private Context context;
    List<Fruit> fruitList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_products, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);

        FruitAdapter fruitAdapter = new FruitAdapter(context, fruitList, this);
        recyclerView.setAdapter(fruitAdapter);

        fruitViewModel = ViewModelProviders.of(this).get(FruitViewModel.class);
        fruitViewModel.getAllFruits().observe(this, new Observer<List<Fruit>>() {
                    @Override
                    public void onChanged(List<Fruit> fruits) {
                        fruitList = fruits;
                        fruitAdapter.setFruitsData(fruitList);
                    }
                });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public void onAddToCart(Fruit fruit) {
        int fruitCount = fruit.getCount();
        if(fruitCount != 0) {
            fruit.setCount(fruitCount - 1);
            fruit.setCartCount(fruit.getCartCount() + 1);
            fruit.setInCart(true);
        }

        fruitViewModel.update(fruit);
    }
}
