package com.codeseasy.loginui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import static com.codeseasy.loginui.MainActivity.ListUser;
public class SignUpActivity  extends AppCompatActivity {
    private TextView username,pass,Rpass,name,studentid;
    private TextView Back;
    private Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

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
        boolean isExits = false;
        if (pass.getText().toString().equals(Rpass.getText().toString())) {
            User user = new User();
            user.setUserName(username.getText().toString());
            user.setPass(pass.getText().toString());
            user.setName(name.getText().toString());
            user.setStudenID(studentid.getText().toString());
            for (User u : ListUser) {
                if ((u.getUserName().equals(user.getUserName()))) {
                    isExits = true;
                    toast =  Toast.makeText(SignUpActivity.this, "Tài khoản đã tồn tại !", Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                }
            }
            if (!isExits) {
                ListUser.add(user);
                toast =  Toast.makeText(SignUpActivity.this, "Đăng kí thành công !", Toast.LENGTH_SHORT);
                toast.show();

                Intent i = new Intent( SignUpActivity.this, MainActivity.class);
                startActivity(i);
            }
        }else{
            toast =  Toast.makeText(SignUpActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}