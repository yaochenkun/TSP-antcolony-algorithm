
public class Constant 
{
	static int CITY_NUM;//城市数
	static Route[][] routes;//路径地图
	static final float C=10.0f;//信息素初值
	static
	{
		//城市坐标
		int[][] cityPoint={
				{0,0},{12,32},{5,25},{8,45},{33,17},
				{25,7},{15,15},{15,25},{25,15},{41,12}};

//		int[][] cityPoint={
//				{1304,        2312},{3639,        1315},         
//				{4177,        2244},{3712,        1399},         	
//				{3488,        1535},{3326,        1556},         
//				{3238,        1229},{4196,        1004},         
//				{4312,         790},{4386,         570},
//				{3007,        1970},{2562,        1756},
//				{2788,        1491},{2381,        1676},
//				{1332,         695},{3715,        1678},
//				{3918,        2179},{4061,        2370},
//				{3780,        2212},{3676,        2578},
//				{4029,        2838},{4263,        2931},
//				{3429,        1908},{3507,        2367},
//				{3394,        2643},{3439,        3201},
//				{2935,        3240},{3140,        3550},
//				{2545,        2357},{2778,        2826},
//				{2370,        2975}};//31个城市（最优解:14700）
		
		
		
		//确定城市数、创建路径地图
		CITY_NUM=cityPoint.length;
		routes=new Route[CITY_NUM][CITY_NUM];
		for(int i=0;i<CITY_NUM;i++)
			for(int j=0;j<CITY_NUM;j++)
				routes[i][j]=new Route();

		for(int i=0;i<CITY_NUM;i++)
			for(int j=i;j<CITY_NUM;j++)
			{
				float dis=(float)Math.sqrt(Math.pow((cityPoint[i][0] - cityPoint[j][0]),2) + Math.pow((cityPoint[i][1] - cityPoint[j][1]),2));

				//装入距离
				routes[i][j].distance=dis;
				routes[j][i].distance=dis;
			}
	}
}
