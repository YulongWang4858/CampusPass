package com.example.wangyulong.campuspass.ViewModel;

import android.databinding.ObservableField;
import android.util.Log;

import com.example.wangyulong.campuspass.Adapter.RequestListViewAdapter;
import com.example.wangyulong.campuspass.Events.MyRequestListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.RequestListRefreshEventListener;
import com.example.wangyulong.campuspass.Helper.RequestEntryCollectionHelper;
import com.example.wangyulong.campuspass.Loader.ComplexDataLoader;
import com.example.wangyulong.campuspass.Model.RequestModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by wangyulong on 22/3/18.
 */

public class RequestViewModel extends BasicViewModel
{
    //region Fields and Const
    private static RequestViewModel _instance = null;
    private RequestEntryCollectionHelper _request_entry_collection_helper;
    public ObservableField<RequestModel> _currently_selected_request = new ObservableField<>(new RequestModel());
    private DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("requests");
    public ObservableField<String> request_name = new ObservableField<>("");
    public ObservableField<String> request_descr = new ObservableField<>("");
    public ObservableField<String> request_price = new ObservableField<>("");
    //endregion Fields and Const

    //region Properties
    public static RequestViewModel requestViewModel()
    {
        if (_instance == null)
        {
            _instance = new RequestViewModel();
        }

        return _instance;
    }

    public void set_currently_selected_request_category(String category)
    {
        this._currently_selected_request.get().setRequest_category(category);
    }
    //endregion Properties

    //region Constructor
    private RequestViewModel()
    {
        this._request_entry_collection_helper = RequestEntryCollectionHelper.requestEntryCollectionHelper();
    }
    //endregion Constructor

    //region Methods
    public ArrayList<RequestModel> get_request_list_entries()
    {
        return this._request_entry_collection_helper.get_full_list();
    }

    public void create_new_request()
    {
        //fill database
        RequestModel new_request = this._currently_selected_request.get();
        String request_id = UUID.randomUUID().toString();
        new_request.setRequest_name(this.request_name.get());
        new_request.setRequest_price(this.request_price.get());
        new_request.setRequest_descrip(this.request_descr.get());
        new_request.setRequest_owner_id(FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
        new_request.setRequest_entry_id(request_id);
        new_request.setRequest_owner(RegisterViewModel.registerViewModel().get_login_user_name());
        this._currently_selected_request.set(new_request);

        //push to database
        databaseRef.child(request_id).setValue(new_request).addOnSuccessListener(new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid)
            {
                showOnSnackBar("New request successfully uploaded");
            }
        });
    }

    public void setRequestPageRefreshEvents(RequestListRefreshEventListener listener)
    {
        ComplexDataLoader.complexDataLoader().setRequestListRefreshEventListener(listener);
    }

    public void setMyRequestPageRefreshEvents(MyRequestListRefreshEventListener myRequestPageRefreshEvents)
    {
        ComplexDataLoader.complexDataLoader().setMyRequestListRefreshEventListener(myRequestPageRefreshEvents);
    }

    public void allow_database_read()
    {
        ComplexDataLoader.complexDataLoader().is_request_read_allowed = true;
    }

    public void read_requests()
    {
        ComplexDataLoader.complexDataLoader().load_request_from_database();
    }

    public ArrayList<RequestModel> get_myrequest_list_entry()
    {
        return this._request_entry_collection_helper.get_myrequest_full_list();
    }

    public void read_myrequests()
    {
        this._request_entry_collection_helper.initialize_myrequests(FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
    }

    public void set_new_item_selected(int index)
    {
        RequestModel request = this._request_entry_collection_helper.retrieve_my_entry_at_position(index);
        this._currently_selected_request.set(request);
        this.request_name.set(request.getRequest_name());
        this.request_descr.set(request.getRequest_descrip());
        this.request_price.set(request.getRequest_price());
    }

    public void update_request_entry()
    {
        RequestModel request = this._currently_selected_request.get();
        request.setRequest_name(this.request_name.get());
        request.setRequest_descrip(this.request_descr.get());
        request.setRequest_price(this.request_price.get());

        this._currently_selected_request.set(request);
        this._request_entry_collection_helper.update_my_request_entry(request);

        String entry_id = request.getRequest_entry_id();
        Log.d("debug -> ", entry_id);
        this._request_entry_collection_helper.update_request_entry(request);

        //push to database
        databaseRef.child(entry_id).setValue(request).addOnSuccessListener(new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid)
            {
                showOnSnackBar("New request successfully uploaded");
            }
        });
    }
    //endregion Methods
}
