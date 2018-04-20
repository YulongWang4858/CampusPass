package com.example.wangyulong.campuspass.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wangyulong.campuspass.Events.ShowHobbyResumeDetailsEventListener;
import com.example.wangyulong.campuspass.Events.ShowItemDetailEventListener;
import com.example.wangyulong.campuspass.Events.ShowItemEditEventListener;
import com.example.wangyulong.campuspass.Model.ItemModel;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.SellingViewModel;

import java.util.List;

/**
 * Created by wangyulong on 17/4/18.
 */

public class ItemThumbnailAdpter extends RecyclerView.Adapter<ItemThumbnailAdpter.ItemThumbnailViewHolder>
{

    //region Fields and Const
    private Context _context;
    private List<ItemModel> itemModelList;
    private int lastPosition = -1;
    private ShowItemDetailEventListener showItemDetailEventListener = null;
    private ShowItemEditEventListener showItemEditEventListener = null;

    private boolean is_edit_enabled = false;
    //endregion Fields and Const

    //region Extend
    public class ItemThumbnailViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name, descr, price, condition;
        public ImageView icon_img;

        public ItemThumbnailViewHolder(View view)
        {
            super(view);

            //init
            this.name = (TextView) view.findViewById(R.id.listItemTitle);
            this.descr = (TextView) view.findViewById(R.id.listItemShortDescr);
            this.price = (TextView) view.findViewById(R.id.listItemPrice);
            this.condition = (TextView) view.findViewById(R.id.listItemCondition);
            this.icon_img = (ImageView) view.findViewById(R.id.listItemImg);
        }
    }
    //endregion Extend

    //region Constructor
    public ItemThumbnailAdpter(Context context, List<ItemModel> itemModelList)
    {
        this._context = context;
        this.itemModelList = itemModelList;
    }
    //endregion Constructor

    //region Override
    @Override
    public ItemThumbnailAdpter.ItemThumbnailViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.buying_page_items_brief, parent, false);

        return new ItemThumbnailAdpter.ItemThumbnailViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ItemThumbnailAdpter.ItemThumbnailViewHolder holder, int position)
    {
        final ItemModel itemModel = this.itemModelList.get(position);

        //fill
        holder.name.setText(itemModel.getItem_name());
        holder.descr.setText(itemModel.getItem_descr());
        holder.price.setText(itemModel.getItem_price());
        holder.condition.setText(itemModel.getItem_condition());

        Glide.with(_context).load(itemModel.getItem_photo_uri()).into(holder.icon_img);

        setAnimation(holder.itemView, position);

        //attach click listener
        holder.icon_img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //trigger event
                if (is_edit_enabled)
                {
                    //update selection
                    SellingViewModel.sellingViewModel().enableEdit();
                    SellingViewModel.sellingViewModel().set_new_item_selected(itemModel);

                    if (showItemEditEventListener != null)
                    {
                        showItemEditEventListener.onShowItemEditEventTrigger();
                    }

                } else
                {
                    SellingViewModel.sellingViewModel().disableEdit();
                    SellingViewModel.sellingViewModel().set_new_item_selected(itemModel);

                    if (showItemDetailEventListener != null)
                    {
                        showItemDetailEventListener.onShowItemDetailEventTrigger();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return this.itemModelList.size();
    }
    //endregion Override

    //region Methods
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(_context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    public void setShowItemDetailEventListener(ShowItemDetailEventListener listener)
    {
        this.showItemDetailEventListener = listener;
    }

    public void setShowItemEditEventListener(ShowItemEditEventListener listener)
    {
        this.showItemEditEventListener = listener;
    }

    public void enableEdit()
    {
        this.is_edit_enabled = true;
    }

    public void disableEdit()
    {
        this.is_edit_enabled = false;
    }

    //endregion Methods
}
