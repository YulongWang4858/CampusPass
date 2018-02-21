package com.example.wangyulong.campuspass;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangyulong.campuspass.ViewModel.LoginViewModel;
import com.example.wangyulong.campuspass.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize binding
        onCreateBinding();
    }


    //region Initialize

    //Configure Databinding
    protected void onCreateBinding()
    {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setLoginVM(LoginViewModel.loginViewModel());
    }
    //endregion Initialize
}
