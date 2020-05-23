package com.mycompany.a1;
import com.codename1.charts.util.ColorUtil;

public class Base extends Fixed {
	
	/**
	 * Private variables
	 */
	private final int baseSize = 10;
	private int baseSeq;

	/**
	 * Base constructor
	 */
	public Base(float x, float y, int seqN) {
		super(x, y);
		this.setSize(baseSize);
		this.setColor(ColorUtil.BLUE);
		this.baseSeq = seqN;
	}
	
	/**
	 * Base sequence number
	 */
	public int getBaseSeq() {
		return this.baseSeq;
	}
	
	/**
	 * Returns size, sequence number, and base
	 */
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " size="+ this.baseSize + " seqNum="+this.baseSeq;
		return "Base: "+ parentDesc + myDesc;
		
	}

}