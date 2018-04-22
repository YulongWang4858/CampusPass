package com.example.wangyulong.campuspass.Activity;

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

import com.example.wangyulong.campuspass.Adapter.ChatRoomWindowAdapter;
import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.Events.ChatMessageLoadedEventListener;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.ChatBoxViewModel;
import com.example.wangyulong.campuspass.databinding.ChatBoxPageBinding;
import com.example.wangyulong.campuspass.databinding.ChatRoomBinding;

import java.util.concurrent.RecursiveAction;

public class ChatRoomActivity extends AppCompatActivity
{
    //region Fields and Const
    private ChatRoomBinding binding;
    private ChatBoxViewModel chatBoxViewModel;
    private ChatRoomWindowAdapter adapter;
    private RecyclerView recyclerView;
    //endregion Fields and Const

    //region Override
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_room);
        this.chatBoxViewModel = ChatBoxViewModel.chatBoxViewModel();

        onCreateBinding();
    }
    //endregion Override

    //region Methods
    private void onCreateBinding()
    {
        this.binding = DataBindingUtil.setContentView(this, R.layout.chat_room);
        this.binding.setChatRoomVM(this.chatBoxViewModel);

        this.bindButtons();

        //init recyclerview
        this.recyclerView = this.binding.messageCardview;
        this.adapter = new ChatRoomWindowAdapter(this, this.chatBoxViewModel.get_full_list());

        this.chatBoxViewModel.setChatMessageLoadedEventListener(new ChatMessageLoadedEventListener()
        {
            @Override
            public void onChatMessageLoadedEventTrigger()
            {
                adapter.notifyDataSetChanged();
            }
        });

        this.chatBoxViewModel.load_chat_messages();

        //set recyclerview config
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new ChatRoomActivity.GridSpacingItemDecoration(2, this.dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(this.adapter);
    }

    private void bindButtons()
    {
        this.binding.setChatRoomSendButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                chatBoxViewModel.SendMessage();
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

    protected void showSnackBar(String txt)
    {
        Snackbar.make(findViewById(android.R.id.content), txt, Snackbar.LENGTH_LONG).show();
    }
    //endregion Methods
}
