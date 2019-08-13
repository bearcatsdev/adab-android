package com.ambinusian.adab.manager;

import android.content.Context;
import android.util.Log;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class APIManager {
    private Context context;
    private String TAG = "APIManager";

    public APIManager(Context context) {
        this.context = context;
    }

    public void authenticateUser(String username, String password, NetworkHelper.authenticateUser callback) {
        String API_PATH = "/user/login";

        NetworkManager networkManager = new NetworkManager(context);

        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);

        JSONObject jsonBody = new JSONObject(params);

        networkManager.doPostRequest(jsonBody, API_PATH, (response -> {
            // OK
            Log.d(TAG, response.toString());
            if (response.getString("status").equals("200")) {
                JSONObject userJson = response.getJSONObject("response");
                Map<String, Object> userProfile = JsonHelper.toMap(userJson);

                callback.onResponse(true, userProfile);

            } else {
                // Not OK

                if (response.getString("status").equals("403")) {
                    // unauthorized
                    callback.onError(403, "Unauthorized");

                } else if (response.getString("status").equals("400")) {
                    // server error
                    callback.onError(response.getJSONObject("reason").getInt("error_code"),
                            response.getJSONObject("reason").getString("error_message"));
                }

                callback.onResponse(false, null);
            }
        }));
    }

}
