
package dev.webfx.demo.flexbox;

import dev.webfx.extras.panes.FlexPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;

public class FlexBoxApplication extends Application {

    private final static String SENTENCE = "Move the bar to see how the words are positioned, each word being embed in a node controlled by the flexbox.";
    private final static String[] WORDS = SENTENCE.split(" ");
    private final static Font FONT = Font.font(48);
    private final Slider spaceSlider = new Slider(0, 20, 10);
    private final Slider marginSlider = new Slider(0, 20, 10);
    private final CheckBox spaceTopCheckBox = new CheckBox("Top"),
            spaceLeftCheckBox = new CheckBox("Left"),
            spaceRightCheckBox = new CheckBox("Right"),
            lastRowCheckBox = new CheckBox("Last row");
    private Color nodeColor = Color.PURPLE; // Initial node color
    private final FlexPane[] flexPanes = { createFlexBox(), createFlexBox() };

    @Override
    public void start(Stage primaryStage) {
        lastRowCheckBox.setSelected(true);
        BorderPane borderPane = new BorderPane(setBackgroundColor(Color.BLACK, new SplitPane(flexPanes)));
        HBox settingsBox = new HBox(10, new Text("Words margin:"), marginSlider, new Text("Spacing:"), spaceSlider, spaceTopCheckBox, spaceLeftCheckBox, spaceRightCheckBox, lastRowCheckBox);
        settingsBox.setAlignment(Pos.CENTER);
        BorderPane.setMargin(settingsBox, new Insets(10));
        borderPane.setTop(settingsBox);
        primaryStage.setScene(new Scene(borderPane, 800, 600));
        primaryStage.show();
        marginSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> updateWordMargin());
    }

    private FlexPane createFlexBox() {
        FlexPane flexPane = new FlexPane(Arrays.stream(WORDS).map(this::createWordNode).toArray(Node[]::new));
        // Bindings
        flexPane.horizontalSpaceProperty().bind(spaceSlider.valueProperty());
        flexPane.verticalSpaceProperty().bind(spaceSlider.valueProperty());
        flexPane.spaceTopProperty().bind(spaceTopCheckBox.selectedProperty());
        flexPane.spaceLeftProperty().bind(spaceLeftCheckBox.selectedProperty());
        flexPane.spaceRightProperty().bind(spaceRightCheckBox.selectedProperty());
        flexPane.flexLastRowProperty().bind(lastRowCheckBox.selectedProperty());
        return flexPane;
    }

    private void updateWordMargin() {
        Insets margin = new Insets(marginSlider.getValue());
        for (FlexPane flexPane : flexPanes)
            flexPane.getChildren().forEach(wordNode -> StackPane.setMargin(((StackPane) wordNode).getChildren().get(0), margin));
    }

    private Node createWordNode(String word) {
        Text wordText = new Text(word);
        wordText.setFont(FONT);
        wordText.setFill(Color.WHITE);
        StackPane stackPane = setBackgroundColor(nodeColor, new StackPane(wordText));
        // Rotating color for next node
        nodeColor = nodeColor.deriveColor(20, 1d, 1d, 1d);
        return stackPane;
    }

    private static <R extends Region> R setBackgroundColor(Color color, R region) {
        region.setBackground(new Background(new BackgroundFill(color, null, null)));
        return region;
    }

}