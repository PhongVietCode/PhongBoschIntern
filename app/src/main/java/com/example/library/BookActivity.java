package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    private TextView bookNametxt, authortxt, pagestxt, destxt;
    private Button btnCurrent, btnWantto, btnAlready, btnFavorite;
    private ImageView bookImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initView();
        //Todo: get book data from recycleView
//        Book book  = new Book(1,"Homes 1","KingD",1020,"https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg","Long time age","try this for free");
        Intent intent = getIntent();
        if(intent != null){
            // Retrieve data from the recycle View
            int bookId = intent.getIntExtra("bookId",-1);
            if(bookId != -1){
                Book incomingBook = Utils.getInstance().getBookbyID(bookId);
                if(incomingBook != null){
                    setData(incomingBook);
                    handelAlreadyRead(incomingBook);
                    handleCurrentlyReadBook(incomingBook);
                }
            }
        }
    }


    private void handleCurrentlyReadBook(final Book incomingBook) {
        ArrayList<Book> currently = Utils.getCurrentlyBook();
        boolean exist = false;
        for (Book b :
                currently) {
            if (b.getId() == incomingBook.getId()) {
                exist = true;
            }
        }
        if(exist){
            btnCurrent.setEnabled(false);
        }
        else {
            btnCurrent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance().addtoCurrentBook(incomingBook))
                    {
                        Toast.makeText(BookActivity.this, "Added to already read", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, CurrentlyBook.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(BookActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

    /**
     * Enable and disable button
     * Add the book to the alreadyBook array
     * @param incomingBook
     */
    private void handelAlreadyRead(Book incomingBook) {
        ArrayList<Book> alreadyBook = Utils.getAlreadyBook();
        boolean exist = false;
        for (Book b :
                alreadyBook) {
            if (b.getId() == incomingBook.getId()) {
                exist = true;
            }
        }
        if(exist){
            btnAlready.setEnabled(false);
        }
        else {
            btnAlready.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance().addtoAlreadyBook(incomingBook))
                    {
                        Toast.makeText(BookActivity.this, "Added to already read", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, AlReadyReadBook.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(BookActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

    private void setData(Book book){
        bookNametxt.setText(book.getName());
        authortxt.setText(book.getAuthor());
        pagestxt.setText(String.valueOf(book.getPages()));
        destxt.setText(book.getLongDes());
        // get image from an url
        Glide.with(this)
                .asBitmap()
                .load(book.getImageUrl())
                .into(bookImage);
    }
    private void initView(){
        bookNametxt = findViewById(R.id.bookname2);
        authortxt = findViewById(R.id.author2);
        pagestxt= findViewById(R.id.page2);
        destxt = findViewById(R.id.longdestxt);
        btnCurrent = findViewById(R.id.btnCurrent);
        btnWantto = findViewById(R.id.btnWantto);
        btnAlready= findViewById(R.id.btnAlready);
        btnFavorite = findViewById(R.id.btnFavo);
        bookImage = findViewById(R.id.bookImage);

    }
}