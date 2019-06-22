package com.example.rabia.loginusingphone;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Student");


    }
    public void Login(View view){
        EditText e1,e2;
        e1=findViewById(R.id.phone);
        e2=findViewById(R.id.password);
        final String phoneNumber=e1.getText().toString();
        final String pass=e2.getText().toString();
        if(pass.isEmpty() || phoneNumber.isEmpty()){
            Toast.makeText(getApplicationContext(),"Enter both ",Toast.LENGTH_LONG).show();
        }
        else{
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Toast.makeText(getApplicationContext(),"Usr",Toast.LENGTH_LONG).show();
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        String pass1=ds.child("password").getValue().toString();
                        String phone1=ds.child("phone").getValue().toString();
                        if(phoneNumber.equals(phone1)){
                            if(pass1.equals(pass)){
                                Intent i=new Intent(MainActivity.this,Main2Activity.class);
                                startActivity(i);
                            }


                        }


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }
}
