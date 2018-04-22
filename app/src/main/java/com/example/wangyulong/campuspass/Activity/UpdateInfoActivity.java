package com.example.wangyulong.campuspass.Activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.Constant.RequestCode;
import com.example.wangyulong.campuspass.Events.UserPhotoUriDownloadedEventListener;
import com.example.wangyulong.campuspass.Loader.ComplexDataLoader;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.RegisterViewModel;
import com.example.wangyulong.campuspass.databinding.UpdateInfoPageBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

public class UpdateInfoActivity extends AppCompatActivity
{
    UpdateInfoPageBinding binding;
    RegisterViewModel registerVM = RegisterViewModel.registerViewModel();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private StorageReference storageRef;
    private ComplexDataLoader dataloader;
    private boolean is_profile_pic_ready = false;

    //region Override
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.update_info_page);
        onCreateBinding();

        //init storage ref
        storageRef = FirebaseStorage.getInstance().getReference();

        //load current user info
        dataloader = ComplexDataLoader.complexDataLoader();
        dataloader.load_user_from_database(FirebaseAuth.getInstance().getCurrentUser().getUid().toString());

        //start loading profile pictures
        this.is_profile_pic_ready = true;

        this.registerVM.setUserPhotoUriDownloadedEventListener(new UserPhotoUriDownloadedEventListener()
        {
            @Override
            public void onUserPhotoUriDownloadedEventTrigger()
            {
                if (registerVM.getUser_profile_pic_uri() != null)
                {
                    Glide.with(getApplicationContext()).load(registerVM.getUser_profile_pic_uri()).into(binding.updateInfoCircularImgHost);
                } else
                {
                    Glide.with(getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/campuspass-5d6a6.appspot.com/o/images%2Fquestion_mark_face.jpg?alt=media&token=f23dfd68-c80e-40f2-b1aa-8be7581f2d3b")
                            .into(binding.updateInfoCircularImgHost);
                }
            }
        });

    }

    @Override
    protected void onResume()
    {
        super.onResume();

        if (this.is_profile_pic_ready)
        {
            if (this.registerVM.getUser_profile_pic_uri() != null)
            {
                Glide.with(this).load(this.registerVM.getUser_profile_pic_uri()).into(this.binding.updateInfoCircularImgHost);
            } else
            {
                Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/campuspass-5d6a6.appspot.com/o/images%2Fquestion_mark_face.jpg?alt=media&token=f23dfd68-c80e-40f2-b1aa-8be7581f2d3b")
                        .into(this.binding.updateInfoCircularImgHost);
            }
        }
    }
    //endregion Override

    //region Methods
    protected void onCreateBinding()
    {
        this.binding = DataBindingUtil.setContentView(this, R.layout.update_info_page);
        this.binding.setRegisterVM(this.registerVM);

        bindButtons();
    }

    private void bindButtons()
    {
        this.binding.setProfilePhotoClickListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                //initiate photo picker activity
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");

                //catch result
                startActivityForResult(photoPickerIntent, RequestCode.ACCESS_DEVICE_GALLERY);
            }
        });

        this.binding.setCancelButtonClickListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                finish();
            }
        });

        this.binding.setDoneButtonClickListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                registerVM.upload_to_database();
            }
        });
    }

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
                registerVM.setUser_profile_pic_uri(imageUri);

                //retrieve bitmap file
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                //show selected photo
                binding.updateInfoCircularImgHost.setImageBitmap(selectedImage);

                //generate id for selected photo
                final String temp_img_id = "images/" + UUID.randomUUID().toString();

                //upload to largestorage
                StorageReference ref = storageRef.child(temp_img_id);
                ref.putFile(Uri.parse(registerVM.getUser_profile_pic_uri()))
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

                                        registerVM.setUser_profile_pic_uri(uri);
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
            catch (FileNotFoundException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    protected void showSnackBar(String txt)
    {
        Snackbar.make(findViewById(android.R.id.content), txt, Snackbar.LENGTH_LONG).show();
    }

    //endregion Methods
}
