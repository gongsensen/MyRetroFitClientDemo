package com.example.bawie.nsg_porjects;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.bawie.nsg_porjects.base.LoginBean;
import com.example.bawie.nsg_porjects.client.Api;
import com.example.bawie.nsg_porjects.client.BaseObserver;
import com.example.bawie.nsg_porjects.client.ExceptionHandle;
import com.example.bawie.nsg_porjects.client.RetrofitClient;
import com.example.bawie.nsg_porjects.home.bean.HomeBean;
import com.example.bawie.nsg_porjects.utils.NetUtils;
import com.example.bawie.nsg_porjects.utils.Toasts;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String  TAG="MainActivity";
    private Button mbutton;
    String mpath="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbutton = (Button) findViewById(R.id.mbu1);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasts.controlShow(false);
                Toasts.show(MainActivity.this,"1508A大神养成记",1000);
               // NetUtils.openWirelessSettings(MainActivity.this);
                Log.d(TAG, "onClick:=== "+NetUtils.getActiveNetworkInfo(MainActivity.this));
                Log.d(TAG, "onClick:=== "+NetUtils.getPsdnIp(MainActivity.this));
               // getDate("v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=0");

                psotdata("ws","ee");

            }
        });





     //   http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=0
         // http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=0
    }
    private void getDate(String url){
        RetrofitClient.getInstance(MainActivity.this).createBaseApi().gets(url,new BaseObserver<HomeBean>(MainActivity.this) {
            @Override
            public void onNext(HomeBean homeBean) {
                Toasts.showShort(MainActivity.this,homeBean.getSong_list().get(0).getTitle());
                Log.d("MainActivity","xx==="+homeBean.getSong_list().get(0).getTitle());


            }

            @Override
            protected void hideDialog() {
              //  CustomDialog.STYLE_HORIZONTAL;

            }

            @Override
            protected void showDialog() {
             // CustomDialog.show(MainActivity.this,"加载中","正在加载。。。");
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {

            }
        });


    }


   public void psotdata(String name,String pswd){
       Map<String,String> map=new HashMap<>();
       map.put("mobile",name);
       map.put("password",pswd);
       RetrofitClient.getInstance(MainActivity.this).createBaseApi().post(Api.LOGIN_URL, map, new BaseObserver<LoginBean>(MainActivity.this) {
           @Override
           public void onNext(LoginBean loginBean) {
               Log.d(TAG, "onNext: ===="+loginBean.getCode());
           }

           @Override
           protected void hideDialog() {

           }

           @Override
           protected void showDialog() {

           }

           @Override
           public void onError(ExceptionHandle.ResponeThrowable e) {

           }
       });

   }
}
