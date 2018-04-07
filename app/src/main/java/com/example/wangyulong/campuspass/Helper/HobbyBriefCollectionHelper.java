package com.example.wangyulong.campuspass.Helper;

import com.example.wangyulong.campuspass.Model.DetailHobbyModel;
import com.example.wangyulong.campuspass.ViewModel.HobbyBriefViewModel;

import java.util.ArrayList;

/**
 * Created by wangyulong on 6/4/18.
 */

public class HobbyBriefCollectionHelper extends BasicCollectionHelper
{
    //region Fields and Const
    private static HobbyBriefCollectionHelper _instance = null;
    private ArrayList<DetailHobbyModel> brief_hobby_list;
    //endregion Fields and Const

    //region Constructor
    private HobbyBriefCollectionHelper()
    {
        //init
        this.brief_hobby_list = new ArrayList<>();
    }
    //endregion Constructor

    //region Methods
    public static HobbyBriefCollectionHelper hobbyBriefCollectionHelper()
    {
        if (_instance == null)
        {
            _instance = new HobbyBriefCollectionHelper();
        }

        return _instance;
    }

    public ArrayList<DetailHobbyModel> get_full_list()
    {
        return this.brief_hobby_list;
    }

    public void reset_list()
    {
        this.brief_hobby_list = new ArrayList<>();
    }

    public void add_new_item_to_collection(DetailHobbyModel hobby_model)
    {
        this.brief_hobby_list.add(hobby_model);
    }

    public boolean check_existance(DetailHobbyModel hobby_model)
    {
        for (DetailHobbyModel hobby : this.brief_hobby_list)
        {
            if (hobby.getDetail_hobby_name().equals(hobby_model.getDetail_hobby_name()))
            {
                return true;
            }
        }

        return false;
    }
    //endregion Methods
}
