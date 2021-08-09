package com.codeseasy.loginui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.codeseasy.loginui.ClassModel.Book;
import com.codeseasy.loginui.Data.BookDAO;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class EditPActivity extends AppCompatActivity {
    private TextView prName,prDesc,prPrice;
    private ImageView prImage;
    private DetailPActivity detailPActivity = new DetailPActivity();
    private BookDAO bookDAO;
    private Book book;
    int REQUEST_CODE_fodel = 123;

    public static String getImageBiMao(ImageView bImage){
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        bookDAO = new BookDAO(this);

        prName = (TextView) findViewById(R.id.PName_edit);
        prPrice = (TextView) findViewById(R.id.PPrice_edit);
        prImage = (ImageView) findViewById(R.id.image_edit);

        Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("PEdit");
        int PrID = packageFromCaller.getInt("PrProductID");
        book = bookDAO.getBookByID(PrID);

        Toast toast =  Toast.makeText(EditPActivity.this,"ID:"+ book.getId(), Toast.LENGTH_SHORT);
        toast.show();
        prName.setText(book.getBookName());
        prPrice.setText(String.valueOf(book.getPrice()));

        byte[] image = book.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        prImage.setImageBitmap(bitmap);
    }
    private void LoadProduct(){
    }
    public void onSave(View view) {
        book.setBookName(prName.getText().toString());
        book.setPrice(Integer.parseInt(prPrice.getText().toString()));
        book.setImage(AddPActivity.getImageBitmap(prImage));
        bookDAO.Update(book);

        Toast toast =  Toast.makeText(EditPActivity.this, "Sửa thành công!", Toast.LENGTH_SHORT);
        toast.show();
        detailPActivity.LoadDetail();
        finish();
    }
    public void onBack(View view) {
        finish();
    }

    public void clickanh(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,REQUEST_CODE_fodel);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_fodel && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try{
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bimap  = BitmapFactory.decodeStream(inputStream);
                prImage.setImageBitmap(bimap);
            }catch (FileNotFoundException e){
                e.printStackTrace(); }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
