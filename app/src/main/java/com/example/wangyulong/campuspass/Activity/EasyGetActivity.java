package com.example.wangyulong.campuspass.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.Constant.Category;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewLogicManager;
import com.example.wangyulong.campuspass.ViewModel.EasyGetViewModel;
import com.example.wangyulong.campuspass.databinding.BuyingPageBinding;
import com.example.wangyulong.campuspass.databinding.EasyGetMainpageBinding;

public class EasyGetActivity extends AppCompatActivity
{
    EasyGetMainpageBinding easyGetMainpageBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.easy_get_mainpage);

        onCreateBinding();
    }

    protected void onCreateBinding()
    {
        easyGetMainpageBinding = DataBindingUtil.setContentView(this, R.layout.easy_get_mainpage);
        easyGetMainpageBinding.setEasyGetVM(EasyGetViewModel.easyGetViewModel());

        bindingButtons();
    }

    protected void bindingButtons()
    {
        easyGetMainpageBinding.setBuyingButtonClickListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                ViewLogicManager.viewLogicManager().set_tab1_currentState(Category.ActivityState.DEEP);

                Intent toBuyingPage = new Intent(EasyGetActivity.this, BuyingActivity.class);
                EasyGetActivity.this.startActivity(toBuyingPage);
            }
        });

        easyGetMainpageBinding.setRequestingButtonClickListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                //TODO: Implement
            }
        });

        easyGetMainpageBinding.setSellingButtonClickListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                //TODO: Implement
                Intent toSelling = new Intent(getApplicationContext(), SellingActivity.class);
                EasyGetActivity.this.startActivity(toSelling);
            }
        });
    }
}
