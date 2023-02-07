package org.overlake.mat803.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CountFragment extends Fragment {

    public static final String COUNT = "count";
    private int mCount;
    private TextView mCountView;
    public static final String INITIAL_COUNT = "initial_count";
    public static final String REQUEST_KEY = "request_key";

    public static CountFragment newInstance(int initialCount) {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCount = getArguments().getInt(INITIAL_COUNT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_count, container, false);
        mCountView = view.findViewById(R.id.count_view);
        view.findViewById(R.id.increment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increment();
            }
        });
        updateView();
        return view;
    }

    public void increment() {
        mCount++;
        updateView();
        Bundle bundle = new Bundle();
        bundle.putInt(COUNT, mCount);
        getParentFragmentManager().setFragmentResult(REQUEST_KEY, bundle);
    }

    public void updateView() {
        mCountView.setText(Integer.toString(mCount));
    }
}
