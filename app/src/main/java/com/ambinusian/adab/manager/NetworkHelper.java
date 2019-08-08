package com.ambinusian.adab.manager;

import java.util.HashMap;

public class NetworkHelper {

    public interface authenticateUser {
        void onResponse(Boolean success, HashMap<String, String> userProfile);
    }

}
