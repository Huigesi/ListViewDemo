package com.example.listviewdemo1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	private static final int UP_DATE=0x01;
	private static final int SHOW_DIALOG=0x02;
	private ProgressDialog mDialog;
	private DataAdapter mAdapter;
	private Handler mHandler;
	private List<ItemBean> mList;
	private Button mButton;
	private ListView mListView;
	private Thread mThread;
	private String mString;
	private String mPath="http://192.168.1.243:8080/transportservice/type/jason/action/GetTrafficLightConfigAction.do";
	private int time=0;	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDialog();
        handMessage();
        initData();
        initView();
        setListener();
        setListView();
        setThread();
        mThread.start();

    }


	private void setThread() {
		mThread=new Thread(){
			@Override
			public void run() {
				int count=1;
				while(count<=5){
				mHandler.sendEmptyMessage(SHOW_DIALOG);
				mString=HttpClients.sendMessage(mPath,"{\"TrafficLightId\":" +count+ "}");
				
				Log.i("mString",mString);
					try {
						parseJson(count);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					mHandler.sendEmptyMessage(UP_DATE);
				count++;
				
				}
				
				super.run();
			}

		
		};
	}
	private void parseJson(int count) throws Exception{
		
			JSONObject jsonObject=new JSONObject(mString);
			String jString=jsonObject.getString("serverinfo");
			JSONObject mJsonObject=new JSONObject(jString);
			Log.i("jString",jString);
			ItemBean bean=new ItemBean();
			bean.setmId(count);
			bean.setmRed(mJsonObject.getInt("RedTime"));
			bean.setmGreen(mJsonObject.getInt("GreenTime"));
			bean.setmYellow(mJsonObject.getInt("YellowTime"));
			mList.add(bean);
			
		
	}


	private void setListView() {
		mAdapter=new DataAdapter(MainActivity.this, mList);
		mListView.setAdapter(mAdapter);
	}


	private void setListener() {
		mButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				time++;
				if(time%2==0){
					sort1();
				}else{
					sort2();
				}
				mHandler.sendEmptyMessage(UP_DATE);
				
			}
		});
	}
	private void sort1(){
		Collections.sort(mList,new Comparator<ItemBean>() {

			@Override
			public int compare(ItemBean b1, ItemBean b2) {
				if (b1.getmId()<b2.getmId()) {
					return -1;
				}else {
					return 0;
				}
				
			}
		});
	}
	private void sort2(){
		Collections.sort(mList,new Comparator<ItemBean>() {

			@Override
			public int compare(ItemBean b1, ItemBean b2) {
				if (b1.getmId()>b2.getmId()) {
					return -1;
				}else {
					return 0;
				}
				
			}
		});
	}


	private void initView() {
		mButton=(Button)findViewById(R.id.button);
		mListView =(ListView)findViewById(R.id.listview);
	}


	private void initData() {
		mList=new ArrayList<ItemBean>();
		
	}


	private void handMessage() {
		mHandler=new Handler(new Handler.Callback() {
			
			@Override
			public boolean handleMessage(Message msg) {
				mDialog.dismiss();
				switch (msg.what) {
				case UP_DATE:
					mAdapter.notifyDataSetChanged();
					break;
				case SHOW_DIALOG:
					mDialog.show();
				
					break;
				}
				return false;
			}
		});
		
	}

	private void getDialog() {
		mDialog=new ProgressDialog(this);
		mDialog.setMessage("ÕýÔÚ¼ÓÔØ");
	}
    


  
}
