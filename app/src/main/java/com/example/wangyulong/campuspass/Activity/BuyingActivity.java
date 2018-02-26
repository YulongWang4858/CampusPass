package com.example.wangyulong.campuspass.Activity;

import android.os.Bundle;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.databinding.BuyingPageBinding;

/**
 * Created by wangyulong on 23/02/18.
 */


public class BuyingActivity extends AppCompatActivity
{

    BuyingPageBinding binding;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.menu_page);

        //initialize binding
        onCreateBinding();
    }

    protected void onCreateBinding()
    {
        //binding = DataBindingUtil.setContentView(this, R.layout.buying_page);
    }
}


