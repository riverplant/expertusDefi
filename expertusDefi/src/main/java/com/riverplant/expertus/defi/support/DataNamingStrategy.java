package com.riverplant.expertus.defi.support;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl;
import org.hibernate.boot.spi.MetadataBuildingContext;

public class DataNamingStrategy extends ImplicitNamingStrategyComponentPathImpl{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected Identifier toIdentifier(String stringForm, MetadataBuildingContext buildingContext) {
		
		//return super.toIdentifier("defi_"+stringForm, buildingContext);
		return super.toIdentifier(stringForm, buildingContext);
	}

}
