package com.example.wangyulong.campuspass.Activity;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.Constant.Category;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewLogicManager;
import com.example.wangyulong.campuspass.databinding.MainmenuPageBinding;

import android.view.View;
import android.widget.TabHost;

/**
 * Created by wangyulong on 26/02/18.
 */

public class MainMenuActivity extends ActivityGroup
{

    //region Fields and Const
    protected MainmenuPageBinding mainmenuPageBinding;
    //endregion Fields and Const

    //region Init
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu_page);

        //initialize binding
        onCreateBinding();

        //initialize UI elements
        initUIElements();
    }
    //endregion Init

    protected void onCreateBinding()
    {
        mainmenuPageBinding = DataBindingUtil.setContentView(this, R.layout.mainmenu_page);

        buttonBinding();
    }

    protected void initUIElements()
    {
        initialTabSettings();
        initialColorolorSettings();
        initialVisibilitySettings();
    }

    //region Methods

    protected void initialTabSettings()
    {
        mainmenuPageBinding.tabHost.setup(this.getLocalActivityManager());

        //Tab 1
        TabHost.TabSpec spec = mainmenuPageBinding.tabHost.newTabSpec("Easy&Get");
        spec.setContent(new Intent(getApplicationContext(), EasyGetActivity.class));
        spec.setIndicator("Easy&Get");
        mainmenuPageBinding.tabHost.addTab(spec);

        //Tab 2
        spec = mainmenuPageBinding.tabHost.newTabSpec("Easy&Earn");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Easy&Earn");
        mainmenuPageBinding.tabHost.addTab(spec);

        //Tab 3
        spec = mainmenuPageBinding.tabHost.newTabSpec("Easy&Excel");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Easy&Excel");
        mainmenuPageBinding.tabHost.addTab(spec);
    }

    protected void initialColorolorSettings()
    {
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#3F51B5"));
        getActionBar().setBackgroundDrawable(colorDrawable);
    }

    protected void initialVisibilitySettings()
    {
        mainmenuPageBinding.goBackTxtButton.setVisibility(View.INVISIBLE);
        mainmenuPageBinding.goBackImg.setVisibility(View.INVISIBLE);

        ViewLogicManager.viewLogicManager()._tab1_currentState.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback()
        {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId)
            {
                if (((ObservableField<Category.ActivityState>) sender).get() != Category.ActivityState.INITIAL)
                {
                    mainmenuPageBinding.goBackTxtButton.setVisibility(View.VISIBLE);
                    mainmenuPageBinding.goBackImg.setVisibility(View.VISIBLE);
                }
            }
        });

        ViewLogicManager.viewLogicManager()._tab2_currentState.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback()
        {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId)
            {
                if (((ObservableField<Category.ActivityState>) sender).get() != Category.ActivityState.INITIAL)
                {
                    mainmenuPageBinding.goBackTxtButton.setVisibility(View.VISIBLE);
                    mainmenuPageBinding.goBackImg.setVisibility(View.VISIBLE);
                }
            }
        });

        ViewLogicManager.viewLogicManager()._tab3_currentState.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback()
        {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId)
            {
                if (((ObservableField<Category.ActivityState>) sender).get() != Category.ActivityState.INITIAL)
                {
                    mainmenuPageBinding.goBackTxtButton.setVisibility(View.VISIBLE);
                    mainmenuPageBinding.goBackImg.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    protected void buttonBinding()
    {
        //TODO: Implement when ready
        mainmenuPageBinding.setGoBackButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                backToPrevious();
            }
        });
    }

    protected void backToPrevious()
    {
        //check for previous page
        switch (mainmenuPageBinding.tabHost.getCurrentTab())
        {
            case 0: easyGetTabLogic(ViewLogicManager.viewLogicManager().get_tab1_currentState());
            case 1:
            default:
        }
    }

    protected void easyGetTabLogic(Category.ActivityState state)
    {
        switch (state)
        {
            case INITIAL:
            default: {

            }
        }
    }

    //endregion Methods
}
