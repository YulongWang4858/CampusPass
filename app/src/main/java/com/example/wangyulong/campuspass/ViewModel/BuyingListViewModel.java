package com.example.wangyulong.campuspass.ViewModel;

import android.app.Activity;
import android.databinding.ObservableField;

import com.bumptech.glide.Glide;
import com.example.wangyulong.campuspass.Constant.Category;
import com.example.wangyulong.campuspass.Helper.BuyingItemsCollectionHelper;
import com.example.wangyulong.campuspass.Helper.ConnectionHelper;
import com.example.wangyulong.campuspass.Model.BuyingItemModel;
import com.example.wangyulong.campuspass.R;

import java.util.ArrayList;

/**
 * Created by wangyulong on 05/03/18.
 */

public class BuyingListViewModel extends BasicViewModel
{
    //region Fields and Const
    private static BuyingListViewModel _instance = null;
    private BuyingItemsCollectionHelper itemsCollectionHelper = BuyingItemsCollectionHelper.buyingItemsCollectionHelper();
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
        //TODO: Remove after testing
        loadFromServer();
    }
    //endregion Constructor

    //region Methods
    private void loadFromServer()
    {
        ConnectionHelper.connectionHelper().loadFromServer();
    }

    public ArrayList<BuyingItemModel> get_buying_elements()
    {
        return this.itemsCollectionHelper.get_full_item_list();
    }

    public void set_new_item_selected(int position)
    {
        this._currentlySelectedItem.set(this.itemsCollectionHelper.get_item_at_position(position));
    }

    public String get_new_item_selected_img_uri()
    {
        return this._currentlySelectedItem.get().get_item_img_uri();
    }

    public BuyingItemModel get_selected_item()
    {
        return this._currentlySelectedItem.get();
    }

    public int get_selected_item_img_rsc()
    {
        return this._currentlySelectedItem.get().get_item_image_id();
    }

    public String get_selected_item_short_descr()
    {
        return this._currentlySelectedItem.get().get_item_short_description();
    }

    public String get_selected_item_title()
    {
        return this._currentlySelectedItem.get().get_item_title();
    }

    public String get_selected_item_price()
    {
        return Double.toString(this._currentlySelectedItem.get().get_item_price());
    }

    public String get_selected_item_condition()
    {
        switch (this._currentlySelectedItem.get().get_item_condition())
        {
            case NEW:
            return "New";
            case SECOND_HAND:
                return "Second Hand";
            default:
                return "Dispose";
        }
    }

    public String get_selected_item_category()
    {
        switch (this._currentlySelectedItem.get().get_item_tag())
        {
            case ELECTRONICS:
                return "Electronics";
            case HOME_EQUIPEMENTS:
                return "Home equipments";
            case BOOKS:
                return "Books";
            case FOOD:
                return "Food";
            default:
                return "Tool";
        }
    }
    //endregion Methods
}
