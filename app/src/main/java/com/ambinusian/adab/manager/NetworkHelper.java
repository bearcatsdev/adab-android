package com.ambinusian.adab.manager;

import java.util.Map;

public class NetworkHelper {

    public interface authenticateUser {
        void onResponse(Boolean success, Map<String, Object> userProfile);
        void onError(int errorCode, String errorReason);
    }

    public interface getUserClasses {
        void onResponse(Boolean success, Map<String, Object>[] userClasses);
        void onError(int errorCode, String errorReason);
    }

    public interface getUserProfile {
        void onResponse(Boolean success, Map<String, Object> userProfile);
        void onError(int errorCode, String errorReason);
    }

}
