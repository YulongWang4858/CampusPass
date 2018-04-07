package com.example.wangyulong.campuspass.Helper;

import android.view.inputmethod.BaseInputConnection;

import com.example.wangyulong.campuspass.Events.HobbyBriefListRefreshEventListener;
import com.example.wangyulong.campuspass.Model.HobbyResumeModel;

import java.util.ArrayList;

/**
 * Created by wangyulong on 7/4/18.
 */

public class HobbyResumeCollectionHelper extends BasicCollectionHelper
{
    //region Fields and Const
    private static HobbyResumeCollectionHelper _instance = null;
    private ArrayList<HobbyResumeModel> hobby_resume_list;
    //endregion Fields and Const

    //region Properties
    public static HobbyResumeCollectionHelper hobbyResumeCollectionHelper()
    {
        if (_instance == null)
        {
            _instance = new HobbyResumeCollectionHelper();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private HobbyResumeCollectionHelper()
    {
        //init
        hobby_resume_list = new ArrayList<>();
    }
    //endregion Constructor

    //region Methods
    public void add_item_to_collection(HobbyResumeModel resume)
    {
        this.hobby_resume_list.add(resume);
    }

    public ArrayList<HobbyResumeModel> get_full_resume_list()
    {
        return this.hobby_resume_list;
    }

    public boolean check_existance(HobbyResumeModel resume_model)
    {
        for (HobbyResumeModel resume : this.hobby_resume_list)
        {
            if (resume.getHobby_resume_entry_id().equals(resume_model.getHobby_resume_entry_id()))
            {
                return true;
            }
        }

        return false;
    }

    public void reset_collection()
    {
        this.hobby_resume_list = new ArrayList<>();
    }
    //endregion Methods
}
