package com.example.kuharskaknjiga;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeAdaptor extends RecyclerView.Adapter<RecipeViewHolder> {

    ArrayList<Recipe> recipes;

    public RecipeAdaptor() {
        recipes = new ArrayList<>();
    }

    public void setData (ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View recipeView = layoutInflater.inflate(R.layout.recycler_row,parent,false);

        return new RecipeViewHolder(recipeView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        Picasso.get().load(recipe.image).into(holder.image);
        holder.title.setText(recipe.title);
        holder.ingredients.setText(recipe.ingredients);
        holder.body.setText(recipe.body);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}
