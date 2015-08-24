package com.hockeyhurd.api.util.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingFormatArgumentException;

/**
 * Class containing IO code for writing and reading text based files.
 *
 * @author hockeyhurd
 * @version 4/26/2015.
 */
public final class FileUtils {

	// TODO: Implement proper logging system for this class!

	private FileUtils() {
	}

	/**
	 * Creates new file object from path.
	 *
	 * @param path path to create object from.
	 * @return file object.
	 */
	public static File createFile(String path) {
		return new File(path);
	}

	/**
	 * Function used to check if a file exists from specified path.
	 *
	 * @param file file object to test.
	 * @return true if file exists, else returns false.
	 */
	public static boolean exists(File file) {
		return file.exists();
	}

	/**
	 * Function used to 'rename' file should it exist.
	 *
	 * @param file file object as reference.
	 * @return new file object with alternate name.
	 */
	public static File getAltFile(File file) {
		int index = 0;
		while (exists(file)) {
			file = createFile(++index + file.getName());
		}

		return file;
	}

	/**
	 * Function used to get name of file.
	 *
	 * @param path path to file.
	 * @return name of path if exists, else returns false.
	 */
	public static String getFileName(String path) {
		File file = createFile(path);
		return exists(file) ? file.getName().substring(0, file.getName().lastIndexOf('.')) : null;
	}

	/**
	 * Method used to write an array of FormattedObject(s) to a text file.
	 *
	 * @param path path to write to.
	 * @param format format to use (Set to null if not applicable).
	 * @param overwrite whether we can overwrite the file should it exist.
	 * @param objects array to print.
	 * @throws IOException
	 */
	public static void writeToFile(String path, String format, boolean overwrite, FormattedObject... objects) throws IOException {

		// Get starting file object.
		File file = createFile(path);

		// If exists, rename file.
		if (!overwrite && exists(file)) file = getAltFile(file);

		PrintWriter pw = new PrintWriter(file);

		// Loop through array printing out objects.
		int counter = 0;
		for (FormattedObject o : objects) {
			try {
				if (o != null && o.getArray().length > 0) {
					if (format != null && format.length() > 1 && o.getArray().length > 0) {
						if (counter++ > 0) pw.println();
						pw.printf(format, o.getArray());
					}
					else pw.println(String.valueOf(o));
				}
			}

			catch (MissingFormatArgumentException e) {
				continue;
			}
		}

		// Flush and close output stream.
		pw.flush();
		pw.close();
	}

	/**
	 * Method used to append formatted objects to file at said path.
	 *
	 * @param path path to write to.
	 * @param objects objects to write.
	 */
	public static void appendToFile(String path, FormattedObject... objects) {
		File file = createFile(path);

		if (!exists(file)) {
			System.err.println("File at path: " + path + " doesn't exist!");
			return;
		}

		FormattedObject[] readArray;
		try {
			readArray = readFromFile(path);
		}
		catch (IOException e) {
			e.printStackTrace();
			return;
		}

		FormattedObject[] newArray = new FormattedObject[objects.length + readArray.length];

		int i = 0;
		for (; i < objects.length; i++) {
			newArray[i] = objects[i];
			System.out.println("Added:\t" + objects[i].toString());
		}

		for (int j = 0; j < readArray.length; j++) {
			newArray[i + j] = readArray[j];
			System.out.println("Added:\t" + readArray[j].toString());
		}

		System.out.println("Read and organizing data complete!  Re-writing file now!");

		try {
			writeToFile(path, null, true, newArray);
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Function used to read a file from given path.
	 *
	 * @param path path to read from.
	 * @return FormattedObject array containing all read objects.
	 * @throws IOException
	 */
	public static FormattedObject[] readFromFile(String path) throws IOException {

		// Create and store file object.
		File file = createFile(path);

		// Make sure file exists and if not, warn user!
		if (!exists(file)) {
			System.err.println("Error! File doesn't exist!");
			return null;
		}

		// Tracking lists.
		List<FormattedObject> list = new ArrayList<FormattedObject>();

		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		int maxSpace = 5;
		int counter = 0;

		while (counter <= maxSpace) {
			if ((line = reader.readLine()) != null) {
				if (counter > 0) counter = 0;
				list.add(new FormattedObject(line));
			}
			else counter++;
		}

		reader.close();

		// Return list --> FormattedObject array.
		return list.toArray(new FormattedObject[list.size()]);
	}

	/**
	 * A recursive function allows getting all files in a given directory.
	 *
	 * @param path path to check
	 * @return list of files if successful else returns null.
	 */
	public static List<File> getFiles(String path) {

		// get file from path.
		File file = createFile(path);

		// check if valid file.
		if (file == null || !exists(file)) return null;

		// instantiate resulting list.
		List<File> files = new ArrayList<File>();

		// if current file path is a file, add to list.
		if (file.isFile()) files.add(file);

			// else if it is a directory:
		else {

			// get files in directory.
			File[] fileArray = file.listFiles();

			// check if directory is not empty (contains something).
			if (fileArray != null && fileArray.length > 0) {

				((ArrayList) files).ensureCapacity(fileArray.length);

				// subfile list.
				List<File> subFiles;
				for (File f : fileArray) {

					// iterate over each file and call recursive method.
					subFiles = getFiles(f.getPath());

					// if subfiles has something in list, add it to main list.
					if (subFiles != null && subFiles.size() > 0) {
						for (File f2 : subFiles) {
							files.add(f2);
						}
					}
				}
			}
		}

		return files;
	}
}
