package com.example.dadmballdontlie.ui.favs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dadmballdontlie.R;
import com.example.dadmballdontlie.adapter.PlayerAdapter;
import com.example.dadmballdontlie.data.model.Player;
import com.example.dadmballdontlie.databinding.FragmentPlayerFavsBinding;
import com.example.dadmballdontlie.viewmodels.SharedViewModel;
import com.example.dadmballdontlie.viewmodels.SharedViewModelFactory;

import java.net.URLEncoder;
import java.util.List;

public class PlayerFavsFragment extends Fragment {

    private FragmentPlayerFavsBinding binding;
    private SharedViewModel sharedViewModel;
    private PlayerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPlayerFavsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedViewModel = new ViewModelProvider(requireActivity(),
                new SharedViewModelFactory(requireActivity().getApplication())).get(SharedViewModel.class);

        RecyclerView recyclerView = binding.recyclerViewPlayerFav;
        RecyclerView.LayoutManager manager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);

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
                sharedViewModel.getStatFromCurrentSeason(player);
                Navigation.findNavController(root).navigate(R.id.action_navigation_favs_to_playerFragment, player.getBundle());
            }
        }, new PlayerAdapter.OnFavClickListener() {
            @Override
            public void onFavClick(Player player) {
                if(player.isFavourite()) {
                    sharedViewModel.removePlayerFromFavourites(player);
                } else {
                    sharedViewModel.savePlayerToFavourites(player);
                }
            }
        });

        recyclerView.setAdapter(adapter);

        sharedViewModel.listFavsPlayers.observe(getViewLifecycleOwner(), new Observer<List<Player>>() {
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