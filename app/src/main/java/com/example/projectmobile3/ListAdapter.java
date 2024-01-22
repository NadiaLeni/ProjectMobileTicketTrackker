package com.example.projectmobile3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<ListDestination> {


    public ListAdapter(@NonNull Context context, ArrayList<ListDestination> dataArrayList) {
        super(context, R.layout.list_view_destination, dataArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ListDestination listDestination = getItem(position);

        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_view_destination, parent, false);
        }

        ImageView listImage = view.findViewById(R.id.listImage);
        TextView listName = view.findViewById(R.id.listName);

        listImage.setImageResource(listDestination.image);
        listName.setText(listDestination.name);

        return view;
    }
}
