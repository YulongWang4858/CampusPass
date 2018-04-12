package com.example.wangyulong.campuspass.Loader;

import android.util.Log;
import android.widget.ExpandableListView;

import com.example.wangyulong.campuspass.Constant.Category;
import com.example.wangyulong.campuspass.Events.BuyingListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.CareerListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.CareerResumeListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.HobbyBriefListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.HobbyCardViewRefreshListener;
import com.example.wangyulong.campuspass.Events.HobbyResumeListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.MyRequestListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.RequestListRefreshEventListener;
import com.example.wangyulong.campuspass.Helper.BuyingItemsCollectionHelper;
import com.example.wangyulong.campuspass.Helper.CareerCollectionHelper;
import com.example.wangyulong.campuspass.Helper.CareerResumeCollectionHelper;
import com.example.wangyulong.campuspass.Helper.HobbyBriefCollectionHelper;
import com.example.wangyulong.campuspass.Helper.HobbyCollectionHelper;
import com.example.wangyulong.campuspass.Helper.HobbyResumeCollectionHelper;
import com.example.wangyulong.campuspass.Helper.RequestEntryCollectionHelper;
import com.example.wangyulong.campuspass.Model.BuyingItemModel;
import com.example.wangyulong.campuspass.Model.CareerModel;
import com.example.wangyulong.campuspass.Model.CareerResumeModel;
import com.example.wangyulong.campuspass.Model.DatabaseSellingModel;
import com.example.wangyulong.campuspass.Model.DatabaseUserModel;
import com.example.wangyulong.campuspass.Model.DetailHobbyModel;
import com.example.wangyulong.campuspass.Model.HobbyModel;
import com.example.wangyulong.campuspass.Model.HobbyResumeModel;
import com.example.wangyulong.campuspass.Model.RequestModel;
import com.example.wangyulong.campuspass.R;
import com.example.wangyulong.campuspass.ViewModel.BuyingListViewModel;
import com.example.wangyulong.campuspass.ViewModel.RequestViewModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by wangyulong on 06/03/18.
 */

public class ComplexDataLoader extends BasicLoader
{

    //region Fields and Const
    private static ComplexDataLoader _instance = null;

    //database
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference itemDatabaseRef = database.getReference("items_for_sale");
    private DatabaseReference requestDatabaseRef = database.getReference("requests");
    private DatabaseReference hobbyDatabaseRef = database.getReference("hobbies");
    private StorageReference hobbyIconsStorage = FirebaseStorage.getInstance().getReference();

    //helpers
    private BuyingItemsCollectionHelper _itemCollectionHelper = BuyingItemsCollectionHelper.buyingItemsCollectionHelper();
    private RequestEntryCollectionHelper _requestCollectionHelper = RequestEntryCollectionHelper.requestEntryCollectionHelper();
    private HobbyCollectionHelper _hobbyCollectionHelper = HobbyCollectionHelper.hobbyCollectionHelper();
    private HobbyBriefCollectionHelper _hobbyBriefCollectionHelper = HobbyBriefCollectionHelper.hobbyBriefCollectionHelper();
    private HobbyResumeCollectionHelper _hobbyResumeCollectionHelper = HobbyResumeCollectionHelper.hobbyResumeCollectionHelper();
    private CareerCollectionHelper _careerCollectionHelper = CareerCollectionHelper.careerCollectionHelper();
    private CareerResumeCollectionHelper _careerResumeCollectionHelper = CareerResumeCollectionHelper.careerResumeCollectionHelper();
    private String new_item_title = "";
    public boolean is_request_read_allowed = false;

    //event listeners
    private ChildEventListener requestChildEventListener;
    private ChildEventListener itemChildEventListener;
    private ChildEventListener hobbyChildEventListener;
    private ChildEventListener hobbyResumeChildEventListener;
    private RequestListRefreshEventListener requestListRefreshEventListener;
    private BuyingListRefreshEventListener buyingListRefreshEventListener;
    private HobbyBriefListRefreshEventListener hobbyBriefListRefreshEventListener;
    private MyRequestListRefreshEventListener myRequestListRefreshEventListener;
    private HobbyCardViewRefreshListener hobbyCardViewRefreshListener;
    private HobbyResumeListRefreshEventListener hobbyResumeListRefreshEventListener;
    private CareerListRefreshEventListener careerListRefreshEventListener;
    private CareerResumeListRefreshEventListener careerResumeListRefreshEventListener;
    //endregion Fields and Const

    //region Properties
    public static ComplexDataLoader complexDataLoader()
    {
        if (_instance == null)
        {
            _instance = new ComplexDataLoader();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private ComplexDataLoader()
    {
        this.requestChildEventListener = new ChildEventListener()
        {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {
                RequestModel request_model = new RequestModel();

                request_model.setRequest_entry_id(dataSnapshot.getValue(RequestModel.class).getRequest_entry_id());
                request_model.setRequest_name(dataSnapshot.getValue(RequestModel.class).getRequest_name());
                request_model.setRequest_owner(dataSnapshot.getValue(RequestModel.class).getRequest_owner());
                request_model.setRequest_owner_id(dataSnapshot.getValue(RequestModel.class).getRequest_owner_id());
                request_model.setRequest_price(dataSnapshot.getValue(RequestModel.class).getRequest_price());
                request_model.setRequest_descrip(dataSnapshot.getValue(RequestModel.class).getRequest_descrip());
                request_model.setRequest_category(dataSnapshot.getValue(RequestModel.class).getRequest_category());

                Log.d("download from database ", "request id -> " + request_model.getRequest_entry_id());
                Log.d("download from database ", "title -> " + request_model.getRequest_name());
                Log.d("download from database ", "description -> " + request_model.getRequest_descrip());
                Log.d("download from database ", "price -> " + request_model.getRequest_price());
                Log.d("download from database ", "category -> " + request_model.getRequest_category());
                Log.d("download from database ", "owner_name -> " + request_model.getRequest_owner());
                Log.d("download from database ", "owner_id -> " + request_model.getRequest_owner_id());

                if (!_requestCollectionHelper.check_existance(request_model.getRequest_entry_id()))
                {
                    _requestCollectionHelper.add_new_item_to_collection(request_model);
                }

                //invoke refresh action
                if (requestListRefreshEventListener != null)
                {
                    requestListRefreshEventListener.onRequestListRefreshEventTrigger();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s)
            {
                //TODO: Implement later

                Log.d("debug -> ", "reached child changed");

                //invoke refresh action
                if (requestListRefreshEventListener != null)
                {
                    requestListRefreshEventListener.onRequestListRefreshEventTrigger();
                }
                if (myRequestListRefreshEventListener != null)
                {
                    myRequestListRefreshEventListener.onMyRequestListRefreshEventTrigger();
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot)
            {
                //TODO: Implement later
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s)
            {
                //TODO: Implement later
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        };

        this.itemChildEventListener = new ChildEventListener()
        {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {
                DatabaseSellingModel item_model = new DatabaseSellingModel();

                //download item model from database
                item_model.setItem_id(dataSnapshot.getValue(DatabaseSellingModel.class).getItem_id());
                item_model.setItem_title(dataSnapshot.getValue(DatabaseSellingModel.class).getItem_title());
                item_model.setItem_category_tag(dataSnapshot.getValue(DatabaseSellingModel.class).getItem_category_tag());
                item_model.setItem_condition_tag(dataSnapshot.getValue(DatabaseSellingModel.class).getItem_condition_tag());
                item_model.setItem_img_uri(dataSnapshot.getValue(DatabaseSellingModel.class).getItem_img_uri());
                item_model.setItem_short_descr(dataSnapshot.getValue(DatabaseSellingModel.class).getItem_short_descr());
                item_model.setItem_owner(dataSnapshot.getValue(DatabaseSellingModel.class).getItem_owner());
                item_model.setItem_stock_left(dataSnapshot.getValue(DatabaseSellingModel.class).getItem_stock_left());
                item_model.setItem_price(dataSnapshot.getValue(DatabaseSellingModel.class).getItem_price());

                //for debugging
                Log.d("download from database", "item id -> " + item_model.getItem_id());
                Log.d("download from database", "title -> " + item_model.getItem_title());
                Log.d("download from database", "short_descr -> " + item_model.getItem_short_descr());
                Log.d("download from database", "condition -> " + item_model.getItem_condition_tag());
                Log.d("download from database", "category -> " + item_model.getItem_category_tag());
                Log.d("download from database", "owner -> " + item_model.getItem_owner());
                Log.d("download from database", "price -> " + item_model.getItem_price());
                Log.d("download from database", "stock -> " + item_model.getItem_stock_left());
                Log.d("download from database", "download link -> " + item_model.getItem_img_uri());

                if (!_itemCollectionHelper.check_existance(item_model))
                {
                    _itemCollectionHelper.add_item_to_collection(new BuyingItemModel(item_model.getItem_img_uri(), item_model.getItem_title(), item_model.getItem_short_descr(), item_model.getItem_category_tag(),
                            item_model.getItem_condition_tag(), item_model.getItem_price(), item_model.getItem_stock_left(), item_model.getItem_owner(), item_model.getItem_id()));
                } else
                {
                    Log.d("duplicated found", "");
                }

                if (buyingListRefreshEventListener != null)
                {
                    buyingListRefreshEventListener.onBuyingListRefreshEventTriggered();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s)
            {
                //TODO: Implement later
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot)
            {
                //TODO: Implement later
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s)
            {
                //TODO: Implement later
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                //TODO: Implement later
            }
        };

        this.hobbyChildEventListener = new ChildEventListener()
        {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {
                HobbyModel hobby_model = new HobbyModel();

                hobby_model = dataSnapshot.getValue(HobbyModel.class);
                hobby_model.setHobby_category(dataSnapshot.getKey().toString());

                if (!_hobbyCollectionHelper.check_existance(hobby_model))
                {
                    Log.d("downloading name ->", hobby_model.getHobby_name());
                    Log.d("downloading partic. ->", Integer.toString(hobby_model.getParticipants()));
                    Log.d("downloading cat. ->", hobby_model.getHobby_category());

                    _hobbyCollectionHelper.add_item_to_collection(hobby_model);

                    if (hobbyCardViewRefreshListener != null)
                    {
                        hobbyCardViewRefreshListener.onHobbyCardViewRefreshTriggered();
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s)
            {
                //TODO: Implement later
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot)
            {
                //TODO: Implement later
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s)
            {
                //TODO: Implement later
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                //TODO: Implement later
            }
        };
    }
    //endregion Constructor

    //region Methods
    public void load_items_from_database()
    {
        this.itemDatabaseRef.addChildEventListener(this.itemChildEventListener);
    }

    public void load_request_from_database()
    {
        this.requestDatabaseRef.addChildEventListener(this.requestChildEventListener);
    }

    public void load_hobbies_from_database()
    {
        this.hobbyDatabaseRef.addChildEventListener(this.hobbyChildEventListener);
    }

    public void load_detail_hobbies_from_database(String category_key)
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("hobbies").child(category_key).child("detail_hobby");

        ref.addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {
                //load hobby
                DetailHobbyModel detail_hobby_model = dataSnapshot.getValue(DetailHobbyModel.class);

                //for debug
                Log.d("downloading name ->", detail_hobby_model.getDetail_hobby_name());
                Log.d("downloading descr ->", detail_hobby_model.getDetail_hobby_descr());
                Log.d("downloading part. ->", Integer.toString(detail_hobby_model.getDetail_participants()));
                Log.d("downloading uri ->", detail_hobby_model.getDetail_hobby_icon_uri());

                //add to collection
                if (!_hobbyBriefCollectionHelper.check_existance(detail_hobby_model))
                {
                    _hobbyBriefCollectionHelper.add_new_item_to_collection(detail_hobby_model);
                }

                //trigger refresh
                if (hobbyBriefListRefreshEventListener != null)
                {
                    hobbyBriefListRefreshEventListener.onHobbyBriefListRefreshEventTrigger();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s)
            {
                //TODO: Implement later
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot)
            {
                //TODO: Implement later
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s)
            {
                //TODO: Implement later
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                //TODO: Implement later
            }
        });
    }

    public void load_hobby_resume_from_database(final String hobby_category)
    {
        //add child event
        DatabaseReference hobbyResumeRef = database.getReference("hobby_resumes").child(hobby_category);

        hobbyResumeRef.addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {
                HobbyResumeModel resume_model = new HobbyResumeModel();

                resume_model = dataSnapshot.getValue(HobbyResumeModel.class);

                //debug
                Log.d("downloading ", "from -> " + "hobby_resumes/" + hobby_category + "/");
                Log.d("downloading ", "resume title -> " + resume_model.getHobby_resume_title());
                Log.d("downloading ", "resume descr -> " + resume_model.getHobby_resume_descr());
                Log.d("downloading ", "resume price -> " + resume_model.getHobby_resume_price());
                Log.d("downloading ", "resume owner_id -> " + resume_model.getHobby_resume_owner_id());
                Log.d("downloading ", "resume res_id -> " + resume_model.getHobby_resume_entry_id());
                Log.d("downloading ", "resume photo_uri -> " + resume_model.getHobby_resume_photo_uri());

                //add to list and trigger refresh event
                if (!_hobbyResumeCollectionHelper.check_existance(resume_model))
                {
                    _hobbyResumeCollectionHelper.add_item_to_collection(resume_model);

                    if (hobbyResumeListRefreshEventListener != null)
                    {
                        hobbyResumeListRefreshEventListener.onHobbyResumeListRefreshEventTrigger();
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s)
            {
                //TODO: Implement later
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot)
            {
                //TODO: Implement later
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s)
            {
                //TODO: Implement later
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                //TODO: Implement later
            }
        });
    }

    public void load_career_from_database()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("careers").child("icons");

        ref.addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {
                CareerModel career = dataSnapshot.getValue(CareerModel.class);

                //debug
                Log.d("downloading -> ", "title " + career.getCareer_title());
                Log.d("downloading -> ", "descr" + career.getCareer_descr());
                Log.d("downloading -> ", "uri" + career.getCareer_icon_photo_uri());
                Log.d("downloading -> ", "participants" + career.getCareer_participants());

                if (!_careerCollectionHelper.check_existance(career))
                {
                    _careerCollectionHelper.add_new_career_to_collection(career);

                    //TODO: Notify adapter
                    if (careerListRefreshEventListener != null)
                    {
                        careerListRefreshEventListener.onCareerListRefreshEventTrigger();
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s)
            {
                //TODO: Implement later
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot)
            {
                //TODO: Implement later
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s)
            {
                //TODO: Implement later
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                //TODO: Implement later
            }
        });
    }

    public void load_career_resume_from_database(String category)
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("careers").child(category);

        ref.addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {
                CareerResumeModel resumeModel = new CareerResumeModel();

                resumeModel = dataSnapshot.getValue(CareerResumeModel.class);

                //debug
                Log.d("debug ", "title -> " + resumeModel.getCareer_name());
                Log.d("debug ", "category -> " + resumeModel.getCareer_category());
                Log.d("debug ", "about self -> " + resumeModel.getCareer_about_self());
                Log.d("debug ", "experience -> " + resumeModel.getCareer_experience());
                Log.d("debug ", "gender -> " + resumeModel.getCareer_gender());
                Log.d("debug ", "major -> " + resumeModel.getCareer_major());
                Log.d("debug ", "owner name -> " + resumeModel.getCareer_owner_name());
                Log.d("debug ", "owner id -> " + resumeModel.getCareer_owner_id());
                Log.d("debug ", "resume id -> " + resumeModel.getCareer_resume_id());

                if (!_careerResumeCollectionHelper.check_existance(resumeModel))
                {
                    _careerResumeCollectionHelper.add_career_resume_to_collection(resumeModel);
                }

                if (careerResumeListRefreshEventListener != null)
                {
                    careerResumeListRefreshEventListener.onCareerResumeListRefreshEventTrigger();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s)
            {
                //TODO: Implement later
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot)
            {
                //TODO: Implement later
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s)
            {
                //TODO: Implement later
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                //TODO: Implement later
            }
        });
    }

    public void unload_request_from_database()
    {
        this.requestDatabaseRef.removeEventListener(this.requestChildEventListener);
    }

    public void set_new_item_title(String title)
    {
        this.new_item_title = title;
    }

    public void setRequestListRefreshEventListener(RequestListRefreshEventListener requestListRefreshEventListener)
    {
        this.requestListRefreshEventListener = requestListRefreshEventListener;
    }

    public void setBuyingListRefreshEventListener(BuyingListRefreshEventListener buyingListRefreshEventListener)
    {
        this.buyingListRefreshEventListener = buyingListRefreshEventListener;
    }

    public void setMyRequestListRefreshEventListener(MyRequestListRefreshEventListener myRequestListRefreshEventListener)
    {
        this.myRequestListRefreshEventListener = myRequestListRefreshEventListener;
    }

    public void setHobbyCardViewRefreshListener(HobbyCardViewRefreshListener hobbyCardViewRefreshListener)
    {
        this.hobbyCardViewRefreshListener = hobbyCardViewRefreshListener;
    }

    public void setHobbyBriefListRefreshEventListener(HobbyBriefListRefreshEventListener hobbyBriefListRefreshEventListener)
    {
        this.hobbyBriefListRefreshEventListener = hobbyBriefListRefreshEventListener;
    }

    public void setHobbyResumeListRefreshEventListener(HobbyResumeListRefreshEventListener hobbyResumeListRefreshEventListener)
    {
        this.hobbyResumeListRefreshEventListener = hobbyResumeListRefreshEventListener;
    }

    public void setCareerListRefreshEventListener(CareerListRefreshEventListener careerListRefreshEventListener)
    {
        this.careerListRefreshEventListener = careerListRefreshEventListener;
    }

    public void setCareerResumeListRefreshEventListener(CareerResumeListRefreshEventListener careerResumeListRefreshEventListener)
    {
        this.careerResumeListRefreshEventListener = careerResumeListRefreshEventListener;
    }
    //endregion Methods
}
