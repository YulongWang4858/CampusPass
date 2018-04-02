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
import com.example.wangyulong.campuspass.ViewModel.RegisterViewModel;
import com.example.wangyulong.campuspass.databinding.MainmenuPageBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.view.View;
import android.widget.TabHost;

/**
 * Created by wangyulong on 26/02/18.
 */

public class MainMenuActivity extends ActivityGroup
{

    //region Fields and Const
    protected MainmenuPageBinding mainmenuPageBinding;
    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("user");
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
        initialDBSettings();
    }

    //region Methods
    protected void initialDBSettings()
    {
        databaseRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                RegisterViewModel.registerViewModel().fill_cur_user_info(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }

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
        spec.setContent(new Intent(getApplicationContext(), EasyEarnActivity.class));
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
    }

    protected void buttonBinding()
    {
        //TODO: Implement when ready
        mainmenuPageBinding.setNotificationButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                //TODO: Attach notification here
            }
        });

        mainmenuPageBinding.setUpdateInfoButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                Intent toUpdateInfoPage = new Intent(getApplicationContext(), UpdateInfoActivity.class);
                MainMenuActivity.this.startActivity(toUpdateInfoPage);
            }
        });

        mainmenuPageBinding.setChatBoxButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                //TODO: Attach Chatbox here
            }
        });
    }

    protected void backToPrevious()
    {
        //check for previous page
        switch (mainmenuPageBinding.tabHost.getCurrentTab())
        {
            case 0:
                easyGetTabLogic(ViewLogicManager.viewLogicManager().get_tab1_currentState());
            case 1:
            default:
        }
    }

    protected void easyGetTabLogic(Category.ActivityState state)
    {
        switch (state)
        {
            case INITIAL:
            default:
            {

            }
        }
    }

    //endregion Methods
}
