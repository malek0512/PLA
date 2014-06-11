package personnages;

public class HitBoxManager {

	static private float hitBox = (float) 0.1; 
	static public boolean hitting(CoordonneesFloat cord1,CoordonneesFloat cord2)
	{
		if(cord1.x < cord2.x)
		{
			if(cord1.x + 2*HitBoxManager.hitBox >= cord2.x)
			{
				return true;
			}
		}
		else
		{
			if(cord1.x + 2*HitBoxManager.hitBox <= cord2.x)
			{
				return true;
			}
		}
		if(cord1.y < cord2.y)
		{
			if(cord1.y + 2*HitBoxManager.hitBox >= cord2.y)
			{
				return true;
			}
		}
		else
		{
			if(cord1.y + 2*HitBoxManager.hitBox <= cord2.y)
			{
				return true;
			}
		}
		return false;
		
	}
}
