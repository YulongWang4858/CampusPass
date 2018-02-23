package com.example.wangyulong.campuspass.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.MenuViewModel;
import com.example.wangyulong.campuspass.databinding.BuyingPageBinding;
import com.example.wangyulong.campuspass.databinding.MenuPageBinding;

/**
 * Created by wangyulong on 22/02/18.
 */

public class MenuActivity extends AppCompatActivity
{

    //region Fields and Const
    private MenuPageBinding binding;
    //endregion Fields and Const

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_page);

        //initialize binding
        onCreateBinding();
    }

    private void onCreateBinding()
    {
        this.binding = DataBindingUtil.setContentView(this, R.layout.menu_page);
        this.binding.setMenuVM(MenuViewModel.menuViewModel());

        buttonBinding();
    }

    protected void buttonBinding()
    {
        this.binding.setEasyGetButtonClickListener(new ClickListener()
        {
            public void onClick()
            {
                MenuViewModel.menuViewModel().setSelection(0);
            }
        });

        this.binding.setNeedAHandButtonClickListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                MenuViewModel.menuViewModel().setSelection(1);
            }
        });

        this.binding.setCareerReadyButtonClickListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                MenuViewModel.menuViewModel().setSelection(2);
            }
        });

        this.binding.setBuyingButtonClickListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                Intent toBuying = new Intent(getApplicationContext(), BuyingActivity.class);
                MenuActivity.this.startActivity(toBuying);
            }
        });
    }
}
