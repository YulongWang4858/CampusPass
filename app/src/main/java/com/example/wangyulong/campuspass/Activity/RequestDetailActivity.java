package com.example.wangyulong.campuspass.Activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.Events.MyRequestListRefreshEventListener;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.RequestViewModel;
import com.example.wangyulong.campuspass.databinding.RequestDetailedPageBinding;

public class RequestDetailActivity extends AppCompatActivity
{

    //region Fields and Const
    private RequestDetailedPageBinding binding;
    private RequestViewModel requestViewModel;
    //endregion Fields and Const

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_detailed_page);
        requestViewModel = RequestViewModel.requestViewModel();

        onCreateBinding();
    }


    //region Methods
    protected void onCreateBinding()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.request_detailed_page);
        binding.setRequestVM(requestViewModel);

        bindButtons();
    }

    protected void bindButtons()
    {
        binding.setUpdateButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                requestViewModel.update_request_entry();
            }
        });
    }
    //endregion Methods
}
