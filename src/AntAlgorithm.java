import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class AntAlgorithm 
{	
	private int NC=10;//迭代次数
	private int antNum=10;//蚂蚁数量
	private Ant[] ants;//蚁群
	private float Q=300.0f;
	private float p=0.3f;//蒸发率
	
	float minLength=Float.MAX_VALUE;//当前最短距离
	int[] minRoute; //当前最短路线
	
	AntAlgorithm()
	{
		ants=new Ant[antNum];
		for(int i=0;i<antNum;i++)
			ants[i]=new Ant();
			
		minRoute=new int[Constant.CITY_NUM];
	}
	
	void run()
	{
		for(int nc=1;nc<=NC;nc++) //迭代次数
		{
			//初始化蚂蚁数据
			for(int k=0;k<ants.length;k++)
				ants[k].init();
			
			//遍历所有城市
			for(int look=1;look<Constant.CITY_NUM;look++)
			{
				for(int k=0;k<ants.length;k++)//每只蚂蚁
				{
					int nextCity=select(ants[k]);//选择下一个城市					
					ants[k].reachNextCity(nextCity);//到达下一个城市
				}
			}
	
			//返回起点城市并计算最优路径
			for(int k=0;k<ants.length;k++)//每只蚂蚁
			{
				ants[k].reachNextCity(ants[k].passed[0]-1);
				if(minLength > ants[k].passedLength)
				{
					minLength=ants[k].passedLength;//记录最短距离
					copyRoute(ants[k].passed);//记录最短路线
				}
			}
			
			//对routes进行信息素更新
			for(int i=0;i<Constant.CITY_NUM;i++)
				for(int j=0;j<Constant.CITY_NUM;j++)
				{
					//所有路径的信息素均蒸发
					Constant.routes[i][j].pheromone *= p;
					
					for(int k=0;k<ants.length;k++)
					{
						for(int n=0;n<Constant.CITY_NUM;n++)
						{
							int curCity=ants[k].passed[n]-1;
							int nextCity=ants[k].passed[(n+1) % Constant.CITY_NUM]-1;
							
							if(curCity == i && nextCity == j)//出现过这段路径
							{
								//更新路径curCity,nextCity信息素
								float dp = Q/ants[k].passedLength;//信息素增量
								Constant.routes[i][j].pheromone += dp;	
							}
						}
					}
				}
			print();
//
//			for(int i=0;i<Constant.CITY_NUM;i++)
//			{
//				for(int j=0;j<Constant.CITY_NUM;j++)
//					System.out.print(Constant.routes[i][j].pheromone+" ");
//				System.out.println();
//			}
//			System.out.println("_____________________");
		}
		
		
	}
	
	//计算选择概率+轮赌
	int select(Ant ant)
	{
		float totalVAP=0.0f;
		List<canReachCity> canSelectedCityList=new LinkedList<canReachCity>();
		for(int nextCity=0;nextCity<Constant.CITY_NUM;nextCity++)
		{
			if(!ant.isPassedCity(nextCity+1))//可选择城市
			{
				double visibility=1.0f/Constant.routes[ant.curCity][nextCity].distance;//能见度
					visibility=Math.pow(visibility,3);
				double pheromone=Constant.routes[ant.curCity][nextCity].pheromone;
						pheromone=Math.pow(pheromone,2);
				
				float VAP=(float)visibility+(float)pheromone;
				totalVAP += VAP; //累加VAP
				//阿尔法和贝塔**********************************************
				canReachCity rCity=new canReachCity(nextCity,VAP);

				canSelectedCityList.add(rCity);//添加
			}
		}


		//计算每个城市被选中的概率
		ListIterator<canReachCity> iterator = canSelectedCityList.listIterator(); // 获取pelList对应的迭代器头结点
		while (iterator.hasNext())
		{
			//取城市
			canReachCity rCity=iterator.next();
			
			//计算概率
			rCity.rate = rCity.VAP/totalVAP;
		}
		
//		iterator = canSelectedCityList.listIterator(); // 获取pelList对应的迭代器头结点
//		while (iterator.hasNext())
//		{
//			canReachCity rCity=iterator.next();
//			System.out.print(rCity.id+" ");
//		}
//		System.out.print("\n"+"____________________");
		
		//赌轮选择其中一个城市
		float rate=(float)Math.random();
		iterator = canSelectedCityList.listIterator(); // 获取pelList对应的迭代器头结点
		while (iterator.hasNext())
		{
			canReachCity rCity=iterator.next();
			if(rate <= rCity.rate)
				return rCity.id;
			else
				rate=rate-rCity.rate;
		}
		
		//精度所致，人为返回最后一个城市
		iterator = canSelectedCityList.listIterator(); // 获取pelList对应的迭代器头结点
		while (iterator.hasNext())
		{
			canReachCity rCity=iterator.next();
			if(iterator.hasNext() == false) //最后一个元素了
				return rCity.id;
		}
		
		return Integer.MAX_VALUE;
	}
	
	//拷贝路线
	void copyRoute(int[] route)
	{
		for(int i=0;i<minRoute.length;i++)
			minRoute[i]=route[i];
	}
	
	void printRoute()
	{
		System.out.print("最短路线：");
		for(int i=0;i<minRoute.length;i++)
			System.out.print(minRoute[i]+"->");
		System.out.print(minRoute[0]+"\n"+"最短距离：" + minLength);
	}
	
	void print()
	{
		System.out.println("最短距离：" + minLength);
	}
}
