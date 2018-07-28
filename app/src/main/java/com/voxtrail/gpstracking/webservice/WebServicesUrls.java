package com.voxtrail.gpstracking.webservice;

/**
 * Created by sunil on 20-01-2017.
 */

public class WebServicesUrls {

//    public static final String BASE_URL = "http://voxtrail.in/visitor/visitor/";
    public static final String BASE_URL = "http://www.zaptac.net:1930/";

    public static final String TOKEN = BASE_URL + "Token";
    public static final String VEHICLE_LIST = BASE_URL + "api/Vehicle/VehicleList";
    public static final String VEHICLE_HISTORY = BASE_URL + "api/Vehicle/HistoryBetween/";
    public static final String RADIUS = BASE_URL + "api/Vehicle/CreateRadiusFence";
    public static final String DEVICE_TOKEN = BASE_URL + "api/Vehicle/UpdateDeviceToken";
    public static final String GET_NOTIFICATIONS_BY_USERID = BASE_URL + "api/Notifications/GetNotificationsByUserId";
    public static final String USER_REGISTRATION = BASE_URL + "api/Account/Register";
    public static final String VEHICLE_CURRENT_STATUS = BASE_URL + "api/Vehicle/CurrentStatus/";

    public static String getVehicleHistoryURL(String vehicle_id,String start_date_time,String end_date_time,String type){
        return VEHICLE_HISTORY+vehicle_id+"?dt1="+start_date_time+"&dt2="+end_date_time+"&type="+type;
    }

    public static String updateDeviceToken(String device_token){
        return DEVICE_TOKEN+"?token="+device_token;
    }

}
