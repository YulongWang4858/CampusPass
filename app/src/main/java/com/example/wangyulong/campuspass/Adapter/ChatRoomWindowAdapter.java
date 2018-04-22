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
import com.example.wangyulong.campuspass.Model.ChatMessageModel;

import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.ChatBoxViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by wangyulong on 21/4/18.
 */

public class ChatRoomWindowAdapter extends RecyclerView.Adapter<ChatRoomWindowAdapter.ChatRoomWindowViewHolder>
{
    //region Fields and Const
    private Context _context;
    private List<ChatMessageModel> messageModelList;
    private int lastPosition = -1;
    //endregion Fields and Const

    //region Extend
    public class ChatRoomWindowViewHolder extends RecyclerView.ViewHolder
    {
        public TextView msg_content, msg_timestamp, msg_user_name;
        public ImageView profile_img;

        public ChatRoomWindowViewHolder(View view)
        {
            super(view);

            msg_content = (TextView) view.findViewById(R.id.chatMsgContentTxt);
            msg_user_name = (TextView) view.findViewById(R.id.chatMsgUserTxt);
            msg_timestamp = (TextView) view.findViewById(R.id.chatMsgTimeTxt);
            profile_img = (ImageView) view.findViewById(R.id.ChatMsgCircularImgHost);
        }
    }
    //endregion Extend

    //region Constructor
    public ChatRoomWindowAdapter(Context context, List<ChatMessageModel> messageModels)
    {
        this._context = context;
        this.messageModelList = messageModels;
    }
    //endregion Constructor

    //region Override
    @Override
    public ChatRoomWindowAdapter.ChatRoomWindowViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_room_list_item, parent, false);

        return new ChatRoomWindowAdapter.ChatRoomWindowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ChatRoomWindowAdapter.ChatRoomWindowViewHolder holder, int position)
    {
        final ChatMessageModel message = this.messageModelList.get(position);

        holder.msg_content.setText(message.getChat_msg_content());
        holder.msg_user_name.setText(message.getChat_msg_owner_name());
        holder.msg_timestamp.setText(android.text.format.DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                message.getChat_msg_timestamp()));

        ChatBoxViewModel vm = ChatBoxViewModel.chatBoxViewModel();

        if (message.getChat_msg_owner().equals(FirebaseAuth.getInstance().getCurrentUser().getUid()))
        {
            if (vm.getCurrent_user_profile_uri() != null)
            {
                Glide.with(_context).load(vm.getCurrent_user_profile_uri()).into(holder.profile_img);
            }
        } else
        {
            if (vm.getCurrent_target_profile_uri() != null)
            {
                Glide.with(_context).load(vm.getCurrent_target_profile_uri()).into(holder.profile_img);
            }
        }

        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount()
    {
        return this.messageModelList.size();
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
