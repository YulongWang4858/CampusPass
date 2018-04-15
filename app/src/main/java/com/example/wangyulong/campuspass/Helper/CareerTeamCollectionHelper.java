package com.example.wangyulong.campuspass.Helper;

import android.util.Log;

import com.example.wangyulong.campuspass.Events.CareerTeamListRefreshEventListener;
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
    private CareerTeamModel team_owned_by_uer;
    private CareerTeamListRefreshEventListener careerTeamListRefreshEventListener = null;
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

    public boolean check_ownership(String id)
    {
        for (CareerTeamModel team : this.teamList)
        {
            if (team.getTeam_leader_id().equals(id))
            {
                this.set_team_owned_by_user(team);

                //debug
                Log.d("current user id -> ", id + "team title -> " + team.getTeam_title());
                return true;
            }
        }

        //debug
        Log.d("", "no team owned by " + id);

        return false;
    }

    public void set_team_owned_by_user(CareerTeamModel team)
    {
        this.team_owned_by_uer = team;
    }

    public CareerTeamModel get_team_owned_by_user()
    {
        return this.team_owned_by_uer;
    }

    public void change_team_info_in_collection(CareerTeamModel team)
    {
        for (CareerTeamModel teamModel : this.teamList)
        {
            if (teamModel.getTeam_id().equals(team.getTeam_id()))
            {
                teamModel = team;
            }
        }
    }

    public void remove_team_from_list(String team_id)
    {
        int index_to_remove = -1;

        for (int cur = 0; cur < this.teamList.size(); cur++)
        {
            if (this.teamList.get(cur).getTeam_id().equals(team_id))
            {
                index_to_remove = cur;
                break;
            }
        }

        if (index_to_remove != -1)
        {
            this.teamList.remove(index_to_remove);

            //debug
            Log.d("collectionhelper -> ", "removing team with id -> " + team_id);

            if (this.careerTeamListRefreshEventListener != null)
            {
                this.careerTeamListRefreshEventListener.onCareerTeamListRefreshEventTrigger();
            }

        }
    }

    public void setCareerTeamListEventListener(CareerTeamListRefreshEventListener listener)
    {
        this.careerTeamListRefreshEventListener = listener;
    }
    //endregion Methods
}
