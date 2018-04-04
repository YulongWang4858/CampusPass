package com.example.wangyulong.campuspass.ViewModel;

import android.util.Log;

import com.example.wangyulong.campuspass.Events.HobbyCardViewRefreshListener;
import com.example.wangyulong.campuspass.Helper.HobbyCollectionHelper;
import com.example.wangyulong.campuspass.Loader.ComplexDataLoader;
import com.example.wangyulong.campuspass.Model.HobbyModel;

import java.util.ArrayList;

/**
 * Created by wangyulong on 4/4/18.
 */

public class EasyEarnViewModel extends BasicViewModel
{
    //region Fields and Const
    private static EasyEarnViewModel _instance = null;
    private HobbyCollectionHelper _hobby_collection_helper;
    private ComplexDataLoader _dataLoader;
    private HobbyModel _current_hobby;
    //endregion Fields and Const

    //region Properties
    public static EasyEarnViewModel easyEarnViewModel()
    {
        if (_instance == null)
        {
            _instance = new EasyEarnViewModel();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructors
    private EasyEarnViewModel()
    {
        //init
        this._hobby_collection_helper = HobbyCollectionHelper.hobbyCollectionHelper();
        this._dataLoader = ComplexDataLoader.complexDataLoader();

        this._dataLoader.load_hobbies_from_database();
    }
    //endregion Constructors

    //region Methods
    public void setHobbyCardViewEvent(HobbyCardViewRefreshListener listener)
    {
        this._dataLoader.setHobbyCardViewRefreshListener(listener);
    }

    public ArrayList<HobbyModel> get_complete_hobby_list()
    {
        return this._hobby_collection_helper.get_full_hobby_list();
    }

    public void set_current_hobby(HobbyModel hobbyModel)
    {
        this._current_hobby = hobbyModel;

        Log.d("selected -> ", this._current_hobby.getHobby_category());
    }
    //endregion Methods
}
