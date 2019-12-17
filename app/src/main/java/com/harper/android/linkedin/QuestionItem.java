package com.harper.android.linkedin;

/**
 * Created: xuemaomao
 * Date: 2019-12-16
 * Package: com.harper.android.linkedin
 * File: QuestionItem
 * Description: TODO
 */
public class QuestionItem {
    String question, answer;

    public QuestionItem(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

//    public static QuestionItem fromJson(JSONObject jsonObject) {
//        QuestionItem questionItem = new QuestionItem();
//        try {
//            //Deserialize json into object fields
//            questionItem.question = jsonObject.getString("question");
//            questionItem.answer = jsonObject.getString("answer");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return questionItem;
//    }

    // Decodes array of book json results into business model objects
//    public static ArrayList<QuestionItem> fromJson(JSONArray jsonArray) {
//        ArrayList<QuestionItem> questionItems = new ArrayList<>(jsonArray.length());
//        // Process each result in json array, decode and convert to business
//        // object
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject questionItemJson;
//            try {
//                questionItemJson = jsonArray.getJSONObject(i);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                continue;
//            }
//            QuestionItem questionItem = QuestionItem.fromJson(questionItemJson);
//            if (questionItem != null) {
//                questionItems.add(questionItem);
//            }
//        }
//        return questionItems;
//    }
}
