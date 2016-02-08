package passgen;

import java.util.ArrayList;

public class Generator extends Element<String>{
	
	public Generator(){
		super(null);
		build();
	}
	
	public String getPassword(int lenght){
		String str = "";
		Element<String> el = getNext();
		while(str.length()<lenght){
			str += el.getValue();
			el = el.getNext();
		}
		return str;
	}

	public static void main(String[] args) {
		SingRandom.init();
		Generator gen = new Generator();
		System.out.println(gen.getPassword(8));
	}
	
	
	public void build(){
		
		String[] voyStr = {"a","e","i","o","u","y","ai","au","eau"};
		String[] conStr = {"b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","z"};
		String[] conDouble = {"ss","tt","mm","nn","ch","ff","ck","pp","cc","nm","sc","st","tr","pt","fr","cr","cl","br","bl","rr","ll"};

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
		addAllToNext(voyelles);
		addAllToNext(consonnes);
	}
}
