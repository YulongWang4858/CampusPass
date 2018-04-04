package com.example.wangyulong.campuspass.Helper;

import com.example.wangyulong.campuspass.Model.HobbyModel;
import com.example.wangyulong.campuspass.R;

import java.util.ArrayList;

/**
 * Created by wangyulong on 4/4/18.
 */

public class HobbyCollectionHelper extends BasicCollectionHelper
{
    //region Fields and Const
    private static HobbyCollectionHelper _instance = null;
    private ArrayList<HobbyModel> _hobbyL_lst = new ArrayList<>();
    //endregion Fields and Const

    //region Properties
    public static HobbyCollectionHelper hobbyCollectionHelper()
    {
        if (_instance == null)
        {
            _instance = new HobbyCollectionHelper();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private HobbyCollectionHelper()
    {
        //init
    }
    //endregion Constructor

    //region Methods
    public void initialize_collection()
    {
        //int[] participants =

        //this._hobbyL_lst.add(new HobbyModel("Photography", R.drawable.photography_icon_pic, ));
    }

    public void reset_collection()
    {
        this._hobbyL_lst = new ArrayList<>();
    }

    public boolean check_existance(HobbyModel hobby_model)
    {
        for (HobbyModel hobby : this._hobbyL_lst)
        {
            if (hobby.getHobby_name().equals(hobby_model.getHobby_name()))
            {
                return true;
            }
        }

        return false;
    }

    public void add_item_to_collection(HobbyModel hobby_model)
    {
        this._hobbyL_lst.add(hobby_model);
    }

    public HobbyModel get_hobby_at_position(int index)
    {
        return this._hobbyL_lst.get(index);
    }

    public ArrayList<HobbyModel> get_full_hobby_list()
    {
        return this._hobbyL_lst;
    }
    //endregion Methods
}
