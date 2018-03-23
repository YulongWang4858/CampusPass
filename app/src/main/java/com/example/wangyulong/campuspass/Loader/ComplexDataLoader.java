package com.example.wangyulong.campuspass.Loader;

import android.util.Log;

import com.example.wangyulong.campuspass.Constant.Category;
import com.example.wangyulong.campuspass.Helper.BuyingItemsCollectionHelper;
import com.example.wangyulong.campuspass.Model.BuyingItemModel;
import com.example.wangyulong.campuspass.Model.DatabaseSellingModel;
import com.example.wangyulong.campuspass.Model.DatabaseUserModel;
import com.example.wangyulong.campuspass.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by wangyulong on 06/03/18.
 */

public class ComplexDataLoader extends BasicLoader
{

    //region Fields and Const
    private static ComplexDataLoader _instance = null;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseRef = database.getReference("items_for_sale");
    BuyingItemsCollectionHelper _itemCollectionHelper = BuyingItemsCollectionHelper.buyingItemsCollectionHelper();
    private String new_item_title = "";
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
        databaseRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                //refresh item list
                //_itemCollectionHelper.refresh_collection();

                //construct item model for each item in database, them stores in collectionHelper
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    DatabaseSellingModel item_model = new DatabaseSellingModel();

                    item_model.setItem_title(ds.getValue(DatabaseSellingModel.class).getItem_title());
                    item_model.setItem_category_tag(ds.getValue(DatabaseSellingModel.class).getItem_category_tag());
                    item_model.setItem_condition_tag(ds.getValue(DatabaseSellingModel.class).getItem_condition_tag());
                    item_model.setItem_img_uri(ds.getValue(DatabaseSellingModel.class).getItem_img_uri());
                    item_model.setItem_short_decr(ds.getValue(DatabaseSellingModel.class).getItem_short_decr());
                    item_model.setItem_owner(ds.getValue(DatabaseSellingModel.class).getItem_owner());
                    item_model.setItem_stock_left(ds.getValue(DatabaseSellingModel.class).getItem_stock_left());
                    item_model.setItem_price(ds.getValue(DatabaseSellingModel.class).getItem_price());

                    Log.d("download from database", "title -> " + item_model.getItem_title());
                    Log.d("download from database", "short_descr -> " + item_model.getItem_short_decr());
                    Log.d("download from database", "condition -> " + item_model.getItem_condition_tag());
                    Log.d("download from database", "category -> " + item_model.getItem_category_tag());
                    Log.d("download from database", "owner -> " + item_model.getItem_owner());
                    Log.d("download from database", "price -> " + item_model.getItem_price());
                    Log.d("download from database", "stock -> " + item_model.getItem_stock_left());
                    Log.d("download from database", "download link -> " + item_model.getItem_img_uri());

                    if (item_model.getItem_title() != null && item_model.getItem_owner() != null && item_model.getItem_stock_left() != null
                            && item_model.getItem_short_decr() != null && item_model.getItem_price() != null && item_model.getItem_img_uri() != null &&
                            item_model.getItem_category_tag() != null && item_model.getItem_condition_tag() != null && (new_item_title.equals("") || new_item_title.equals(item_model.getItem_title())))
                    {
                        _itemCollectionHelper.add_item_to_collection(new BuyingItemModel(item_model.getItem_img_uri(), item_model.getItem_title(), item_model.getItem_short_decr(), item_model.getItem_category_tag(),
                                item_model.getItem_condition_tag(), item_model.getItem_price(), item_model.getItem_stock_left(), item_model.getItem_owner()));
                    } else
                    {
                        Log.d("download from database", "null item creation");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                //TODO: Implement cancel if required
            }
        });
    }
    //endregion Constructor

    //region Methods
    public void loadFromServer()
    {

    }

    public void set_new_item_title(String title)
    {
        this.new_item_title = title;
    }
    //endregion Methods
}
