package org.overlake.mat803.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mCountPhrase;
    public static final int INITIAL_COUNT = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCountPhrase = findViewById(R.id.count_phrase);
        FragmentManager fm = getSupportFragmentManager();
        CountFragment fragment = CountFragment.newInstance(INITIAL_COUNT);
        Bundle bundle = new Bundle();
        bundle.putInt(CountFragment.INITIAL_COUNT, INITIAL_COUNT);
        updateView(INITIAL_COUNT);

        fm.beginTransaction()
                .add(R.id.fragmentContainerView, CountFragment.class, bundle)
                .commit();


        fm.setFragmentResultListener(CountFragment.REQUEST_KEY, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                updateView(result.getInt(CountFragment.COUNT));
            }
        });
    }

    private void updateView(int initialCount) {
        String msg = getResources().getString(R.string.count_phrase, initialCount);
        mCountPhrase.setText(msg);
    }
}