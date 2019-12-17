package com.harper.android.linkedin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SummaryActivity extends AppCompatActivity {
    List<QuestionItem> itemList;
    RecyclerView rvSummary;
    private SummaryAdapter summaryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        rvSummary = findViewById(R.id.rvSummary);

        itemList = new ArrayList<>();

        SharedPreferences prefs = this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String ans = prefs.getString("answer", null);
        String que = prefs.getString("question", null);


        String[] answers = ans.split(",");
        String[] quesions = que.split(",");

        for (int i = 1; i < answers.length; i++) {
            String a = answers[i];
            String q = quesions[i];

            QuestionItem qi = new QuestionItem(q, a);
            itemList.add(qi);
        }
        summaryAdapter = new SummaryAdapter(itemList);
        rvSummary.setAdapter(summaryAdapter);
        rvSummary.setLayoutManager(new LinearLayoutManager(this));
    }
}
