package com.hockeyhurd.handler;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import com.hockeyhurd.mod.HCoreLibMain;
import com.hockeyhurd.util.AbstractReference;
import com.hockeyhurd.util.LogHelper;

public class UpdateHandler {

	private final short currentBuild; //  = LibReference.BUILD;
	private short latestBuild;
	private boolean upToDate = true;
	
	// NOTE: Just add build number + .jar
	private final String url; // = "http://75.68.113.97:8080/downloads/versions/ExtraTools+-1.1.";
	private String latestUrl = "";

	/**
	 * From created reference class, get data needed and store into memory.
	 * @param reference = created reference class that extends AbstractReference.
	 */
	public UpdateHandler(Class<? extends AbstractReference> reference) {
		short val = -1;
		String vUrl = "";
		try {
			val = reference.getDeclaredField("BUILD").getShort(reference);
			vUrl = reference.getDeclaredField("MOD_URL").get(reference).toString();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		this.currentBuild = val;
		this.url = vUrl;
		System.err.println(this.url);
	}

	/**
	 * Call this method to check for updates.
	 */
	public void check() {
		short copyBuild = currentBuild;
		String lastUrl;
		String copyUrl = lastUrl = url;
		copyUrl += copyBuild + ".jar";
		
		if (!exists(copyUrl)) {
			upToDate = true;
			this.latestBuild = copyBuild;
			this.latestUrl = "dev_build";
			return;
		}
		
		// Loop while there are still more 'updates found'
		while (exists(copyUrl)) {
			copyUrl = lastUrl;
			copyUrl += ++copyBuild + ".jar";
		}
		
		copyUrl = lastUrl;
		copyUrl += --copyBuild + ".jar";

		// Make sure the 'latest update' is not fake and if not, report 'not up to date'.
		if (copyBuild > currentBuild && exists(copyUrl)) upToDate = false;
		this.latestBuild = copyBuild;
		this.latestUrl = copyUrl;
	}
	
	/**
	 * @return true is up to date, else false.
	 */
	public boolean getUpToDate() {
		return upToDate;
	}
	
	/**
	 * @return latest build value.
	 */
	public short getLatestBuild() {
		return latestBuild;
	}
	
	/**
	 * @return latest url.
	 */
	public String getLatestURL() {
		return this.latestUrl;
	}
	
	/**
	 * @return gets mapping from key: build_version, value: download_link_url.
	 */
	public HashMap<Short,String> getMap() {
		HashMap<Short, String> ent = new HashMap<Short, String>();
		ent.put(getLatestBuild(), getLatestURL());
		return ent;
	}
	
	/**
	 * Localized method for checking is something exists in the world wide web at given url.
	 * @param urlCheck = url to test.
	 * @return true if exists, else false.
	 */
	private boolean exists(String urlCheck) {
		try {
			HttpURLConnection.setFollowRedirects(false);
			// note : you may also need
			// HttpURLConnection.setInstanceFollowRedirects(false)
			HttpURLConnection con = (HttpURLConnection) new URL(urlCheck).openConnection();
			con.setRequestMethod("HEAD");
			
			// seconds length
			float seconds = 1.0f;
			// Convert to ms.
			seconds *= 1000;
			// Convert to int.
			int timeout = (int) seconds;
			
			con.setConnectTimeout(timeout);
			con.setReadTimeout(timeout);
			
			boolean exists = con.getResponseCode() == HttpURLConnection.HTTP_OK;
			
			// Make sure we disconnect connection to server.
			con.disconnect();
			return exists; 
		}
		catch (Exception e) {
			// e.printStackTrace();
			HCoreLibMain.lh.warn("Could not find requested url!", urlCheck);
			HCoreLibMain.lh.warn("Update server must be down or this build has not yet been released properly!");
			return false;
		}
	}

}
