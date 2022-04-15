package com.example.dadmballdontlie.ui.search;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import android.widget.Toast;

import com.example.dadmballdontlie.R;
import com.example.dadmballdontlie.repositories.NbaRepository;
import com.example.dadmballdontlie.repositories.NbaRepositoryImpl;
import com.example.dadmballdontlie.R;
import com.example.dadmballdontlie.viewmodels.SharedViewModel;
import com.example.dadmballdontlie.viewmodels.SharedViewModelFactory;
import com.example.dadmballdontlie.adapter.PlayerAdapter;
import com.example.dadmballdontlie.data.model.Player;
import com.example.dadmballdontlie.databinding.FragmentPlayerSearchBinding;

import java.net.URLEncoder;
import java.util.ArrayList;
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
        }, new PlayerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Player player) {
                Bundle bundle = new Bundle();
                bundle.putString("first_name", player.getFirst_name());
                bundle.putString("last_name", player.getLast_name());
                bundle.putString("position", player.getPosition());
                bundle.putInt("height_feet", player.getHeight_feet());
                bundle.putInt("height_inches", player.getHeight_inches());
                bundle.putInt("weight_pounds", player.getWeight_pounds());
                bundle.putString("team_name", player.getTeam().getName());
                Navigation.findNavController(root).navigate(R.id.action_navigation_search_to_playerFragment, bundle);
            }
        });
        
        recyclerView.setAdapter(adapter);

        // Expand the ViewSearch
        binding.inputSearch.onActionViewExpanded();

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