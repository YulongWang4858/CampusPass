package com.example.wangyulong.campuspass.ViewModel;

import android.databinding.ObservableField;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.wangyulong.campuspass.Events.MyItemListRefreshEventListener;
import com.example.wangyulong.campuspass.Helper.ItemCollectionHelper;
import com.example.wangyulong.campuspass.Helper.MyItemsCollectionHelper;
import com.example.wangyulong.campuspass.Loader.ComplexDataLoader;
import com.example.wangyulong.campuspass.Model.ItemModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by wangyulong on 09/03/18.
 */

public class SellingViewModel extends BasicViewModel
{

    //region Fields and Const
    private static SellingViewModel _instance;
    private ItemModel newItemModel;
    private ItemModel selectedItemModel;
    public ObservableField<Boolean> isImageSelected = new ObservableField<>(false);
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseRef = database.getReference("items_for_sale");
    public ObservableField<Boolean> isUploadFinished = new ObservableField<>(false);
    private String selected_item_id;

    //binding
    public ObservableField<String> item_name = new ObservableField<>(new String(""));
    public ObservableField<String> item_descr = new ObservableField<>(new String(""));
    public ObservableField<String> item_price = new ObservableField<>(new String(""));
    public ObservableField<String> item_photo_uri = new ObservableField<>(new String(""));
    public ObservableField<String> item_condition = new ObservableField<>(new String(""));
    public ObservableField<String> item_category = new ObservableField<>(new String(""));
    public ObservableField<String> item_stock = new ObservableField<>(new String(""));
    public ObservableField<Boolean> delete_button_visibility = new ObservableField<>(false);

    private boolean is_change = false;
    private ComplexDataLoader dataLoader;
    private ItemCollectionHelper itemCollectionHelper;
    private MyItemsCollectionHelper myItemsCollectionHelper;
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
        this.newItemModel = new ItemModel();
        this.selectedItemModel = new ItemModel();
        this.dataLoader = ComplexDataLoader.complexDataLoader();
        this.itemCollectionHelper = ItemCollectionHelper.itemCollectionHelper();
        this.myItemsCollectionHelper = MyItemsCollectionHelper.myItemsCollectionHelper();
    }
    //endregion Constructor

    //region Methods
    public void set_item_condition(int index)
    {
        switch (index)
        {
            case 1:
                this.item_condition.set("New");
            case 2:
                this.item_condition.set("Dispose");
            case 3:
                this.item_condition.set("Second Hand");
            default:
        }

        this.showOnSnackBar("new condition set to -> " + this.newItemModel.getItem_condition());
    }

    public void set_item_category(int index)
    {
        switch (index)
        {
            case 1:
                this.item_category.set("Books");
            case 2:
                this.item_category.set("Electronics");
            case 3:
                this.item_category.set("Food");
            case 4:
                this.item_category.set("Home Equipments");
            default:
        }

        this.showOnSnackBar("new category set to -> " + this.newItemModel.getItem_category());
    }

    public void is_selling_item_upload_complete()
    {
        this.isImageSelected.set(false);
    }

    public void set_photo_to_upload(Uri uri)
    {
        this.item_photo_uri.set(uri.toString());
        this.newItemModel.setItem_photo_uri(uri.toString());

        //debug
        Log.d("debug -> ", "obtained download link -> " + uri.toString());
    }

    public Uri get_photo_to_upload()
    {
        return Uri.parse(this.newItemModel.getItem_photo_uri());
    }

    public void create_selling_item_on_database()
    {
        String owner_id = firebaseAuth.getCurrentUser().getUid();
        String _database_item_identifier = this.is_change ? this.selected_item_id : UUID.randomUUID().toString();

        //fill database object
        this.newItemModel.setItem_name(this.item_name.get());
        this.newItemModel.setItem_descr(this.item_descr.get());
        this.newItemModel.setItem_price(this.item_price.get());
        this.newItemModel.setItem_stock_left(this.item_stock.get());
        this.newItemModel.setItem_owner_id(owner_id);
        this.newItemModel.setItem_condition(this.item_condition.get());
        this.newItemModel.setItem_category(this.item_category.get());
        this.newItemModel.setItem_id(_database_item_identifier);
        this.newItemModel.setItem_photo_uri(this.item_photo_uri.get());

        //push to database
        databaseRef.child(_database_item_identifier).setValue(this.newItemModel).addOnSuccessListener(new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid)
            {
                showOnSnackBar("Selling item uploaded successfully");
            }
        }).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                showOnSnackBar("Selling item uploaded successfully");

                delete_button_visibility.set(false);
            }
        });
    }

    public void load_item_list_from_database()
    {
        dataLoader.load_items_from_database();
    }

    public void upload_complete()
    {
        this.isUploadFinished.set(true);
    }

    public ArrayList<ItemModel> get_my_item_full_list()
    {
        return (ArrayList<ItemModel>) this.myItemsCollectionHelper.get_full_list();
    }

    public void set_new_item_selected(ItemModel item)
    {
        //make delete button visible
        this.delete_button_visibility.set(true);

        //set update selection
        this.selected_item_id = item.getItem_id();
        this.item_name.set(item.getItem_name());
        this.item_descr.set(item.getItem_descr());
        this.item_price.set(item.getItem_price());
        this.item_stock.set(item.getItem_stock_left());
        this.item_photo_uri.set(item.getItem_photo_uri());

        //debug
        Log.d("debug -> ", "new item selected: " + item.getItem_name());

        if (this.is_change)
        {
            this.isUploadFinished.set(true);
        } else
        {
            this.isUploadFinished.set(false);
        }
    }

    public void load_my_item_list_from_database()
    {
        this.dataLoader.load_my_item_from_database();
    }

    public void enableEdit()
    {
        this.is_change = true;
    }

    public void disableEdit()
    {
        this.is_change = false;
    }

    public void setRefreshListener(MyItemListRefreshEventListener listener)
    {
        this.dataLoader.setMyItemListRefreshEventListener(listener);
    }

    public void toggle_delete_visibility()
    {
        this.delete_button_visibility.set(false);
    }

    public void delete_current_item()
    {
        this.dataLoader.remove_value_from_items(FirebaseDatabase.getInstance().getReference("items_for_sale").child(this.selected_item_id), this.selected_item_id);
    }

    public String set_detail_photo()
    {
        return this.item_photo_uri.get();
    }
    //endregion Methods
}
