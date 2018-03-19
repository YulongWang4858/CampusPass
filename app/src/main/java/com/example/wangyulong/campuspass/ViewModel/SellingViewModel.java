package com.example.wangyulong.campuspass.ViewModel;

import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.inputmethod.BaseInputConnection;

import com.example.wangyulong.campuspass.Constant.Category;
import com.example.wangyulong.campuspass.Model.SellingItemModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

/**
 * Created by wangyulong on 09/03/18.
 */

public class SellingViewModel extends BasicViewModel
{

    //region Fields and Const
    private static SellingViewModel _instance;
    public SellingItemModel itemModel;
    public ObservableField<Boolean> isImageSelected = new ObservableField<>(false);
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseRef = database.getReference("items_for_sale");
    //endregion Fields and Const

    //region Properties
    public static SellingViewModel sellingViewModel()
    {
        if (_instance == null)
        {
            _instance = new SellingViewModel();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private SellingViewModel()
    {
        this.itemModel = new SellingItemModel();
    }
    //endregion Constructor

    //region Methods
    public void set_item_condition(Category.BuyingItemCondition condition)
    {
        this.itemModel.item_condition.set(condition);
    }

    public void set_item_tag(Category.BuyingItemTag tag)
    {
        this.itemModel.item_tag.set(tag);
    }

    public void is_selling_item_upload_complete()
    {
        this.isImageSelected.set(false);
    }

    public void set_photo_to_upload(Uri uri)
    {
        Log.d("Binding --> ", "New uri set for resh photo");

        this.itemModel.item_img_download_uri.set(uri);
    }

    public Uri get_photo_to_upload()
    {
        return this.itemModel.item_img_download_uri.get();
    }

    public void create_selling_item_on_database()
    {
        String _database_user_identifier = firebaseAuth.getCurrentUser().getUid();
        String _database_item_identifier = UUID.randomUUID().toString();

        databaseRef.child(_database_item_identifier).child("item_owner")
                .setValue(_database_user_identifier);
        databaseRef.child(_database_item_identifier).child("item_title")
                .setValue(this.itemModel.item_title.get());
        databaseRef.child(_database_item_identifier).child("item_short_descr")
                .setValue(this.itemModel.item_short_descr.get());
        databaseRef.child(_database_item_identifier).child("item_condition_tag")
                .setValue(this.itemModel.item_condition.get());
        databaseRef.child(_database_item_identifier).child("item_category_tag")
                .setValue(this.itemModel.item_tag.get());
        databaseRef.child(_database_item_identifier).child("item_stock_left")
                .setValue(this.itemModel.stock.get());
        databaseRef.child(_database_item_identifier).child("item_price")
                .setValue(this.itemModel.price.get());
        databaseRef.child(_database_item_identifier).child("item_img_uri")
                .setValue(this.itemModel.item_img_download_uri.get().toString());
    }
    //endregion Methods
}
