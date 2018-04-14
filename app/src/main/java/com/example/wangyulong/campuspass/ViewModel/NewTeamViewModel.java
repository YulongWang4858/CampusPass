package com.example.wangyulong.campuspass.ViewModel;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.wangyulong.campuspass.Model.CareerModel;
import com.example.wangyulong.campuspass.Model.CareerTeamModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

/**
 * Created by wangyulong on 13/4/18.
 */

public class NewTeamViewModel extends BasicViewModel
{
    //region Fields and Const
    private static NewTeamViewModel _instance = null;
    private String career_category;
    private String team_id;

    //binding
    private CareerTeamModel new_team;
    public ObservableField<String> team_title = new ObservableField<>(new String(""));
    public ObservableField<String> team_type = new ObservableField<>(new String(""));
    public ObservableField<String> team_descr = new ObservableField<>(new String(""));
    public ObservableField<String> team_end_date = new ObservableField<>(new String(""));
    public ObservableField<String> team_starting_date = new ObservableField<>(new String(""));
    public ObservableField<String> team_weekly_hours = new ObservableField<>(new String(""));
    public ObservableField<String> team_participants = new ObservableField<>(new String(""));
    public ObservableField<String> team_incentive = new ObservableField<>(new String(""));
    public ObservableField<String> team_remarks = new ObservableField<>(new String(""));
    //endregion Fields and Const

    //region Properties
    public static NewTeamViewModel newTeamViewModel()
    {
        if (_instance == null)
        {
            _instance = new NewTeamViewModel();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructors
    private NewTeamViewModel()
    {
        this.new_team = new CareerTeamModel();
    }
    //endregion Constructors

    //region Methods
    public void set_current_category(CareerModel careerModel)
    {
        this.team_id = UUID.randomUUID().toString();

        this.career_category = careerModel.getCareer_title();
        this.new_team.setTeam_category(this.career_category);
        this.new_team.setTeam_id(this.team_id);
    }

    public void upload_new_team()
    {
        //fill data
        this.new_team.setTeam_title(this.team_title.get());
        this.new_team.setTeam_type(this.team_type.get());
        this.new_team.setTeam_descr(this.team_descr.get());
        this.new_team.setTeam_participants(this.team_participants.get());
        this.new_team.setTeam_leader_id(FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
        this.new_team.setTeam_deadline(this.team_end_date.get());
        this.new_team.setTeam_date_start(this.team_starting_date.get());
        this.new_team.setTeam_incentive_type(this.team_incentive.get());
        this.new_team.setTeam_weekly_hour(this.team_weekly_hours.get());
        this.new_team.setTeam_remarks(this.team_remarks.get());

        //upload
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("careers").child("teams").child(this.career_category).child(this.team_id);
        ref.setValue(this.new_team).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                //debug
                Log.d("upload success", "");
            }
        });
    }
    //endregion Methods

}
