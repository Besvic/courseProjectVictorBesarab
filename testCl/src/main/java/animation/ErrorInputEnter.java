package animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;



public class ErrorInputEnter {

    private TranslateTransition translateTransition;

    public ErrorInputEnter(Node node) {
        translateTransition = new TranslateTransition(Duration.millis(50),  node);
        translateTransition.setFromX(0f);
        translateTransition.setByX(10f);
        translateTransition.setCycleCount(3);
        translateTransition.setAutoReverse(true);
    }
    public void playAnimation(){
        translateTransition.playFromStart();
    }
}
