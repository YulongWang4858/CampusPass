package com.example.wangyulong.campuspass.Model;

import android.database.Observable;
import android.databinding.ObservableField;

/**
 * Created by wangyulong on 22/3/18.
 */

public class RequestModel
{
    //region Fields and Const
    public String request_name = new String("");
    public String request_owner = new String("");
    public String request_descrip = new String("");
    public String request_price = new String("");
    public String request_category = new String("");
    public String request_owner_id = new String("");
    public String request_entry_id = new String("");
//    public ObservableField<String> o_request_name = new ObservableField<>(request_name);
//    public ObservableField<String> o_request_owner = new ObservableField<>(request_owner);
//    public ObservableField<String> o_request_descrip = new ObservableField<>(request_descrip);
//    public ObservableField<String> o_request_price = new ObservableField<>(request_price);
//    public ObservableField<String> o_request_category = new ObservableField<>(request_category);
//    public ObservableField<String> o_request_owner_id = new ObservableField<>(request_owner_id);
//    public ObservableField<String> o_request_entry_id = new ObservableField<>(request_entry_id);
    //endregion Fields and Const

    //region Properties
    public String getRequest_name()
    {
        return request_name;
    }

    public void setRequest_name(String request_name)
    {
        this.request_name = request_name;
        //this.o_request_name.set(request_name);
    }

    public String getRequest_owner()
    {
        return request_owner;
    }

    public void setRequest_owner(String request_owner)
    {
        this.request_owner = request_owner;
        //this.o_request_owner.set(request_owner);
    }

    public String getRequest_descrip()
    {
        return request_descrip;
    }

    public void setRequest_descrip(String request_descrip)
    {
        this.request_descrip = request_descrip;
        //this.o_request_descrip.set(request_descrip);
    }

    public String getRequest_price()
    {
        return request_price;
    }

    public void setRequest_price(String request_price)
    {
        this.request_price = request_price;
        //this.o_request_price.set(request_price);
    }

    public String getRequest_category()
    {
        return request_category;
    }

    public void setRequest_category(String request_category)
    {
        this.request_category = request_category;
        //this.o_request_category.set(request_category);
    }

    public String getRequest_owner_id()
    {
        return request_owner_id;
    }

    public void setRequest_owner_id(String request_owner_id)
    {
        this.request_owner_id = request_owner_id;
        //this.o_request_owner_id.set(request_owner_id);
    }

    public String getRequest_entry_id()
    {
        return request_entry_id;
    }

    public void setRequest_entry_id(String request_entry_id)
    {
        this.request_entry_id = request_entry_id;
        //this.o_request_entry_id.set(request_entry_id);
    }
    //endregion Properties

    //region Constructor
    public RequestModel()
    {

    }

    public RequestModel(String request_name, String request_owner, String request_descrip, String request_price, String request_category)
    {
//        this.request_category = request_category;
//        this.request_owner = request_owner;
//        this.request_name = request_name;
//        this.request_price = request_price;
//        this.request_descrip = request_descrip;
    }
    //endregion Constructor
}
