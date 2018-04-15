package com.example.wangyulong.campuspass.ViewModel;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.wangyulong.campuspass.Helper.CareerTeamCollectionHelper;
import com.example.wangyulong.campuspass.Loader.ComplexDataLoader;
import com.example.wangyulong.campuspass.Model.CareerModel;
import com.example.wangyulong.campuspass.Model.CareerTeamModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
    private ComplexDataLoader loader;

    //binding
    private CareerTeamModel new_team;
    private String team_id;
    public ObservableField<String> team_title = new ObservableField<>(new String(""));
    public ObservableField<String> team_type = new ObservableField<>(new String(""));
    public ObservableField<String> team_descr = new ObservableField<>(new String(""));
    public ObservableField<String> team_end_date = new ObservableField<>(new String(""));
    public ObservableField<String> team_starting_date = new ObservableField<>(new String(""));
    public ObservableField<String> team_weekly_hours = new ObservableField<>(new String(""));
    public ObservableField<String> team_participants = new ObservableField<>(new String(""));
    public ObservableField<String> team_incentive = new ObservableField<>(new String(""));
    public ObservableField<String> team_remarks = new ObservableField<>(new String(""));
    public ObservableField<Boolean> delet_button_visibility = new ObservableField<>(false);
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
        this.loader = ComplexDataLoader.complexDataLoader();
    }
    //endregion Constructors

    //region Methods
    public void set_current_category(CareerModel careerModel)
    {
        this.team_id = UUID.randomUUID().toString();

        this.career_category = careerModel.getCareer_title();
        this.new_team.setTeam_category(this.career_category);
        this.new_team.setTeam_id(this.team_id);

        //toggle visibility
        this.delet_button_visibility.set(false);
    }
    
    public void upload_new_team()
    {
        //fill data into model
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
        this.new_team.setTeam_id(this.team_id);

        //upload and set listener
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("careers").child("teams").child(this.career_category).child(this.team_id);
        ref.setValue(this.new_team).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                //debug
                Log.d("upload success", "");
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid)
            {
                showOnSnackBar("Uploading Success!");
            }
        });
    }

    public void hide_delete_button()
    {
        this.delet_button_visibility.set(false);
    }

    public void load_my_team_into_VM()
    {
        //fill data into model
        this.new_team = CareerTeamCollectionHelper.careerTeamCollectionHelper().get_team_owned_by_user();
        this.team_title.set(this.new_team.getTeam_title());
        this.team_descr.set(this.new_team.getTeam_descr());
        this.team_type.set(this.new_team.getTeam_type());
        this.team_participants.set(this.new_team.getTeam_participants());
        this.team_id = this.new_team.team_id;
        this.team_end_date.set(this.new_team.getTeam_deadline());
        this.team_starting_date.set(this.new_team.getTeam_date_start());
        this.team_weekly_hours.set(this.new_team.getTeam_weekly_hour());
        this.team_remarks.set(this.new_team.getTeam_remarks());
        this.team_incentive.set(this.new_team.getTeam_incentive_type());

        //debug
        Log.d("manage -> title : ", this.team_title.get());

        //toggle visibility
        this.delet_button_visibility.set(true);
    }

    public void delete_current_entry()
    {
        //remove child branch
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("careers").child("teams").child(this.career_category).child(this.new_team.getTeam_id());
        this.loader.remove_value_from_database(ref, this.new_team.getTeam_id());

        //debug
        Log.d("removing -> ", "/careers/teams/" + this.career_category + "/" + this.new_team.getTeam_id());

    }
    //endregion Methods

}
