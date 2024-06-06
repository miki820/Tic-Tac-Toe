import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

//Controller class to manage game logic
public class TicTacToe implements Initializable {

    //FXML annotations to link UI components from FXML file
    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    @FXML
    private Button b6;
    @FXML
    private Button b7;
    @FXML
    private Button b8;
    @FXML
    private Button b9;
    @FXML
    private Text Title;

    ArrayList<Button> buttons;
    private String[] winning;
    private int playerTurn = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initializing buttons list and game state array
        buttons = new ArrayList<>(Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8, b9));
        winning = new String[9];
        Arrays.fill(winning, "");

        //Setting up each button's behavior
        buttons.forEach(this::setupButtonActions);
    }

    //Method to restart the game
    @FXML
    void restart() {
        buttons.forEach(button -> {
                    button.setDisable(false);
                    button.setGraphic(null);
                }
        );
        Title.setText("TicTacToe");
        playerTurn = 0;
        Arrays.fill(winning, "");
        buttons.forEach(this::setupButtonActions);

        b1.requestFocus();
    }

    //Method for buttons configuration
    private void setupButtonActions(Button button) {
        //Mouse click behavior for buttons
        button.setOnMouseClicked(mouseEvent -> handleButtonClick(button));

        //Numpad press behavior for buttons
        button.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case NUMPAD7 -> handleButtonClick(b1);
                case NUMPAD8 -> handleButtonClick(b2);
                case NUMPAD9 -> handleButtonClick(b3);
                case NUMPAD4 -> handleButtonClick(b4);
                case NUMPAD5 -> handleButtonClick(b5);
                case NUMPAD6 -> handleButtonClick(b6);
                case NUMPAD1 -> handleButtonClick(b7);
                case NUMPAD2 -> handleButtonClick(b8);
                case NUMPAD3 -> handleButtonClick(b9);
            }
        });
    }

    //Method to handle button clicks
    private void handleButtonClick(Button button) {
        if (!button.isDisabled()) {
            changeMove(button);
            updateGameState(button);
            button.setDisable(true);
            checkGameOver();
        }
    }

    //Updates graphics and title after a move
    public void changeMove(Button button) {
        if (playerTurn % 2 == 0) {
            button.setGraphic(new ImageView(new Image("images/circle.png")));
            Title.setText("X moving...");
            playerTurn = 1;
        } else {
            button.setGraphic(new ImageView(new Image("images/cross.png")));
            Title.setText("O moving...");
            playerTurn = 0;
        }
    }

    //Updating game state array after click
    public void updateGameState(Button button) {
        if (playerTurn % 2 == 0) {
            winning[buttons.indexOf(button)] = "X";
        } else {
            winning[buttons.indexOf(button)] = "O";
        }
    }

    //Checking if game is over
    public void checkGameOver() {
        String winner = (playerTurn % 2 == 0) ? "X" : "O";

        String[][] winningCombinations = {
                {winning[0], winning[1], winning[2]},
                {winning[3], winning[4], winning[5]},
                {winning[6], winning[7], winning[8]},
                {winning[0], winning[3], winning[6]},
                {winning[1], winning[4], winning[7]},
                {winning[2], winning[5], winning[8]},
                {winning[0], winning[4], winning[8]},
                {winning[2], winning[4], winning[6]}
        };

        //Checking for a winner
        for (String[] combination : winningCombinations) {
            if (combination[0].equals(winner) && combination[1].equals(winner) && combination[2].equals(winner)) {
                Title.setText(winner + " won");
                disableButtons();
                return;
            }
        }

        //Checking for a draw
        if (Arrays.stream(winning).noneMatch(String::isEmpty)) {
            Title.setText("Draw");
            disableButtons();
        }
    }


    //Method to disable all buttons
    private void disableButtons() {
        buttons.forEach(button -> button.setDisable(true));
    }
}
