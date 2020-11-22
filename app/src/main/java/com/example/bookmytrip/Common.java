package com.example.bookmytrip;

import com.example.bookmytrip.Remote.RetrofitClient;
import com.example.bookmytrip.Remote.iGoogleAPIService;

class Common {
    private static final String GOOGLE_API_URL ="https://maps.googleapis.com/";
    static iGoogleAPIService getGoogleAPIService(){
        return RetrofitClient.getClient(GOOGLE_API_URL).create(iGoogleAPIService.class);

    }
}
