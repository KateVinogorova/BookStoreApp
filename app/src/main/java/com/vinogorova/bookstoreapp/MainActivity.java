package com.vinogorova.bookstoreapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vinogorova.bookstoreapp.data.BookContract.BookEntry;

import com.vinogorova.bookstoreapp.data.BookDbHelper;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //String for creating logs
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    BookDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set floating action button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add an action starting when button is clicked
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

        insertBook();
        displayDatabaseInfo();
    }


    private void insertBook() {
        //Create database helper
        dbHelper = new BookDbHelper(this);
        //Gets the database in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and book attributes from the editor are the values.
        ContentValues values = new ContentValues();

        values.put(BookEntry.COLUMN_PRODUCT_NAME, "The Brothers Karamazov");
        values.put(BookEntry.COLUMN_AUTHOR, "Fedor Dostoevsky");
        values.put(BookEntry.COLUMN_PRICE, 25);
        values.put(BookEntry.COLUMN_QUANTITY, 3);
        values.put(BookEntry.COLUMN_SUPPLIER_NAME, "Eksmo");
        values.put(BookEntry.COLUMN_SUPPLIER_PHONE, "89009009090");

        // Insert a new row for book in the database, returning the ID of that new row.
        long newRowId = db.insert(BookEntry.TABLE_NAME, null, values);
        Log.e(LOG_TAG, "New row ID " + newRowId);

        // Show a toast message depending on whether or not the insertion was success
        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving pet", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Pet saved with row id " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    private void displayDatabaseInfo() {

        // Create database helper
        dbHelper = new BookDbHelper(this);
        // Gets the database in read mode
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BookEntry._ID,
                BookEntry.COLUMN_PRODUCT_NAME,
                BookEntry.COLUMN_AUTHOR,
                BookEntry.COLUMN_PRICE,
                BookEntry.COLUMN_QUANTITY,
                BookEntry.COLUMN_SUPPLIER_NAME,
                BookEntry.COLUMN_SUPPLIER_PHONE
        };

        // Perform a query on the books table
        Cursor cursor = database.query(
                BookEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        //Find text views in which we need to set data from books table
        TextView productNameTextView = findViewById(R.id.product_name_text_view);
        TextView authorTextView = findViewById(R.id.author_text_view);
        TextView priceTextView = findViewById(R.id.price_value_text_view);
        TextView quantityTextView = findViewById(R.id.quantity_value_text_view);
        TextView supplierNameTextView = findViewById(R.id.supplier_name_value_text_view);
        TextView supplierPhoneTextView = findViewById(R.id.supplier_phone_value_text_view);

        try {
            while (cursor.moveToNext()) {
                //Find the values of cursor rows using indexes of each column
                String currentProductName = cursor.getString(cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_NAME));
                String currentAuthor = cursor.getString(cursor.getColumnIndex(BookEntry.COLUMN_AUTHOR));
                int currentPrice = cursor.getInt(cursor.getColumnIndex(BookEntry.COLUMN_PRICE));
                int currentQuantity = cursor.getInt(cursor.getColumnIndex(BookEntry.COLUMN_QUANTITY));
                String currentSupplierName = cursor.getString(cursor.getColumnIndex(BookEntry.COLUMN_SUPPLIER_NAME));
                String currentSupplierPhone = cursor.getString(cursor.getColumnIndex(BookEntry.COLUMN_SUPPLIER_PHONE));

                //Set data from database to text views
                productNameTextView.setText(currentProductName);
                authorTextView.setText(currentAuthor);
                priceTextView.setText(String.valueOf(currentPrice));
                quantityTextView.setText(String.valueOf(currentQuantity));
                supplierNameTextView.setText(currentSupplierName);
                supplierPhoneTextView.setText(currentSupplierPhone);
            }
        }finally {
            cursor.close();
        }
    }
}
