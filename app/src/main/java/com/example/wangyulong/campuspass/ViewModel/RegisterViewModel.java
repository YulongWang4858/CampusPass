package com.example.wangyulong.campuspass.ViewModel;

import android.databinding.ObservableField;
import android.util.Log;

import com.example.wangyulong.campuspass.Model.DatabaseUserModel;
import com.example.wangyulong.campuspass.Model.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

/**
 * Created by wangyulong on 22/02/18.
 */

public class RegisterViewModel extends BasicViewModel
{
    //region Fields and Consts
    private static RegisterViewModel _instance = null;
    private UserModel _new_user = new UserModel();

    public ObservableField<String> user_name = _new_user.user_name;
    public ObservableField<String> user_email = _new_user.user_email;
    public ObservableField<String> user_contact_info = _new_user.user_contact_info;
    public ObservableField<String> user_course_info = _new_user.user_course_info;
    public ObservableField<String> user_pickup_address = _new_user.user_pickup_address;
    public ObservableField<String> user_password = _new_user.user_password;
    public ObservableField<String> user_career_info = _new_user.user_career_info;
    public ObservableField<UUID> user_unique_id = _new_user.user_id;

    private String cur_user_name = new String("");
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseRef = database.getReference("user");
    //endregion Fields and Consts

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

    }
    //endregion Constructor

    //region Methods
    private boolean CreateUser(String name, String email, String hpNum, String studentInfo)
    {
        return true;
    }

    public boolean VerifyUserInfoFormat()
    {
        return true;
    }

    public void init_new_user()
    {
        this._new_user = new UserModel();
    }

    public void fill_cur_user_info(DataSnapshot dataSnapshot)
    {
        for (DataSnapshot ds : dataSnapshot.getChildren())
        {

            if (ds.getKey().equals(firebaseAuth.getCurrentUser().getUid()))
            {
                String _database_user_identifier = firebaseAuth.getCurrentUser().getUid();
                DatabaseUserModel user = new DatabaseUserModel();
                this._new_user.user_name.set(ds.getValue(DatabaseUserModel.class).getUser_name());
                this._new_user.user_email.set(ds.getValue(DatabaseUserModel.class).getUser_email());
                this._new_user.user_contact_info.set(ds.getValue(DatabaseUserModel.class).getUser_contact());
                this._new_user.user_career_info.set(ds.getValue(DatabaseUserModel.class).getUser_career_info());
                this._new_user.user_pickup_address.set(ds.getValue(DatabaseUserModel.class).getUser_pickup_address());
                this._new_user.user_password.set(ds.getValue(DatabaseUserModel.class).getUser_password());
                this._new_user.user_course_info.set(ds.getValue(DatabaseUserModel.class).getUser_student_info());

                Log.d("Filling user name: ", this._new_user.user_name.get());
                Log.d("Filling user email: ", this._new_user.user_email.get());

                this.cur_user_name = this._new_user.user_name.get();
            }
        }
    }

    public void upload_to_database()
    {
        String _database_user_identifier = firebaseAuth.getCurrentUser().getUid();

        databaseRef.child(_database_user_identifier).child("user_name")
                .setValue(this._new_user.user_name.get());
        databaseRef.child(_database_user_identifier).child("user_email")
                .setValue(this._new_user.user_email.get());
        databaseRef.child(_database_user_identifier).child("user_contact")
                .setValue(this.user_contact_info.get());
        databaseRef.child(_database_user_identifier).child("user_student_info")
                .setValue(this.user_course_info.get());
        databaseRef.child(_database_user_identifier).child("user_career_info")
                .setValue(this.user_career_info.get());
        databaseRef.child(_database_user_identifier).child("user_pickup_address")
                .setValue(this.user_pickup_address.get());
        databaseRef.child(_database_user_identifier).child("user_password")
                .setValue(this.user_password.get());
    }

    public void init_database()
    {
        String _database_user_identifier = firebaseAuth.getCurrentUser().getUid();

        databaseRef.child(_database_user_identifier).child("user_name")
                .setValue("");
        databaseRef.child(_database_user_identifier).child("user_email")
                .setValue(this.firebaseAuth.getCurrentUser().getEmail());
        databaseRef.child(_database_user_identifier).child("user_contact")
                .setValue("");
        databaseRef.child(_database_user_identifier).child("user_student_info")
                .setValue("");
        databaseRef.child(_database_user_identifier).child("user_career_info")
                .setValue("");
        databaseRef.child(_database_user_identifier).child("user_pickup_address")
                .setValue("");
        databaseRef.child(_database_user_identifier).child("user_password")
                .setValue("*******");
    }

    public String get_login_user_name()
    {
        return this.cur_user_name;
    }
    //endregion Methods
}
