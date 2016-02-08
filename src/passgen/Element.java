package passgen;

import java.util.ArrayList;

public class Element<E> {
	E value;
	int weight;
	
	ArrayList<Element<E>> next;
	
	public Element(E v){
		weight = 1;
		value = v;
		next = new ArrayList<Element<E>>();
	}
	
	public Element(E v, int w){
		weight = w;
		value = v;
		next = new ArrayList<Element<E>>();
	}
	
	public Element<E> getNext(){
		if(next.size() == 0) return null;
		return next.get(SingRandom.nextInt(next.size()));
	}
	
	public E getValue(){
		return value;
	}
	
	public void addNext(Element<E> element){
		next.add(element);
	}
	
	public void addAllToNext(ArrayList<Element<E>> list){
		next.addAll(list);
	}
	
	public void removeFromNext(Element<E> el){
		next.remove(el);
	}
}
