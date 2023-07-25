package com.example.recycleview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class contactRecyclerViewAdapter extends RecyclerView.Adapter<contactRecyclerViewAdapter.ViewHolder>
{
    private ArrayList<Contact> contacts = new ArrayList<>();
    private Context context;
    public contactRecyclerViewAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_list_item,parent,false );
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    /**
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     *         // -> The most important function, it will set anything related to item
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtName.setText(contacts.get(position).getName());
        holder.txtEmail.setText(contacts.get(position).getEmail());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,contacts.get(position).getEmail(), Toast.LENGTH_SHORT).show();
            }
        });
        Glide.with(context)
                .asBitmap()
                .load(contacts.get(position).getImageUrl())
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged(); // refresh the page when have the noti
    }

    /**
     * Hold the viewItem for every item in recycle view
     */
public class ViewHolder extends RecyclerView.ViewHolder{
    // Elements in layout file
    private TextView txtName;
    private TextView txtEmail;
    private CardView parent;
    private ImageView image;
    //-----
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        txtName = itemView.findViewById(R.id.txtContact);
        txtEmail = itemView.findViewById(R.id.txtContactEmail);
        parent = itemView.findViewById(R.id.parent);
        image =itemView.findViewById(R.id.image);

    }
}
}
