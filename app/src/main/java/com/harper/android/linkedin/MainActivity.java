package com.harper.android.linkedin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView tvQuestion;
    private EditText etAnswer;
    private Button btnNext;
    private ArrayList<QuestionItem> questionItems;

    private int currentQuestion = 0;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQuestion = findViewById(R.id.tvQuestion);
        etAnswer = findViewById(R.id.etAnswer);
        btnNext = findViewById(R.id.btnNext);

        //go to next question if any,
        //otherwise over, go to summary
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //saveAnswersToLocalFile();
                saveAnswersToSharedPref();

                if (currentQuestion < questionItems.size() - 1) {
                    currentQuestion++;
                    setQuestionScreen(currentQuestion);

                } else {
                    Toast.makeText(MainActivity.this, "end ", Toast.LENGTH_LONG).show();
                    goSummary();
                }
            }
        });
        clearSharedPref();
        loadAllQuestions();
        setQuestionScreen(currentQuestion);
    }

    private void clearSharedPref() {
        SharedPreferences settings = getApplicationContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        settings.edit().clear().commit();
    }

    //saveAnswersTo shared preference
    private void saveAnswersToSharedPref() {
        String ques = tvQuestion.getText().toString();
        String ans = etAnswer.getText().toString();
        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        String ansValue = sharedpreferences.getString("answer", "");
        String quesValue = sharedpreferences.getString("question", "");

        String appendQue = append(quesValue, ques);
        String appendAns = append(ansValue, ans);

        editor.putString("question", appendQue);
        editor.putString("answer", appendAns);
        editor.commit();
//        Toast.makeText(MainActivity.this,"Thanks",Toast.LENGTH_LONG).show();
        etAnswer.setText("");
    }

    protected String append(String oldValue, String newValue) {
        String total = oldValue + ","+ newValue;
        return total;
    }

    //save each question's answer to local file
    private void saveAnswersToLocalFile() {
        String answer = etAnswer.getText().toString();

        try {
            FileOutputStream fileOutputStream = openFileOutput("answers", MODE_PRIVATE);
            fileOutputStream.write(answer.getBytes());
            fileOutputStream.close();
            //Toast.makeText(this, "save to "+ getFilesDir(), Toast.LENGTH_SHORT).show();
            etAnswer.setText("");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void goSummary() {
        Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
        startActivity(intent);
        finish();
    }

    private void setQuestionScreen(int number) {
        tvQuestion.setText(questionItems.get(number).getQuestion());


    }

    private void loadAllQuestions() {
        questionItems = new ArrayList<>();
        String jsonStr = loadJSONFromAssets("linkedin.json");

        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            JSONArray questions = jsonObj.getJSONArray("questions");
            for (int i = 0; i < questions.length(); i++) {
                JSONObject question = questions.getJSONObject(i);

                String questionString = question.getString("question");

                questionItems.add(new QuestionItem(questionString, ""));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private String loadJSONFromAssets(String file) {
        String json = "";
        try {
            InputStream is = getAssets().open(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            System.out.println(json);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
