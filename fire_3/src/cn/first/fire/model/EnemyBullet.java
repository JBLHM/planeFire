package cn.first.fire.model;

import java.awt.image.BufferedImage;

import cn.first.fire.util.ImageUtil;

//小敌机子弹
public class EnemyBullet extends Bullet{
	//特征:图标
	
	//定义多张图片
	private static BufferedImage[] IMAGES;
	static {
		IMAGES=new BufferedImage[5];
		for (int i=0;i<IMAGES.length;i++) {
			IMAGES[i]=ImageUtil.readImage("enemybullet"+i+".png");
		}
	}
	public EnemyBullet(int x, int y){
		super(IMAGES[0], x, y);
	}
	
	//小敌机子弹移动
	public void move()
	{
		this.y+=3;
		
	}
	//
	//出界：
	public Boolean isOutOfBound(){
		//必须要等飞机机身出去界面之后才算出界
		return this.y >= ShootGame.HEIGHT + image.getHeight();
	}
	//重写getImage方法
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
