package com.example.wangyulong.campuspass.Activity;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.BuyingListViewModel;
import com.example.wangyulong.campuspass.ViewModel.SellingViewModel;
import com.example.wangyulong.campuspass.databinding.BuyingPageItemsDetailBinding;

public class BuyingItemDetailedActivity extends AppCompatActivity
{
    //region Fields and Const
    BuyingPageItemsDetailBinding binding;
    SellingViewModel sellingViewModel = null;
    //endregion Fields and Const

    //region Override
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buying_page_items_detail);

        onCreateBinding();
    }
    //endregion Override

    //region Methods
    protected void onCreateBinding()
    {
        this.binding = DataBindingUtil.setContentView(this, R.layout.buying_page_items_detail);
        this.sellingViewModel = SellingViewModel.sellingViewModel();
        this.binding.setSellingVM(this.sellingViewModel);

        Glide.with(this).load(this.sellingViewModel.set_detail_photo().toString()).into(this.binding.detailedImg);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        if (this.sellingViewModel != null)
        {
            Glide.with(this).load(this.sellingViewModel.set_detail_photo().toString()).into(this.binding.detailedImg);
        }
    }
    //endregion Methods
}
