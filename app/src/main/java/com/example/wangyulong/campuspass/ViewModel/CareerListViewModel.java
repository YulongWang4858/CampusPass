package com.example.wangyulong.campuspass.ViewModel;

import android.databinding.ObservableField;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.BoringLayout;
import android.util.Log;

import com.example.wangyulong.campuspass.Events.CareerResumeListRefreshEventListener;
import com.example.wangyulong.campuspass.Helper.BasicCollectionHelper;
import com.example.wangyulong.campuspass.Helper.CareerResumeCollectionHelper;
import com.example.wangyulong.campuspass.Loader.ComplexDataLoader;
import com.example.wangyulong.campuspass.Model.CareerModel;
import com.example.wangyulong.campuspass.Model.CareerResumeModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by wangyulong on 10/4/18.
 */

public class CareerListViewModel extends BasicViewModel
{
    //region Fields and Const
    private static CareerListViewModel _instance = null;
    private CareerResumeModel selected_resume;
    private CareerResumeModel new_resume;
    private CareerModel selected_career;
    private String career_category;
    private String owner_name;
    private String new_resume_id;
    private ComplexDataLoader dataLoader;

    //controls
    public ObservableField<Boolean> is_uploadbtn_visible = new ObservableField<>(false);
    public ObservableField<Boolean> is_donebtn_visible = new ObservableField<>(false);

    //binding
    public ObservableField<String> career_title = new ObservableField<>(new String(""));
    public ObservableField<String> career_gender = new ObservableField<>(new String(""));
    public ObservableField<String> career_major = new ObservableField<>(new String(""));
    public ObservableField<String> career_about_self = new ObservableField<>(new String(""));
    public ObservableField<String> career_experience = new ObservableField<>(new String(""));
    public ObservableField<String> career_sample_product_link = new ObservableField<>(new String(""));
    public ObservableField<String> career_resume_photo_uri = new ObservableField<>(new String(""));
    public ObservableField<String> career_owner_name = new ObservableField<>(new String(""));

    private CareerResumeCollectionHelper careerResumeCollectionHelper;
    //endregion Fields and Const

    //region Properties
    public static CareerListViewModel careerListViewModel()
    {
        if (_instance == null)
        {
            _instance = new CareerListViewModel();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private CareerListViewModel()
    {
        this.dataLoader = ComplexDataLoader.complexDataLoader();
        this.careerResumeCollectionHelper = CareerResumeCollectionHelper.careerResumeCollectionHelper();
    }
    //endregion Constructor

    //region Methods
    public void set_current_resume_selection(CareerResumeModel resume)
    {
        this.selected_resume = resume;
    }

    public void set_selected_career(CareerModel career)
    {
        this.selected_career = career;

        //debug
        Log.d("debug -> ", "creating new resume with category -> " + career.getCareer_title());
        this.new_resume = new CareerResumeModel();
        this.new_resume.setCareer_category(career.getCareer_title());

        this.get_current_user_name();
        this.new_resume_id = UUID.randomUUID().toString();

        this.career_category = career.getCareer_title();
    }

    public void set_new_resume_gender(String gender)
    {
        this.career_gender.set(gender);

        //debug
        Log.d("debug -> ", "gender set to be -> " + gender);
    }

    public Uri get_photo_to_upload()
    {
        return Uri.parse(this.new_resume.getCareer_resume_photo_uri());
    }

    public void set_photo_to_upload(Uri photo_uri)
    {
        this.new_resume.setCareer_resume_photo_uri(photo_uri.toString());
    }

    public void change_upload_visibility(boolean visibility)
    {
        this.is_uploadbtn_visible.set(visibility);
    }

    public void upload_complete()
    {
        this.is_donebtn_visible.set(true);
    }

    public void upload_resume_to_datebase()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("careers").child(this.new_resume.career_category);

        this.new_resume.setCareer_name(this.career_title.get());
        this.new_resume.setCareer_gender(this.career_gender.get());
        this.new_resume.setCareer_about_self(this.career_about_self.get());
        this.new_resume.setCareer_experience(this.career_experience.get());
        this.new_resume.setCareer_major(this.career_major.get());
        this.new_resume.setCareer_owner_id(FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
        this.new_resume.setCareer_owner_name(this.owner_name);
        this.new_resume.setCareer_resume_id(this.new_resume_id);
        this.new_resume.setCareer_sample_product_link(this.career_sample_product_link.get());

        //upload
        ref.child(this.new_resume.getCareer_resume_id()).setValue(this.new_resume).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                showOnSnackBar("resume upload success");
            }
        });

        showOnSnackBar("Uploading Success!");
    }

    private void get_current_user_name()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("user_name");

        ref.addValueEventListener(new ValueEventListener()
        {
            String name;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                name = dataSnapshot.getValue(String.class);

                set_user_name(name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                //do nothing
            }
        });
    }

    public void set_user_name(String name)
    {
        this.owner_name = name;

        Log.d("user name -> ", this.owner_name);
    }

    public void load_resume_list()
    {
        dataLoader.load_career_resume_from_database(this.career_category);
    }

    public ArrayList<CareerResumeModel> get_full_list()
    {
        return this.careerResumeCollectionHelper.get_full_resume_list();
    }

    public void setCareerResumeRefreshEvent(CareerResumeListRefreshEventListener listRefreshEventListener)
    {
        this.dataLoader.setCareerResumeListRefreshEventListener(listRefreshEventListener);
    }

    public boolean user_already_submitted(String user_id)
    {
        return this.careerResumeCollectionHelper.contains_resume_owned_by_id(user_id);
    }

    public void set_current_resume_to_manage(String user_id)
    {
        this.new_resume = this.careerResumeCollectionHelper.retrieve_resume_owned_by_id(user_id);

        this.career_title.set(this.new_resume.getCareer_name());
        this.career_category = this.new_resume.getCareer_category();
        this.career_major.set(this.new_resume.getCareer_major());
        this.career_about_self.set(this.new_resume.getCareer_about_self());
        this.career_experience.set(this.new_resume.getCareer_experience());
        this.career_sample_product_link.set(this.new_resume.getCareer_sample_product_link());
        this.new_resume_id = this.new_resume.getCareer_resume_id();
    }

    public void reset()
    {
        this.careerResumeCollectionHelper.reset_collection();
    }
    //endregion Methods
}
