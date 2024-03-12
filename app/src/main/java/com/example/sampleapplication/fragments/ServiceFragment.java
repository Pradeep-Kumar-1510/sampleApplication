package com.example.sampleapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.sampleapplication.POJO.MusicService;
import com.example.sampleapplication.R;

public class ServiceFragment extends Fragment {

    private Intent serviceIntent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);

        Button btnStart = view.findViewById(R.id.btnStart);
        Button btnStop = view.findViewById(R.id.btnStop);

        btnStart.setOnClickListener(v -> startMusicService());

        btnStop.setOnClickListener(v -> stopMusicService());

        return view;
    }

    private void startMusicService() {
        if (serviceIntent == null) {
            serviceIntent = new Intent(requireContext(), MusicService.class);
            requireContext().startService(serviceIntent);

        }
    }

    private void stopMusicService() {
        if (serviceIntent != null) {
            requireContext().stopService(serviceIntent);
            serviceIntent = null;
        }
    }
}