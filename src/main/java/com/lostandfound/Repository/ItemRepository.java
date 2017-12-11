package com.lostandfound.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lostandfound.entities.Item;


@Repository
public interface ItemRepository extends CrudRepository<Item, Long>
{
	@Query( nativeQuery = true, value = "select  id,name,description,found_date,image_location from item where is_active=1" ) List getAllLostItems();
	
	
	
	@Query( nativeQuery = true, value = "select  id,name,description,found_date,image_location from item where is_active=0" ) List getAllFoundItems();
	
	
	
	@Query( nativeQuery= true, value= "select  id,name,description,found_date,image_location from item where name=:name and is_active=1")
	List searchLostItems(@Param("name") String name);


	@Query( nativeQuery= true, value= "select  id,name,description,found_date,image_location from item where name=:name and is_active=0")
	List searchFoundItems(@Param("name") String name);

	
	
	@Modifying
	@Query("update Item set isActive=false where id=:id")
	int removeItem(@Param("id") int id);

}
