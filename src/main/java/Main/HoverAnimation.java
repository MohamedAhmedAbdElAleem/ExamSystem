package Main;

import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class HoverAnimation implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent mouseEvent) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(300));
        ScaleTransition scaleTransition2 = new ScaleTransition(Duration.millis(300));
        scaleTransition.setToX(1.2);
        scaleTransition.setToY(1.2);
        scaleTransition2.setToX(1);
        scaleTransition2.setToY(1);
        String action =mouseEvent.getEventType().getName();
        switch (action){
            case "MOUSE_ENTERED":
                scaleTransition.setNode((Node) mouseEvent.getSource());
                scaleTransition.playFromStart();
                break;
            case "MOUSE_EXITED":

                scaleTransition2.setNode((Node) mouseEvent.getSource());
                scaleTransition2.playFromStart();
                break;
        }

    }
}
