package com.ramogi.app.spreadsheetreader;

/**
 * Created by ramogiochola on 11/16/17.
 */

import org.json.JSONObject;

/**
 * Created by kstanoev on 1/14/2015.
 */
interface AsyncResult
{
    void onResult(JSONObject object);
}