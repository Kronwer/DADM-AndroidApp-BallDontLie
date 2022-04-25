package com.example.dadmballdontlie.ui.favs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
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
import com.example.dadmballdontlie.adapter.TeamAdapter;
import com.example.dadmballdontlie.adapter.TeamFavsAdapter;
import com.example.dadmballdontlie.data.model.Team;
import com.example.dadmballdontlie.databinding.FragmentTeamFavsBinding;
import com.example.dadmballdontlie.databinding.FragmentTeamSearchBinding;
import com.example.dadmballdontlie.viewmodels.SharedViewModel;
import com.example.dadmballdontlie.viewmodels.SharedViewModelFactory;

import java.net.URLEncoder;
import java.util.List;

public class TeamFavsFragment extends Fragment {

    private FragmentTeamFavsBinding binding;
    private SharedViewModel sharedViewModel;
    private TeamFavsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTeamFavsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedViewModel = new ViewModelProvider(requireActivity(),
                new SharedViewModelFactory(requireActivity().getApplication())).get(SharedViewModel.class);

        RecyclerView recyclerView = binding.recyclerViewTeamFav;
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);

        recyclerView.setLayoutManager(manager);

        adapter = new TeamFavsAdapter(new TeamFavsAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(Team team) {
                try {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    String teamName = team.getName();
                    teamName = URLEncoder.encode(teamName, "UTF-8");
                    teamName = teamName.toLowerCase();
                    if (teamName.equals("76ers")) {
                        teamName = "sixers";
                    }
                    intent.setData(Uri.parse("https://es.global.nba.com/teams/#!/"
                            + teamName));
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getContext(),
                            R.string.search_error_message, Toast.LENGTH_SHORT).show();
                }
            }
        }, new TeamFavsAdapter.OnRemoveFavClickListener() {
            @Override
            public void onRemoveClick(Team team) {
                sharedViewModel.removeTeamFromFavourites(team);
            }
        });

        recyclerView.setAdapter(adapter);

        sharedViewModel.listFavsTeams.observe(getViewLifecycleOwner(), new Observer<List<Team>>() {
            @Override
            public void onChanged(List<Team> teams) {
                if (teams != null) {
                    adapter.updateList(teams);
                    adapter.loadTeamImages(getContext());
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