package com.wangkang.repository;


import com.wangkang.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PersonRepository extends JpaRepository<Person, Long> {
	// 使用方法名查询，接受一个name参数，返回值为列表。
	//这里String name可以，String address也可以
	List<Person> findByAddress(String address);

	// 使用方法名查询，接受name和address，返回值为单个对象。
	Person findByNameAndAddress(String name, String address);

	// 使用@Query查询，参数按照名称绑定。
	@Query("select p from Person p where p.name= :name and p.address= :address")
	Person withNameAndAddressQuery(@Param("name") String name, @Param("address") String address);

	// 使用@NamedQuery查询，请注意我们在实体类中做的@NamedQuery的定义。
	Person withNameAndAddressNamedQuery(String name, String address);
}