package com.example.realmdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import io.realm.Realm;

public class UpdateData extends AppCompatActivity {

    TextInputEditText CourseID,CName,CDesc,CTrack,CDuration;
    MaterialButton update,delete;
    long id;
    Realm realm;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        realm = Realm.getDefaultInstance();

        CourseID = findViewById(R.id.Courseid);
        CName = findViewById(R.id.Coursename);
        CDesc = findViewById(R.id.Coursedescription);
        CTrack = findViewById(R.id.Coursetrack);
        CDuration = findViewById(R.id.Courseduration);
        update = findViewById(R.id.Update);
        delete = findViewById(R.id.Delete);

        String Cname,Cdesc,Ctrack,Cduration;

        Cname = getIntent().getStringExtra("courseName");
        Cdesc = getIntent().getStringExtra("courseDescription");
        Ctrack = getIntent().getStringExtra("courseTrack");
        Cduration= getIntent().getStringExtra("courseDuration");
        id = getIntent().getLongExtra("id", 0);

        CName.setText(Cname);
        CDesc.setText(Cdesc);
        CTrack.setText(Ctrack);
        CDuration.setText(Cduration);



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String courseN, courseDecp, courseT, courseDur;

                courseN = CName.getText().toString();
                courseDecp = CDesc.getText().toString();
                courseT = CTrack.getText().toString();
                courseDur = CDuration.getText().toString();

            }
        });



    }

}