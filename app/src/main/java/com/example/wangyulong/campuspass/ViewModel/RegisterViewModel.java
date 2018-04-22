package com.example.wangyulong.campuspass.ViewModel;

import android.databinding.ObservableField;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.wangyulong.campuspass.Events.UserInfoLoadCompleteEventListener;
import com.example.wangyulong.campuspass.Events.UserPhotoUriDownloadedEventListener;
import com.example.wangyulong.campuspass.Loader.ComplexDataLoader;
import com.example.wangyulong.campuspass.Model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by wangyulong on 22/02/18.
 */

public class RegisterViewModel extends BasicViewModel
{
    //region Fields and Const
    private static RegisterViewModel _instance = null;
    private UserModel _new_user = new UserModel();
    private UserModel current_user = null;

    //binding
    public ObservableField<String> user_name = new ObservableField<>(new String(""));
    public ObservableField<String> user_email = new ObservableField<>(new String(""));
    public ObservableField<String> user_contact_info = new ObservableField<>(new String(""));
    public ObservableField<String> user_course_info = new ObservableField<>(new String(""));
    public ObservableField<String> user_pickup_address = new ObservableField<>(new String(""));
    public ObservableField<String> user_password = new ObservableField<>(new String(""));
    public ObservableField<String> user_career_info = new ObservableField<>(new String(""));
    public ObservableField<String> user_unique_id = new ObservableField<>(new String(""));
    private Uri user_profile_pic_uri;

    private String cur_user_name = new String("");
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseRef = database.getReference("user");
    private UserModel updated_user_model;
    private ComplexDataLoader dataLoader;
    private UserPhotoUriDownloadedEventListener userPhotoUriDownloadedEventListener = null;
    //endregion Fields and Const

    //region Properties
    public static RegisterViewModel registerViewModel()
    {
        if (_instance == null)
        {
            _instance = new RegisterViewModel();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private RegisterViewModel()
    {
        //init
        this.dataLoader = ComplexDataLoader.complexDataLoader();

        //register event listener
        this.dataLoader.setUserInfoLoadCompleteEventListener(new UserInfoLoadCompleteEventListener()
        {
            @Override
            public void onUserInfoLoadCompleteEventTrigger()
            {
                //store user info
                if (dataLoader.get_downloaded_user() != null)
                {
                    current_user = dataLoader.get_downloaded_user();

                    //fill in
                    user_name.set(current_user.getUser_name());
                    user_email.set(current_user.getUser_email());
                    user_unique_id.set(current_user.getUser_id());
                    user_password.set(current_user.getUser_password());
                    user_profile_pic_uri = Uri.parse(current_user.getUser_profile_pic_uri());
                    user_pickup_address.set(current_user.getUser_pickup_address());
                    user_career_info.set(current_user.getUser_career_info());
                    user_contact_info.set(current_user.getUser_contact());
                    user_course_info.set(current_user.getUser_student_info());

                    if (userPhotoUriDownloadedEventListener != null)
                    {
                        userPhotoUriDownloadedEventListener.onUserPhotoUriDownloadedEventTrigger();
                    }
                } else
                {
                    showOnSnackBar("Downloaded user data corrupted");

                    //TODO: Trigger re-download
                }
            }
        });
    }
    //endregion Constructor

    //region Methods
    public boolean VerifyUserInfoFormat()
    {
        return true;
    }

    public void upload_to_database()
    {
        String _database_user_identifier = firebaseAuth.getCurrentUser().getUid();

        this.current_user.setUser_name(this.user_name.get());
        this.current_user.setUser_email(this.user_email.get());
        this.current_user.setUser_student_info(this.user_course_info.get());
        this.current_user.setUser_career_info(this.user_career_info.get());
        this.current_user.setUser_pickup_address(this.user_pickup_address.get());
        this.current_user.setUser_contact(this.user_contact_info.get());
        this.current_user.setUser_profile_pic_uri(this.user_profile_pic_uri.toString());


        //upload to database
        databaseRef.child(_database_user_identifier).setValue(this.current_user)
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        //debug
                        Log.d("debug -> ", "upload complete");
                    }
                });
    }

    //This method is to create a user template and store in database
    public void init_database()
    {
        //init
        String _database_user_identifier = firebaseAuth.getCurrentUser().getUid();
        UserModel new_user = new UserModel();

        //fill skeleton
        new_user.setUser_name("");
        new_user.setUser_career_info("");
        new_user.setUser_contact("");
        new_user.setUser_email(this.firebaseAuth.getCurrentUser().getEmail());
        new_user.setUser_id(_database_user_identifier);
        new_user.setUser_password("*********");
        new_user.setUser_profile_pic_uri("");
        new_user.setUser_student_info("");
        new_user.setUser_pickup_address("");

        //upload skeleton
        databaseRef.child(_database_user_identifier).setValue(new_user);
    }

    public String get_login_user_name()
    {
        return "";
    }

    public void setUser_profile_pic_uri(Uri uri)
    {
        this.user_profile_pic_uri = uri;
    }

    public String getUser_profile_pic_uri()
    {
        if (this.user_profile_pic_uri == null)
        {
            return null;
        } else
        {
            return this.user_profile_pic_uri.toString();
        }
    }

    public void setUserPhotoUriDownloadedEventListener(UserPhotoUriDownloadedEventListener listener)
    {
        this.userPhotoUriDownloadedEventListener = listener;
    }
    //endregion Methods
}
