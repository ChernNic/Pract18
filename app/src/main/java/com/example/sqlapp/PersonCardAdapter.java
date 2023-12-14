package com.example.sqlapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PersonCardAdapter extends RecyclerView.Adapter<PersonCardAdapter.PersonViewHolder> {

    private ArrayList<Person> personList;
    private Context context;
    private DatabaseHelper dbHelper;
    private Activity activity;

    public PersonCardAdapter(Context context, ArrayList<Person> personList, Activity activity) {
        this.context = context;
        this.personList = personList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_card, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person = personList.get(position);
        dbHelper = new DatabaseHelper(activity);

        holder.nameTextView.setText(person.getName());
        holder.ageTextView.setText(String.valueOf(person.getAge()));
        holder.occupationTextView.setText(person.getOccupation());
        holder.addressTextView.setText(person.getAddress());

        holder.editPersonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, EditPersonActivity.class);
                intent.putExtra("position", position);
                activity.startActivity(intent);
            }
        });

        holder.deletePersonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deletePerson(person.getName());
                Toast.makeText(context,"Person Deleted", Toast.LENGTH_SHORT).show();

                personList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, personList.size());
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("position", position);
                activity.startActivity(intent);
            }
        });

        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    private int lastPosition = -1;

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in);
            view.startAnimation(animation);
            lastPosition = position;
        }
    }


    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, ageTextView, occupationTextView, addressTextView;
        AppCompatButton editPersonBtn, deletePersonBtn;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            ageTextView = itemView.findViewById(R.id.ageTextView);
            occupationTextView = itemView.findViewById(R.id.occupationTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
            editPersonBtn = itemView.findViewById(R.id.editPersonButton);
            deletePersonBtn = itemView.findViewById(R.id.deletePersonButton);
        }
    }
}