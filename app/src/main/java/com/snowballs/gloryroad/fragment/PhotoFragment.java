package com.snowballs.gloryroad.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.snowballs.gloryroad.R;
import com.squareup.picasso.Picasso;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhotoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhotoFragment extends Fragment {

    private static int LOAD_RESULT = 1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button mBackButton;
    private Button mNextButton;
    private Button mLoadButton;
    private Button mColorizeButton;
    private ImageView mPhoto;
    private ProgressBar mLoading;

    public PhotoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhotoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PhotoFragment newInstance(String param1, String param2) {
        PhotoFragment fragment = new PhotoFragment();
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

        View view = inflater.inflate(R.layout.fragment_photo, container, false);

        mLoading = view.findViewById(R.id.loading);

        mPhoto = view.findViewById(R.id.ivPhoto);

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

        mLoadButton = view.findViewById(R.id.btnLoad);
        mLoadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPhoto();
            }
        });

        mColorizeButton = view.findViewById(R.id.btnColorize);
        mColorizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorize();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((resultCode == RESULT_OK) && (requestCode == LOAD_RESULT)) {
            Picasso.with(this.getContext()).load(data.getData()).noPlaceholder().centerCrop().fit().into(mPhoto);
        }
    }

    private void colorize() {
        mLoading.setVisibility(View.VISIBLE);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                colorizationCallback();
            }
        }, 2000);
    }

    private void colorizationCallback() {
        mLoading.setVisibility(View.GONE);
    }

    private void loadPhoto() {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, LOAD_RESULT);
    }

    private void back(View v) {
        NavDirections action = PhotoFragmentDirections.actionPhotoFragmentToChooseFragment("", "", "", "");
        Navigation.findNavController(v).navigate(action);
    }

    private void next(View v) {
        NavDirections action = PhotoFragmentDirections.actionPhotoFragmentToShowFragment();
        Navigation.findNavController(v).navigate(action);
    }
}
