package com.example.danielleonett.playground;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.danielleonett.playground.base.BaseActivity;
import com.example.danielleonett.playground.data.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends BaseActivity implements
        MySampleInterface,
        MyAnotherSampleInterface,
        MyYetAnotherInterface {

    // Constants
    public static final String TAG = DetailActivity.class.getSimpleName();
    public static final String ARG_TITLE = "ARG_TITLE";
    public static final String ARG_USER = "ARG_USER";

    // Views
    @BindView(R.id.labelTitle)
    TextView labelTitle;

    // Fields
    private String title;
    private User user;

    public static Intent newIntent(Context context, Bundle args) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtras(args);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initViews();
    }

    @Override
    protected void initVars() {
        title = (String) getArgument(ARG_TITLE);
        user = (User) getArgument(ARG_USER);
    }

    @Override
    protected void bindViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void useViews() {
        labelTitle.setText(String.format("%s, %s", title, user.getName()));
    }

    @Override
    public void mySampleMethod(int mySampleParam) {
        Log.d(TAG, "mySampleMethod()");
    }

    @OnClick(R.id.btnSendOkResult)
    public void onSendOkResultClicked() {
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, "Thanks");

        sendOkResultWithData(args);
    }

}
