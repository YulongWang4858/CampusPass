package com.example.wangyulong.campuspass.PresentationLogicManager;

import android.databinding.ObservableField;
import android.view.Menu;

import com.example.wangyulong.campuspass.Constant.HexColor;

import java.util.ArrayList;

/**
 * Created by wangyulong on 23/02/18.
 */

public class MenuTabLogicManager
{
    //region Fields and Consts
    private static MenuTabLogicManager _instance = null;
    //endregion Fields and Consts

    //region Properties
    public static MenuTabLogicManager menuTabLogicManager()
    {
        if (_instance == null)
        {
            _instance = new MenuTabLogicManager();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private MenuTabLogicManager()
    {

    }
    //endregion Constructor

    //region Methods
    public void selectionChanged(ArrayList<ObservableField<Integer>> tabList, int index)
    {
        for (int cur = 0; cur < 3; cur++)
        {
            if (cur == index)
            {
                tabList.get(cur).set(HexColor.RED_HIGHTLIGHT);
            } else
            {
                tabList.get(cur).set(HexColor.DEFAULT_BLACK);
            }
        }
    }
    //endregion Methods
}
