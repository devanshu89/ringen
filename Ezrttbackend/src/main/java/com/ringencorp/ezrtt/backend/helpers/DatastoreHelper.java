package com.ringencorp.ezrtt.backend.helpers;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatastoreHelper {

    public static DatastoreService mDatastoreservice = DatastoreServiceFactory
            .getDatastoreService();
    public static String KIND_EMPEE = "kind_empee";
    public static String KIND_EMPER = "kind_emper";
    public static String KIND_NAME_EMPEE = "kind_name_empee";
    public static String KIND_NAME_EMPER = "kind_name_emper";
    public static String PARENT_EMPEE = "store_empee";
    public static String PARENT_EMPER = "store_emper";
    public static String PROP_EMPEE_ID = "_empeeId";
    public static String PROP_EMPEE_NAME = "_empeeName";
    public static String PROP_EMPEE_NICK_NAME = "_empeeNickName";
    public static String PROP_EMPEE_EMAIL = "_empeeEmail";
    public static String PROP_DATE_TIME = "_dateTime";
    public static String PROP_STATUS = "_status";
    private static Key mKeyEmpee;
    private static Key mKeyEmper;
    private ImagesService mImagesService = ImagesServiceFactory
            .getImagesService();
    private String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    // Initializes the key kind
    public DatastoreHelper(Date _idDate, String emperName) {

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        mKeyEmpee = KeyFactory.createKey(KIND_EMPEE, sdf.format(_idDate));
        mKeyEmper = KeyFactory.createKey(KIND_EMPER, emperName);
    }

    public static DatastoreService getDataStoreservice() {
        return mDatastoreservice;
    }

    public ImagesService getImageservice() {
        return mImagesService;
    }

    public Key getEmpeeKey() {
        return mKeyEmpee;
    }

    public Key getParentEmpeeKey() {
        return mKeyEmpee.getParent();
    }

    public Key getParentEmperKey() {
        return mKeyEmper.getParent();
    }

    public Key getEmperKey() {
        return mKeyEmper;
    }

}
