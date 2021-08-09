package com.codeseasy.loginui;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeseasy.loginui.ClassModel.Book;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class CustomListAdapter  extends BaseAdapter {

    private List<Book> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter() {

    }

    public CustomListAdapter(Context aContext, List<Book> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new ViewHolder();
            holder.bookNameView = (TextView) convertView.findViewById(R.id.txt_ProductName);
            holder.priceView = (TextView) convertView.findViewById(R.id.textView_price);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView_flag);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Book book = this.listData.get(position);
        holder.bookNameView.setText(book.getBookName());
        holder.priceView.setText("Giá bán: " + CurrencyVN(book.getPrice()));

        byte[] image = book.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        try {
            holder.imageView.setImageBitmap(bitmap);
        }catch (Exception e){

        }
        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView bookNameView;
        TextView priceView;
    }

    public static String CurrencyVN(int currency) {
        //đơn vị VN
        Locale vn = new Locale("vi", "VN");
        Currency dollars = Currency.getInstance(vn);
        NumberFormat vnFormat = NumberFormat.getCurrencyInstance(vn);
        return String.valueOf(vnFormat.format(currency));
    }
}

