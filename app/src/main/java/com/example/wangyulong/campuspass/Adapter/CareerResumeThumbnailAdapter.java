package com.example.wangyulong.campuspass.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wangyulong.campuspass.Events.HobbyResumeListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.ShowCareerResumeDetailEventListener;
import com.example.wangyulong.campuspass.Events.ShowHobbyResumeDetailsEventListener;
import com.example.wangyulong.campuspass.Model.CareerResumeModel;
import com.example.wangyulong.campuspass.Model.HobbyResumeModel;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.CareerResumeDetailViewModel;
import com.example.wangyulong.campuspass.ViewModel.HobbyResumeViewModel;

import java.util.List;

/**
 * Created by wangyulong on 10/4/18.
 */

public class CareerResumeThumbnailAdapter extends RecyclerView.Adapter<CareerResumeThumbnailAdapter.CareerResumeViewHolder>
{
    //region Fields and Const
    private Context _context;
    private List<CareerResumeModel> resumeList;
    private ShowCareerResumeDetailEventListener showCareerResumeDetailEventListener = null;
    //endregion Fields and Const

    //region Extend
    public class CareerResumeViewHolder extends RecyclerView.ViewHolder
    {
        public TextView title, experience;
        public ImageView pic;

        public CareerResumeViewHolder(View view)
        {
            super(view);

            this.title = (TextView) view.findViewById(R.id.careerResumeBriefTitleTxt);
            this.experience = (TextView) view.findViewById(R.id.careerResumeBriefExperienceTxt);
            this.pic = (ImageView) view.findViewById(R.id.careerResumeBriefImg);
        }
    }
    //endregion Extend

    //region Constructor
    public CareerResumeThumbnailAdapter(Context context, List<CareerResumeModel> resumeList)
    {
        this._context = context;
        this.resumeList = resumeList;
    }
    //endregion Constructor

    //region Override
    @Override
    public CareerResumeThumbnailAdapter.CareerResumeViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.career_resume_brief_card, parent, false);

        return new CareerResumeThumbnailAdapter.CareerResumeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CareerResumeThumbnailAdapter.CareerResumeViewHolder holder, int position)
    {
        final CareerResumeModel resumeModel = this.resumeList.get(position);

        holder.title.setText(resumeModel.getCareer_name());
        holder.experience.setText(resumeModel.getCareer_experience());
        Glide.with(_context).load(resumeModel.getCareer_resume_photo_uri()).into(holder.pic);

        holder.pic.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TODO: Implement
                //debug
                Log.d("debug -> ", "image clicked, to detail page");
                //HobbyResumeViewModel.hobbyResumeViewModel().set_selected_resume(resumeModel);

                ///TODO: trigger event
                CareerResumeDetailViewModel.CareerResumeDetailViewModel().set_selected_resume(resumeModel);

                if (showCareerResumeDetailEventListener != null)
                {
                    showCareerResumeDetailEventListener.onShowCareerResumeDetailEventTrigger();
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return this.resumeList.size();
    }
    //endregion Override

    //region Events
    public void setShowCareerResumeDetailEventListener(ShowCareerResumeDetailEventListener showCareerResumeDetailEventListener)
    {
        this.showCareerResumeDetailEventListener = showCareerResumeDetailEventListener;
    }
    //endregion Events
}
