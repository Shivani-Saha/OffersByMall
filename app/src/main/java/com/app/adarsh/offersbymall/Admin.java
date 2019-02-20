package com.app.adarsh.offersbymall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adarsh.offersbymall.R;
import com.firebase.client.Firebase;

public class Admin  extends AppCompatActivity {
    EditText User, Pass;
    String Username, Password;
    Button btn;
    //Firebase mRootRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        btn=(Button)findViewById(R.id.btn_login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User = (EditText) findViewById(R.id.email);
                Pass = (EditText) findViewById(R.id.password);
                Username = User.getText().toString();
                Password = Pass.getText().toString();
                if(Username.equals("admin") && Password.equals("admiN123")){
                    Intent intent=new Intent(Admin.this,Adminpannel.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getBaseContext(),"Wrong username/password!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
   public  void  admin(View v)
   {
      // mRootRef = new Firebase("https://offersbymall-93fb1.firebaseio.com/Admin");


       /*
       mRootRef.addListenerForSingleValueEvent(
               new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       String a=dataSnapshot.getValue().toString();
                       Toast.makeText(Admin.this,a, Toast.LENGTH_SHORT).show();
                       if(a.equals(Password))
                       {
                           Intent intent=new Intent(Admin.this,Adminpannel.class);
                           startActivity(intent);
                       }
                       else {
                           Toast.makeText(Admin.this, "wrong username password", Toast.LENGTH_SHORT).show();
                       }

                   }

                   @Override
                   public void onCancelled(FirebaseError firebaseError) {
                       Toast.makeText(Admin.this, "Check your Internet connection!!", Toast.LENGTH_SHORT).show();
                   }




               });

      */
     /* mRootRef.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot snapshot) {
               if (snapshot.getValue()!= null) {
                   //user exists, do something
                   UserDetails user = snapshot.getValue(UserDetails.class);

                   Toast.makeText(Admin.this, "User name: " + user.getName() + ", Phone " + user.getPhone() + ",ShopNo" + user.getShopNo() + ", Offer " + user.getOffer() + ",City" + user.getCity(), Toast.LENGTH_SHORT).show();
                   //Toast.makeText(Admin.this,snapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
               } else {
                   //user does not exist, do something else
                   Toast.makeText(Admin.this, "no", Toast.LENGTH_SHORT).show();
               }
           }
           @Override
           public void onCancelled(FirebaseError arg0) {
           }
       });*/
   }
}

