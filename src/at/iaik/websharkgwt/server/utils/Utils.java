package at.iaik.websharkgwt.server.utils;

import java.io.InputStream;
import java.io.OutputStream;

public class Utils {
	public static void copyStream(InputStream inputStream, OutputStream outputStream) {
		final int bufferSize=2048;
        try	{
            byte[] bytes=new byte[bufferSize];
            while (true) {
              int count=inputStream.read(bytes, 0, bufferSize);
              if(count==-1)
                  break;
              outputStream.write(bytes, 0, count);
            }
        }
        catch(Exception ex) {
        	ex.printStackTrace();
        }
    }
}
