package cn.first.fire.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.first.fire.util.ImageUtil;
public class BossPlane extends EnemyPlane {
		//����:��ͼ��
		
		
			//��ըЧ��
			//���ź�ը��ͼƬ�л�
				private static BufferedImage[] IMAGES;
				static {
					IMAGES=new BufferedImage[5];
					for (int i=0;i<IMAGES.length;i++) {
						IMAGES[i]=ImageUtil.readImage("bossplane"+i+".png");
					}
				}
			//��Ϊ:
			//�ٿ���
			//���ƶ�
			//�۳���
			public BossPlane(){
			//(0,-�ɻ���height)��(��Ļ����-�ɻ�weight,-�ɻ���height)֮�������
			//��(0~��Ļ����-�ɻ�weight,-�ɻ���height)
				
				super(IMAGES[0],new Random().nextInt(ShootGame.WIDTH-IMAGES[0].getWidth()),-IMAGES[0].getHeight());
				life=5;
				score=5;
				}
			//����
				public List<EnemyBullet> fire()
				{
					List<EnemyBullet> list = new ArrayList<EnemyBullet>();
			
					list.add(new EnemyBullet(this.getX()+IMAGES[0].getWidth()/6, this.getY()+IMAGES[0].getHeight()/2));
					list.add(new EnemyBullet(this.getX()+IMAGES[0].getWidth()*2/6, this.getY()+IMAGES[0].getHeight()/2));
					list.add(new EnemyBullet(this.getX()+IMAGES[0].getWidth()*3/6, this.getY()+IMAGES[0].getHeight()/2));
					list.add(new EnemyBullet(this.getX()+IMAGES[0].getWidth()*4/6, this.getY()+IMAGES[0].getHeight()/2));
					list.add(new EnemyBullet(this.getX()+IMAGES[0].getWidth()*5/6, this.getY()+IMAGES[0].getHeight()/2));
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

