package com.se151536_phanvannam.homePage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.se151536_phanvannam.MainActivity;
import com.se151536_phanvannam.R;
import com.se151536_phanvannam.menu.MainMenu;
import com.se151536_phanvannam.pages.ProfilePage;
import com.se151536_phanvannam.pages.UpdateSoonPage;
import com.se151536_phanvannam.recycle.RecycleViewActivity;

public class HomePage extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        TextView welcomeUser = findViewById(R.id.welcomeUser);

        if (getIntent() != null) {
            String username = getIntent().getStringExtra("username");
            welcomeUser.setText("WELCOME " + username);
        }

        LinearLayout menuBtn = findViewById(R.id.more_option);

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, RecycleViewActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_setting:
            case R.id.menu_faq:
                Intent intent = new Intent(HomePage.this, UpdateSoonPage.class);
                startActivity(intent);
                return true;
            case R.id.menu_profile:
                showProfilePage();
                return true;
            case R.id.log_out:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showProfilePage() {
        Intent intent = new Intent(HomePage.this, ProfilePage.class);
        startActivity(intent);
    }

    private void logout() {
        Intent intent = new Intent(HomePage.this, MainActivity.class);
        intent.putExtra("logoutMsg", "LOGGED OUT");
        setResult(123, intent);
        finish();
    }
}
