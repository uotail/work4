package com.lesson.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import com.lession.model.Student;
import com.lession.utils.JedisPoolUtil;
import com.lession.utils.StudentUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
public class StudentDao {

	public List<Student> studentList(String currentPage){
		int cp = Integer.parseInt(currentPage);
		List<Student> students = new ArrayList<>();
		Jedis jedis = JedisPoolUtil.getJedis();
		Set<Tuple> stus = jedis.zrevrangeByScoreWithScores("students", 100, 0, (cp-1)*10, 10);
		for (Iterator iterator = stus.iterator(); iterator.hasNext();) {
			Tuple tuple = (Tuple) iterator.next();
			String data = tuple.getElement();
			double score = tuple.getScore();
			Student student = StudentUtil.dsToStudent(data, (int)score);
			students.add(student);
		}
		return students;
		
	}
	public void studentAdd(String data, double score) {
		Jedis jedis = JedisPoolUtil.getJedis();
    	jedis.zadd("students", score, data);
		
	}
	public Student findByScoreAndId(double score,String id) {
		Student student = new Student();
		Jedis jedis = JedisPoolUtil.getJedis();
		Set<String> studentByScore = jedis.zrangeByScore("students", score, score);
		for (String str : studentByScore) {
			if(id.equals(str.split(",")[0])) {
				student = StudentUtil.dsToStudent(str, score);
			}
		}
		return student;
		
	}
	public void studentRem(String data) {
		Jedis jedis = JedisPoolUtil.getJedis();
		Long zrem = jedis.zrem("students", data);
		System.out.println(zrem+"sa");
	}
	
}
