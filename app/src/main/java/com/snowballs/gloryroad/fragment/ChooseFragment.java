package com.snowballs.gloryroad.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.snowballs.gloryroad.R;
import com.snowballs.gloryroad.SoldierAdapter;
import com.snowballs.gloryroad.SoldierDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChooseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChooseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private static final SoldierDto s = new SoldierDto("Карлов Вениамин Петрович", "", "Московская обл., г.Иваново");
    private static final SoldierDto s2 = new SoldierDto("Карловиц Велемир Иванович", "", "Костромская обл., г.Кострома");
    private static final SoldierDto s3 = new SoldierDto("Карлин Витольд Всеволодович", "", "Иркутская обл., г.Зима");
    private static final SoldierDto s4 = new SoldierDto("Карлов Вениамин Петрович", "", "Московская обл., г.Иваново");
    private static final SoldierDto s5 = new SoldierDto("Карлов Вениамин Петрович", "", "Московская обл., г.Иваново");
    private static final SoldierDto s6 = new SoldierDto("Карлов Вениамин Петрович", "", "Московская обл., г.Иваново");
    private static final SoldierDto s7 = new SoldierDto("Карлов Вениамин Петрович", "", "Московская обл., г.Иваново");
    private static final SoldierDto s8 = new SoldierDto("Карлов Вениамин Петрович", "", "Московская обл., г.Иваново");
    private static final SoldierDto s9 = new SoldierDto("Карлов Вениамин Петрович", "", "Московская обл., г.Иваново");
    private static final SoldierDto s10 = new SoldierDto("Карлов Вениамин Петрович", "", "Московская обл., г.Иваново");
    private static final SoldierDto s11 = new SoldierDto("Карлов Вениамин Петрович", "", "Московская обл., г.Иваново");
    private static final SoldierDto s12 = new SoldierDto("Карлов Вениамин Петрович", "", "Московская обл., г.Иваново");

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ProgressBar mLoading;
    private androidx.recyclerview.widget.RecyclerView mRecyclerView;
    private SoldierAdapter adapter;
    private List<SoldierDto> soldierList = Arrays.asList(s, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12);

    private Button mBackButton;
    private Button mNextButton;
    private MaterialCheckBox cbMyData;
    private View overlay;

    public ChooseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChooseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChooseFragment newInstance(String param1, String param2) {
        ChooseFragment fragment = new ChooseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose, container, false);

        mLoading = view.findViewById(R.id.loading);
        mLoading.setVisibility(View.VISIBLE);

        overlay = view.findViewById(R.id.overlay);

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        adapter = new SoldierAdapter(soldierList);

        cbMyData = view.findViewById(R.id.cbMyData);
        cbMyData.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                overlay.setVisibility((isChecked) ? View.VISIBLE : View.GONE);
            }
        });

        mBackButton = view.findViewById(R.id.btnBack);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(v);
            }
        });

        mNextButton = view.findViewById(R.id.btnNext);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(v);
            }
        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                request();
            }
        }, 1000);

        return view;
    }

    private void request() {
        mLoading.setVisibility(View.INVISIBLE);
        mRecyclerView.setAdapter(adapter);
    }

    private void back(View v) {
        NavDirections action =
                ChooseFragmentDirections.actionChooseFragmentToSearchFragment();
        Navigation.findNavController(v).navigate(action);
    }

    private void next(View v) {
        NavDirections action =
                ChooseFragmentDirections.actionChooseFragmentToPhotoFragment();
        Navigation.findNavController(v).navigate(action);
    }
}
