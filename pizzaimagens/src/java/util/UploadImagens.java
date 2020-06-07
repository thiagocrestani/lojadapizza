/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
/**
 *
 * @author thiagocrestani
 */
public class UploadImagens {
    JavaImageResizer javaImageResizer = new JavaImageResizer();
    private static final long serialVersionUID = 1L;
    private static final String DATA_DIRECTORY = "imguploaded";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;
    public String upload(String root, boolean isMultipart, FileItem item, String novonome){
        
               
	        	//FileItemFactory factory = new DiskFileItemFactory();

	 
	        	//ServletFileUpload upload = new ServletFileUpload(factory);
	            try {
	            	// Parse the request
	            	//List /* FileItem */ items = upload.parseRequest(request);
	               // Iterator iterator = items.iterator();
	               // while (iterator.hasNext()) {
	                   // FileItem item = (FileItem) iterator.next();
	                    if (!item.isFormField()) {
                                //String novonome = "2";
	                        String fileName = item.getName();
                                String extensao = fileName.substring(fileName.lastIndexOf("."));
                                String antigonome = novonome;
                                novonome += extensao;
                                if(extensao.equals(".jpg") || extensao.equals(".png") || extensao.equals(".JPG") || extensao.equals(".PNG") || extensao.equals(".jpg") || extensao.equals(".JPEG")){
	                       // String root = getServletContext().getRealPath("/");
	                        File path = new File(root + "/imguploaded");
	                        if (!path.exists()) {
	                            boolean status = path.mkdirs();
	                        }
                                File uploadedFile = new File(path + "/" + novonome);
                                for(int i = 1;true; i++){
                                   if(uploadedFile.exists()){
                                       uploadedFile = new File(path + "/" + antigonome + String.valueOf(i) + extensao);
                                   }else{
                                       break;
                                   } 
                                }
                                
	                        item.write(uploadedFile);
                                javaImageResizer.resize(path + "/" + novonome);
                                return novonome;
                                }else{
                                  return "semimagem.jpg";  
                                }
                                //response.sendRedirect("admin/finalupload.jsp?img="+);
	                    }
	                //}
	            } catch (FileUploadException e) {
	                e.printStackTrace();
                        return "semimagem.jpg";
	            } catch (Exception e) {
	                e.printStackTrace();
                        return "semimagem.jpg";
	            }
	        
               return "semimagem.jpg";
        
    }
    
}
