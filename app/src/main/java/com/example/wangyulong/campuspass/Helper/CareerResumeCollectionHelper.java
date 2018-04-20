package com.example.wangyulong.campuspass.Helper;

import android.util.Log;

import com.example.wangyulong.campuspass.Model.CareerResumeModel;
import com.example.wangyulong.campuspass.ViewModel.BasicViewModel;
import com.example.wangyulong.campuspass.ViewModel.CareerListViewModel;

import java.util.ArrayList;

/**
 * Created by wangyulong on 10/4/18.
 */

public class CareerResumeCollectionHelper extends BasicViewModel
{
    //region Fields and Const
    private static CareerResumeCollectionHelper _instance = null;
    private ArrayList<CareerResumeModel> careerResumeList;
    //endregion Fields and Const

    //region Properties
    public static CareerResumeCollectionHelper careerResumeCollectionHelper()
    {
        if (_instance == null)
        {
            _instance = new CareerResumeCollectionHelper();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private CareerResumeCollectionHelper()
    {
        //init
        careerResumeList = new ArrayList<>();
    }
    //endregion Constructor

    //region Methods
    public void reset_collection()
    {
        this.careerResumeList = new ArrayList<>();
    }

    public void add_career_resume_to_collection(CareerResumeModel resumeModel)
    {
        this.careerResumeList.add(resumeModel);
    }

    public boolean check_existance(CareerResumeModel resumeModel)
    {
        for (CareerResumeModel resume : this.careerResumeList)
        {
            if (resume.getCareer_resume_id().equals(resumeModel.getCareer_resume_id()))
            {
                return true;
            }
        }

        return false;
    }

    public ArrayList<CareerResumeModel> get_full_resume_list()
    {
        return this.careerResumeList;
    }

    public boolean contains_resume_owned_by_id(String user_id)
    {
        for (CareerResumeModel resume : this.careerResumeList)
        {
            if (resume.getCareer_owner_id().equals(user_id))
            {
                //debug
                Log.d("debug -> ", "a resume has been found owned by this user");

                return true;
            }
        }

        return false;
    }

    public CareerResumeModel retrieve_resume_owned_by_id(String user_id)
    {
        for (CareerResumeModel resume : this.careerResumeList)
        {
            if (resume.getCareer_owner_id().equals(user_id))
            {
                //debug
                Log.d("debug -> ", "a resume has been found owned by this user");

                return resume;
            }
        }

        return null;
    }

    public void change_resume_in_collection(CareerResumeModel resume)
    {
        for (CareerResumeModel resumeModel : this.careerResumeList)
        {
            if (resumeModel.getCareer_resume_id().equals(resume.getCareer_resume_id()))
            {
                resumeModel = resume;
                break;
            }
        }
    }

    public void remove_team_from_list(String resume_id)
    {
        int index_to_remove = -1;

        for (int cur = 0; cur < this.careerResumeList.size(); cur++)
        {
            if (this.careerResumeList.get(cur).getCareer_resume_id().equals(resume_id))
            {
                index_to_remove = cur;

                break;
            }
        }

        if (index_to_remove != -1)
        {
            this.careerResumeList.remove(index_to_remove);

            //debug
            Log.d("collectionHelper -> ", "removing resume with id -> " + resume_id);
        }
    }
    //endregion Methods
}
