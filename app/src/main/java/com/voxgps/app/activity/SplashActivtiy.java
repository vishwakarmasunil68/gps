package com.voxgps.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.voxgps.app.R;
import com.voxgps.app.testing.GetLocationActivity;
import com.voxgps.app.testing.PlayBackTesting;
import com.voxgps.app.util.Pref;
import com.voxgps.app.util.StringUtils;
import com.voxgps.app.util.TagUtils;

import io.fabric.sdk.android.Fabric;

public class SplashActivtiy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash_activtiy);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TagUtils.getTag(),"device_token:-"+Pref.GetDeviceToken(getApplicationContext(),""));
                if (Pref.GetBooleanPref(getApplicationContext(), StringUtils.IS_LOGIN, false)) {
                    Intent intent = new Intent(SplashActivtiy.this, HomeActivity.class);
//                    Intent intent = new Intent(SplashActivtiy.this, PlayBackTesting.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SplashActivtiy.this, LoginActivity.class);
                    startActivity(intent);
                }
//                startActivity(new Intent(SplashActivtiy.this, VehicleGeoFenceFragment.class));
                finish();
            }
        }, 2000);
    }
}
