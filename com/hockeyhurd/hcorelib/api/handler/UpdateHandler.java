package com.hockeyhurd.hcorelib.api.handler;

import com.hockeyhurd.hcorelib.api.util.AbstractReference;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class UpdateHandler {

	private final int currentBuildNumber;
	private final String modName;
	private final String currentBuild; //  = LibReference.BUILD;
	private String latestBuild;
	private boolean upToDate = true;
	
	// NOTE: Just add build number + .jar
	private final String url;
	private String latestUrl = "";
	private String changelogUrl;
	private List<String> changelogList;

	/**
	 * From created reference class, get data needed and store into memory.
	 *
	 * @param currentBuild int build number.
	 * @param modName String mod name.
	 * @param version String mod version.
	 * @param versionURL String version URL.
	 * @param changelogUrl String changlelog URL.
	 */
	public UpdateHandler(int currentBuild, String modName, String version, String versionURL, String changelogUrl) {
		this.currentBuildNumber = currentBuild;
		this.modName = modName;
		this.currentBuild = version;
		this.url = versionURL;
		this.changelogUrl = changelogUrl;

		changelogList = new ArrayList<String>();
	}

	/**
	 * Call this method to check for updates.
	 */
	public void check() {
		checkForLatestBuild();
		checkForChangeLog();
	}

	/**
	 * Method used to check for latest build number.
	 */
	private void checkForLatestBuild() {
		if (exists(this.url)) {
			URL link = null;

			try {
				link = new URL(this.url);
			}

			catch (MalformedURLException e) {
				e.printStackTrace();
				HCoreLibMain.logHelper.warn("URL:", this.url, "doesn't exist!");
			}

			if (link == null) {
				this.upToDate = true;
				return;
			}

			try {
				Scanner sc = new Scanner(link.openStream());

				this.latestBuild = sc.next();

				int latestNumber = Integer.parseInt(this.latestBuild.substring(this.latestBuild.lastIndexOf('.') + 1, this.latestBuild.length()));

				if (this.currentBuildNumber < latestNumber) {
					this.upToDate = false;
					this.latestUrl = getAppropriateUrl();
				}

				sc.close();
			}
			catch (IOException e) {
				e.printStackTrace();
				HCoreLibMain.logHelper.warn("Error reading file! Please ensure data in file is valid!");
			}
		}

		else {
			HCoreLibMain.logHelper.warn("Error url doesn't exist!");
			this.upToDate = true;
		}
	}

	/**
	 * Method to check for changelog.
	 */
	private void checkForChangeLog() {
		boolean exists = exists(this.changelogUrl);

		if (!this.upToDate && exists) {
			URL link = null;

			try {
				link = new URL(this.changelogUrl);
			}

			catch (MalformedURLException e) {
				e.printStackTrace();
				HCoreLibMain.logHelper.warn("URL:", this.changelogUrl, "doesn't exist!");
			}

			// nothing exists, return.
			if (link == null) return;

			try {
				Scanner sc = new Scanner(link.openStream());

				while (sc.hasNext()) {
					String line = sc.next() + sc.nextLine();
					changelogList.add(line);
				}

				sc.close();

			}

			catch (IOException e) {
				e.printStackTrace();
				HCoreLibMain.logHelper.warn("Error reading file! Please ensure data in file is valid");
			}
		}

		else {
			if (!exists) HCoreLibMain.logHelper.warn("Changelog url doesn't exist or is null.");
			else HCoreLibMain.logHelper.info("No changelog found since we are UP to date!");
		}
	}
	
	/**
	 * @return true is UP to date, else false.
	 */
	public boolean getUpToDate() {
		return upToDate;
	}
	
	/**
	 * @return latest build value.
	 */
	public String getLatestBuild() {
		return latestBuild;
	}
	
	/**
	 * @return latest url.
	 */
	public String getLatestURL() {
		return this.latestUrl;
	}

	/**
	 * @return changelog as array of strings.
	 */
	public String[] getChangelogInfo() {
		if (!upToDate && !changelogList.isEmpty()) return changelogList.toArray(new String[changelogList.size()]);
		else return null;
	}

	/**
	 * @return gets mapping from key: build_version, value: download_link_url.
	 */
	public HashMap<String,String> getMap() {
		HashMap<String, String> ent = new HashMap<String, String>();
		ent.put(getLatestBuild(), getLatestURL());

		/*if (!upToDate && !changelogList.isEmpty()) {
			for (int i = 0; i < changelogList.size(); i++) {
				ent.put(String.valueOf(i), changelogList.get(i));
			}
		}*/

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
			HCoreLibMain.logHelper.warn("Could not find requested url!", urlCheck);
			HCoreLibMain.logHelper.warn("Update server must be DOWN or this build has not yet been released properly!");
			return false;
		}
	}
	
	/**
	 * Gets the appropriate latest url.
	 * 
	 * @return latest url.
	 */
	private String getAppropriateUrl() {
		return url.substring(0, url.lastIndexOf('/')) + "/versions/" + modName + "-" + (latestBuild.substring(1, latestBuild.length())) + ".jar";
	}

}
