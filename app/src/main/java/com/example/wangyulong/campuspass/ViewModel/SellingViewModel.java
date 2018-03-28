package com.example.wangyulong.campuspass.ViewModel;

import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.inputmethod.BaseInputConnection;

import com.example.wangyulong.campuspass.Constant.Category;
import com.example.wangyulong.campuspass.Loader.ComplexDataLoader;
import com.example.wangyulong.campuspass.Model.DatabaseSellingModel;
import com.example.wangyulong.campuspass.Model.DatabaseUserModel;
import com.example.wangyulong.campuspass.Model.SellingItemModel;
import com.google.android.gms.tasks.OnSuccessListener;
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
    public ObservableField<Boolean> isUploadFinished = new ObservableField<>(false);
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

        ComplexDataLoader.complexDataLoader().set_new_item_title(this.itemModel.item_title.get());

        //fill database object
        DatabaseSellingModel selling_model = new DatabaseSellingModel();
        selling_model.setItem_id(_database_item_identifier);
        selling_model.setItem_owner(_database_user_identifier);
        selling_model.setItem_title(this.itemModel.item_title.get());
        selling_model.setItem_short_descr(this.itemModel.item_short_descr.get());
        selling_model.setItem_price(this.itemModel.price.get());
        selling_model.setItem_category_tag(this.itemModel.item_tag.get().toString());
        selling_model.setItem_condition_tag(this.itemModel.item_condition.get().toString());
        selling_model.setItem_stock_left(this.itemModel.stock.get());
        selling_model.setItem_img_uri(this.itemModel.item_img_download_uri.get().toString());

        databaseRef.child(_database_item_identifier).setValue(selling_model).addOnSuccessListener(new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid)
            {
                showOnSnackBar("Selling item uploaded successfully");
            }
        });
    }

    public void upload_complete()
    {
        this.isUploadFinished.set(true);
    }
    //endregion Methods
}
