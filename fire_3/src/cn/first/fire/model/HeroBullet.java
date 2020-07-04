package cn.first.fire.model;

import java.awt.image.BufferedImage;

import cn.first.fire.util.ImageUtil;

//Ӣ���ӵ�
public class HeroBullet extends Bullet{
	//�ӵ�ͼ��
	private BufferedImage image = ImageUtil.readImage("herobullet0.png");

	
	//�����ӵ�ͼƬ�л�
	private static BufferedImage[] IMAGES;
	static {
		IMAGES=new BufferedImage[5];
		for (int i=0;i<IMAGES.length;i++) {
			IMAGES[i]=ImageUtil.readImage("herobullet"+i+".png");
		}
	}
	//��Ϊ:�ƶ�
	public HeroBullet(int x,int y)
	{
		super(IMAGES[0],x, y);
	}
	//��Ϊ:����
	public Boolean isOutOfBound()
	{
		//return this.y<0?true:false;
		return this.y<0;
	}
	//��дgetImage()
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
