package com.example.wangyulong.campuspass.Helper;

import android.databinding.ObservableField;

import com.example.wangyulong.campuspass.Loader.ComplexDataLoader;
import com.example.wangyulong.campuspass.Model.UserModel;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by wangyulong on 20/02/18.
 */

public class ConnectionHelper
{
    //region Fields and Consts
    private UserModel _user;
    private ObservableField<Boolean> isUserConnected;
    //endregion Fields and Consts

    //region Properties
    private static ConnectionHelper _connectionHelper_instance;
    private FirebaseAuth firebaseAuth;

    public static ConnectionHelper connectionHelper()
    {
        if (_connectionHelper_instance == null)
        {
            _connectionHelper_instance = new ConnectionHelper();
        }

        return _connectionHelper_instance;
    }
    //endregion Properties

    //region Contructor

    //Default
    private ConnectionHelper()
    {
        firebaseAuth = FirebaseAuth.getInstance();

        isUserConnected = new ObservableField<Boolean>();
        isUserConnected.set(false);
    }
    //endregion Constructor

    //region Methods
    public void set_connection_status(boolean status)
    {
        this.isUserConnected.set(status);
    }
    //endregion Methods
}
