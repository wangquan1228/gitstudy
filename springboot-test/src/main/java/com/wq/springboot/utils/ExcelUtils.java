package com.wq.springboot.utils;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: wq
 * @Desc:
 * @Date: 2020/2/18 10:00
 * @Version 1.0
 */
public class ExcelUtils {

    public static void main(String[] args) {
        // 创建一个文件输出流实例

        FileOutputStream fileOut = null;

        /*

         * 目的：操作图片 实现：第一步-需要将图片从磁盘加载到内存中，第二步-java中有Image和BufferedImage这两种处理图片的类，

         * 第一种类似copy，不能对 图片进行操作，而BufferedImage则是将图片放入内存图片缓冲区中，可以对图片进行修改。

         */

        BufferedImage bufferImg = null;

// 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray

        try {

            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();

            bufferImg = ImageIO.read(new File("D:\\download\\猫和老鼠.jpg"));

            ImageIO.write(bufferImg, "jpg", byteArrayOut);


            HSSFWorkbook wb = new HSSFWorkbook();

            HSSFSheet sheet1 = wb.createSheet("test picture");

            // 画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）

            HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();

            // anchor主要用于设置图片的属性

            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 255, (short) 1, 1, (short) 5, 8);

            // 插入图片

            patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));

            File file = new File("C:\\Users\\lenovo\\Desktop\\test\\123.xls");

            file.createNewFile();

            fileOut = new FileOutputStream(file);

            // 写入excel文件

            wb.write(fileOut);

            System.out.println("----Excel文件已生成------");

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (fileOut != null) {

                try {

                    fileOut.close();

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }

        }
    }
}
