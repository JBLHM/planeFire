package cn.first.fire;

import cn.first.fire.model.ShootGame;

//����Ϸ������ڵ�ַ
public class StartUp {
	
	
	public  static void main(String[] args){
		System.out.println("����ʼ��....");
	
		
		//����һ����Ϸ������
		ShootGame shootGame = new ShootGame();
		//��ʼ����Ϸ�����淽��
		shootGame.init();
	}
}
