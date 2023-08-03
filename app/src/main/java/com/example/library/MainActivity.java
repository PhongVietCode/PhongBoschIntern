package com.example.library;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button seeAll, currently, alreadyRead,wishList, favorite, about;
    private TextView myLib, txtDesign;
    private ImageView myIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AllBookActivity.class);
                startActivity(intent);
            }
        });
        currently.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CurrentlyBook.class);
                startActivity(intent);
            }
        });
        alreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AlReadyReadBook.class);
                startActivity(intent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("This is my first app");
                builder.setMessage("Design by PhongVietCode, follow by a tutorial on Youtube");
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Helllo welcomeeee from me",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                        intent.putExtra("url", "https://translate.google.com/?hl=vi");
                        startActivity(intent);
                    }
                });
                builder.create().show();
            }
        });


        Utils.getInstance();
    }


    private void initView() {
        seeAll = findViewById(R.id.seeAll);
        currently = findViewById(R.id.currently);
        alreadyRead = findViewById(R.id.already);
        wishList = findViewById(R.id.wishlist);
        favorite = findViewById(R.id.favorite);
        about = findViewById(R.id.about);
        myIcon = findViewById(R.id.myIcon);
        myLib = findViewById(R.id.myLib);
        txtDesign = findViewById(R.id.txtDesign);

    }


}