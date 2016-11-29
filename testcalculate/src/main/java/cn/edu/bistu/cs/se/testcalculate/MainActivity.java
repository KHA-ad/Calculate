package cn.edu.bistu.cs.se.testcalculate;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MyTag";
    TextView textViewInput; //显示当前输入的数字
    TextView textViewResult; //显示表达式及结果
    Button buttonMenu;
    private static boolean pointflag = false; //小数点重复表示
    boolean num_flag;//运算出结果后，再次点击数字按钮屏幕清空标识
    private static boolean operator = false;//运算符重复标识

    String mid=null;
    static LinkedList<String> opStack=new LinkedList<String>();
    //优先级映射
    static Map<String, Integer> priority=new HashMap<String, Integer>(){
        {
            put("(", 0);
            put(")", 0);
            put("+", 1);
            put("-", 1);
            put("×", 2);
            put("÷",2);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple);

        //按钮实例化
        Button buttonC = (Button) findViewById(R.id.c_btn);//按钮C
        Button buttonDEL = (Button) findViewById(R.id.del_btn);//按钮退格
        Button buttonDivide = (Button) findViewById(R.id.divide_btn);//按钮除
        Button buttonMultiply = (Button) findViewById(R.id.multiply_btn); //按钮乘
        Button buttonAdd = (Button) findViewById(R.id.add_btn);//按钮加
        Button buttonEqual = (Button) findViewById(R.id.equal_btn);//按钮等于
        Button buttonSub = (Button) findViewById(R.id.sub_btn);//按钮减
        Button button0 = (Button) findViewById(R.id.zero_btn);
        Button button1 = (Button) findViewById(R.id.one_btn);
        Button button2 = (Button) findViewById(R.id.two_btn);
        Button button3 = (Button) findViewById(R.id.three_btn);
        Button button4 = (Button) findViewById(R.id.four_btn);
        Button button5 = (Button) findViewById(R.id.five_btn);
        Button button6 = (Button) findViewById(R.id.six_btn);
        Button button7 = (Button) findViewById(R.id.seven_btn);
        Button button8 = (Button) findViewById(R.id.eight_btn);
        Button button9 = (Button) findViewById(R.id.nine_btn);
        Button buttonPoint = (Button) findViewById(R.id.point_btn);
        textViewInput = (TextView) findViewById(R.id.show_input_tv);
        textViewResult = (TextView) findViewById(R.id.show_result_tv);
        buttonMenu = (Button) findViewById(R.id.menu_btn);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        String str = textViewInput.getText().toString();
        switch (v.getId()) {
            case R.id.zero_btn:
            case R.id.one_btn:
            case R.id.two_btn:
            case R.id.three_btn:
            case R.id.four_btn:
            case R.id.five_btn:
            case R.id.six_btn:
            case R.id.seven_btn:
            case R.id.eight_btn:
            case R.id.nine_btn:
                if(num_flag){
                    num_flag=false;
                    operator=false;
                    pointflag=false;
                    str="";
                    textViewInput.setText("");
                    //         textViewResult.setText("");
                }
                operator=false; //设置运算符重复标识
                textViewInput.setText(str+((Button)v).getText());
                break;
            case R.id.point_btn: //忽略小数点重复输入
                if(pointflag==false){//以前没有输入过小数点
                    pointflag=true;
                    textViewInput.setText(str+((Button)v).getText());
                }
                else
                    return;
                break;
            case R.id.add_btn:
            case R.id.sub_btn:
            case R.id.multiply_btn:
            case R.id.divide_btn:
                if(num_flag){
                    num_flag=false;
                    operator=false;
                    pointflag=false;
                    str="";
                    textViewInput.setText("");
                    textViewResult.setText("");
                }
                if(!operator){ //运算符不重复
                    textViewInput.setText(str + ((Button)v).getText());
                    operator=true;
                }else{  //运算符重复
                    String string=str.substring(0,str.length()-1);
                    textViewInput.setText(string + ((Button)v).getText());
                }
                pointflag=false; //点击运算符，默认前一运算数已输入完成，更改小数点标志
               break;
            case R.id.del_btn:
                if(num_flag){
                    num_flag=false;
                    operator=false;
                    pointflag=false;
                    str="";
                    textViewInput.setText("");
                    textViewResult.setText("");
                } else if(str!=null&&str.length()!=0){
                    str=str.substring(0, str.length()-1);
                    textViewInput.setText(str);
                }
                break;
            case R.id.c_btn:
                num_flag=false;
                operator=false;
                pointflag=false;
                str = "";
                textViewInput.setText("");
                textViewResult.setText("");
                break;
            case R.id.equal_btn:
                calc();
                break;
            case R.id.menu_btn:
                Intent intent=new Intent(MainActivity.this,MenuActivity.class);
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
        mid = (String) textViewInput.getText();
        String[] midSplit=goToSplit(mid);
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
        int pre=-1;//上一个符号的位置，当两个符号一起时：）*   应分成：*# 否则分成：#*#
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!='.'&&(s.charAt(i)<'0'||s.charAt(i)>'9')){
                if(i-1==pre){ //上一个也是操作符号
                    sb.append(s.charAt(i)+"#");
                } else sb.append("#"+s.charAt(i)+"#");
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
     *      c:若是）将栈中操作符依次退栈输出，直到遇到（为止，将（出栈丢弃，输出下一个元素
     *      d其他：将当前操作符的优先级小于等于栈顶操作符优先级，则将栈顶操作出栈输出，直到不小于或栈空为止；将当前操作符入栈
     */
    public static List<String> midToAfter(String [] mid) throws Exception{
        LinkedList<String> after=new LinkedList<String>();
        int index=0;
        for(String ss:mid){ //增强for循环遍历mid字符串数组
            if(ss.equals("=")) continue;
            if(priority.get(ss)==null){//说明是操作数
                after.add(ss);
            }else if(ss.equals("(")){
                opStack.push(ss);
            }else if(ss.equals(")")){
                while(!opStack.peek().equals("(")){//不是“(”,则输出，
                    after.add(opStack.pop());
                }
                opStack.pop();//去除（
            }else {
                while(!opStack.isEmpty()&&priority.get(ss)<=priority.get(opStack.peek())){
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
     *      b:若为操作符，从栈中出栈两个数字，按操作符计算，再把结果入栈，注意两个操作数运算顺序
     *      结果：最后栈中只有一个数字，出栈即为答案
     */
    public static double afterValue(List<String> after) throws Exception{
        LinkedList<Double> number=new LinkedList<Double>();
        for(String ss:after){
            if(priority.get(ss)!=null){//是操作符,取出两个数，按操作符计算后入数字栈
                Double y=number.pop();//第二个数
                Double x=number.pop();
                if(ss.equals("+")) number.push(x+y);
                else if(ss.equals("-")) number.push(x-y);
                else if(ss.equals("×")) number.push(x*y);
                else if(ss.equals("÷")) number.push(x/y);
            }else{
                number.push(Double.valueOf(ss));
            }
        }
        return number.pop();
    }
}

