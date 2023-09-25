package com.example.fetch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class UserAdapter extends RecyclerView.Adapter<UserView> {

    ArrayList<User> users;

    public UserAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserView(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserView holder, int position) {
        holder.id.setText("ID = "+users.get(position).getID());
        holder.listid.setText("ListID = "+users.get(position).getListID());
        holder.name.setText("Name = "+users.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
