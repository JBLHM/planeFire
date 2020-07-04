package cn.first.fire.model;

import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;

import cn.first.fire.util.ImageUtil;



/**
 * 英雄飞机
 */
public class HeroPlane {
//英雄飞机特征:①图标
	//实现动态效果,改用数组,注释掉以下行
//	private BufferedImage image=ImageUtil.readImage("heroplane0.png");
	private static BufferedImage[] images;
	//静态块
	static{
	images=new BufferedImage[2];
	for(int i=0;i<images.length;i++)
	{
		images[i]=ImageUtil.readImage("heroplane"+i+".png");
	}
	}
//	②生命,英雄飞机默认10条命
	//生命值
	private int life=100;
	//分数
	private int score=0;
	//行为:加分,建命
	//加分
	public void addScore(int i)
	{
		this.score+=i;
		
	}
	//减命
	public void subLife(int i)
	{
		this.life-=i;
	}
	//衍生--判断你是否还有命
	public Boolean hasLife()
	{
		return this.life>0;
	}
//③分数
	
/**
	 * @return the life
	 */
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

	//④位置(x轴,y轴)
	private int x;
	/**
	 * @return the images
	 */
	public static BufferedImage[] getImages() {
		return images;
	}
	/**
	 * @param images the images to set
	 */
	public static void setImages(BufferedImage[] images) {
		HeroPlane.images = images;
	}
	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}
	/**
	 * @return the flag
	 */
	public Boolean getFlag() {
		return flag;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	private int y;
	/**
	 * @return the x
	 */
	//状态
	public static int ALIVE = 0;  //活着状态
	public static int DEATH = 1;  //死的状态
	public static int DELETE = 2;  //删除状态
			
	private int state = ALIVE;  //默认是活着状态
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
	/**
	 * @return the image
	 */
	//重写getImage(),setImage()
	/*
	public BufferedImage getImage() {
		return image;
	}
	*/
	private Boolean flag=true;
	public BufferedImage getImage()
	{
		flag=!flag;
		return images[flag?0:1];
	}
	
	
	
	
	
	/**
	 * @param image the image to set
	 */
	/*
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	*/
//英雄飞机行为:①出场②射击(fire)③碰撞
	
	
	//④移动(move)

	public void move(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	//发射子弹
	public List<HeroBullet> fire()
	{
		List<HeroBullet> listt=new ArrayList<HeroBullet>(); 
		//一次性发一枚子弹,如果一次发10枚子弹则将下面的位置变化,即注释内容
		listt.add(new HeroBullet(this.x, this.y));
		/*
		listt.add(new HeroBullet(this.x, this.y-48));
		listt.add(new HeroBullet(this.x, this.y-96));
		listt.add(new HeroBullet(this.x, this.y-144));
		listt.add(new HeroBullet(this.x, this.y-196));
		*/
		return listt;
	}
	
}
