package com.example.wangyulong.campuspass.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wangyulong.campuspass.Model.DetailHobbyModel;
import com.example.wangyulong.campuspass.Model.HobbyModel;
import com.example.wangyulong.campuspass.R;

import java.util.List;

import android.content.Context;

/**
 * Created by wangyulong on 5/4/18.
 */

public class DetailHobbyThumbnailAdapter extends RecyclerView.Adapter<DetailHobbyThumbnailAdapter.DetailHobbyViewHolder>
{
    //region Fields and Const
    private Context _context;
    private List<DetailHobbyModel> detailHobbyModelList;
    //endregion Fields and Const

    //region Extend
    public class DetailHobbyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView title, participation, descr;
        public ImageView thumbnail;

        public DetailHobbyViewHolder(View view)
        {
            super(view);

            title = (TextView) view.findViewById(R.id.detailHobbyTitleTxt);
            participation = (TextView) view.findViewById(R.id.detailHobbyParticipationCountTxt);
            descr = (TextView) view.findViewById(R.id.detailHobbyDescrTxt);
            thumbnail = (ImageView) view.findViewById(R.id.detailHobbyImg);
        }
    }
    //endregion Extend

    //region Constructor
    public DetailHobbyThumbnailAdapter(Context context, List<DetailHobbyModel> detailHobbyList)
    {
        this._context = context;
        this.detailHobbyModelList = detailHobbyList;
    }
    //endregion Constructor

    //region Override
    @Override
    public DetailHobbyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_hobby_card, parent, false);

        return new DetailHobbyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DetailHobbyViewHolder holder, int position)
    {
        DetailHobbyModel detailHobbyModel = this.detailHobbyModelList.get(position);

        holder.title.setText(detailHobbyModel.getDetail_hobby_name());
        holder.participation.setText(detailHobbyModel.getDetail_participants() + " participants");
        holder.descr.setText("- " + detailHobbyModel.getDetail_hobby_descr());

        Glide.with(_context).load(detailHobbyModel.getDetail_hobby_icon_uri()).into(holder.thumbnail);

        holder.thumbnail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TODO: Open List

            }
        });
    }

    @Override
    public int getItemCount()
    {
        return this.detailHobbyModelList.size();
    }
    //endregion Override
}