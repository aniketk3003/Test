package com.kartech.locationtracker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.kartech.locationtracker.adapter.RecyclerViewAdapter;
import com.kartech.locationtracker.model.GridObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private GridLayoutManager lLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        List<GridObject> rowListItem = getAllItemList();
        lLayout = new GridLayoutManager(HomeActivity.this, 2);
        /*lLayout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (3 - position % 3);
            }
        });*/

        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(HomeActivity.this, rowListItem);
        rView.setAdapter(rcAdapter);


    }


    private List<GridObject> getAllItemList(){

        List<GridObject> allItems = new ArrayList<GridObject>();
        allItems.add(new GridObject("Location", R.drawable.location));
        allItems.add(new GridObject("Current Location", R.drawable.current_location));
        allItems.add(new GridObject("Direction", R.drawable.direction));
        allItems.add(new GridObject("Location History", R.drawable.location_history));
        allItems.add(new GridObject("Search Location", R.drawable.search_location));
        allItems.add(new GridObject("Places Around", R.drawable.places_around));
        allItems.add(new GridObject("Meeting Point", R.drawable.meeting_point));
//        allItems.add(new GridObject("Service", R.drawable.services));
        allItems.add(new GridObject("Share App", R.drawable.share_app));

        return allItems;
    }
}
