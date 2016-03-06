/**
 *
 */
package com.ringencorp.ezrtt.backend.apis;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.appengine.api.datastore.GeoPt;
import com.ringencorp.ezrtt.backend.db.DatabaseWrapper;
import com.ringencorp.ezrtt.backend.model.EmpLoc;
import com.ringencorp.ezrtt.backend.model.EmpWeekInfo;
import com.ringencorp.ezrtt.backend.model.Employee;
import com.ringencorp.ezrtt.backend.model.Status;
import com.ringencorp.ezrtt.backend.model.entities.ModelEmpee;
import com.ringencorp.ezrtt.backend.model.entities.ModelEmper;
import com.ringencorp.ezrtt.backend.model.entities.ModelLocEmpee;
import com.ringencorp.ezrtt.backend.utils.EngineConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Devanshu
 */

@Api(name = "empeeapi", version = "v1", clientIds = {EngineConstants.WEB_CLIENT_ID, EngineConstants.ANDROID_CLIENT_ID,
        EngineConstants.IOS_CLIENT_ID, EngineConstants.APIEXP_CLIENT_ID, EngineConstants.ANDROID_CLIENT_ID_DEBUG}, audiences = {EngineConstants.ANDROID_AUDIENCE})
public class EmpeeEndpoint {

    DatabaseWrapper db = new DatabaseWrapper();

    @ApiMethod(name = "updateloc")
    public EmpLoc updateLoc(EmpLoc empLoc) throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(EngineConstants.LOC_DATE_PATTERN);
        GeoPt offAdd = db.getOffAddress(empLoc.getEmpNum());
        boolean isEmpeePresent = isEmpeePresent(offAdd, empLoc.getLtln(), 2);
        db.insertEmpeeLoc(new ModelLocEmpee(empLoc.getLtln(), dateFormatter.parse(empLoc.getLocdate()),
                empLoc.getEmpNum(), isEmpeePresent));
        empLoc.setLocdate(dateFormatter.parse(empLoc.getLocdate()).toString());
        return empLoc;
    }

    @ApiMethod(name = "testdate")
    public Status testdate() throws ParseException {
        String date = "2016-02-09 20:15";
        Status res = new Status(false);
        String LOC_DATE_PATTERN = "yyyy-MM-dd HH:mm";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(LOC_DATE_PATTERN);
        System.out.println(dateFormatter.parse(date).toString());
        res.setData(dateFormatter.parse(date).toString());
        return res;

    }

    @ApiMethod(name = "getloc")
    public ArrayList<EmpWeekInfo> getLoc(@Named("employeenum") String employeenum, @Named("fromdate") String fromdate,
                                         @Named("todate") String todate) throws ParseException {
        ArrayList<EmpWeekInfo> ret = new ArrayList<EmpWeekInfo>();

        SimpleDateFormat dateFormatter = new SimpleDateFormat(EngineConstants.LOC_DATE_PATTERN);
        SimpleDateFormat timeFormatter = new SimpleDateFormat(EngineConstants.TIME_PATTERN);
        dateFormatter.setTimeZone(TimeZone.getTimeZone(EngineConstants.TIME_ZONE));

        EmpWeekInfo wkInfo = new EmpWeekInfo();
        ModelEmper emper = db.getEmperOfEmpee(employeenum);
        ModelEmpee empee = db.getEmployee(employeenum);
        wkInfo.setDayOfwkStart(emper.getWeekStartDay());
        wkInfo.setDayOfwkEnd(emper.getWeekEndDay());

        Date wkDate = dateFormatter.parse(empee.getInTime());
        System.out.println(wkDate.toString());

        Calendar wkCal = Calendar.getInstance();
        wkCal.setTime(wkDate);


        //wkInfo.setInHour(wkCal.get(Calendar.HOUR_OF_DAY));

        // This is test commit

        ArrayList<EmpLoc> empLoc = db.getEmpLoc(employeenum, fromdate, todate);

        for (EmpLoc locInfo : empLoc) {

        }

        return ret;

    }

    @ApiMethod(name = "verifyempee")
    public Employee verifyEmpee(@Named("emploeenum") String number) {
        ModelEmpee empee = db.getEmployee(number);
        ModelEmper emper = db.getEmper(empee.getEmployername());
        Employee ret = new Employee(empee.getEmployername(), emper.getLatlng(), empee.getInTime(), empee.getOutTime(),
                emper.getWeekStartDay(), emper.getWeekEndDay());
        return ret;
    }

    private boolean isEmpeePresent(GeoPt offAdd, GeoPt empeeLoc, int rangeRad) {
        float earthRad = 6371000;

        double lat1 = toRadians(offAdd.getLatitude());
        double lat2 = toRadians(empeeLoc.getLatitude());

        double latDelta = toRadians(offAdd.getLatitude() - empeeLoc.getLatitude());
        double lngDelta = toRadians(offAdd.getLongitude() - empeeLoc.getLongitude());

        double a = Math.sin(latDelta / 2) * Math.sin(latDelta / 2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.sin(lngDelta / 2) * Math.sin(lngDelta / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = earthRad * c;

        System.out.println(Double.toString(distance / 1000));

        System.out.println(distance / 1000 > 5 ? "Out of office" : "In office");
        return false;
    }

    private double toRadians(float latOrLng) {

        return (Math.PI * latOrLng / 180);
    }

}
