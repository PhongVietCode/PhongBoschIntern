package com.example.library;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.transition.TransitionManager;
import android.util.Log;
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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class bookRecViewAdapter extends RecyclerView.Adapter<bookRecViewAdapter.ViewHolder> {
    private static final String TAG = "bookRecViewAdapter";
    private ArrayList<Book> books =new ArrayList<>();
    private Context context;
    private String parentActivity;
    public bookRecViewAdapter(Context context,String parentActivity) {
        this.context = context;
        this.parentActivity = parentActivity;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    // this is the wrapper of our item
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d(TAG, "onBindViewHolder: started");
        holder.txtName.setText(books.get(position).getName());
        //Use glide to load the image from an url
        Glide.with(context)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.imgBook);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BookActivity.class);
                intent.putExtra("bookId", books.get(position).getId());
                context.startActivity(intent);
            }
        });

        holder.txtAuthor.setText(books.get(position).getAuthor());
        holder.txtDes.setText(books.get(position).getShortDes());
        if(books.get(position).isExpanded()){
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expanedRel.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);
            if(parentActivity.equals("allbook")){
                holder.txtDelete.setVisibility(View.GONE);
            }
            else if(parentActivity.equals("already")){
                holder.txtDelete.setVisibility(View.VISIBLE);
                holder.txtDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(Utils.getInstance().rmvFromalready(books.get(position))){
                            Toast.makeText(context," removed", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                        else{
                            Toast.makeText(context," something wrong ", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        }
        else{
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expanedRel.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    // Keep all the things in design file
    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private ImageView imgBook;
        private TextView txtName;
        private ImageView downArrow, upArrow;
        private RelativeLayout expanedRel;
        private TextView txtAuthor, txtDes;
        private TextView txtDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            imgBook = itemView.findViewById(R.id.imgBook);
            txtName = itemView.findViewById(R.id.txtBookName);
            expanedRel = itemView.findViewById(R.id.expandedRelayout);
            downArrow = itemView.findViewById(R.id.btnDownArrow);
            upArrow = itemView.findViewById(R.id.btnUpArrow);
            txtAuthor = itemView.findViewById(R.id.txtAuthor2);
            txtDes = itemView.findViewById(R.id.txtShortDc);
            txtDelete = itemView.findViewById(R.id.btnDelete);

            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book = books.get(getAdapterPosition()); // find the exactly the position of the book we are clicking to
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book = books.get(getAdapterPosition()); // find the exactly the position of the book we are clicking to
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}
