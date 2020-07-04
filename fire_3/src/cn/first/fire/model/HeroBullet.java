package cn.first.fire.model;

import java.awt.image.BufferedImage;

import cn.first.fire.util.ImageUtil;

//英雄子弹
public class HeroBullet extends Bullet{
	//子弹图标
	private BufferedImage image = ImageUtil.readImage("herobullet0.png");

	
	//多张子弹图片切换
	private static BufferedImage[] IMAGES;
	static {
		IMAGES=new BufferedImage[5];
		for (int i=0;i<IMAGES.length;i++) {
			IMAGES[i]=ImageUtil.readImage("herobullet"+i+".png");
		}
	}
	//行为:移动
	public HeroBullet(int x,int y)
	{
		super(IMAGES[0],x, y);
	}
	//行为:出界
	public Boolean isOutOfBound()
	{
		//return this.y<0?true:false;
		return this.y<0;
	}
	//重写getImage()
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
	/**
	 * @param image the image to set
	 */
	
	public void move()
	{
		this.y-=30;
		//this.y-=30;
		
	}
}
