package cn.first.fire.model;

import java.awt.image.BufferedImage;

import cn.first.fire.util.ImageUtil;

//С�л��ӵ�
public class EnemyBullet extends Bullet{
	//����:ͼ��
	
	//�������ͼƬ
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
	
	//С�л��ӵ��ƶ�
	public void move()
	{
		this.y+=3;
		
	}
	//
	//���磺
	public Boolean isOutOfBound(){
		//����Ҫ�ȷɻ������ȥ����֮��������
		return this.y >= ShootGame.HEIGHT + image.getHeight();
	}
	//��дgetImage����
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
