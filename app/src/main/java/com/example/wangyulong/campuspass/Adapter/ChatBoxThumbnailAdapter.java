package com.example.wangyulong.campuspass.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wangyulong.campuspass.Events.ShowChatRoomMessagesEventListener;
import com.example.wangyulong.campuspass.Model.ChatBoxModel;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.ChatBoxViewModel;

import java.util.List;

/**
 * Created by wangyulong on 22/4/18.
 */

public class ChatBoxThumbnailAdapter extends RecyclerView.Adapter<ChatBoxThumbnailAdapter.ChatBoxThumbnailViewHolder>
{
    //region Fields and Const
    private Context _context;
    private List<ChatBoxModel> chatBoxModelList;
    private int lastPosition = -1;
    private ShowChatRoomMessagesEventListener showChatRoomMessagesEventListener = null;
    //endregion Fields and Const

    //region Extend
    public class ChatBoxThumbnailViewHolder extends RecyclerView.ViewHolder
    {
        public TextView msg_user_name;
        public ImageView profile_img;
        public RelativeLayout clickable_parts;

        public ChatBoxThumbnailViewHolder(View view)
        {
            super(view);

            msg_user_name = (TextView) view.findViewById(R.id.ChatBoxUserName);
            profile_img = (ImageView) view.findViewById(R.id.ChatBoxCircularImgHost);
            clickable_parts = (RelativeLayout) view.findViewById(R.id.chatBoxCardViewOverallLayout);
        }
    }
    //endregion Extend

    //region Constructor
    public ChatBoxThumbnailAdapter(Context context, List<ChatBoxModel> chatBoxModelList)
    {
        this._context = context;
        this.chatBoxModelList = chatBoxModelList;
    }
    //endregion Constructor

    //region Override
    @Override
    public ChatBoxThumbnailAdapter.ChatBoxThumbnailViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_box_list_item, parent, false);

        return new ChatBoxThumbnailAdapter.ChatBoxThumbnailViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ChatBoxThumbnailAdapter.ChatBoxThumbnailViewHolder holder, int position)
    {
        final ChatBoxModel chatBoxModel = this.chatBoxModelList.get(position);

        holder.msg_user_name.setText(chatBoxModel.getChat_box_owner_name());

        //load image
        if (chatBoxModel.getChat_box_target_photo_uri() == null)
        {
            holder.profile_img.setImageResource(R.drawable.question_mark_face);
        } else if (chatBoxModel.getChat_box_target_photo_uri().length() < 2)
        {
            holder.profile_img.setImageResource(R.drawable.question_mark_face);
        } else
        {
            Glide.with(_context).load(chatBoxModel.getChat_box_target_photo_uri()).into(holder.profile_img);
        }

        //set onClick
        holder.clickable_parts.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ChatBoxViewModel.chatBoxViewModel().load_target_user_info(chatBoxModel.getChat_box_target_id());
                ChatBoxViewModel.chatBoxViewModel().set_current_chat_target_id(chatBoxModel.getChat_box_target_id());

                //expand chat box
                if (showChatRoomMessagesEventListener != null)
                {
                    showChatRoomMessagesEventListener.onShowChatRoomMessagesEventTrigger();
                }
            }
        });

        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount()
    {
        return this.chatBoxModelList.size();
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

    public void setShowChatRoomMessagesEventListener(ShowChatRoomMessagesEventListener showChatRoomMessagesEventListener)
    {
        this.showChatRoomMessagesEventListener = showChatRoomMessagesEventListener;
    }
    //endregion Methods
}
