package com.example.dadmballdontlie.ui.favs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dadmballdontlie.R;
import com.example.dadmballdontlie.adapter.ViewPagerAdapter;
import com.example.dadmballdontlie.viewmodels.SharedViewModel;
import com.example.dadmballdontlie.databinding.FragmentFavsBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class FavsFragment extends Fragment {

    private FragmentFavsBinding binding;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentFavsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tabLayout = binding.tabLayoutFavs;
        viewPager = binding.viewPagerFavs;

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPagerAdapter.addFragment(new PlayerFavsFragment(), "Player Favs");
        viewPagerAdapter.addFragment(new TeamFavsFragment(), "Teams Favs");
        viewPager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText(getString(R.string.tabLayoutPlayers));
                } else {
                    tab.setText(getString(R.string.tabLayoutTeams));
                }
            }
        }).attach();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}