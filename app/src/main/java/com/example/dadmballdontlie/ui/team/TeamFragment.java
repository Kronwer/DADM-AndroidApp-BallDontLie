package com.example.dadmballdontlie.ui.team;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dadmballdontlie.R;
import com.example.dadmballdontlie.adapter.PlayerAdapter;
import com.example.dadmballdontlie.adapter.TeamAdapter;
import com.example.dadmballdontlie.data.model.Player;
import com.example.dadmballdontlie.data.model.Team;
import com.example.dadmballdontlie.databinding.FragmentTeamBinding;
import com.example.dadmballdontlie.viewmodels.SharedViewModel;
import com.example.dadmballdontlie.viewmodels.SharedViewModelFactory;

import java.net.URLEncoder;

public class TeamFragment extends Fragment {

    private FragmentTeamBinding binding;
    private SharedViewModel sharedViewModel;
    private PlayerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTeamBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedViewModel = new ViewModelProvider(requireActivity(),
                new SharedViewModelFactory(requireActivity().getApplication())).get(SharedViewModel.class);

        binding.teamNameTextView.setText(getTeamFullName());
        binding.teamLogoImageView.setImageResource(getTeamLogo());
        binding.teamCityTextView.setText(getTeamCity());
        binding.teamConferenceTextView.setText(getString(R.string.team_conference,getTeamConference()));
        binding.teamDivisionTextView.setText(getString(R.string.team_division,getTeamDivision()));

        RecyclerView recyclerView = binding.playersRecyclerView;
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

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
                Navigation.findNavController(root).navigate(R.id.action_teamFragment_to_playerFragment, player.getBundle());
            }
        });

        recyclerView.setAdapter(adapter);

        adapter.updateList(sharedViewModel.getPlayersFromTeam(getTeamId()));

        return root;
    }

    private int getTeamId() { return getArguments().getInt("id"); }

    private String getTeamFullName() {
        return getArguments().getString("full_name");
    }
    
    private String getTeamName() {
        return getArguments().getString("name");
    }

    private String getTeamCity() {
        return getArguments().getString("city");
    }

    private String getTeamConference() {
        return getArguments().getString("conference");
    }

    private String getTeamDivision() {
        return getArguments().getString("division");
    }

    private int getTeamLogo() {
        return getArguments().getInt("team_logo");
    }


}