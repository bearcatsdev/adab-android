package com.ambinusian.adab.manager;

import java.util.Map;

public class NetworkHelper {

    public interface authenticateUser {
        void onResponse(Boolean success, Map<String, Object> userProfile);
    }

}
