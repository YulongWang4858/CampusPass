package com.example.wangyulong.campuspass.Helper;

import com.example.wangyulong.campuspass.Model.ChatBoxModel;

import java.util.ArrayList;

/**
 * Created by wangyulong on 21/4/18.
 */

public class ChatBoxCollectionHelper implements CollectionHelper
{
    //region Fields and Const
    private static ChatBoxCollectionHelper _instance = null;
    private ArrayList<ChatBoxModel> chatbox_list;
    //endregion Fields and Const

    //region Properties
    public static ChatBoxCollectionHelper chatBoxCollectionHelper()
    {
        if (_instance == null)
        {
            _instance = new ChatBoxCollectionHelper();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructors
    private ChatBoxCollectionHelper()
    {
        //init
        this.chatbox_list = new ArrayList<>();
    }
    //endregion Constructors

    //region Override
    @Override
    public Object get_full_list()
    {
        return this.chatbox_list;
    }

    @Override
    public Object get_item_at_position(int i)
    {
        return this.chatbox_list.get(i);
    }

    @Override
    public void reset_collection()
    {
        this.chatbox_list = new ArrayList<>();
    }

    @Override
    public void add_item_to_collection(Object obj)
    {
        this.chatbox_list.add((ChatBoxModel) obj);
    }

    @Override
    public void remove_item_from_collection(String id)
    {
        int index_of_item = -1;

        for (int cur = 0; cur < this.chatbox_list.size(); cur++)
        {
            if (this.chatbox_list.get(cur).getChat_box_id().equals(id))
            {
                index_of_item = cur;
                break;
            }
        }

        if (index_of_item > 0)
        {
            this.chatbox_list.remove(index_of_item);
        }
    }

    @Override
    public boolean check_exsitance(Object obj)
    {
        for (ChatBoxModel chatBox : this.chatbox_list)
        {
            if (chatBox.getChat_box_id().equals(((ChatBoxModel) obj).getChat_box_id()))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean check_participation(Object obj)
    {
        return true;
    }

    @Override
    public void replace_item(Object obj)
    {
        int index_of_item = -1;
        ChatBoxModel chatBox = (ChatBoxModel) obj;

        for (int cur = 0; cur < this.chatbox_list.size(); cur++)
        {
            if (this.chatbox_list.get(cur).getChat_box_id().equals(chatBox.getChat_box_id()))
            {
                index_of_item = cur;
                break;
            }
        }

        if (index_of_item > 0)
        {
            this.chatbox_list.set(index_of_item, chatBox);
        }
    }
    //endregion Override

    //region Method

    //endregion Method
}
