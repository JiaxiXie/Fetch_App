package com.example.fetch;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserView extends RecyclerView.ViewHolder {
    TextView id, listid, name;
    public UserView(@NonNull View itemView) {
        super(itemView);
        id = itemView.findViewById(R.id.id);
        listid = itemView.findViewById(R.id.listid);
        name = itemView.findViewById(R.id.name);
    }
}
