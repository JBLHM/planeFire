package cn.first.fire.util;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * ��ϷͼƬ���ع�����
 */
public class ImageUtil {

	/**
	 * ����ָ�����ֵ�ͼƬ�����ڴ���(��Ϸ)
	 * @param name
	 * @return
	 */
	public static BufferedImage readImage(String name){
		
		//������ݼ��� ctrl + 1
		try {
			return  ImageIO.read(new File("images/", name));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;  //����Ҳ���ͼƬ����null
	}
}
