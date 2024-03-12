package com.example.sampleapplication.fragments;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapplication.POJO.StorageClass;
import com.example.sampleapplication.R;
import com.example.sampleapplication.adapters.RecyclerAdapter;

import java.util.ArrayList;

public class ViewDetailsFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    ArrayList<String> itemList;


    BroadcastReceiver newItemReceiver = new BroadcastReceiver() {
        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void onReceive(Context context, Intent intent) {
            itemList.clear();
            itemList.addAll(StorageClass.loadData(requireContext()));
            adapter.notifyDataSetChanged();
            Log.d("View","method called");
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentFilter intentFilter = new IntentFilter("com.example.sampleapplication.NEW_ITEM_ADDED");
        requireContext().registerReceiver(newItemReceiver,intentFilter, Context.RECEIVER_EXPORTED);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        requireContext().unregisterReceiver(newItemReceiver);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_details, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        itemList = new ArrayList<>();
        itemList.addAll(StorageClass.loadData(requireContext()));
        adapter= new RecyclerAdapter(itemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }


}