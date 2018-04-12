package com.example.wangyulong.campuspass.Activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.HobbyResumeViewModel;
import com.example.wangyulong.campuspass.databinding.DetailHobbyContentPageBinding;
import com.example.wangyulong.campuspass.databinding.DetailHobbyResumePageBinding;

public class DetailHobbyResumeActivity extends AppCompatActivity
{
    //region Fields and Const
    private DetailHobbyResumePageBinding binding;
    private HobbyResumeViewModel hobbyResumeViewModel;
    //endregion Fields and Const

    //region Override
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_hobby_resume_page);

        onCreateBinding();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        this.displayImg();
    }
    //endregion Override

    //region Methods
    private void onCreateBinding()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.detail_hobby_resume_page);

        hobbyResumeViewModel = HobbyResumeViewModel.hobbyResumeViewModel();
        binding.setHobbyResumeVM(hobbyResumeViewModel);

        displayImg();
    }

    private void displayImg()
    {
        Glide.with(this).load(this.hobbyResumeViewModel.get_selected_resume_photo_uri()).into(binding.detailHobbyResumeImg);
    }
    //endregion Methods
}
