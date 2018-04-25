package com.br.MyFunChatBot.customizedTypes;

import java.awt.GridBagConstraints;

public class CustomizedGridBagConstraints extends GridBagConstraints{

	private static final long serialVersionUID = -5306744783352326407L;

	public CustomizedGridBagConstraints(){
		super();
	}
	
	public CustomizedGridBagConstraints setXYPosition(int x, int y){
		gridx = x;
		gridy = y;
		return this;
	}
	
	public CustomizedGridBagConstraints setXYAnchorPosition(int x, int y, int a){
		gridx = x;
		gridy = y;
		anchor = a;
		return this;
	}
}
