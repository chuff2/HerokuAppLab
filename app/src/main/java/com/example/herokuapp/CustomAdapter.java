package com.example.herokuapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        viewHolder.name.setText(game.getName());
        viewHolder.genre.setText(game.getGenre());
        viewHolder.platform.setText(game.getPlatform());


        //TODO implement onclick for delete (do this after implementing retrofit)
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CUSTOM_ADAPTER", "Delete button was pressed");
                LinearLayout parent = (LinearLayout) ((ViewGroup) v.getParent());
                LinearLayout sibling = (LinearLayout) ((ViewGroup) parent).getChildAt(0);
                TextView name = (TextView) ((ViewGroup) sibling).getChildAt(0);
                int deleteIndex = 0;
                for (Game singleGame : games) {
                    Log.d("CUSTOM_ADAPTER", singleGame.getName().toString());
                    if (singleGame.getName().toString().equals(name.getText().toString())){
                        break;
                    }
                    deleteIndex++;
                }
                games.remove(deleteIndex);
                MainActivity.deleteGame(null);
                //deleteGame(null);
                //possible make a listener in this class and set it here and have it update in MainActivity
                //context.getApplicationContext();
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }


}
