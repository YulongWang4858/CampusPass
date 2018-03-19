package com.example.wangyulong.campuspass;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.wangyulong.campuspass.Activity.MainMenuActivity;
import com.example.wangyulong.campuspass.Activity.RegisterActivity;
import com.example.wangyulong.campuspass.Activity.SellingActivity;
import com.example.wangyulong.campuspass.Message.SnackBarMessageHandler;
import com.example.wangyulong.campuspass.ViewModel.LoginViewModel;
import com.example.wangyulong.campuspass.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{
    //region Fields and Consts
    private ActivityMainBinding binding;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private LoginViewModel loginVM;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginVM = LoginViewModel.loginViewModel();

        //initialize Firebase
        initFirebaseSettings();

        //initialize binding
        onCreateBinding();
        toastEventListener();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        if (firebaseAuthListener != null)
        {
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }

    protected void initFirebaseSettings()
    {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener()
        {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null)         // user signed in
                {
                    showSnackBar("AUTH: User signed in");
                } else                    // user signed out
                {
                    showSnackBar("AUTH: User signed out");
                }
            }
        };
    }

    //Configure Databinding
    protected void onCreateBinding()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLoginVM(LoginViewModel.loginViewModel());

        bindingButtons();
    }

    protected void toastEventListener()
    {
        android.databinding.Observable.OnPropertyChangedCallback callback = new android.databinding.Observable.OnPropertyChangedCallback()
        {
            @Override
            public void onPropertyChanged(android.databinding.Observable sender, int propertyId)
            {
                String snackBarMsg = ((ObservableField<String>) sender).get();
                snackBarMsg = snackBarMsg.substring(0, snackBarMsg.length() - 5);

                Snackbar.make(findViewById(android.R.id.content), snackBarMsg, Snackbar.LENGTH_LONG).show();

            }
        };

        SnackBarMessageHandler.errorMsg.addOnPropertyChangedCallback(callback);
    }

    protected void bindingButtons()
    {
        binding.setLoginButtonClickListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                verify_user_info();
            }
        });

        binding.setRegisterButtonClickListener(new ClickListener()
        {
            public void onClick()
            {

                //TODO: Uncomment when tested
                Intent toRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                MainActivity.this.startActivity(toRegister);
            }
        });


        //TODO: Remove after testing
        binding.setByPassButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                Intent toTestActivity = new Intent(getApplicationContext(), SellingActivity.class);
                MainActivity.this.startActivity(toTestActivity);
            }
        });
    }

    protected void verify_user_info()
    {
        firebaseAuth.signInWithEmailAndPassword(loginVM.user_matric.get(), loginVM.user_pass.get())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {

                        loginVM.set_user_connection_status(task.isSuccessful());
                        if (!task.isSuccessful())
                        {
                            showSnackBar("Login: login failed");
                        } else
                        {
                            showSnackBar("Login: Success! ");

                            //start mainmenu activity
                            Intent toMainMenu = new Intent(getApplicationContext(), MainMenuActivity.class);
                            MainActivity.this.startActivity(toMainMenu);
                        }
                    }
                });
    }
    //endregion Initialization

    //region SnackBar
    protected void showSnackBar(String txt)
    {
        Snackbar.make(findViewById(android.R.id.content), txt, Snackbar.LENGTH_LONG).show();
    }
    //endregion SnackBar
}
