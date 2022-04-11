package com.example.dadmballdontlie.ui.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.dadmballdontlie.viewmodels.SharedViewModel;
import com.example.dadmballdontlie.viewmodels.SharedViewModelFactory;
import com.example.dadmballdontlie.adapter.PlayerAdapter;
import com.example.dadmballdontlie.data.model.Player;
import com.example.dadmballdontlie.databinding.FragmentPlayerSearchBinding;

import java.util.List;

public class PlayerSearchFragment extends Fragment {

    private FragmentPlayerSearchBinding binding;
    private SharedViewModel sharedViewModel;
    private PlayerAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPlayerSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedViewModel = new ViewModelProvider(requireActivity(),
                new SharedViewModelFactory(requireActivity().getApplication())).get(SharedViewModel.class);

        RecyclerView recyclerView = binding.recyclerViewPlayerSearch;
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);

        recyclerView.setLayoutManager(manager);
        //Add a separation line between items
        //recyclerView.addItemDecoration(itemDecoration);

        adapter = new PlayerAdapter();
        recyclerView.setAdapter(adapter);

        binding.inputSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                sharedViewModel.getPlayersSearch(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                sharedViewModel.getPlayersSearch(s);
                return false;
            }
        });
        binding.inputSearch.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                sharedViewModel.getPlayersSearch("");
                return false;
            }
        });

        sharedViewModel.mediatorListPlayer.observe(getViewLifecycleOwner(), new Observer<List<Player>>() {
            @Override
            public void onChanged(List<Player> players) {
                if (players != null) {
                    adapter.updateList(players);
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