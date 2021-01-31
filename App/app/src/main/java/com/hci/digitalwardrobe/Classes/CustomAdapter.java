package com.hci.digitalwardrobe.Classes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hci.digitalwardrobe.Classes.Clothes_temp;
import com.hci.digitalwardrobe.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Clothes_temp> {

    private final List<Clothes_temp> items;

    public CustomAdapter(@NonNull Context context, List<Clothes_temp> items) {
        super(context,-1, items);
        this.items = items;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater buckysInflater = LayoutInflater.from(getContext());
        View customView = buckysInflater.inflate(R.layout.list_positions, parent, false);

        TextView title = (TextView) customView.findViewById(R.id.Title);
        title.setText(this.items.get(position).getTitle());
        TextView desc = (TextView) customView.findViewById(R.id.Description);
        desc.setText(this.items.get(position).getDescription());
        ImageView img = (ImageView) customView.findViewById(R.id.image1);
        img.setImageResource(this.items.get(position).getImage_resource_id());

        return customView;
    }
}