package com.vinogorova.bookstoreapp.data;

import android.provider.BaseColumns;

/**
 * This class contains inner classes for each table and its constants in existing database
 */
public class BookContract {

    private BookContract(){};

    /**
     * This class defines name of table and its constants
     */
    public static class BookEntry implements BaseColumns {
        public static final String TABLE_NAME = "books";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PRODUCT_NAME = "product_name";
        public static final String COLUMN_AUTHOR = "author";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_SUPPLIER_PHONE = "supplier_phone";

    }

}
