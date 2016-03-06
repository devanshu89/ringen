package com.ringencorp.ezrtt.backend.model.entities;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

/**
 * @author Devanshu
 * 
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public class ModelBase {

	@PrimaryKey
	private String mID ;

	public ModelBase(String id) {
		this.mID = id;
	}

	public String getId() {
		return mID;
	}

	public void updateID(String id) {
		this.mID = id;
	}

}
