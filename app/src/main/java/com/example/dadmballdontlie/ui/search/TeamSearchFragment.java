package com.example.dadmballdontlie.ui.search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dadmballdontlie.adapter.TeamAdapter;
import com.example.dadmballdontlie.repositories.NbaRepository;
import com.example.dadmballdontlie.repositories.NbaRepositoryImpl;
import com.example.dadmballdontlie.viewmodels.SharedViewModel;
import com.example.dadmballdontlie.viewmodels.SharedViewModelFactory;
import com.example.dadmballdontlie.data.model.Team;
import com.example.dadmballdontlie.databinding.FragmentTeamSearchBinding;

import java.util.ArrayList;
import java.util.List;

public class TeamSearchFragment extends Fragment {

    private FragmentTeamSearchBinding binding;
    private SharedViewModel sharedViewModel;
    private TeamAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTeamSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedViewModel = new ViewModelProvider(requireActivity(),
                new SharedViewModelFactory(requireActivity().getApplication())).get(SharedViewModel.class);

        RecyclerView recyclerView = binding.recyclerViewTeamSearch;
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);

        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(itemDecoration);

        adapter = new TeamAdapter();
        recyclerView.setAdapter(adapter);

        sharedViewModel.listTeamLocal.observe(getViewLifecycleOwner(), new Observer<List<Team>>() {
            @Override
            public void onChanged(List<Team> teams) {
                if (teams != null) {
                    adapter.updateList(teams);
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}