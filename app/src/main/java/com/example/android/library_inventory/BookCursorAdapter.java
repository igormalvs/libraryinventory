package com.example.android.library_inventory;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.library_inventory.data.BookContract.BookEntry;

public class BookCursorAdapter extends CursorAdapter {

    public BookCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView summaryTextView = (TextView) view.findViewById(R.id.summary);
        final TextView summary2TextView = (TextView) view.findViewById(R.id.summary2);

        Button sellButton = (Button) view.findViewById( R.id.sell_button );
        final int id = cursor.getInt(cursor.getColumnIndex(BookEntry._ID));
        String bookName = cursor.getString(cursor.getColumnIndex(BookEntry.COLUMN_BOOK_NAME));
        float bookPrice = cursor.getFloat(cursor.getColumnIndex(BookEntry.COLUMN_BOOK_PRICE));
        final int bookQuant = cursor.getInt(cursor.getColumnIndex(BookEntry.COLUMN_BOOK_QUANT));

        nameTextView.setText(bookName);
        summaryTextView.setText("$ " + Float.toString(bookPrice));
        summary2TextView.setText("Quantity: " + Integer.toString(bookQuant));

        sellButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bookQuant > 0){
                    Uri currentProdutoUri = ContentUris.withAppendedId(BookEntry.CONTENT_URI, id);

                    int newQuant = bookQuant - 1;

                    ContentValues values = new ContentValues();
                    values.put(BookEntry.COLUMN_BOOK_QUANT, newQuant);
                    context.getContentResolver().update(currentProdutoUri, values, null, null);
                    Toast.makeText( context, "Item Sold", Toast.LENGTH_SHORT ).show();
                    summary2TextView.setText( Integer.toString( newQuant ) );
                }else{
                    Toast.makeText( context, "You need to register more items", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
    }

}
