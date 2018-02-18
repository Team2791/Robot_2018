package org.usfirst.frc.team2791.robot.util;

import edu.wpi.first.wpilibj.buttons.Button;

public class DoubleButton extends Button {
    Button b1, b2;
    String comparasionOpereator;
    
    public DoubleButton(Button button1, Button button2, String comparasionOpereator){
        b1 = button1;
        b2 = button2;
        this.comparasionOpereator = comparasionOpereator;
        assert(comparasionOpereator == "OR" || comparasionOpereator == "AND");
    }
    
    @Override
    public boolean get(){
    	if(comparasionOpereator == "AND") {
    		return b1.get() && b2.get();
    	} else {
    		return b1.get() || b2.get();
    	}
    }
}
