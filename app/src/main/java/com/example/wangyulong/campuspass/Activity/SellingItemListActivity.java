package com.example.wangyulong.campuspass.Activity;

import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.example.wangyulong.campuspass.Adapter.ItemThumbnailAdpter;
import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.Events.MyItemListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.ShowItemEditEventListener;
import com.example.wangyulong.campuspass.Helper.MyItemsCollectionHelper;
import com.example.wangyulong.campuspass.Model.ItemModel;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.SellingViewModel;
import com.example.wangyulong.campuspass.databinding.SellingItemListPageBinding;

public class SellingItemListActivity extends AppCompatActivity
{
    //region Fields and Const
    private SellingItemListPageBinding binding;
    private RecyclerView recyclerView;
    private ItemThumbnailAdpter adapter;
    private MyItemsCollectionHelper myItemsCollectionHelper;
    private SellingViewModel sellingViewModel;
    //endregion Fields and Const

    //region Properties

    //endregion Properties

    //region Override
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selling_item_list_page);

        onCreateBinding();
    }
    //endregion Override

    //region Methods
    private void onCreateBinding()
    {
        this.binding = DataBindingUtil.setContentView(this, R.layout.selling_item_list_page);
        this.sellingViewModel = SellingViewModel.sellingViewModel();
        this.myItemsCollectionHelper = MyItemsCollectionHelper.myItemsCollectionHelper();

        bindButtons();
        initRecyclerView();
    }

    private void bindButtons()
    {
        binding.setSellingItemButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                //generate new UUID
                sellingViewModel.disableEdit();
                sellingViewModel.toggle_delete_visibility();

                Intent toSellingItemPage = new Intent(getApplicationContext(), SellingActivity.class);
                SellingItemListActivity.this.startActivity(toSellingItemPage);
            }
        });
    }

    private void initRecyclerView()
    {
        //init adapter
        this.recyclerView = binding.sellingRecyclerView;
        this.adapter = new ItemThumbnailAdpter(this, this.sellingViewModel.get_my_item_full_list());

        //set thumbnail onclick event
        this.adapter.setShowItemEditEventListener(new ShowItemEditEventListener()
        {
            @Override
            public void onShowItemEditEventTrigger()
            {
                //enable edit
                sellingViewModel.enableEdit();

                Intent toEditSellingItemPage = new Intent(getApplicationContext(), SellingActivity.class);
                SellingItemListActivity.this.startActivity(toEditSellingItemPage);
            }
        });

        //set list refresh listener
        this.sellingViewModel.setRefreshListener(new MyItemListRefreshEventListener()
        {
            @Override
            public void onMyItemListRefreshEventListener()
            {
                adapter.notifyDataSetChanged();
            }
        });

        //enable edit
        this.adapter.enableEdit();
        this.sellingViewModel.load_my_item_list_from_database();

        //configure recyclerview
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SellingItemListActivity.GridSpacingItemDecoration(2, this.dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(this.adapter);
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
