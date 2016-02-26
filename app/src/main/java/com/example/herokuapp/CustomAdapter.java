package com.example.herokuapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by James on 2/24/2016.
 */
public class CustomAdapter extends BaseAdapter {
    private ArrayList<Game> games;
    private Context context;

    // A cache for looking up Views
    private static class ViewHolder {
        TextView name;
        TextView genre;
        TextView platform;
        Button delete;
    }

    public CustomAdapter(Context context, ArrayList<Game> games) {
        this.games = games;
        this.context = context;
    }

    @Override
    public int getCount() {
        return games.size();
    }

    @Override
    public Game getItem(int position) {
        return games.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.custom_list_item, parent, false);

            viewHolder.name = (TextView) convertView.findViewById(R.id.name_text_view);

            //TODO initialize the other UI elements
            viewHolder.genre = (TextView) convertView.findViewById(R.id.genre_text_view);
            viewHolder.platform = (TextView) convertView.findViewById(R.id.platform_text_view);
            viewHolder.delete = (Button) convertView.findViewById(R.id.delete_button);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Get the data item for this position
        final Game game = getItem(position);

        //TODO Populate the data into the template view using the data object
        viewHolder.name.setText("The name of the Game");
        viewHolder.genre.setText("The genre of the game");
        viewHolder.platform.setText("The platform of the game");


        //TODO implement onclick for delete (do this after implementing retrofit)
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CUSTOM_ADAPTER", "Delete button was pressed");
                /*
                for (Game singleGame : games) {
                    if (singleGame.getName().equals(v.)){

                    }
                }
                */
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }


}
