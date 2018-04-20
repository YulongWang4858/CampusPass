package com.example.wangyulong.campuspass.Helper;

import android.content.ClipData;

import com.example.wangyulong.campuspass.Model.ItemModel;

import java.util.ArrayList;

/**
 * Created by wangyulong on 19/4/18.
 */

public class MyItemsCollectionHelper implements CollectionHelper
{
    //region Fields and Const
    private static MyItemsCollectionHelper _instance = null;
    private ArrayList<ItemModel> myItemList;
    //endregion Fields and Const

    //region Properties
    public static MyItemsCollectionHelper myItemsCollectionHelper()
    {
        if (_instance == null)
        {
            _instance = new MyItemsCollectionHelper();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructors
    private MyItemsCollectionHelper()
    {
        //init
        this.myItemList = new ArrayList<>();
    }
    //endregion Constructors

    //region Override
    @Override
    public Object get_full_list()
    {
        return this.myItemList;
    }

    @Override
    public Object get_item_at_position(int i)
    {
        return this.myItemList.get(i);
    }

    @Override
    public void reset_collection()
    {
        this.myItemList = new ArrayList<>();
    }

    @Override
    public void add_item_to_collection(Object obj)
    {
        this.myItemList.add((ItemModel) obj);
    }

    @Override
    public void remove_item_from_collection(String id)
    {
        int index_of_item = -1;

        for (int cur = 0; cur < this.myItemList.size(); cur++)
        {
            if (this.myItemList.get(cur).getItem_id().equals(id))
            {
                index_of_item = cur;
                break;
            }
        }

        if (index_of_item >= 0)
        {
            this.myItemList.remove(index_of_item);
        }
    }

    @Override
    public boolean check_exsitance(Object obj)
    {
        for (ItemModel item : this.myItemList)
        {
            if (item.getItem_id().equals(((ItemModel) obj).getItem_id()))
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
        ItemModel itemModel = (ItemModel) obj;

        int index_to_replace = -1;

        for (int cur = 0; cur < this.myItemList.size(); cur++)
        {
            if (this.myItemList.get(cur).getItem_id().equals(itemModel.getItem_id()))
            {
                index_to_replace = cur;
                break;
            }
        }

        if (index_to_replace >= 0)
        {
            this.myItemList.set(index_to_replace, itemModel);
        }
    }
    //endregion Override

    //region Methods

    //endregion Methods
}
