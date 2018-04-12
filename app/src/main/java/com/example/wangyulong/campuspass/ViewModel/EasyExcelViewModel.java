package com.example.wangyulong.campuspass.ViewModel;

import com.example.wangyulong.campuspass.Events.CareerListRefreshEventListener;
import com.example.wangyulong.campuspass.Helper.CareerCollectionHelper;
import com.example.wangyulong.campuspass.Loader.ComplexDataLoader;
import com.example.wangyulong.campuspass.Model.CareerModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by wangyulong on 9/4/18.
 */

public class EasyExcelViewModel extends BasicViewModel
{
    //region Fields and Const
    private static EasyExcelViewModel _instance = null;
    private CareerCollectionHelper careerCollectionHelper;
    private ComplexDataLoader dataLoader;
    //endregion Fields and Const

    //region Properties
    public static EasyExcelViewModel easyExcelViewModel()
    {
        if (_instance == null)
        {
            _instance = new EasyExcelViewModel();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private EasyExcelViewModel()
    {
        //init
        this.careerCollectionHelper = CareerCollectionHelper.careerCollectionHelper();
        this.dataLoader = ComplexDataLoader.complexDataLoader();
    }
    //endregion Constructor

    //region Methods
    public void reset()
    {
        this.careerCollectionHelper.reset_collection();
    }

    public void load_from_database()
    {
        this.dataLoader.load_career_from_database();
    }

    public ArrayList<CareerModel> get_full_list()
    {
        return this.careerCollectionHelper.get_full_career_list();
    }

    public void setCareerListRefreshEventListener(CareerListRefreshEventListener careerListRefreshEventListener)
    {
        this.dataLoader.setCareerListRefreshEventListener(careerListRefreshEventListener);
    }
    //endregion Methods
}
