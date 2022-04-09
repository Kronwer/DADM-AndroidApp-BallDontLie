package com.example.dadmballdontlie.ui.search;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.example.dadmballdontlie.R;
import com.example.dadmballdontlie.repositories.NbaRepository;
import com.example.dadmballdontlie.repositories.NbaRepositoryImpl;
import com.example.dadmballdontlie.viewmodels.SharedViewModel;
import com.example.dadmballdontlie.viewmodels.SharedViewModelFactory;
import com.example.dadmballdontlie.adapter.PlayerAdapter;
import com.example.dadmballdontlie.data.model.Player;
import com.example.dadmballdontlie.data.model.Team;
import com.example.dadmballdontlie.databinding.FragmentPlayerSearchBinding;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class PlayerSearchFragment extends Fragment {

    private FragmentPlayerSearchBinding binding;
    private SharedViewModel sharedViewModel;
    private PlayerAdapter adapter;
    private NbaRepository nbaRepository;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPlayerSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedViewModel = new ViewModelProvider(requireActivity(),
                new SharedViewModelFactory(requireActivity().getApplication())).get(SharedViewModel.class);

        nbaRepository = new NbaRepositoryImpl(getContext());

        RecyclerView recyclerView = binding.recyclerViewPlayerSearch;
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);

        recyclerView.setLayoutManager(manager);
        //Add a separation line between items
        //recyclerView.addItemDecoration(itemDecoration);

        adapter = new PlayerAdapter(new PlayerAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(Player player) {
                try {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    String fullName = player.getFirst_name() + "_" + player.getLast_name();
                    fullName = URLEncoder.encode(fullName, "UTF-8");
                    intent.setData(Uri.parse("https://en.wikipedia.org/wiki/Special:Search?search="
                            + fullName));
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getContext(),
                            R.string.search_error_message, Toast.LENGTH_SHORT).show();
                }
            }
        });
        recyclerView.setAdapter(adapter);

        LiveData<List<Player>> list = nbaRepository.getAllPlayers();
        list.observe(getViewLifecycleOwner(), new Observer<List<Player>>() {
            @Override
            public void onChanged(List<Player> players) {
                adapter.updateList(players);
            }
        });

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