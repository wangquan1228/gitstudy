package com.wq.springboot.utils;

import java.io.ByteArrayOutputStream;

/**
 * @Author: wq
 * @Desc:
 * @Date: 2020/2/18 10:06
 * @Version 1.0
 */
public class ImgFile {
    private ByteArrayOutputStream pngByteArray;//
 
    private double width; 
             
    private double heigth; 
            
             

    public double getWidth() { 
  
   return width; 
 } 
             

    public void setWidth(double width) { 
  
   this.width = width; 
 } 
             

    public double getHeigth() { 
  
   return heigth; 
 } 
             

    public void setHeigth(double heigth) { 

this.heigth = heigth;
}

    public ByteArrayOutputStream getPngByteArray() {
        return pngByteArray;
    }

    public void setPngByteArray(ByteArrayOutputStream outPut) {
        this.pngByteArray = outPut;
    }
}
