package com.riverplant.expertus.defi.domain;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author riverplant
 *
 */
@MappedSuperclass
public class DomainImpl {
	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	
}
