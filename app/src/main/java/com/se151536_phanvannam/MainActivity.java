package com.se151536_phanvannam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.se151536_phanvannam.dto.UserManagementDAO;
import com.se151536_phanvannam.homePage.HomePage;
import com.se151536_phanvannam.homePage.SignUpPage;
import com.se151536_phanvannam.users.Users;

public class MainActivity extends AppCompatActivity {
    private UserManagementDAO userManagementDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login();
    }

    private void login() {
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        Button loginBtn = findViewById(R.id.logInBtn);
        Button registerBtn = findViewById(R.id.sign_up_redirect_btn);
        CheckBox rememberMe = findViewById(R.id.remember_me_checkbox);
        loadData(username, password, rememberMe);
        userManagementDAO = new UserManagementDAO(MainActivity.this);

        //With username, password are "admin", login successful, else failed
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameInput = username.getText().toString();
                String passwordInput = password.getText().toString();
                boolean remember = rememberMe.isChecked();
                Users user = new Users(usernameInput, passwordInput);

                if (userManagementDAO.checkUser(user)) {
                    saveInfo(user.getUserName(), user.getPassword(), remember);
                    Toast.makeText(MainActivity.this, "LOGIN SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, HomePage.class);
                    intent.putExtra("username", username.getText().toString());
                    intent.putExtra("password", password.getText().toString());
                    startActivityForResult(intent, 123);
                } else {
                    Toast.makeText(MainActivity.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpPage.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123) {
            String logoutMsg = data.getStringExtra("logoutMsg");
            Toast.makeText(MainActivity.this, logoutMsg, Toast.LENGTH_SHORT).show();
        }
    }

    private void saveInfo(String username, String password, boolean check) {
        SharedPreferences pref = getSharedPreferences("information.save", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (check) {
            editor.putString("username", username);
            editor.putString("password", password);
            editor.putBoolean("check", check);
        } else {
            editor.clear();
        }
        editor.commit();
    }

    private void loadData(EditText username, EditText password, CheckBox checkBox) {
        SharedPreferences pref = getSharedPreferences("information.save", MODE_PRIVATE);
        boolean check = pref.getBoolean("check", false);
        if (check) {
            username.setText(pref.getString("username", ""));
            username.setText(pref.getString("password", ""));
            checkBox.setChecked(check);
        }
    }
}