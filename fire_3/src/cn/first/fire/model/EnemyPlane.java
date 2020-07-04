package cn.first.fire.model;

import java.awt.image.BufferedImage;
import java.util.List;

import cn.first.fire.util.ImageUtil;

public abstract class EnemyPlane {
	protected BufferedImage image ;
	//����
		protected int life;
		//����
		protected int score;
		protected int x=0;
		protected int y=0;
		//״̬
		public static int ALIVE = 0;  //����״̬
		public static int DEATH = 1;  //����״̬
		public static int DELETE = 2;  //ɾ��״̬
				
		protected int state = ALIVE;  //Ĭ���ǻ���״̬
		public EnemyPlane(BufferedImage image,int x,int y)
		{
			this.image=image;
			this.x=x;
			this.y=y;
		}
		//�ƶ�����
		public void move()
		{
			y+=3;
		}
		//����
		public abstract List<EnemyBullet> fire();
		//����
		public void subLife(int i)
		{
			this.life-=i;
		}
				
		//�ж��Ƿ�����
		public Boolean hasLife()
		{
			return this.life>0;
		}

		//���磺
		public boolean isOutOfBound(){
			//����Ҫ�ȷɻ������ȥ����֮��������
			return this.y >= ShootGame.HEIGHT + image.getHeight();
		}
		//��ȡͼƬ
		public abstract BufferedImage getImage();
		//get(),set()
		public void setImage(BufferedImage image) {
			this.image = image;
		}
		//��д��getImage()
		/*
		
		*/
		
	/**
	 * @param image the image to set
	 */
		
		
		
		
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

		public int getState() {
			return state;
		}
		/**
		 * @param state the state to set
		 */
		public void setState(int state) {
			this.state = state;
		}
		public int getLife() {
			return life;
		}
		/**
		 * @param life the life to set
		 */
		public void setLife(int life) {
			this.life = life;
		}
		/**
		 * @return the score
		 */
		public int getScore() {
			return score;
		}
		/**
		 * @param score the score to set
		 */
		public void setScore(int score) {
			this.score = score;
		}
}
