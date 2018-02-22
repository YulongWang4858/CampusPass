package com.example.wangyulong.campuspass.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.wangyulong.campuspass.R;
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
    }
}
