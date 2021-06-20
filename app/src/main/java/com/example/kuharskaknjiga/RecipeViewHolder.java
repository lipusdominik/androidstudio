package com.example.kuharskaknjiga;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class RecipeViewHolder extends RecyclerView.ViewHolder {

    ImageView image;
    TextView title;
    TextView ingredients;
    TextView body;

    public RecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
        title = itemView.findViewById(R.id.title);
        ingredients = itemView.findViewById(R.id.ingredients);
        body = itemView.findViewById(R.id.body);

    }
}
