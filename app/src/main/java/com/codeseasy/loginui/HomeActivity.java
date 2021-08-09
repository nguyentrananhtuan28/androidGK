package com.codeseasy.loginui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import com.codeseasy.loginui.ClassModel.Book;
import com.codeseasy.loginui.Data.BookDAO;

public class HomeActivity extends AppCompatActivity {
  private BookDAO bookDAO = new BookDAO(this);
  private ListView listView;
  private Object Books;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    listView = (ListView) findViewById(R.id.listView);
    LoadListView();

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> a, View v, int position, long id) {
        Object o = listView.getItemAtPosition(position);
        Book p = (Book) o;

        Intent myIntent = new Intent(HomeActivity.this, DetailPActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("PrProductID", p.getId());
        myIntent.putExtra("Pcurrent", bundle);
        startActivity(myIntent);
        LoadListView();
      }
    });
  }
  public void LoadListView(){
    List<Book> Books = bookDAO.getAllBook();
    Toast toast =  Toast.makeText(HomeActivity.this, "Size : "+ Books.size(), Toast.LENGTH_SHORT);
    toast.show();
    listView.setAdapter(new CustomListAdapter(this, Books));
  }
  public void onBack(View view) {
    Intent i = new Intent(HomeActivity.this, MainActivity.class);
    startActivity(i);
  }

  public void onAdd(View view) {
    Intent i = new Intent(HomeActivity.this, AddPActivity.class);
    startActivity(i);
    LoadListView();
  }
}