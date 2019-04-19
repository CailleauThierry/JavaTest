// Author: Thierry Cailleau on 04_14_2019 based on "Java the easy way" course by Bryson Payne
// Android Studio 3.3.1
// Build #AI-182.5107.16.33.5264788, built on January 28, 2019
// JRE: 1.8.0_152-release-1248-b01 amd64
// JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
// Windows Server 2012 R2 6.3
// Tested to work on Samsung S9+ (SM-G956F)

package com.example.secretmessages;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;


public class MainActivity extends AppCompatActivity {
    // Setting up variables for our GUI
    EditText txtIn;
    EditText txtKey;
    EditText txtOut;
    SeekBar sb;
    Button button;


    public String encode(String message, int k) {
        String out="";
        char key = (char)k;

        for (int x=0 ; x < message.length(); x++)
        {
            char in = message.charAt(x);

            if (in >= 'A' && in <= 'Z') {
                in += key;
                if (in > 'Z')
                {
                    //if the character went past Z we wrap it around back to the beginning of the alphabet i.e. back 26 characters
                    in -= 26;
                }
                if (in < 'A') {
                    //if the character went less than A, we bring it back forward 26 characters
                    in += 26;
                }
            }
            if (in >= 'a' && in <= 'z') {
                in += key;
                if (in > 'z')
                {
                    //if the character went past z we wrap it around back to the beginning of the alphabet i.e. back 26 characters
                    in -= 26;
                }
                if (in < 'a') {
                    //if the character went less than a, we bring it back forward 26 characters
                    in += 26;
                }
            }
            if (in >= '0' && in <= '9') {
                // with a key of 12, 83 becomes :5 when we want it to stay a number. If we did not want it to stay a number (there are only 10 digits so that does not span 13) the modulo % operator would not be needed. The decrypting would have worked fine
                in += (k % 10); // Ex: 13 % 10 is 3, so modulo insures the number stays between 0 and 9, so some numbers will be re-used for more than 1 encryption
                if (in > '9')
                {
                    //if the character went past 9 we wrap it around back to the beginning of the number range i.e. back 10 characters
                    in -= 10;
                }
                if (in < '0') {
                    //if the character went less than 0, we bring it back forward 10 characters
                    in += 10;
                }
            }

            out += in;

        }
        return out;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //  We created the same variable name txtIn, txtKey, txtOut than the ones created by R framework from consistency
        // but the name does not have to match (like sb does not match seekBar)
        txtIn = (EditText)findViewById(R.id.txtIn);
        txtKey = (EditText)findViewById(R.id.txtKey);
        txtOut = (EditText)findViewById(R.id.txtOut);
        sb = (SeekBar)findViewById(R.id.seekBar);
        button =(Button)findViewById(R.id.button);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int key = progress - 13;
                // android does not return a String right aware it returns an Editable which contains a few more methods
                String message = txtIn.getText().toString();
                String out = encode(message, key);
                // txtOut.setText(out); takes the encoded message and sent it to the txtOut view
                txtOut.setText(out);
                txtKey.setText("" + key);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            // you can change this if you want to pause a playing video when the user uses this app
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // you can change this if you want to pause a playing video when the user uses this app
            }
        });

        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                int key = Integer.parseInt(txtKey.getText().toString());
                int progress = key + 13;
                // android does not return a String right aware it returns an Editable which contains a few more methods
                String message = txtIn.getText().toString();
                String out = encode(message, key);
                // txtOut.setText(out); takes the encoded message and sent it to the txtOut view
                txtOut.setText(out);
                sb.setProgress(progress);
            }
        });

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
