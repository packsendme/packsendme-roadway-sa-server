package com.packsendme.roadway.simulation.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.packsendme.roadbrewa.entity.Roadway;
import com.packsendme.roadway.simulation.config.Redis_Config;

@Repository
@Transactional
public class RoadwayCacheImpl_Dao implements ICrudCache<Roadway>{
	
	private final boolean RESULT_SUCCESS = true;
	private final boolean RESULT_ERROR = false;

	@Autowired(required=true)
	private RedisTemplate<Object, Object> redisTemplate;

	@Autowired
	private Redis_Config cacheConfig;
	

	@Override
	public Roadway findOne(String hashKey) {
		try {
			return (Roadway) redisTemplate.opsForHash().get(cacheConfig.NAME_CACHE, hashKey);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

 
}
