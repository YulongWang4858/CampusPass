package com.example.wangyulong.campuspass.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wangyulong.campuspass.Events.ShowCareerResumeListEventListener;
import com.example.wangyulong.campuspass.Events.ViewResumeListEventListener;
import com.example.wangyulong.campuspass.Model.CareerModel;
import com.example.wangyulong.campuspass.Model.DetailHobbyModel;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.CareerListViewModel;
import com.example.wangyulong.campuspass.ViewModel.CareerTeamListViewModel;
import com.example.wangyulong.campuspass.ViewModel.HobbyResumeViewModel;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by wangyulong on 8/4/18.
 */

public class CareerThumbnailAdapter extends RecyclerView.Adapter<CareerThumbnailAdapter.CareerThumbnailViewHolder>
{
    //region Fields and Const
    private Context _context;
    private List<CareerModel> careerModelList;
    private ShowCareerResumeListEventListener showCareerResumeListEventListener;
    private int lastPosition = -1;
    //endregion Fields and Const

    //region Extend
    public class CareerThumbnailViewHolder extends RecyclerView.ViewHolder
    {
        public TextView title, participants;
        public ImageView thumbnail;

        public CareerThumbnailViewHolder(View view)
        {
            super(view);

            title = (TextView) view.findViewById(R.id.careerTitle);
            participants = (TextView) view.findViewById(R.id.careerParticipants);
            thumbnail = (ImageView) view.findViewById(R.id.careerThumbnail);
        }
    }
    //endregion Extend

    //region Constructor
    public CareerThumbnailAdapter(Context context, List<CareerModel> careerModelList)
    {
        this._context = context;
        this.careerModelList = careerModelList;
    }
    //endregion Constructor

    //region Override
    @Override
    public CareerThumbnailAdapter.CareerThumbnailViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.career_card, parent, false);

        return new CareerThumbnailAdapter.CareerThumbnailViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CareerThumbnailAdapter.CareerThumbnailViewHolder holder, int position)
    {
        final CareerModel careerModel = this.careerModelList.get(position);

        holder.title.setText(careerModel.getCareer_title());
        holder.participants.setText(careerModel.getCareer_participants() + " participants");

        Glide.with(_context).load(careerModel.getCareer_icon_photo_uri()).into(holder.thumbnail);

        holder.thumbnail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                CareerListViewModel.careerListViewModel().set_selected_career(careerModel);
                CareerTeamListViewModel.careerTeamListViewModel().set_current_category(careerModel);
                if (showCareerResumeListEventListener != null)
                {
                    showCareerResumeListEventListener.onShowCareerResumeListEventTrigger();
                }
            }
        });

        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount()
    {
        return this.careerModelList.size();
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

    public void setShowCareerResumeListEventListener(ShowCareerResumeListEventListener showCareerResumeListEventListener)
    {
        this.showCareerResumeListEventListener = showCareerResumeListEventListener;
    }
    //endregion Methods
}
