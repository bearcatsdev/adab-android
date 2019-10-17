package com.ambinusian.adab.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import com.ambinusian.adab.R;
import com.ambinusian.adab.preferences.UserPreferences;
import com.ambinusian.adab.ui.login.ActivityLogin;
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
        String API_PATH = "api/v1/login";

        NetworkManager networkManager = new NetworkManager(context);

        HashMap<String, String> params = new HashMap<>();
        params.put("user_email", username);
        params.put("user_password", password);

        JSONObject jsonBody = new JSONObject(params);

        networkManager.doPostRequest(context, "", jsonBody, API_PATH, (response -> {
            // OK
            Log.d(TAG, response.toString());
            if (response.getString("status").equals("200")) {
                JSONObject userJson = response.getJSONObject("values");
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
        String API_PATH = "api/v1/user/sessions";

        NetworkManager networkManager = new NetworkManager(context);

        networkManager.doGetRequest(context, tokenId, API_PATH, (response -> {
            // OK
            Log.d(TAG, response.toString());
            if (response.getString("status").equals("200")) {
                JSONArray userClassesJson = response.getJSONArray("values");
                Map<String, Object>[] userClasses = new HashMap[userClassesJson.length()];

                for (int x=0; x<userClassesJson.length(); ++x) {
                    userClasses[x] = JsonHelper.toMap(userClassesJson.getJSONObject(x));
                }

                callback.onResponse(true, userClasses);

            } else {
                // Not OK

                if (response.getString("status").equals("403")) {
                    // unauthorized, tell user to re-login
                    reLoginUser(context);
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

    public void getUserProfile(String tokenId, NetworkHelper.getUserProfile callback) {
        String API_PATH = "api/v1/user/profile";

        NetworkManager networkManager = new NetworkManager(context);

        networkManager.doGetRequest(context, tokenId, API_PATH, (response -> {
            // OK
            Log.d(TAG, response.toString());
            if (response.getString("status").equals("200")) {
                JSONObject userJson = response.getJSONObject("values");
                Map<String, Object> userProfile = JsonHelper.toMap(userJson);

                callback.onResponse(true, userProfile);

            } else {
                // Not OK

                if (response.getString("status").equals("403")) {
                    // unauthorized, tell user to re-login
                    reLoginUser(context);
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

    public void getClassDetails(String tokenId, int sessionId, NetworkHelper.getClassDetails callback) {
        String API_PATH = "api/v1/session/details";

        NetworkManager networkManager = new NetworkManager(context);

        HashMap<String, Object> params = new HashMap<>();
        params.put("session_id", sessionId);

        JSONObject jsonBody = new JSONObject(params);

        networkManager.doPostRequest(context, tokenId, jsonBody, API_PATH, (response -> {
            // OK
            Log.d(TAG, response.toString());
            if (response.getString("status").equals("200")) {
                JSONObject userJson = response.getJSONObject("values");
                Map<String, Object> classDetails = JsonHelper.toMap(userJson);

                callback.onResponse(true, classDetails);

            } else {
                // Not OK

                if (response.getString("status").equals("403")) {
                    // unauthorized, tell user to re-login
                    reLoginUser(context);
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

    public void endSession(String tokenId, int transactionId, NetworkHelper.endSession callback) {
        String API_PATH = "/lecturer/endSession";

        NetworkManager networkManager = new NetworkManager(context);

        HashMap<String, Object> params = new HashMap<>();
        params.put("token_id", tokenId);
        params.put("transaction_id", transactionId);

        JSONObject jsonBody = new JSONObject(params);

        networkManager.doPostRequest(context, tokenId, jsonBody, API_PATH, (response -> {
            // OK
            Log.d(TAG, response.toString());
            if (response.getString("status").equals("200")) {
                callback.onResponse(true, null);

            } else {
                // Not OK

                if (response.getString("status").equals("403")) {
                    // unauthorized, tell user to re-login
                    reLoginUser(context);
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

    private void reLoginUser(Context context) {
        UserPreferences userPreferences = new UserPreferences(context);
        userPreferences.clearLoggedInUser();
        Toast.makeText(context, context.getString(R.string.session_expired), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context, ActivityLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
        ActivityCompat.finishAffinity((Activity) context);
    }

}
