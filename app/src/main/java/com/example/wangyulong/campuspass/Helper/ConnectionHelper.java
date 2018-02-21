package com.example.wangyulong.campuspass.Helper;

import com.example.wangyulong.campuspass.Model.UserModel;

/**
 * Created by wangyulong on 20/02/18.
 */

public class ConnectionHelper
{
    //region Fields and Consts
    private UserModel _user;
    //endregion Fields and Consts

    //region Properties
    private static ConnectionHelper _connectionHelper_instance;

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

    }
    //endregion Constructor

    //region Methods
    public boolean ConnectUser(String matricN, String passW)
    {
        if (Connect(matricN, passW))
        {
            _user = new UserModel(matricN, passW);

            return true;
        }

        return false;
    }

    private boolean Connect(String matricN, String passW)
    {
        //TODO: Implement Web Server Methods

        return testVerification(matricN, passW);
    }

    private boolean testVerification(String matricN, String passW)
    {
        return matricN.equals("Wang Yulong") && passW.equals("2enXm?83");
    }
    //endregion Methods
}
