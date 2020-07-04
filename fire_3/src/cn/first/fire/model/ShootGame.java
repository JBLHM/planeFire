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
/*引入的包:不是import java.awt.List;而是import java.util.List;
两者不能共存
*/


/*
 * 游戏主界面
 * 任务:
 * ①初始化游戏窗口(JPanel)
 * ②加载游戏背景
 * ③加载游戏角色(英雄飞机,小型机,大型机,轰炸机,子弹)
 * ④游戏逻辑控制(移动,射击,碰撞)
 * 
 */



public class ShootGame extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//窗口宽度
	public static final int WIDTH = 400; 
	//窗口的长度
	public static final int HEIGHT = 654;  
	//背景图片
	private BufferedImage background = ImageUtil.readImage("background.jpg");
	private BufferedImage background1 = ImageUtil.readImage("background1.jpg"); //蓝色
	private BufferedImage backgroundA = ImageUtil.readImage("background2.png"); //星星
	//添加一个星星
	private BufferedImage backgroundB = ImageUtil.readImage("background2.png"); //星星
	
	//游戏状态(游戏开始,暂停,终止)
	//三种状态图片
	private BufferedImage pause = ImageUtil.readImage("pause.png");
	private BufferedImage start = ImageUtil.readImage("start.png");
	private BufferedImage gameover= ImageUtil.readImage("gameover.png");
	//所有状态(游戏开始,运行,暂停,终止)
	private static final int START=0;
	private static final int RUNNING=1;
	private static final int PAUSE=2;
	private static final int GAMEOVER=3;
	//当前状态,默认是开始状态
	private int state=START;
	//添加英雄对象图片到画布中
	private  HeroPlane heroPlane=new HeroPlane();
	//英雄机子弹
	private List<HeroBullet> heroBullets=new ArrayList<HeroBullet>();
	
	//小敌机
	private List<EnemyPlane>  enemyPlanes = new ArrayList<EnemyPlane>();
	//小敌机子弹
	private List<EnemyBullet> enemyBullets=new ArrayList<EnemyBullet>();
	
	
	
	
	
	
	
	//英雄对象图片引入画布方法
	public void paintHeroPlane(Graphics g)
	{
		BufferedImage heroimage=heroPlane.getImage();
		
		if(heroimage!=null)
		{
			//判断是否是刚开始时
			if (heroPlane.getX()==0)
			{
				g.drawImage(heroimage,WIDTH/2-heroimage.getWidth()/2, 
									  HEIGHT/2+heroimage.getHeight(), null);
			}
			else{
				//跟随鼠标移动的设置
				g.drawImage(heroimage,heroPlane.getX()-heroimage.getWidth()/2, 
						heroPlane.getY() - heroimage.getHeight()/2, null);
			}
			//g.drawImage(heroimage, 0, 0, null);
			
		}
		
	}
	
	//绘制背景图片
	//private int y=0;
	private int yA=-backgroundA.getHeight();
	private int yB=0;
	public void paintBackground(Graphics g)
	{
		g.drawImage(background1, 0, 0, null);
		g.drawImage(backgroundA, 0, yA, null);
		g.drawImage(backgroundB, 0, yB, null);
	}
	
	//绘制游戏状态
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
		//运行状态
		//..................................
	}
	//如果键盘按1 一条火线， 默认就一条
		private boolean flag1 = true;
		//如果键盘按2二条火线： 左右2条
		private boolean flag2 = false;
		//如果键盘按3三条火线， 左中右
		private boolean flag3 = false;
		//控制子弹移动速度标记
		private boolean flagMax = false;
	//绘制英雄子弹
	public void paintHeroBullet(Graphics g)
	{
		/*这里的子弹不是那种始终绵延一定长度的那种,而是根据x,y变化,对应的子弹位置也变化
		,因此会有无数个子弹,通过遍历集合把这些子弹画出来
		
		
		*/
		for(int i=0;i<heroBullets.size();i++)
		{
		HeroBullet heroBullet=	heroBullets.get(i);
		BufferedImage image=heroBullet.getImage();
		BufferedImage hImage = heroPlane.getImage();
		//图像不为空,而且英雄子弹不出界
		if(image!=null&&!heroBullet.isOutOfBound())
		{
			if(flag1){
				g.drawImage(image, heroBullet.getX(), heroBullet.getY(), null);  //中间
			}
			
			if(flag2){
				g.drawImage(image, heroBullet.getX()-hImage.getWidth()/2, heroBullet.getY(), null); //左边
				g.drawImage(image, heroBullet.getX()+hImage.getWidth()/2, heroBullet.getY(), null); //右边
			}
			
			if(flag3){
				g.drawImage(image, heroBullet.getX()-hImage.getWidth()/2, heroBullet.getY(), null); //左边
				g.drawImage(image, heroBullet.getX(), heroBullet.getY(), null);  //中间
				g.drawImage(image, heroBullet.getX()+hImage.getWidth()/2, heroBullet.getY(), null); //右边
			}
		}
		}
	}
	//绘制小敌机(先得有多个敌机,然后放在集合中,然后再绘制)
	
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
	//绘制子弹
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
	
	//绘制分数与生命
		private void  paintScoreAndLife(Graphics g) {
			g.setColor(new Color(255, 0, 0));	//设置画笔颜色	红0	绿0	蓝0	(0~255)
			g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));	//设置字体		字体、样式、字号
			g.drawString("score: "+heroPlane.getScore(), 10, 25);
			g.drawString("life: "+heroPlane.getLife(), 10, 45);
		}
	
	
	
	
	//重写JPanel的draw方法
	//所有图片的绘制方法
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		if(state==RUNNING)
		{
		//g.drawImage(background, 0, 0, null);
		//重写加载背景函数
		this.paintBackground(g);
		//绘制英雄飞机
		this.paintHeroPlane(g);
		//绘制英雄子弹
		this.paintHeroBullet(g);
		//绘制敌机
		this.paintEnemyPlane(g);
		//绘制小敌机子弹
		this.paintEnemyBullet(g);
		this.paintScoreAndLife(g);
		}
		this.paintState(g);
		
	}
	
	
	//背景移动
	public void backGroundMove()
	{
		
		
		//yB=0,而不是yA=0
		//注意是yB >= backgroundB.getHeight(),而不是yB >= backgroundA.getHeight()
		yA++;
		yB++;
		if(yB >= backgroundB.getHeight()){
			yB = -backgroundB.getHeight();
		}
		if(yA >= backgroundB.getHeight()){
			yA = -backgroundB.getHeight();
		}
	}
	//英雄飞机开火
	public void heroPlaneFire()
	{
		List<HeroBullet> list= heroPlane.fire();
		//将子弹都存到heroBullets,以方便画出子弹图片
		heroBullets.addAll(list);
		 
	}
	//英雄子弹移动
	public void heroBulletMove()
	{
		for(HeroBullet heroBullet:heroBullets)
		{
			heroBullet.move();
		}
		
	}
	
	
	
	
	
	//创建敌机
	public void enemyPlaneEnter()
	{
		int type=new Random().nextInt(6);
		EnemyPlane plane=null;
		//创建对象
		if(type<3){
			plane=new SmallPlane();
			
			
		}else if(type<5){
			//大敌机
			plane=new BigPlane();
		}else  //忘记加else会使得只有轰炸机出现
			 plane = new BossPlane();
		//轰炸机
		
		//搜集飞机,存到集合中
		enemyPlanes.add(plane);
	}
	
	
	//小敌机移动(遍历集合,EnemyPlane里才是move()真正内容,enemyPlaneMove()是遍历集合方法
	public void enemyPlaneMove()
	{
		for(EnemyPlane plane:enemyPlanes)
		{
			plane.move();
		}
	}
	//小敌机开火
	public void enemyPlaneFire()
	{
		for(EnemyPlane plane:enemyPlanes)
		{
			 List<EnemyBullet> list	=plane.fire();
			//收集子弹方便画出
			 enemyBullets.addAll(list);
		}
	}
	
	//小敌机子弹移动
	public void enemyBulletMove()
	{
		for (EnemyBullet enemyBullet : enemyBullets) {
			enemyBullet.move();
			
		}
	}
	
	//检测敌我子弹碰撞
	public void checkHit(){
		
		//敌我方所有子弹碰撞计算,双重循环
		for(HeroBullet heroBullet:heroBullets )
		{
			
			for (EnemyBullet enemyBullet : enemyBullets) 
			{
			
				if(heroBullet.getState()==heroBullet.ALIVE&&
						enemyBullet.getState()==enemyBullet.ALIVE&&
						HitUtil.heroBulletHitBullet(heroBullet, enemyBullet))
				{
					//使双方子弹消失
					//英雄子弹状态变成DEATH
					heroBullet.setState(HeroBullet.DEATH);
					//小敌军子弹状态变为DEATH
					enemyBullet.setState(EnemyBullet.DEATH);
					//System.out.println("子弹碰撞了");
				}
				
			}
		
		}
		//英雄子弹与敌机碰撞
		for(HeroBullet hb:heroBullets )
		{
			for (EnemyPlane ep : enemyPlanes) {
				//英雄子弹,敌机必须都是活的,而且发生了碰撞
				if(hb.getState()==HeroPlane.ALIVE&&
						ep.getState()==EnemyPlane.ALIVE
						&&HitUtil.heroBulletHitPlane(hb, ep))
				{
					//英雄子弹消失
					hb.setState(HeroBullet.DEATH);
					
					
					
					
					//敌机减命
					ep.subLife(1);
					//敌机被击落时才加分
					if(!ep.hasLife())
					{
						ep.setState(EnemyPlane.DEATH);
						heroPlane.addScore(ep.getScore());
					}
					
				}
				
			}
		}
		
		//英雄机与敌军子弹碰撞
		for (EnemyBullet enemyBullet : enemyBullets) 
		{
		
			if(heroPlane.getState()==HeroPlane.ALIVE&&
					enemyBullet.getState()==enemyBullet.ALIVE&&
					HitUtil.heroPlaneHitBullet(heroPlane,enemyBullet))
			{
				//减一条命
				heroPlane.subLife(1);
				//小敌军子弹状态变为DEATH
				enemyBullet.setState(EnemyBullet.DEATH);
				if(!heroPlane.hasLife())
				{
					state=GAMEOVER;//游戏结束
				}
				//System.out.println("子弹碰撞了");
			}
			
		}
		//英雄机与敌机碰撞
		for (EnemyPlane ep : enemyPlanes) {
			//英雄子弹,敌机必须都是活的,而且发生了碰撞
			if(heroPlane.getState()==HeroPlane.ALIVE&&
					ep.getState()==EnemyPlane.ALIVE
					&&HitUtil.heroPlaneHitEnamyPlane(heroPlane, ep))
			{
				//(无论有几条命)敌机死了,英雄机减命
				//heroPlane.setState(HeroBullet.DEATH);
				ep.setState(EnemyPlane.DEATH);
				
				//我方分数
				heroPlane.addScore(1);
				heroPlane.subLife(1);
				if(!heroPlane.hasLife())
				{
					state=GAMEOVER;//游戏结束
				}
				
			}
			
		}
		
		
		
		
		
	}
	
	//根据死亡状态清除失效的子弹
	public void clearObject()
	{
		
		//敌机子弹
		synchronized (enemyBullets) {
			//清除越界子弹和状态为死亡状态的状态
			Iterator<EnemyBullet> iterator = enemyBullets.iterator();
			while(iterator.hasNext())
					{
				EnemyBullet next=iterator.next();
				//if(next.isOutOfBound()||next.getState()==EnemyBullet.DEATH)
				//现在可以等到了DELETE状态再清理
				if(next.isOutOfBound()||next.getState()==EnemyBullet.DELETE)
				{
					iterator.remove();
					//System.out.println("敌机子弹清除了()");
				}
					}
		}

		//英雄子弹
		synchronized (heroBullets) {
			//清除越界子弹和状态为死亡状态的状态
			Iterator<HeroBullet> iterator = heroBullets.iterator();
			while(iterator.hasNext())
					{
				HeroBullet next=iterator.next();
				//if(next.isOutOfBound()||next.getState()==HeroBullet.DEATH)
				//现在可以等到了DELETE状态再清理
				if(next.isOutOfBound()||next.getState()==HeroBullet.DELETE)
				{
					iterator.remove();
					//System.out.println("我方子弹清除");
				}
					}
		}
		//敌机
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
	
	
	
	
	
	
	
	//void init():窗口初始化方法
	public void init(){
		//创建窗口类，并设置窗口的标题
		JFrame jframe=new JFrame("飞机大战");
		//this表示ShootGame 也是画板
		//这行代码非常重要,缺失后会发现图片没有显示出来
				jframe.add(this);
		//设置窗口大小
		jframe.setSize(WIDTH, HEIGHT);
		//显示窗口
		jframe.setVisible(true);
		//使窗口运行时处于正中央
		jframe.setLocationRelativeTo(null);
		//设置窗口在屏幕最上方
		jframe.setAlwaysOnTop(true);
		//设置窗口不可拖动
		jframe.setResizable(false);
		//设置窗口关闭就停止程序,而不是点出x时还在运行程序
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//添加监听器方法
		initListener();
		//设置定时器
		initTimer();
	}
	//控制频率
	private long count=0;
	//count最大值
	private long Maxcount=999999;
	//定时器
	private void initTimer()
	{
		//定时器对象
		Timer timer=new Timer();
		//第一个参数:任务内容,第二个:起始时间(ms),第三个:周期(ms)
		
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//改进,添加状态,只有运行状态时才可以背景移动
				//(注意不能将repaint()方法也写在下面的判断语句)
				//背景移动
				if(state==RUNNING){
					//英雄开火
					heroPlaneFire();
					
				
					//英雄子弹移动,因为是时间变化子弹位置也在变化的,故用定时器
				/*	if(count%10==0)
					{
					heroBulletMove();
					}
					*/
					heroBulletMove();
					//小敌机登场
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
					//小敌机移动
					if(count%2==0)
					{
					enemyPlaneMove();
					}
					//敌机开火(收集子弹)
					if(count%50==0)
					{
						enemyPlaneFire();
					}
					//敌机子弹移动
					enemyBulletMove();
					//达到极限则归零
					if(count==(Maxcount-50))
					{
						count=0;
					}
					count++;
				}
				//检测子弹碰撞
				checkHit();
				//清除无效子弹,不能频繁清除
				if(count%100==0)
				{
					clearObject();
				}
				
				//System.out.println("ShootGame.initTimer().new TimerTask() {...}.run()");
				//重新绘制画板
				//背景移动
				backGroundMove();
				repaint();
				/*
				if(state==RUNNING){
					backGroundMove();
					repaint();
					}是不对的
					*/
			}
		}, 10, 10);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
		//监听鼠标是否有动作
	public void initListener()
	{
		//装一个监听器
		MouseAdapter adapter = new MouseAdapter() {
			//鼠标移动监听方法：
			@Override
			public void mouseMoved(MouseEvent e) {
				//System.out.println("x: " + e.getX() + ",y:" + e.getY() );
				heroPlane.move(e.getX(), e.getY()); //移动
				//不添加repaint()方法时会出现图片没有刷新,即不动
				repaint();  //刷新画板， 重新调用paint方法
			}
			//鼠标点击方法,点击初始页面时改变开始状态成运行状态

			/* (non-Javadoc)
			 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				//点击鼠标时切换运行状态
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
			//鼠标移出去时,改变运行状态成暂停状态

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

			
			//鼠标移进来,由暂停状态进入运行状态
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
		
		
		//安装一个监控器
		this.addMouseListener(adapter);
		this.addMouseMotionListener(adapter);


		//设置键盘监听
		this.setFocusable(true);
		
		//键盘的监控
		//键盘事件监听器不能挂在frame上的
		KeyAdapter k = new KeyAdapter() {
			//键盘按键按下去，马上执行方法
			
			@Override
			public void keyPressed(KeyEvent e) {
				//键盘的按键编码
				int code = e.getKeyCode();
				//键盘按键的编码对应文字
				String text = KeyEvent.getKeyText(code);
				//测试键盘按有变化不1
				//System.out.println("keyPressed()"+code);
				if("1".equals(text)){ //1代表着1条火线
					flag1 = true;
					flag2 = false;
					flag3 = false;
					System.out.println("flag1="+flag1+"\n"+"flag2="+flag2+"flag3="+flag3);
				}
				
				if("2".equals(text)){ //2代表着2条火线
					flag1 = false;
					flag2 = true;
					flag3 = false;
					System.out.println("flag1="+flag1+"\n"+"flag2="+flag2+"flag3="+flag3);
				}
				
				if("3".equals(text)){ //3代表着3条火线
					flag1 = false;
					flag2 = false;
					flag3 = true;
					System.out.println("flag1="+flag1+"\n"+"flag2="+flag2+"flag3="+flag3);
				}
				//测试
				
				if(code == 37){
					System.out.println("测试keycode");
					}
					
				if("M".equals(text)){ //M键控制子弹输出频率
					flagMax = true;
					System.out.println("flag1="+flag1+"\n"+"flag2="+flag2+"flag3="+flag3);
				}
				
			}
		};
		this.addKeyListener(k);
		//千万要加以下代码this.requestFocus();,否则java 往frame或组件中添加监听事件无效
		this.requestFocus();
		
		
		
	}

	


	
}
