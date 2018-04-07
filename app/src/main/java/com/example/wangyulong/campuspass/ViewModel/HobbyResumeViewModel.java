package com.example.wangyulong.campuspass.ViewModel;

import android.databinding.ObservableField;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.wangyulong.campuspass.Events.HobbyResumeListRefreshEventListener;
import com.example.wangyulong.campuspass.Helper.HobbyResumeCollectionHelper;
import com.example.wangyulong.campuspass.Loader.ComplexDataLoader;
import com.example.wangyulong.campuspass.Model.DetailHobbyModel;
import com.example.wangyulong.campuspass.Model.HobbyResumeModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by wangyulong on 7/4/18.
 */

public class HobbyResumeViewModel extends BasicViewModel
{
    //region Fields and Const
    private static HobbyResumeViewModel _instance = null;
    private HobbyResumeCollectionHelper _hobbyResumeCollectionHelper;
    private HobbyResumeModel resume_model;
    private DetailHobbyModel detail_hobby_model;
    private String hobby_of_interest;
    private ComplexDataLoader _dataLoader;
    private Uri photo_uri;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference("hobby_resumes");

    public ObservableField<String> resume_title = new ObservableField<>(new String(""));
    public ObservableField<String> resume_descr = new ObservableField<>(new String(""));
    public ObservableField<String> resume_price = new ObservableField<>(new String(""));
    public ObservableField<Boolean> upload_btn_visible = new ObservableField<>(false);
    public ObservableField<Boolean> done_btn_visible = new ObservableField<>(false);
    //endregion Fields and Const

    //region Properties
    public static HobbyResumeViewModel hobbyResumeViewModel()
    {
        if (_instance == null)
        {
            _instance = new HobbyResumeViewModel();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private HobbyResumeViewModel()
    {
        //init
        this._hobbyResumeCollectionHelper = HobbyResumeCollectionHelper.hobbyResumeCollectionHelper();
        this._dataLoader = ComplexDataLoader.complexDataLoader();
    }
    //endregion Constructor

    //region Methods
    public void set_current_detail_hobby(DetailHobbyModel detail_hobby_model)
    {
        this.detail_hobby_model = detail_hobby_model;
        this.hobby_of_interest = detail_hobby_model.getDetail_hobby_name();

        //debug
        Log.d("debug -> ", "selected hobby -> " + this.detail_hobby_model.getDetail_hobby_name());
        this.resume_title.set(this.hobby_of_interest);
        this.resume_descr.set(new String(""));
        this.resume_price.set(new String(""));
    }

    public void load_hobby_resumes()
    {
        this._dataLoader.load_hobby_resume_from_database(this.detail_hobby_model.getDetail_hobby_name());
    }

    public void set_photo_to_upload(Uri uri)
    {
        //debug
        Log.d("debug -> ", "upload photo Uri -> " + uri);

        this.photo_uri = uri;

        //show upload button
        this.upload_btn_visible.set(true);
    }

    public void reset()
    {
        this._hobbyResumeCollectionHelper.reset_collection();
    }

    public Uri get_photo_to_upload()
    {
        return this.photo_uri;
    }

    public void upload_complete()
    {
        this.done_btn_visible.set(true);
    }

    public void reset_button_visibility_status()
    {
        this.upload_btn_visible.set(false);
        this.done_btn_visible.set(false);
    }

    public ArrayList<HobbyResumeModel> get_full_list()
    {
        return this._hobbyResumeCollectionHelper.get_full_resume_list();
    }

    public void push_to_database()
    {
        HobbyResumeModel resume = new HobbyResumeModel();

        String owner_id = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        String resume_id = UUID.randomUUID().toString();

        resume.setHobby_resume_title(this.resume_title.get());
        resume.setHobby_resume_descr(this.resume_descr.get());
        resume.setHobby_resume_price(this.resume_price.get());
        resume.setHobby_resume_photo_uri(this.photo_uri.toString());
        resume.setHobby_resume_owner_id(owner_id);
        resume.setHobby_resume_entry_id(resume_id);

        Log.d("debug -> ", "uploading new resume");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("hobby_resumes");
        ref.child(this.hobby_of_interest).child(resume_id).setValue(resume).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                Log.d("debug -> ", "resume upload completed");
            }
        });
    }

    public void setHobbyResumeListRefreshListener(HobbyResumeListRefreshEventListener listener)
    {
        this._dataLoader.setHobbyResumeListRefreshEventListener(listener);
    }
    //endregion Methods
}
