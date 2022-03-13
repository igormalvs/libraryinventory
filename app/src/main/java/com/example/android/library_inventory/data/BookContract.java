package com.example.android.library_inventory.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import java.io.Serializable;

public final class BookContract {

    private BookContract() {}

    public static final String CONTENT_AUTHORITY = "com.example.android.library_inventory";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_BOOKS = "books";

    public static final class BookEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_BOOKS);

        public final static String TABLE_NAME = "BOOKS";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_BOOK_NAME ="NAME";
        public final static String COLUMN_BOOK_PRICE = "PRICE";
        public final static String COLUMN_BOOK_QUANT = "QUANTITY";
        public final static String COLUMN_BOOK_SUPPLIER = "SUPPLIER";
        public static final String COLUMN_BOOK_NUMBER = "NUMBER" ;

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;
    }

}
