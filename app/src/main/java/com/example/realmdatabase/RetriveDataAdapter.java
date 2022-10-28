package com.example.realmdatabase;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RetriveDataAdapter extends RecyclerView.Adapter<RetriveDataAdapter.retrivedata> {


    RealmResults<DataModel> list;
    Context context;

    Realm realm;

    public RetriveDataAdapter(RealmResults<DataModel> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public retrivedata onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.datalist, parent, false);

        return new retrivedata(view);
    }

    @Override
    public void onBindViewHolder(@NonNull retrivedata holder, @SuppressLint("RecyclerView") int position) {

        DataModel dataModel = (DataModel) list.get(position);


        holder.courseName.setText(dataModel.getCourseName());
        holder.courseDescription.setText(dataModel.getCourseDescription());
        holder.coursetrack.setText(dataModel.getCourseTrack());
        holder.courseduration.setText(dataModel.getCourseDuration());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, UpdateData.class);
                intent.putExtra("dataModels", dataModel);
                context.startActivity(intent);


            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class retrivedata extends RecyclerView.ViewHolder{

        TextView courseId, courseName,courseDescription,coursetrack,courseduration;


        public retrivedata(View itemView) {

            super(itemView);
//            courseId = itemView.findViewById(R.id.courseid);
            courseName = itemView.findViewById(R.id.courseName);
            courseDescription = itemView.findViewById(R.id.courseDescription);
            coursetrack = itemView.findViewById(R.id.courseTrack);
            courseduration = itemView.findViewById(R.id.courseDuration);








        }
    }
}

