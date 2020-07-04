package cn.first.fire.util;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 游戏图片加载工具类
 */
public class ImageUtil {

	/**
	 * 加载指定名字的图片进入内存中(游戏)
	 * @param name
	 * @return
	 */
	public static BufferedImage readImage(String name){
		
		//帮助快捷键： ctrl + 1
		try {
			return  ImageIO.read(new File("images/", name));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;  //如果找不到图片返回null
	}
}
