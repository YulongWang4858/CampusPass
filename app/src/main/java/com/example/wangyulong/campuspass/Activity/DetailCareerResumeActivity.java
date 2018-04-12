package com.example.wangyulong.campuspass.Activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.CareerResumeDetailViewModel;
import com.example.wangyulong.campuspass.databinding.CareerResumeDetailBinding;

public class DetailCareerResumeActivity extends AppCompatActivity
{
    //region Fields and Const
    private CareerResumeDetailBinding binding = null;
    private CareerResumeDetailViewModel careerResumeDetailViewModel = null;
    //endregio Fields and Const

    //region Override
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.career_resume_detail);

        this.onCreateBinding();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        //refresh photo
        if (this.careerResumeDetailViewModel != null && this.binding != null)
        {
            try
            {
                Glide.with(this).load(this.careerResumeDetailViewModel.get_resume_photo_uri()).into(binding.careerResumeSampleImg);
            }
            catch (NullPointerException e)
            {
                Log.d("debug -> ", "no image");
            }
        }
    }
    //endregion Override

    //region Methods
    private void onCreateBinding()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.career_resume_detail);

        this.careerResumeDetailViewModel = CareerResumeDetailViewModel.CareerResumeDetailViewModel();

        //bind variables
        binding.setCareerResumeDetailVM(this.careerResumeDetailViewModel);

        //bind image
        try
        {
            Glide.with(this).load(this.careerResumeDetailViewModel.get_resume_photo_uri()).into(binding.careerResumeSampleImg);
        }
        catch (NullPointerException e)
        {
            Log.d("debug -> ", "no image");
        }
    }
    //endregion Methods
}
