package com.codeseasy.loginui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.codeseasy.loginui.ClassModel.User;
import com.codeseasy.loginui.Data.UserDAO;

import static com.codeseasy.loginui.MainActivity.ListUser;
public class SignUpActivity  extends AppCompatActivity {
    private TextView username,pass,Rpass,name,studentid;
    private TextView Back;
    private Toast toast;
    private UserDAO userDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        userDAO = new UserDAO(this);
        username = (TextView) findViewById(R.id.UserSignUp);
        pass = (TextView) findViewById(R.id.PassSignUp);
        Rpass = (TextView) findViewById(R.id.RPassSignUp);
        name = (TextView) findViewById(R.id.NameSignUp);
        studentid = (TextView) findViewById(R.id.StudentNumberSignUp);

        Back = (TextView) findViewById(R.id.btnBack);
        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent( SignUpActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void Clicksignup(View view) {
        if (username.getText().toString().length()>0 && pass.getText().toString().length()>0 && Rpass.getText().toString().length()>0){
            //Mật khẩu không trùng khớp
            if (pass.getText().toString().equals(Rpass.getText().toString())) {
                User user = new User();
                user.setUserName(username.getText().toString());
                user.setPass(pass.getText().toString());
                user.setName(name.getText().toString());
                if (userDAO.CheckUserExits(username.getText().toString())){
                    Toast toast =  Toast.makeText(SignUpActivity.this, "Tài khoản đã tồn tại !", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    if (userDAO.addUser(user)){
                        Toast toast =  Toast.makeText(SignUpActivity.this, "Đăng kí thành công !", Toast.LENGTH_SHORT);
                        toast.show();

                        Intent i = new Intent( SignUpActivity.this, MainActivity.class);
                        startActivity(i);
                    }else{
                        Toast toast =  Toast.makeText(SignUpActivity.this, "Có lỗi xảy ra", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }else{
                Toast toast =  Toast.makeText(SignUpActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT);
                toast.show();
            }
        }else{
            Toast toast =  Toast.makeText(SignUpActivity.this, "Không được bỏ trống!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}