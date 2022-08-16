
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

    private final static String SENTENCE = "Move the bars to see how the flexbox layouts the words, each word being embed in an individual node.";
    private final static String[] WORDS = SENTENCE.split(" ");
    private final static Font FONT = Font.font(48);
    private final static Insets MARGIN = new Insets(10);

    private static Color COLOR = Color.PURPLE; // Initial value

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(setBackgroundColor(Color.BLACK, new SplitPane(createFlexBox(), createFlexBox())), 800, 600));
        primaryStage.show();
    }

    private static FlexBox createFlexBox() {
        FlexBox flexBox = new FlexBox(Arrays.stream(WORDS).map(FlexBoxApplication::createWordNode).toArray(Node[]::new));
        flexBox.setHorizontalSpace(10);
        flexBox.setVerticalSpace(10);
        return flexBox;
    }

    private static Node createWordNode(String word) {
        Text wordText = new Text(word);
        wordText.setFont(FONT);
        wordText.setFill(Color.WHITE);
        StackPane stackPane = setBackgroundColor(COLOR, new StackPane(wordText));
        COLOR = COLOR.deriveColor(20, 1d, 1d, 1d);
        StackPane.setMargin(wordText, MARGIN);
        return stackPane;
    }

    private static <R extends Region> R setBackgroundColor(Color color, R region) {
        region.setBackground(new Background(new BackgroundFill(color, null, null)));
        return region;
    }

}