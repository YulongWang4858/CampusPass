package com.example.wangyulong.campuspass.ViewModel;

/**
 * Created by wangyulong on 19/4/18.
 */

public interface BasicListViewModel
{
    public void setRefreshListener(Object obj);
    public void load_from_database();
    public Object get_full_collection();
}
