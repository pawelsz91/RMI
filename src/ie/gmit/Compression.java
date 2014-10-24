package ie.gmit;

import java.io.*;
import java.nio.file.*;
import java.util.zip.*;

public class Compression implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final String REGQUERY_UTIL = "reg query ";
	private static final String REGSTR_TOKEN = "REG_SZ";
	private static final String DESKTOP_FOLDER_CMD = REGQUERY_UTIL + "\"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\" + "Explorer\\Shell Folders\" /v DESKTOP";
	
	public static void compressGzipFile(String file, String gzipFile) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(gzipFile);
        GZIPOutputStream gzipOS = new GZIPOutputStream(fos);
        byte[] buffer = new byte[1024];
        int len;
        while((len=fis.read(buffer)) != -1){
            gzipOS.write(buffer, 0, len);
        }
        //close resources
        gzipOS.close();
        fos.close();
        fis.close();
    }
         
	public static void decompressGzipFile(String gzipFile, String newFile) throws Exception {
            FileInputStream fis = new FileInputStream(gzipFile);
            GZIPInputStream gis = new GZIPInputStream(fis);
            FileOutputStream fos = new FileOutputStream(newFile);
            byte[] buffer = new byte[1024];
            int len;
            while((len = gis.read(buffer)) != -1){
                fos.write(buffer, 0, len);
            }
            //close resources
            fos.close();
            gis.close();        
    }
    
	
	//finding path to the current user desktop
    public static String getCurrentUserDesktopPath() {
		try {
			Process process = Runtime.getRuntime().exec(DESKTOP_FOLDER_CMD);
			StreamReader reader = new StreamReader(process.getInputStream());

			reader.start();
			process.waitFor();
			reader.join();
			String result = reader.getResult();
			int p = result.indexOf(REGSTR_TOKEN);

			if (p == -1) return null;
			return result.substring(p + REGSTR_TOKEN.length()).trim();
		}
		catch (Exception e) {
			return null;
		}
	}

	static class StreamReader extends Thread {
		private InputStream is;
		private StringWriter sw;

		StreamReader(InputStream is) {
			this.is = is;
			sw = new StringWriter();
		}

		public void run() {
			try {
				int c;
				while ((c = is.read()) != -1)
					sw.write(c);
			}
			catch (IOException e) { 
				; 
			}
		}

    	String getResult() {
    		return sw.toString();
    	}
	}

}

