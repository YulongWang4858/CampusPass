package com.example.wangyulong.campuspass.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.RequestViewModel;
import com.example.wangyulong.campuspass.databinding.RequestPageBinding;

public class RequestActivity extends AppCompatActivity
{

    RequestPageBinding binding;
    RequestViewModel requestViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_page);

        onCreateBinding();
        initializeRequestList();
    }

    //region Methods
    protected void onCreateBinding()
    {
        requestViewModel = RequestViewModel.requestViewModel();

        binding = DataBindingUtil.setContentView(this, R.layout.request_page);
        binding.setRequestVM(this.requestViewModel);
        bindingButtons();
    }

    protected void bindingButtons()
    {
        binding.setNewRequestButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                //TODO: Link to NewReqActivity
                Intent toNewRequestPage = new Intent(RequestActivity.this, NewRequestActivity.class);
                RequestActivity.this.startActivity(toNewRequestPage);
            }
        });

        binding.setMyRequestButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                //TODO: Link to MyReqActivity
                Intent toMyRequestPage = new Intent(RequestActivity.this, MyRequestActivity.class);
                RequestActivity.this.startActivity(toMyRequestPage);
            }
        });
    }

    protected void initializeRequestList()
    {

    }
    //endregion Methods
}
