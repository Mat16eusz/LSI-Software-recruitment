package com.example.lsisoftware;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsisoftware.database.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {

    private Context context;
    private List<User> userList;
    private SelectListener listener;

    public UserListAdapter(Context context, SelectListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.MyViewHolder holder, int position) {
        Picasso.get().load(this.userList.get(position).avatar);
        holder.name.setText(this.userList.get(position).name);
        holder.sourceAPI.setText(this.userList.get(position).sourceAPI);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView avatar;
        TextView name;
        TextView sourceAPI;
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);

            avatar = view.findViewById(R.id.avatar);
            name = view.findViewById(R.id.name);
            sourceAPI = view.findViewById(R.id.sourceAPI);
            cardView = view.findViewById(R.id.main_container);
        }
    }
}
