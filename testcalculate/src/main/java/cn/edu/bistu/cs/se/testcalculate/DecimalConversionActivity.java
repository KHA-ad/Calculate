package cn.edu.bistu.cs.se.testcalculate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DecimalConversionActivity extends AppCompatActivity {
    public static String option = ""; //记录进制转换的选择
    TextView textViewInput; //转换数字
    TextView textViewResult; //转换结果
    Button buttonMenu;
    Button buttonDEL;//按钮退格
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button buttonA;
    Button buttonB;
    Button buttonC;
    Button buttonD;
    Button buttonE;
    Button buttonF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decimal_conversion);
        //按钮实例化
        buttonDEL = (Button) findViewById(R.id.decimal_DEL_btn);//按钮退格
        button0 = (Button) findViewById(R.id.decimal_zero_btn);
        button1 = (Button) findViewById(R.id.decimal_one_btn);
        button2 = (Button) findViewById(R.id.decimal_two_btn);
        button3 = (Button) findViewById(R.id.decimal_three_btn);
        button4 = (Button) findViewById(R.id.decimal_four_btn);
        button5 = (Button) findViewById(R.id.decimal_five_btn);
        button6 = (Button) findViewById(R.id.decimal_six_btn);
        button7 = (Button) findViewById(R.id.decimal_seven_btn);
        button8 = (Button) findViewById(R.id.decimal_eight_btn);
        button9 = (Button) findViewById(R.id.decimal_nine_btn);
        buttonA = (Button) findViewById(R.id.decimal_A_btn);
        buttonB = (Button) findViewById(R.id.decimal_B_btn);
        buttonC = (Button) findViewById(R.id.decimal_C_btn);
        buttonD = (Button) findViewById(R.id.decimal_D_btn);
        buttonE = (Button) findViewById(R.id.decimal_E_btn);
        buttonF = (Button) findViewById(R.id.decimal_F_btn);
        textViewInput = (TextView) findViewById(R.id.decimal_origin); //转换数字文本框
        textViewResult = (TextView) findViewById(R.id.decimal_change); //转换结果文本框
        buttonMenu = (Button) findViewById(R.id.decimal_menu_btn);  //菜单按钮

        Button buttonBinaryChange = (Button) findViewById(R.id.binaryChange_btn);   //转换数字二进制
        Button buttonOctonaryChange = (Button) findViewById(R.id.octonaryChange_btn);   //转换数字八进制
        Button buttonDecimalChange = (Button) findViewById(R.id.decimalChange_btn);     //转换数字十进制
        Button buttonHexadecimalChange = (Button) findViewById(R.id.hexadecimalChange_btn);     //转换数字十六进制
        Button buttonBinaryOrigin = (Button) findViewById(R.id.binaryOrigin_btn);   //转换结果二进制
        Button buttonOctonaryOrigin = (Button) findViewById(R.id.octonaryOrigin_btn);   //转换结果八进制
        Button buttonDecimalOrigin = (Button) findViewById(R.id.decimalOrigin_btn);     //转换结果十进制
        Button buttonHexadecimalOrigin = (Button) findViewById(R.id.hexadecimalOrigin_btn);     //转换结果十六进制

        //设置按钮不可用
        button0.setEnabled(false);
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        button5.setEnabled(false);
        button6 .setEnabled(false);
        button7.setEnabled(false);
        button8.setEnabled(false);
        button9.setEnabled(false);
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
        buttonE.setEnabled(false);
        buttonF.setEnabled(false);
        //设置按钮点击事件
        buttonDEL.setOnClickListener(new CommonOriginListener());
        buttonMenu.setOnClickListener(new CommonOriginListener());
        //转换数字按钮
        buttonBinaryOrigin.setOnClickListener(new OriginListener());
        buttonOctonaryOrigin.setOnClickListener(new OriginListener());
        buttonDecimalOrigin.setOnClickListener(new OriginListener());
        buttonHexadecimalOrigin.setOnClickListener(new OriginListener());
        //转换结果按钮
        buttonBinaryChange.setOnClickListener(new ChangeListener());
        buttonOctonaryChange.setOnClickListener(new ChangeListener());
        buttonDecimalChange.setOnClickListener(new ChangeListener());
        buttonHexadecimalChange.setOnClickListener(new ChangeListener());
        //数字按钮
        button0.setOnClickListener(new CommonOriginListener());
        button1.setOnClickListener(new CommonOriginListener());
        button2.setOnClickListener(new CommonOriginListener());
        button3.setOnClickListener(new CommonOriginListener());
        button4.setOnClickListener(new CommonOriginListener());
        button5.setOnClickListener(new CommonOriginListener());
        button6.setOnClickListener(new CommonOriginListener());
        button7.setOnClickListener(new CommonOriginListener());
        button8.setOnClickListener(new CommonOriginListener());
        button9.setOnClickListener(new CommonOriginListener());
        buttonA.setOnClickListener(new CommonOriginListener());
        buttonB.setOnClickListener(new CommonOriginListener());
        buttonC.setOnClickListener(new CommonOriginListener());
        buttonD.setOnClickListener(new CommonOriginListener());
        buttonE.setOnClickListener(new CommonOriginListener());
        buttonF.setOnClickListener(new CommonOriginListener());
    }
    // 定义2进制转10进制的方法。
    public static int Change2To10(String numb) {
        int k = 0, result = 0;
        for (int i = Integer.valueOf(numb); i > 0; i /= 10) {
            result += (i % 10) * Math.pow(2, k);
            k++;
        }
        return result;
    }
    // 定义8进制转10进制的方法。
    public static int Change8To10(String numb) {
        int k = 0, temp = 0;
        for (int i = Integer.valueOf(numb); i > 0; i /= 10) {
            temp += (i % 10) * Math.pow(8, k);
            k++;
        }
        return temp;
    }

    class CommonOriginListener implements View.OnClickListener {    //输入监听
        @Override
        public void onClick(View v) {
            String str = textViewInput.getText().toString();
            switch (v.getId()) {
                case R.id.decimal_zero_btn:
                case R.id.decimal_one_btn:
                case R.id.decimal_two_btn:
                case R.id.decimal_three_btn:
                case R.id.decimal_four_btn:
                case R.id.decimal_five_btn:
                case R.id.decimal_six_btn:
                case R.id.decimal_seven_btn:
                case R.id.decimal_eight_btn:
                case R.id.decimal_nine_btn:
                case R.id.decimal_A_btn:
                case R.id.decimal_B_btn:
                case R.id.decimal_C_btn:
                case R.id.decimal_D_btn:
                case R.id.decimal_E_btn:
                case R.id.decimal_F_btn:
                    textViewInput.setText(str + ((Button) v).getText());
                    break;
                case R.id.decimal_DEL_btn:
                    if(str!=null&&str.length()!=0){
                        str=str.substring(0, str.length()-1);
                        textViewInput.setText(str);
                        textViewResult.setText("");
                    }
                    break;
                case R.id.decimal_menu_btn:
                    Intent intent=new Intent(DecimalConversionActivity.this,MenuActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    }
    class ChangeListener implements View.OnClickListener {  // //转换结果按钮监听

        @Override
        public void onClick(View v) {
            String input = textViewInput.getText().toString();
            try {
                switch (v.getId()) {
                    case R.id.binaryChange_btn:
                        if (option.equals("八进制")) {   //8to10to2
                            textViewResult.setText(Integer.toBinaryString(Change8To10(input)));
                        } else if (option.equals("十进制")) { //10to2
                            textViewResult.setText(Integer.toBinaryString(Integer.valueOf(input)));
                        } else if (option.equals("十六进制")) {    //16to10to2
                            textViewResult.setText(Integer.toBinaryString(Integer.parseInt(input, 16)));
                        } else if (option.equals("二进制")) {
                            textViewResult.setText(input);
                        }
                        break;
                    case R.id.octonaryChange_btn:
                        if (option.equals("八进制")) { //8to8
                            textViewResult.setText(input);
                        } else if (option.equals("十进制")) { //10to8
                            textViewResult.setText(Integer.toOctalString(Integer.valueOf(input)));
                        } else if (option.equals("十六进制")) {    //16to10to8
                            textViewResult.setText(Integer.toOctalString(Integer.parseInt(input, 16)));
                        } else if (option.equals("二进制")) { //2to10to8
                            textViewResult.setText(Integer.toOctalString(Change2To10(input)));
                        }
                        break;
                    case R.id.decimalChange_btn:    //十进制
                        if (option.equals("八进制")) { //8to10
                            textViewResult.setText(""+Change8To10(input));
                        } else if (option.equals("十进制")) {
                            textViewResult.setText(input);
                        } else if (option.equals("十六进制")) { //16to10
                            textViewResult.setText(""+Integer.parseInt(input, 16));
                        } else if (option.equals("二进制")) {  //2to10
                            textViewResult.setText(""+Change2To10(input));
                        }
                        break;
                    case R.id.hexadecimalChange_btn:
                        if (option.equals("八进制")) {   //8to10to16
                            textViewResult.setText(Integer.toHexString(Change8To10(input)).toUpperCase());
                        } else if (option.equals("十进制")) { //10to16
                            textViewResult.setText(Integer.toHexString(Integer.valueOf(input)).toUpperCase());
                        } else if (option.equals("十六进制")) {
                            textViewResult.setText(input);
                        } else if (option.equals("二进制")) {  //2to10to16
                            textViewResult.setText(Integer.toHexString(Change2To10(input)).toUpperCase());
                        }
                        break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    class OriginListener implements View.OnClickListener {  //转换数字按钮监听

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.binaryOrigin_btn: //二进制
                    setButtonEnabled(2);
                    option=""+((Button) v).getText();
                    textViewResult.setText("");
                    textViewInput.setText("");
                    break;
                case R.id.octonaryOrigin_btn: //八进制
                    setButtonEnabled(8);
                    option=""+((Button) v).getText();
                    textViewResult.setText("");
                    textViewInput.setText("");
                    break;
                case R.id.decimalOrigin_btn: //十进制
                    setButtonEnabled(10);
                    option=""+((Button) v).getText();
                    textViewResult.setText("");
                    textViewInput.setText("");

                    break;
                case R.id.hexadecimalOrigin_btn: //
                    setButtonEnabled(16);
                    option=""+((Button) v).getText();
                    textViewResult.setText("");
                    textViewInput.setText("");
                    break;
            }
        }
    }

    private void setButtonEnabled(int radix){   //设置按钮可用函数
        if(radix == 2){
            button0.setEnabled(true);
            button1.setEnabled(true);

            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
            button5.setEnabled(false);
            button6 .setEnabled(false);
            button7.setEnabled(false);
            button8.setEnabled(false);
            button9.setEnabled(false);
            buttonA.setEnabled(false);
            buttonB.setEnabled(false);
            buttonC.setEnabled(false);
            buttonD.setEnabled(false);
            buttonE.setEnabled(false);
            buttonF.setEnabled(false);
        }
        if(radix==10){
            button0.setEnabled(true);
            button1.setEnabled(true);
            button2.setEnabled(true);
            button3.setEnabled(true);
            button4.setEnabled(true);
            button5.setEnabled(true);
            button6.setEnabled(true);
            button7.setEnabled(true);
            button8.setEnabled(true);
            button9.setEnabled(true);

            buttonA.setEnabled(false);
            buttonB.setEnabled(false);
            buttonC.setEnabled(false);
            buttonD.setEnabled(false);
            buttonE.setEnabled(false);
            buttonF.setEnabled(false);
        }

        if(radix==8){
            button0.setEnabled(true);
            button1.setEnabled(true);
            button2.setEnabled(true);
            button3.setEnabled(true);
            button4.setEnabled(true);
            button5.setEnabled(true);
            button6.setEnabled(true);
            button7.setEnabled(true);

            button8.setEnabled(false);
            button9.setEnabled(false);
            buttonA.setEnabled(false);
            buttonB.setEnabled(false);
            buttonC.setEnabled(false);
            buttonD.setEnabled(false);
            buttonE.setEnabled(false);
            buttonF.setEnabled(false);
        }
        if(radix==16){
            button0.setEnabled(true);
            button1.setEnabled(true);
            button2.setEnabled(true);
            button3.setEnabled(true);
            button4.setEnabled(true);
            button5.setEnabled(true);
            button6.setEnabled(true);
            button7.setEnabled(true);
            button8.setEnabled(true);
            button9.setEnabled(true);
            buttonA.setEnabled(true);
            buttonB.setEnabled(true);
            buttonC.setEnabled(true);
            buttonD.setEnabled(true);
            buttonE.setEnabled(true);
            buttonF.setEnabled(true);
        }
    }
}
