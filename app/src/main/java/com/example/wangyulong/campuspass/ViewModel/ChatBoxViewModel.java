package com.example.wangyulong.campuspass.ViewModel;

import android.databinding.ObservableField;
import android.util.Log;

import com.example.wangyulong.campuspass.Events.ChatBoxLsitRefreshEventListener;
import com.example.wangyulong.campuspass.Events.ChatMessageLoadedEventListener;
import com.example.wangyulong.campuspass.Events.ChatRoomIdLoadedEventListener;
import com.example.wangyulong.campuspass.Events.TargetUserLoadedEventListener;
import com.example.wangyulong.campuspass.Helper.ChatBoxCollectionHelper;
import com.example.wangyulong.campuspass.Helper.ChatMessageCollectionHelper;
import com.example.wangyulong.campuspass.Loader.ComplexDataLoader;
import com.example.wangyulong.campuspass.Model.ChatBoxModel;
import com.example.wangyulong.campuspass.Model.ChatMessageModel;
import com.example.wangyulong.campuspass.Model.UserModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by wangyulong on 20/4/18.
 */

public class ChatBoxViewModel extends BasicViewModel
{
    //region Fields and Const
    private static ChatBoxViewModel _instance = null;
    private String current_user_id = new String("");
    private String current_user_name = new String("");
    private String current_target_id = new String("");
    private String current_user_profile_uri = new String("");
    private String current_target_profile_uri = new String("");
    private String current_target_user_name = new String("");
    //private ChatBoxCollectionHelper chatBoxCollectionHelper = null;
    private String current_chat_box_id = new String("");
    private String current_message_id = new String("");
    private String current_chat_box_photo_uri = new String("");
    private DatabaseReference chat_room_ref;
    private String chat_room_id;
    private ComplexDataLoader dataLoader = null;
    private ChatMessageLoadedEventListener messageListRefreshListener = null;
    private ChatBoxLsitRefreshEventListener chatBoxLsitRefreshEventListener = null;
    private UserModel target_user_model = null;

    //binding
    public ObservableField<String> message_content = new ObservableField<>(new String(""));
    private ChatMessageModel message_to_send = null;

    private ChatMessageCollectionHelper chatMessageCollectionHelper = null;
    private ChatBoxCollectionHelper chatBoxCollectionHelper = null;
    private boolean is_profile_pic_loading_finished = false;
    private boolean is_user_name_loading_finished = false;
    //endregion Fields and Const

    //region Properties
    public static ChatBoxViewModel chatBoxViewModel()
    {
        if (_instance == null)
        {
            _instance = new ChatBoxViewModel();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructors
    private ChatBoxViewModel()
    {
        //init
        this.load_current_user_name();
        this.empyt_msg_content();
        this.message_to_send = new ChatMessageModel();
        this.chatBoxCollectionHelper = ChatBoxCollectionHelper.chatBoxCollectionHelper();
        this.dataLoader = ComplexDataLoader.complexDataLoader();
        this.chatMessageCollectionHelper = ChatMessageCollectionHelper.chatMessageCollectionHelper();
    }
    //endregion Constructors

    //region Methods
    private void load_current_user_name()
    {
        //init database reference
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("user_name");

        //TODO: Move code below to ComplexDataLoader in future
        //get user name from database
        ref.addValueEventListener(new ValueEventListener()
        {
            String name;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                name = dataSnapshot.getValue(String.class);

                set_user_name(name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                //do nothing
                showOnSnackBar(databaseError.getMessage());

                Log.d("database err -> ", databaseError.getMessage());
            }
        });

        DatabaseReference owner_profile_pic_ref = FirebaseDatabase.getInstance().getReference("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("user_profile_pic_uri");

        owner_profile_pic_ref.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                String uri = dataSnapshot.getValue(String.class);

                current_user_profile_uri = uri;
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                //do noting
            }
        });

    }

    private ArrayList<ChatBoxModel> get_full_chat_room_list()
    {
        return (ArrayList<ChatBoxModel>) this.chatBoxCollectionHelper.get_full_list();
    }

    private void set_user_name(String name)
    {
        this.current_user_id = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        this.current_user_name = name;
    }

    private void empyt_msg_content()
    {
        this.message_content.set(new String(""));
    }

    public void load_target_user_info(String target_id)
    {
        this.dataLoader.setTargetUserLoadedEventListener(new TargetUserLoadedEventListener()
        {
            @Override
            public void onTargetUserLoadedEventTrigger()
            {
                target_user_model = dataLoader.getTarget_user();

                current_target_user_name = target_user_model.getUser_name();
                current_target_profile_uri = target_user_model.getUser_profile_pic_uri();

                Log.d("target downloading -> ", current_target_user_name + " " + current_target_profile_uri);
            }
        });

        this.dataLoader.load_target_user_from_database(target_id);
    }

    public void set_current_chat_target_id(String id)
    {
        if (this.current_target_id != id)
        {
            chatMessageCollectionHelper.reset_collection();

            if (this.messageListRefreshListener != null)
            {
                this.messageListRefreshListener.onChatMessageLoadedEventTrigger();
            }
        }

        //fill in info
        this.current_target_id = id;
        this.current_user_id = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

//        final DatabaseReference target_profile_pic_ref = FirebaseDatabase.getInstance().getReference("user").child(id).child("user_profile_pic_uri");
//
//        target_profile_pic_ref.addValueEventListener(new ValueEventListener()
//        {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot)
//            {
//                current_target_profile_uri = dataSnapshot.getValue(String.class);
//
//                Log.d("debug", "target profile uri set to -> " + current_target_profile_uri);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError)
//            {
//                //do noting
//            }
//        });

        //set chat id
        this.dataLoader.setChatRoomIdLoadedEventListener(new ChatRoomIdLoadedEventListener()
        {
            @Override
            public void onChatRoomIdLoadedEventTrigger()
            {
                if (dataLoader.getChat_id() == null)
                {
                    chat_room_id = UUID.randomUUID().toString();

                    create_new_chat_room();
                } else
                {
                    chat_room_id = dataLoader.getChat_id();
                }
            }
        });

        //load chat id
        this.dataLoader.load_chat_id_from_database(this.current_user_id, id);
    }

    public void SendMessage()
    {
        //enable send only when chatroom has been created
        if (this.chat_room_id != null)
        {
            create_new_chat_room();

            //update database reference
            this.chat_room_ref = FirebaseDatabase.getInstance().getReference("chats").child(this.chat_room_id);

            //create new chat msg
            this.message_to_send = new ChatMessageModel(this.message_content.get());
            this.message_to_send.setChat_msg_id(UUID.randomUUID().toString());
            this.message_to_send.setChat_msg_owner(this.current_user_id);
            this.message_to_send.setChat_msg_owner_name(this.current_user_name);

            try
            {
                //check if message content is not empty
                if (this.message_content.get().length() > 1)
                {
                    //upload message to database
                    this.chat_room_ref.child(this.message_to_send.chat_msg_id).setValue(this.message_to_send).addOnSuccessListener(new OnSuccessListener<Void>()
                    {
                        @Override
                        public void onSuccess(Void aVoid)
                        {
                            //debug
                            Log.d("debug -> ", "text sent -> " + message_content);

                            //reset text
                            empyt_msg_content();
                        }
                    });
                } else
                {
                    //debug
                    Log.d("debug -> ", "empty message, not sent");
                }
            }
            catch (NullPointerException e)
            {
                //debug
                Log.d("error -> ", "Null message found");
            }
        }
    }

    private void create_new_chat_room()
    {
        //this copy of chatbox will be created on the user's perspective
        ChatBoxModel chatbox = new ChatBoxModel();
        chatbox.setChat_box_id(this.chat_room_id);
        chatbox.setChat_box_owner_id(this.current_user_id);
        chatbox.setChat_box_owner_name(this.current_target_user_name);
        chatbox.setChat_box_target_id(this.current_target_id);
        chatbox.setChat_box_target_photo_uri(this.current_target_profile_uri);

        //this copy of chatbox will be created on the target's perspective
        ChatBoxModel chatbox_to_target = new ChatBoxModel();
        chatbox_to_target.setChat_box_id(this.chat_room_id);
        chatbox_to_target.setChat_box_owner_id(this.current_target_id);
        chatbox_to_target.setChat_box_owner_name(this.current_user_name);
        chatbox_to_target.setChat_box_target_id(this.current_user_id);
        chatbox_to_target.setChat_box_target_photo_uri(this.current_user_profile_uri);

        //upload to user's chatbox
        FirebaseDatabase.getInstance().getReference("user").child(this.current_user_id).child("chat_room").child(current_target_id).setValue(chatbox)
                .addOnSuccessListener(new OnSuccessListener<Void>()
                {
                    @Override
                    public void onSuccess(Void aVoid)
                    {
                        //debug
                        Log.d("debug -> ", "new id created");
                    }
                });

        //upload to target's chatbox
        FirebaseDatabase.getInstance().getReference("user").child(this.current_target_id).child("chat_room").child(current_user_id).setValue(chatbox_to_target)
                .addOnSuccessListener(new OnSuccessListener<Void>()
                {
                    @Override
                    public void onSuccess(Void aVoid)
                    {
                        //debug
                        Log.d("debug -> ", "pushed to target");
                    }
                });
    }

    public ArrayList<ChatMessageModel> get_full_list()
    {
        return (ArrayList<ChatMessageModel>) this.chatMessageCollectionHelper.get_full_list();
    }

    public ArrayList<ChatBoxModel> get_full_chat_box()
    {
        return (ArrayList<ChatBoxModel>) this.chatBoxCollectionHelper.get_full_list();
    }

    public void load_chat_messages()
    {
        this.dataLoader.load_chat_messages_from_database(this.chat_room_id);
    }

    public void load_chat_rooms()
    {
        this.dataLoader.load_chat_rooms_from_database();
    }

    public void setChatMessageLoadedEventListener(ChatMessageLoadedEventListener listener)
    {
        this.messageListRefreshListener = listener;

        this.dataLoader.setChatMessageLoadedEventListener(listener);
    }

    public void setChatBoxLsitRefreshEventListener(ChatBoxLsitRefreshEventListener listener)
    {
        this.chatBoxLsitRefreshEventListener = listener;
        this.dataLoader.setChatBoxLsitRefreshEventListener(listener);
    }

    public String getCurrent_user_profile_uri()
    {
        return current_user_profile_uri;
    }

    public String getCurrent_target_profile_uri()
    {
        return current_target_profile_uri;
    }
    //endregion Methods
}
