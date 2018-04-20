package com.example.wangyulong.campuspass.Helper;

import android.content.ClipData;

import com.example.wangyulong.campuspass.Loader.ComplexDataLoader;
import com.example.wangyulong.campuspass.Model.ItemModel;

import java.util.ArrayList;

/**
 * Created by wangyulong on 18/4/18.
 */

public class ItemCollectionHelper implements CollectionHelper
{
    //region Fields and Const
    private ArrayList<ItemModel> itemList;
    private static ItemCollectionHelper _instance = null;
    //private ComplexDataLoader loader;
    //endregion Fields and Const

    //region Properties
    public static ItemCollectionHelper itemCollectionHelper()
    {
        if (_instance == null)
        {
            _instance = new ItemCollectionHelper();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private ItemCollectionHelper()
    {
        //inti
        this.itemList = new ArrayList<>();
    }
    //endregion Constructor

    //region Override
    @Override
    public Object get_full_list()
    {
        return this.itemList;
    }

    @Override
    public Object get_item_at_position(int i)
    {
        return this.itemList.get(i);
    }

    @Override
    public void reset_collection()
    {
        this.itemList = new ArrayList<>();
    }

    @Override
    public void add_item_to_collection(Object obj)
    {
        this.itemList.add((ItemModel) obj);
    }

    @Override
    public void remove_item_from_collection(String id)
    {
        int index = -1;

        for (int cur = 0; cur < this.itemList.size(); cur++)
        {
            if (this.itemList.get(cur).getItem_id().equals(id))
            {
                index = cur;
                break;
            }
        }

        if (index != -1)
        {
            this.itemList.remove(index);
        }

        //TODO: Attach refresh events here
    }

    @Override
    public boolean check_exsitance(Object obj)
    {
        ItemModel item = (ItemModel) obj;

        for (ItemModel item_model : this.itemList)
        {
            if (item_model.getItem_id().equals(item.getItem_id()))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean check_participation(Object obj)
    {
        ItemModel item = (ItemModel) obj;

        for (ItemModel item_model : this.itemList)
        {
            if (item_model.getItem_owner_id().equals(item.getItem_owner_id()))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public void replace_item(Object obj)
    {
        ItemModel itemModel = (ItemModel) obj;

        int index_to_replace = -1;

        for (int cur = 0; cur < this.itemList.size(); cur++)
        {
            if (this.itemList.get(cur).getItem_id().equals(itemModel.getItem_id()))
            {
                index_to_replace = cur;
                break;
            }
        }

        if (index_to_replace >= 0)
        {
            this.itemList.set(index_to_replace, itemModel);
        }
    }
    //endregion Override
}
