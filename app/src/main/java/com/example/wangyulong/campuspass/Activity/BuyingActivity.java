package com.example.wangyulong.campuspass.Activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import com.example.wangyulong.campuspass.Adapter.BuyingListViewAdapter;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.BuyingListViewModel;
import com.example.wangyulong.campuspass.databinding.BuyingPageBinding;

/**
 * Created by wangyulong on 23/02/18.
 */


public class BuyingActivity extends ListActivity
{

    BuyingPageBinding binding;
    BuyingListViewModel buyingListVM = BuyingListViewModel.buyingListViewModel();

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
        //customize adpater
        ListView buyingItemList = getListView();
        BuyingListViewAdapter buyingListViewAdapter = new BuyingListViewAdapter(this, R.layout.buying_page_items_brief, this.buyingListVM.get_buying_elements());
        buyingItemList.setAdapter(buyingListViewAdapter);
    }

    protected void loadBuyingList()
    {

    }
}


