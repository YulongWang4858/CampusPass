package com.example.wangyulong.campuspass.Activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.Constant.Category;
import com.example.wangyulong.campuspass.Constant.RequestCode;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.SellingViewModel;
import com.example.wangyulong.campuspass.databinding.SellingPageBinding;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class SellingActivity extends AppCompatActivity
{

    SellingPageBinding binding;
    SellingViewModel sellingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selling_page);

        //init
        sellingViewModel = SellingViewModel.sellingViewModel();

        onCreateBinding();
    }

    protected void onCreateBinding()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.selling_page);

        bindVariables();
        bindSpinners();
        bindButtons();
    }

    protected void bindVariables()
    {
        binding.setSellingVM(sellingViewModel);
    }

    protected void bindSpinners()
    {
        binding.itemConditionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                setSellingItemCondition(String.valueOf(binding.itemConditionSpinner.getSelectedItem()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
                //TODO: Show warning here

            }
        });

        binding.itemCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                setSellingItemCategory(String.valueOf(binding.itemConditionSpinner.getSelectedItem()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
    }

    protected void setSellingItemCondition(String condition)
    {

        Category.BuyingItemCondition item_condition;

        switch (condition)
        {
            case "New":
                item_condition = Category.BuyingItemCondition.NEW;
            case "Dispose":
                item_condition = Category.BuyingItemCondition.DISPOSE;
            default:
                item_condition = Category.BuyingItemCondition.SECOND_HAND;
        }

        sellingViewModel.set_item_condition(item_condition);
    }

    protected void setSellingItemCategory(String tag)
    {
        Category.BuyingItemTag item_tag;

        switch (tag)
        {
            case "Books":
                item_tag = Category.BuyingItemTag.BOOKS;
            case "Home Equipments":
                item_tag = Category.BuyingItemTag.HOME_EQUIPEMENTS;
            case "Electronics":
                item_tag = Category.BuyingItemTag.ELECTRONICS;
            default:
                item_tag = Category.BuyingItemTag.FOOD;
        }

        sellingViewModel.set_item_tag(item_tag);
    }

    protected void bindButtons()
    {
        binding.setSelectPhotoButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                //startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI), RequestCode.ACCESS_DEVICE_GALLERY);

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RequestCode.ACCESS_DEVICE_GALLERY);
            }
        });

        binding.setUploadPhotoButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                //
            }
        });

        binding.setUploadSellingItemButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                //TODO: Upload and link to mainmenupage
                sellingViewModel.is_selling_item_upload_complete();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        //Detects request codes
        if (requestCode == RequestCode.ACCESS_DEVICE_GALLERY && resultCode == Activity.RESULT_OK)
        {
            try
            {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                binding.uploadSellingItemImg.setImageBitmap(selectedImage);
                sellingViewModel.isImageSelected.set(true);
            }
            catch (FileNotFoundException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
