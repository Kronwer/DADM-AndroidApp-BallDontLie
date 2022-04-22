package com.example.dadmballdontlie.ui.player;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dadmballdontlie.R;
import com.example.dadmballdontlie.adapter.PlayerAdapter;
import com.example.dadmballdontlie.data.model.Stat;
import com.example.dadmballdontlie.databinding.FragmentPlayerBinding;
import com.example.dadmballdontlie.viewmodels.SharedViewModel;
import com.example.dadmballdontlie.viewmodels.SharedViewModelFactory;

public class PlayerFragment extends Fragment {

    static final double FEET_TO_M = 0.3048;
    static final double INCHES_TO_M = 0.0254;
    static final double POUND_TO_KG = 0.453592;

    private FragmentPlayerBinding binding;
    private SharedViewModel sharedViewModel;

    private Stat stat = new Stat();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPlayerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedViewModel = new ViewModelProvider(requireActivity(),
                new SharedViewModelFactory(requireActivity().getApplication())).get(SharedViewModel.class);

        displayPlayerInfo();
        setListeners();
        setObservers();

        return root;

    }

    private void displayPlayerInfo() {

        // Set player name
        binding.nameTextView.setText(getPlayerName());

        // Set player position
        String position = changePosition(getPlayerPosition());
        if(position.equals("")) {
            binding.personIcon.setVisibility(View.GONE);
        }
        binding.positionTextView.setText(position);

        // Units to be used (ENG/INT)
        String unitSystem = getString(R.string.units);

        // Set player height
        binding.heightTextView.setText(getPlayerHeight(unitSystem));

        // Set player weight
        binding.weightTextView.setText(getPlayerWeight(unitSystem));

        // Set player's team logo
        binding.imageViewPlayer.setImageResource(getPlayerTeamLogo());

    }

    private void setListeners() {

        binding.glossaryButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Glossary glossaryFragment = new Glossary();
                glossaryFragment.show(requireActivity().getSupportFragmentManager(), getString(R.string.stat_glossary));
            }

        });

    }

    private void setObservers() {

        sharedViewModel.stat.observe(getViewLifecycleOwner(), new Observer<Stat>() {
            @Override
            public void onChanged(Stat stat) {
                if (stat != null && stat.getSeason() == 2021){
                    setStat(stat);
                    binding.seasonTextView.setText(getString(R.string.stat_season_stats,stat.getSeason()+""));
                    binding.textViewStatMin.setText(stat.getMinutes());
                    binding.textViewStatPts.setText(stat.getPoints()+"");
                    binding.textViewStatAst.setText(stat.getAssists()+"");
                    binding.textViewStatStl.setText(stat.getSteals()+"");
                    binding.textViewStatTurnover.setText(stat.getTurnover()+"");
                    binding.textViewStatBlk.setText(stat.getBlocks()+"");
                    binding.textViewStatReb.setText(stat.getRebounds()+"");
                    binding.textViewStatOReb.setText(stat.getOffensiveRebounds()+"");
                    binding.textViewStatDReb.setText(stat.getDefensiveRebounds()+"");
                    binding.textViewStatPf.setText(stat.getPersonalFaults()+"");
                    binding.textViewStatFga.setText(stat.getPointsAttempted()+"");
                    binding.textViewStatFgm.setText(stat.getPointsMade()+"");
                    binding.textViewStatFgpct.setText(stat.getPointsPercentage()+"");
                    binding.textViewStatFg3a.setText(stat.getPoints3Attempted()+"");
                    binding.textViewStatFg3m.setText(stat.getPoints3Made()+"");
                    binding.textViewStatFg3pct.setText(stat.getPoints3Percentage()+"");
                    binding.textViewStatFta.setText(stat.getFreeThrowsAttempted()+"");
                    binding.textViewStatFtm.setText(stat.getFreeThrowsMade()+"");
                    binding.textViewStatFtpct.setText(stat.getFreeThrowsPercentage()+"");
                } else {
                    binding.seasonTextView.setText(getString(R.string.stat_season_stats,""));
                    String defaultValue = getString(R.string.stat_default_value);
                    binding.textViewStatMin.setText(defaultValue);
                    binding.textViewStatPts.setText(defaultValue);
                    binding.textViewStatAst.setText(defaultValue);
                    binding.textViewStatStl.setText(defaultValue);
                    binding.textViewStatTurnover.setText(defaultValue);
                    binding.textViewStatBlk.setText(defaultValue);
                    binding.textViewStatReb.setText(defaultValue);
                    binding.textViewStatOReb.setText(defaultValue);
                    binding.textViewStatDReb.setText(defaultValue);
                    binding.textViewStatPf.setText(defaultValue);
                    binding.textViewStatFga.setText(defaultValue);
                    binding.textViewStatFgm.setText(defaultValue);
                    binding.textViewStatFgpct.setText(defaultValue);
                    binding.textViewStatFg3a.setText(defaultValue);
                    binding.textViewStatFg3m.setText(defaultValue);
                    binding.textViewStatFg3pct.setText(defaultValue);
                    binding.textViewStatFta.setText(defaultValue);
                    binding.textViewStatFtm.setText(defaultValue);
                    binding.textViewStatFtpct.setText(defaultValue);
                }
            }
        });

    }

    private String getPlayerName() {
        String firstName = getArguments().getString("first_name");
        String lastName = getArguments().getString("last_name");
        return firstName + " " + lastName;
    }

    private String getPlayerPosition() {
        return getArguments().getString("position");
    }

    private String getPlayerHeight(String unitSystem) {

        double heightFeet = getArguments().getInt("height_feet");
        double heightInches = getArguments().getInt("height_inches");

        if(unitSystem.equals("ENG")) { // English System (Feet + inches)

            return getString(R.string.height_unit_eng, String.format("%.0f", heightFeet), String.format("%.0f", heightInches));

        } else {

            heightFeet *= FEET_TO_M;
            heightInches *= INCHES_TO_M;

            return getString(R.string.height_unit_int, String.format("%.2f", heightFeet + heightInches));

        }

    }

    private String getPlayerWeight(String unitSystem) {

        double weightPounds = getArguments().getInt("weight_pounds");

        if(unitSystem.equals("ENG")) {

            return getString(R.string.weight_unit_eng, String.format("%.0f", weightPounds));

        } else {

            return getString(R.string.weight_unit_int, String.format("%.1f", weightPounds * POUND_TO_KG));

        }

    }

    private int getPlayerTeamLogo() {
        return getArguments().getBundle("team").getInt("team_logo");
    }

    private Stat getStat(){
        return stat;
    }

    private void setStat(Stat stat){
        this.stat = stat;
    }

    private String changePosition(String pos){

        switch (pos){
            case "C": return getString(R.string.position_C);
            case "F": return getString(R.string.position_F);
            case "G": return getString(R.string.position_G);
            default: return "";
        }

    }

    public static class Glossary extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Get the layout inflater
            LayoutInflater inflater = requireActivity().getLayoutInflater();

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(inflater.inflate(R.layout.fragment_glossary, null));

            return builder.create();
        }
    }

}