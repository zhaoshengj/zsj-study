package com.zsj.nacos;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author zsj
 * @date 2019-08-27  17:20
 */
public class down {

    public static void main(String[] args) {
        down("http://image.uduojin.com/image/static/20181225160008957.MP4","pic");
    }


     public static void down(String url,String fileName){

        HttpURLConnection conn = null;
        InputStream inputStream = null;
        BufferedInputStream bis = null;
        FileOutputStream out = null;

        try{

            File file = new File("/Users/luqin001/Desktop/"+fileName);
            if(!file.isDirectory() && !file.exists()){
                file.mkdirs();
            }
           // out = new FileOutputStream(file+"/1.jpg");
            out = new FileOutputStream(file+"/1.mp4");
            // 建立链接
            URL httpUrl=new URL(url);
            conn=(HttpURLConnection) httpUrl.openConnection();
            //以Post方式提交表单，默认get方式
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // post方式不能使用缓存
            conn.setUseCaches(false);
            //连接指定的资源
            conn.connect();
            //获取网络输入流
            inputStream=conn.getInputStream();
            bis = new BufferedInputStream(inputStream);
            byte b [] = new byte[1024];
            int len = 0;
            while((len=bis.read(b))!=-1){
                out.write(b, 0, len);
            }
            System.out.println("下载完成...");

        }catch (Exception e){

        }finally {
            try {
                if(out!=null){
                    out.close();
                }
                if(bis!=null){
                    bis.close();
                }
                if(inputStream!=null){
                    inputStream.close();
                }
            }catch (Exception e){

            }

        }



    }
}
