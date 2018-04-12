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
import android.util.TypedValue;
import android.view.View;

import com.example.wangyulong.campuspass.Adapter.CareerThumbnailAdapter;
import com.example.wangyulong.campuspass.Events.CareerListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.ShowCareerResumeListEventListener;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.EasyExcelViewModel;
import com.example.wangyulong.campuspass.databinding.EasyExcelMainpageBinding;

public class EasyExcelActivity extends AppCompatActivity
{
    //region Fields and Const
    private EasyExcelMainpageBinding binding;
    private EasyExcelViewModel easyExcelViewModel;
    private RecyclerView recyclerView;
    private CareerThumbnailAdapter adapter;
    //endregion Fields and Const

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.easy_excel_mainpage);

        onCreateBinding();
    }

    //region Methods
    private void onCreateBinding()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.easy_excel_mainpage);

        easyExcelViewModel = EasyExcelViewModel.easyExcelViewModel();
        recyclerView = binding.easyExcelHobbyMenu;
        easyExcelViewModel.load_from_database();
        adapter = new CareerThumbnailAdapter(this, this.easyExcelViewModel.get_full_list());

        //specify recyclerView presentation logic
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new EasyExcelActivity.GridSpacingItemDecoration(2, this.dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.setShowCareerResumeListEventListener(new ShowCareerResumeListEventListener()
        {
            @Override
            public void onShowCareerResumeListEventTrigger()
            {
                Intent toChoicePage = new Intent(getApplicationContext(), CareerScreenChoiceActivity.class);
                EasyExcelActivity.this.startActivity(toChoicePage);
            }
        });

        this.easyExcelViewModel.setCareerListRefreshEventListener(new CareerListRefreshEventListener()
        {
            @Override
            public void onCareerListRefreshEventTrigger()
            {
                //refresh
                adapter.notifyDataSetChanged();
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
