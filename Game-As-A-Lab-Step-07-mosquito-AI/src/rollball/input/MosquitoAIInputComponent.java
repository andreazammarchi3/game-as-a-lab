package rollball.input;

import rollball.common.V2d;
import rollball.model.*;
import java.util.*;

public class MosquitoAIInputComponent implements InputComponent {

	private long lastChoiceTime;
	private Random rand;
	private static final long DECISION_PERSISTANCE_TIME = 50;
	
	public MosquitoAIInputComponent(){
		rand = new Random(System.currentTimeMillis());
		lastChoiceTime = 0;
	}
	
	public void update(GameObject ball, InputController ctrl){
		
		if (isTimeToChoose()){		
			int choice = rand.nextInt(4);
			if (choice == 0){
				double speed = ball.getCurrentVel().module();
				ball.setVel(new V2d(0,1).mul(speed));
			} else if (choice == 1){
				double speed = ball.getCurrentVel().module();
				ball.setVel(new V2d(0,-1).mul(speed));
			} else if (choice == 2){
				double speed = ball.getCurrentVel().module();
				ball.setVel(new V2d(-1,0).mul(speed));			
			} else {
				double speed = ball.getCurrentVel().module();
				ball.setVel(new V2d(1,0).mul(speed));			
			}
			lastChoiceTime = System.currentTimeMillis();
		}
	}

	private boolean isTimeToChoose(){
		return System.currentTimeMillis() - lastChoiceTime > DECISION_PERSISTANCE_TIME;
	}
}
