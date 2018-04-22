package com.example.wangyulong.campuspass.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.BuyingListViewModel;
import com.example.wangyulong.campuspass.ViewModel.ChatBoxViewModel;
import com.example.wangyulong.campuspass.ViewModel.SellingViewModel;
import com.example.wangyulong.campuspass.databinding.BuyingPageItemsDetailBinding;
import com.example.wangyulong.campuspass.databinding.ChatRoomBinding;

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

    @Override
    protected void onResume()
    {
        super.onResume();

        if (this.sellingViewModel != null)
        {
            Glide.with(this).load(this.sellingViewModel.set_detail_photo().toString()).into(this.binding.detailedImg);
        }
    }
    //endregion Override

    //region Methods
    protected void onCreateBinding()
    {
        this.binding = DataBindingUtil.setContentView(this, R.layout.buying_page_items_detail);
        this.sellingViewModel = SellingViewModel.sellingViewModel();
        this.binding.setSellingVM(this.sellingViewModel);

        Glide.with(this).load(this.sellingViewModel.set_detail_photo().toString()).into(this.binding.detailedImg);

        bindButtons();
    }

    private void bindButtons()
    {
        this.binding.setBuyingItemDetailCancelButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                finish();
            }
        });

        this.binding.setContactOwnerButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                //set chat target id
                sellingViewModel.initiate_chat();

                Intent createNewChatRoom = new Intent(getApplicationContext(), ChatRoomActivity.class);
                BuyingItemDetailedActivity.this.startActivity(createNewChatRoom);
            }
        });
    }

    //endregion Methods
}
