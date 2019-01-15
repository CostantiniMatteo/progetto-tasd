package com.tasd.seekers;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.lightcouch.CouchDbClient;
import org.lightcouch.NoDocumentException;
import org.lightcouch.Response;

public class CouchDBHelper {

	private static CouchDbClient db;
	private static Map<String, String> revs = new HashMap();
	
	public static CouchDbClient getInstance() {
		if (db == null) {
			db = new CouchDbClient("pdf", true, "http", "pdfdb", 5984, "couchdb", "1234");
		}
		return db;
	}

	public static void saveDocument(String username, InputStream file) {
		Response response = getInstance().saveAttachment(file, username + "_curriculum.pdf", "application/pdf",
				username, null);
		revs.put(username, response.getRev());
	}

	public static InputStream getDocument(String username) {
		try {
			return getInstance().find(username + "/" + username + "_curriculum.pdf");
		} catch (NoDocumentException e) {
			return null;
		}
	}

	public static void deleteDocument(String username) {
		getInstance().remove(username, revs.get(username));
	}
}
