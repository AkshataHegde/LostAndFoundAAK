package com.lostandfound.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import com.lostandfound.Repository.ItemRepository;
import com.lostandfound.bean.ItemModel;
import com.lostandfound.entities.Item;
import com.lostandfound.services.ItemServiceImpl;
import com.lostandfound.utils.Validator;





@RunWith( PowerMockRunner.class )
public class ItemServiceImplTest {
	
	final static private String SUCCESS_MESSAGE="Item added successfully";
	final static private int ID=1;
	

	@Mock
	Validator validator;
	
	@Mock
	ItemRepository itemRepository;
	
	
	private ItemModel itemModel;
	private ItemModel itemModel1;
	private Item item;
	
	@InjectMocks
	ItemServiceImpl itemServiceImpl=new ItemServiceImpl();

	@Before
	public void setUp() throws Exception {
		
		itemModel=new ItemModel();
		itemModel.setName("Marker");
		itemModel.setDescription("Black colour");
		itemModel.setImageLocation("/home/Desktop");
		itemModel.setFoundDate("12/03/19995");
	}


	@Test
	public void testAddItem() throws Exception {
		try
		{
			doNothing().when(validator).validateFoundItemDetails(any(ItemModel.class));
			assertEquals(SUCCESS_MESSAGE,itemServiceImpl.addItem(itemModel));
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	@Test
	public void testGetAllFoundItems() {
		try
		{
			List<ItemModel> itemModelList;
			//itemModelList.add(itemModel);
			when(itemRepository.getAllFoundItems()).thenReturn(Arrays.asList(itemModel));
			//assertEquals(itemModelList,itemServiceImpl.getAllFoundItems());
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	@Test
	public void testGetAllLostItems() {
		try
		{
			
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	@Test
	public void testSearchLostItems() {
		try
		{
			
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	@Test
	public void testSearchFoundItems() {
		try
		{
			
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	@Test
	public void testRemoveItem() {
		try
		{
			when(itemRepository.removeItem(anyInt())).thenReturn(ID);
			assertEquals(1,itemServiceImpl.removeItem(ID));
		}
		catch(Exception e)
		{
			throw e;
		}
	}
}
