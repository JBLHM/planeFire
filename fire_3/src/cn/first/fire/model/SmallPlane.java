package cn.first.fire.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.first.fire.util.ImageUtil;
//С�ͻ�
public class SmallPlane extends EnemyPlane {
//����:��ͼ��
	
	
	//��ըЧ��
	//����С�л�ͼƬ�л�
		private static BufferedImage[] IMAGES;
		static {
			IMAGES=new BufferedImage[5];
			for (int i=0;i<IMAGES.length;i++) {
				IMAGES[i]=ImageUtil.readImage("smallplane"+i+".png");
			}
		}
	//��Ϊ:
	//�ٿ���
	//���ƶ�
	//�۳���
	public SmallPlane(){
	//(0,-�ɻ���height)��(��Ļ���-�ɻ�weight,-�ɻ���height)֮�������
	//��(0~��Ļ���-�ɻ�weight,-�ɻ���height)
		
		super(IMAGES[0],new Random().nextInt(ShootGame.WIDTH-IMAGES[0].getWidth()),-IMAGES[0].getHeight());
		life=1;
		score=1;
		}
	//����
		public List<EnemyBullet> fire()
		{
			List<EnemyBullet> list = new ArrayList<EnemyBullet>();
	
		 list.add(new EnemyBullet(this.x + image.getWidth()/2, 
				 this.y + image.getHeight()/2));
		 return list;
		}
	
	int index=0;
	public BufferedImage getImage() {
		if(state==ALIVE)
		{
			return image;
			
		}
		else if(state==DEATH)
		{
			index++;
			if(index<IMAGES.length)
			{
				return IMAGES[index];
			}
			
		}
		else
			state=DELETE;
		return null;
	}
	
	}
