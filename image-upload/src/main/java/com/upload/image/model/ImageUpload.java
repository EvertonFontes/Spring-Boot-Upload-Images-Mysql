package com.upload.image.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class ImageUpload {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String fileName;

	private String fileType;

	@Lob
	private byte[] data;

	public ImageUpload() {}

	public ImageUpload(String fileName, String fileType, byte[] data) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImageUpload other = (ImageUpload) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
