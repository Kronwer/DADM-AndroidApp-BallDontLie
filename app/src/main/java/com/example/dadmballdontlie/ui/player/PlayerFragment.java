package com.example.dadmballdontlie.ui.player;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

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

    private PlayerAdapter adapter;
    private Stat stat = new Stat();

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

        binding.textViewStatMin.setText("0");
        binding.textViewStatPts.setText("0");
        binding.textViewStatAst.setText("0");
        binding.textViewStatStl.setText("0");
        binding.textViewStatTurnover.setText("0");
        binding.textViewStatBlk.setText("0");
        binding.textViewStatReb.setText("0");
        binding.textViewStatOReb.setText("0");
        binding.textViewStatDReb.setText("0");
        binding.textViewStatPf.setText("0");
        binding.textViewStatFga.setText("0");
        binding.textViewStatFgm.setText("0");
        binding.textViewStatFgpct.setText("0");
        binding.textViewStatFg3a.setText("0");
        binding.textViewStatFg3m.setText("0");
        binding.textViewStatFg3pct.setText("0");
        binding.textViewStatFta.setText("0");
        binding.textViewStatFtm.setText("0");
        binding.textViewStatFtpct.setText("0");
        StringBuilder glossary1 = new StringBuilder();
        glossary1.append(getString(R.string.stat_min) + "=" + getString(R.string.stat_min_def) + "\n");
        glossary1.append(getString(R.string.stat_pts) + " = " + getString(R.string.stat_pts_def) + "\n");
        glossary1.append(getString(R.string.stat_ast) + "=" + getString(R.string.stat_ast_def) + "\n");
        glossary1.append(getString(R.string.stat_stl) + "=" + getString(R.string.stat_stl_def) + "\n");
        glossary1.append(getString(R.string.stat_turnover) + "=" + getString(R.string.stat_turnover_def) + "\n");
        glossary1.append(getString(R.string.stat_blk) + "=" + getString(R.string.stat_blk_def) + "\n");
        glossary1.append(getString(R.string.stat_reb) + "=" + getString(R.string.stat_reb_def) + "\n");
        glossary1.append(getString(R.string.stat_oreb) + "=" + getString(R.string.stat_oreb_def) + "\n");
        glossary1.append(getString(R.string.stat_dreb) + "=" + getString(R.string.stat_dreb_def) + "\n");
        glossary1.append(getString(R.string.stat_pf) + "=" + getString(R.string.stat_pf_def) + "\n");
        binding.textViewGlossary1.setText(glossary1);

        binding.buttonVisibility1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.textViewGlossary1.getVisibility() == View.INVISIBLE) binding.textViewGlossary1.setVisibility(View.VISIBLE);
                else if (binding.textViewGlossary1.getVisibility() == View.VISIBLE) binding.textViewGlossary1.setVisibility(View.INVISIBLE);
            }
        });

        StringBuilder glossary2 = new StringBuilder();
        glossary2.append(getString(R.string.stat_fga) + "=" + getString(R.string.stat_fga_def) + "\n");
        glossary2.append(getString(R.string.stat_fgm) + "=" + getString(R.string.stat_fgm_def) + "\n");
        glossary2.append(getString(R.string.stat_fg_pct) + "=" + getString(R.string.stat_fg_pct) + "\n");
        glossary2.append(getString(R.string.stat_fg3a) + "=" + getString(R.string.stat_fg3a_def) + "\n");
        glossary2.append(getString(R.string.stat_fg3m) + "=" + getString(R.string.stat_fg3m_def) + "\n");
        glossary2.append(getString(R.string.stat_fg3_pct) + "=" + getString(R.string.stat_fg3_pct_def) + "\n");
        glossary2.append(getString(R.string.stat_fta) + "=" + getString(R.string.stat_fta_def) + "\n");
        glossary2.append(getString(R.string.stat_ftm) + "=" + getString(R.string.stat_ftm_def) + "\n");
        glossary2.append(getString(R.string.stat_ft_pct) + "=" + getString(R.string.stat_ft_pct_def) + "\n");
        binding.textViewGlossary2.setText(glossary2);

        binding.buttonVisibility2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.textViewGlossary2.getVisibility() == View.INVISIBLE) binding.textViewGlossary2.setVisibility(View.VISIBLE);
                else if (binding.textViewGlossary2.getVisibility() == View.VISIBLE) binding.textViewGlossary2.setVisibility(View.INVISIBLE);
            }
        });

        sharedViewModel.stat.observe(getViewLifecycleOwner(), new Observer<Stat>() {
            @Override
            public void onChanged(Stat stat) {
                if (stat != null && stat.getSeason() == 2021){
                    setStat(stat);
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
                }else{
                    binding.textViewStatMin.setText("0");
                    binding.textViewStatPts.setText("0");
                    binding.textViewStatAst.setText("0");
                    binding.textViewStatStl.setText("0");
                    binding.textViewStatTurnover.setText("0");
                    binding.textViewStatBlk.setText("0");
                    binding.textViewStatReb.setText("0");
                    binding.textViewStatOReb.setText("0");
                    binding.textViewStatDReb.setText("0");
                    binding.textViewStatPf.setText("0");
                    binding.textViewStatFga.setText("0");
                    binding.textViewStatFgm.setText("0");
                    binding.textViewStatFgpct.setText("0");
                    binding.textViewStatFg3a.setText("0");
                    binding.textViewStatFg3m.setText("0");
                    binding.textViewStatFg3pct.setText("0");
                    binding.textViewStatFta.setText("0");
                    binding.textViewStatFtm.setText("0");
                    binding.textViewStatFtpct.setText("0");
                }
            }
        });

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

    private Stat getStat(){
        return stat;
    }

    private void setStat(Stat stat){
        this.stat = stat;
    }

}