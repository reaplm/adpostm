package com.adpostm.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.adpostm.controller.Utils.Uploadcare;

public class TestUploadcare {
	/**
	 * File uuid must be real otherwise FileNotFoundException is thrown
	 * @throws JSONException
	 */
	@Test
	public void testDeleteBatch() throws JSONException {
			Uploadcare ucare = new Uploadcare();
			
			//Attempt to delete two images
			JSONObject jsonObject = ucare.deleteBatch(Arrays.asList("e45920a4-1d42-43b0-ac9b-a4e797f8edb4", 
					"d32a0038-f9bd-4948-9ac5-4e23352e0f07"));
			
			System.out.println("------------------------Delete Batch-------------------------");	
			System.out.println(jsonObject.toString(2));	
			
			String status = jsonObject.getString("status");
			
			JSONArray result = jsonObject.getJSONArray("result");
			JSONObject response0 = result.getJSONObject(0);
			JSONObject response1 = result.getJSONObject(1);
			assert(status.equals("ok"));
			
			//response for first image
			assertFalse(response0.get("datetime_removed").equals("null"));
			assertTrue(response0.getString("datetime_stored").equals("null"));
			assert(response0.get("uuid").equals("e45920a4-1d42-43b0-ac9b-a4e797f8edb4"));
			assert(response0.get("url").equals("https://api.uploadcare.com/files/e45920a4-1d42-43b0-ac9b-a4e797f8edb4/"));
		
			//response for second image
			assertFalse(response1.get("datetime_removed").equals("null"));
			assertTrue(response1.getString("datetime_stored").equals("null"));
			assert(response1.get("uuid").equals("d32a0038-f9bd-4948-9ac5-4e23352e0f07"));
			assert(response1.get("url").equals("https://api.uploadcare.com/files/d32a0038-f9bd-4948-9ac5-4e23352e0f07/"));
		
	}
	@Test
	public void testDeleteFile() throws JSONException {
			Uploadcare ucare = new Uploadcare();
			
			//Attempt to delete two images
			JSONObject jsonObject = ucare.deleteFile("4f1ef9c1-498a-4319-92fe-616d6a5a297e");
			
			System.out.println("------------------------Delete single file-------------------------");	
			System.out.println(jsonObject.toString(2));	
									
			//response
			assertFalse(jsonObject.getString("datetime_removed").equals("null"));
			assertTrue(jsonObject.getString("datetime_stored").equals("null"));
			assert(jsonObject.get("uuid").equals("4f1ef9c1-498a-4319-92fe-616d6a5a297e"));
			assert(jsonObject.get("url").equals("https://api.uploadcare.com/files/4f1ef9c1-498a-4319-92fe-616d6a5a297e/"));
		
		
	}
	@Test
	public void testGetFileInfo() throws JSONException {
		Uploadcare ucare = new Uploadcare();
		
		JSONObject jsonObject = ucare.getFileInfo("bc7482a5-132a-4fe3-a129-7a20773816c9");
		
		System.out.println("-------------------------------------Get File Info------------------");
		System.out.println(jsonObject.toString(2));
		
		assertTrue(jsonObject.getString("datetime_removed").equals("null"));
		assertFalse(jsonObject.getString("datetime_stored").equals("null"));
		assert(jsonObject.get("uuid").equals("bc7482a5-132a-4fe3-a129-7a20773816c9"));
		assert(jsonObject.get("url").equals("https://api.uploadcare.com/files/bc7482a5-132a-4fe3-a129-7a20773816c9/"));
	
		
	}
}
