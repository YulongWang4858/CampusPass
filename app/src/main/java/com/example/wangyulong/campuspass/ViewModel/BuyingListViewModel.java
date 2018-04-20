package com.example.wangyulong.campuspass.ViewModel;

import android.app.Activity;
import android.databinding.ObservableField;

import com.bumptech.glide.Glide;
import com.example.wangyulong.campuspass.Adapter.ItemThumbnailAdpter;
import com.example.wangyulong.campuspass.Constant.Category;
import com.example.wangyulong.campuspass.Events.BuyingListRefreshEventListener;
import com.example.wangyulong.campuspass.Helper.BuyingItemsCollectionHelper;
import com.example.wangyulong.campuspass.Helper.ConnectionHelper;
import com.example.wangyulong.campuspass.Helper.ItemCollectionHelper;
import com.example.wangyulong.campuspass.Loader.ComplexDataLoader;
import com.example.wangyulong.campuspass.Model.BuyingItemModel;
import com.example.wangyulong.campuspass.R;

import java.util.ArrayList;

/**
 * Created by wangyulong on 05/03/18.
 */

public class BuyingListViewModel implements BasicListViewModel
{
    //region Fields and Const
    private static BuyingListViewModel _instance = null;
    private ItemCollectionHelper itemCollectionHelper;
    private BuyingListRefreshEventListener refreshEventListener = null;
    private ComplexDataLoader loader;


    public ObservableField<BuyingItemModel> _currentlySelectedItem = new ObservableField<>(new BuyingItemModel());
    //endregion Fields and Const

    //region Properties
    public static BuyingListViewModel buyingListViewModel()
    {
        if (_instance == null)
        {
            _instance = new BuyingListViewModel();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private BuyingListViewModel()
    {
        //init
        this.itemCollectionHelper = ItemCollectionHelper.itemCollectionHelper();
        this.loader = ComplexDataLoader.complexDataLoader();
    }
    //endregion Constructor

    //region Override
    @Override
    public void setRefreshListener(Object obj)
    {
        this.refreshEventListener = (BuyingListRefreshEventListener) obj;
        this.loader.setBuyingListRefreshEventListener((BuyingListRefreshEventListener) obj);
    }

    @Override
    public void load_from_database()
    {
        this.loader.load_items_from_database();
    }

    @Override
    public Object get_full_collection()
    {
        return this.itemCollectionHelper.get_full_list();
    }
    //endregion Override

    //region Methods

    //endregion Methods
}
