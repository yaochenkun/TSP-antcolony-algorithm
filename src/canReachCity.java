//下一步可走的城市
public class canReachCity 
{
	int id;
	float rate=0.0f;//被选择概率
	float VAP=0.0f;//能见度和信息素浓度总和
	
	canReachCity(int id,float VAP)
	{
		this.id=id;
		this.VAP=VAP;
	}
}
