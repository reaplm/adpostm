package com.adpostm.controller.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class Uploadcare {
	private String baseUrl;
	private URL url;
	private HttpURLConnection connection;

	public Uploadcare() {
		this.baseUrl = "https://api.uploadcare.com/files/";
	}
	/**
	 * Delete a single file from storage
	 * url: https://api.uploadcare.com/files/ + uuid + /
	 * type: DELETE
	 * @param uuid
	 * @return JSON string
	 * 
	 * JSON response contains the following
	 * -datetime_removed
	 * -datetime_stored
	 * -datetime_uploaded
	 * -image_info: 
	 * 		{
	 *     		color_mode
	 *         	datetime_original
	 *          dpi": [w,h]
	 *          format
	 *          geo_location
	 *          height
	 *          orientation
	 *          width
	 *       }
	 * -is_image
	 * -is_ready
	 * -mime_type
	 * -original_file_url
	 * -original_filename
	 * -size
	 * -source
	 * -url
	 * -uuid
	 */
	public JSONObject deleteFile(String uuid) {
		BufferedReader rd;
		String line;
		String response = "";
		String targetUrl;
		JSONObject jsonObject = null;
		
		try {
			targetUrl = baseUrl + uuid + "/";
			
			url = new URL(targetUrl);
			connection = (HttpURLConnection)url.openConnection();
			
			//headers
			setHeaders();
			//type
			connection.setRequestMethod("DELETE");
			
			rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while((line = rd.readLine()) != null) {
				response += line;
			}
			rd.close();
			connection.disconnect();
			jsonObject =  new JSONObject(response);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		catch(JSONException ex) {
			ex.printStackTrace();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return jsonObject;
	}
	/**
	 * Delete a group of images. The input is a list of uuids.
	 * 
	 * @param uuidGroup
	 * @return JSONObject response
	 * 
	 * * Response from uploadcare takes the following format
	 * -problems: {}
	 * -result: [
	 *  		{
	 *   			datetime_removed
	 *   			datetime_stored
	 *   			datetime_uploaded
	 *   			image_info: {
	 *   				color_mode
	 *   				datetime_original
	 *   				dpi: [w,h]
	 *   				format
	 *   				geo_location
	 *   				height
	 *   				orientation
	 *   				width
	 *         		}
	 * 			is_image
	 * 			is_ready
	 * 			mime_type
	 * 			original_file_url
	 * 			original_filename
	 * 			size
	 * 			source
	 * 			url
	 * 			uuid
	 * 		}
	 * 	]
	 * -status
	 * 
	 * When theres an error the response is as follows
	 * 
	 * "problems": {
	 * 		    "uuid1": "Invalid",
	 * 		    "uuid2": "Invalid"
	 *   },
	 *   "result": [],
	 *    "status": "ok"
	 *   
	 */
	public JSONObject deleteBatch(List<String> uuidGroup) {
		String targetUrl = baseUrl + "storage/";
		BufferedReader rd;
		String line, jsonString;
		String response = "";
		JSONObject jsonObject = null;
		
		try {
			jsonString = "[";
			
			for(String uuid:uuidGroup)
				jsonString += "\"" + uuid + "\"" + ",";
			
			jsonString = jsonString.substring(0, jsonString.length()-1);
			jsonString += "]";
			
			URL url = new URL(targetUrl);
			connection = (HttpURLConnection)url.openConnection();
			
			//type
			connection.setRequestMethod("DELETE");
			//headers
			setHeaders();
			
			//write json string to body
			connection.setDoOutput(true);
			OutputStream os = connection.getOutputStream();
			os.write(jsonString.getBytes());
			os.flush();
			
			rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while((line = rd.readLine()) != null) {
				response += line;
			}
			rd.close();
			connection.disconnect();
			jsonObject =  new JSONObject(response);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		catch(JSONException ex) {
			ex.printStackTrace();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return jsonObject;

	}
	/**
	 * Get information about a single file
	 * 
	 * @param uuid
	 * @return JSONObject containing file information
	 * 
	 * * JSON response contains the following
	 * -datetime_removed
	 * -datetime_stored
	 * -datetime_uploaded
	 * -image_info: 
	 * 		{
	 *     		color_mode
	 *         	datetime_original
	 *          dpi": [w,h]
	 *          format
	 *          geo_location
	 *          height
	 *          orientation
	 *          width
	 *       }
	 * -is_image
	 * -is_ready
	 * -mime_type
	 * -original_file_url
	 * -original_filename
	 * -size
	 * -source
	 * -url
	 * -uuid
	 */
	public JSONObject getFileInfo(String uuid) {
		String targetUrl = baseUrl;
		BufferedReader rd;
		String line;
		String response = "";
		JSONObject jsonObject = null;
		
		try {
			targetUrl += uuid + "/";

			
			URL url = new URL(targetUrl);
			connection = (HttpURLConnection)url.openConnection();
			//type
			connection.setRequestMethod("GET");
			//headers
			setHeaders();
			
			rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while((line = rd.readLine()) != null) {
				response += line;
			}
			rd.close();
			connection.disconnect();
			jsonObject = new JSONObject(response);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		catch(JSONException ex) {
			ex.printStackTrace();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 *
	 * headers: { "Authorization": "Uploadcare.Simple publickey:privatekey" }
	 */
	private void setHeaders() {
		if(connection != null) {
			//headers
			connection.setRequestProperty("Accept", "application/vnd.uploadcare-v0.5+json");
			connection.setRequestProperty("Authorization", 
					"Uploadcare.Simple 402840513ca8fdd44f3b:248a2cdd20ea47dae231");
			connection.setRequestProperty("Content-Type", "application/json");
		}
	}
}
