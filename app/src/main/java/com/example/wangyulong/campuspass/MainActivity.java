package com.example.wangyulong.campuspass;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangyulong.campuspass.Activity.MainMenuActivity;
import com.example.wangyulong.campuspass.Message.SnackBarMessageHandler;
import com.example.wangyulong.campuspass.ViewModel.LoginViewModel;
import com.example.wangyulong.campuspass.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{
    //region Fields and Consts
    private ActivityMainBinding binding;
    //endregion Fields and Consts

    //region Initialization
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize binding
        onCreateBinding();
        toastEventListener();
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
                if (LoginViewModel.loginViewModel().userLogin()) //upon successful login only
                {
                    //TODO: Link to MainMenuActivity
                }
            }
        });

        binding.setRegisterButtonClickListener(new ClickListener()
        {
            public void onClick()
            {

                //TODO: Uncomment when tested
//                Intent toRegister = new Intent(getApplicationContext(), RegisterActivity.class);
//                MainActivity.this.startActivity(toRegister);

                Intent toMainMenu = new Intent(getApplicationContext(), MainMenuActivity.class);
                MainActivity.this.startActivity(toMainMenu);
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
