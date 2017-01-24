import java.util.Random;

//蚂蚁
public class Ant 
{
	int[] passed;//已经过的城市列表（禁忌表）
	float passedLength=0.0f;//周游长度
	int curCity;//蚂蚁当前所在城市
	int curIndex;//当前所在下标
	
	//初始化蚂蚁数据
	void init()
	{
		initPassed();
		passedLength=0.0f;
		curIndex=0;
		initBeginCity();
	}
	
	//初始化禁忌表
	void initPassed()
	{
		passed=new int[Constant.CITY_NUM+1];
		for(int i=0;i<passed.length;i++)
			passed[i]=Integer.MIN_VALUE;
	}
	
	//初始化蚂蚁所在城市
	void initBeginCity()
	{
		Random rand=new Random();
		int beginCity=rand.nextInt(Constant.CITY_NUM);
		reachNextCity(beginCity);
	}
	
	//到达下一个城市
	void reachNextCity(int nextCity)
	{
		//累加周游距离
		passedLength += Constant.routes[curCity][nextCity].distance;
		
		//前进
		curCity=nextCity;
		passed[curIndex++]=nextCity+1;
	}
	
	//判断城市nCity是否在禁忌表中
	boolean isPassedCity(int nCity)
	{
		for(int i=0;passed[i] != Integer.MIN_VALUE;i++)
		{
			if(passed[i] == nCity) //存在的城市
				return true;
		}
		return false;
	}
}
