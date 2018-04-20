package com.example.wangyulong.campuspass.Helper;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by wangyulong on 18/4/18.
 */

public interface CollectionHelper
{
    //region Abstraction
    public Object get_full_list();

    public Object get_item_at_position(int i);

    public void reset_collection();

    public void add_item_to_collection(Object obj);

    public void remove_item_from_collection(String id);

    public boolean check_exsitance(Object obj);

    public boolean check_participation(Object obj);

    public void replace_item(Object obj);
    //endregion Abstraction
}
