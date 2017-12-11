package com.lostandfound.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lostandfound.bean.ItemModel;
import com.lostandfound.services.ItemService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RestController
@EnableAutoConfiguration
@RequestMapping("/rest/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@ApiOperation( value = "Add Lost Item to List",response =ResponseEntity.class, notes ="Adds the lost item to list ")
	@ApiParam( value = "This consists of item info" )
	
	
	@ApiResponses( value = {@ApiResponse( code = 401, message ="item do not exist" ) } )
	//@ApiImplicitParams( {@ApiImplicitParam( name ="x-auth-token", value = "", required= true, dataType = "string",paramType = "header" ) } )
	
	@RequestMapping(value = "/addLostItem", method = RequestMethod.POST)
	public String addLostItem(@RequestBody ItemModel itemModel) throws Exception {
		try {
			return itemService.addItem(itemModel);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/getAllLostItems", method = RequestMethod.GET)
	public List getAllLostItems() {
		try {
			return itemService.getAllLostItems();
		} catch (Exception e) {
			throw e;
		}
	}

	@RequestMapping(value = "/getAllFoundItems", method = RequestMethod.GET)
	public List getAllFoundItems() {
		try {
			return itemService.getAllFoundItems();
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	@RequestMapping(value="/searchLostItem/{name}",method = RequestMethod.GET)
	public List searchLostItems(@PathVariable String name)
	{
		try
		{
			List items=new ArrayList<>();
			return itemService.searchLostItems(name);
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	@RequestMapping(value="/searchFoundItem/{name}",method=RequestMethod.GET)
	public List searchFoundItems(@PathVariable String name)
	{
		try
		{
			return itemService.searchFoundItems(name);
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	
	@RequestMapping(value="/removeItem/{id}",method=RequestMethod.GET)
	public String removeItem(@PathVariable int id)
	{
		try
		{
			int i=itemService.removeItem(id);
			if(i>0)
			{
				return "Item removed successfully";
			}
			else
			{
				throw new Exception("Error");
			}
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
	}
}
