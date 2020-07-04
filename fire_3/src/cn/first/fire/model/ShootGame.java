package cn.first.fire.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cn.first.fire.util.HitUtil;
import cn.first.fire.util.ImageUtil;
/*����İ�:����import java.awt.List;����import java.util.List;
���߲��ܹ���
*/


/*
 * ��Ϸ������
 * ����:
 * �ٳ�ʼ����Ϸ����(JPanel)
 * �ڼ�����Ϸ����
 * �ۼ�����Ϸ��ɫ(Ӣ�۷ɻ�,С�ͻ�,���ͻ�,��ը��,�ӵ�)
 * ����Ϸ�߼�����(�ƶ�,���,��ײ)
 * 
 */



public class ShootGame extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//���ڿ��
	public static final int WIDTH = 400; 
	//���ڵĳ���
	public static final int HEIGHT = 654;  
	//����ͼƬ
	private BufferedImage background = ImageUtil.readImage("background.jpg");
	private BufferedImage background1 = ImageUtil.readImage("background1.jpg"); //��ɫ
	private BufferedImage backgroundA = ImageUtil.readImage("background2.png"); //����
	//���һ������
	private BufferedImage backgroundB = ImageUtil.readImage("background2.png"); //����
	
	//��Ϸ״̬(��Ϸ��ʼ,��ͣ,��ֹ)
	//����״̬ͼƬ
	private BufferedImage pause = ImageUtil.readImage("pause.png");
	private BufferedImage start = ImageUtil.readImage("start.png");
	private BufferedImage gameover= ImageUtil.readImage("gameover.png");
	//����״̬(��Ϸ��ʼ,����,��ͣ,��ֹ)
	private static final int START=0;
	private static final int RUNNING=1;
	private static final int PAUSE=2;
	private static final int GAMEOVER=3;
	//��ǰ״̬,Ĭ���ǿ�ʼ״̬
	private int state=START;
	//���Ӣ�۶���ͼƬ��������
	private  HeroPlane heroPlane=new HeroPlane();
	//Ӣ�ۻ��ӵ�
	private List<HeroBullet> heroBullets=new ArrayList<HeroBullet>();
	
	//С�л�
	private List<EnemyPlane>  enemyPlanes = new ArrayList<EnemyPlane>();
	//С�л��ӵ�
	private List<EnemyBullet> enemyBullets=new ArrayList<EnemyBullet>();
	
	
	
	
	
	
	
	//Ӣ�۶���ͼƬ���뻭������
	public void paintHeroPlane(Graphics g)
	{
		BufferedImage heroimage=heroPlane.getImage();
		
		if(heroimage!=null)
		{
			//�ж��Ƿ��Ǹտ�ʼʱ
			if (heroPlane.getX()==0)
			{
				g.drawImage(heroimage,WIDTH/2-heroimage.getWidth()/2, 
									  HEIGHT/2+heroimage.getHeight(), null);
			}
			else{
				//��������ƶ�������
				g.drawImage(heroimage,heroPlane.getX()-heroimage.getWidth()/2, 
						heroPlane.getY() - heroimage.getHeight()/2, null);
			}
			//g.drawImage(heroimage, 0, 0, null);
			
		}
		
	}
	
	//���Ʊ���ͼƬ
	//private int y=0;
	private int yA=-backgroundA.getHeight();
	private int yB=0;
	public void paintBackground(Graphics g)
	{
		g.drawImage(background1, 0, 0, null);
		g.drawImage(backgroundA, 0, yA, null);
		g.drawImage(backgroundB, 0, yB, null);
	}
	
	//������Ϸ״̬
	public void paintState(Graphics g)
	{
		if(state==START)
		{
			g.drawImage(start, 0, 0, null);
		}else if( state==PAUSE)
		{
			g.drawImage(pause, 0, 0, null);
			
		}else if(state==GAMEOVER)
		{
			g.drawImage(gameover, 0, 0, null);
		}
		//����״̬
		//..................................
	}
	//������̰�1 һ�����ߣ� Ĭ�Ͼ�һ��
		private boolean flag1 = true;
		//������̰�2�������ߣ� ����2��
		private boolean flag2 = false;
		//������̰�3�������ߣ� ������
		private boolean flag3 = false;
		//�����ӵ��ƶ��ٶȱ��
		private boolean flagMax = false;
	//����Ӣ���ӵ�
	public void paintHeroBullet(Graphics g)
	{
		/*������ӵ���������ʼ������һ�����ȵ�����,���Ǹ���x,y�仯,��Ӧ���ӵ�λ��Ҳ�仯
		,��˻����������ӵ�,ͨ���������ϰ���Щ�ӵ�������
		
		
		*/
		for(int i=0;i<heroBullets.size();i++)
		{
		HeroBullet heroBullet=	heroBullets.get(i);
		BufferedImage image=heroBullet.getImage();
		BufferedImage hImage = heroPlane.getImage();
		//ͼ��Ϊ��,����Ӣ���ӵ�������
		if(image!=null&&!heroBullet.isOutOfBound())
		{
			if(flag1){
				g.drawImage(image, heroBullet.getX(), heroBullet.getY(), null);  //�м�
			}
			
			if(flag2){
				g.drawImage(image, heroBullet.getX()-hImage.getWidth()/2, heroBullet.getY(), null); //���
				g.drawImage(image, heroBullet.getX()+hImage.getWidth()/2, heroBullet.getY(), null); //�ұ�
			}
			
			if(flag3){
				g.drawImage(image, heroBullet.getX()-hImage.getWidth()/2, heroBullet.getY(), null); //���
				g.drawImage(image, heroBullet.getX(), heroBullet.getY(), null);  //�м�
				g.drawImage(image, heroBullet.getX()+hImage.getWidth()/2, heroBullet.getY(), null); //�ұ�
			}
		}
		}
	}
	//����С�л�(�ȵ��ж���л�,Ȼ����ڼ�����,Ȼ���ٻ���)
	
	public void paintEnemyPlane(Graphics g)
	{
		for(int i=0;i<enemyPlanes.size();i++)
		{
			EnemyPlane EnemyPlane=	enemyPlanes.get(i);
			BufferedImage  image=EnemyPlane.getImage();
			
			if(image!=null&&!EnemyPlane.isOutOfBound())
			{
				g.drawImage(image, EnemyPlane.getX(), EnemyPlane.getY(), null);
			}
			}
		
	}
	//�����ӵ�
	public void paintEnemyBullet(Graphics g)
	{
		for(int i=0;i<enemyBullets.size();i++)
		{
			EnemyBullet enemyaBullet=enemyBullets.get(i);
			BufferedImage image = enemyaBullet.getImage();
			if(image!=null&&!enemyaBullet.isOutOfBound())
			{
				g.drawImage(image, enemyaBullet.getX(),enemyaBullet.getY(), null);
			}
		}
	}
	
	//���Ʒ���������
		private void  paintScoreAndLife(Graphics g) {
			g.setColor(new Color(255, 0, 0));	//���û�����ɫ	��0	��0	��0	(0~255)
			g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));	//��������		���塢��ʽ���ֺ�
			g.drawString("score: "+heroPlane.getScore(), 10, 25);
			g.drawString("life: "+heroPlane.getLife(), 10, 45);
		}
	
	
	
	
	//��дJPanel��draw����
	//����ͼƬ�Ļ��Ʒ���
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		if(state==RUNNING)
		{
		//g.drawImage(background, 0, 0, null);
		//��д���ر�������
		this.paintBackground(g);
		//����Ӣ�۷ɻ�
		this.paintHeroPlane(g);
		//����Ӣ���ӵ�
		this.paintHeroBullet(g);
		//���Ƶл�
		this.paintEnemyPlane(g);
		//����С�л��ӵ�
		this.paintEnemyBullet(g);
		this.paintScoreAndLife(g);
		}
		this.paintState(g);
		
	}
	
	
	//�����ƶ�
	public void backGroundMove()
	{
		
		
		//yB=0,������yA=0
		//ע����yB >= backgroundB.getHeight(),������yB >= backgroundA.getHeight()
		yA++;
		yB++;
		if(yB >= backgroundB.getHeight()){
			yB = -backgroundB.getHeight();
		}
		if(yA >= backgroundB.getHeight()){
			yA = -backgroundB.getHeight();
		}
	}
	//Ӣ�۷ɻ�����
	public void heroPlaneFire()
	{
		List<HeroBullet> list= heroPlane.fire();
		//���ӵ����浽heroBullets,�Է��㻭���ӵ�ͼƬ
		heroBullets.addAll(list);
		 
	}
	//Ӣ���ӵ��ƶ�
	public void heroBulletMove()
	{
		for(HeroBullet heroBullet:heroBullets)
		{
			heroBullet.move();
		}
		
	}
	
	
	
	
	
	//�����л�
	public void enemyPlaneEnter()
	{
		int type=new Random().nextInt(6);
		EnemyPlane plane=null;
		//��������
		if(type<3){
			plane=new SmallPlane();
			
			
		}else if(type<5){
			//��л�
			plane=new BigPlane();
		}else  //���Ǽ�else��ʹ��ֻ�к�ը������
			 plane = new BossPlane();
		//��ը��
		
		//�Ѽ��ɻ�,�浽������
		enemyPlanes.add(plane);
	}
	
	
	//С�л��ƶ�(��������,EnemyPlane�����move()��������,enemyPlaneMove()�Ǳ������Ϸ���
	public void enemyPlaneMove()
	{
		for(EnemyPlane plane:enemyPlanes)
		{
			plane.move();
		}
	}
	//С�л�����
	public void enemyPlaneFire()
	{
		for(EnemyPlane plane:enemyPlanes)
		{
			 List<EnemyBullet> list	=plane.fire();
			//�ռ��ӵ����㻭��
			 enemyBullets.addAll(list);
		}
	}
	
	//С�л��ӵ��ƶ�
	public void enemyBulletMove()
	{
		for (EnemyBullet enemyBullet : enemyBullets) {
			enemyBullet.move();
			
		}
	}
	
	//�������ӵ���ײ
	public void checkHit(){
		
		//���ҷ������ӵ���ײ����,˫��ѭ��
		for(HeroBullet heroBullet:heroBullets )
		{
			
			for (EnemyBullet enemyBullet : enemyBullets) 
			{
			
				if(heroBullet.getState()==heroBullet.ALIVE&&
						enemyBullet.getState()==enemyBullet.ALIVE&&
						HitUtil.heroBulletHitBullet(heroBullet, enemyBullet))
				{
					//ʹ˫���ӵ���ʧ
					//Ӣ���ӵ�״̬���DEATH
					heroBullet.setState(HeroBullet.DEATH);
					//С�о��ӵ�״̬��ΪDEATH
					enemyBullet.setState(EnemyBullet.DEATH);
					//System.out.println("�ӵ���ײ��");
				}
				
			}
		
		}
		//Ӣ���ӵ���л���ײ
		for(HeroBullet hb:heroBullets )
		{
			for (EnemyPlane ep : enemyPlanes) {
				//Ӣ���ӵ�,�л����붼�ǻ��,���ҷ�������ײ
				if(hb.getState()==HeroPlane.ALIVE&&
						ep.getState()==EnemyPlane.ALIVE
						&&HitUtil.heroBulletHitPlane(hb, ep))
				{
					//Ӣ���ӵ���ʧ
					hb.setState(HeroBullet.DEATH);
					
					
					
					
					//�л�����
					ep.subLife(1);
					//�л�������ʱ�żӷ�
					if(!ep.hasLife())
					{
						ep.setState(EnemyPlane.DEATH);
						heroPlane.addScore(ep.getScore());
					}
					
				}
				
			}
		}
		
		//Ӣ�ۻ���о��ӵ���ײ
		for (EnemyBullet enemyBullet : enemyBullets) 
		{
		
			if(heroPlane.getState()==HeroPlane.ALIVE&&
					enemyBullet.getState()==enemyBullet.ALIVE&&
					HitUtil.heroPlaneHitBullet(heroPlane,enemyBullet))
			{
				//��һ����
				heroPlane.subLife(1);
				//С�о��ӵ�״̬��ΪDEATH
				enemyBullet.setState(EnemyBullet.DEATH);
				if(!heroPlane.hasLife())
				{
					state=GAMEOVER;//��Ϸ����
				}
				//System.out.println("�ӵ���ײ��");
			}
			
		}
		//Ӣ�ۻ���л���ײ
		for (EnemyPlane ep : enemyPlanes) {
			//Ӣ���ӵ�,�л����붼�ǻ��,���ҷ�������ײ
			if(heroPlane.getState()==HeroPlane.ALIVE&&
					ep.getState()==EnemyPlane.ALIVE
					&&HitUtil.heroPlaneHitEnamyPlane(heroPlane, ep))
			{
				//(�����м�����)�л�����,Ӣ�ۻ�����
				//heroPlane.setState(HeroBullet.DEATH);
				ep.setState(EnemyPlane.DEATH);
				
				//�ҷ�����
				heroPlane.addScore(1);
				heroPlane.subLife(1);
				if(!heroPlane.hasLife())
				{
					state=GAMEOVER;//��Ϸ����
				}
				
			}
			
		}
		
		
		
		
		
	}
	
	//��������״̬���ʧЧ���ӵ�
	public void clearObject()
	{
		
		//�л��ӵ�
		synchronized (enemyBullets) {
			//���Խ���ӵ���״̬Ϊ����״̬��״̬
			Iterator<EnemyBullet> iterator = enemyBullets.iterator();
			while(iterator.hasNext())
					{
				EnemyBullet next=iterator.next();
				//if(next.isOutOfBound()||next.getState()==EnemyBullet.DEATH)
				//���ڿ��Եȵ���DELETE״̬������
				if(next.isOutOfBound()||next.getState()==EnemyBullet.DELETE)
				{
					iterator.remove();
					//System.out.println("�л��ӵ������()");
				}
					}
		}

		//Ӣ���ӵ�
		synchronized (heroBullets) {
			//���Խ���ӵ���״̬Ϊ����״̬��״̬
			Iterator<HeroBullet> iterator = heroBullets.iterator();
			while(iterator.hasNext())
					{
				HeroBullet next=iterator.next();
				//if(next.isOutOfBound()||next.getState()==HeroBullet.DEATH)
				//���ڿ��Եȵ���DELETE״̬������
				if(next.isOutOfBound()||next.getState()==HeroBullet.DELETE)
				{
					iterator.remove();
					//System.out.println("�ҷ��ӵ����");
				}
					}
		}
		//�л�
		synchronized (enemyPlanes) {
			Iterator<EnemyPlane> iterator=enemyPlanes.iterator();
			while(iterator.hasNext())
			{
				EnemyPlane next=iterator.next();
				if(next.isOutOfBound()||next.getState()==EnemyPlane.DELETE)
				{
					iterator.remove();
				}
			}
			
		}
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	//void init():���ڳ�ʼ������
	public void init(){
		//���������࣬�����ô��ڵı���
		JFrame jframe=new JFrame("�ɻ���ս");
		//this��ʾShootGame Ҳ�ǻ���
		//���д���ǳ���Ҫ,ȱʧ��ᷢ��ͼƬû����ʾ����
				jframe.add(this);
		//���ô��ڴ�С
		jframe.setSize(WIDTH, HEIGHT);
		//��ʾ����
		jframe.setVisible(true);
		//ʹ��������ʱ����������
		jframe.setLocationRelativeTo(null);
		//���ô�������Ļ���Ϸ�
		jframe.setAlwaysOnTop(true);
		//���ô��ڲ����϶�
		jframe.setResizable(false);
		//���ô��ڹرվ�ֹͣ����,�����ǵ��xʱ�������г���
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//��Ӽ���������
		initListener();
		//���ö�ʱ��
		initTimer();
	}
	//����Ƶ��
	private long count=0;
	//count���ֵ
	private long Maxcount=999999;
	//��ʱ��
	private void initTimer()
	{
		//��ʱ������
		Timer timer=new Timer();
		//��һ������:��������,�ڶ���:��ʼʱ��(ms),������:����(ms)
		
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//�Ľ�,���״̬,ֻ������״̬ʱ�ſ��Ա����ƶ�
				//(ע�ⲻ�ܽ�repaint()����Ҳд��������ж����)
				//�����ƶ�
				if(state==RUNNING){
					//Ӣ�ۿ���
					heroPlaneFire();
					
				
					//Ӣ���ӵ��ƶ�,��Ϊ��ʱ��仯�ӵ�λ��Ҳ�ڱ仯��,���ö�ʱ��
				/*	if(count%10==0)
					{
					heroBulletMove();
					}
					*/
					heroBulletMove();
					//С�л��ǳ�
					if(heroPlane.getScore()<50)
					{
						if(count%60==0)
						{
							enemyPlaneEnter();
						}
					}
					else {
						
						if(count%40==0)
						{
							enemyPlaneEnter();
						}
					}
					//С�л��ƶ�
					if(count%2==0)
					{
					enemyPlaneMove();
					}
					//�л�����(�ռ��ӵ�)
					if(count%50==0)
					{
						enemyPlaneFire();
					}
					//�л��ӵ��ƶ�
					enemyBulletMove();
					//�ﵽ���������
					if(count==(Maxcount-50))
					{
						count=0;
					}
					count++;
				}
				//����ӵ���ײ
				checkHit();
				//�����Ч�ӵ�,����Ƶ�����
				if(count%100==0)
				{
					clearObject();
				}
				
				//System.out.println("ShootGame.initTimer().new TimerTask() {...}.run()");
				//���»��ƻ���
				//�����ƶ�
				backGroundMove();
				repaint();
				/*
				if(state==RUNNING){
					backGroundMove();
					repaint();
					}�ǲ��Ե�
					*/
			}
		}, 10, 10);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
		//��������Ƿ��ж���
	public void initListener()
	{
		//װһ��������
		MouseAdapter adapter = new MouseAdapter() {
			//����ƶ�����������
			@Override
			public void mouseMoved(MouseEvent e) {
				//System.out.println("x: " + e.getX() + ",y:" + e.getY() );
				heroPlane.move(e.getX(), e.getY()); //�ƶ�
				//�����repaint()����ʱ�����ͼƬû��ˢ��,������
				repaint();  //ˢ�»��壬 ���µ���paint����
			}
			//���������,�����ʼҳ��ʱ�ı俪ʼ״̬������״̬

			/* (non-Javadoc)
			 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				//������ʱ�л�����״̬
				if(state==START)
				{
					state=RUNNING;
				}else if(state==GAMEOVER)
				{
					state=START;
					//heroPlane.setLife(10);
					//heroPlane.setScore(0);
					heroPlane=new HeroPlane();
					heroBullets=new ArrayList<>();
					enemyBullets=new ArrayList<>();
					enemyPlanes=new  ArrayList<>();
				}
				
			}
			//����Ƴ�ȥʱ,�ı�����״̬����ͣ״̬

			/* (non-Javadoc)
			 * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if(state==RUNNING)
				{
					state=PAUSE;
				}
			}

			
			//����ƽ���,����ͣ״̬��������״̬
			/* (non-Javadoc)
			 * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if(state==PAUSE)
				{
					state=RUNNING;
				}
			}
			
			};
		
		
		//��װһ�������
		this.addMouseListener(adapter);
		this.addMouseMotionListener(adapter);


		//���ü��̼���
		this.setFocusable(true);
		
		//���̵ļ��
		//�����¼����������ܹ���frame�ϵ�
		KeyAdapter k = new KeyAdapter() {
			//���̰�������ȥ������ִ�з���
			
			@Override
			public void keyPressed(KeyEvent e) {
				//���̵İ�������
				int code = e.getKeyCode();
				//���̰����ı����Ӧ����
				String text = KeyEvent.getKeyText(code);
				//���Լ��̰��б仯��1
				//System.out.println("keyPressed()"+code);
				if("1".equals(text)){ //1������1������
					flag1 = true;
					flag2 = false;
					flag3 = false;
					System.out.println("flag1="+flag1+"\n"+"flag2="+flag2+"flag3="+flag3);
				}
				
				if("2".equals(text)){ //2������2������
					flag1 = false;
					flag2 = true;
					flag3 = false;
					System.out.println("flag1="+flag1+"\n"+"flag2="+flag2+"flag3="+flag3);
				}
				
				if("3".equals(text)){ //3������3������
					flag1 = false;
					flag2 = false;
					flag3 = true;
					System.out.println("flag1="+flag1+"\n"+"flag2="+flag2+"flag3="+flag3);
				}
				//����
				
				if(code == 37){
					System.out.println("����keycode");
					}
					
				if("M".equals(text)){ //M�������ӵ����Ƶ��
					flagMax = true;
					System.out.println("flag1="+flag1+"\n"+"flag2="+flag2+"flag3="+flag3);
				}
				
			}
		};
		this.addKeyListener(k);
		//ǧ��Ҫ�����´���this.requestFocus();,����java ��frame���������Ӽ����¼���Ч
		this.requestFocus();
		
		
		
	}

	


	
}
