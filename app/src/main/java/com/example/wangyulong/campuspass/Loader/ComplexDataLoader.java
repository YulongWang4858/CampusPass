package com.example.wangyulong.campuspass.Loader;

import android.util.Log;
import android.widget.ExpandableListView;

import com.example.wangyulong.campuspass.Constant.Category;
import com.example.wangyulong.campuspass.Events.BuyingListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.MyRequestListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.RequestListRefreshEventListener;
import com.example.wangyulong.campuspass.Helper.BuyingItemsCollectionHelper;
import com.example.wangyulong.campuspass.Helper.RequestEntryCollectionHelper;
import com.example.wangyulong.campuspass.Model.BuyingItemModel;
import com.example.wangyulong.campuspass.Model.DatabaseSellingModel;
import com.example.wangyulong.campuspass.Model.DatabaseUserModel;
import com.example.wangyulong.campuspass.Model.RequestModel;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.BuyingListViewModel;
import com.example.wangyulong.campuspass.ViewModel.RequestViewModel;
import com.google.firebase.database.ChildEventListener;
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
    private DatabaseReference itemDatabaseRef = database.getReference("items_for_sale");
    private DatabaseReference requestDatabaseRef = database.getReference("requests");
    private BuyingItemsCollectionHelper _itemCollectionHelper = BuyingItemsCollectionHelper.buyingItemsCollectionHelper();
    private RequestEntryCollectionHelper _requestCollectionHelper = RequestEntryCollectionHelper.requestEntryCollectionHelper();
    private String new_item_title = "";
    public boolean is_request_read_allowed = false;

    //event listeners
    private ChildEventListener requestChildEventListener;
    private ChildEventListener itemChildEventListener;
    private RequestListRefreshEventListener requestListRefreshEventListener;
    private BuyingListRefreshEventListener buyingListRefreshEventListener;
    private MyRequestListRefreshEventListener myRequestListRefreshEventListener;
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
        this.requestChildEventListener = new ChildEventListener()
        {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {
                RequestModel request_model = new RequestModel();

                request_model.setRequest_entry_id(dataSnapshot.getValue(RequestModel.class).getRequest_entry_id());
                request_model.setRequest_name(dataSnapshot.getValue(RequestModel.class).getRequest_name());
                request_model.setRequest_owner(dataSnapshot.getValue(RequestModel.class).getRequest_owner());
                request_model.setRequest_owner_id(dataSnapshot.getValue(RequestModel.class).getRequest_owner_id());
                request_model.setRequest_price(dataSnapshot.getValue(RequestModel.class).getRequest_price());
                request_model.setRequest_descrip(dataSnapshot.getValue(RequestModel.class).getRequest_descrip());
                request_model.setRequest_category(dataSnapshot.getValue(RequestModel.class).getRequest_category());

                Log.d("download from database ", "request id -> " + request_model.getRequest_entry_id());
                Log.d("download from database ", "title -> " + request_model.getRequest_name());
                Log.d("download from database ", "description -> " + request_model.getRequest_descrip());
                Log.d("download from database ", "price -> " + request_model.getRequest_price());
                Log.d("download from database ", "category -> " + request_model.getRequest_category());
                Log.d("download from database ", "owner_name -> " + request_model.getRequest_owner());
                Log.d("download from database ", "owner_id -> " + request_model.getRequest_owner_id());

                if (!_requestCollectionHelper.check_existance(request_model.getRequest_entry_id()))
                {
                    _requestCollectionHelper.add_new_item_to_collection(request_model);
                }

                //invoke refresh action
                if (requestListRefreshEventListener != null)
                {
                    requestListRefreshEventListener.onRequestListRefreshEventTrigger();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s)
            {
                //TODO: Implement later

                Log.d("debug -> ", "reached child changed");

                //invoke refresh action
                if (requestListRefreshEventListener != null)
                {
                    requestListRefreshEventListener.onRequestListRefreshEventTrigger();
                }
                if (myRequestListRefreshEventListener != null)
                {
                    myRequestListRefreshEventListener.onMyRequestListRefreshEventTrigger();
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot)
            {
                //TODO: Implement later
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s)
            {
                //TODO: Implement later
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        };

        this.itemChildEventListener = new ChildEventListener()
        {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {
                DatabaseSellingModel item_model = new DatabaseSellingModel();

                //download item model from database
                item_model.setItem_id(dataSnapshot.getValue(DatabaseSellingModel.class).getItem_id());
                item_model.setItem_title(dataSnapshot.getValue(DatabaseSellingModel.class).getItem_title());
                item_model.setItem_category_tag(dataSnapshot.getValue(DatabaseSellingModel.class).getItem_category_tag());
                item_model.setItem_condition_tag(dataSnapshot.getValue(DatabaseSellingModel.class).getItem_condition_tag());
                item_model.setItem_img_uri(dataSnapshot.getValue(DatabaseSellingModel.class).getItem_img_uri());
                item_model.setItem_short_descr(dataSnapshot.getValue(DatabaseSellingModel.class).getItem_short_descr());
                item_model.setItem_owner(dataSnapshot.getValue(DatabaseSellingModel.class).getItem_owner());
                item_model.setItem_stock_left(dataSnapshot.getValue(DatabaseSellingModel.class).getItem_stock_left());
                item_model.setItem_price(dataSnapshot.getValue(DatabaseSellingModel.class).getItem_price());

                //for debugging
                Log.d("download from database", "item id -> " + item_model.getItem_id());
                Log.d("download from database", "title -> " + item_model.getItem_title());
                Log.d("download from database", "short_descr -> " + item_model.getItem_short_descr());
                Log.d("download from database", "condition -> " + item_model.getItem_condition_tag());
                Log.d("download from database", "category -> " + item_model.getItem_category_tag());
                Log.d("download from database", "owner -> " + item_model.getItem_owner());
                Log.d("download from database", "price -> " + item_model.getItem_price());
                Log.d("download from database", "stock -> " + item_model.getItem_stock_left());
                Log.d("download from database", "download link -> " + item_model.getItem_img_uri());

                if (!_itemCollectionHelper.check_existance(item_model))
                {
                    _itemCollectionHelper.add_item_to_collection(new BuyingItemModel(item_model.getItem_img_uri(), item_model.getItem_title(), item_model.getItem_short_descr(), item_model.getItem_category_tag(),
                            item_model.getItem_condition_tag(), item_model.getItem_price(), item_model.getItem_stock_left(), item_model.getItem_owner(), item_model.getItem_id()));
                } else
                {
                    Log.d("duplicated found", "");
                }

                if (buyingListRefreshEventListener != null)
                {
                    buyingListRefreshEventListener.onBuyingListRefreshEventTriggered();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s)
            {
                //TODO: Implement later
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot)
            {
                //TODO: Implement later
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s)
            {
                //TODO: Implement later
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                //TODO: Implement later
            }
        };
    }
    //endregion Constructor

    //region Methods
    public void load_items_from_database()
    {
        this.itemDatabaseRef.addChildEventListener(this.itemChildEventListener);
    }

    public void load_request_from_database()
    {
        this.requestDatabaseRef.addChildEventListener(this.requestChildEventListener);
    }

    public void unload_request_from_database()
    {
        this.requestDatabaseRef.removeEventListener(this.requestChildEventListener);
    }

    public void set_new_item_title(String title)
    {
        this.new_item_title = title;
    }

    public void setRequestListRefreshEventListener(RequestListRefreshEventListener requestListRefreshEventListener)
    {
        this.requestListRefreshEventListener = requestListRefreshEventListener;
    }

    public void setBuyingListRefreshEventListener(BuyingListRefreshEventListener buyingListRefreshEventListener)
    {
        this.buyingListRefreshEventListener = buyingListRefreshEventListener;
    }

    public void setMyRequestListRefreshEventListener(MyRequestListRefreshEventListener myRequestListRefreshEventListener)
    {
        this.myRequestListRefreshEventListener = myRequestListRefreshEventListener;
    }
    //endregion Methods
}
