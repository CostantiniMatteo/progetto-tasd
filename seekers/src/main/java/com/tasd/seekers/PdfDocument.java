package com.tasd.seekers;

import org.lightcouch.Document;

import com.google.gson.annotations.SerializedName;

public class PdfDocument extends Document {
	
	private String id;
	
	public String get_id() {
		return id;
	}

	public void set_id(String _id) {
		this.id = _id;
	}
}
