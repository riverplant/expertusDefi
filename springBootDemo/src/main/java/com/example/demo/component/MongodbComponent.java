package com.example.demo.component;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.example.demo.bean.Sduser;

@Component
public class MongodbComponent {
	@Autowired
	private MongoTemplate mongoTemplate;

	public void insert(Sduser sduser) {
		mongoTemplate.insert(sduser);
	}
	
	public void deleteById(int id) {
		//标准
		Criteria criteria  = Criteria.where("id").in(id);
		Query query = new Query(criteria);
		mongoTemplate.remove(query, Sduser.class);
	}
	
	public void findById(int id) {
		//标准
		Criteria criteria  = Criteria.where("id").in(id);
		
		Query query = new Query(criteria);
		mongoTemplate.findOne(query, Sduser.class);
		List<Sduser> sdusers = mongoTemplate.findAll(Sduser.class);
	}
	
	public void updateById(Sduser sduser) {
		//标准
		Criteria criteria  = Criteria.where("id").in(sduser.getId());
		
		Query query = new Query(criteria);
		Update update = new Update();
		update.set("name", sduser.getName());
		update.set("create_time", new Date());
		mongoTemplate.updateMulti(query, update, Sduser.class);
		
	}
}
