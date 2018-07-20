package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {

    public MainActivityFragment() {
    }

    private InterstitialAd mInterstitialAd;
    private Button mLoadJokeBtn;
    private ProgressBar loadingCircle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Context mContext = getActivity();

        //declare and hide loading circle
        loadingCircle = root.findViewById(R.id.progressBar);
        loadingCircle.setVisibility(View.GONE);


        MobileAds.initialize(mContext, getString(R.string.interstitial_ad_initialize_key));
        mInterstitialAd = new InterstitialAd(mContext);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
              @Override
              public void onAdLoaded() {
                  // Code to be executed when an ad finishes loading.
              }
              @Override
              public void onAdFailedToLoad(int errorCode) {
                  // Code to be executed when an ad request fails.
              }
              @Override
              public void onAdOpened() {
                  // Code to be executed when the ad is displayed.
              }
              @Override
              public void onAdLeftApplication() {
                  // Code to be executed when the user has left the app.
              }
              @Override
              public void onAdClosed() {
                  // Code to be executed when when the interstitial ad is closed.
                  EndpointsAsyncTask task = new EndpointsAsyncTask(getActivity());
                  task.execute();
              }
          });

        mLoadJokeBtn = root.findViewById(R.id.tell_joke_btn);
        mLoadJokeBtn.setOnClickListener(this);

        if (root.findViewById(R.id.adView) != null) {
            AdView mAdView = (AdView) root.findViewById(R.id.adView);
            // Create an ad request. Check logcat output for the hashed device ID to
            // get test ads on a physical device. e.g.
            // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            mAdView.loadAd(adRequest);
        }
        return root;
    }

    @Override
    public void onClick(View v) {
        loadingCircle.setVisibility(View.VISIBLE);
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
//                Log.d("TAG", "The interstitial wasn't loaded yet.");
                EndpointsAsyncTask task = new EndpointsAsyncTask(getActivity());
                task.execute();
            }
        }

    @Override
    public void onResume() {
        super.onResume();
        loadingCircle.setVisibility(View.GONE);
    }

}

