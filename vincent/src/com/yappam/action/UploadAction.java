package com.yappam.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class UploadAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	protected final Log log = LogFactory.getLog(this.getClass());
	
	private static final int BUFFER_SIZE=16*1024;
	
	//上传文件要的参数
//	private File[] upload;
//	private String[] uploadFileName;
//	private String[] uploadContentType;
	
	private File minPic ;
	private String minPicFileName ;
	private String minPicContentType ;
	/**
	 * <form action="uploadportrait.action" method="post" enctype="multipart/form-data">
						<ul>
							<li><div class="label">&nbsp;上传头像</div> <input type="file" name="minPic" id="protrait" class="text required" /> </li>
							<li><div class="label">&nbsp;</div> <button class="submit" id="submit_button" name="commit" type="submit" >上传</button> </li>
						</ul>
				</form>
	 */
	private int id ;
    
	//把源文件对象复制成目标文件对象
	private static boolean  copy(File src, File dst) {
    	boolean result=false;
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
            out = new BufferedOutputStream(new FileOutputStream(dst),BUFFER_SIZE);
            byte[] buffer = new byte[BUFFER_SIZE];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            result=true;
        } catch (Exception e) {
            e.printStackTrace();
            result=false;
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    
    public String execute() throws Exception {
    	ActionContext ctx = ActionContext.getContext() ;
    	try{
			//处理上传
    		String dstPath = "upload";
	        String tomcatPath = ServletActionContext.getServletContext().getRealPath(dstPath);
	        File file=new File(tomcatPath);
            if(!file.exists()){
            	file.mkdirs();
            }
            /**
             * 上传缩略图
             */
            String minPicName = null ;
            if(this.getMinPic()!=null){
            	String fileRename=generateFileName(this.getMinPicFileName());
            	String frilurl =  tomcatPath+"\\"+ fileRename ;
            	File dstFile = new File(frilurl);
	            if(copy(this.getMinPic(), dstFile)){
	            	minPicName = fileRename ;
	            }
            }
            File ffile = new File(tomcatPath+"\\"+minPicName) ;
            
//            Thumbnail thumbnail = new Thumbnail() ;
            
//			thumbnail.saveImageAsJpg(ffile, tomcatPath, 120, 120, minPicName) ;

//			System.out.println(minPicName);
	        //存数据
//            User user = userService.getUserById(this.getId()) ;
//            user.setHeadpic(minPicName) ;
//            
//            userService.changeMessage(user) ;
            ctx.put("msg", "上传成功") ;
            ctx.put("url", "uploadprotrait.jsp") ;
            
			return "error";
		}catch(Exception e){
			e.printStackTrace();
            ctx.put("msg", "NoMessage") ;
            ctx.put("url", "") ;
			return "error";
		}
    }
    
  public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	//重命名文件名
	public String generateFileName(String fileName){
        String formatDate = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
        int random = new Random().nextInt(10000);
        int position = fileName.lastIndexOf(".");
        String extension = fileName.substring(position);
        return formatDate + random + extension;
    }


//	public File[] getUpload() {
//		return upload;
//	}
//
//	public void setUpload(File[] upload) {
//		this.upload = upload;
//	}
//
//	public String[] getUploadFileName() {
//		return uploadFileName;
//	}
//
//	public void setUploadFileName(String[] uploadFileName) {
//		this.uploadFileName = uploadFileName;
//	}

//	public String[] getUploadContentType() {
//		return uploadContentType;
//	}
//
//	public void setUploadContentType(String[] uploadContentType) {
//		this.uploadContentType = uploadContentType;
//	}

	public static int getBUFFER_SIZE() {
		return BUFFER_SIZE;
	}

	public File getMinPic() {
		return minPic;
	}

	public void setMinPic(File minPic) {
		this.minPic = minPic;
	}

	public String getMinPicFileName() {
		return minPicFileName;
	}

	public void setMinPicFileName(String minPicFileName) {
		this.minPicFileName = minPicFileName;
	}

	public String getMinPicContentType() {
		return minPicContentType;
	}

	public void setMinPicContentType(String minPicContentType) {
		this.minPicContentType = minPicContentType;
	}

//	public File getMinPic() {
//		return protrait;
//	}
//
//	public void setMinPic(File minPic) {
//		this.protrait = minPic;
//	}

//	public String getMinPicFileName() {
//		return minPicFileName;
//	}
//
//	public void setMinPicFileName(String minPicFileName) {
//		this.minPicFileName = minPicFileName;
//	}
//
//	public String getMinPicContentType() {
//		return minPicContentType;
//	}
//
//	public void setMinPicContentType(String minPicContentType) {
//		this.minPicContentType = minPicContentType;
//	}

}
