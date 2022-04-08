package com.example.dadmballdontlie.ui.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dadmballdontlie.viewmodels.SharedViewModel;
import com.example.dadmballdontlie.viewmodels.SharedViewModelFactory;
import com.example.dadmballdontlie.adapter.PlayerList;
import com.example.dadmballdontlie.data.model.Player;
import com.example.dadmballdontlie.data.model.Team;
import com.example.dadmballdontlie.databinding.FragmentPlayerSearchBinding;

import java.util.ArrayList;
import java.util.List;

public class PlayerSearchFragment extends Fragment {

    private FragmentPlayerSearchBinding binding;
    private SharedViewModel sharedViewModel;
    private PlayerList adapter;

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
        recyclerView.addItemDecoration(itemDecoration);

        List<Player> list = fakeLists();
        adapter = new PlayerList(list);
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private List<Player> fakeLists(){
        Player player;
        List<Player> list = new ArrayList<>();
        Team team = new Team(1,"mh","Miami","Northwest","Northwest", "Miami Heats", "Miami Heats");

        for(int i = 0; i < 15; i++){
            player = new Player(i,"Nombre" + i, "Apellido", "Posicion", 12, 12, 12, team);
            list.add(player);
        }

        return list;
    }
}