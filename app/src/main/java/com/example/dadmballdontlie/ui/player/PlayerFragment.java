package com.example.dadmballdontlie.ui.player;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dadmballdontlie.R;
import com.example.dadmballdontlie.databinding.FragmentPlayerBinding;
import com.example.dadmballdontlie.viewmodels.SharedViewModel;
import com.example.dadmballdontlie.viewmodels.SharedViewModelFactory;

public class PlayerFragment extends Fragment {

    static final double FEET_TO_M = 0.3048;
    static final double INCHES_TO_M = 0.0254;
    static final double POUND_TO_KG = 0.453592;

    private FragmentPlayerBinding binding;
    private SharedViewModel sharedViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPlayerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedViewModel = new ViewModelProvider(requireActivity(),
                new SharedViewModelFactory(requireActivity().getApplication())).get(SharedViewModel.class);

        binding.nameTextView.setText(getPlayerName());
        binding.positionTextView.setText(getPlayerPosition());
        binding.heightTextView.setText(getPlayerHeight());
        binding.weightTextView.setText(getPlayerWeight());
        binding.teamTextView.setText(getPlayerTeam());

        return root;

    }

    private String getPlayerName() {
        String firstName = getArguments().getString("first_name");
        String lastName = getArguments().getString("last_name");
        return firstName + " " + lastName;
    }

    private String getPlayerPosition() {
        return getArguments().getString("position");
    }

    private String getPlayerHeight() {
        double heightFeet = getArguments().getInt("height_feet") * FEET_TO_M;
        double heightInches = getArguments().getInt("height_inches") * INCHES_TO_M;
        return String.format("%.2f", heightFeet + heightInches);
    }

    private String getPlayerWeight() {
        double weightPounds = getArguments().getInt("weight_pounds") * POUND_TO_KG;
        return String.format("%.2f", weightPounds);
    }

    private String getPlayerTeam() {
        String team = getArguments().getString("team_name");
        return team;
    }

}