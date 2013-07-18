import com.temboo.core.TembooException;


public class Tester {
	
	public static void main(String[] args) throws TembooException
	{
		NYTRunner t = new NYTRunner();
		
		System.out.println(t.refresh());
	}
}
