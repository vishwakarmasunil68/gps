package com.voxtrail.gpstracking.webservice;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import com.voxtrail.gpstracking.util.TagUtils;
import com.voxtrail.gpstracking.util.ToastClass;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by sunil on 05-04-2017.
 */

public class GetWebServices extends AsyncTask<String,Void,String> {

    private final String TAG=getClass().getSimpleName();
    Context context;
    Map<String,String> headers;
    ProgressDialog progressDialog;
    String msg;
    boolean is_dialog=true;
    String response;
    Object className;

    public GetWebServices(Context context,Map<String,String> headers, Object className, String msg, boolean is_dialog){
        this.context=context;
        this.msg = msg;
        this.className=className;
        this.is_dialog=is_dialog;
        this.headers=headers;
        Log.d(TAG, this.toString());
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(is_dialog) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection urlConnection = null;
        URL url = null;
        InputStream inStream = null;
        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    // do stuff
                    urlConnection.setRequestProperty(key, value);
                }
            }
            urlConnection.setDoInput(true);
            urlConnection.connect();
            inStream = urlConnection.getInputStream();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));
            String temp, response = "";
            while ((temp = bReader.readLine()) != null) {
                response += temp;
            }
            Log.d(TagUtils.getTag(),msg+":-"+response);
            this.response=response;
//            object = (JSONObject) new JSONTokener(response).nextValue();
            return response;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
        Log.d(TAG,"response:-"+response);
        if(s!=null&&s.length()>0) {
            if (className != null) {
                WebServicesCallBack mcallback = (WebServicesCallBack) className;
                mcallback.onGetMsg(msg,s);
            } else {
                ToastClass.showShortToast(context,"Something went wrong");
            }
        }else{
//            ToastClass.showShortToast(context,"No Internet");
        }
    }


}
