package com.demo.recyclerview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by noor on 05/05/15.
 */
public class PlaceholderFragment extends Fragment {

    private static final String TAG = "PlaceholderFragment";
    /*Number of columns in the grid view*/
    private static final int NUM_OF_COLUMNS = 2;
    /*Total number of items in the RecyclerView*/
    private static final int NUM_OF_ITEMS = 100;
    private ArrayList<DataModel> mDataModels;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private int addItemCount = 0;


    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        //Must be set in order to capture menu item click events. If you don't set it, it will not show the menu items set in the Activity holding this fragment.
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerview);


        RecyclerView.LayoutManager layoutManager = null;
        String type = getArguments().getString(MainActivity.TYPE);
        if( type.equals(MainActivity.TYPE_VERTICAL_LIST)){
            /*LinearLayoutManager to show a vertical list view*/
            layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        }else if(type.equals(MainActivity.TYPE_HORIZONTAL_LIST)){
            /*LinearLayoutManager to show a horizontal list view*/
            layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        }else if(type.equals(MainActivity.TYPE_GRID_VIEW)){
             /*LinearLayoutManager to show a grid view. We can specify number of columns in the grid.*/
            layoutManager = new GridLayoutManager(getActivity(), NUM_OF_COLUMNS);

        }else if(type.equals(MainActivity.TYPE_HORIZONTAL_GRID_VIEW_STAGGERED)){
             /*LinearLayoutManager to show a staggered grid view. We can specify number of columns in the grid.*/
            //spanCount:   If orientation is vertical, spanCount is number of columns. If orientation is horizontal, spanCount is number of rows.
            //orientation: StaggeredGridLayoutManager.HORIZONTAL or StaggeredGridLayoutManager.HORIZONTAL
            layoutManager = new StaggeredGridLayoutManager(3/*span count*/, StaggeredGridLayoutManager.HORIZONTAL/* orientation*/);

        }else if(type.equals(MainActivity.TYPE_VERTICAL_GRID_VIEW_STAGGERED)){
             /*LinearLayoutManager to show a staggered grid view. We can specify number of columns in the grid.*/
            //spanCount:   If orientation is vertical, spanCount is number of columns. If orientation is horizontal, spanCount is number of rows.
            //orientation: StaggeredGridLayoutManager.HORIZONTAL or StaggeredGridLayoutManager.HORIZONTAL
            layoutManager = new StaggeredGridLayoutManager(2/*span count*/, StaggeredGridLayoutManager.VERTICAL/* orientation*/);

        }

        recyclerView.setLayoutManager(layoutManager);
        mDataModels = getDataModelList();
        recyclerViewAdapter = new RecyclerViewAdapter(mDataModels, type);

        /*Third party ItemDecoration found from https://gist.github.com/alexfu/0f464fc3742f134ccd1e*/
        RecyclerView.ItemDecoration verticalDivider  = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        RecyclerView.ItemDecoration horizontalDivider = new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST);
        recyclerView.addItemDecoration(horizontalDivider);
        recyclerView.addItemDecoration(verticalDivider);

        // this is the default;
        // this call is actually only necessary with custom ItemAnimators
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(recyclerViewAdapter);
        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_add_item:
                addItem();
                break;
            case R.id.action_delete_item:
                deleteItem();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    /** Creates and returns the data items to be shown in the Recycler View*/
    private ArrayList<DataModel> getDataModelList(){
        ArrayList<DataModel> dataModels = new ArrayList<>();

        for (int i = 0; i < NUM_OF_ITEMS; i++) {
            dataModels.add(new DataModel("Title:"+i));
        }

        return dataModels;
    }


    /** Adds a single item in the existing list*/
    private final void addItem(){
        //Adding item at top second position(i.e. at index 1).
        mDataModels.set(1, new DataModel("Added Item "+String.valueOf(++addItemCount)));
        //See for similar methods to know more item insertion options
        recyclerViewAdapter.notifyItemInserted(1);
    }

    /** Deletes a single item from the existing list*/
    private void deleteItem(){
        //Removing top item
        mDataModels.remove(0);
        //See for similar methods to know more item removing options
        recyclerViewAdapter.notifyItemRemoved(0);
    }

}