package com.tasd.seekers;

import java.io.InputStream;

import org.lightcouch.CouchDbClient;
import org.lightcouch.Response;



public class CouchDBHelper {

	private static CouchDbClient db;

	public static CouchDbClient getInstance() {
		if (db == null) {
			db = new CouchDbClient("pdf", true, "http", "pdfdb", 5984, "couchdb", "1234");
		}
		return db;
	}

	public static void saveDocument(String username, InputStream file) {
		Response response = getInstance().saveAttachment(file, username + "_curriculum.pdf", "application/pdf", username, null);
		System.out.println("REVISION: " + response.getRev());
	}

	public static InputStream getDocument(String username) {
		 return getInstance().find("doc_id/" + username + "_curriculum.pdf");
	}
}
