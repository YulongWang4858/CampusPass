package com.example.wangyulong.campuspass.Activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.databinding.DetailHobbyContentPageBinding;

public class DetailHobbyActivity extends AppCompatActivity
{

    //region Fields and Const
    private DetailHobbyContentPageBinding binding;
    //endregion Fields and Const

    //region Override
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_hobby_content_page);

        onCreateBinding();
    }

    //endregion Override

    //region Methods
    private void onCreateBinding()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.detail_hobby_content_page);

        //initialize RecyclerView
        
    }
    //endregion Methods
}
