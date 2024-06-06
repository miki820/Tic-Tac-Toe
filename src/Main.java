import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        //Loading the FXML layout for the main stage
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("board.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 380);

        //Setting the title for application window
        stage.setTitle("TicTacToe");

        //Setting the icon for application window
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/icon.png"))));

        //Setting the scene and displaying it
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
