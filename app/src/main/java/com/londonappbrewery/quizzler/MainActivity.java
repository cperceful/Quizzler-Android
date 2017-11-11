package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {



    // TODO: Declare member variables here:
    Button trueButton;
    Button falseButton;
    TextView questionTextView;
    int index;
    int question;
    TextView scoreTextView;
    ProgressBar progressBar;
    int score;

    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };

    // TODO: Declare constants here
    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0 / mQuestionBank.length);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            score = savedInstanceState.getInt("ScoreKey");
            index = savedInstanceState.getInt("IndexKey");
        } else {
            score = 0;
            index = 0;
        }

        trueButton = (Button) findViewById(R.id.true_button);
        falseButton = (Button) findViewById(R.id.false_button);
        questionTextView = (TextView) findViewById(R.id.question_text_view);
        scoreTextView = (TextView) findViewById(R.id.score);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        question = mQuestionBank[index].getQuestionId();

        questionTextView.setText(question);
        String scoreText = getString(R.string.score, score, mQuestionBank.length);
        scoreTextView.setText(scoreText);


        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                updateQuestion();
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                updateQuestion();
            }
        });



    }

    private void updateQuestion(){
        index = (index + 1) % mQuestionBank.length;
        if (index == 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            //this refers to current object, which is main activity here. It can supply context
            //as an alternative to getApplicationContext()

            alert.setTitle("Game over, jabroni");
            alert.setCancelable(false);
            alert.setMessage("You got " + score + " points!");
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }
        question = mQuestionBank[index].getQuestionId();
        questionTextView.setText(question);
        progressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        String scoreText = getString(R.string.score, score, mQuestionBank.length);
        scoreTextView.setText(scoreText);
    }

    private void checkAnswer(boolean answer){
        boolean correctAnswer = mQuestionBank[index].isCorrectAnswer();

        if (answer == correctAnswer) {
            Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
            score++;
        } else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }

    //Bundle stores information about app state as key-value pairs
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("ScoreKey", score);
        outState.putInt("IndexKey", index);
    }
}
