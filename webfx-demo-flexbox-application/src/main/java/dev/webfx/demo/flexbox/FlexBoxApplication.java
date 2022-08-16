
package dev.webfx.demo.flexbox;

import dev.webfx.extras.flexbox.FlexBox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;

public class FlexBoxApplication extends Application {

    private final static String SENTENCE = "Move the bar to see how the words are positioned, each word being embed in a node controlled by the flexbox.";
    private final static String[] WORDS = SENTENCE.split(" ");
    private final static Font FONT = Font.font(48);
    private final static Insets WORD_MARGIN = new Insets(10);
    private final static int FLEXBOX_SPACE = 10;

    private Color nodeColor = Color.PURPLE; // Initial node color

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(setBackgroundColor(Color.BLACK, new SplitPane(createFlexBox(), createFlexBox())), 800, 600));
        primaryStage.show();
    }

    private FlexBox createFlexBox() {
        FlexBox flexBox = new FlexBox(Arrays.stream(WORDS).map(this::createWordNode).toArray(Node[]::new));
        flexBox.setHorizontalSpace(FLEXBOX_SPACE);
        flexBox.setVerticalSpace(FLEXBOX_SPACE);
        return flexBox;
    }

    private Node createWordNode(String word) {
        Text wordText = new Text(word);
        wordText.setFont(FONT);
        wordText.setFill(Color.WHITE);
        StackPane stackPane = setBackgroundColor(nodeColor, new StackPane(wordText));
        StackPane.setMargin(wordText, WORD_MARGIN);
        // Rotating color for next node
        nodeColor = nodeColor.deriveColor(20, 1d, 1d, 1d);
        return stackPane;
    }

    private static <R extends Region> R setBackgroundColor(Color color, R region) {
        region.setBackground(new Background(new BackgroundFill(color, null, null)));
        return region;
    }

}