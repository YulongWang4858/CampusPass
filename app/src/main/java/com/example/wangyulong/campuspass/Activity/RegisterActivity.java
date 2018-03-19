package com.example.wangyulong.campuspass.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.renderscript.Double2;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.RegisterViewModel;
import com.example.wangyulong.campuspass.databinding.RegisterPageBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;
import java.util.UUID;

/**
 * Created by wangyulong on 22/02/18.
 */

public class RegisterActivity extends AppCompatActivity
{
    //region Fields and Consts
    private RegisterPageBinding binding;
    private RegisterViewModel registerVM;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseRef = database.getReference("user");
    private boolean _is_registration_success = false;
    //endregion Fields and Consts

    //region Initialization
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        firebaseAuth = FirebaseAuth.getInstance();
        registerVM = RegisterViewModel.registerViewModel();

        //initialize binding
        onCreateBinding();
    }

    //Configure Databinding
    protected void onCreateBinding()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.register_page);
        binding.setRegisterVM(RegisterViewModel.registerViewModel());

        //Button Events
        binding.setDoneButtonClickListener(new ClickListener()
        {
            public void onClick()
            {
                if (RegisterViewModel.registerViewModel().VerifyUserInfoFormat())
                {
                    createUser();

                    if (_is_registration_success)
                    {
                        Log.d("Registration: ", "new user registration success, moving on to login page");
                        _is_registration_success = false;

                        //revert to login page
                        finish();
                    }
                }
            }
        });
    }

    protected void showSnackBar(String txt)
    {
        Snackbar.make(findViewById(android.R.id.content), txt, Snackbar.LENGTH_LONG).show();
    }

    protected void createUser()
    {
        firebaseAuth.createUserWithEmailAndPassword(registerVM.user_email.get(), registerVM.user_password.get())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        showSnackBar("Register: Create user with email " + task.isSuccessful());


                        if (!task.isSuccessful())
                        {
                            showSnackBar("Register: registration failed");
                            showSnackBar(task.getException().getMessage());
                        } else
                        {
                            _is_registration_success = true; //update view

                            //update data base
                            registerVM.init_database();
                        }
                    }
                });
    }
}
