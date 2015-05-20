package com.demo.recyclerview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    public static final String TYPE_VERTICAL_LIST= "Vertical List";
    public static final String TYPE_HORIZONTAL_LIST= "Horizontal List";
    public static final String TYPE_GRID_VIEW = "Grid View";
    public static final String TYPE_HORIZONTAL_GRID_VIEW_STAGGERED = "HorizontalStaggeredGridView";
    public static final String TYPE_VERTICAL_GRID_VIEW_STAGGERED = "VerticalStaggeredGridView";
    public static final String TYPE = "TYPE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attachFragment(TYPE_VERTICAL_LIST);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
//            case R.id.action_add_item:
//                attachFragment(TYPE_VERTICAL_LIST);
//                break;
//            case R.id.action_delete_item:
//                attachFragment(TYPE_VERTICAL_LIST);
//                break;
            case R.id.action_vertical_list:
                attachFragment(TYPE_VERTICAL_LIST);
                break;
            case R.id.action_horizontal_list:
                attachFragment(TYPE_HORIZONTAL_LIST);
                break;
            case R.id.action_grid_view:
                attachFragment(TYPE_GRID_VIEW);
                break;
            case R.id.action_vertical_grid_view_staggered:
                attachFragment(TYPE_VERTICAL_GRID_VIEW_STAGGERED);
                break;
            case R.id.action_horizontal_grid_view_staggered:
                attachFragment(TYPE_HORIZONTAL_GRID_VIEW_STAGGERED);
                break;
        }

        return super.onOptionsItemSelected(item);
        //return false;
    }


    private void attachFragment(String type){
        Bundle bundle = new Bundle();
        bundle.putString(TYPE, type);
        PlaceholderFragment placeholderFragment = new PlaceholderFragment();
        placeholderFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, placeholderFragment).commit();
    }

}


