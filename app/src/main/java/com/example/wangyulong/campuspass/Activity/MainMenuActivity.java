package com.example.wangyulong.campuspass.Activity;

import android.app.ActivityGroup;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.databinding.BuyingPageBinding;
import com.example.wangyulong.campuspass.databinding.MainmenuPageBinding;

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

        //intialize UI
        tabHostInitalization();

        colorSettings();
    }
    //endregion Init

    //region Methods
    protected void onCreateBinding()
    {
        mainmenuPageBinding = DataBindingUtil.setContentView(this, R.layout.mainmenu_page);

        buttonBinding();
    }

    protected void tabHostInitalization()
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

    protected void buttonBinding()
    {

    }

    protected void colorSettings()
    {
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#3F51B5"));
        getActionBar().setBackgroundDrawable(colorDrawable);
    }

    //endregion Methods
}
