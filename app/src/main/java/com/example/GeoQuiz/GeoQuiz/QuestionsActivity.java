package com.example.vikasojha.quizbee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionsActivity extends AppCompatActivity {
    TextView tv;
    Button submitbutton, quitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;

    String questions[] = {
                            "Qual é a capital do Acre?",
                            "Qual desses países não é da Europa?",
                            "Em qual continente está o Brasil?",
                            "Qual desses países não é africano?",
                            "Quantos países existem no continente americano?",
                            "Qual a capial do México?"
                         };
    String answers[] = {"Rio Branco","Casaquistão","América do Sul","Arábia Saudita","37","Cidade do México"};
    String opt[] = {
                    "Rio Branco","Acre","Macapá","Porto Alegre",
                    "França","Alemanha","Bélgica","Casaquistão",
                    "América do Norte","América do Sul","Ásia","África",
                    "Egito","Madagascar","Arábia Saudita","Ruanda",
                    "37","52","95","41",
                    "Del México","Cidade do México","Tihuana","Mexicali"
                   };
    int flag=0;
    public static int marks=0,correct=0,wrong=0;

    protected void onSaveInstanceState(Bundle dados){
        super.onSaveInstanceState(dados);
        dados.putInt("flag", flag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);


        final TextView score = (TextView)findViewById(R.id.textView4);
        TextView textView=(TextView)findViewById(R.id.DispName);
        Intent intent = getIntent();
        String name= intent.getStringExtra("myname");

        if(savedInstanceState != null){
            flag = savedInstanceState.getInt("flag");
        }else{
            flag = 0;
        }

        if (name.trim().equals("")){
            textView.setText("Olá");
            score.setText("0");
        }else {
            textView.setText("Olá, " + name);
            score.setText("" + correct);
        }
        
        submitbutton=(Button)findViewById(R.id.button3);
        quitbutton=(Button)findViewById(R.id.buttonquit);
        tv=(TextView) findViewById(R.id.tvque);

        radio_g=(RadioGroup)findViewById(R.id.answersgrp);
        rb1=(RadioButton)findViewById(R.id.radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton2);
        rb3=(RadioButton)findViewById(R.id.radioButton3);
        rb4=(RadioButton)findViewById(R.id.radioButton4);
        tv.setText(questions[flag]);
        rb1.setText(opt[flag*4]);
        rb2.setText(opt[flag*4 +1]);
        rb3.setText(opt[flag*4 +2]);
        rb4.setText(opt[flag*4 +3]);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int color = mBackgroundColor.getColor();
                //mLayout.setBackgroundColor(color);

                if(radio_g.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Selecione uma opção!", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton uans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uans.getText().toString();
//                Toast.makeText(getApplicationContext(), ansText, Toast.LENGTH_SHORT).show();
                if(ansText.equals(answers[flag])) {
                    correct++;
                    Toast.makeText(getApplicationContext(), "Correto!", Toast.LENGTH_SHORT).show();
                }
                else {
                    wrong++;
                    Toast.makeText(getApplicationContext(), "Errado!", Toast.LENGTH_SHORT).show();
                }

                flag++;

                if (score != null)
                    score.setText(""+correct);

                if(flag<questions.length)
                {
                    tv.setText(questions[flag]);
                    rb1.setText(opt[flag*4]);
                    rb2.setText(opt[flag*4 +1]);
                    rb3.setText(opt[flag*4 +2]);
                    rb4.setText(opt[flag*4 +3]);
                }
                else
                {
                    marks=correct;
                    Intent in = new Intent(getApplicationContext(),ResultActivity.class);
                    startActivity(in);
                }
                radio_g.clearCheck();
            }
        });

        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_refresh){
            toast("Reiniciar");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void toast(String msg){
        Toast.makeText(this, msg.toString(), Toast.LENGTH_SHORT).show();
        Intent in = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(in);
    }
}