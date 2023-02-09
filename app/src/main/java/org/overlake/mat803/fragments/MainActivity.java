package org.overlake.mat803.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.overlake.mat803.fragments.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    TextView mCountPhrase;
    public static final int INITIAL_COUNT = 5;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        mCountPhrase = binding.countPhrase;
        mFragmentManager = getSupportFragmentManager();
        CountFragment fragment = CountFragment.newInstance(INITIAL_COUNT);
        updateView(INITIAL_COUNT);

        mFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, fragment)
                .addToBackStack(null)
                .commit();


        mFragmentManager.setFragmentResultListener(CountFragment.REQUEST_KEY, this, new FragmentResultListener() {
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

    public void next(View view) {
        CountFragment fragment = CountFragment.newInstance();
        mFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .addToBackStack(null)
                .commit();
    }
}