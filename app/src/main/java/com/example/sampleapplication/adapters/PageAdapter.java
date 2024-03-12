package com.example.sampleapplication.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.sampleapplication.apiPackage.ApiFragment;
import com.example.sampleapplication.fragments.AddDetailsFragment;
import com.example.sampleapplication.fragments.ServiceFragment;
import com.example.sampleapplication.fragments.ViewDetailsFragment;

public class PageAdapter extends FragmentStateAdapter {
    public PageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new ViewDetailsFragment();
        } else if (position == 1) {
            return new AddDetailsFragment();
        } else if (position == 2) {
            return new ServiceFragment();
        } else if (position == 3) {
            return new ApiFragment();
        }
        return new ViewDetailsFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
