package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 作业1：
 * Logcat在屏幕旋转的时候 #onStop() #onDestroy()会展示出来
 * 但我们的 mLifecycleDisplay 由于生命周期的原因(Tips:执行#onStop()之后，UI界面我们是看不到的)并没有展示
 * 在原有@see Exercises1 基础上如何补全它，让其跟logcat的展示一样?
 * <p>
 * Tips：思考用比Activity的生命周期要长的来存储？  （比如：application、static变量）
 */
public class Exercises1 extends AppCompatActivity {

    private TextView lifeCycleDisplay;

    private static final String LIFECYCLE_CALLBACKS = "lifecycle callbacks:";
    private static final String ON_CREATE = "OnCreate";
    private static final String ON_START = "onStart";
    private static final String ON_RESUME = "onResume";
    private static final String ON_PAUSE = "onPause";
    private static final String ON_STOP = "onStop";
    private static final String ON_RESTART = "onRestart";
    private static final String ON_DESTROY = "onDestroy";
    private static final String ON_SAVE_INSTANCE_STATE = "onSaveInstanceState";

    private static final String LIFECYCLE_CALLBACKS_TEXT_KEY = "lifecycle callbacks";


    private static final String TAG = "cairenjie";//方便在日志中查钊追加的记录

    //设置一个静态对象保存saveInstanceState保存不到的日志信息
    private static ArrayList<String> lifeCycleEvents = new ArrayList<>();


    @Override
    protected void onStart() {
        super.onStart();
        LogAndAppend(ON_START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogAndAppend(ON_RESUME);
        lifeCycleEvents.clear();//每次重新进入的时候会清空之前的记录，防止记录累加
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogAndAppend(ON_PAUSE);
    }


    @Override
    protected void onStop() {
        super.onStop();
        LogAndAppend(ON_STOP);
        lifeCycleEvents.add(ON_STOP);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogAndAppend(ON_RESTART);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogAndAppend(ON_DESTROY);
        lifeCycleEvents.add(ON_DESTROY);
    }

    //封装日志打印和TextView追加显示操作
    public void LogAndAppend(String lifecycleEvent) {
        Log.d(TAG, "LifeCycle Event:" + lifecycleEvent);
        lifeCycleDisplay.append(lifecycleEvent + "\n");
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        lifeCycleDisplay = findViewById(R.id.tv_loglifecycle);
        final Button button = findViewById(R.id.Clearbutton);
        if (savedInstanceState != null) {
            //取出保存在bundle中的数据
            if (savedInstanceState.containsKey(LIFECYCLE_CALLBACKS_TEXT_KEY)) {
                lifeCycleDisplay.setText(savedInstanceState.getString(LIFECYCLE_CALLBACKS_TEXT_KEY));
                for (String event : lifeCycleEvents) {
                    lifeCycleDisplay.append(event + "\n");
                }
            }
        }
        LogAndAppend(ON_CREATE);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                View rootview = findViewById(R.id.exercise2);
                lifeCycleDisplay.setText("");//清空当前所有的log
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogAndAppend(ON_SAVE_INSTANCE_STATE);
        String lifeCycleEventsText = lifeCycleDisplay.getText().toString();
        //保存到Bundle，调用onCreate的取出
        outState.putString(LIFECYCLE_CALLBACKS_TEXT_KEY, lifeCycleEventsText);
    }
}
