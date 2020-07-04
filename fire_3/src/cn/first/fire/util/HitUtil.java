package cn.first.fire.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import cn.first.fire.model.EnemyBullet;
import cn.first.fire.model.EnemyPlane;
import cn.first.fire.model.HeroBullet;
import cn.first.fire.model.HeroPlane;

/**
 * ��ײ������
 * @author dafei
 */
public class HitUtil {
	/**
	 * ��һ����ײ�߼���2��������ײ
	 *  ����1�Ĳ����ǣ����Ͻǵ�������(x1,y1)�������w1���߶���h1;
	 *  ����2�Ĳ����ǣ����Ͻǵ�������(x2,y2)�������w2���߶���h2��
	 */
	private static boolean isHit(int x1, int y1, int w1,int h1, int x2,int y2,int w2, int h2) {
		//���ߵľ��η�Χ	
		Rectangle tank1 = new Rectangle(x1,y1,w1,h1);
		
		Rectangle tank2 = new Rectangle(x2,y2,w2,h2);
		//�ж����������Ƿ��н�����crash Ϊ true ˵����ײ��
		return tank1.intersects(tank2);
	}
	
	//Ӣ���ӵ���л��ӵ���ײ
	public static boolean heroBulletHitBullet(HeroBullet hb, EnemyBullet eb) {
		BufferedImage x = hb.getImage();
		BufferedImage y = eb.getImage();
		return isHit(hb.getX(), hb.getY(), x.getWidth(), x.getHeight(), eb.getX(), eb.getY(), y.getWidth(), y.getHeight());
	}
	//Ӣ���ӵ���л���ײ
	public static boolean heroBulletHitPlane(HeroBullet hb, EnemyPlane eb) {
		BufferedImage x = hb.getImage();
		BufferedImage y = eb.getImage();
		return isHit(hb.getX(), hb.getY(), x.getWidth(), x.getHeight(), eb.getX(), eb.getY(), y.getWidth(), y.getHeight());
	}
	
	//Ӣ�۷ɻ���л��ӵ���ײ
	public static boolean heroPlaneHitBullet(HeroPlane hb, EnemyBullet eb) {
		BufferedImage x = hb.getImage();
		BufferedImage y = eb.getImage();
		return isHit(hb.getX()-x.getWidth()/6, hb.getY()-x.getHeight()/6, x.getWidth()/3, x.getHeight()/3, eb.getX(), eb.getY(), y.getWidth(), y.getHeight());
	}
	
	/**
	 * ��һ����ײ�߼���2��������ײ��ȡ���������Բ��ײ����2������תΪ2��Բ����ײ
	 *   r1  r2 �ֱ�Ϊ2��Բ�İ뾶
	 *   ��һ��ԲԲ�ģ�(x1��y1) 
	 *   �ڶ���ԲԲ�ģ�(x2��y2)  
	 *   
	 * ԭ�����ɶ���  
	 * ��ʽ��(x1-x2)^2 + (y1 - y2)^2 < r1 + r2   
	 *  
	 */
	//Ӣ�۷ɻ���л���ײ
	public static boolean heroPlaneHitEnamyPlane(HeroPlane hb, EnemyPlane eb) {
		BufferedImage x = hb.getImage();
		BufferedImage y = eb.getImage();
		
		int centerX1 =hb.getX();
		int centerY1 =hb.getY();
		int centerX2 = eb.getX() + y.getWidth()/2, centerY2 = eb.getY() + y.getHeight()/2;
		// ����Բ��Բ�ľ�
		double length = Math.sqrt(Math.pow(centerX1 - centerX2, 2)+ Math.pow(centerY1 - centerY2, 2));
		
		int r1 = Math.min(x.getWidth(), x.getHeight())/2;
		int r2 = Math.min(y.getWidth(), y.getHeight())/2;
		
		return length < (r1 + r2);
	}
	
}
