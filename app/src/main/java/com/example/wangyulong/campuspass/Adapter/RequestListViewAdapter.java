package com.example.wangyulong.campuspass.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.wangyulong.campuspass.Model.RequestModel;
import com.example.wangyulong.campuspass.R;

import java.util.ArrayList;

/**
 * Created by wangyulong on 23/3/18.
 */

public class RequestListViewAdapter extends ArrayAdapter<RequestModel>
{

    //region Override Constructors
    public RequestListViewAdapter(Context context, int requestId)
    {
        super(context, requestId);
    }

    public RequestListViewAdapter(Context context, int resource, ArrayList<RequestModel> requestList)
    {
        super(context, resource, requestList);
    }
    //endregion Override Constructors

    //region Override Methods
    @Override
    public View getView(int position, View contentView, ViewGroup parentGroup)
    {
        View v = contentView;

        //inflate view when empty
        if (v == null)
        {
            LayoutInflater vi;

            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.request_brief_page, null);
        }

        //set view components when valid
        RequestModel request_model = getItem(position);
        if (request_model != null)
        {
            //propagate items
            TextView request_title = (TextView) v.findViewById(R.id.requestTitleTxt);
            TextView request_category = (TextView) v.findViewById(R.id.requestCategory);
            TextView request_price_range = (TextView) v.findViewById(R.id.requestPriceRange);

            if (request_title != null)
            {
                request_title.setText(request_model.getRequest_name());

                Log.d("adapter -> ", "setting request title to " + request_model.getRequest_name());
            }

            if (request_category != null)
            {
                request_category.setText(request_model.getRequest_category());

                Log.d("adapter -> ", "setting request category to " + request_model.getRequest_category());
            }

            if (request_price_range != null)
            {
                request_price_range.setText(request_model.getRequest_price());

                Log.d("adapter -> ", "setting price range to " + request_model.getRequest_price());
            }
        }

        return v;
    }
    //endregion Override Methods
}
