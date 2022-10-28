package com.example.realmdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import io.realm.Realm;

public class UpdateData extends AppCompatActivity {

    TextInputEditText Id, CourseID,CName,CDesc,CTrack,CDuration;
    MaterialButton update,delete;
    long id;
    Realm realm;

//    private Bundle bundle;
    private DataModel dataModel;
//    private int position;


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
        Id.setText(String.valueOf(id));



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String  ID,courseN, courseDecp, courseT, courseDur;

                ID=Id.getText().toString();
                courseN = CName.getText().toString();
                courseDecp = CDesc.getText().toString();
                courseT = CTrack.getText().toString();
                courseDur = CDuration.getText().toString();

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        dataModel.setId(id);
                        dataModel.setCourseName(courseN);
                        dataModel.setCourseDescription(courseDecp);
                        dataModel.setCourseTrack(courseT);
                        dataModel.setCourseDuration(courseDur);
                        Intent intent = new Intent(UpdateData.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

            }
        });



    }

}