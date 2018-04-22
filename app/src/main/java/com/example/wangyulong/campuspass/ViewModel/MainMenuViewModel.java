package com.example.wangyulong.campuspass.ViewModel;

import com.example.wangyulong.campuspass.Activity.MainMenuActivity;
import com.example.wangyulong.campuspass.Loader.ComplexDataLoader;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by wangyulong on 26/02/18.
 */

public class MainMenuViewModel extends BasicViewModel
{
    //region Fields and Const
    private static MainMenuViewModel _instance = null;
    //endregion Fields and Const

    //region Properties
    public static MainMenuViewModel mainMenuViewModel()
    {
        if (_instance == null)
        {
            _instance = new MainMenuViewModel();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private MainMenuViewModel()
    {

    }
    //endregion Constructor

    //region Methods
    public void load_user_info()
    {
        ComplexDataLoader.complexDataLoader().load_user_from_database(FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
    }
    //endregion Methods
}
