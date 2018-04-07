package com.example.wangyulong.campuspass.ViewModel;

import android.util.Log;

import com.example.wangyulong.campuspass.Events.HobbyBriefListRefreshEventListener;
import com.example.wangyulong.campuspass.Helper.HobbyBriefCollectionHelper;
import com.example.wangyulong.campuspass.Loader.ComplexDataLoader;
import com.example.wangyulong.campuspass.Model.DetailHobbyModel;

import java.util.ArrayList;

/**
 * Created by wangyulong on 6/4/18.
 */

public class HobbyBriefViewModel extends BasicViewModel
{
    //region Fields and Const
    private static HobbyBriefViewModel _instance = null;
    private HobbyBriefCollectionHelper hobbyBriefCollectionHelper;
    private ComplexDataLoader dataLoader;
    private String category;
    //endregion Fields and Const

    //region Constructor
    private HobbyBriefViewModel()
    {
        //init
        hobbyBriefCollectionHelper = HobbyBriefCollectionHelper.hobbyBriefCollectionHelper();
        dataLoader = ComplexDataLoader.complexDataLoader();
    }
    //endregion Constructor

    //region Properties
    public static HobbyBriefViewModel hobbyBriefViewModel()
    {
        if (_instance == null)
        {
            _instance = new HobbyBriefViewModel();
        }

        return _instance;
    }
    //endregion Properties

    //region Methods
    public ArrayList<DetailHobbyModel> get_full_detailed_hobby_list()
    {
        return this.hobbyBriefCollectionHelper.get_full_list();
    }

    public void load_brief_list()
    {
        this.dataLoader.load_detail_hobbies_from_database(this.category);
    }

    public void set_current_category(String category)
    {
        this.category = category;

        Log.d("debug -> ", "category of interest " + category);
        this.hobbyBriefCollectionHelper.reset_list();
    }

    public void setHobbyBriefRefreshEvent(HobbyBriefListRefreshEventListener listener)
    {
        this.dataLoader.setHobbyBriefListRefreshEventListener(listener);
    }
    //endregion Methods
}
