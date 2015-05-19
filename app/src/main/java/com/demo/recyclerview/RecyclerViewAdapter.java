package com.demo.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by noor on 05/05/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<ListItemViewHolder> {


    private ArrayList<DataModel> mDataModels;
    private String mType;

    public RecyclerViewAdapter(ArrayList<DataModel> mDataModels, String mType) {
        this.mDataModels = mDataModels;
        this.mType = mType;
    }

    /**
     * Called when RecyclerView needs a new {@link android.support.v7.widget.RecyclerView.ViewHolder} of the given type to represent
     * an item.
     * <p/>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p/>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder, int)}. Since it will be re-used to display different
     * items in the data set, it is a good idea to cache references to sub views of the View to
     * avoid unnecessary {@link android.view.View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder, int)
     */
    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new ListItemViewHolder(itemView);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method
     * should update the contents of the {@link android.support.v7.widget.RecyclerView.ViewHolder#itemView} to reflect the item at
     * the given position.
     * <p/>
     * Note that unlike {@link android.widget.ListView}, RecyclerView will not call this
     * method again if the position of the item changes in the data set unless the item itself
     * is invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside this
     * method and should not keep a copy of it. If you need the position of an item later on
     * (e.g. in a click listener), use {@link android.support.v7.widget.RecyclerView.ViewHolder#getAdapterPosition()} which will have
     * the updated adapter position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {
        DataModel model = mDataModels.get(position);
        holder.title.setText(model.getTitle());
        if(position % 2 == 0){
            if(mType.equals(MainActivity.TYPE_VERTICAL_GRID_VIEW_STAGGERED)){
                holder.imgView.setImageResource(R.drawable.lollipop);
            }else{
                holder.imgView.setImageResource(R.drawable.lollipop_h1);
            }

        }else if(position % 3 == 0){
            if(mType.equals(MainActivity.TYPE_VERTICAL_GRID_VIEW_STAGGERED)){
                holder.imgView.setImageResource(R.drawable.lollipop1);
            }else{
                holder.imgView.setImageResource(R.drawable.lollipop_h2);
            }
        }else{
            if(mType.equals(MainActivity.TYPE_VERTICAL_GRID_VIEW_STAGGERED)){
                holder.imgView.setImageResource(R.drawable.lollipop2);
            }else{

                holder.imgView.setImageResource(R.drawable.lollipop_h3);
            }
        }
        //holder.itemView.setActivated(selectedItems.get(position, false));

    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mDataModels.size();
    }
}
