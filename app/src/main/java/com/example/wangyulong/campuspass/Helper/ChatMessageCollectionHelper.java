package com.example.wangyulong.campuspass.Helper;

import com.example.wangyulong.campuspass.Model.ChatBoxModel;
import com.example.wangyulong.campuspass.Model.ChatMessageModel;

import java.util.ArrayList;

/**
 * Created by wangyulong on 21/4/18.
 */

public class ChatMessageCollectionHelper implements CollectionHelper
{
    //region Fields and Const
    private static ChatMessageCollectionHelper _instance = null;
    private ArrayList<ChatMessageModel> chatMessageList;
    private SortingHelper sortter = null;
    //endregion Fields and Const

    //region Properties
    public static ChatMessageCollectionHelper chatMessageCollectionHelper()
    {
        if (_instance == null)
        {
            _instance = new ChatMessageCollectionHelper();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructors
    private ChatMessageCollectionHelper()
    {
        //init
        this.chatMessageList = new ArrayList<>();
        this.sortter = SortingHelper.sortingHelper();
    }
    //endregion Constructors

    //region Override
    @Override
    public Object get_full_list()
    {
        return this.chatMessageList;
    }

    @Override
    public Object get_item_at_position(int i)
    {
        return this.chatMessageList.get(i);
    }

    @Override
    public void reset_collection()
    {
        this.chatMessageList = new ArrayList<>();
    }

    @Override
    public void add_item_to_collection(Object obj)
    {
        this.chatMessageList.add((ChatMessageModel) obj);
    }

    @Override
    public void remove_item_from_collection(String id)
    {
    }

    @Override
    public boolean check_exsitance(Object obj)
    {
        for (ChatMessageModel msg : this.chatMessageList)
        {
            if (msg.getChat_msg_id().equals(((ChatMessageModel) obj).getChat_msg_id()))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean check_participation(Object obj)
    {
        return false;
    }

    @Override
    public void replace_item(Object obj)
    {

    }

    //endregion Override

    //region Methods
    public void sort_messages_by_date()
    {
        int array[] = new int[this.chatMessageList.size()];

        for (int cur = 0; cur < this.chatMessageList.size(); cur++)
        {
            array[cur] = Integer.getInteger(Long.toString(this.chatMessageList.get(cur).getChat_msg_timestamp()));
        }

        int sorted[] = sortter.sort(array);

        ArrayList<ChatMessageModel> new_list = new ArrayList<>();

    }
    //endregion Methods
}
