package com.example.wangyulong.campuspass.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.RegisterViewModel;
import com.example.wangyulong.campuspass.databinding.RegisterPageBinding;

/**
 * Created by wangyulong on 22/02/18.
 */

public class RegisterActivity extends AppCompatActivity
{
    //region Fields and Consts
    private RegisterPageBinding binding;
    //endregion Fields and Consts

    //region Initialization
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        //initialize binding
        onCreateBinding();
    }

    //Configure Databinding
    protected void onCreateBinding()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.register_page);
        binding.setRegisterVM(RegisterViewModel.registerViewModel());

        //Button Events
        binding.setDoneButtonClickListener(new ClickListener()
        {
            public void onClick()
            {
                //back to Login page
                finish();
            }
        });
    }
}
