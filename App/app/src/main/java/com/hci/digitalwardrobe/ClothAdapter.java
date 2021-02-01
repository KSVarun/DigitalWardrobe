package com.hci.digitalwardrobe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hci.digitalwardrobe.models.ClothesModel;
import com.hci.digitalwardrobe.models.WardrobeFactory;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ClothAdapter extends ArrayAdapter<ClothesModel> {

    private final List<ClothesModel> items;

    public ImageView cloth_image;
    public TextView cloth_name;
    public TextView cloth_category;
    public TextView cloth_weather;
    public TextView cloth_sleevelength;
    public TextView cloth_neckline;
    public TextView cloth_pattern;
    public TextView cloth_skin_exposure;
    public TextView cloth_collar;
    public TextView cloth_scarf;
    public TextView cloth_necktie;
    public TextView cloth_placket;
    public TextView cloth_colour;

    public ClothAdapter(Context context, List<ClothesModel> items) {
        super(context, -1, items);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)
                this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row_view = inflater.inflate(R.layout.cloth_item_recycler_view_row, parent, false);

        cloth_name = row_view.findViewById(R.id.cloth_name);
        cloth_category = row_view.findViewById(R.id.cloth_category);
        cloth_weather = row_view.findViewById(R.id.cloth_weather);
        cloth_sleevelength = row_view.findViewById(R.id.cloth_sleevelength);
        cloth_neckline = row_view.findViewById(R.id.cloth_neckline);
        cloth_pattern = row_view.findViewById(R.id.cloth_pattern);
        cloth_skin_exposure = row_view.findViewById(R.id.cloth_skin_exposure);
        cloth_collar = row_view.findViewById(R.id.cloth_collar);
        cloth_scarf = row_view.findViewById(R.id.cloth_scarf);
        cloth_necktie = row_view.findViewById(R.id.cloth_necktie);
        cloth_placket = row_view.findViewById(R.id.cloth_placket);
        cloth_colour = row_view.findViewById(R.id.cloth_colour);
        cloth_image = row_view.findViewById(R.id.cloth_image);

        cloth_name.setText(this.items.get(position).getName());
        cloth_category.setText(this.items.get(position).getCategory());
        cloth_weather.setText(this.items.get(position).getWeather());
        cloth_sleevelength.setText(this.items.get(position).getSleevelength());
        cloth_neckline.setText(this.items.get(position).getNeckline());
        cloth_pattern.setText(this.items.get(position).getPattern());
        cloth_skin_exposure.setText(this.items.get(position).getSkin_exposure());
        cloth_collar.setText(this.items.get(position).getCollar());
        cloth_scarf.setText(this.items.get(position).getScarf());
        cloth_necktie.setText(this.items.get(position).getNecktie());
        cloth_placket.setText(this.items.get(position).getPlacket());
        cloth_colour.setText(this.items.get(position).getColour());

        Picasso.get().load(WardrobeFactory.getInstance().getAppContext().getString
                ((R.string.MEDIA_SERVER_URL)) + this.items.get(position).getImage()).into(cloth_image);

        return row_view;
    }

}
