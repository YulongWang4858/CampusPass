package com.example.wangyulong.campuspass.Activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.CareerTeamListViewModel;
import com.example.wangyulong.campuspass.databinding.DetailCareerTeamPageBinding;

public class CareerTeamDetailActivity extends AppCompatActivity
{
    //region Fields and Const
    private DetailCareerTeamPageBinding binding;
    private CareerTeamListViewModel careerTeamListViewModel;
    //endregion Fields and Const

    //region Override
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_career_team_page);

        onCreateBinding();
    }
    //endregion Override

    //region Methods
    private void onCreateBinding()
    {
        this.binding = DataBindingUtil.setContentView(this, R.layout.detail_career_team_page);
        this.careerTeamListViewModel = CareerTeamListViewModel.careerTeamListViewModel();

        this.binding.setCareerTeamListVM(this.careerTeamListViewModel);
    }
    //endregion Methods
}
