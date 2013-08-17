package at.iaik.websharkgwt.server.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import at.iaik.websharkgwt.server.utils.Utils;
import at.iaik.websharkgwt.server.webserverstorage.PCAPFileStorageSingleton;

@SuppressWarnings("serial")
public class PCAPFileUploadServlet extends HttpServlet {
	
	//check out: http://stackoverflow.com/questions/2422468/how-to-upload-files-in-jsp-servlet/2424824#2424824
    public void doPost(HttpServletRequest request, HttpServletResponse res)
        throws ServletException, IOException {
    	String sessionID = request.getSession().getId();
    	System.out.println("PcapFileUpload Was called");
    	/*
    	try {
			ServletFileUpload upload = new ServletFileUpload();
	    	FileItemIterator iterator = upload.getItemIterator(request);
	        FileItemStream item = iterator.next();
 	        InputStream pcapFileInputStream = item.openStream();
 	        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
 	        Utils.copyStream(pcapFileInputStream, bOut);
 	        pcapFileInputStream.close();
 	        bOut.close();
 	        PCAPFileStorageSingleton pcapFileStorageSingleton =  PCAPFileStorageSingleton.getInstance();
 	        pcapFileStorageSingleton.addFile(sessionID, bOut.toByteArray());
 	    } catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      */
    }
    
}
