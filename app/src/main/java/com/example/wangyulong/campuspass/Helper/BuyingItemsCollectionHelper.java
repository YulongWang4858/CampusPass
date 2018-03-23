package com.example.wangyulong.campuspass.Helper;

import android.databinding.ObservableField;

import com.example.wangyulong.campuspass.Constant.Category;
import com.example.wangyulong.campuspass.Model.BuyingItemModel;

import java.util.ArrayList;

/**
 * Created by wangyulong on 05/03/18.
 */

public class BuyingItemsCollectionHelper extends BasicCollectionHelper
{
    //region Fields and Const
    private static BuyingItemsCollectionHelper _instance = null;
    public ArrayList<BuyingItemModel> item_list = new ArrayList<>();
    public ObservableField<ArrayList<BuyingItemModel>> buying_item_collection = new ObservableField<>(item_list);
    //endregion Fields and Const

    //region Properties
    public static BuyingItemsCollectionHelper buyingItemsCollectionHelper()
    {
        if (_instance == null)
        {
            _instance = new BuyingItemsCollectionHelper();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private BuyingItemsCollectionHelper()
    {

    }
    //endregion Constructor

    //region CRUD
    public void add_item_to_collection(BuyingItemModel new_item)
    {
        ArrayList<BuyingItemModel> inter_arr = buying_item_collection.get();

//        if (new_item.get_item_img_uri().equals(buying_item_collection.get().get(buying_item_collection.get().size() - 2)))
//        {
            inter_arr.add(new_item);
//        }
        buying_item_collection.set(inter_arr);
    }

    public ArrayList<BuyingItemModel> get_full_item_list()
    {
        return this.buying_item_collection.get();
    }

    public BuyingItemModel get_item_at_position(int position)
    {
        return this.item_list.get(position);
    }

    public void refresh_collection()
    {
        this.buying_item_collection.set(new ArrayList<BuyingItemModel>());
    }
    //endregion CRUD
}
