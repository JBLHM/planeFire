package cn.first.fire;

import cn.first.fire.model.ShootGame;

//本游戏程序入口地址
public class StartUp {
	
	
	public  static void main(String[] args){
		System.out.println("程序开始了....");
	
		
		//创建一个游戏主界面
		ShootGame shootGame = new ShootGame();
		//初始化游戏主界面方法
		shootGame.init();
	}
}
