package com.example.dadmballdontlie.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dadmballdontlie.R;
import com.example.dadmballdontlie.SharedViewModel;
import com.example.dadmballdontlie.SharedViewModelFactory;
import com.example.dadmballdontlie.adapter.teamList;
import com.example.dadmballdontlie.databinding.FragmentSearchBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private SharedViewModel sharedViewModel;
    private teamList adapter;
    private TabLayout tabLayoutTeam;
    private RecyclerView recyclerViewTeam;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedViewModel = new ViewModelProvider(requireActivity(),
                new SharedViewModelFactory(requireActivity().getApplication())).get(SharedViewModel.class);

        initViews();
        initTabLayout();
        initRecycler();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initViews(){
        tabLayoutTeam = binding.tabLayoutSearch;
        recyclerViewTeam = binding.recyclerViewSearch;
    }

    private void initTabLayout(){
        tabLayoutTeam.addTab(tabLayoutTeam.newTab().setText(R.string.tab_teams));
        tabLayoutTeam.addTab(tabLayoutTeam.newTab().setText(R.string.tab_players));
    }

    private void initRecycler(){
        adapter = new teamList(fakeTeamList());
        recyclerViewTeam.setAdapter(adapter);
    }

    private List<String> fakeTeamList(){
        List<String> fakeList = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            fakeList.add("Los Angeles Lakers");
        }

        return fakeList;
    }
}