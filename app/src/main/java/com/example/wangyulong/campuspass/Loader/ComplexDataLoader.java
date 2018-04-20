package com.example.wangyulong.campuspass.Loader;

import android.content.ClipData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.wangyulong.campuspass.Events.BuyingListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.CareerListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.CareerResumeListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.CareerTeamListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.HobbyBriefListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.HobbyCardViewRefreshListener;
import com.example.wangyulong.campuspass.Events.HobbyResumeListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.MyItemListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.MyRequestListRefreshEventListener;
import com.example.wangyulong.campuspass.Events.RequestListRefreshEventListener;
import com.example.wangyulong.campuspass.Helper.BuyingItemsCollectionHelper;
import com.example.wangyulong.campuspass.Helper.CareerCollectionHelper;
import com.example.wangyulong.campuspass.Helper.CareerResumeCollectionHelper;
import com.example.wangyulong.campuspass.Helper.CareerTeamCollectionHelper;
import com.example.wangyulong.campuspass.Helper.HobbyBriefCollectionHelper;
import com.example.wangyulong.campuspass.Helper.HobbyCollectionHelper;
import com.example.wangyulong.campuspass.Helper.HobbyResumeCollectionHelper;
import com.example.wangyulong.campuspass.Helper.ItemCollectionHelper;
import com.example.wangyulong.campuspass.Helper.MyItemsCollectionHelper;
import com.example.wangyulong.campuspass.Helper.RequestEntryCollectionHelper;
import com.example.wangyulong.campuspass.Model.BuyingItemModel;
import com.example.wangyulong.campuspass.Model.CareerModel;
import com.example.wangyulong.campuspass.Model.CareerResumeModel;
import com.example.wangyulong.campuspass.Model.CareerTeamModel;
import com.example.wangyulong.campuspass.Model.DatabaseSellingModel;
import com.example.wangyulong.campuspass.Model.DetailHobbyModel;
import com.example.wangyulong.campuspass.Model.HobbyModel;
import com.example.wangyulong.campuspass.Model.HobbyResumeModel;
import com.example.wangyulong.campuspass.Model.ItemModel;
import com.example.wangyulong.campuspass.Model.RequestModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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
    //private BuyingItemsCollectionHelper _itemCollectionHelper = BuyingItemsCollectionHelper.buyingItemsCollectionHelper();
    private RequestEntryCollectionHelper _requestCollectionHelper = RequestEntryCollectionHelper.requestEntryCollectionHelper();
    private HobbyCollectionHelper _hobbyCollectionHelper = HobbyCollectionHelper.hobbyCollectionHelper();
    private HobbyBriefCollectionHelper _hobbyBriefCollectionHelper = HobbyBriefCollectionHelper.hobbyBriefCollectionHelper();
    private HobbyResumeCollectionHelper _hobbyResumeCollectionHelper = HobbyResumeCollectionHelper.hobbyResumeCollectionHelper();
    private CareerCollectionHelper _careerCollectionHelper = CareerCollectionHelper.careerCollectionHelper();
    private CareerResumeCollectionHelper _careerResumeCollectionHelper = CareerResumeCollectionHelper.careerResumeCollectionHelper();
    private CareerTeamCollectionHelper _careerTeamCollectionHelper = CareerTeamCollectionHelper.careerTeamCollectionHelper();
    private ItemCollectionHelper _itemCollectionHelper = ItemCollectionHelper.itemCollectionHelper();
    private MyItemsCollectionHelper _myItemCollectionHelper = MyItemsCollectionHelper.myItemsCollectionHelper();
    private String new_item_title = "";
    public boolean is_request_read_allowed = false;

    //event listeners
    private ChildEventListener requestChildEventListener = null;
    private ChildEventListener itemChildEventListener = null;
    private ChildEventListener hobbyChildEventListener = null;
    private ChildEventListener hobbyResumeChildEventListener = null;
    private RequestListRefreshEventListener requestListRefreshEventListener = null;
    private BuyingListRefreshEventListener buyingListRefreshEventListener = null;
    private HobbyBriefListRefreshEventListener hobbyBriefListRefreshEventListener = null;
    private MyRequestListRefreshEventListener myRequestListRefreshEventListener = null;
    private HobbyCardViewRefreshListener hobbyCardViewRefreshListener = null;
    private HobbyResumeListRefreshEventListener hobbyResumeListRefreshEventListener = null;
    private CareerListRefreshEventListener careerListRefreshEventListener = null;
    private CareerResumeListRefreshEventListener careerResumeListRefreshEventListener = null;
    private CareerTeamListRefreshEventListener careerTeamListRefreshEventListener = null;
    private MyItemListRefreshEventListener myItemListRefreshEventListener = null;
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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("items_for_sale");

        ref.addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {
                ItemModel new_item = dataSnapshot.getValue(ItemModel.class);

                //debug
                Log.d("downloading -> ", new_item.getItem_name());
                Log.d("downloading -> ", new_item.getItem_descr());
                Log.d("downloading -> ", new_item.getItem_category());
                Log.d("downloading -> ", new_item.getItem_condition());
                Log.d("downloading -> ", new_item.getItem_owner_id());
                Log.d("downloading -> ", new_item.getItem_id());
                Log.d("downloading -> ", new_item.getItem_price());
                Log.d("downloading -> ", new_item.getItem_stock_left());
                Log.d("downloading -> ", new_item.getItem_photo_uri());

                //check duplicate, add to collection
                if (!_itemCollectionHelper.check_exsitance(new_item))
                {
                    _itemCollectionHelper.add_item_to_collection(new_item);

                    //trigger list refresh event
                    if (buyingListRefreshEventListener != null)
                    {
                        buyingListRefreshEventListener.onBuyingListRefreshEventTriggered();
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s)
            {
                _itemCollectionHelper.replace_item(dataSnapshot.getValue(ItemModel.class));

                //trigger list refresh event
                if (buyingListRefreshEventListener != null)
                {
                    buyingListRefreshEventListener.onBuyingListRefreshEventTriggered();
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot)
            {
                //trigger list refresh event
                if (buyingListRefreshEventListener != null)
                {
                    buyingListRefreshEventListener.onBuyingListRefreshEventTriggered();
                }
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

    public void load_my_item_from_database()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("items_for_sale");

        ref.addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {
                ItemModel new_item = dataSnapshot.getValue(ItemModel.class);

                if (!_myItemCollectionHelper.check_exsitance(new_item))
                {
                    String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

                    if (user_id.equals(new_item.getItem_owner_id()))
                    {
                        _myItemCollectionHelper.add_item_to_collection(new_item);

                        //trigger refresh event
                        if (myItemListRefreshEventListener != null)
                        {
                            myItemListRefreshEventListener.onMyItemListRefreshEventListener();
                        }
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s)
            {
                _myItemCollectionHelper.replace_item(dataSnapshot.getValue(ItemModel.class));

                //trigger refresh event
                if (myItemListRefreshEventListener != null)
                {
                    myItemListRefreshEventListener.onMyItemListRefreshEventListener();
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot)
            {
                //trigger refresh event
                if (myItemListRefreshEventListener != null)
                {
                    myItemListRefreshEventListener.onMyItemListRefreshEventListener();
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s)
            {

            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
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

                    //trigger refresh event
                    if (hobbyResumeListRefreshEventListener != null)
                    {
                        hobbyResumeListRefreshEventListener.onHobbyResumeListRefreshEventTrigger();
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s)
            {
                //update value in collection
                _hobbyResumeCollectionHelper.change_hobby_resume(dataSnapshot.getValue(HobbyResumeModel.class));

                //trigger refresh event
                if (hobbyResumeListRefreshEventListener != null)
                {
                    hobbyResumeListRefreshEventListener.onHobbyResumeListRefreshEventTrigger();
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot)
            {
                if (hobbyResumeListRefreshEventListener != null)
                {
                    hobbyResumeListRefreshEventListener.onHobbyResumeListRefreshEventTrigger();
                }
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

    public void load_career_teams_from_database(String category)
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("careers").child("teams").child(category);

        ref.addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {
                CareerTeamModel teamModel = new CareerTeamModel();
                teamModel = dataSnapshot.getValue(CareerTeamModel.class);

                //debug
                //Log.d("downloading ", "from category " + category);
                Log.d("downloading ", "child key " + dataSnapshot.getKey().toString());
                Log.d("downloading -> ", "title " + teamModel.getTeam_title());
                Log.d("downloading -> ", "type " + teamModel.getTeam_type());
                Log.d("downloading -> ", "id " + teamModel.getTeam_id());
                Log.d("downloading -> ", "descr " + teamModel.getTeam_descr());
                Log.d("downloading -> ", "participants " + teamModel.getTeam_participants());
                Log.d("downloading -> ", "weekly hours " + teamModel.getTeam_weekly_hour());
                Log.d("downloading -> ", "date start " + teamModel.getTeam_date_start());
                Log.d("downloading -> ", "date end " + teamModel.getTeam_deadline());
                Log.d("downloading -> ", "category " + teamModel.getTeam_category());
                Log.d("downloading -> ", "leader " + teamModel.getTeam_leader_id());
                Log.d("downloading -> ", "incentives " + teamModel.getTeam_incentive_type());
                Log.d("downloading -> ", "remarks " + teamModel.getTeam_remarks());

                //check for duplicate and trigger refresh
                if (!_careerTeamCollectionHelper.check_existance(teamModel))
                {
                    _careerTeamCollectionHelper.add_team_to_collection(teamModel);

                    if (careerTeamListRefreshEventListener != null)
                    {
                        careerTeamListRefreshEventListener.onCareerTeamListRefreshEventTrigger();
                    }
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s)
            {
                _careerTeamCollectionHelper.change_team_info_in_collection(dataSnapshot.getValue(CareerTeamModel.class));

                if (careerTeamListRefreshEventListener != null)
                {
                    careerTeamListRefreshEventListener.onCareerTeamListRefreshEventTrigger();
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
                //TODO: Implement if needed
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                //TODO: Implement if needed

                Log.d("database upload -> ", "canceling");
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
                _careerResumeCollectionHelper.change_resume_in_collection(dataSnapshot.getValue(CareerResumeModel.class));

                if (careerResumeListRefreshEventListener != null)
                {
                    careerResumeListRefreshEventListener.onCareerResumeListRefreshEventTrigger();
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot)
            {
                if (careerResumeListRefreshEventListener != null)
                {
                    careerResumeListRefreshEventListener.onCareerResumeListRefreshEventTrigger();
                }
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

    public void remove_value_from_database(DatabaseReference ref, String id)
    {
        //remove child from database
        ref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                Log.d("", "remove action complete");
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid)
            {
                Log.d("", "remove action success");
            }
        });

        //update local collection
        this._careerTeamCollectionHelper.remove_team_from_list(id);
    }

    public void remove_value_from_hobby_resume(DatabaseReference ref, String id)
    {
        ref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {

            }
        }).addOnSuccessListener(new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid)
            {

            }
        });
    }

    public void remove_value_from_career_resume(DatabaseReference ref, String id)
    {
        ref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                Log.d("", "remove action complete");
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid)
            {
                Log.d("", "remove action success");
            }
        });

        //update local collection
        this._careerResumeCollectionHelper.remove_team_from_list(id);
    }

    public void remove_value_from_items(DatabaseReference ref, String id)
    {
        ref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                //debug
                Log.d("", "remove action complete");
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid)
            {
                //debug
                Log.d("", "remove action complete");
            }
        });

        this._myItemCollectionHelper.remove_item_from_collection(id);
        this._itemCollectionHelper.remove_item_from_collection(id);
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

    public void setCareerTeamListRefreshEventListener(CareerTeamListRefreshEventListener careerTeamListRefreshEventListener)
    {
        this.careerTeamListRefreshEventListener = careerTeamListRefreshEventListener;
    }

    public void setMyItemListRefreshEventListener(MyItemListRefreshEventListener myItemListRefreshEventListener)
    {
        this.myItemListRefreshEventListener = myItemListRefreshEventListener;
    }
    //endregion Methods
}
