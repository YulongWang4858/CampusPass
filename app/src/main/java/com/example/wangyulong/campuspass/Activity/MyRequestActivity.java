package com.example.wangyulong.campuspass.Activity;

import android.app.ListActivity;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.wangyulong.campuspass.Adapter.RequestListViewAdapter;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.RequestViewModel;
import com.example.wangyulong.campuspass.databinding.MyRequestPageBinding;

public class MyRequestActivity extends ListActivity
{
    //region Fields and Const
    MyRequestPageBinding binding;
    RequestViewModel requestViewModel;
    //endregion Fields and Const

    //region Override
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_request_page);

        onCreateBinding();
        initListView();
    }
    //endregion Override

    //region Methods
    protected void onCreateBinding()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.my_request_page);
    }

    protected void initListView()
    {
        requestViewModel = RequestViewModel.requestViewModel();
        requestViewModel.read_myrequests();
        ListView myrequest_list = getListView();
        RequestListViewAdapter myrequestAdapter = new RequestListViewAdapter(this, R.layout.request_brief_page, requestViewModel.get_myrequest_list_entry());
        myrequest_list.setAdapter(myrequestAdapter);
    }
    //endregion Methods
}
