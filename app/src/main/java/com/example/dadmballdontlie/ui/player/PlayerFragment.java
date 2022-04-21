package com.example.dadmballdontlie.ui.player;

import android.os.Bundle;

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

    private PlayerAdapter adapter;
    private Stat stat = new Stat();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPlayerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedViewModel = new ViewModelProvider(requireActivity(),
                new SharedViewModelFactory(requireActivity().getApplication())).get(SharedViewModel.class);

        String position = changePosition(getPlayerPosition());

        binding.nameTextView.setText(getPlayerName());
        binding.positionTextView.setText(position);
        binding.heightTextView.setText(getPlayerHeight() + "m");
        binding.weightTextView.setText(getPlayerWeight() + "kg");
        binding.teamTextView.setText(getPlayerTeam());
        loadTeamImage(binding, getPlayerTeam());  // Añadir icono del equipo


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

    private String changePosition(String pos){
        String aux = "-";

        switch (pos){
            case "C":
               aux =  getString(R.string.position_C);
                break;
            case "F":
                aux = getString(R.string.position_F);
                break;
            case "G":
                aux = getString(R.string.position_G);
                break;
        }
        return aux;
    }

    private void loadTeamImage(FragmentPlayerBinding binding,String team){
        switch (team){
            case "Bucks": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_bucks)); break;
            case "Bulls": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_bulls)); break;
            case "Cavaliers": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_cavaliers)); break;
            case "Celtics": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_celtics)); break;
            case "Clippers": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_clippers)); break;
            case "Grizzlies": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_grizzlies)); break;
            case "Hawks": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_hawks)); break;
            case "Heat": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_heat)); break;
            case "Hornets": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_hornets)); break;
            case "Jazz": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_jazz)); break;
            case "Kings": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_kings)); break;
            case "Knicks": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_knicks)); break;
            case "Lakers": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_lakers)); break;
            case "Magic": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_magic));  break;
            case "Mavericks": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_maverick)); break;
            case "Nets": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_nets)); break;
            case "Nuggets": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_nuggets)); break;
            case "Pacers": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_pacers)); break;
            case "Pelicans": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_pelicans)); break;
            case "Pistons": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_pistons)); break;
            case "Raptors": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_raptors)); break;
            case "Rockets": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_rockets)); break;
            case "76ers": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_sixers)); break;
            case "Spurs": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_spurs)); break;
            case "Suns":  binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_suns)); break;
            case "Thunder": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_thunders)); break;
            case "Timberwolves": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_timberwolves)); break;
            case "Trail Blazers": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_trailblazers)); break;
            case "Warriors": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_warriors)); break;
            case "Wizards": binding.imageViewPlayer.setImageDrawable(getResources().getDrawable(R.drawable.ic_wizards)); break;
        }
    }



}