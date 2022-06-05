package com.se151536_phanvannam.pages;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.se151536_phanvannam.MainActivity;
import com.se151536_phanvannam.R;

public class ProfilePage extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile);

        Button moreInfoBtn = findViewById(R.id.more_info);
        registerForContextMenu(moreInfoBtn);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_context_layout, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_facebook:
                Toast.makeText(ProfilePage.this, "FACEBOOK", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_insta:
                Toast.makeText(ProfilePage.this, "INSTAGRAM", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_twitter:
                Toast.makeText(ProfilePage.this, "TWITTER", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_tiktok:
                Toast.makeText(ProfilePage.this, "TIKTOK", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
