package com.example.bookmytrip.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.bookmytrip.EssentialServices;
import com.example.bookmytrip.ExpressTrainShedule;
import com.example.bookmytrip.Helpline;
import com.example.bookmytrip.PNRStatus;
import com.example.bookmytrip.R;
import com.example.bookmytrip.SearchStation;
import com.example.bookmytrip.Shedule;
import com.example.bookmytrip.ShowTKTValue;
import com.example.bookmytrip.TrainTktOne;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private LinearLayout train_ticket_layout;
    private LinearLayout trainStation;
    private LinearLayout Express, helpline, pnrstatus, essential_services, searchStation, bookingHistory;
    private AdView mAdview;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        train_ticket_layout = (LinearLayout) root.findViewById(R.id.train_ticket_layout);
        trainStation = (LinearLayout) root.findViewById(R.id.trainStation);
        Express = (LinearLayout) root.findViewById(R.id.Express);
        helpline= (LinearLayout) root.findViewById(R.id.Helpline) ;
        pnrstatus = (LinearLayout) root.findViewById(R.id.pnr);
        essential_services = (LinearLayout) root.findViewById(R.id.essential_services);
        searchStation = (LinearLayout) root.findViewById(R.id.searchStation);
        bookingHistory = (LinearLayout) root.findViewById(R.id.booking_History);


        trainStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Shedule.class);
                startActivity(intent);
            }
        });

        train_ticket_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TrainTktOne.class);
                startActivity(intent);
            }
        });

        helpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Helpline.class);
                startActivity(intent);
            }
        });

        Express.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ExpressTrainShedule.class);
                startActivity(intent);
            }
        });

        pnrstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PNRStatus.class);
                startActivity(intent);
            }
        });

        essential_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EssentialServices.class);
                startActivity(intent);
            }
        });



        searchStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchStation.class);
                startActivity(intent);

            }
        });

        bookingHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShowTKTValue.class);
                startActivity(intent);
            }
        });

        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = new AdView(getActivity());
        AdSize adSize = new AdSize(320, 100);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");

        mAdview =(AdView)root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);

        mAdview.setAdListener(new AdListener() {
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
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

        return root;
    }

}