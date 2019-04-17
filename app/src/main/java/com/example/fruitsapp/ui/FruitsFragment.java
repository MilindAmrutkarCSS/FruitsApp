package com.example.fruitsapp.ui;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fruitsapp.R;
import com.example.fruitsapp.adapter.FruitAdapter;
import com.example.fruitsapp.interfaces.IFruitListener;
import com.example.fruitsapp.model.Fruit;
import com.example.fruitsapp.viewmodel.FruitViewModel;

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
        if (fruitCount != 0) {
            fruit.setCount(fruitCount - 1);
            fruit.setCartCount(fruit.getCartCount() + 1);
            fruit.setInCart(true);
        }

        fruitViewModel.update(fruit);
    }

    @Override
    public void onItemClick(Fruit fruit) {
        Fruit fruit1 = fruit;
        Dialog dialog = new Dialog(context, R.style.Dialog);


        dialog.setContentView(R.layout.custom_dialog_main);
        dialog.setTitle("Edit Changes");

        EditText etFruit = dialog.findViewById(R.id.etFruit);
        EditText etFruitCount = dialog.findViewById(R.id.etFruitCount);
        Button btnAddFruit = dialog.findViewById(R.id.btnAddFruit);

        btnAddFruit.setText("Edit Changes");
        etFruit.setText(fruit1.getName());
        etFruitCount.setText(Integer.toString(fruit1.getCount()));


        btnAddFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etFruit.getText().toString()) &&
                        !TextUtils.isEmpty(etFruitCount.getText().toString())) {
                    if (Integer.valueOf(etFruitCount.getText().toString()) <= 50) {

                        fruit1.setName(etFruit.getText().toString());
                        fruit1.setCount(Integer.valueOf(etFruitCount.getText().toString()));
                        fruitViewModel.update(fruit1);
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "Fruit count cannot be greater than 50", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        dialog.show();
    }
}
