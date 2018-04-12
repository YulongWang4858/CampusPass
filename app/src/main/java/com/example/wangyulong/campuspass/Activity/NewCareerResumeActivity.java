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
import android.view.View;
import android.widget.AdapterView;

import com.example.wangyulong.campuspass.ClickListener;
import com.example.wangyulong.campuspass.Constant.RequestCode;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.CareerListViewModel;
import com.example.wangyulong.campuspass.databinding.NewCareerResumePageBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

public class NewCareerResumeActivity extends AppCompatActivity
{

    //region Fields and Const
    private NewCareerResumePageBinding binding;
    private CareerListViewModel careerListViewModel;
    //endregion Fields and Const

    //region Override
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_career_resume_page);

        onCreateBinding();
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
                final Uri imageUri = data.getData(); // obtaining uri from gallery
                careerListViewModel.set_photo_to_upload(imageUri);
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                binding.newCareerImg.setImageBitmap(selectedImage);
                careerListViewModel.change_upload_visibility(true);
            }
            catch (FileNotFoundException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    //endregion Override

    //region Methods
    private void onCreateBinding()
    {
        this.binding = DataBindingUtil.setContentView(this, R.layout.new_career_resume_page);
        this.careerListViewModel = CareerListViewModel.careerListViewModel();
        this.binding.setCareerListVM(this.careerListViewModel);

        this.bindSpinners();
        this.bindButtons();
    }

    private void bindSpinners()
    {
        this.binding.newCareerGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                //setSellingItemCondition(adapterView.getItemAtPosition(i).toString());
                //careerListViewModel.set_new_resume_gender(String.valueOf(adapterView.getItemIdAtPosition(i)));

                if (String.valueOf(adapterView.getItemIdAtPosition(i)).equals("0"))
                {
                    careerListViewModel.set_new_resume_gender("Male");
                } else
                {
                    careerListViewModel.set_new_resume_gender("Female");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
                //do nothing
            }
        });
    }

    private void bindButtons()
    {
        this.binding.setNewCareerResumeSelectPhotoButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RequestCode.ACCESS_DEVICE_GALLERY);
            }
        });

        this.binding.setNewCareerResumeUploadPhotoButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                final String temp_img_id = "images/" + UUID.randomUUID().toString();

                StorageReference ref = storageRef.child(temp_img_id);
                ref.putFile(careerListViewModel.get_photo_to_upload())
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

                                        careerListViewModel.set_photo_to_upload(uri);
                                        careerListViewModel.upload_complete();
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

        this.binding.setNewCareerResumeDoneButtonClickedListener(new ClickListener()
        {
            @Override
            public void onClick()
            {
                careerListViewModel.upload_resume_to_datebase();
            }
        });
    }

    protected void showSnackBar(String txt)
    {
        Snackbar.make(findViewById(android.R.id.content), txt, Snackbar.LENGTH_LONG).show();
    }
    //endregion Methods
}
