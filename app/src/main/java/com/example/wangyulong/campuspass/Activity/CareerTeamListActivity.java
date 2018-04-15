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
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.wangyulong.campuspass.Adapter.CareerTeamThumbnailAdapter;
import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.Events.CareerTeamListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.ShowCareerTeamDetailsEventListener;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.CareerTeamListViewModel;
import com.example.wangyulong.campuspass.ViewModel.NewTeamViewModel;
import com.example.wangyulong.campuspass.databinding.CareerTeamListPageBinding;

public class CareerTeamListActivity extends AppCompatActivity
{

    //region Fields and Const
    private CareerTeamListPageBinding binding;
    private RecyclerView recyclerView;
    private CareerTeamThumbnailAdapter adapter;
    private CareerTeamListViewModel careerTeamListViewModel;
    //endregion Fields and Const

    //region Override
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.career_team_list_page);

        onCreateBinding();
    }
    //endregion Override

    //region Methods
    private void onCreateBinding()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.career_team_list_page);

        //init recyclerview
        this.careerTeamListViewModel = CareerTeamListViewModel.careerTeamListViewModel();
        this.careerTeamListViewModel.load_from_database();
        this.recyclerView = binding.careerTeamListRecyclerview;
        this.adapter = new CareerTeamThumbnailAdapter(this, careerTeamListViewModel.get_full_list());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new CareerTeamListActivity.GridSpacingItemDecoration(2, this.dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(this.adapter);

        //refresh event
        this.careerTeamListViewModel.setRefreshListener(new CareerTeamListRefreshEventListener()
        {
            @Override
            public void onCareerTeamListRefreshEventTrigger()
            {
                adapter.notifyDataSetChanged();
            }
        });

        adapter.setShowCareerTeamDetailsEventListener(new ShowCareerTeamDetailsEventListener()
        {
            @Override
            public void onShowCareerTeamDetailsEventTriggered()
            {
                //TODO: Open new activity
                Intent toCareerTeamDetailPage = new Intent(getApplicationContext(), CareerTeamDetailActivity.class);
                CareerTeamListActivity.this.startActivity(toCareerTeamDetailPage);
            }
        });

        bindButtons();
    }

    private void bindButtons()
    {
        this.binding.setFormNewTeamButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                if (!careerTeamListViewModel.check_if_user_owns_team())
                {
                    Intent toNewTeamFormationPage = new Intent(getApplicationContext(), NewCareerTeamActivity.class);
                    CareerTeamListActivity.this.startActivity(toNewTeamFormationPage);
                } else
                {
                    showSnackBar("You can only form one team");
                }
            }
        });

        this.binding.setManageExistingTeamInfoClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                if (careerTeamListViewModel.check_if_user_owns_team())
                {
                    NewTeamViewModel.newTeamViewModel().load_my_team_into_VM();

                    careerTeamListViewModel.set_refresh_listener(new CareerTeamListRefreshEventListener()
                    {
                        @Override
                        public void onCareerTeamListRefreshEventTrigger()
                        {
                            adapter.notifyDataSetChanged();
                        }
                    });

                    Intent toMyTeamPage = new Intent(getApplicationContext(), MyTeamActivity.class);
                    CareerTeamListActivity.this.startActivity(toMyTeamPage);
                } else
                {
                    //user does not own any
                    showSnackBar("You have not formed a team yet");
                }
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

    protected void showSnackBar(String txt)
    {
        Snackbar.make(findViewById(android.R.id.content), txt, Snackbar.LENGTH_LONG).show();
    }
    //endregion Methods
}
