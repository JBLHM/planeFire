package cn.first.fire.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.first.fire.util.ImageUtil;
public class BossPlane extends EnemyPlane {
		//特征:①图标
		
		
			//爆炸效果
			//多张轰炸机图片切换
				private static BufferedImage[] IMAGES;
				static {
					IMAGES=new BufferedImage[5];
					for (int i=0;i<IMAGES.length;i++) {
						IMAGES[i]=ImageUtil.readImage("bossplane"+i+".png");
					}
				}
			//行为:
			//①开火
			//②移动
			//③出界
			public BossPlane(){
			//(0,-飞机的height)和(屏幕宽度-飞机weight,-飞机的height)之间的区域
			//即(0~屏幕宽度-飞机weight,-飞机的height)
				
				super(IMAGES[0],new Random().nextInt(ShootGame.WIDTH-IMAGES[0].getWidth()),-IMAGES[0].getHeight());
				life=5;
				score=5;
				}
			//开火
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


