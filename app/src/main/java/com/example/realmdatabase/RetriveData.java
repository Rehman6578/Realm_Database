package com.example.realmdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RetriveData extends AppCompatActivity {

    RecyclerView recyclerView;
    Realm realm;
    RetriveDataAdapter retriveDataAdapter;

    ArrayList<DataModel> dataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrive_data);


        realm = Realm.getDefaultInstance();
        dataModel= new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager llm= new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);



    }

    @Override
    protected void onResume() {
        super.onResume();


        // Retrive all data from datbase
        RealmResults<DataModel> dataModels = realm.where(DataModel.class).findAll();

        retriveDataAdapter= new RetriveDataAdapter(dataModels,this);

        recyclerView.setAdapter(retriveDataAdapter);


    }
}