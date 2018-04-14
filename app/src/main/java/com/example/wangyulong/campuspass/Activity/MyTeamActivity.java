package com.example.wangyulong.campuspass.Activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.NewTeamViewModel;
import com.example.wangyulong.campuspass.databinding.NewCareerTeamPageBinding;

public class MyTeamActivity extends AppCompatActivity
{
    //region Fields and Const
    private NewCareerTeamPageBinding binding;
    private NewTeamViewModel newTeamViewModel;
    //endregion Fields and Const

    //region Override
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_career_team_page);

        onCreateBinding();
    }

    private void onCreateBinding()
    {
        this.binding = DataBindingUtil.setContentView(this, R.layout.new_career_team_page);
        this.newTeamViewModel = NewTeamViewModel.newTeamViewModel();

        this.binding.setNewTeamVM(this.newTeamViewModel);
    }
    //endregion Override
}
