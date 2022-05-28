package com.example.myquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView question, num_ques, time;
    private Button button1,button2,button3,button4;
    private List<Question>questionList;
    int quesNum;
    private  int score;

    private CountDownTimer countDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        question=findViewById(R.id.question);
        num_ques=findViewById(R.id.num_ques);
        time=findViewById(R.id.time);

        button1=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        getQuestionList();
        score=0;
    }

    private void getQuestionList() {
        questionList=new ArrayList<>();
        questionList.add(new Question("Question 1","A", "B","C","D",2));
        questionList.add(new Question("Question 2","A", "B","C","D",2));
        questionList.add(new Question("Question 3","A", "B","C","D",4));
        questionList.add(new Question("Question 4","A", "B","C","D",1));
        questionList.add(new Question("Question 5","A", "B","C","D",1));
        questionList.add(new Question("Question 6","A", "B","C","D",2));
//        questionList.add(new Question("Question 7","A", "B","C","D",3));
//        questionList.add(new Question("Question 8","A", "B","C","D",3));
//        questionList.add(new Question("Question 9","A", "B","C","D",3));
//        questionList.add(new Question("Question 10","A", "B","C","D",2));
//        questionList.add(new Question("Question 11","A", "B","C","D",1));
//        questionList.add(new Question("Question 12","A", "B","C","D",1));
//        questionList.add(new Question("Question 13","A", "B","C","D",4));
//        questionList.add(new Question("Question 14","A", "B","C","D",4));
//        questionList.add(new Question("Question 15","A", "B","C","D",2));
//
        
        
        setQuestion();


    }

    private void setQuestion() {
        time.setText(String.valueOf(80));
        question.setText(questionList.get(0).getQuestion());
        button1.setText(questionList.get(0).getOptionA());
        button2.setText(questionList.get(0).getOptionB());
        button3.setText(questionList.get(0).getOptionC());
        button4.setText(questionList.get(0).getOptionD());

        num_ques.setText(String.valueOf(1)+"/"+String.valueOf(questionList.size()));
        startTime();

        quesNum=0;
         
    }

    private void startTime() {

        CountDownTimer countDown= new CountDownTimer(80000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                //if(millisUntilFinished<80000)
                    time.setText(String.valueOf(millisUntilFinished/1000));


            }

            @Override
            public void onFinish() {
                changeQuestion();


            }
        };
        countDown.start();
    }

    @Override
    public void onClick(View v) {
        int selectedOption=0;

        switch (v.getId()){
            case R.id.button:
                selectedOption=1;
                break;
            case R.id.button2:
                selectedOption=2;
                break;

            case R.id.button3:
                selectedOption=3;
                break;

            case R.id.button4:
                selectedOption=4;
                break;
            default:
        }
        //countDown.cancel();
        checkAnswer(selectedOption, v);

    }

    private void checkAnswer(int selectedOption, View view) {
        if (selectedOption==questionList.get(quesNum).getCorectAns()){

            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            score++;

        }else {
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.RED));

            switch (questionList.get(quesNum).getCorectAns()){
                case 1:
                    button1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 2:
                    button2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 3:
                    button3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 4:
                    button4.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
            }


        }
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeQuestion();
            }
        },1000);

    }

    private void changeQuestion() {
        if (quesNum<questionList.size()-1){

            quesNum++;

            playAnim(question,0,0);
            playAnim(button1,0,1);
            playAnim(button2,0,2);
            playAnim(button3,0,3);
            playAnim(button4,0,4);

            num_ques.setText(String.valueOf(quesNum+1)+"/"+String.valueOf(questionList.size()));
            time.setText(String.valueOf(80));
            startTime();




        }else {

            Intent intent=new Intent(QuestionActivity.this,ScoreActivity.class);
            intent.putExtra("Điểm của bạn",String.valueOf(score)+"/"+String.valueOf(questionList.size()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

            //QuestionActivity.this.finish();
        }
    }

    private void playAnim(View view, final  int value, int viewNum) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500)
                .setStartDelay(100).setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {


                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (value==0){
                            switch (viewNum){
                                case 0:
                                    ((TextView)view).setText(questionList.get(quesNum).getQuestion());
                                    break;
                                case 1:
                                    ((Button)view).setText(questionList.get(quesNum).getOptionA());
                                    break;
                                case 2:
                                    ((Button)view).setText(questionList.get(quesNum).getOptionB());
                                    break;
                                case 3:
                                    ((Button)view).setText(questionList.get(quesNum).getOptionC());
                                    break;
                                case 4:
                                    ((Button)view).setText(questionList.get(quesNum).getOptionD());
                                    break;

                            }
                            if (viewNum!=0){
                                ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E99C03")));
                            }
                            playAnim(view,1,viewNum);

                        }

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        countDown.cancel();

    }
}