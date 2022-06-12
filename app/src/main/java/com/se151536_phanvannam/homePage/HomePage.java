package com.se151536_phanvannam.homePage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.se151536_phanvannam.MainActivity;
import com.se151536_phanvannam.R;
import com.se151536_phanvannam.notifications.NotificationActivity;
import com.se151536_phanvannam.pages.ProfilePage;
import com.se151536_phanvannam.pages.UpdateSoonPage;
import com.se151536_phanvannam.recycle.RecycleViewActivity;

public class HomePage extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CAMERA = 0;
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

        findViewById(R.id.play_music_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMusic();
            }
        });

        findViewById(R.id.call_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall();
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

    private void insertDummyCameraWrapper() {
        int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.CAMERA);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS},
                    PERMISSION_REQUEST_CAMERA);
            return;
        }
    }

    private void playMusic() {
        Intent intent = new Intent(HomePage.this, NotificationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CAMERA:
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    insertDummyCameraWrapper();
                } else {
                    Toast.makeText(HomePage.this, "CAMERA FAILED", Toast.LENGTH_SHORT).show();
                }
                return;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void makeCall() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel: 0334472199"));
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }
}

