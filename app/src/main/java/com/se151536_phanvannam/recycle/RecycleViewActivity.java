package com.se151536_phanvannam.recycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;

import com.se151536_phanvannam.R;
import com.se151536_phanvannam.adapter.Adapter;
import com.se151536_phanvannam.menu.MoreOptions;

import java.util.ArrayList;

public class RecycleViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view);
        RecyclerView recyclerView = findViewById(R.id.recycle_view);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        ArrayList<MoreOptions> moreOptionsList = new ArrayList<>();
        moreOptionsList.add(new MoreOptions("Cash", "123456789"));
        moreOptionsList.add(new MoreOptions("Money", "123456789"));
        moreOptionsList.add(new MoreOptions("Gold", "123456789"));
        moreOptionsList.add(new MoreOptions("People", "123456789"));

        Adapter adapter = new Adapter(moreOptionsList, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
