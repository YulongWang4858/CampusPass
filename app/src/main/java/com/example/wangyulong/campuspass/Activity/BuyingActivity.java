package com.example.wangyulong.campuspass.Activity;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.generated.callback.OnClickListener;
import android.graphics.Rect;
import android.os.Bundle;
import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import com.example.wangyulong.campuspass.Adapter.BuyingListViewAdapter;
import com.example.wangyulong.campuspass.Adapter.ItemThumbnailAdpter;
import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.Events.BuyingListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.ShowItemDetailEventListener;
import com.example.wangyulong.campuspass.Model.ItemModel;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.BuyingListViewModel;
import com.example.wangyulong.campuspass.ViewModel.SellingViewModel;
import com.example.wangyulong.campuspass.databinding.BuyingPageBinding;
import com.example.wangyulong.campuspass.databinding.BuyingPageItemsBriefBinding;
import com.example.wangyulong.campuspass.databinding.BuyingPageItemsDetailBinding;

import java.util.ArrayList;

/**
 * Created by wangyulong on 23/02/18.
 */


public class BuyingActivity extends AppCompatActivity
{

    //region Fields and Const
    private BuyingPageBinding binding;
    private BuyingListViewModel buyingListViewModel;
    private ItemThumbnailAdpter adapter;
    private RecyclerView recyclerView;
    //endregion Fields and Const


    //Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buying_page);

        //initialize binding
        onCreateBinding();

        //initialize item list
        loadBuyingList();
    }
    //endregion Override

    //region Methods
    protected void onCreateBinding()
    {
        buyingListViewModel = BuyingListViewModel.buyingListViewModel();
        binding = DataBindingUtil.setContentView(this, R.layout.buying_page);

        bindButton();
    }

    protected void bindButton()
    {

    }

    protected void loadBuyingList()
    {
        //Init adapter
        this.recyclerView = this.binding.buyingRecyclerView;

        this.adapter = new ItemThumbnailAdpter(this, (ArrayList<ItemModel>) this.buyingListViewModel.get_full_collection());

        //set iconimg click event
        this.adapter.setShowItemDetailEventListener(new ShowItemDetailEventListener()
        {
            @Override
            public void onShowItemDetailEventTrigger()
            {
                Intent toItemDetailPage = new Intent(getApplicationContext(), BuyingItemDetailedActivity.class);
                BuyingActivity.this.startActivity(toItemDetailPage);
            }
        });

        //set list refresh event
        this.buyingListViewModel.setRefreshListener(new BuyingListRefreshEventListener()
        {
            @Override
            public void onBuyingListRefreshEventTriggered()
            {
                adapter.notifyDataSetChanged();
            }
        });

        //load list
        this.buyingListViewModel.load_from_database();

        //configure recyclerview
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.addItemDecoration(new BuyingActivity.GridSpacingItemDecoration(2, this.dpToPx(10), true));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setAdapter(this.adapter);
    }

    private int dpToPx(int dp)
    {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration
    {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge)
        {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
        {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge)
            {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount)
                { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else
            {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount)
                {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    protected void showSnackBar(String txt)
    {
        Snackbar.make(findViewById(android.R.id.content), txt, Snackbar.LENGTH_LONG).show();
    }

    //endregion Methods
}


