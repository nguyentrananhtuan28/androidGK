package com.codeseasy.loginui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.codeseasy.loginui.ClassModel.Book;
import com.codeseasy.loginui.Data.BookDAO;

public class DetailPActivity extends AppCompatActivity {
    private TextView productName, pDesc, productPrice;
    private ImageView pImage, ImageBack;
    private Button buttonDeleteProduct, buttonEditProduct;

    private HomeActivity homeActivity = new HomeActivity();
    private BookDAO bookDAO;
    private Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pactivity);

        bookDAO = new BookDAO(this);
        book = new Book();

        productName = (TextView) findViewById(R.id.txt_nameproduct);
        productPrice = (TextView) findViewById(R.id.txt_priceproduct);
        pImage = (ImageView) findViewById(R.id.image_edit);

        buttonDeleteProduct = (Button) findViewById(R.id.Btn_Save_edit);
        buttonEditProduct = (Button) findViewById(R.id.Btn_Back_edit);
        ImageBack = (ImageView) findViewById(R.id.imageBack);

        LoadDetail();

        buttonEditProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(DetailPActivity.this, EditPActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("PrProductID", book.getId());
                myIntent.putExtra("PEdit", bundle);
                startActivity(myIntent);
            }
        });

        buttonDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookDAO.deleteBook(book);
                Toast toast = Toast.makeText(DetailPActivity.this, "Xóa thành công", Toast.LENGTH_SHORT);
                toast.show();
                homeActivity.LoadListView();
                finish();
            }
        });

        ImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void LoadDetail() {
        Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("Pcurrent");
        int PrID = packageFromCaller.getInt("PrProductID");
        book = bookDAO.getBookByID(PrID);

        byte[] image = book.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        productName.setText(book.getBookName());
        productPrice.setText(CustomListAdapter.CurrencyVN(book.getPrice()));
        pImage.setImageBitmap(bitmap);
    }
    private int getMipmapResIdByName(String resName) {
        try {
            String pkgName = this.getPackageName();
            // Return 0 nếu không tìm thấy
            int resID = this.getResources().getIdentifier(resName, "mipmap", pkgName);
            Log.i("CustomListView", "Res Name: " + resName + "==> Res ID = " + resID);
            return resID;
        } catch (Exception e) {
            return 0;
        }
    }
}



