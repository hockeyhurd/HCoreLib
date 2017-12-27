package com.hockeyhurd.hcorelib.api.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hockeyhurd.hcorelib.api.util.StringUtils;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author hockeyhurd
 * @version 12/27/2017.
 */
public class VersionGen {

    private static final Gson GSON_BUILDER = new GsonBuilder().setPrettyPrinting().create();
    private final File VERSION_DIRECTORY;

    public VersionGen(final String modID) {
        VERSION_DIRECTORY = new File("forgeversion" + File.separator + modID);

        if (!VERSION_DIRECTORY.exists()) {
            final boolean result = VERSION_DIRECTORY.mkdirs();

            if(!result)
                throw new IllegalStateException("Cannot create recipe output dir!");
        }
    }

    public void generateVersion(final VersionData latestVersion, final VersionData recommendedVersion, final String homepageURL) {
        if (latestVersion == null || recommendedVersion == null || !StringUtils.nullCheckString(homepageURL)) {
            HCoreLibMain.logHelper.severe("Could not generate forge version checker json!!");
        }

        final Map<String, Object> json = new TreeMap<>();

        json.put("homepage", homepageURL);

        // append mcversioning:
        getMCVersioning(latestVersion, recommendedVersion, json);

        // append "promos":
        getPromos(latestVersion, recommendedVersion, json);

        final File outputJsonFile = new File(VERSION_DIRECTORY, "update.json");

        try (FileWriter fileWriter = new FileWriter(outputJsonFile)) {
            GSON_BUILDER.toJson(json, fileWriter);
        }

        catch (IOException e) {
            HCoreLibMain.logHelper.severe("Failed to write JSON", e);
        }
    }

    private static Map<String, Object> getMCVersioning(final VersionData latestVersion, final VersionData recommendedVersion, final Map<String, Object> json) {
        final Map<String, Object> versionMap = new TreeMap<>();

        if (!latestVersion.equals(recommendedVersion)) {
            versionMap.put(latestVersion.toString(), latestVersion.versionLink);
            versionMap.put(recommendedVersion.toString(), recommendedVersion.versionLink);
        }

        else {
            versionMap.put(latestVersion.toString(), latestVersion.versionLink);
        }

        json.put("1.12.2", versionMap);

        return versionMap;
    }

    private static Map<String, Object> getPromos(final VersionData latestVersion, final VersionData recommendedVersion, final Map<String, Object> json) {
        final Map<String, Object> promosMap = new TreeMap<>();

        promosMap.put("1.12.2-latest", latestVersion.toString());
        promosMap.put("1.12.2-recommended", recommendedVersion.toString());

        json.put("promos", promosMap);

        return promosMap;
    }

    public static class VersionData {

        public final int majorVersion;
        public final int minorVersion;
        public final int buildNumber;
        public final String versionLink;
        public final String changelog;

        public VersionData(int majorVersion, int minorVersion, int buildNumber, String versionLink, String changelog) {
            this.majorVersion = majorVersion;
            this.minorVersion = minorVersion;
            this.buildNumber = buildNumber;
            this.versionLink = versionLink;
            this.changelog = changelog;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;

            final VersionData that = (VersionData) obj;

            if (buildNumber != that.buildNumber)
                return false;
            if (minorVersion != that.minorVersion)
                return false;
            if (majorVersion != that.majorVersion)
                return false;
            if (!versionLink.equals(that.versionLink))
                return false;
            return changelog.equals(that.changelog);
        }

        @Override
        public int hashCode() {
            int result = buildNumber;
            result = 31 * result + minorVersion;
            result = 31 * result + majorVersion;
            result = 31 * result + versionLink.hashCode();
            result = 31 * result + changelog.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return String.format("%d.%d.%d", majorVersion, minorVersion, buildNumber);
        }
    }

}
