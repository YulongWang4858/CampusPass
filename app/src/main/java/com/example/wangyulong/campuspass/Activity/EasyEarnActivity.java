package com.example.wangyulong.campuspass.Activity;

import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.wangyulong.campuspass.Adapter.ThumbnailAdapter;
import com.example.wangyulong.campuspass.Events.HobbyCardViewRefreshListener;
import com.example.wangyulong.campuspass.Events.ParticipateHobbyEventListener;
import com.example.wangyulong.campuspass.Events.ViewHobbyEventListener;
import com.example.wangyulong.campuspass.Model.HobbyModel;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.EasyEarnViewModel;
import com.example.wangyulong.campuspass.databinding.EasyEarnMianpageBinding;

import java.util.ArrayList;
import java.util.List;

public class EasyEarnActivity extends AppCompatActivity
{

    //region Fields and Const
    private EasyEarnMianpageBinding binding;
    private RecyclerView recyclerView;
    private ThumbnailAdapter thumbnailAdapter;
    public EasyEarnViewModel easyEarnViewModel;

    //TODO: Move to VM after testing
    private List<HobbyModel> hobbyModelList;
    //endregion Fields and Const

    //region Override
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.easy_earn_mianpage);

        easyEarnViewModel = EasyEarnViewModel.easyEarnViewModel();

        onCreateBinding();
    }
    //endregion Override

    //region Methods
    private void onCreateBinding()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.easy_earn_mianpage);

        recyclerView = binding.easyEarnHobbyMenu;

        //TODO: Modify after testing
        hobbyModelList = new ArrayList<>();
        thumbnailAdapter = new ThumbnailAdapter(this, this.easyEarnViewModel.get_complete_hobby_list());

        thumbnailAdapter.setViewHobbyEventListener(new ViewHobbyEventListener()
        {
            @Override
            public void onViewHobbyEventTrigger()
            {
                Intent toHobbyBrefListPage = new Intent(getApplicationContext(), HobbyBriefActivity.class);
                EasyEarnActivity.this.startActivity(toHobbyBrefListPage);
            }
        });
        thumbnailAdapter.setParticipateHobbyEventListener(new ParticipateHobbyEventListener()
        {
            @Override
            public void onParticipateMenuItemClicked()
            {
                Intent toParticipateHobbyPage = new Intent(getApplicationContext(), ParticipateHobbyActivity.class);
                EasyEarnActivity.this.startActivity(toParticipateHobbyPage);
            }
        });

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, this.dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(thumbnailAdapter);

        easyEarnViewModel.setHobbyCardViewEvent(new HobbyCardViewRefreshListener()
        {
            @Override
            public void onHobbyCardViewRefreshTriggered()
            {
                thumbnailAdapter.notifyDataSetChanged();
            }
        });
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
    //endregion Methods
}
