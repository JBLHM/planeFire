package cn.first.fire.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.first.fire.util.ImageUtil;

public class BigPlane extends EnemyPlane {
	//����:��ͼ��
	
	
		//��ըЧ��
		//���Ŵ�л�ͼƬ�л�
			private static BufferedImage[] IMAGES;
			static {
				IMAGES=new BufferedImage[5];
				for (int i=0;i<IMAGES.length;i++) {
					IMAGES[i]=ImageUtil.readImage("bigplane"+i+".png");
				}
			}
		//��Ϊ:
		//�ٿ���
		//���ƶ�
		//�۳���
		public BigPlane(){
		//(0,-�ɻ���height)��(��Ļ���-�ɻ�weight,-�ɻ���height)֮�������
		//��(0~��Ļ���-�ɻ�weight,-�ɻ���height)
			
			super(IMAGES[0],new Random().nextInt(ShootGame.WIDTH-IMAGES[0].getWidth()),-IMAGES[0].getHeight());
			life=3;
			score=3;
			}
		//����
			public List<EnemyBullet> fire()
			{
				List<EnemyBullet> list = new ArrayList<EnemyBullet>();
		
				list.add(new EnemyBullet(this.getX()+IMAGES[0].getWidth()/3-8, this.getY()+IMAGES[0].getHeight()/2));
				list.add(new EnemyBullet(this.getX()+IMAGES[0].getWidth()*2/3, this.getY()+IMAGES[0].getHeight()/2));
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

