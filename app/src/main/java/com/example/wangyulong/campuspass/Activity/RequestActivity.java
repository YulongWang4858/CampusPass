package com.example.wangyulong.campuspass.Activity;

import android.app.ListActivity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.wangyulong.campuspass.Adapter.RequestListViewAdapter;
import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.Events.RequestListRefreshEventListener;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.RequestViewModel;
import com.example.wangyulong.campuspass.databinding.RequestPageBinding;

public class RequestActivity extends ListActivity implements RequestListRefreshEventListener
{
    //region Fields and Const
    RequestPageBinding binding;
    RequestViewModel requestViewModel;
    RequestListViewAdapter request_list_adapter;
    //endregion Fields and Const

    //region Override
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_page);

        //init fields
        requestViewModel = RequestViewModel.requestViewModel();
        requestViewModel.allow_database_read();

        //init UI
        onCreateBinding();
        initializeRequestList();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

    //endregion Override

    //region Methods
    protected void onCreateBinding()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.request_page);
        binding.setRequestVM(this.requestViewModel);
        bindingButtons();
    }

    protected void bindingButtons()
    {
        binding.setNewRequestButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                //TODO: Link to NewReqActivity
                Intent toNewRequestPage = new Intent(RequestActivity.this, NewRequestActivity.class);
                RequestActivity.this.startActivity(toNewRequestPage);
            }
        });

        binding.setMyRequestButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                //TODO: Link to MyReqActivity
                Intent toMyRequestPage = new Intent(RequestActivity.this, MyRequestActivity.class);
                RequestActivity.this.startActivity(toMyRequestPage);
            }
        });
    }

    protected void initializeRequestList()
    {
        //connect list view to adapter
        ListView request_list = getListView();
        request_list_adapter = new RequestListViewAdapter(getApplicationContext(), R.layout.request_brief_page,
                requestViewModel.get_request_list_entries());
        request_list.setAdapter(request_list_adapter);

        //refresh event
        this.requestViewModel.read_requests();
        this.requestViewModel.setRequestPageRefreshEvents(new RequestListRefreshEventListener()
        {
            @Override
            public void onRequestListRefreshEventTrigger()
            {
                //refresh list view
                request_list_adapter.notifyDataSetChanged();
            }
        });
    }
    //endregion Methods

    //region Implementation
    public void onRequestListRefreshEventTrigger()
    {
        //TODO: Remove later
    }
    //endregion Implementation
}
