package com.example.wangyulong.campuspass.Service;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by wangyulong on 23/4/18.
 */

public class CampusFirebaseMessagingService extends FirebaseMessagingService
{
    //region Fields and Const
    private static final String TAG = "CampusPassFirebaseMsgingService";
    //endregion Fields and Const

    //region Override

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage)
    {
        Log.d("", TAG + ": Received data from " + remoteMessage.getFrom());

        //check if message contains data
        if (remoteMessage.getData().size() > 0)
        {
            Log.d("", TAG + ": Received data -> " + remoteMessage.getData());
        }

        if (remoteMessage.getNotification() != null)
        {
            Log.d("", TAG + ": Received notification -> " + remoteMessage.getNotification());
        }
    }

    //endregion Override
}
