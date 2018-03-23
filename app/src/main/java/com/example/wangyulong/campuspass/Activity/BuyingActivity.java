package com.example.wangyulong.campuspass.Activity;

import android.app.ListActivity;
import android.content.Intent;
import android.databinding.generated.callback.OnClickListener;
import android.os.Bundle;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import com.example.wangyulong.campuspass.Adapter.BuyingListViewAdapter;
import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.BuyingListViewModel;
import com.example.wangyulong.campuspass.databinding.BuyingPageBinding;
import com.example.wangyulong.campuspass.databinding.BuyingPageItemsBriefBinding;
import com.example.wangyulong.campuspass.databinding.BuyingPageItemsDetailBinding;

/**
 * Created by wangyulong on 23/02/18.
 */


public class BuyingActivity extends ListActivity
{

    BuyingPageBinding binding;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buying_page);

        //initialize binding
        onCreateBinding();

        //initialize item list
        loadBuyingList();
    }

    protected void onCreateBinding()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.buying_page);

        bindButton();
    }

    protected void bindButton()
    {
    }

    protected void loadBuyingList()
    {
        //customize adpater
        ListView buyingItemList = getListView();
        buyingItemList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
//                adapterView.getItemAtPosition(i)
                Intent toDetailedPage = new Intent(getApplicationContext(), BuyingItemDetailedActivity.class);
                BuyingListViewModel.buyingListViewModel().set_new_item_selected(i);
                BuyingActivity.this.startActivity(toDetailedPage);
            }
        });
        BuyingListViewAdapter buyingListViewAdapter =
                new BuyingListViewAdapter(this, R.layout.buying_page_items_brief, BuyingListViewModel.buyingListViewModel().get_buying_elements());
        buyingItemList.setAdapter(buyingListViewAdapter);
    }
}


