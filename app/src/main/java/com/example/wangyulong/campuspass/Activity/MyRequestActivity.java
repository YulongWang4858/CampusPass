package com.example.wangyulong.campuspass.Activity;

import android.app.IntentService;
import android.app.ListActivity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.generated.callback.OnClickListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.wangyulong.campuspass.Adapter.RequestListViewAdapter;
import com.example.wangyulong.campuspass.Events.MyRequestListRefreshEventListener;
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
        final RequestListViewAdapter myrequestAdapter = new RequestListViewAdapter(this, R.layout.request_brief_page, requestViewModel.get_myrequest_list_entry());
        myrequest_list.setAdapter(myrequestAdapter);

        myrequest_list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                requestViewModel.set_new_item_selected(i);

                Intent toRequestDetail = new Intent(getApplicationContext(), RequestDetailActivity.class);
                MyRequestActivity.this.startActivity(toRequestDetail);
            }
        });

        requestViewModel = RequestViewModel.requestViewModel();
        requestViewModel.setMyRequestPageRefreshEvents(new MyRequestListRefreshEventListener()
        {
            @Override
            public void onMyRequestListRefreshEventTrigger()
            {
                //refresh
                myrequestAdapter.notifyDataSetChanged();
            }
        });
    }
    //endregion Methods
}
