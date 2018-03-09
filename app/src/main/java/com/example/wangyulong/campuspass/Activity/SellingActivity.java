package com.example.wangyulong.campuspass.Activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.SellingViewModel;
import com.example.wangyulong.campuspass.databinding.SellingPageBinding;

public class SellingActivity extends AppCompatActivity
{

    SellingPageBinding binding;
    SellingViewModel sellingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selling_page);

        //init
        sellingViewModel = SellingViewModel.sellingViewModel();

        onCreateBinding();
    }

    protected void onCreateBinding()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.selling_page);

        binding.setSellingVM(sellingViewModel);
    }
}
