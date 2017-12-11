package com.lostandfound.services;


import java.util.List;

import org.springframework.stereotype.Component;

import com.lostandfound.bean.ItemModel;


@Component
public interface ItemService {
	
	public String addItem(ItemModel itemModel) throws Exception;
	
	public List getAllLostItems();
	
	public List getAllFoundItems();
	
	public List searchLostItems(String name);
	
	public List searchFoundItems(String name);
	
	public int removeItem(int id);
	
	

}
