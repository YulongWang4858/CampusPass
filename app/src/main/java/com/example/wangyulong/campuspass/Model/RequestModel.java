package com.example.wangyulong.campuspass.Model;

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
    //endregion Fields and Const

    //region Properties
    public String getRequest_name()
    {
        return request_name;
    }

    public void setRequest_name(String request_name)
    {
        this.request_name = request_name;
    }

    public String getRequest_owner()
    {
        return request_owner;
    }

    public void setRequest_owner(String request_owner)
    {
        this.request_owner = request_owner;
    }

    public String getRequest_descrip()
    {
        return request_descrip;
    }

    public void setRequest_descrip(String request_descrip)
    {
        this.request_descrip = request_descrip;
    }

    public String getRequest_price()
    {
        return request_price;
    }

    public void setRequest_price(String request_price)
    {
        this.request_price = request_price;
    }

    public String getRequest_category()
    {
        return request_category;
    }

    public void setRequest_category(String request_category)
    {
        this.request_category = request_category;
    }

    //endregion Properties

    //region Constructor
    public RequestModel()
    {

    }

    public RequestModel(String request_name, String request_owner, String request_descrip, String request_price, String request_category)
    {
        this.request_category = request_category;
        this.request_owner = request_owner;
        this.request_name = request_name;
        this.request_price = request_price;
        this.request_descrip = request_descrip;
    }
    //endregion Constructor
}
