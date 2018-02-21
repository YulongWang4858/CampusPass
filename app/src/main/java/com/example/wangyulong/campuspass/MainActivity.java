package com.example.wangyulong.campuspass;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.Observable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.wangyulong.campuspass.Message.ToastMessageHandler;
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
    }

    protected void toastEventListener() {
        android.databinding.Observable.OnPropertyChangedCallback callback = new android.databinding.Observable.OnPropertyChangedCallback()
        {
            @Override
            public void onPropertyChanged(android.databinding.Observable sender, int propertyId)
            {
                String toastMsg = ((ObservableField<String>) sender).get();

                //Toast.makeText(MainActivity.this, "" + toastMsg, Toast.LENGTH_LONG).show();
                Snackbar.make(findViewById(android.R.id.content), toastMsg, Snackbar.LENGTH_LONG).show();
            }
        };

        ToastMessageHandler.errorMsg.addOnPropertyChangedCallback(callback);
    }
    //endregion Initialization
}
