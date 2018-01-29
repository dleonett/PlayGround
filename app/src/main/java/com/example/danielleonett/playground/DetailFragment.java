package com.example.danielleonett.playground;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.danielleonett.playground.base.BaseFragment;
import com.example.danielleonett.playground.data.User;

public class DetailFragment extends BaseFragment {

    // Constants
    public static final String TAG = DetailFragment.class.getSimpleName();
    public static final String ARG_USER = "ARG_USER";

    // Views
    private TextView labelName;

    // Fields
    private OnFragmentInteractionListener interactionListener;
    private User user;

    public static DetailFragment newInstance(User user) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_USER, user);

        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            interactionListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement " + OnFragmentInteractionListener.class.getSimpleName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        interactionListener = null;
    }

    @Override
    protected void initVars() {
        user = (User) getArgument(ARG_USER);
    }

    @Override
    protected void bindViews(View view) {
        labelName = view.findViewById(R.id.labelName);
    }

    @Override
    protected void useViews() {
        labelName.setText(user.getName());
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }
}