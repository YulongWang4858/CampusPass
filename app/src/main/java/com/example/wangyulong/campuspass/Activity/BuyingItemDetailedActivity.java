package com.example.wangyulong.campuspass.Activity;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.BuyingListViewModel;
import com.example.wangyulong.campuspass.databinding.BuyingPageItemsDetailBinding;

public class BuyingItemDetailedActivity extends AppCompatActivity
{
    BuyingPageItemsDetailBinding binding;
    BuyingListViewModel buyingListViewModel = BuyingListViewModel.buyingListViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buying_page_items_detail);

        onCreateBinding();
    }

    protected void onCreateBinding()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.buying_page_items_detail);
        binding.setBuyingListVM(BuyingListViewModel.buyingListViewModel());

        settingUI();
    }

    protected void settingUI()
    {
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        //reload to selected item icon image
        try
        {
            Glide.with(this).load(buyingListViewModel.get_new_item_selected_img_uri()).into(binding.detailedImg);
        }
        catch (Exception e)
        {
            Log.d("Error msg -> ", e.getMessage());
        }
    }
}
