package com.codeseasy.loginui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailPActivity extends AppCompatActivity {
    TextView pName,pPrice;
    ImageView pImage;
    CustomListAdapter Custom = new CustomListAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pactivity);

        pName = (TextView) findViewById(R.id.textView_PName);
        pPrice = (TextView) findViewById(R.id.textView_PPrice);
        pImage = (ImageView) findViewById(R.id.imageView_detail);

        Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("Pcurrent");

        String Pname = packageFromCaller.getString("PName");
        String PImage = packageFromCaller.getString("PImage");
        int PPrice = packageFromCaller.getInt("PPrice");
        pName.setText(String.valueOf(Pname));
        pPrice.setText(String.valueOf(PPrice));
        pImage.setImageResource(R.mipmap.m1);
    }

    public void quaylai(View view) {
        setContentView(R.layout.activity_login);
    }
}


