package com.codeseasy.loginui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Tools.setSystemBarLight(this);
        Tools.setSystemBarColor(this,R.color.white);
    }

    public void ClickDangNhap(View view) {
        TextView userLogin = findViewById(R.id.UserLogin);
        TextView passLogin = findViewById(R.id.PassLogin);
        TextView errorLogin = findViewById(R.id.ErrorLogin);

        errorLogin.setText("");
        if (userLogin.getText().toString().trim().equals("")){
            userLogin.requestFocus();
        }
        else if (passLogin.getText().toString().trim().equals("")){
            passLogin.requestFocus();
        }
        else{
            //SQL xử lý
            if (userLogin.getText().toString().trim().equals("admin") && passLogin.getText().toString().trim().equals("admin")){
                setContentView(R.layout.activity_home);
                TextView UserLogin = findViewById(R.id.UserLogin);
                    UserLogin.setText(userLogin.getText());
                TextView PassLogin = findViewById(R.id.PassLogin);
                    PassLogin.setText(passLogin.getText());
            }else{
                errorLogin.setText("Sai tài khoản hoặc mật khẩu!");
            }
        }
    }

    public void ClickDangXuat(View view) {
        setContentView(R.layout.activity_login);
    }
}
