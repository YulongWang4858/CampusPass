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

import com.example.wangyulong.campuspass.Adapter.CareerResumeThumbnailAdapter;
import com.example.wangyulong.campuspass.Adapter.DetailHobbyThumbnailAdapter;
import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.Events.CareerResumeListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.ShowCareerResumeDetailEventListener;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.CareerListViewModel;
import com.example.wangyulong.campuspass.ViewModel.HobbyBriefViewModel;
import com.example.wangyulong.campuspass.databinding.CareerResumeListBinding;
import com.google.firebase.auth.FirebaseAuth;

public class CareerResumeListActivity extends AppCompatActivity
{

    //region Fields and Const
    private CareerResumeListBinding binding;
    private CareerListViewModel careerListViewModel;
    private RecyclerView recyclerView;
    private CareerResumeThumbnailAdapter adapter;
    //endregion Fields and Const

    //region Override
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.career_resume_list);

        onCreateBinding();
    }
    //endregion Override

    //region Methods
    private void onCreateBinding()
    {
        this.binding = DataBindingUtil.setContentView(this, R.layout.career_resume_list);

        careerListViewModel = CareerListViewModel.careerListViewModel();
        this.binding.setCareerListVM(careerListViewModel);

        this.bindButtons();

        //configure recycler view
        this.recyclerView = binding.careerResumeRecyclerView;
        adapter = new CareerResumeThumbnailAdapter(this, this.careerListViewModel.get_full_list());
        this.careerListViewModel.load_resume_list();

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new CareerResumeListActivity.GridSpacingItemDecoration(2, this.dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(this.adapter);

        this.careerListViewModel.setCareerResumeRefreshEvent(new CareerResumeListRefreshEventListener()
        {
            @Override
            public void onCareerResumeListRefreshEventTrigger()
            {
                adapter.notifyDataSetChanged();
            }
        });

        //set thumbnail clicked event
        adapter.setShowCareerResumeDetailEventListener(new ShowCareerResumeDetailEventListener()
        {
            @Override
            public void onShowCareerResumeDetailEventTrigger()
            {
                Intent toCareerResumeDetailPage = new Intent(getApplicationContext(), DetailCareerResumeActivity.class);
                CareerResumeListActivity.this.startActivity(toCareerResumeDetailPage);
            }
        });
    }

    private void bindButtons()
    {
        this.binding.setCareerParticipateBtnClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {

                //check if user owns an existing resume
                if (!careerListViewModel.user_already_submitted(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()))
                {
                    Intent toNewCareerResumePage = new Intent(getApplicationContext(), NewCareerResumeActivity.class);
                    CareerResumeListActivity.this.startActivity(toNewCareerResumePage);
                } else
                {
                    showSnackBar("you can only upload one career resume");
                }
            }
        });

        this.binding.setCareerResumeManageBtnClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                if (careerListViewModel.user_already_submitted(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()))
                {
                    //debug
                    Log.d("debug -> ", "resume found");

                    //load resume owned by current user
                    careerListViewModel.set_current_resume_to_manage(FirebaseAuth.getInstance().getCurrentUser().getUid().toString());

                    Intent toManageCareerResumePage = new Intent(getApplicationContext(), NewCareerResumeActivity.class);
                    CareerResumeListActivity.this.startActivity(toManageCareerResumePage);
                } else
                {
                    showSnackBar("you do not submitted a resume yet");
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
