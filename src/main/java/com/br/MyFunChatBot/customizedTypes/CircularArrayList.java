package com.br.MyFunChatBot.customizedTypes;

import java.util.ArrayList;
import java.util.List;

public class CircularArrayList<E> extends ArrayList<E>{

	private static final long serialVersionUID = 3800070000343913105L;

	public CircularArrayList(){
		super();
	}
	
	public CircularArrayList(List<E> l){
		super(l);
	}
	
	public E next(){
		E temp = remove(0);
		add(temp);
		return temp;
	}
	
}
