package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView contactsReView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactsReView = findViewById(R.id.recycleView);
        // create our own adapter
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("phong","phong.tran","https://dfstudio-d420.kxcdn.com/wordpress/wp-content/uploads/2019/06/digital_camera_photo-1080x675.jpg"));
        contacts.add(new Contact("jji","jiad.come","https://imgv3.fotor.com/images/blog-richtext-image/part-blurry-image.jpg"));
        contacts.add(new Contact("jji","jiad.come","https://imgv3.fotor.com/images/blog-richtext-image/part-blurry-image.jpg"));
        contacts.add(new Contact("jji","jiad.come","https://imgv3.fotor.com/images/blog-richtext-image/part-blurry-image.jpg"));

        contactRecyclerViewAdapter adapter = new contactRecyclerViewAdapter(this);
        adapter.setContacts(contacts);

        contactsReView.setAdapter(adapter);
        contactsReView.setLayoutManager(new GridLayoutManager(this, 2));



    }

}