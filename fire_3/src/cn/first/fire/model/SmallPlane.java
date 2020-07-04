package cn.first.fire.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.first.fire.util.ImageUtil;
//小型机
public class SmallPlane extends EnemyPlane {
//特征:①图标
	
	
	//爆炸效果
	//多张小敌机图片切换
		private static BufferedImage[] IMAGES;
		static {
			IMAGES=new BufferedImage[5];
			for (int i=0;i<IMAGES.length;i++) {
				IMAGES[i]=ImageUtil.readImage("smallplane"+i+".png");
			}
		}
	//行为:
	//①开火
	//②移动
	//③出界
	public SmallPlane(){
	//(0,-飞机的height)和(屏幕宽度-飞机weight,-飞机的height)之间的区域
	//即(0~屏幕宽度-飞机weight,-飞机的height)
		
		super(IMAGES[0],new Random().nextInt(ShootGame.WIDTH-IMAGES[0].getWidth()),-IMAGES[0].getHeight());
		life=1;
		score=1;
		}
	//开火
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
