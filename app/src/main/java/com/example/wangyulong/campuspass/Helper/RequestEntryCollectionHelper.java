package com.example.wangyulong.campuspass.Helper;


import android.database.Observable;
import android.databinding.ObservableField;
import android.util.Log;

import com.example.wangyulong.campuspass.Model.RequestModel;

import java.util.ArrayList;

/**
 * Created by wangyulong on 23/3/18.
 */

public class RequestEntryCollectionHelper extends BasicCollectionHelper
{
    //region Fields and Const
    private static RequestEntryCollectionHelper _instance = null;
    private ArrayList<RequestModel> _request_model_list = new ArrayList<>();
    private ObservableField<ArrayList<RequestModel>> _request_entry_list = new ObservableField<>(_request_model_list);
    private ArrayList<RequestModel> _my_request_list = new ArrayList<>();
    private int _selection_index = 0;
    private int _overall_selection_index = 0;
    //endregion Fields and Const

    //region Properties
    public static RequestEntryCollectionHelper requestEntryCollectionHelper()
    {
        if (_instance == null)
        {
            _instance = new RequestEntryCollectionHelper();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private RequestEntryCollectionHelper()
    {
    }
    //endregion Constructor

    //region CRUD
    /*restore empty list*/
    public void reset_all_entries()
    {
        this._request_model_list = new ArrayList<RequestModel>();
        this._request_entry_list = new ObservableField<>(new ArrayList<RequestModel>());
    }

    public RequestModel get_item_at(int i)
    {
        return this._request_entry_list.get().get(i);
    }

    public void add_new_item_to_collection(RequestModel new_request)
    {
        _request_model_list.add(new_request);
        this._request_entry_list.set(_request_model_list); //TODO: Protect with Mutex
    }

    public void replace_changed_item_to_collection(RequestModel changed_request, String request_id)
    {
        for (int cur = 0; cur < this._request_model_list.size(); cur++)
        {
            if (this._request_model_list.get(cur).getRequest_entry_id().equals(request_id))
            {
                //replace
                this._request_model_list.set(cur, changed_request);
                Log.d("duplicate found", "");
                break;
            }
        }
    }

    public boolean check_existance(String request_id)
    {
        for (RequestModel request_model : this._request_model_list)
        {
            if (request_model.getRequest_entry_id().equals(request_id))
            {
                Log.d("duplicated found", "->" + request_id);
                return true;
            }
        }
        return false;
    }

    public ArrayList<RequestModel> get_full_list()
    {
        return this._request_entry_list.get();
    }

    public void remove_duplicate()
    {
        this._request_model_list = this._request_entry_list.get(); //TODO: Continue if needed
    }

    public ArrayList<RequestModel> get_myrequest_full_list()
    {
        return this._my_request_list;
    }

    public void add_to_myrequest_collection(RequestModel new_myrequest)
    {
        this._my_request_list.add(new_myrequest);
    }

    public void reset_myrequest_list()
    {
        this._my_request_list = new ArrayList<RequestModel>();
    }

    public void initialize_myrequests(String cur_uid)
    {
        reset_myrequest_list();

        for (RequestModel request : this._request_entry_list.get())
        {
            if (request.request_owner_id.equals(cur_uid))
            {
                this._my_request_list.add(request);
            }
        }
    }

    public RequestModel retrieve_entry_at_position(int index)
    {
        return this._request_entry_list.get().get(index);
    }

    public RequestModel retrieve_my_entry_at_position(int index)
    {
        this._selection_index = index;

        for (int cur = 0; cur < this._request_entry_list.get().size(); cur++)
        {
            if (this._request_entry_list.get().get(cur).getRequest_entry_id().equals(this._my_request_list.get(index).getRequest_entry_id()))
            {
                this._overall_selection_index = cur;
            }
        }

        return this._my_request_list.get(index);
    }

    public void update_my_request_entry(RequestModel request)
    {
        this._my_request_list.set(this._selection_index, request);
    }

    public void update_request_entry(RequestModel request)
    {
        this._my_request_list.set(this._selection_index, request);

        ArrayList<RequestModel> entries = this._request_entry_list.get();
        entries.set(this._overall_selection_index, request);
        this._request_model_list = entries;
        this._request_entry_list.set(entries);
    }
    //endregion CRUD
}
