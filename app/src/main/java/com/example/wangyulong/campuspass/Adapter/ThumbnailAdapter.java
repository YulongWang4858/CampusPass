package com.example.wangyulong.campuspass.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wangyulong.campuspass.Model.HobbyModel;
import com.example.wangyulong.campuspass.R;

import java.util.List;

/**
 * Created by wangyulong on 2/4/18.
 */

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailAdapter.MyViewHolder>
{
    //region Fields and Const
    private Context _context;
    private List<HobbyModel> hobbyList;
    //endregion Fields and Const

    //region Extension
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        //region Fields and Const
        public TextView hobby_title, participants;
        public ImageView thumbnail, dot_menu;
        //endregion Fields and Const

        //region Constructor
        public MyViewHolder(View view)
        {
            super(view);

            this.hobby_title = (TextView) view.findViewById(R.id.hobbyTitle);
            this.participants = (TextView) view.findViewById(R.id.participant_count);
            this.thumbnail = (ImageView) view.findViewById(R.id.hobbyThumbnail);
            this.dot_menu = (ImageView) view.findViewById(R.id.overflow);
        }
        //endregion Constructor

    }
    //endregion Extension

    //region Override
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hobbies_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position)
    {
        HobbyModel hobbyModel = hobbyList.get(position);
        holder.hobby_title.setText(hobbyModel.getHobby_name());
        holder.participants.setText(hobbyModel.getParticipants() + " participants");

        Glide.with(this._context).load(hobbyModel.getHobby_icon_uri()).into(holder.thumbnail);

        holder.dot_menu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TODO: Implement details
                Log.d("dot menu -> ", "clicked");
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return hobbyList.size();
    }

    //endregion Override

    //region Constructor
    public ThumbnailAdapter(Context context, List<HobbyModel> hobbyList)
    {
        this._context = context;
        this.hobbyList = hobbyList;
    }
    //endregion Constructor

    //region Methods
    private void showPopupMenu(View view)
    {
        PopupMenu popup = new PopupMenu(this._context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_hobby_dot, popup.getMenu());

        //TODO: Set onClick
        //popup.setOnMenuItemClickListener();
        popup.show();
    }
    //endregion Methods
}