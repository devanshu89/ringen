/**
 * 
 */
package com.ringencorp.ezrtt.backend.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.GeoPt;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.ringencorp.ezrtt.backend.constants.EngineConstants;
import com.ringencorp.ezrtt.backend.helpers.PMF;
import com.ringencorp.ezrtt.backend.model.EmpLoc;
import com.ringencorp.ezrtt.backend.model.Employee;
import com.ringencorp.ezrtt.backend.model.entities.ModelEmpee;
import com.ringencorp.ezrtt.backend.model.entities.ModelEmper;
import com.ringencorp.ezrtt.backend.model.entities.ModelLocEmpee;

/**
 * WRAPPER FOR STORING/DELETING/UPLDATING/RETREIVING ENTITES OBJECT
 * 
 * @author Devanshu
 * 
 */
public class DatabaseWrapper {

	/**
	 * 
	 */
	public DatabaseWrapper() {
	}

	public static DatastoreService mDatastoreservice = DatastoreServiceFactory.getDatastoreService();

	public static ImagesService mImagesService = ImagesServiceFactory.getImagesService();

	public void insertEmpee(ModelEmpee empee) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(empee);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}
	}

	public void insertEmper(ModelEmper emper) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(emper);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ModelEmpee> getEmpee(ModelEmper emper) {
		List<ModelEmpee> ret = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(ModelEmpee.class);
		query.setFilter("employername == " + emper.getName());
		ret = (List<ModelEmpee>) query.execute();
		return ret;
	}

	public ModelEmper getEmper(String loggedUser) {
		ModelEmper ret = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			ret = pm.getObjectById(ModelEmper.class, loggedUser);
		} catch (JDOObjectNotFoundException e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}
		// Query query = pm.newQuery(ModelEmper.class);
		// query.setFilter("name == loggedUser");
		// Map<String, String> paramVal = new HashMap();
		// paramVal.put("loggedUser", loggedUser);
		// ret = (ModelEmper) query.executeWithMap(paramVal);
		return ret;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Employee> getAllEmployees(String emperEmail, ModelEmper emper) {
		System.out.println(emperEmail);
		List<ModelEmpee> ret = null;
		ArrayList<Employee> empeeeList = new ArrayList<Employee>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(ModelEmpee.class);
		query.setFilter("employername == loggedUser");
		query.declareParameters("java.lang.String loggedUser");
		ret = (List<ModelEmpee>) query.execute(emper);
		for (ModelEmpee employee : ret) {
			empeeeList.add(new Employee(employee.getFirstName(), employee.getLastName(), employee.getEmail(),
					employee.getMobile(), employee.isActive(), employee.getEmployername(),
					employee.getInTime().toString(), employee.getOutTime().toString(), emper.getWeekStartDay(),
					emper.getWeekEndDay()));
		}

		return empeeeList;
	}

	public ModelEmpee getEmployee(String number) {
		ModelEmpee ret = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			ret = pm.getObjectById(ModelEmpee.class, number);
		} catch (JDOObjectNotFoundException e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}
		return ret;

	}

	public GeoPt getOffAddress(String empNum) {
		ModelEmpee empee = null;
		ModelEmper emper = null;
		empee = getEmployee(empNum);
		emper = getEmper(empee.getEmployername());
		return emper.getLatlng();
	}

	public void insertEmpeeLoc(ModelLocEmpee empLoc) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		System.out.println(empLoc.getLocdate().toString());
		try {
			pm.makePersistent(empLoc);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<EmpLoc> getEmpLoc(String empee, String locFrom, String locTo) throws ParseException {
		Date dateFrom = null;
		Date dateTo = null;
		ArrayList<EmpLoc> ret = new ArrayList<EmpLoc>();
		List<ModelLocEmpee> modEmpLoc;

		dateFrom = new SimpleDateFormat(EngineConstants.LOC_DATE_PATTERN).parse(locFrom);
		dateTo = new SimpleDateFormat(EngineConstants.LOC_DATE_PATTERN).parse(locTo);

		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query query = pm.newQuery(ModelLocEmpee.class);
		query.declareParameters("java.util.Date dateFrom");
		query.declareParameters("java.util.Date dateTo");
		query.declareParameters("java.lang.String empee");
		query.setFilter("locdatetime > dateFrom");
		query.setFilter("locdatetime < dateTo");
		query.setFilter("employeename == empee");
		modEmpLoc = (List<ModelLocEmpee>) query.execute(dateFrom, dateTo, empee);
		for (ModelLocEmpee modelLocEmpee : modEmpLoc) {
			ret.add(new EmpLoc(modelLocEmpee.getLtln(), modelLocEmpee.getLocdate().toString(),
					modelLocEmpee.getEmpNum()));
		}
		return ret;
	}

	public ModelEmper getEmperOfEmpee(String employeenum) {
		ModelEmper ret = null;
		ModelEmpee empee = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			empee = pm.getObjectById(ModelEmpee.class, employeenum);
			ret = getEmper(empee.getEmployername());
		} catch (JDOObjectNotFoundException e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}
		return ret;
	}
}
