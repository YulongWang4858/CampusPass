package com.example.wangyulong.campuspass.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangyulong.campuspass.Constant.Category;
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
            ImageView item_icon_img = (ImageView) v.findViewById(R.id.listItemImg);
            TextView item_condition = (TextView) v.findViewById(R.id.listItemCondition);
            TextView item_price = (TextView) v.findViewById(R.id.listItemPrice);

            if (item_title != null)
            {
                item_title.setText(itemModel.get_item_title());
            }

            if (item_short_descr != null)
            {
                item_short_descr.setText(itemModel.get_item_short_description());
            }

            if (item_icon_img != null)
            {
                item_icon_img.setImageResource(itemModel.get_item_image_id());
            }

            if (item_price != null)
            {
                item_price.setText("$" + Double.toString(itemModel.get_item_price()));
            }

            if (item_condition != null)
            {
                item_condition.setText(convert_condition(itemModel.get_item_condition()));
            }
        }

        return v;
    }

    private String convert_condition(Category.BuyingItemCondition condition)
    {
        switch (condition)
        {
            case DISPOSE:
                return "Dispose";
            case SECOND_HAND:
                return "Secondhand";
            default:
                return "New";
        }
    }
    //endregion Method
}
