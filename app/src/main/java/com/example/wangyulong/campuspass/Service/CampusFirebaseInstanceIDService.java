package com.example.wangyulong.campuspass.Service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by wangyulong on 23/4/18.
 */

public class CampusFirebaseInstanceIDService extends FirebaseInstanceIdService
{
    //region Fields and Const
    private static final String TAG = "CampusPassFirebaseInsIDServie";
    //endregion Fields and Const

    //region Override
    @Override
    public void onTokenRefresh()
    {
        //update refreshed token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("", TAG + ": New token -> " + refreshedToken);
    }
    //endregion Override
}
