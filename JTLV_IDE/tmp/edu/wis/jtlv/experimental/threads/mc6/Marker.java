package edu.wis.jtlv.experimental.threads.mc6;

public  class Marker{
	private int id;
	private int index;
	
	protected Marker(int _id, int _index){
		this.id = _id;
		this.index = _index;
	}
	
	protected int getId(){
		return id;
	}
	
	protected int getIndex(){
		return index;
	}		
} 