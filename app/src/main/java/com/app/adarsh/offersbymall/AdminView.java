package com.app.adarsh.offersbymall;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import com.example.adarsh.offersbymall.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class AdminView extends AppCompatActivity {
    Firebase mRootRef;
    private FirebaseUser user;
    private String uId;
    ArrayList<String> list= new ArrayList<>();
    ArrayAdapter<String> adapter;
    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter adapters;
    private ProgressBar progressBar;
    private ArrayList<UserDe> feedsList;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        getSupportActionBar().setTitle("User List");

        mProgressDialog = new ProgressDialog(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.show();

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mRootRef = new Firebase("https://offersbymall-93fb1.firebaseio.com/users");
        mRootRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                feedsList = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {


                    UserDe people = child.getValue(UserDe.class);
                    String name = people.getName();
                    String shop = people.getShopNo();
                    String Mall = people.getMall();
                    String City = people.getCity();
                    UserDe item = new UserDe();
                    item.setName(people.getName());
                    item.setCity(people.getCity());
                    item.setShopNo(people.getShopNo());
                    list.add(name);
                    list.add(shop);
                    list.add(Mall);
                    list.add(City);
                    feedsList.add(people);

                   // Toast.makeText(AdminView.this, "ok", Toast.LENGTH_SHORT).show();

                }
                mProgressDialog.dismiss();
                adapters = new MyRecyclerViewAdapter(AdminView.this, feedsList);
                mRecyclerView.setAdapter(adapters);
                // String value=dataSnapshot.getValue(String.class);
                // list.add(value);


            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


/*final ListView listView=(ListView)findViewById(R.id.list_item);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,list);
        listView.setAdapter(adapter);


        mRootRef = new Firebase("https://offersbymall-93fb1.firebaseio.com/users");
        mRootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                   UserDe people = child.getValue(UserDe.class);
                    String name=people.getName();
                    String shop=people.getShopNo();
                   list.add(name);
                    list.add(shop);
                }
               // String value=dataSnapshot.getValue(String.class);
               // list.add(value);
                adapter.notifyDataSetChanged();

            }



            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        /*mRootRef.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        collectPhoneNumbers((Map<String, Object>) dataSnapshot.getValue());
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }


                });
    }
    private void collectPhoneNumbers(Map<String,Object> users) {

        ArrayList<Long> phoneNumbers = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()){

            //Get user map
          // String applicationNumber = (String) entry.getValue();
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            phoneNumbers.add(Long.valueOf((String) singleUser.get(i)));
        }

        System.out.println(phoneNumbers.toString());
    }

      /*  mRootRef.child(uId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //String a =snapshot.getValue().toString();
                //Toast.makeText(AdminView.this,a, Toast.LENGTH_SHORT).show();
                    //user exists, do something
                  //  UserDetails user = snapshot.getValue(UserDetails.class);

               // Toast.makeText(AdminView.this, "User name: " + user.getName() + ", Phone " + user.getPhone() + ",ShopNo" + user.getShopNo() + ", Offer " + user.getOffer() + ",City" + user.getCity(), Toast.LENGTH_SHORT).show();
                }


            @Override
            public void onCancelled(FirebaseError arg0) {
            }
        });*/
    }
}







