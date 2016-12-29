package aktong.tictactoe_weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SinglePlayer extends AppCompatActivity {
    ImageButton[][] buttonBoard = new ImageButton[3][3];
    int[][] gameBoard = new int[3][3];
    int oppID;
    int moves = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        Toast.makeText(getApplicationContext(), "Choose either lightning or clouds for your icon then" +
                "click any space on the board to begin", Toast.LENGTH_SHORT).show();

        final RadioGroup rGroup = (RadioGroup)findViewById(R.id.radioGroup);
        final RadioButton lightRadio = (RadioButton)rGroup.findViewById(R.id.lightningChoice);
        final RadioButton cloudRadio = (RadioButton)rGroup.findViewById(R.id.cloudsChoice);
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkId) {


                if (checkId == R.id.cloudsChoice) {
                    lightRadio.setEnabled(false);
                    oppID = R.drawable.lightning;
                    setupGame(R.drawable.clouds);
                }
                else {
                    cloudRadio.setEnabled(false);
                    oppID = R.drawable.clouds;
                    setupGame(R.drawable.lightning);
                }
            }
        });

        Button restart = (Button) findViewById(R.id.restartBtn);

        restart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        gameBoard[i][j] = 0;
                        buttonBoard[i][j].setImageResource(android.R.color.transparent);
                        buttonBoard[i][j].setEnabled(true);
                        rGroup.clearCheck();
                        lightRadio.setEnabled(true);
                        cloudRadio.setEnabled(true);
                        moves = 0;
                    }
                }
            }
        });
    }

    private void setupGame(int iconID) {

        buttonBoard[0][0] = (ImageButton) findViewById(R.id.button0);
        buttonBoard[0][1] = (ImageButton) findViewById(R.id.button1);
        buttonBoard[0][2] = (ImageButton) findViewById(R.id.button2);
        buttonBoard[1][0] = (ImageButton) findViewById(R.id.button3);
        buttonBoard[1][1] = (ImageButton) findViewById(R.id.button4);
        buttonBoard[1][2] = (ImageButton) findViewById(R.id.button5);
        buttonBoard[2][0] = (ImageButton) findViewById(R.id.button6);
        buttonBoard[2][1] = (ImageButton) findViewById(R.id.button7);
        buttonBoard[2][2] = (ImageButton) findViewById(R.id.button8);

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j)
                gameBoard[i][j] = 0;
        }

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                buttonBoard[i][j].setOnClickListener(new GameClickListener(i,j,iconID));
            }
        }
    }

    class GameClickListener implements View.OnClickListener{
        int x, y, icon;
        public GameClickListener(int x, int y, int ic) {
            this.x = x;
            this.y = y;
            this.icon = ic;
        }
        public void onClick(View view) {
            buttonBoard[x][y].setImageResource(icon);
            buttonBoard[x][y].setEnabled(false);
            gameBoard[x][y] = 1;
            moves++;
            if (!checkWinner()) {
                if (moves == 9){
                    Toast.makeText(getApplicationContext(), "No Winner! Click Restart if you " +
                            "want to play a new game!", Toast.LENGTH_SHORT).show();
                }
                makeMove();
                moves++;
                checkWinner();
            }
            else {
                for (int i = 0; i < 3 ; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (gameBoard[i][j] == 0) {
                            buttonBoard[i][j].setEnabled(false);
                        }
                    }
                }
            }
        }
    }

    private boolean checkWinner() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (gameBoard[i][j] != 0) {
                    if ((gameBoard[(i + 1) % 3][j] == gameBoard[i][j]) &&
                            (gameBoard[(i + 2) % 3][j] == gameBoard[i][j])) {
                        if (gameBoard[(i + 1) % 3][j] == 1) {
                            Toast.makeText(getApplicationContext(), "You are the winner!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "You Lose. Computer Wins!",
                                    Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(getApplicationContext(),
                                "Click Restart if you want to play a new game!",
                                Toast.LENGTH_LONG).show();
                        return true;
                    }
                    if ((gameBoard[i][(j + 1) % 3] == gameBoard[i][j]) &&
                            (gameBoard[i][(j + 2) % 3] == gameBoard[i][j])) {
                        if (gameBoard[i][(j + 1) % 3] == 1) {
                            Toast.makeText(getApplicationContext(), "You are the winner!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "You Lose. Computer Wins!",
                                    Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    } else {
                        if (i == 0) {
                            if (j == 0) {
                                if (((gameBoard[1][1] == gameBoard[i][j]) &&
                                        (gameBoard[2][2] == gameBoard[i][j]))) {

                                    if (gameBoard[i][j] == 1) {
                                        Toast.makeText(getApplicationContext(),
                                                "You are the winner!",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(),
                                                "You Lose. Computer Wins!",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                    return true;
                                }
                            }
                            if (j == 2) {
                                if (((gameBoard[1][1] == gameBoard[i][j]) &&
                                        (gameBoard[2][0] == gameBoard[i][j]))) {

                                    if (gameBoard[i][j] == 1) {
                                        Toast.makeText(getApplicationContext(),
                                                "You are the winner!",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(),
                                                "You Lose. Computer Wins!",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                    return true;
                                }
                                break;
                            }

                        }

                    }
                }
            }
        }
        return false;
    }

    public void makeMove() {
        boolean leave = false;
        boolean moveMade = false;
        //Check if Computer can Win
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if ((gameBoard[i][j] == 0) && ((((gameBoard[(i+1)%3][j] == 2) &&
                        (gameBoard[(i+2)%3][j] == 2)) || ((gameBoard[i][(j+1)%3] == 2) &&
                        (gameBoard[i][(j+2)%3] == 2)) || ((gameBoard[(i+1)%3][(j+1)%3] == 2) &&
                        (gameBoard[(i+2)%3][(j+2)%3] == 2))))) {

                    gameBoard[i][j] = 2;
                    buttonBoard[i][j].setImageResource(oppID);
                    buttonBoard[i][j].setEnabled(false);
                    leave = true;
                    moveMade = true;
                    break;
                }
            }
            if (leave) {
                break;
            }
        }
        // Check if Opponent can Win for across and down
        if (moveMade == false) {
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    if ((gameBoard[i][j] == 0) && ((((gameBoard[(i + 1) % 3][j] == 1) &&
                            (gameBoard[(i + 2) % 3][j] == 1)) ||
                            ((gameBoard[i][(j + 1) % 3] == 1) && (gameBoard[i][(j + 2) % 3] == 1))))) {
                        gameBoard[i][j] = 2;
                        buttonBoard[i][j].setImageResource(oppID);
                        buttonBoard[i][j].setEnabled(false);
                        leave = true;
                        moveMade = true;
                        break;
                    }

                }
                if (leave) {
                    break;
                }
            }
        }
        // Check if Opponent can win for across

        if (moveMade == false) {
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    if ((gameBoard[i][j] == 0) && (((gameBoard[(i+1)%3][(j+1)%3] == 1) &&
                            (gameBoard[(i+2)%3][(j+2)%3] == 1)) || ((gameBoard[(i+1)%3][(j+2)%3] == 1) &&
                            (gameBoard[(i+2)%3][(j+1)%3] == 1)))) {
                        gameBoard[i][j] = 2;
                        buttonBoard[i][j].setImageResource(oppID);
                        buttonBoard[i][j].setEnabled(false);
                        leave = true;
                        moveMade = true;
                        break;
                    }

                }
                if (leave) {
                    break;
                }
            }
        }
        // Simple Move Otherwise
        if (moveMade == false){
            leave = false;
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    if (gameBoard[i][j] == 0) {
                        gameBoard[i][j] = 2;
                        buttonBoard[i][j].setImageResource(oppID);
                        buttonBoard[i][j].setEnabled(false);
                        leave = true;
                        break;
                    }
                }
                if (leave) {
                    break;
                }
            }
        }
    }
}
