package cn.first.fire.model;

import java.awt.image.BufferedImage;

//�ӵ�����
public abstract class Bullet {
	protected BufferedImage image;

	protected int x=0;
	protected int y=0;
	//�ӵ�״̬
	public static int ALIVE = 0;  //����״̬
	public static int DEATH = 1;  //����״̬
	public static int DELETE = 2;  //ɾ��״̬
			
	protected int state = ALIVE;  //Ĭ���ǻ���״̬
	public Bullet(BufferedImage image,int x,int y)
	{
		this.image=image;
		this.x=x;
		this.y=y;
	}
	public abstract void move();
	public abstract Boolean isOutOfBound();
	
	//getter,setter����
	public int getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	
}
