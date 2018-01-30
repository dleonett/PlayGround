package com.example.danielleonett.playground;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.danielleonett.playground.base.BaseActivity;
import com.example.danielleonett.playground.data.User;

public class MainActivity extends BaseActivity implements
        MySampleInterface,
        MyAnotherSampleInterface,
        MyYetAnotherInterface {

    // Constants
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String ARG_TITLE = "ARG_TITLE";

    // Views
    private TextView labelTitle;
    private EditText inputName;
    private Button btnSubmit;

    // Fields
    private ArrayAdapter<String> exampleArrayAdapter;

    public static Intent newIntent(Context context, Bundle args) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtras(args);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu()");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected()");
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    protected void initVars() {
        String someString = (String) getArgument(ARG_TITLE);

        exampleArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
    }

    @Override
    protected void bindViews() {
        labelTitle = findViewById(R.id.labelTitle);
        inputName = findViewById(R.id.inputName);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    @Override
    protected void useViews() {
        labelTitle.setText(getString(R.string.main_title));
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBtnSubmitClicked();
            }
        });
    }

    @Override
    public void mySampleMethod(int mySampleParam) {
        Log.d(TAG, "mySampleMethod()");
    }

    private void onBtnSubmitClicked() {
        User user = new User(inputName.getText().toString().trim(), 25);
        navigateToDetail(user);
    }

    private void navigateToDetail(User user) {
        Bundle args = new Bundle();
        args.putString(DetailActivity.ARG_TITLE, "My awesome title");
        args.putParcelable(DetailActivity.ARG_USER, user);

        startActivityForResult(DetailActivity.newIntent(this, args),
                DetailActivity.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case DetailActivity.REQUEST_CODE:
                handleDetailResult(resultCode, data);
                break;
        }
    }

    private void handleDetailResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String title = (String) getArgument(data, DetailActivity.ARG_TITLE);
            showToast(title);
        }
    }

}
