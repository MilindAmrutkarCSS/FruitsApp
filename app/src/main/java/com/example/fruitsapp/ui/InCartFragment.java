package com.example.fruitsapp.ui;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fruitsapp.viewmodel.FruitViewModel;
import com.example.fruitsapp.interfaces.IInCartListener;
import com.example.fruitsapp.R;
import com.example.fruitsapp.adapter.InCartAdapter;
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
public class InCartFragment extends Fragment implements IInCartListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private FruitViewModel fruitViewModel;
    private Context context;
    List<Fruit> fruitList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_in_cart, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);

        InCartAdapter inCartAdapter = new InCartAdapter(context, fruitList, this);
        recyclerView.setAdapter(inCartAdapter);

        fruitViewModel = ViewModelProviders.of(this).get(FruitViewModel.class);
        fruitViewModel.getAllFruitsFromCart().observe(this, new Observer<List<Fruit>>() {
            @Override
            public void onChanged(List<Fruit> fruits) {
                fruitList = fruits;
                inCartAdapter.setFruitsData(fruitList);
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
    public void deleteFromCart(Fruit fruit) {
        if((fruit.getCartCount()-1) == 0) {
            fruit.setInCart(false);
        }
        fruit.setCartCount(fruit.getCartCount()-1);
        fruit.setCount(fruit.getCount() + 1);
        fruitViewModel.update(fruit);
    }
}
