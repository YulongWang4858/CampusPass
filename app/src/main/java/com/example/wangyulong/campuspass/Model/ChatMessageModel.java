package com.example.wangyulong.campuspass.Model;

import java.util.Date;

/**
 * Created by wangyulong on 20/4/18.
 */

public class ChatMessageModel
{
    //region Fields and Const
    public String chat_msg_content;
    public long chat_msg_timestamp;
    public String chat_msg_owner;
    public String chat_msg_target_id;
    public String chat_msg_id;
    public String chat_msg_owner_name;
    //endregion Fields and Const

    //region Properties

    public String getChat_msg_content()
    {
        return chat_msg_content;
    }

    public void setChat_msg_content(String chat_msg_content)
    {
        this.chat_msg_content = chat_msg_content;
    }

    public long getChat_msg_timestamp()
    {
        return chat_msg_timestamp;
    }

    public void setChat_msg_timestamp(long chat_msg_timestamp)
    {
        this.chat_msg_timestamp = chat_msg_timestamp;
    }

    public String getChat_msg_owner()
    {
        return chat_msg_owner;
    }

    public void setChat_msg_owner(String chat_msg_owner)
    {
        this.chat_msg_owner = chat_msg_owner;
    }

    public String getChat_msg_target_id()
    {
        return chat_msg_target_id;
    }

    public void setChat_msg_target_id(String chat_msg_target_id)
    {
        this.chat_msg_target_id = chat_msg_target_id;
    }

    //endregion Properties

    //region Constructor

    public ChatMessageModel(String chat_msg_content)
    {
        this.chat_msg_content = chat_msg_content;

        //stamp
        this.chat_msg_timestamp = new Date().getTime();
    }

    public String getChat_msg_id()
    {
        return chat_msg_id;
    }

    public void setChat_msg_id(String chat_msg_id)
    {
        this.chat_msg_id = chat_msg_id;
    }

    public String getChat_msg_owner_name()
    {
        return chat_msg_owner_name;
    }

    public void setChat_msg_owner_name(String chat_msg_owner_name)
    {
        this.chat_msg_owner_name = chat_msg_owner_name;
    }

    //overload
    public ChatMessageModel()
    {
        //empty
    }
    //endregion Constructor
}
