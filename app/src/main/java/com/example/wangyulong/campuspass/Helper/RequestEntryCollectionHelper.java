package com.example.wangyulong.campuspass.Helper;


import android.database.Observable;
import android.databinding.ObservableField;

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
    //endregion CRUD
}
