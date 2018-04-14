package com.example.wangyulong.campuspass.ViewModel;

import android.databinding.ObservableField;
import android.util.Log;

import com.example.wangyulong.campuspass.Events.CareerTeamListRefreshEventListener;
import com.example.wangyulong.campuspass.Helper.CareerTeamCollectionHelper;
import com.example.wangyulong.campuspass.Loader.ComplexDataLoader;
import com.example.wangyulong.campuspass.Model.CareerModel;
import com.example.wangyulong.campuspass.Model.CareerTeamModel;
import com.example.wangyulong.campuspass.databinding.CareerTeamListPageBinding;
import com.google.firebase.auth.FirebaseAuth;

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
    private CareerTeamModel selected_team;

    //binding
    public ObservableField<String> selected_team_title = new ObservableField<>(new String(""));
    public ObservableField<String> selected_team_descr = new ObservableField<>(new String(""));
    public ObservableField<String> selected_team_type = new ObservableField<>(new String(""));
    public ObservableField<String> selected_team_participants = new ObservableField<>(new String(""));
    public ObservableField<String> selected_team_weekly_hours = new ObservableField<>(new String(""));
    public ObservableField<String> selected_team_end_date = new ObservableField<>(new String(""));
    public ObservableField<String> selected_team_start_date = new ObservableField<>(new String(""));
    public ObservableField<String> selected_team_incentive = new ObservableField<>(new String(""));
    public ObservableField<String> selected_team_remarks = new ObservableField<>(new String(""));
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
        this.selected_team = new CareerTeamModel();
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

    public void set_currently_selected_team(CareerTeamModel team)
    {
        this.selected_team = team;
        this.selected_team_title.set(team.getTeam_title());
        this.selected_team_descr.set(team.getTeam_descr());
        this.selected_team_type.set(team.getTeam_type());
        this.selected_team_weekly_hours.set(team.getTeam_weekly_hour());
        this.selected_team_start_date.set(team.getTeam_date_start());
        this.selected_team_end_date.set(team.getTeam_deadline());
        this.selected_team_participants.set(team.getTeam_participants());
        this.selected_team_incentive.set(team.getTeam_incentive_type());
        this.selected_team_remarks.set(team.getTeam_remarks());
    }

    public boolean check_if_user_owns_team()
    {
        return this.collectionHelper.check_ownership(FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
    }
    //endregion Methods
}
