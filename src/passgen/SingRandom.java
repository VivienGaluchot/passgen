package passgen;

import java.util.Date;
import java.util.Random;

public class SingRandom {
	private static Random rand = null;
	
	static final void init(){
		rand = new Random(new Date().getTime());
	}
	
	static final int nextInt(int n){
		return rand.nextInt(n);
	}
}
