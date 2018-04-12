package com.example.wangyulong.campuspass.ViewModel;

import android.databinding.ObservableField;
import android.util.Log;

import com.example.wangyulong.campuspass.Model.CareerResumeModel;

/**
 * Created by wangyulong on 12/4/18.
 */

public class CareerResumeDetailViewModel extends BasicViewModel
{
    //region Fields and Const
    private static CareerResumeDetailViewModel _instance = null;
    private CareerResumeModel resume;

    //binding
    public ObservableField<String> title = new ObservableField<>(new String(""));
    public ObservableField<String> gender = new ObservableField<>(new String(""));
    public ObservableField<String> about_self = new ObservableField<>(new String(""));
    public ObservableField<String> experience = new ObservableField<>(new String(""));
    public ObservableField<String> photo_uri = new ObservableField<>(new String(""));
    public ObservableField<String> major = new ObservableField<>(new String(""));
    public ObservableField<String> links = new ObservableField<>(new String(""));
    public ObservableField<String> section_about_self_name = new ObservableField<>(new String(""));

    //endregion Fields and Const

    //region Properties
    public static CareerResumeDetailViewModel CareerResumeDetailViewModel()
    {
        if (_instance == null)
        {
            _instance = new CareerResumeDetailViewModel();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private CareerResumeDetailViewModel()
    {
        //init
    }
    //endregion Constructor

    //region Methods
    public void set_selected_resume(CareerResumeModel resume)
    {
        this.resume = resume;

        //debug
        Log.d("debug -> ", "selected resume with title " + resume.getCareer_name() + " owned by " + resume.getCareer_owner_name());

        //fill info
        this.title.set(resume.getCareer_name());
        this.gender.set("Gender: " + resume.getCareer_gender());
        this.major.set("Major: " + resume.getCareer_major());
        this.about_self.set(resume.getCareer_about_self());
        this.section_about_self_name.set("About " + resume.getCareer_owner_name());
        this.experience.set(resume.getCareer_experience());
        this.photo_uri.set(resume.getCareer_resume_photo_uri());
    }

    public String get_resume_photo_uri()
    {
        if (this.photo_uri.get().equals(""))
        {
            return null;
        }

        return this.photo_uri.get();
    }
    //endregion Methods
}
