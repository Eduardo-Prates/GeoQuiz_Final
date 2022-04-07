package com.example.vikasojha.quizbee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {
    TextView tv, tv2;
    Button btnRestart;
    String tv_text,tv2_text;

    protected void onSaveInstanceState(Bundle dados){
        super.onSaveInstanceState(dados);
        dados.putString("tv", tv_text);
        dados.putString("tv2", tv2_text);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tv = (TextView)findViewById(R.id.tvres);
        tv2 = (TextView)findViewById(R.id.tvres2);
        btnRestart = (Button) findViewById(R.id.btnRestart);

        StringBuffer sb = new StringBuffer();
        sb.append("Acertos: " + QuestionsActivity.correct + "\n");
        StringBuffer sb2 = new StringBuffer();
        sb2.append("Erros: " + QuestionsActivity.wrong + "\n");
        tv_text = sb.toString();
        tv2_text = sb2.toString();

        if(savedInstanceState != null){
            tv_text = savedInstanceState.getString("tv");
            tv2_text = savedInstanceState.getString("tv2");
        }else{
            tv_text = sb.toString();
            tv2_text = sb2.toString();
        }
        tv.setText(tv_text);
        tv2.setText(tv2_text);

        QuestionsActivity.correct=0;
        QuestionsActivity.wrong=0;

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(in);
            }
        });
    }

}
