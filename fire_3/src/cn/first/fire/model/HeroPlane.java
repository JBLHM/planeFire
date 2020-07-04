package cn.first.fire.model;

import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;

import cn.first.fire.util.ImageUtil;



/**
 * Ӣ�۷ɻ�
 */
public class HeroPlane {
//Ӣ�۷ɻ�����:��ͼ��
	//ʵ�ֶ�̬Ч��,��������,ע�͵�������
//	private BufferedImage image=ImageUtil.readImage("heroplane0.png");
	private static BufferedImage[] images;
	//��̬��
	static{
	images=new BufferedImage[2];
	for(int i=0;i<images.length;i++)
	{
		images[i]=ImageUtil.readImage("heroplane"+i+".png");
	}
	}
//	������,Ӣ�۷ɻ�Ĭ��10����
	//����ֵ
	private int life=100;
	//����
	private int score=0;
	//��Ϊ:�ӷ�,����
	//�ӷ�
	public void addScore(int i)
	{
		this.score+=i;
		
	}
	//����
	public void subLife(int i)
	{
		this.life-=i;
	}
	//����--�ж����Ƿ�����
	public Boolean hasLife()
	{
		return this.life>0;
	}
//�۷���
	
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

	//��λ��(x��,y��)
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
	//״̬
	public static int ALIVE = 0;  //����״̬
	public static int DEATH = 1;  //����״̬
	public static int DELETE = 2;  //ɾ��״̬
			
	private int state = ALIVE;  //Ĭ���ǻ���״̬
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
	//��дgetImage(),setImage()
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
//Ӣ�۷ɻ���Ϊ:�ٳ��������(fire)����ײ
	
	
	//���ƶ�(move)

	public void move(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	//�����ӵ�
	public List<HeroBullet> fire()
	{
		List<HeroBullet> listt=new ArrayList<HeroBullet>(); 
		//һ���Է�һö�ӵ�,���һ�η�10ö�ӵ��������λ�ñ仯,��ע������
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
