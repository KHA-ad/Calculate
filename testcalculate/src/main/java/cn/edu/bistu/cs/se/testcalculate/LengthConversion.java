package cn.edu.bistu.cs.se.testcalculate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class LengthConversion extends AppCompatActivity {

    private static final String[] lenghtBasic={"毫米(mm)","厘米(cm)","分米(dm)","米(m)","千米(km)"};
    private Spinner spinnerBasic;//
    private Spinner spinnerChange;
    private EditText lengthEdit;
    private ArrayAdapter<String> adapterBasic;
    private ArrayAdapter<String> adapterChange;
    private  int basic=0;
    private  int change=0;
    private double num=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length_conversion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button buttonMenu = (Button) findViewById(R.id.length_menu_btn);
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LengthConversion.this,MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
        lengthEdit = (EditText) findViewById(R.id.editText_length);
        spinnerBasic = (Spinner) findViewById(R.id.spinner_basic);
        spinnerChange=(Spinner) findViewById(R.id.spinner_change);
        //将可选内容与ArrayAdapter连接起来
        adapterBasic = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lenghtBasic);
        adapterChange= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lenghtBasic);
        //设置下拉列表的风格
        adapterBasic.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterChange.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        spinnerBasic.setAdapter(adapterBasic);
        spinnerChange.setAdapter(adapterChange);
        //添加事件Spinner事件监听
        spinnerBasic.setOnItemSelectedListener(new SpinnerSelectedListener());
        spinnerChange.setOnItemSelectedListener(new SpinnerSelectedListener());
        //设置默认值
        spinnerBasic.setVisibility(View.VISIBLE);
        spinnerChange.setVisibility(View.VISIBLE);
        Button buttonChange=(Button)findViewById(R.id.lengthChange_btn);
        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=lengthEdit.getText().toString();
                num=Double.parseDouble(str);
                num=num * Math.pow(10,basic - change);
                if(change==4){
                    num=num/100;
                }else if(basic==4){
                    num=num*100;
                }
                lengthEdit.setText(""+num);
            }
        });
    }
        //使用数组形式操作
        class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                       long arg3) {
                switch (arg0.getId()){
                    case R.id.spinner_basic:
                        basic = arg2;
                        break;
                    case R.id.spinner_change:
                        change = arg2;
                        break;
                }
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        }
    }
