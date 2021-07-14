package com.codeseasy.loginui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
  private List<ComicBooks> image_details;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);


    image_details = getListData();
    final ListView listView = (ListView) findViewById(R.id.listView);
    listView.setAdapter(new CustomListAdapter(this, image_details));
    image_details = getListData();
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> a, View v, int position, long id) {
        ComicBooks p = image_details.get(position);
        Intent myIntent = new Intent(HomeActivity.this, DetailPActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Pname", p.getCountryName());
        bundle.putString("PImage", p.getFlagName());
        bundle.putInt("PPrice", p.getPopulation());
        myIntent.putExtra("Pcurrent", bundle);
        startActivity(myIntent);
      }
    });
  }

  private  List<ComicBooks> getListData() {
    List<ComicBooks> list = new ArrayList<ComicBooks>();
    ComicBooks m1 = new ComicBooks("Doremon tập 1", "m1", 980);
    ComicBooks m2 = new ComicBooks("Doremon tập 2", "m2", 320);
    ComicBooks m3 = new ComicBooks("Doremon tập 3", "m3", 142);
    ComicBooks m4 = new ComicBooks("Doremon tập 4", "m4", 360);
    ComicBooks m5 = new ComicBooks("Doremon tập 5", "m5", 100);
    ComicBooks m6 = new ComicBooks("Doremon tập 6", "m6", 242);
    ComicBooks m7 = new ComicBooks("Doremon tập 7", "m7", 125);


    list.add(m1);
    list.add(m2);
    list.add(m3);
    list.add(m4);
    list.add(m5);
    list.add(m6);
    list.add(m7);

    return list;
  }
}
