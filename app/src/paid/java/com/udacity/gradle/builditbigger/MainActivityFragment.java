package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.displaydadjoke.FullscreenDadJoke;
import com.example.javadadjokes.DadJokes;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {

    private Button mLoadJokeBtn;
    private ProgressBar loadingCircle;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        //declare and hide loading circle
        loadingCircle = root.findViewById(R.id.progressBar);
        loadingCircle.setVisibility(View.GONE);

        mLoadJokeBtn = root.findViewById(R.id.tell_joke_btn);
        mLoadJokeBtn.setOnClickListener(this);

        return root;
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.tell_joke_btn:
                //set loading circle to display while we wait for AsyncTask to finish
                loadingCircle.setVisibility(View.VISIBLE);
                //start loading of joke via a background thread b/c it is a network request
                //and network requests should always take place outside of the main thread
                EndpointsAsyncTask task = new EndpointsAsyncTask(getActivity());
                task.execute();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadingCircle.setVisibility(View.GONE);
    }
}
