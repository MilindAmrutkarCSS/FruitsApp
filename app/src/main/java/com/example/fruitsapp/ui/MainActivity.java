package com.example.fruitsapp.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fruitsapp.R;
import com.example.fruitsapp.adapter.DataAdapter;
import com.example.fruitsapp.model.Fruit;
import com.example.fruitsapp.viewmodel.FruitViewModel;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    FruitViewModel fruitViewModel;

    Fruit fruit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fruitViewModel = ViewModelProviders.of(this).get(FruitViewModel.class);

        DataAdapter productAdapter = new DataAdapter(this, getSupportFragmentManager(), 2);
        viewPager.setAdapter(productAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_fruit:
                Dialog dialog = new Dialog(this, R.style.Dialog);
                dialog.setContentView(R.layout.custom_dialog_main);
                dialog.setTitle("Add Fruit");

                EditText etFruit = dialog.findViewById(R.id.etFruit);
                EditText etFruitCount = dialog.findViewById(R.id.etFruitCount);
                Button btnAddFruit = dialog.findViewById(R.id.btnAddFruit);


                btnAddFruit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!TextUtils.isEmpty(etFruit.getText().toString()) &&
                                !TextUtils.isEmpty(etFruitCount.getText().toString())) {
                            if (Integer.valueOf(etFruitCount.getText().toString()) <= 50) {
                                fruit = new Fruit();
                                fruit.setName(etFruit.getText().toString());
                                fruit.setCount(Integer.valueOf(etFruitCount.getText().toString()));
                                fruitViewModel.insert(fruit);
                                dialog.dismiss();
                            } else {
                                Toast.makeText(MainActivity.this, "Fruit count cannot be greater than 50", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

                dialog.show();
        }
        return super.onOptionsItemSelected(item);

    }
}
