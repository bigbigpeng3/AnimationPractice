package com.pengzhangdemo.com.animationpractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pengzhangdemo.com.animationpractice.custombezier.SecondOrderActivity;
import com.pengzhangdemo.com.animationpractice.custombezier.ThreeOrderActivity;
import com.pengzhangdemo.com.animationpractice.custombezier.ThreeOrderBezier;

public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }


    /**
     * 跳转到 CustomLunchView,
     * startTriangle,
     * startLunchView,
     * startMovePath,
     * startSecondOrder
     * startThreeOrder
     * @param view
     */
    public void startLunchView(View view){
        startAct(MainActivity.class);
    }

    public void startTriangle(View view){
        startAct(TriangleActivity.class);
    }

    public void startMovePath(View view){
        startAct(MovePathActivity.class);
    }

    public void startSecondOrder(View view){
        startAct(SecondOrderActivity.class);
    }

    public void startThreeOrder(View view){
        startAct(ThreeOrderActivity.class);
    }








    public void startAct(Class clzz){
        Intent intent = new Intent(this,clzz);
        startActivity(intent);
    }

}
