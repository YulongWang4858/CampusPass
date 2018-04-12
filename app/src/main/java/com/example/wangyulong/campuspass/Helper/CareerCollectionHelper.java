package com.example.wangyulong.campuspass.Helper;

import com.example.wangyulong.campuspass.Model.CareerModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by wangyulong on 9/4/18.
 */

public class CareerCollectionHelper extends BasicCollectionHelper
{
    //region Fields and Const
    private static CareerCollectionHelper _instance = null;
    private ArrayList<CareerModel> careerModelList;
    //endregion Fields and Const

    //region Properties
    public static CareerCollectionHelper careerCollectionHelper()
    {
        if (_instance == null)
        {
            _instance = new CareerCollectionHelper();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private CareerCollectionHelper()
    {
        careerModelList = new ArrayList<>();
    }
    //endregion Constructor

    //region Methods
    public void add_new_career_to_collection(CareerModel careerModel)
    {
        this.careerModelList.add(careerModel);
    }

    public void reset_collection()
    {
        this.careerModelList = new ArrayList<>();
    }

    public boolean check_existance(CareerModel careerModel)
    {
        for (CareerModel career : this.careerModelList)
        {
            if (career.getCareer_title().equals(careerModel.getCareer_title()))
            {
                return true;
            }
        }

        return false;
    }

    public ArrayList<CareerModel> get_full_career_list()
    {
        return this.careerModelList;
    }
    //endregion Methods
}
