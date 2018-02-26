package com.example.wangyulong.campuspass.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.databinding.MainmenuPageBinding;

import android.widget.TabHost;

/**
 * Created by wangyulong on 26/02/18.
 */

public class MainMenuActivity extends AppCompatActivity
{
    MainmenuPageBinding binding;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu_page);

        //initialize binding
        onCreateBinding();

        //intialize UI
        tabHostInitalization();

    }

    protected void onCreateBinding()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.mainmenu_page);

        buttonBinding();
    }

    protected void tabHostInitalization()
    {
        binding.tabHost.setup();

        //Tab 1
        TabHost.TabSpec spec = binding.tabHost.newTabSpec("Easy&Get");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Easy&Get");
        binding.tabHost.addTab(spec);

        //Tab 2
        spec = binding.tabHost.newTabSpec("Easy&Earn");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Easy&Earn");
        binding.tabHost.addTab(spec);

        //Tab 3
        spec = binding.tabHost.newTabSpec("Easy&Excel");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Easy&Excel");
        binding.tabHost.addTab(spec);


        //binding.tabHost.addTab();

    }

    protected void buttonBinding()
    {

    }
}
