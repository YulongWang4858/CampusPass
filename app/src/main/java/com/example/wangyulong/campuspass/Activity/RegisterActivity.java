package com.example.wangyulong.campuspass.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.RegisterViewModel;
import com.example.wangyulong.campuspass.databinding.RegisterPageBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by wangyulong on 22/02/18.
 */

public class RegisterActivity extends AppCompatActivity
{
    //region Fields and Consts
    private RegisterPageBinding binding;
    private FirebaseAuth firebaseAuth;
    private RegisterViewModel registerVM;
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
                    //showSnackBar("Register: Registration in process");

                    createUser();
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
        firebaseAuth.createUserWithEmailAndPassword(registerVM.email.get(), registerVM.student_name.get())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        showSnackBar("Register: Create user with email " + task.isSuccessful());


                        if (!task.isSuccessful())
                        {
                            showSnackBar("Register: registration failed");
                            showSnackBar(task.getException().getMessage());
                        }
                    }
                });
    }
}
