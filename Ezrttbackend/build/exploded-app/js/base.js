
/** ezrtt global namespace for Ezrtt projects */
var ezrtt = ezrtt || {};

/** appengine namespace for Google Developer Relation projects */
ezrtt.appengine = ezrtt.appengine || {};

/** emper namespace for App Engine sample code */
ezrtt.appengine.emper = ezrtt.appengine.emper || {};


/**
 * Client ID of the application (from the APIs Console).
 * 
 * @type {string}
 */
ezrtt.appengine.emper.CLIENT_ID = '119346301347-jegi7nhdfsp1mcrkimav77kdde6hjp2g.apps.googleusercontent.com'; // change client

/**
 * Scopes used by the application.
 * 
 * @type {string}
 */
ezrtt.appengine.emper.SCOPES = 'https://www.googleapis.com/auth/userinfo.email';
/**
 * Whether or not the user is signed in.
 * 
 * @type {boolean}
 */
ezrtt.appengine.emper.signedIn = false;

var empeelist;
var emper;
var emperInfo;

/**
 * Initializes the application
 * 
 * @param {string}
 *            apiRoot Root of the API's path.
 */
ezrtt.appengine.emper.init = function(apiRoot) {
	// loads the OAuth and emper APIs asynchronously, and triggers the
	// login
	// when they have completed.

	var apisToLoad;
	var callback = function() {
		if (--apisToLoad == 0) {
			// once loaded do your task.
			// alert("api loaded successfully");
			ezrtt.appengine.emper.signin(true,
					ezrtt.appengine.emper.userAuthed);

		}
	}

	apisToLoad = 2; // must match number of calls to gapi.client.load()
	gapi.client.load('emperapi', 'v1', callback, apiRoot);
	gapi.client.load('oauth2', 'v2', callback);
}

/**
 * Handles the auth flow, with the given value for immediate mode.
 * 
 * @param {boolean}
 *            mode Whether or not to use immediate mode.
 * @param {Function}
 *            callback Callback to call on completion.
 */
ezrtt.appengine.emper.signin = function(mode, callback) {
	gapi.auth.authorize({
		client_id : ezrtt.appengine.emper.CLIENT_ID,
		scope : ezrtt.appengine.emper.SCOPES,
		immediate : mode
	}, callback);
};

/**
 * Loads the application UI after the user has completed auth.
 */
ezrtt.appengine.emper.userAuthed = function() {
	var request = gapi.client.oauth2.userinfo
			.get()
			.execute(
					function(resp) {
						if (!resp.code) {
							ezrtt.appengine.emper.signedIn = true;
							document.querySelector("#signinButton").textContent = 'Sign out';

							gapi.client.emperapi
									.getloggeduser()
									.execute(
											function(resp) {
												if (!resp.code) {
													console.log(resp);
													document
															.querySelector("#loggeduser").textContent = "Hello, "
															+ resp.name;
													emper = resp.email;
													emperInfo = resp.userinfo;
													ezrtt.appengine.emper
															.getemperdet();
												}
											});
						}
					});
};

/**
 * Presents the user with the authorization popup.
 */
ezrtt.appengine.emper.auth = function() {
	console.log("Inside Auth()");
	if (!ezrtt.appengine.emper.signedIn) {
		console.log("Signing In.");
		ezrtt.appengine.emper.signin(false,
				ezrtt.appengine.emper.userAuthed);
	} else {
		console.log("Signing Out.");
		ezrtt.appengine.emper.signedIn = false;
		document.querySelector('#signinButton').textContent = 'Sign in';
		document.querySelector("#loggeduser").textContent = "";
	}
};

ezrtt.appengine.emper.getemperdet = function() {
	// var user = document.getElementById('loggeduser').value;
	// console.log(emper);
	gapi.client.emperapi.getemperdetails().execute(function(resp) {
		if (!resp.code) {
			console.log(resp);
			if (resp.active) {
				ezrtt.appengine.emper.getallempee();
			} else {
				ezrtt.appengine.emper.displayRegEmper();
			}

		}
	});
};

ezrtt.appengine.emper.closeSelf = function() {
	// do something

	if (true) {
		// alert("conditions satisfied, submiting the form.");
		// document.forms['addempee'].submit();
		window.close();
	} else {
		alert("conditions not satisfied, returning to form");
	}
};

ezrtt.appengine.emper.sendempeedata = function() {
	var firstname = document.querySelector('#firstname').value;
	var lastname = document.querySelector('#lastname').value;
	var email = document.querySelector('#email').value;
	var mobile = document.querySelector('#mobile').value;
	var intime = document.querySelector('#intime').value;
	var outtime = document.querySelector('#outtime').value;

	if (firstname == null || lastname == null || email == null
			|| mobile == null || intime == null || outtime == null) {
		alert("Missing details. please fill out details completeley");
		return false;
	}

	gapi.client.emperapi.addemployee({
		'firstname' : firstname,
		'lastName' : lastName,
		'email' : email,
		'mobile' : mobile,
		'active' : false,
		'inTime' : intime,
		'outTime' : outtime

	}).execute(function(resp) {
		console.log(resp);
		if (!resp.code) {
			if (resp.status == true) {
				alert("Empee added");
			} else {
				alert("failed");
			}
			window.close();
		}
	});

};

ezrtt.appengine.emper.addempee = function() {
	alert('adding employee');
	var mylink = 'https://ezrttsys.appspot.com/addempee.jsp';
	if (!window.focus)
		return true;
	var href;
	if (typeof (mylink) == 'string')
		href = mylink;
	else
		href = mylink.href;
	window.open(href, 'Add employee', 'width=800,height=600,scrollbars=yes');
	return false;
};

ezrtt.appengine.emper.setouttime = function() {
	alert("conditions not satisfied, returning to form");
	var intime = document.getElementById('intime').value;
	var outtime = document.getElementById('outime');
	// Date inDate = new Date valueAsDate;
	// outtime.value = intime.value + 1;
};

ezrtt.appengine.emper.validateouttime = function() {
	var outtime = document.getElementById('outime');
};

ezrtt.appengine.emper.getallempee = function() {
	alert("getting list of all employee");
	gapi.client.emperapi.getallemployees().execute(function(resp) {
		console.log(resp);
		if (!resp.code) {
			resp.items = resp.items || [];
			empeelist = resp.items;
			console.log(empeelist);
			$("#board").load("EmpeeList.jsp #empee-add");
			if (empeelist.length > 0) {
				$("#board-table").load("EmpeeList.jsp #empee-list", function() {
					ezrtt.appengine.emper.displayempee();
				});
			}
		}
	});
};

ezrtt.appengine.emper.viewreport = function(mobile) {
	alert(mobile);
};

ezrtt.appengine.emper.displayempee = function() {
	var table = document.getElementById("empee-table");
	if (empeelist.length > 0) {
		for (var i = 0; i < empeelist.length; i++) {
			console.log(table);

			var row = table.insertRow(-1);
			var cell = row.insertCell(0);
			cell.innerHTML = empeelist[i].firstName + empeelist[i].lastName;

			var cell1 = row.insertCell(1);
			cell1.innerHTML = empeelist[i].status;

			var cell2 = row.insertCell(2);
			cell2.innerHTML = empeelist[i].mobile;

			var cell3 = row.insertCell(3);
			cell3.innerHTML = "<button id = '" + empeelist[i].mobile
					+ "' onClick=ezrtt.appengine.emper.viewreport("
					+ empeelist[i].mobile
					+ ") class='btn btn-success btn-xs'>View Report </a>";
		}

	}
};

ezrtt.appengine.emper.displayRegEmper = function() {
	$("#board").load('EmperReg.jsp #regemper');
};

ezrtt.appengine.emper.addemper = function() {
	alert("Registering emper");

	var name = document.querySelector('#name').value;
	var address = document.querySelector('#address').value;
	var headcount = document.querySelector('#headcount').value;
	var cntrylist = document.querySelector('#country');
	var country = cntrylist.options[cntrylist.selectedIndex].value;
	var latitude = 41.90;
	var longitude = 87.65;

	if (name == null || address == null || headcount == null || country == null) {
		alert("Missing details. please fill out details completeley");
		return false;
	}

	gapi.client.emperapi.regemper({
		'name' : name,
		'address' : address,
		'country' : country,
		'headcount' : headcount,
		'latlng' : {
			'latitude' : latitude,
			'longitude' : Location
		}
	}).execute(function(resp) {
		console.log(resp);
		if (!resp.code) {
			ezrtt.appengine.emper.getallempee();
		}
	});

};
