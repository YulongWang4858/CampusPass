package com.example.wangyulong.campuspass.Loader;

import com.example.wangyulong.campuspass.Constant.Category;
import com.example.wangyulong.campuspass.Helper.BuyingItemsCollectionHelper;
import com.example.wangyulong.campuspass.Model.BuyingItemModel;
import com.example.wangyulong.campuspass.R;

import java.util.ArrayList;

/**
 * Created by wangyulong on 06/03/18.
 */

public class ComplexDataLoader extends BasicLoader
{

    //region Fields and Const
    private static ComplexDataLoader _instance = null;
    //endregion Fields and Const

    //region Properties
    public static ComplexDataLoader complexDataLoader()
    {
        if (_instance == null)
        {
            _instance = new ComplexDataLoader();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private ComplexDataLoader()
    {
        //init
        //this._itemCollectionHelper = BuyingItemsCollectionHelper.buyingItemsCollectionHelper();
    }
    //endregion Constructor

    //region Methods
    public void loadFromServer()
    {
        BuyingItemsCollectionHelper _itemCollectionHelper = BuyingItemsCollectionHelper.buyingItemsCollectionHelper();

        BuyingItemModel kimchi = new BuyingItemModel(R.drawable.kimchi_img, "Korean Kimchi", "Fresh homeade kimchi by exchange student", Category.BuyingItemTag.FOOD,
                Category.BuyingItemCondition.NEW, 5, 7, 1);
        BuyingItemModel javabook = new BuyingItemModel(R.drawable.book_img, "Java Book", "Old book from graduating Computing student", Category.BuyingItemTag.BOOKS,
                Category.BuyingItemCondition.SECOND_HAND, 21.5, 1, 1);
        BuyingItemModel aircon = new BuyingItemModel(R.drawable.air_conidtioner_img, "Air Conditioner", "Second hand air conditioner, in perfect condition", Category.BuyingItemTag.ELECTRONICS,
                Category.BuyingItemCondition.SECOND_HAND, 100, 1, 1);
        BuyingItemModel table = new BuyingItemModel(R.drawable.table_img, "Large Desk", "80 * 40 long desk for sale, free shipping, negotiable price", Category.BuyingItemTag.HOME_EQUIPEMENTS,
                Category.BuyingItemCondition.SECOND_HAND, 50.5, 1, 1);
        BuyingItemModel macbook = new BuyingItemModel(R.drawable.macbook_img, "MacBook Pro", "13 inch Macbook pro, used for 13 month, comes with casing", Category.BuyingItemTag.ELECTRONICS,
                Category.BuyingItemCondition.SECOND_HAND, 1000, 1, 1);

        _itemCollectionHelper.add_item_to_collection(kimchi);
        _itemCollectionHelper.add_item_to_collection(javabook);
        _itemCollectionHelper.add_item_to_collection(aircon);
        _itemCollectionHelper.add_item_to_collection(table);
        _itemCollectionHelper.add_item_to_collection(macbook);
    }
    //endregion Methods
}
