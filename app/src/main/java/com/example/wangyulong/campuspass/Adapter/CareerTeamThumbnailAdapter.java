package com.example.wangyulong.campuspass.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.wangyulong.campuspass.Model.CareerTeamModel;
import com.example.wangyulong.campuspass.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by wangyulong on 14/4/18.
 */

public class CareerTeamThumbnailAdapter extends RecyclerView.Adapter<CareerTeamThumbnailAdapter.CareerTeamThumbnailViewHolder>
{
    //region Fields and Const
    private Context _context;
    private List<CareerTeamModel> careerTeamModelList;
    private int lastPosition = -1;
    //endregion Fields and Const

    //region Extend
    public class CareerTeamThumbnailViewHolder extends RecyclerView.ViewHolder
    {
        public TextView title, participants, weekly_hours, incentives, type;

        public CareerTeamThumbnailViewHolder(View view)
        {
            super(view);

            //fill
            title = (TextView) view.findViewById(R.id.teamTitleTxt);
            participants = (TextView) view.findViewById(R.id.teamParticipantsTxt);
            weekly_hours = (TextView) view.findViewById(R.id.teamWeeklyHoursTxt);
            incentives = (TextView) view.findViewById(R.id.teamIncentivesTxt);
            type = (TextView) view.findViewById(R.id.teamTypeTxt);
        }
    }
    //endregion Extend

    //region Constructor
    public CareerTeamThumbnailAdapter(Context context, List<CareerTeamModel> careerTeamModelList)
    {
        this._context = context;
        this.careerTeamModelList = careerTeamModelList;
    }
    //endregion Constructor

    //region Override
    @Override
    public CareerTeamThumbnailAdapter.CareerTeamThumbnailViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.career_team_card, parent, false);

        return new CareerTeamThumbnailAdapter.CareerTeamThumbnailViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CareerTeamThumbnailAdapter.CareerTeamThumbnailViewHolder holder, int position)
    {
        final CareerTeamModel careerTeamModel = this.careerTeamModelList.get(position);

        //fill
        holder.title.setText(careerTeamModel.getTeam_title());
        holder.participants.setText("Up to " + careerTeamModel.getTeam_participants() + " Members");
        holder.weekly_hours.setText("Weekly Hours: " + careerTeamModel.getTeam_weekly_hour());
        holder.incentives.setText("Incentive: " + careerTeamModel.getTeam_incentive_type());
        holder.type.setText("Qualification: " + careerTeamModel.getTeam_type());

        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount()
    {
        return this.careerTeamModelList.size();
    }
    //endregion Override

    //region Methods
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(_context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
    //endregion Methods
}
