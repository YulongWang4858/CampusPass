package com.example.wangyulong.campuspass.Helper;

import com.example.wangyulong.campuspass.Model.CareerTeamModel;

import java.util.ArrayList;

/**
 * Created by wangyulong on 14/4/18.
 */

public class CareerTeamCollectionHelper extends BasicCollectionHelper
{
    //region Fields and Const
    private static CareerTeamCollectionHelper _instance = null;
    private ArrayList<CareerTeamModel> teamList;
    //endregion Fields and Const

    //region Properties
    public static CareerTeamCollectionHelper careerTeamCollectionHelper()
    {
        if (_instance == null)
        {
            _instance = new CareerTeamCollectionHelper();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private CareerTeamCollectionHelper()
    {
        this.teamList = new ArrayList<>();
    }
    //endregion Constructor

    //region Methods
    public void add_team_to_collection(CareerTeamModel teamModel)
    {
        this.teamList.add(teamModel);
    }

    public void reset()
    {
        this.teamList = new ArrayList<>();
    }

    public boolean check_existance(CareerTeamModel teamModel)
    {
        for (CareerTeamModel team : this.teamList)
        {
            if (team.getTeam_id().equals(teamModel.getTeam_id()))
            {
                return true;
            }
        }

        return false;
    }

    public ArrayList<CareerTeamModel> get_full_team_list()
    {
        return this.teamList;
    }
    //endregion Methods
}
