package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import android.view.ViewGroup;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise2);

        final Button button = findViewById(R.id.button);
        final TextView text = findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                View rootview = findViewById(R.id.exercise2);
                text.setText(String.valueOf(getAllChildViewCount(rootview) ));
            }
        });
    }

    public int getAllChildViewCount(View view) {
        //todo 补全你的代码
        //View和ViewGroup的组合实际上是一个树形结构，可以用递归进行深度遍历，统计出结点为View的个数
        //非递归方法可以采用广度优先遍历，即用队列来辅助实现
        int count = 0;
        if (view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) view;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View childView = vg.getChildAt(i);
                count += getAllChildViewCount(childView);
            }
        } else {
            count = 1;
        }
        return count;
    }
}
