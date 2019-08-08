package com.ambinusian.adab.manager;

import android.content.Context;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class APIManager {
    private Context context;

    public APIManager(Context context) {
        this.context = context;
    }

    public void authenticateUser(String username, String password, NetworkHelper.authenticateUser callback) {
        String API_PATH = "/api/user/login";

        NetworkManager networkManager = new NetworkManager(context);

        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);

        JSONObject jsonBody = new JSONObject(params);

        networkManager.doPostRequest(jsonBody, API_PATH, (response -> {
            // OK
            if (response.getString("status").equals("200")) {
                Map<String, Object> userProfile = new HashMap<>();
                JSONObject userJson = response.getJSONObject("response");

                userProfile.put("username", userJson.getString("username"));
                userProfile.put("name", userJson.getString("name"));
                userProfile.put("email", userJson.getString("email"));
                userProfile.put("isLecturer", userJson.getBoolean("lecturer"));
                userProfile.put("picture", userJson.getString("picture"));

                callback.onResponse(true, userProfile);

            } else {
                // Not OK
                
                callback.onResponse(false, null);
            }
        }));
    }

}
