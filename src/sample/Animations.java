package sample;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Static Animations class I pulled from my last project.
 */
class Animations {
    private static final Interpolator materialInterp = Interpolator.SPLINE(.62,.28,.23,.99);

    public static ParallelTransition newCardAnimation(Node node) {
        node.setTranslateX(-180);
        node.setTranslateY(-180);
        node.setScaleX(0);
        node.setScaleY(0);
        ParallelTransition NewCard = new ParallelTransition(node);

        ScaleTransition cardScale = new ScaleTransition(Duration.millis(700));
        TranslateTransition cardTranslate = new TranslateTransition(Duration.millis(700));

        cardTranslate.setToX(0);
        cardTranslate.setToY(0);
        cardTranslate.setCycleCount(1);
        cardTranslate.setInterpolator(materialInterp);

        cardScale.setInterpolator(materialInterp);
        cardScale.setToX(1);
        cardScale.setToY(1);
        cardScale.setCycleCount(1);

        NewCard.getChildren().addAll(cardScale, cardTranslate);
        return NewCard;
    }

    public static ParallelTransition newCardDestroyAnimation(Node node) {
        ParallelTransition NewCardDestroy = new ParallelTransition(node);

        ScaleTransition cardScale = new ScaleTransition(Duration.millis(700));
        TranslateTransition cardTranslate = new TranslateTransition(Duration.millis(700));

        cardTranslate.setToX(-180);
        cardTranslate.setToY(-180);
        cardTranslate.setCycleCount(1);
        cardTranslate.setInterpolator(materialInterp);

        cardScale.setInterpolator(materialInterp);
        cardScale.setToX(0);
        cardScale.setToY(0);
        cardScale.setCycleCount(1);

        NewCardDestroy.getChildren().addAll(cardScale, cardTranslate);
        return NewCardDestroy;
    }
}
