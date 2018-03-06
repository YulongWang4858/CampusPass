package com.example.wangyulong.campuspass.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.wangyulong.campuspass.Model.BuyingItemModel;
import com.example.wangyulong.campuspass.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyulong on 06/03/18.
 */

public class BuyingListViewAdapter extends ArrayAdapter<BuyingItemModel>
{
    //region Fields and Const

    //endregion Fields and Const

    //region Constructor
    public BuyingListViewAdapter(Context context, int buyingItemId)
    {
        //inherit constructor
        super(context, buyingItemId);
    }

    public BuyingListViewAdapter(Context context, int resource, ArrayList<BuyingItemModel> itemModelList)
    {
        super(context, resource, itemModelList);
    }
    //endregion Constructor

    //region Method
    @Override
    public View getView(int position, View contentView, ViewGroup parentGroup)
    {
        View v = contentView;

        //inflate view when empty
        if (v == null)
        {
            LayoutInflater vi;

            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.buying_page_items_brief, null);
        }

        //retrieve item and fill row
        BuyingItemModel itemModel = getItem(position);
        if (itemModel != null)
        {
            TextView item_title = (TextView) v.findViewById(R.id.listItemTitle);
            TextView item_short_descr = (TextView) v.findViewById(R.id.listItemShortDescr);
            //Drawable item_icon_img = (Drawable) v.findViewById(R.id.listItemImg);

            if (item_title != null)
            {
                item_title.setText(itemModel.get_item_title());
            }

            if (item_short_descr != null)
            {
                item_short_descr.setText(itemModel.get_item_short_description());
            }
        }

        return v;
    }
    //endregion Method
}
