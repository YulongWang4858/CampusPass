package com.example.wangyulong.campuspass.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.EasyExcelViewModel;
import com.example.wangyulong.campuspass.databinding.CareerScreenChoicePageBinding;

public class CareerScreenChoiceActivity extends AppCompatActivity
{

    //region Fields and Const
    private CareerScreenChoicePageBinding binding;
    private EasyExcelViewModel easyExcelViewModel;
    //endregion Fields and Const

    //region Override
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.career_screen_choice_page);

        onCreateBinding();
    }
    //endregion Override

    //region Methods
    private void onCreateBinding()
    {
//        this.easyExcelViewModel = EasyExcelViewModel.easyExcelViewModel();
//
//        this.binding.setEasyExcelVM(this.easyExcelViewModel);

        binding = DataBindingUtil.setContentView(this, R.layout.career_screen_choice_page);

        //bind image onClick
        this.binding.setCareerResumeImgClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                Intent toCareerResumeListPage = new Intent(getApplicationContext(), CareerResumeListActivity.class);
                CareerScreenChoiceActivity.this.startActivity(toCareerResumeListPage);
            }
        });

        //TODO: add click listener for TeamUp
    }
    //endregion Methods
}
