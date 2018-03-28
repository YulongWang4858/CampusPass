package com.example.wangyulong.campuspass.Activity;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.RequestViewModel;
import com.example.wangyulong.campuspass.databinding.NewRequestPageBinding;

public class NewRequestActivity extends AppCompatActivity
{
    private NewRequestPageBinding binding;
    private RequestViewModel requestViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_request_page);
        requestViewModel = RequestViewModel.requestViewModel();

        onCreateBinding();
    }

    //region Methods

    protected void onCreateBinding()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.new_request_page);
        binding.setRequestVM(requestViewModel);
        bindSpinners();
        bindButtons();
    }

    protected void bindSpinners()
    {
        binding.requestCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                String selected_category = adapterView.getItemAtPosition(i).toString();

                requestViewModel.set_currently_selected_request_category(selected_category);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
                //TODO: Show warning here
            }
        });
    }

    protected void bindButtons()
    {
        binding.setUploadNewRequestButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                //TODO: Push to database
                requestViewModel.create_new_request();
            }
        });
    }

    //endregion Methods
}
