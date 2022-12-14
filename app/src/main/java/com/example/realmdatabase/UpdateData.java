package com.example.realmdatabase;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Update;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import io.realm.Realm;

public class UpdateData extends AppCompatActivity {

    TextInputEditText  CName, CDesc, CTrack, CDuration;
    MaterialButton update, delete;
    long id;
    Realm realm;

    private DataModel dataModel;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        realm = Realm.getDefaultInstance();

        CName = findViewById(R.id.Coursename);
        CDesc = findViewById(R.id.Coursedescription);
        CTrack = findViewById(R.id.Coursetrack);
        CDuration = findViewById(R.id.Courseduration);
        update = findViewById(R.id.Update);
        delete = findViewById(R.id.Delete);




        CName.setText(getIntent().getStringExtra("courseName"));
        CDesc.setText(getIntent().getStringExtra("courseDescription"));
        CTrack.setText(getIntent().getStringExtra("courseTrack"));
        CDuration.setText(getIntent().getStringExtra("courseDuration"));


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String courseN, courseDecp, courseT, courseDur;

                id = getIntent().getLongExtra("id", 0);

                courseN = CName.getText().toString();
                courseDecp = CDesc.getText().toString();
                courseT = CTrack.getText().toString();
                courseDur = CDuration.getText().toString();


                // Update Record in database
                dataModel = realm.where(DataModel.class).equalTo("id", id).findFirst();

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        dataModel.setCourseName(courseN);
                        dataModel.setCourseDescription(courseDecp);
                        dataModel.setCourseTrack(courseT);
                        dataModel.setCourseDuration(courseDur);

                        realm.copyToRealmOrUpdate(dataModel);

                        Toast.makeText(UpdateData.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdateData.this, RetriveData.class);
                        startActivity(intent);
                        finish();
                    }
                });

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id=getIntent().getLongExtra("id",0);
                deleteCourse(id);

                Toast.makeText(UpdateData.this, "Course Deleted.", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(UpdateData.this, RetriveData.class);
                startActivity(i);
                finish();
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        realm.close();
    }

    private void deleteCourse(long id) {

        // Delete Record from database
         dataModel = realm.where(DataModel.class).equalTo("id", id).findFirst();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                dataModel.deleteFromRealm();
            }
        });

}


}