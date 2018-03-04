package com.example.wangyulong.campuspass.Activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.databinding.EasyGetMainpageBinding;

public class EasyGetActivity extends AppCompatActivity
{
    EasyGetMainpageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.easy_get_mainpage);

        onCreateBinding();
    }

    protected void onCreateBinding()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.easy_get_mainpage);
    }
}
