package cn.edu.bistu.cs.se.testcalculate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ScienceCalculateActivity extends AppCompatActivity implements View.OnClickListener{
    TextView textViewInput; //显示当前输入的数字
    TextView textViewResult; //显示表达式及结果
    String stringInput; //记录输入的表达式
    Button buttonMenu;

    private static boolean pointflag = false; //小数点重复表示
    boolean num_flag;//运算出结果后，再次点击数字按钮屏幕清空标识
    private static boolean operator = false;//运算符重复标识

    static LinkedList<String> opStack=new LinkedList<String>();

    static Map<String, Integer> priorityOption=new HashMap<String, Integer>(){
        {
            put("(", 0);
            put(")", 0);
            put("+", 1);
            put("-", 1);
            put("×", 2);
            put("÷",2);
            put("√",4);
            put("^",4);
            put("l",3);
            put("s",3);
            put("c",3);
            put("t",3);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science_calculate);
      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
     //   setSupportActionBar(toolbar);
        //按钮实例化
        Button buttonC = (Button) findViewById(R.id.s_c_btn);//按钮C
        Button buttonDEL = (Button) findViewById(R.id.s_del_btn);//按钮退格
        Button buttonDivide = (Button) findViewById(R.id.s_divide_btn);//按钮除
        Button buttonMultiply = (Button) findViewById(R.id.s_multply_btn); //按钮乘
        Button buttonAdd = (Button) findViewById(R.id.s_add_btn);//按钮加
        Button buttonEqual = (Button) findViewById(R.id.s_equal_btn);//按钮等于
        Button buttonSub = (Button) findViewById(R.id.s_sub_btn);//按钮减
        Button buttonLeft= (Button) findViewById(R.id.s_left_btn);//左括号
        Button buttonRight= (Button) findViewById(R.id.s_right_btn);//右括号
        Button buttonSin= (Button) findViewById(R.id.s_sin_btn);//Sin
        Button buttonCos= (Button) findViewById(R.id.s_cos_btn);//Cos
        Button buttonTan= (Button) findViewById(R.id.s_tan_btn);//tan
        Button buttonSqrt= (Button) findViewById(R.id.s_sqrt_btn);//根号
        Button buttonSquare= (Button) findViewById(R.id.s_square_btn);//平方
        Button buttonLog= (Button) findViewById(R.id.s_lg_btn);//lg

        Button button0 = (Button) findViewById(R.id.s_0_btn);
        Button button1 = (Button) findViewById(R.id.s_1_btn);
        Button button2 = (Button) findViewById(R.id.s_2_btn);
        Button button3 = (Button) findViewById(R.id.s_3_btn);
        Button button4 = (Button) findViewById(R.id.s_4_btn);
        Button button5 = (Button) findViewById(R.id.s_5_btn);
        Button button6 = (Button) findViewById(R.id.s_6_btn);
        Button button7 = (Button) findViewById(R.id.s_7_btn);
        Button button8 = (Button) findViewById(R.id.s_8_btn);
        Button button9 = (Button) findViewById(R.id.s_9_btn);
        Button buttonPoint = (Button) findViewById(R.id.s_point_btn);//小数点
        textViewInput = (TextView) findViewById(R.id.science_input_tv);
        textViewResult=(TextView) findViewById(R.id.science_result_tv);
        buttonMenu = (Button) findViewById(R.id.s_menu_btn);
        //设置按钮点击事件
        buttonC.setOnClickListener(this);
        buttonDEL.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
        buttonSub.setOnClickListener(this);
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonPoint.setOnClickListener(this);
        buttonMenu.setOnClickListener(this);

        buttonLeft.setOnClickListener(this);
        buttonRight.setOnClickListener(this);
        buttonSin.setOnClickListener(this);
        buttonCos.setOnClickListener(this);
        buttonTan.setOnClickListener(this);
        buttonSqrt.setOnClickListener(this);
        buttonSquare.setOnClickListener(this);
        buttonLog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str = textViewInput.getText().toString();
        switch (v.getId()) {
            case R.id.s_0_btn:
            case R.id.s_1_btn:
            case R.id.s_2_btn:
            case R.id.s_3_btn:
            case R.id.s_4_btn:
            case R.id.s_5_btn:
            case R.id.s_6_btn:
            case R.id.s_7_btn:
            case R.id.s_8_btn:
            case R.id.s_9_btn:
                if(num_flag){
                    num_flag=false;
                    operator=false;
                    pointflag=false;
                    str="";
                    textViewInput.setText("");
                    textViewResult.setText("");
                    stringInput="";
                }
                operator=false; //设置运算符重复标识
                textViewInput.setText(str+((Button)v).getText());
                stringInput=textViewInput.getText().toString();
                break;
            case R.id.s_point_btn: //忽略小数点重复输入
                if(pointflag==false){//以前没有输入过小数点
                    pointflag=true;
                    textViewInput.setText(str+((Button)v).getText());
                    stringInput=textViewInput.getText().toString();
                }
                else
                    return;
                break;
            case R.id.s_add_btn:
            case R.id.s_sub_btn:
            case R.id.s_multply_btn:
            case R.id.s_divide_btn:
            case R.id.s_sqrt_btn:   //根号
            case R.id.s_square_btn: //平方
                if(num_flag){
                num_flag=false;
                operator=false;
                pointflag=false;
                str="";
                textViewInput.setText("");
                textViewResult.setText("");
                stringInput="";
            }
             //   if(!operator){ //运算符不重复
                    textViewInput.setText(str + ((Button)v).getText());
                    stringInput=textViewInput.getText().toString();
                //    operator=true;
         /*       }else{  //运算符重复
                    String string=str.substring(0,str.length()-1);
                    textViewInput.setText(string + ((Button)v).getText());
                    stringInput=textViewInput.getText().toString();
                }*/
                pointflag=false; //点击运算符，默认前一运算数已输入完成，更改小数点标志
                break;
            case R.id.s_left_btn:   //左括号
            case R.id.s_right_btn:  //右括号
                if(num_flag){
                    num_flag=false;
                    operator=false;
                    pointflag=false;
                    str="";
                    textViewInput.setText("");
                    textViewResult.setText("");
                    stringInput="";
                }
                textViewInput.setText(str + ((Button)v).getText());
                stringInput=textViewInput.getText().toString();
                pointflag=false; //点击运算符，默认前一运算数已输入完成，更改小数点标志
                break;
            case R.id.s_sin_btn:    //sin
                if(num_flag){
                    num_flag=false;
                    operator=false;
                    pointflag=false;
                    str="";
                    textViewInput.setText("");
                    textViewResult.setText("");
                    stringInput="";
                }
         //       if(!operator){ //运算符不重复
                    textViewInput.setText(str + ((Button)v).getText());
                    stringInput=str+"s";
                 //   operator=true;
         /*       }else{  //运算符重复
                    String string=str.substring(0,str.length()-3);
                    textViewInput.setText(string + ((Button)v).getText());
                    stringInput=string+"s";
                }*/
                pointflag=false; //点击运算符，默认前一运算数已输入完成，更改小数点标志
                break;
            case R.id.s_cos_btn:    //cos
                if(num_flag){
                    num_flag=false;
                    operator=false;
                    pointflag=false;
                    str="";
                    textViewInput.setText("");
                    textViewResult.setText("");
                    stringInput="";
                }
           //     if(!operator){ //运算符不重复
                    textViewInput.setText(str + ((Button)v).getText());
                    stringInput= str+"c";
            //        operator=true;
            /*    }else{  //运算符重复
                    String string=str.substring(0,str.length()-3);
                    textViewInput.setText(string + ((Button)v).getText());
                    stringInput=string+"c";
                }*/
                pointflag=false; //点击运算符，默认前一运算数已输入完成，更改小数点标志
                break;
            case R.id.s_tan_btn:    //tan
                if(num_flag){
                    num_flag=false;
                    operator=false;
                    pointflag=false;
                    str="";
                    textViewInput.setText("");
                    textViewResult.setText("");
                    stringInput="";
                }
       //         if(!operator){ //运算符不重复
                    textViewInput.setText(str + ((Button)v).getText());
                    stringInput=str+"t";
            /*        operator=true;
                }else{  //运算符重复
                    String string=str.substring(0,str.length()-3);
                    textViewInput.setText(string + ((Button)v).getText());
                    stringInput=string+"t";
                }*/
                pointflag=false; //点击运算符，默认前一运算数已输入完成，更改小数点标志
                break;
            case R.id.s_lg_btn: //lg
                if(num_flag){
                    num_flag=false;
                    operator=false;
                    pointflag=false;
                    str="";
                    textViewInput.setText("");
                    textViewResult.setText("");
                    stringInput="";
                }
          //      if(!operator){ //运算符不重复
                    textViewInput.setText(str + ((Button)v).getText());
                    stringInput=str+"l";
           /*           operator=true;
                }else{  //运算符重复
                    String string=str.substring(0,str.length()-2);
                    textViewInput.setText(string + ((Button)v).getText());
                    stringInput=string+"l";
                }*/
                pointflag=false; //点击运算符，默认前一运算数已输入完成，更改小数点标志
                break;
            case R.id.s_del_btn:
                if(num_flag){
                    num_flag=false;
                    operator=false;
                    pointflag=false;
                    str="";
                    textViewInput.setText("");
                    textViewResult.setText("");
                    stringInput="";
                } else if(str!=null&&str.length()!=0){
                    str=str.substring(0, str.length()-1);
                    textViewInput.setText(str);
                    stringInput=str;
                }
                break;
            case R.id.s_c_btn:
                num_flag=false;
                operator=false;
                pointflag=false;
                str = "";
                stringInput="";
                textViewInput.setText("");
                textViewResult.setText("");
                break;
            case R.id.s_equal_btn:
                calc();
                break;
            case R.id.s_menu_btn:
                Intent intent=new Intent(ScienceCalculateActivity.this,MenuActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
    public void calc(){
        if(num_flag){//防止点击完等号后再次点击
            num_flag=false;
            return;
        }
        num_flag=true;
        String[] midSplit=goToSplit(stringInput);
        Double ans=0.;
        try {
            List<String> after = midToAfter(midSplit);
            ans = afterValue(after);
            textViewResult.setText(ans.toString());
        } catch (Exception e) {
            Toast.makeText(this, "输入不合法，请检查", Toast.LENGTH_LONG).show();
        }

    }
    public String[] goToSplit(String s){
        int pre=-1;//上一个符号的位置，当两个符号一起时：）* ，应分成：*# 否则分成：#*#
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!='.'&&(s.charAt(i)<'0'||s.charAt(i)>'9')){   //是操作符
                if(i-1==pre){ //上一个也是操作符号
                    sb.append(s.charAt(i)+"#");
                }
                else sb.append("#"+s.charAt(i)+"#");
                pre=i;//更新pre
            }else{
                sb.append(s.charAt(i));
            }
        }
        String[] split = sb.toString().split("#");
        return split;
    }

    /**
     * 中缀转后缀：
     *      从左到右扫描表达式
     *      a:若是数字直接输出
     *      b:若是（直接入栈
     *      c:若是）将栈中操作符依次退栈输出，直到遇到（为止，将（出栈丢弃
     *      d其他：将当前操作符的优先级小于等于栈顶操作符优先级，则将栈顶操作出栈输出，直到不小于或栈空为止；将当前操作符入栈
     */
    public static List<String> midToAfter(String [] mid) throws Exception{
        LinkedList<String> after=new LinkedList<String>();

        for(String ss:mid){ //增强for循环遍历mid字符串数组
            if(ss.equals("=")) continue;
            if(priorityOption.get(ss)==null){//说明是操作数
                after.add(ss);
            }else if(ss.equals("(")){
                opStack.push(ss);
            }else if(ss.equals(")")){
                while(!opStack.peek().equals("(")){//不是“(”,则输出，
                    after.add(opStack.pop());
                }
                opStack.pop();//去除（
            }else {
                while(!opStack.isEmpty()&&priorityOption.get(ss)<=priorityOption.get(opStack.peek())){
                    after.add(opStack.pop());
                }
                opStack.push(ss);
            }
        }
        while(!opStack.isEmpty()) after.add(opStack.pop());
        return after;
    }
    /**
     * 后缀求值：从左到右扫描后缀表达式
     *      a:若为数字，直接入栈
     *      b:若为二元操作符，从栈中出栈两个数字，按操作符计算，再把结果入栈，注意两个操作数运算顺序
     *      c:若为一元操作符，从栈中出栈一个数字，按操作符计算，再把结果入栈
     *      结果：最后栈中只有一个数字，出栈即为答案
     */
    public static double afterValue(List<String> after) throws Exception{
        LinkedList<Double> number=new LinkedList<Double>();
        for(String ss:after){
            if(priorityOption.get(ss)!=null){//是操作符,取出两个数，按操作符计算后入数字栈 //priority(ss)!=-1
                if(ss.equals("+")) {
                    Double y=number.pop();//第二个数
                    Double x=number.pop();
                    number.push(x+y);
                }
                else if(ss.equals("-")){
                    Double y=number.pop();//第二个数
                    Double x=number.pop();
                    number.push(x-y);
                }
                else if(ss.equals("×")) {
                    Double y=number.pop();//第二个数
                    Double x=number.pop();
                    number.push(x*y);
                }
                else if(ss.equals("÷")){
                    Double y=number.pop();//第二个数
                    Double x=number.pop();
                    number.push(x/y);
                }
                else if(ss.equals("√")){
                    Double x=number.pop();
                    number.push(Math.sqrt(x));
                }
                else if(ss.equals("^")){
                    Double y=number.pop();//第二个数
                    Double x=number.pop();
                    number.push(Math.pow(x,y));
                }
                else if(ss.equals("l")){
                    Double x=number.pop();
                    number.push(Math.log10(x));
                }
                else if(ss.equals("s")){ //sin
                    Double x=number.pop();
                    number.push((Math.sin(Math.toRadians(x))));
                }
                else if(ss.equals("c")){ //cos
                    Double x=number.pop();
                    number.push((Math.cos(Math.toRadians(x))));
                }
                else if(ss.equals("t")){ //tan
                    Double x=number.pop();
                    number.push((Math.tan(Math.toRadians(x))));
                }
            }else if(number(ss)){ //是数字
                number.push(Double.valueOf(ss));
            }
        }
        return number.pop();
    }

    public static boolean number(String s){ //判断是数字
        int count=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)>='0'&& s.charAt(i)<='9'||s.charAt(i) =='.'){
                count++;
            }
        }
        if(count==s.length())
            return true;
        else
            return false;
    }
}
