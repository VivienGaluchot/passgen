package passgen;

import java.util.ArrayList;

public class Generator extends Element<String>{
	
	public static void main(String[] args) {
		SingRandom.init();
		Generator gen = new Generator();
		System.out.println(gen.getPassword(8));
	}
	
	public Generator(){
		super(null);
		build();
	}
	
	public String getPassword(int lenght){
		String str = "";
		Element<String> el = getNext();
		while(str.length()<lenght && el != null){
			str += el.getValue();
			el = el.getNext();
		}
		return str;
	}

	public void build(){
		
		String[] voyStr = {"a","e","i","o","u","y","ai","au","eau","é","è"};
		String[] conStr = {"b","c","d","f","g","h","j","k","l","m","n","p","r","s","t","v","w","x","z"};
		String[] conDouble = {"ss","tt","tr","mm","nn","ch","ck","sc","st","tr","pt","fr","cr","cl","br","bl"};
		String[] syll = {"que","qua","qui","queau","queu","quo"};

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
		
		ArrayList<Element<String>> syllabes = new ArrayList<Element<String>>();
		for(String str : syll){
			Element<String> el = new Element<String>(str);
			syllabes.add(el);
		}
		
		// set des nexts
		for(Element<String> el : voyelles){
			el.addAllToNext(consonnes);
			el.addAllToNext(consonnesDoubles);
			el.addAllToNext(syllabes);
		}
		
		for(Element<String> el : consonnes){
			el.addAllToNext(voyelles);
		}
		
		for(Element<String> el : consonnesDoubles){
			el.addAllToNext(voyelles);
		}
		
		for(Element<String> el : syllabes){
			el.addAllToNext(consonnes);
		}
		
		// Ajout des lettres
		next.clear();
		addAllToNext(voyelles);
		addAllToNext(consonnes);
		addAllToNext(syllabes);
	}
}
