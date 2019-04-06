// Android Studio 3.3.2
// Build #AI-182.5107.16.33.5314842, built on February 15, 2019
// JRE: 1.8.0_152-release-1248-b01 amd64
// JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
// Windows Server 2012 R2 6.3
// Tested to work on Samsung S9+ (SM-G956F)
package thierry.guessinggame;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Connecting Java code with the GUI
    private EditText txtGuess;
    private Button btnGuess;
    private TextView lblOutput;
    private TextView lblRemainingTries;
    private int theNumber;
    private int totalNumberOfTries = 7;
    private int remainingTries = totalNumberOfTries;

    public void checkGuess(){

        // get the number the user entered, Android would return an editable if we don't transform to String
        String theirNumber;
        theirNumber = txtGuess.getText().toString();
        String message = "";
        String message2 = "";

        try{
            int guess = Integer.parseInt(theirNumber);
            if (guess > theNumber){// too high
                message = guess + " was too high. Guess again!";
                lblOutput.setText(message);
                remainingTries--;
                message2 = "You have " + remainingTries + " remaining tries!" ;
                lblRemainingTries.setText(message2);
            }
            else if (guess < theNumber){// too low
                message = guess + " was too low. Guess again!";
                lblOutput.setText(message);
                remainingTries--;
                message2 = "You have " + remainingTries + " remaining tries!" ;
                lblRemainingTries.setText(message2);
            }
            else{// correct number
                message = guess + " was correct! You win! Play again!";
                lblOutput.setText(message);
                remainingTries--;
                message2 = "You have guessed the number in " + (totalNumberOfTries - remainingTries) + " attempt(s)!";
                lblRemainingTries.setText(message2);
                newGame();
            }
        }
        catch (Exception ex){
            message = "Error, the number is: " + theNumber;
            lblOutput.setText(message);
        }
        //highlight the txtGuess text field
        finally{
            // requestFocus will move the cursor back in the text field if it is not there
            if(remainingTries == 0){
                message = "No more tries. The number was: " + theNumber;
                lblOutput.setText(message);
                message2 = "You will have " + totalNumberOfTries + " tries for the next Game!" ;
                lblRemainingTries.setText(message2);
                newGame();
            }

            txtGuess.requestFocus();
            txtGuess.selectAll();
        }
    }

    private void newGame(){
        theNumber = (int)(Math.random() * 100 + 1);
        totalNumberOfTries = 7;
        remainingTries = totalNumberOfTries;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // onCreate means "When this App loads"
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // scanning the Resource ID
        txtGuess = (EditText) findViewById(R.id.txtGuess);
        btnGuess = (Button) findViewById(R.id.btnGuess);
        lblOutput = (TextView) findViewById(R.id.lblOutput);
        lblRemainingTries = (TextView) findViewById(R.id.lblRemainingTries);

        newGame();
        //setup the event listener for the Guess! button
        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // we tell what happen when we click a button
                checkGuess();
            }
        });

        // set the event listener for our input field
        txtGuess.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                checkGuess();
                return true;
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
