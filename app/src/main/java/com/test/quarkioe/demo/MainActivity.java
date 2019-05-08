package com.test.quarkioe.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.quarkioe.ncore.sdk.client.QuarkIoeManage;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    RadioButton rd1,rd2,rd3,rd4;
    RadioGroup rdg;
    LinearLayout mylayout;
    EditText myurl,myname,mypwd;

    private static final String url1 = "http://192.168.8.143";
    private static final String url2 = "http://47.92.0.73:8000";
    private static final String url3 = "http://localhost:52001/outer";
    private static final String url4 = "自定义url";

    private static final String name = "management/admin";
    private static final String pwd = "admin123";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();

    }

    private void initview() {
        tv = findViewById(R.id.tv);
        rd1 = findViewById(R.id.rd1);
        rd2 = findViewById(R.id.rd2);
        rd3 = findViewById(R.id.rd3);
        rd4 = findViewById(R.id.rd4);
        rdg = findViewById(R.id.rdg);
        mylayout = findViewById(R.id.mylayout);
        myurl = findViewById(R.id.myurl);
        myname = findViewById(R.id.myname);
        mypwd = findViewById(R.id.mypwd);

        rd1.setText(url1);
        rd2.setText(url2);
        rd3.setText(url3);
        rd4.setText(url4);

        myurl.setText(url1);
        myname.setText(name);
        mypwd.setText(pwd);

        rd4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mylayout.setVisibility(View.VISIBLE);
                }else{
                    mylayout.setVisibility(View.GONE);
                }
            }
        });

        rd1.setChecked(true);
    }

    public void Test(View view) {

        int id = rdg.getCheckedRadioButtonId();
        switch (id){
            case R.id.rd1:
                client( url1 ,name ,pwd);
                break;
            case R.id.rd2:
                client( url2 ,name ,pwd);
                break;
            case R.id.rd3:
                client( url3 ,name ,pwd);
                break;
            case R.id.rd4:
                client( myurl.getText().toString().trim() ,
                        myname.getText().toString().trim() ,
                        mypwd.getText().toString().trim() );
                break;
        }

    }

    private void client(String url , String name , String pwd){

        appenStr(url,name,pwd);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    QuarkIoeManage.Instance(url,name,pwd);
                    QuarkIoeManage.Start();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("TAG", e.toString());
                    tv.setText(e.toString());
                }
            }
        }).start();

    }

    private void appenStr(String url, String name, String pwd) {

        tv.setText("url:" + url + "\nname:" + name + "\npwd:" + pwd);
    }

    private void client(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    QuarkIoeManage.Instance();
                    QuarkIoeManage.Start();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("TAG", e.toString());
                }
            }
        }).start();
    }

}
