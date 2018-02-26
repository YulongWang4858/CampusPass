package com.example.wangyulong.campuspass.ViewModel;

import android.databinding.ObservableField;
import com.example.wangyulong.campuspass.Constant.HexColor;
import com.example.wangyulong.campuspass.PresentationLogicManager.MenuTabLogicManager;

import java.util.ArrayList;

/**
 * Created by wangyulong on 22/02/18.
 */

public class MenuViewModel extends BasicViewModel
{
    //region Fields and Consts
    private static MenuViewModel _instance = null;
    public ObservableField<Integer> tab1Color = new ObservableField<Integer>(HexColor.RED_HIGHTLIGHT);
    public ObservableField<Integer> tab2Color = new ObservableField<Integer>(HexColor.DEFAULT_BLACK);
    public ObservableField<Integer> tab3Color = new ObservableField<Integer>(HexColor.DEFAULT_BLACK);

    private ArrayList<ObservableField<Integer>> tabList = new ArrayList<ObservableField<Integer>>(3);
    private MenuTabLogicManager _menuTabLogicManager = MenuTabLogicManager.menuTabLogicManager();

    //endregion Fields and Consts

    //region Properties
    public static MenuViewModel menuViewModel()
    {
        if (_instance == null)
        {
            _instance = new MenuViewModel();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private MenuViewModel()
    {
        //setInitialColor();
    }
    //endregion Constructor

    //region Methods

    public void setSelection(int index)
    {
        _menuTabLogicManager.selectionChanged(tabList, index);
    }

    private void setInitialColor()
    {
        tabList.add(0, tab1Color);
        tabList.add(1, tab2Color);
        tabList.add(2, tab3Color);
    }

    //endregion Methods
}
