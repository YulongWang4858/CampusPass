package com.example.wangyulong.campuspass.Activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.databinding.ParticipateHobbyPageBinding;

public class HobbyParticipateActivity extends AppCompatActivity
{
    //region Fields and Const
    private ParticipateHobbyPageBinding binding;
    //endregion Fields and Const

    //region Override
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participate_hobby_page);

        onCreateBinding();
    }
    //endregion Override

    //region Methods
    private void onCreateBinding()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.participate_hobby_page);

    }
    //endregion Methods
}
