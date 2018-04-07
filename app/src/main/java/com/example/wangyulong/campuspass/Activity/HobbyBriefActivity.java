package com.example.wangyulong.campuspass.Activity;

import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.wangyulong.campuspass.Adapter.DetailHobbyThumbnailAdapter;
import com.example.wangyulong.campuspass.Adapter.ThumbnailAdapter;
import com.example.wangyulong.campuspass.Events.HobbyBriefListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.ViewResumeListEventListener;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.HobbyBriefViewModel;
import com.example.wangyulong.campuspass.databinding.HobbyBriefActivityBinding;

import java.util.ArrayList;

public class HobbyBriefActivity extends AppCompatActivity
{

    //region Fields and Const
    private HobbyBriefActivityBinding binding;
    private RecyclerView recyclerView;
    private DetailHobbyThumbnailAdapter detailHobbyThumbnailAdapter;
    private HobbyBriefViewModel hobbyBriefViewModel;
    //endregion Fields and Const


    //region Override
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hobby_brief_activity);

        onCreateBinding();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        Log.d("debug ->", "resuming HobbyBriefActivity");
        hobbyBriefViewModel.load_brief_list();
    }
    //endregion Override

    //region Methods
    private void onCreateBinding()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.hobby_brief_activity);

        //init recyclerView
        this.hobbyBriefViewModel = HobbyBriefViewModel.hobbyBriefViewModel();
        this.recyclerView = binding.briefHobbyRecyclerView;
        this.detailHobbyThumbnailAdapter = new DetailHobbyThumbnailAdapter(this, this.hobbyBriefViewModel.get_full_detailed_hobby_list());

        //recyclerView presentation logic
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new HobbyBriefActivity.GridSpacingItemDecoration(2, this.dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(this.detailHobbyThumbnailAdapter);

        this.detailHobbyThumbnailAdapter.setViewResumeListEventListener(new ViewResumeListEventListener()
        {
            @Override
            public void onViewResumeListEventTrigger()
            {
                Intent toDetailHobbyActivity = new Intent(getApplicationContext(), DetailHobbyActivity.class);
                HobbyBriefActivity.this.startActivity(toDetailHobbyActivity);
            }
        });

        //prepare list
        hobbyBriefViewModel.load_brief_list();

        //TODO: Attach refresh event
        this.hobbyBriefViewModel.setHobbyBriefRefreshEvent(new HobbyBriefListRefreshEventListener()
        {
            @Override
            public void onHobbyBriefListRefreshEventTrigger()
            {
                detailHobbyThumbnailAdapter.notifyDataSetChanged();
            }
        });
    }

    private int dpToPx(int dp)
    {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    //endregion Methods

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
}