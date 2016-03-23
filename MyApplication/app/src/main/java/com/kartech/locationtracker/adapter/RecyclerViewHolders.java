package com.kartech.locationtracker.adapter;

/**
 * Created by kkokate on 18-03-2016.
 */
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kartech.locationtracker.AutoCompleteActivity;
import com.kartech.locationtracker.DirectionActivity;
import com.kartech.locationtracker.MainActivity;
import com.kartech.locationtracker.PlacesActivity;
import com.kartech.locationtracker.R;
import com.kartech.locationtracker.SearchLocationActivity;

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView countryName;
    public ImageView countryPhoto;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        countryName = (TextView)itemView.findViewById(R.id.country_name);
        countryPhoto = (ImageView)itemView.findViewById(R.id.country_photo);
    }

    @Override
    public void onClick(View view) {




        switch (getPosition())
        {
            case 0:
            {
                Toast.makeText(view.getContext(), " Location Under Constructions ", Toast.LENGTH_SHORT).show();
                break;
            }

            case 1:
            {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(intent);
//                Toast.makeText(view.getContext(), "Clicked  Position = " + getPosition(), Toast.LENGTH_SHORT).show();
                break;
            }

            case 2:
            {
                Intent intent = new Intent(view.getContext(), DirectionActivity.class);
                view.getContext().startActivity(intent);
//                Toast.makeText(view.getContext(), "Clicked  Position = " + getPosition(), Toast.LENGTH_SHORT).show();
                break;
            }

            case 3:
            {
                Toast.makeText(view.getContext(), "Location History Under Constructions", Toast.LENGTH_SHORT).show();
                break;
            }

            case 4:
            {
                Intent intent = new Intent(view.getContext(), AutoCompleteActivity.class);
                view.getContext().startActivity(intent);
//                Toast.makeText(view.getContext(), "Search Location Under Constructions" , Toast.LENGTH_SHORT).show();
                break;
            }

            case 5:
            {
                Intent intent = new Intent(view.getContext(), PlacesActivity.class);
                view.getContext().startActivity(intent);
//                Toast.makeText(view.getContext(), "Clicked  Position = " + getPosition(), Toast.LENGTH_SHORT).show();
                break;
            }

            case 6:
            {
                Toast.makeText(view.getContext(), "Meeting Point Under Constructions" , Toast.LENGTH_SHORT).show();
                break;
            }

            case 7:
            {
                Toast.makeText(view.getContext(), "Share App Under Constructions" , Toast.LENGTH_SHORT).show();
                break;
            }

            /*case 8:
            {
                Toast.makeText(view.getContext(), "Clicked  Position = " + getPosition(), Toast.LENGTH_SHORT).show();
                break;
            }*/
        }

    }
}
