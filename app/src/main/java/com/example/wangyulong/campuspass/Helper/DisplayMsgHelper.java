package com.example.wangyulong.campuspass.Helper;

import com.example.wangyulong.campuspass.Constant.DisplayMsg;

/**
 * Created by wangyulong on 20/02/18.
 */

public class DisplayMsgHelper
{
    //region Properties
    private static DisplayMsgHelper _instance = null;

    public static DisplayMsgHelper getInstance() {
        if (_instance == null) {
            _instance = new DisplayMsgHelper();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private DisplayMsgHelper() {

    }
    //endregion Constructor

    //region Methods
    public String getWelcomeMsg() {
        return DisplayMsg.WELCOME_MSG;
    }

    public String getAppTitle() {
        return DisplayMsg.APP_TITLE;
    }

    public String getAskForMatric() {
        return DisplayMsg.ASK_MATRIC;
    }

    public String getAskForPass() {
        return DisplayMsg.ASK_PASSWORD;
    }

    public String getLoginButtonTxt() {
        return DisplayMsg.LOGIN_BUTTON;
    }

    public String getRegisterButtonTxt() {
        return DisplayMsg.REGISTER_BUTTON;
    }
    //endregion Methods
}
