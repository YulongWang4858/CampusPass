package com.example.wangyulong.campuspass.Activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.BuyingListViewModel;
import com.example.wangyulong.campuspass.databinding.BuyingPageItemsDetailBinding;

public class BuyingItemDetailedActivity extends AppCompatActivity
{
    BuyingPageItemsDetailBinding binding;

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
//        Bundle bundle = getIntent().getExtras();
//        int selectedModelId = bundle.getInt("id");
//
//        BuyingListViewModel.buyingListViewModel().set_new_item_selected(selectedModelId);
//        BuyingItemModel item = BuyingListViewModel.buyingListViewModel().get_selected_item();
//
//        binding.detailedItemTitle.setText(item.get_item_title());
//        binding.detailedImg.setImageResource(item.get_item_image_id());
//        binding.detailedLongDescr.setText(item.get_item_short_description());
//        binding.detailedItemPrice.setText(Double.toString(item.get_item_price()));
    }
}
