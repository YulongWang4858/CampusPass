package com.example.wangyulong.campuspass.ViewModel;

import com.example.wangyulong.campuspass.Message.SnackBarMessageHandler;

/**
 * Created by wangyulong on 20/02/18.
 */

public class BasicViewModel
{

    //region Constructor
    public BasicViewModel()
    {

    }
    //endregion Constructor

    //region Inheritance
    protected void showOnSnackBar(String content)
{
    SnackBarMessageHandler.errorMsg.set(content + randMsgIndex());
}

    protected String randMsgIndex()
    {
        return ((Integer)(int)(Math.random() * 50000 + 10000)).toString();
    }
    //endregion Inheritance
}
