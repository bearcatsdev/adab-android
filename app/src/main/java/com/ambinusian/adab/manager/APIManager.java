package com.ambinusian.adab.manager;

import android.content.Context;
import android.content.Intent;
import android.net.Network;
import android.util.Log;
import android.widget.Toast;
import com.ambinusian.adab.R;
import com.ambinusian.adab.preferences.UserPreferences;
import com.ambinusian.adab.ui.login.LoginActivity;
import org.json.JSONArray;
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

    public void getUserClasses(String tokenId, NetworkHelper.getUserClasses callback) {
        String API_PATH = "/user/class";

        NetworkManager networkManager = new NetworkManager(context);

        HashMap<String, String> params = new HashMap<>();
        params.put("token_id", tokenId);

        JSONObject jsonBody = new JSONObject(params);

        networkManager.doPostRequest(jsonBody, API_PATH, (response -> {
            // OK
            Log.d(TAG, response.toString());
            if (response.getString("status").equals("200")) {
                JSONArray userClassesJson = response.getJSONArray("response");
                Map<String, Object>[] userClasses = new HashMap[userClassesJson.length()];

                for (int x=0; x<userClassesJson.length(); ++x) {
                    userClasses[x] = JsonHelper.toMap(userClassesJson.getJSONObject(x));
                }

                callback.onResponse(true, userClasses);

            } else {
                // Not OK

                if (response.getString("status").equals("403")) {
                    // unauthorized, tell user to re-login
                    callback.onError(403, "Unauthorized");
                    UserPreferences userPreferences = new UserPreferences(context);
                    userPreferences.clearLoggedInUser();
                    Toast.makeText(context, context.getString(R.string.session_expired), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);

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
