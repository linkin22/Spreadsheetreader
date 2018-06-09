package com.ramogi.app.spreadsheetreader;

import android.os.AsyncTask;
import android.util.Log;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.ramogi.app.spreadsheetreader.Constants.DEV1;
import static com.ramogi.app.spreadsheetreader.Constants.DEV1_APPLICATION_ID;
import static com.ramogi.app.spreadsheetreader.Constants.DEV1_REST_KEY;
import static com.ramogi.app.spreadsheetreader.Constants.DEV3;
import static com.ramogi.app.spreadsheetreader.Constants.DEV3_APPLICATION_ID;
import static com.ramogi.app.spreadsheetreader.Constants.DEV3_REST_KEY;
import static com.ramogi.app.spreadsheetreader.Constants.DEV4;
import static com.ramogi.app.spreadsheetreader.Constants.DEV4_APPLICATION_ID;
import static com.ramogi.app.spreadsheetreader.Constants.DEV4_REST_KEY;
import static com.ramogi.app.spreadsheetreader.Constants.DEVMASTER;
import static com.ramogi.app.spreadsheetreader.Constants.DEVMASTER_APPLICATION_ID;
import static com.ramogi.app.spreadsheetreader.Constants.DEVMASTER_REST_KEY;
import static com.ramogi.app.spreadsheetreader.Constants.PROD_APPLICATION_ID;
import static com.ramogi.app.spreadsheetreader.Constants.PROD_REST_KEY;
import static com.ramogi.app.spreadsheetreader.Constants.SANDBOX;
import static com.ramogi.app.spreadsheetreader.Constants.SANDBOX_APPLICATION_ID;
import static com.ramogi.app.spreadsheetreader.Constants.SANDBOX_REST_KEY;
import static com.ramogi.app.spreadsheetreader.Constants.STAGING;
import static com.ramogi.app.spreadsheetreader.Constants.STAGING_APPLICATION_ID;
import static com.ramogi.app.spreadsheetreader.Constants.STAGING_REST_KEY;
import static com.ramogi.app.spreadsheetreader.Constants.productionVersion;

/**
 * Created by ramogiochola on 6/21/16.
 */
public class UpdateAutoStopGPSTask extends AsyncTask<Void, Void, String> {

    private static final String TAG = "UpdateAutoStopGPSTask";
    private UpdateAutoStopGPSCallBack addTrackingUrlCallBack;
    private HashMap<String, String> postDataParams = new HashMap<>();
    private HashMap<String, String> parameters = new HashMap<>();
    private int responseCode;
    private Long requestDuration, requestStartTime, requestEndtime;


    public UpdateAutoStopGPSTask(UpdateAutoStopGPSCallBack addTrackingUrlCallBack,
                                 HashMap<String, String> parameters) {
        displayLog(" ***** UpdateAutoStopGPSTask called *********");

        this.addTrackingUrlCallBack = addTrackingUrlCallBack;
        this.parameters = parameters;

        try {
            if (parameters.containsKey("deliveryId")) {
                postDataParams.put("deliveryId", parameters.get("deliveryId"));
                displayLog("deliveryId" + " " + parameters.get("deliveryId"));
            }
        } catch (Exception e) {
            displayLog("deliveryId" + " error " + e.toString());
        }
        try {
            if (parameters.containsKey("userId")) {
                postDataParams.put("userId", parameters.get("userId"));
                displayLog("userId " + parameters.get("userId"));
            }
        } catch (Exception e) {
            displayLog("userId error " + e.toString());
        }
        try {
            if (parameters.containsKey("sessionToken")) {
                postDataParams.put("sessionToken", parameters.get("sessionToken"));
                displayLog("sessionToken " + parameters.get("sessionToken"));
            }
        } catch (Exception e) {
            displayLog("sessionToken error " + e.toString());
        }
        try {
            if (parameters.containsKey("autoStopDeliveryGPSAccuracy")) {
                postDataParams.put("autoStopDeliveryGPSAccuracy", parameters.get("autoStopDeliveryGPSAccuracy"));
                displayLog("autoStopDeliveryGPSAccuracy" + " " + parameters.get("autoStopDeliveryGPSAccuracy"));
            }
        } catch (Exception e) {
            displayLog("hypertrack url error " + e.toString());
        }
        try {
            if (parameters.containsKey("autoStopDeliveryLongitude")) {
                postDataParams.put("autoStopDeliveryLongitude", parameters.get("autoStopDeliveryLongitude"));
                displayLog("autoStopDeliveryLongitude" + " " + parameters.get("autoStopDeliveryLongitude"));
            }
        } catch (Exception e) {
            displayLog("affiliation error " + e.toString());
        }
        try {
            if (parameters.containsKey("autoStopDeliveryLatitude")) {
                postDataParams.put("autoStopDeliveryLatitude", parameters.get("autoStopDeliveryLatitude"));
                displayLog("autoStopDeliveryLatitude" + " " + parameters.get("autoStopDeliveryLatitude"));
            }
        } catch (Exception e) {
            displayLog("branch error " + e.toString());
        }
        try {
            if (parameters.containsKey("affiliation")) {
                postDataParams.put("affiliation", parameters.get("affiliation"));
                displayLog("affiliation" + " " + parameters.get("affiliation"));
            }
        } catch (Exception e) {
            displayLog("affiliation error " + e.toString());
        }
        try {
            if (parameters.containsKey("branch")) {
                postDataParams.put("branch", parameters.get("branch"));
                displayLog("branch" + " " + parameters.get("branch"));
            }
        } catch (Exception e) {
            displayLog("branch error " + e.toString());
        }
        try {
            postDataParams.put("productVersion", "1.0.0");
            postDataParams.put("product", "spreadSheetReader");
            postDataParams.put("appType", "android");
        } catch (Exception e) {
            displayLog("error adding three params " + e.toString());
        }
        try {
            requestStartTime = System.currentTimeMillis();
        } catch (Exception e) {
            displayLog("error adding three params " + e.toString());
        }
    }

    @Override
    protected String doInBackground(Void... params) {

        String results = "";
        try {
            String appId, restApi, urlString;
            if (productionVersion) {
                appId = PROD_APPLICATION_ID;
                restApi = PROD_REST_KEY;
            } else if (DEV1) {
                appId = DEV1_APPLICATION_ID;
                restApi = DEV1_REST_KEY;
            } else if (DEVMASTER) {
                appId = DEVMASTER_APPLICATION_ID;
                restApi = DEVMASTER_REST_KEY;
            } else if (DEV3) {
                appId = DEV3_APPLICATION_ID;
                restApi = DEV3_REST_KEY;
            } else if (DEV4) {
                appId = DEV4_APPLICATION_ID;
                restApi = DEV4_REST_KEY;
            } else if (STAGING) {
                appId = STAGING_APPLICATION_ID;
                restApi = STAGING_REST_KEY;
            } else if (SANDBOX) {
                appId = SANDBOX_APPLICATION_ID;
                restApi = SANDBOX_REST_KEY;
            }
            if (productionVersion) {
                urlString = "https://okhi.back4app.io/updateAutoStopGPS";
            } else if (DEVMASTER) {
                urlString = "https://okhicore-development-master.back4app.com/updateAutoStopGPS";
            } else if (SANDBOX) {
                urlString = "https://okhi-sbox.back4app.io/updateAutoStopGPS";
            } else if (DEV1) {
                urlString = "https://okhi-d1.back4app.io/updateAutoStopGPS";
            } else if (DEV4) {
                urlString = "https://okhi-d4.back4app.io/updateAutoStopGPS";
            } else if (DEV3) {
                urlString = "https://okhicore-development-3.back4app.com/updateAutoStopGPS";
            }

            OkHttpClient client = new OkHttpClient();

            // Initialize Builder (not RequestBody)
            FormBody.Builder builder = new FormBody.Builder();

            // Add Params to Builder
            for (Map.Entry<String, String> entry : postDataParams.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }

            RequestBody requestBody = builder.build();

            Request request = new Request.Builder()
                    .url(urlString)
                    .post(requestBody)
                    .addHeader("X-Parse-Application-Id", appId)
                    .addHeader("X-Parse-REST-API-Key", restApi)
                    .build();

            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            results = responseBody.string();
            responseCode = response.code();
        } catch (UnsupportedEncodingException e) {
            displayLog("unsupported encoding exception " + e.toString());
        } catch (IOException io) {
            displayLog("io exception " + io.toString());
        } catch (IllegalArgumentException iae) {
            displayLog("illegal argument exception " + iae.toString());
        }
        return results;
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            requestEndtime = System.currentTimeMillis();
            requestDuration = requestEndtime - requestStartTime;
        } catch (Exception e) {
            displayLog("request error " + e.toString());
        }
        displayLog("@@ response code " + responseCode + " result " + result);

        if ((200 <= responseCode) && (responseCode < 300)) {
            displayLog("updated");
            addTrackingUrlCallBack.querycomplete(result, true);
        } else {
            displayLog("not updated");
            addTrackingUrlCallBack.querycomplete(result, false);
        }
    }
    private void displayLog(String me) {
        Log.i(TAG, me);
    }
}
