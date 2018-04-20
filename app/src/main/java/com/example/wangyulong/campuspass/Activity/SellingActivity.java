package com.example.wangyulong.campuspass.Activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.Constant.Category;
import com.example.wangyulong.campuspass.Constant.RequestCode;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.SellingViewModel;
import com.example.wangyulong.campuspass.databinding.SellingPageBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

public class SellingActivity extends AppCompatActivity
{

    SellingPageBinding binding;
    SellingViewModel sellingViewModel;
    private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selling_page);

        //init
        sellingViewModel = SellingViewModel.sellingViewModel();
        storageRef = FirebaseStorage.getInstance().getReference();

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
                sellingViewModel.set_item_condition(i);
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
                //setSellingItemCategory(adapterView.getItemAtPosition(i).toString());
                sellingViewModel.set_item_category(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
                //TODO: Show snack bar msg to notify
            }
        });
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
                final String temp_img_id = "images/" + UUID.randomUUID().toString();

                StorageReference ref = storageRef.child(temp_img_id);
                ref.putFile(sellingViewModel.get_photo_to_upload())
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                        {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                            {
                                Log.d("Uploading photo -> ", "Successfully uploaded!");
                                showSnackBar("Upload photo success!");

                                //obtain a fresh download link
                                StorageReference fresh_img_ref = FirebaseStorage.getInstance().getReference().child(temp_img_id);
                                fresh_img_ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                                {
                                    @Override
                                    public void onSuccess(Uri uri)
                                    {
                                        Log.d("Obtain download link ->", "download url obtianed -> " + uri);

                                        sellingViewModel.set_photo_to_upload(uri);
                                        sellingViewModel.upload_complete();
                                    }
                                }).addOnFailureListener(new OnFailureListener()
                                {
                                    @Override
                                    public void onFailure(@NonNull Exception exception)
                                    {
                                        Log.d("Obtain download link ->", "failed with " + exception.getMessage());
                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() //trace upload error
                        {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {
                                Log.d("Uploading photo -> ", e.getMessage());
                                showSnackBar("upload photo failed -> " + e.getMessage());
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() //trace upload progress
                        {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot)
                            {
                                //TODO: Show progress bar in future
                                Log.d("uploading -> ", "100%");

                                showSnackBar("Uploading, please wait");
                            }
                        });
            }
        });

        binding.setUploadSellingItemButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                sellingViewModel.is_selling_item_upload_complete();
                sellingViewModel.create_selling_item_on_database();
            }
        });

        binding.setSellingPageCancelButton(new ClickListener()
        {
            @Override
            public void onClick()
            {
                finish();
            }
        });

        binding.setSellingPageDeleteButton(new ClickListener()
        {
            @Override
            public void onClick()
            {
                sellingViewModel.delete_current_item();
            }
        });
    }

    //region Methods

    //Result of accessing Gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        //Detects request codes
        if (requestCode == RequestCode.ACCESS_DEVICE_GALLERY && resultCode == Activity.RESULT_OK)
        {
            try
            {
                final Uri imageUri = data.getData(); // obtaining uri from gallery
                sellingViewModel.set_photo_to_upload(imageUri);
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
