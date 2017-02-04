package passgen;

import java.util.ArrayList;

public class WordGenerator extends Element<String>{	
	public WordGenerator(){
		super(null);
		build();
		SingRandom.init();
	}
	
	public String getWord(int lenght){
		String str = "";
		Element<String> el = getNext();
		while(str.length()<lenght && el != null){
			str += el.getValue();
			el = el.getNext();
		}
		return str;
	}

	public void build(){
		
		String[] voyStr = {"a","e","i","o","u","y","ai","au","eau"};
		String[] conStr = {"b","c","d","f","g","h","j","k","l","m","n","p","r","s","t","v","w","x","z"};
		String[] conDouble = {"qu","ss","tt","tr","mm","nn","ch","ck","sc","st","tr","pt","fr","cr","cl","br","bl"};

		ArrayList<Element<String>> voyelles = new ArrayList<Element<String>>();
		for(String str : voyStr){
			Element<String> el = new Element<String>(str);
			voyelles.add(el);
		}
		
		ArrayList<Element<String>> consonnes = new ArrayList<Element<String>>();
		for(String str : conStr){
			Element<String> el = new Element<String>(str);
			consonnes.add(el);
		}
		
		ArrayList<Element<String>> consonnesDoubles = new ArrayList<Element<String>>();
		for(String str : conDouble){
			Element<String> el = new Element<String>(str);
			consonnesDoubles.add(el);
		}
		
		// set des nexts
		for(Element<String> el : voyelles){
			el.addAllToNext(consonnes);
			el.addAllToNext(consonnesDoubles);
		}
		
		for(Element<String> el : consonnes){
			el.addAllToNext(voyelles);
		}
		
		for(Element<String> el : consonnesDoubles){
			el.addAllToNext(voyelles);
		}
		
		// Ajout des lettres
		next.clear();
		addAllToNext(voyelles);
		addAllToNext(consonnes);
		addAllToNext(consonnesDoubles);
	}
	
	// Example d'utilisation
	public static void main(String[] args) {
		SingRandom.init();
		WordGenerator gen = new WordGenerator();
		System.out.println(gen.getWord(8));
	}
}
