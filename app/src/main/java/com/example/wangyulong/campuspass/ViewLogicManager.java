package com.example.wangyulong.campuspass;

import android.databinding.ObservableField;

import com.example.wangyulong.campuspass.Constant.Category;

/**
 * Created by wangyulong on 04/03/18.
 */

public class ViewLogicManager
{
    //region Fields and Const
    private static ViewLogicManager _instance = null;
    public ObservableField<Category.ActivityState>  _tab1_currentState = new ObservableField<Category.ActivityState> (Category.ActivityState.INITIAL);
    public ObservableField<Category.ActivityState>  _tab2_currentState = new ObservableField<Category.ActivityState> (Category.ActivityState.INITIAL);
    public ObservableField<Category.ActivityState>  _tab3_currentState = new ObservableField<Category.ActivityState> (Category.ActivityState.INITIAL);
    //endregion Fields and Const

    //region Properties
    public static ViewLogicManager viewLogicManager()
    {
        if (_instance == null)
        {
            _instance = new ViewLogicManager();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private ViewLogicManager()
    {

    }
    //endregion Constructor

    //region Methods
    public Category.ActivityState get_tab1_currentState()
    {
        return _tab1_currentState.get();
    }

    public void set_tab1_currentState(Category.ActivityState newState)
    {
        _tab1_currentState.set(newState);
    }
    //endregion Methods
}
