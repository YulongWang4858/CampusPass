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
import com.example.wangyulong.campuspass.Events.HobbyBriefListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.HobbyResumeListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.ViewResumeListEventListener;
import com.example.wangyulong.campuspass.Model.HobbyResumeModel;
import com.example.wangyulong.campuspass.R;

import java.util.List;

/**
 * Created by wangyulong on 8/4/18.
 */

public class HobbyResumeListViewAdapter extends RecyclerView.Adapter<HobbyResumeListViewAdapter.HobbyResumeViewHolder>
{
    //region Fields and Const
    private Context _context;
    private List<HobbyResumeModel> resumeList;
    private HobbyResumeListRefreshEventListener hobbyResumeListRefreshEventListener;
    //endregion Fields and Const

    //region Extend
    public class HobbyResumeViewHolder extends RecyclerView.ViewHolder
    {
        public TextView descr, price;
        public ImageView pic;

        public HobbyResumeViewHolder(View view)
        {
            super(view);

            descr = (TextView) view.findViewById(R.id.hobbyResumeDescr);
            price = (TextView) view.findViewById(R.id.hobbyResumePrice);
            pic = (ImageView) view.findViewById(R.id.hobbyResumeShowCaseImg);
        }
    }
    //endregion Extend

    //region Constructor
    public HobbyResumeListViewAdapter(Context context, List<HobbyResumeModel> resumeList)
    {
        this._context = context;
        this.resumeList = resumeList;
    }
    //endregion Constructor

    //region Override
    @Override
    public HobbyResumeListViewAdapter.HobbyResumeViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hobby_resume_card, parent, false);

        return new HobbyResumeListViewAdapter.HobbyResumeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final HobbyResumeListViewAdapter.HobbyResumeViewHolder holder, int position)
    {
        final HobbyResumeModel resumeModel = this.resumeList.get(position);

        holder.descr.setText(resumeModel.getHobby_resume_descr());
        holder.price.setText(resumeModel.getHobby_resume_price());
        Glide.with(_context).load(resumeModel.getHobby_resume_photo_uri()).into(holder.pic);

        holder.pic.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TODO: Implement
                //debug
                Log.d("debug -> ", "image clicked, to detail page");
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return this.resumeList.size();
    }
    //endregion Override

    //region Methods
    //endregion Methods
}
