package com.example.wangyulong.campuspass.ViewModel;

import android.util.Log;

import com.example.wangyulong.campuspass.Events.CareerTeamListRefreshEventListener;
import com.example.wangyulong.campuspass.Helper.CareerTeamCollectionHelper;
import com.example.wangyulong.campuspass.Loader.ComplexDataLoader;
import com.example.wangyulong.campuspass.Model.CareerModel;
import com.example.wangyulong.campuspass.Model.CareerTeamModel;
import com.example.wangyulong.campuspass.databinding.CareerTeamListPageBinding;

import java.util.ArrayList;

/**
 * Created by wangyulong on 13/4/18.
 */

public class CareerTeamListViewModel extends BasicViewModel
{
    //region Fields and Const
    private static CareerTeamListViewModel _instance = null;
    private String current_category;
    private CareerTeamCollectionHelper collectionHelper;
    private ComplexDataLoader dataLoader;
    //endregion Fields and Const

    //region Properties
    public static CareerTeamListViewModel careerTeamListViewModel()
    {
        if (_instance == null)
        {
            _instance = new CareerTeamListViewModel();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructors
    private CareerTeamListViewModel()
    {
        this.collectionHelper = CareerTeamCollectionHelper.careerTeamCollectionHelper();
        this.dataLoader = ComplexDataLoader.complexDataLoader();
    }
    //endregion Constructors

    //region Methods
    public void set_current_category(CareerModel career)
    {
        this.current_category = career.getCareer_title();
        NewTeamViewModel.newTeamViewModel().set_current_category(career);

        //debug
        Log.d("debug -> ", "setting current category to " + career.getCareer_title());
    }

    public ArrayList<CareerTeamModel> get_full_list()
    {
        return this.collectionHelper.get_full_team_list();
    }

    public void load_from_database()
    {
        this.dataLoader.load_career_teams_from_database(this.current_category);
    }

    public void setRefreshListener(CareerTeamListRefreshEventListener listener)
    {
        this.dataLoader.setCareerTeamListRefreshEventListener(listener);
    }
    //endregion Methods
}
