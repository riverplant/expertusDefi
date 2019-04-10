package com.riverplant.expertus.defi.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * 
 * @author riverplant
 *
 */
public class MyRepositoryImpl<T> extends SimpleJpaRepository<T, Long>{
    private static final Logger LOG = LoggerFactory.getLogger(MyRepositoryImpl.class);
	public MyRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		
	}
	
	@Override
	public <S extends T> S save(S entity) {
		System.out.println("save: "+entity.getClass().getSimpleName()+" at "+LocalDateTime.now().toString());
		LOG.info("save: "+entity.getClass().getSimpleName()+" at "+LocalDateTime.now().toString());
		return super.save(entity);
	}

	@Override
	public <S extends T> List<S> saveAll(Iterable<S> entities) {
		entities.forEach(item->LOG.info("save: "+item.getClass().getSimpleName()+" at "+LocalDateTime.now().toString()));
		entities.forEach(item->System.out.println("save: "+item.getClass().getSimpleName()+" at "+LocalDateTime.now().toString()));
		//System.out.println("save: "+entity.getClass().getSimpleName()+" at "+LocalDateTime.now().toString());
		
		return super.saveAll(entities);
	}

	

}
