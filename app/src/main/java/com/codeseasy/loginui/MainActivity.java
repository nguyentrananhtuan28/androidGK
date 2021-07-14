package com.codeseasy.loginui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<User> ListUser = new ArrayList<>();
    TextView userLogin, passLogin,errorLogin,txtDangKy;
    TextView btnlogin;
    private Intent myInten= new Intent(this, SignUpActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ListUser.add(new User("admin","admin"));

        userLogin = (TextView) findViewById(R.id.UserLogin);
        passLogin = (TextView) findViewById(R.id.PassLogin);
        errorLogin = (TextView) findViewById(R.id.ErrorLogin);
        txtDangKy = (TextView) findViewById(R.id.txtDangKy);
        btnlogin = (TextView) findViewById(R.id.btnlogin);
        txtDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( MainActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isLogin = false;
                errorLogin.setText("");
                if (userLogin.getText().toString().trim().equals("")){
                    userLogin.requestFocus();
                }
                else if (passLogin.getText().toString().trim().equals("")){
                    passLogin.requestFocus();
                }
                else{
                    String user = userLogin.getText().toString();
                    String pass = passLogin.getText().toString();
                    for (User u : ListUser) {
                        if (((u.getUserName().trim().equals(user.trim()))&&(u.getPass().trim().equals(pass.trim())))) {
                            isLogin = true;
                            Intent listItem=new Intent( MainActivity.this, HomeActivity.class);
                            startActivity(listItem);
                        }
                    }
                    if(!isLogin){
                        errorLogin.setText("Sai tài khoản hoặc mật khẩu!");
                    }
                }
            }
        });
    }
}