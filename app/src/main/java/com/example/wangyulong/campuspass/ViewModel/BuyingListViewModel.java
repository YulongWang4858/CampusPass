package com.example.wangyulong.campuspass.ViewModel;

import com.example.wangyulong.campuspass.Constant.Category;
import com.example.wangyulong.campuspass.Helper.BuyingItemsCollectionHelper;
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
        BuyingItemModel kimchi = new BuyingItemModel(R.drawable.kimchi_img, "Korean Kimchi", "Fresh homeade kimchi by exchange student", Category.BuyingItemTag.FOOD,
                Category.BuyingItemCondition.NEW, 5, 7, 1);
        BuyingItemModel javabook = new BuyingItemModel(R.drawable.book_img, "Java Book", "Old book from graduating Computing student", Category.BuyingItemTag.BOOKS,
                Category.BuyingItemCondition.SECOND_HAND, 21.5, 1, 1);
        BuyingItemModel aircon = new BuyingItemModel(R.drawable.air_conidtioner_img, "Air Conditioner", "Second hand air conditioner, in perfect condition", Category.BuyingItemTag.ELECTRONICS,
                Category.BuyingItemCondition.SECOND_HAND, 100, 1, 1);
        BuyingItemModel table = new BuyingItemModel(R.drawable.table_img, "Large Desk", "80 * 40 long desk for sale, free shipping, negotiable price", Category.BuyingItemTag.HOME_EQUIPEMENTS,
                Category.BuyingItemCondition.SECOND_HAND, 50.5, 1, 1);

        this.itemsCollectionHelper.add_item_to_collection(kimchi);
        this.itemsCollectionHelper.add_item_to_collection(javabook);
        this.itemsCollectionHelper.add_item_to_collection(aircon);
        this.itemsCollectionHelper.add_item_to_collection(table);
    }

    public ArrayList<BuyingItemModel> get_buying_elements()
    {
        return this.itemsCollectionHelper.get_full_item_list();
    }
    //endregion Methods
}
