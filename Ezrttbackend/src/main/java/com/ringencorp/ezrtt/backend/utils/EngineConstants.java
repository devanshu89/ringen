package com.ringencorp.ezrtt.backend.utils;

import com.google.appengine.api.users.User;

/**
 * Contains the client IDs and scopes for allowed clients consuming the
 * selfiemanagerapi API.
 */
public class EngineConstants {

    public static final String WEB_CLIENT_ID = "119346301347-jegi7nhdfsp1mcrkimav77kdde6hjp2g.apps.googleusercontent.com";
    public static final String APIEXP_CLIENT_ID = "292824132082.apps.googleusercontent.com";
    public static final String ANDROID_CLIENT_ID_DEBUG = "119346301347-gcan5nnuvnc53hvg23pe37bhregg7jh2.apps.googleusercontent.com";
    public static final String ANDROID_CLIENT_ID_BETA_RELEASE = "536245570166-fdqiogl4lo18sks32a4962f9ofsi2aq4.apps.googleusercontent.com";
    public static final String ANDROID_CLIENT_ID = "536245570166-cakvv3ap32oig22e6m9vlgkem5acfa3n.apps.googleusercontent.com";
    public static final String IOS_CLIENT_ID = "replace this with your iOS client ID";
    public static final String ANDROID_AUDIENCE = WEB_CLIENT_ID;
    public static final String PUBLIC_KEY = "AIzaSyBCEXINLIkRjUvxXv5TizaZgQDpnEdjk3g";
    public static final String EMAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email";
    public static final String API_EXPLORER_CLIENT_ID = com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID;
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String LOC_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String LOC_DAY_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String TIME_PATTERN = "HH:mm:ss";
    public final static int FRESH_SELFIES_LIMIT = 60;
    // Admin account.
    public static final String ADMIN_ACCOUNT_NAME = "gr8selfies@gmail.com";
    public static final String TIME_ZONE = "UTC";
    public static boolean TEST_RUN = true;


    /**
     * Checks whether the logged user is Admin.
     *
     * @return true if logged user is Admin. false otherwise
     */
    public static boolean isAdminLogged(User user) {

        String emailAccount = user.getEmail();

        return emailAccount.equalsIgnoreCase(ADMIN_ACCOUNT_NAME);

    }
}
