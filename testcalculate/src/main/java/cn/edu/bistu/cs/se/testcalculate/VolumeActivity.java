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

public class VolumeActivity extends AppCompatActivity {
    private static final String[] volumeBasic ={"立方毫米(mm³)","立方厘米(cm³)","立方分米(dm³)","立方米(m³)","升(l)","毫升(ml)"};
    private Spinner spinnerBasic;//
    private Spinner spinnerChange;
    private EditText volumeEdit;
    private ArrayAdapter<String> adapterBasic;
    private ArrayAdapter<String> adapterChange;
    private  int basic=0;
    private  int change=0;
    private double num=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button buttonMenu = (Button) findViewById(R.id.volume_menu_btn);
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VolumeActivity.this,MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
        volumeEdit = (EditText) findViewById(R.id.editText_volume);
        spinnerBasic = (Spinner) findViewById(R.id.spinner_basic_volume);
        spinnerChange=(Spinner) findViewById(R.id.spinner_change_volume);
        //将可选内容与ArrayAdapter连接起来
        adapterBasic = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, volumeBasic);
        adapterChange= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, volumeBasic);
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
        Button buttonChange=(Button)findViewById(R.id.volumeChange_btn);
        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=volumeEdit.getText().toString();
                num=Double.parseDouble(str);
                if(basic==4){
                    if(change==1||change==5){
                        num=num * Math.pow(1000,1);
                    }else if(change==0){
                        num=num* Math.pow(1000,2);
                    } else if(change==3){
                        num=num* Math.pow(1000,-1);
                    }
                }else if(basic==5){
                    if(change==2||change==4){
                        num=num * Math.pow(1000,-1);
                    }else if(change==0){
                        num=num* Math.pow(1000,1);
                    } else if(change==3){
                        num=num* Math.pow(1000,-2);
                    }
                }else{
                    num=num * Math.pow(1000,basic - change);
                }
                volumeEdit.setText(""+num);
            }
        });
    }
    //使用数组形式操作
    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            switch (arg0.getId()){
                case R.id.spinner_basic_volume:
                    basic = arg2;
                    break;
                case R.id.spinner_change_volume:
                    change = arg2;
                    break;
            }
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
}
