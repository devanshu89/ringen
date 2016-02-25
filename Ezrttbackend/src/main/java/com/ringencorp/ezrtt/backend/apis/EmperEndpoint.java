/**
 * 
 */
package com.ringencorp.ezrtt.backend.apis;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.users.User;
import com.ringencorp.ezrtt.backend.db.DatabaseWrapper;
import com.ringencorp.ezrtt.backend.helpers.DatastoreHelper;
import com.ringencorp.ezrtt.backend.model.EmpWeekInfo;
import com.ringencorp.ezrtt.backend.model.Employee;
import com.ringencorp.ezrtt.backend.model.Employer;
import com.ringencorp.ezrtt.backend.model.Status;
import com.ringencorp.ezrtt.backend.model.entities.ModelEmpee;
import com.ringencorp.ezrtt.backend.model.entities.ModelEmper;
import com.ringencorp.ezrtt.backend.utils.Constants;

import java.util.ArrayList;

/**
 * @author Devanshu
 *
 */

@Api(name = "emperapi", version = "v1", scopes = { Constants.EMAIL_SCOPE, Constants.PROFILE_SCOPE }, clientIds = {
		Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID,
		Constants.APIEXP_CLIENT_ID }, audiences = { Constants.ANDROID_AUDIENCE })
public class EmperEndpoint {

	DatastoreHelper mDSHelper;

	DatabaseWrapper db = new DatabaseWrapper();

	@ApiMethod(name = "getallemployees")
	public ArrayList<Employee> getEmpees(User loggedUser) {
		ArrayList<Employee> listEmpee = new ArrayList<Employee>();
		ModelEmper emper = db.getEmper(loggedUser.getEmail());
		listEmpee = db.getAllEmployees(loggedUser.getEmail(), emper);
		return listEmpee;
	}

	@ApiMethod(name = "getloggeduser")
	public Employer getLoggedUser(User loggedUser) throws OAuthRequestException {
		if (loggedUser == null) {
			throw new OAuthRequestException("User not logged in");
		}
		Employer ret = new Employer(loggedUser.getEmail(), loggedUser.getNickname());
		return ret;
	}

	@ApiMethod(name = "addemployee")
	public Employee addEmployee(Employee empee, User emper) throws OAuthRequestException {
		if (emper == null) {
			throw new OAuthRequestException("User not logged in");
		}

		ModelEmpee modEmpee = new ModelEmpee(empee.getMobile(), emper.getEmail(), empee.getFirstName(),
				empee.getLastName(), empee.getEmail(), empee.getMobile(), empee.getInTime() + ":00",
				empee.getOutTime() + ":00");

		modEmpee.setActive(false);

		DatabaseWrapper db = new DatabaseWrapper();

		db.insertEmpee(modEmpee);
		Employee ret = new Employee();
		ret.setStatus(true);
		return ret;

	}


	@ApiMethod(name = "getemperdetails", path = "emper/getdetails")
	public Employer getEmperDetails(User loggedUser) {
		Employer ret;
		System.out.println(loggedUser.getEmail());
		ModelEmper emper = db.getEmper(loggedUser.getEmail());
		if (emper != null && emper.isActive()) {
			ret = new Employer(null, null, null, null, 0, emper.getId(), emper.isActive(), -1, -1);
			return ret;
		}
		return null;
	}


	@ApiMethod(name = "viewreport")
	public ArrayList<EmpWeekInfo> viewreport(@Named("emploeenum") String number, @Named("date") String date, User logged)
			throws OAuthRequestException {
		ArrayList<EmpWeekInfo> wkInfo = new ArrayList<EmpWeekInfo>();
		if (logged != null) {

		} else {
			throw new OAuthRequestException("User not logged in");
		}
		return wkInfo;
	}

	@ApiMethod(name = "regemper")
	public Status regEmployer(Employer emper, User logged) throws OAuthRequestException {
		Status ret = new Status();
		if (logged != null) {
			ModelEmper modE = new ModelEmper(logged.getEmail(), emper.getName(), emper.getAddress(), emper.getLatlng(),
					emper.getCountry(), emper.getHeadcount(), emper.getWeekStartDay(), emper.getWeekEndDay());

			modE.setActive(true);

			DatabaseWrapper db = new DatabaseWrapper();

			db.insertEmper(modE);
			ret.setUpdated(true);
		} else {
			throw new OAuthRequestException("User not logged in");
		}
		return ret;
	}

	@ApiMethod(name = "testapi", path = "/testapi", httpMethod = ApiMethod.HttpMethod.GET)
	public Status testApi() throws OAuthRequestException {
		Status ret = new Status();
		ret.setData("working");
		return ret;
	}

}
