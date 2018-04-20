package com.example.wangyulong.campuspass.Helper;

import android.util.Log;
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

    public boolean check_user_participation(String user_id)
    {
        for (HobbyResumeModel resume : this.hobby_resume_list)
        {
            if (resume.getHobby_resume_owner_id().equals(user_id))
            {
                return true;
            }
        }

        return false;
    }

    public void change_hobby_resume(HobbyResumeModel hobby)
    {
        for (HobbyResumeModel hobby_model : this.hobby_resume_list)
        {
            if (hobby_model.getHobby_resume_entry_id().equals(hobby.getHobby_resume_entry_id()))
            {
                hobby_model = hobby;
            }
        }
    }

    public void remove_resume_from_collection(String id)
    {
        int index_of_reusme = -1;

        for (int cur = 0; cur < this.hobby_resume_list.size(); cur++)
        {
            if (this.hobby_resume_list.get(cur).getHobby_resume_entry_id().equals(id))
            {
                index_of_reusme = cur;

                break;
            }
        }

        if (index_of_reusme != -1)
        {
            //debug
            Log.d("collectionHelper -> ", "removing resume of id -> " + id);

            this.hobby_resume_list.remove(index_of_reusme);
        }
    }
    //endregion Methods
}
