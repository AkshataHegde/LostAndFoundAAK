package com.lostandfound.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lostandfound.Repository.ItemRepository;
import com.lostandfound.bean.ItemModel;
import com.lostandfound.entities.Item;
import com.lostandfound.utils.Validator;

@Transactional
@Component
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private Validator validator;

	@Override
	public String addItem(ItemModel itemModel) throws Exception {
		try {

			validator.validateFoundItemDetails(itemModel);

			Item item = new Item();
			item.setName(itemModel.getName());
			item.setImageLocation(itemModel.getImageLocation());
			item.setDescription(itemModel.getDescription());
			item.setFoundDate(itemModel.getFoundDate());
			item.setActive(true);

			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			Calendar calobj = Calendar.getInstance();
			item.setCreatedDate((df.format(calobj.getTime())));

			itemRepository.save(item);

			return "Item added successfully";
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List getAllFoundItems() {
		try {
			return itemRepository.getAllFoundItems();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List getAllLostItems() {
		try {

			return itemRepository.getAllLostItems();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List searchLostItems(String name) {
		try {
			List lostItems=new ArrayList<>();
			lostItems=itemRepository.searchLostItems(name);
			return lostItems;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List searchFoundItems(String name) {
		try {
			return itemRepository.searchFoundItems(name);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	@Override
	public int removeItem(int id)
	{
		try
		{
			int i=itemRepository.removeItem(id);	
		
			return i;
			
			
		}
		catch(Exception e)
		{
			throw e;
		}
	}

}
