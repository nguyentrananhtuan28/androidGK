package com.codeseasy.loginui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.codeseasy.loginui.ClassModel.User;
import com.codeseasy.loginui.Data.UserDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<User> ListUser = new ArrayList<>();
    TextView userLogin, passLogin,errorLogin,txtDangKy;
    TextView btnlogin;
    private UserDAO userDAO;
    private Intent myInten= new Intent(this, SignUpActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userDAO = new UserDAO(this);

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

                    if (userDAO.CheckLogin(user,pass)){
                        Intent listItem=new Intent( MainActivity.this, HomeActivity.class);
                        startActivity(listItem);
                    }else{
                        errorLogin.setText("Sai tài khoản hoặc mật khẩu!");
                    }
                }
            }
        });
    }
}