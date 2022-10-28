package com.example.realmdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {


    TextInputEditText courseName,courseDescription,coursetrack,courseduration;
    MaterialButton Save,showdata;
    Realm realm;

    String CourseName,CourseDescription,Coursetrack,Courseduration;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       realm = Realm.getDefaultInstance();

        courseName=(TextInputEditText)findViewById(R.id.coursename);
        courseDescription=(TextInputEditText)findViewById(R.id.coursedescription);
        coursetrack=(TextInputEditText)findViewById(R.id.coursetrack);
        courseduration=(TextInputEditText)findViewById(R.id.courseduration);
        Save=(MaterialButton)findViewById(R.id.save);
        showdata=(MaterialButton)findViewById(R.id.read);



        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 CourseName = courseName.getText().toString();
                 CourseDescription = courseDescription.getText().toString();
                 Coursetrack = coursetrack.getText().toString();
                 Courseduration = courseduration.getText().toString();


                addTodatabase(CourseName, Coursetrack, CourseDescription, courseduration);
                Toast.makeText(MainActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                courseName.setText("");
                courseDescription.setText("");
                coursetrack.setText("");
                courseduration.setText("");
            }
        });

        showdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(MainActivity.this,RetriveData.class);
                startActivity(intent);
            }
        });

    }

    private void addTodatabase(String courseName, String coursetrack, String courseDescription, TextInputEditText courseduration) {

        DataModel dataModel = new DataModel();
        Number id= realm.where(DataModel.class).max("id");

        long nextid ;


        if (id ==null){
            nextid =1;

        }
        else{

            nextid = id.intValue()+1;

        }

        dataModel.setId(nextid);
        dataModel.setCourseName(CourseName);
        dataModel.setCourseDescription(CourseDescription);
        dataModel.setCourseTrack(Coursetrack);
        dataModel.setCourseDuration(Courseduration);


        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                realm.copyToRealm(dataModel);
            }
        });

    }
}