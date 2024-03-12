package com.example.sampleapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.sampleapplication.POJO.StorageClass;
import com.example.sampleapplication.R;

import java.util.ArrayList;


public class AddDetailsFragment extends Fragment {

    TextView textView;
    EditText editText;
    Button btnAdd;

    ArrayList<String> itemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_details, container, false);

        editText = view.findViewById(R.id.editText);
        textView = view.findViewById(R.id.text);
        btnAdd = view.findViewById(R.id.btnAdd);
        itemList = StorageClass.loadData(requireContext());

        btnAdd.setOnClickListener(v -> {

            String newItem = editText.getText().toString();

            if (!newItem.isEmpty()){

                itemList.add(newItem);
                StorageClass.saveData(requireContext(),itemList);
                editText.setText("");
                Intent broadcastIntent = new Intent("com.example.sampleapplication.NEW_ITEM_ADDED");
                requireContext().sendBroadcast(broadcastIntent);
                Toast.makeText(getContext(),"Data added",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(),"Failed to add data",Toast.LENGTH_SHORT).show();
            }

        });



        return view;
    }
}