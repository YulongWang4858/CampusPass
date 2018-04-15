package com.example.wangyulong.campuspass.Activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.NewTeamViewModel;
import com.example.wangyulong.campuspass.databinding.NewCareerResumePageBinding;
import com.example.wangyulong.campuspass.databinding.NewCareerTeamPageBinding;

public class NewCareerTeamActivity extends AppCompatActivity
{
    //region Fields and Const
    private NewCareerTeamPageBinding binding;
    private NewTeamViewModel newTeamViewModel = null;
    //endregion Fields and Const

    //region Override
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_career_team_page);
        newTeamViewModel = NewTeamViewModel.newTeamViewModel();

        onCreateBinding();
    }

    protected void onResume()
    {
        super.onResume();

        //toggle delete button visibility
        if (this.newTeamViewModel != null)
        {
            this.newTeamViewModel.hide_delete_button();
        }
    }
    //endregion Override

    //region Methods
    private void onCreateBinding()
    {
        this.binding = DataBindingUtil.setContentView(this, R.layout.new_career_team_page);
        this.binding.setNewTeamVM(newTeamViewModel);

        bindingButtons();
    }

    private void bindingButtons()
    {
        this.binding.setNewTeamCancelButtonClicked(new ClickListener()
        {
            @Override
            public void onClick()
            {
                finish();
            }
        });

        this.binding.setNewTeamDoneButtonClicked(new ClickListener()
        {
            @Override
            public void onClick()
            {
                newTeamViewModel.upload_new_team();
            }
        });

        this.binding.setNewTeamDeleteButtonClicked(new ClickListener()
        {
            @Override
            public void onClick()
            {
                newTeamViewModel.delete_current_entry();
            }
        });
    }
    //endregion Methods
}
