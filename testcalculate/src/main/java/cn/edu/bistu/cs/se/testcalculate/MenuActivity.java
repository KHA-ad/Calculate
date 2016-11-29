package cn.edu.bistu.cs.se.testcalculate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuActivity extends AppCompatActivity {
    private Integer[] images = {
            //九宫格图片的设置
            R.mipmap.image_calculate1,
            R.mipmap.image_sciencecalculate1,
            R.mipmap.image_length1,
            R.mipmap.image_volume1,
            R.mipmap.image_hex
    };
    private String[] texts = {
            //九宫格图片下方文字的设置
           "计算器",
            "科学计算器",
            "长度转换",
            "体积转换",
            "进制转换"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
   //     Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
   //     setSupportActionBar(toolbar);
        GridView gridview = (GridView) findViewById(R.id.GridView);
        ArrayList<HashMap<String, Object>> meumList = new ArrayList<HashMap<String, Object>>();

        //初始化数据源
        for(int i = 0;i < 5;i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage",images[i]);
            map.put("ItemText", texts[i]);
            meumList.add(map);
        }
        SimpleAdapter saItem = new SimpleAdapter(this,
                meumList, //数据源
                R.layout.menu_item_layout, //xml实现
                new String[]{"ItemImage","ItemText"}, //对应map的Key
                new int[]{R.id.ItemImage,R.id.ItemText});  //对应R的Id
        //添加Item到网格中
        gridview.setAdapter(saItem);
        //添加点击事件
        gridview.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3)
                    {
                        Intent intent;
                        switch (arg2){
                            case 0://计算器
                                intent=new Intent(MenuActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                                break;
                            case 1://科学计算器
                                Toast.makeText(MenuActivity.this, "科学计算器", Toast.LENGTH_SHORT).show();
                                intent=new Intent(MenuActivity.this,ScienceCalculateActivity.class);
                                startActivity(intent);
                                finish();
                                break;
                            case 2://长度转换
                                intent=new Intent(MenuActivity.this,LengthConversion.class);
                                startActivity(intent);
                                finish();
                                break;
                            case 3://体积转换
                                Toast.makeText(MenuActivity.this, "体积转换", Toast.LENGTH_SHORT).show();
                                intent=new Intent(MenuActivity.this,VolumeActivity.class);
                                startActivity(intent);
                                finish();
                                break;
                            case 4://进制转换
                                Toast.makeText(MenuActivity.this, "进制转换", Toast.LENGTH_SHORT).show();
                                intent=new Intent(MenuActivity.this,DecimalConversionActivity.class);
                                startActivity(intent);
                                finish();
                                break;
                        }
                    }
                }
        );

    }

}
