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

import com.example.wangyulong.campuspass.Adapter.HobbyResumeListViewAdapter;
import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.Events.HobbyBriefListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.HobbyResumeListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.ShowHobbyResumeDetailsEventListener;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.HobbyBriefViewModel;
import com.example.wangyulong.campuspass.ViewModel.HobbyResumeViewModel;
import com.example.wangyulong.campuspass.databinding.DetailHobbyContentPageBinding;
import com.example.wangyulong.campuspass.databinding.HobbyResumeBriefPageBinding;

public class DetailHobbyActivity extends AppCompatActivity
{

    //region Fields and Const
    private HobbyResumeBriefPageBinding binding;
    private HobbyResumeViewModel hobbyResumeViewModel;
    private HobbyResumeListViewAdapter adapter;
    private RecyclerView recyclerView;
    //endregion Fields and Const

    //region Override
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hobby_resume_brief_page);

        onCreateBinding();
    }

    //endregion Override

    //region Methods
    private void onCreateBinding()
    {
        //set binding
        this.hobbyResumeViewModel = HobbyResumeViewModel.hobbyResumeViewModel();
        this.binding = DataBindingUtil.setContentView(this, R.layout.hobby_resume_brief_page);
        this.binding.setHobbyResumeVM(this.hobbyResumeViewModel);

        //initialize RecyclerView
        this.recyclerView = binding.hobbyResumeRecyclerView;
        adapter = new HobbyResumeListViewAdapter(this, this.hobbyResumeViewModel.get_full_list());
        adapter.setShowHobbyResumeDetailsEventListener(new ShowHobbyResumeDetailsEventListener()
        {
            @Override
            public void onShowHobbyResumeDetailsEventTrigger()
            {
                Intent toDetailResumePage = new Intent(getApplicationContext(), DetailHobbyResumeActivity.class);
                DetailHobbyActivity.this.startActivity(toDetailResumePage);
            }
        });

        //recyclerView presentation logic
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DetailHobbyActivity.GridSpacingItemDecoration(2, this.dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(this.adapter);

        //load
        this.hobbyResumeViewModel.load_hobby_resumes();
        this.hobbyResumeViewModel.setHobbyResumeListRefreshListener(new HobbyResumeListRefreshEventListener()
        {
            @Override
            public void onHobbyResumeListRefreshEventTrigger()
            {
                adapter.notifyDataSetChanged();
            }
        });

        bindingButtons();
    }

    private void bindingButtons()
    {
        this.binding.setHobbyParticipateButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                Intent toParticipatePage = new Intent(getApplicationContext(), ParticipateHobbyActivity.class);
                DetailHobbyActivity.this.startActivity(toParticipatePage);
            }
        });

        this.binding.setViewMyHobbyButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                if (hobbyResumeViewModel.participation())
                {
                    //to new intent
                    Intent toParticipatePage = new Intent(getApplicationContext(), ParticipateHobbyActivity.class);
                    DetailHobbyActivity.this.startActivity(toParticipatePage);
                } else
                {
                    showSnackBar("You have not participated yet!");
                }
            }
        });
    }

    protected void showSnackBar(String txt)
    {
        Snackbar.make(findViewById(android.R.id.content), txt, Snackbar.LENGTH_LONG).show();
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
