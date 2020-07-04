package cn.first.fire.model;

import java.awt.image.BufferedImage;
import java.util.List;

import cn.first.fire.util.ImageUtil;

public abstract class EnemyPlane {
	protected BufferedImage image ;
	//生命
		protected int life;
		//分数
		protected int score;
		protected int x=0;
		protected int y=0;
		//状态
		public static int ALIVE = 0;  //活着状态
		public static int DEATH = 1;  //死的状态
		public static int DELETE = 2;  //删除状态
				
		protected int state = ALIVE;  //默认是活着状态
		public EnemyPlane(BufferedImage image,int x,int y)
		{
			this.image=image;
			this.x=x;
			this.y=y;
		}
		//移动方法
		public void move()
		{
			y+=3;
		}
		//开火
		public abstract List<EnemyBullet> fire();
		//减命
		public void subLife(int i)
		{
			this.life-=i;
		}
				
		//判断是否有命
		public Boolean hasLife()
		{
			return this.life>0;
		}

		//出界：
		public boolean isOutOfBound(){
			//必须要等飞机机身出去界面之后才算出界
			return this.y >= ShootGame.HEIGHT + image.getHeight();
		}
		//获取图片
		public abstract BufferedImage getImage();
		//get(),set()
		public void setImage(BufferedImage image) {
			this.image = image;
		}
		//重写的getImage()
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
