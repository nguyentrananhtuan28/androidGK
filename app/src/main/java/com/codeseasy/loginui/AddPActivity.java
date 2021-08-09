package com.codeseasy.loginui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codeseasy.loginui.ClassModel.Book;
import com.codeseasy.loginui.Data.BookDAO;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddPActivity extends AppCompatActivity {
    private TextView bName,bprice;
    private ImageView bImage;
    private BookDAO bookDAO;
    private HomeActivity homeActivity = new HomeActivity();
    private Book book;
    private ImageButton anh;
    int REQUEST_CODE_fodel = 123;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        bookDAO = new BookDAO(this);
        book = new Book();

        bName = (TextView) findViewById(R.id.PName_add);
        bprice = (TextView) findViewById(R.id.PPrice_add);
        bImage = (ImageView) findViewById(R.id.image_add);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode == REQUEST_CODE_fodel && resultCode == RESULT_OK && data != null){
           Uri uri = data.getData();
           try{
           InputStream inputStream = getContentResolver().openInputStream(uri);
               Bitmap bimap  = BitmapFactory.decodeStream(inputStream);
               bImage.setImageBitmap(bimap);
           }catch (FileNotFoundException e){
               e.printStackTrace(); }
       }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onBack(View view) {
        finish();
    }

    public void onSave(View view) {
        book.setBookName(bName.getText().toString());
        book.setPrice(Integer.parseInt(bprice.getText().toString()));
        book.setImage(getImageBitmap(bImage));
        bookDAO.addBook(book);

        homeActivity.LoadListView();
        finish();
    }

    public void clickanh(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,REQUEST_CODE_fodel);
    }
    public static byte[] getImageBitmap(ImageView img) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] imageProduct = byteArrayOutputStream.toByteArray();
        return imageProduct;
    }

}
