package com.lj.app.core.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import org.apache.struts2.ServletActionContext;

public class FileUploadAndDownload {
	  private static final int BUFFER_SIZE = 16 * 1024;  
	  
	  private String storageFileName;  
	
	 public String getStorageFileName() {
		return storageFileName;
	}

	public void setStorageFileName(String storageFileName) {
		this.storageFileName = storageFileName;
	}

	public static void copy(File src, File dst) {  
	        try {  
	            InputStream in = null;  
	            OutputStream out = null;  
	            try {  
	                in = new BufferedInputStream(new FileInputStream(src),  
	                        BUFFER_SIZE);  
	                out = new BufferedOutputStream(new FileOutputStream(dst),  
	                        BUFFER_SIZE);  
	                byte[] buffer = new byte[BUFFER_SIZE];  
	                while (in.read(buffer) > 0) {  
	                    out.write(buffer);  
	                }  
	            } finally {  
	                if (null != in) {  
	                    in.close();  
	                }  
	                if (null != out) {  
	                    out.close();  
	                }  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	 }
	
	public void downloadFile(String filename,String filepath) throws Exception {
		 download(filepath,  filename,  ServletActionContext.getResponse().getOutputStream());
	}

	public  void download(String filepath, String filedisplay, OutputStream out)
			throws IOException {
		File f = new File(filepath);
		FileInputStream in = new FileInputStream(f);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] b = new byte[MagicNumber.INT_2048];
		int i = 0;
		while ((i = in.read(b)) != -1) {
			baos.write(b, 0, i);
		}

		ServletActionContext.getResponse().setContentType("bin");
		filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
		ServletActionContext.getResponse().addHeader("Content-Disposition",
				"attachment;filename=" + filedisplay);
		baos.writeTo(out);
		out.flush();
	}
}
