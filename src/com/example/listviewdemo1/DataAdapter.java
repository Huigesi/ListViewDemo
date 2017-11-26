package com.example.listviewdemo1;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DataAdapter extends ArrayAdapter<ItemBean>{
	private List<ItemBean> mList;
	private Context mContext;
	private LayoutInflater mInflater;
	public DataAdapter(Context context, List<ItemBean> list) {
		super(context, 0, list);
		this.mContext=context;
		this.mList=list;
		mInflater=LayoutInflater.from(context);
		//mInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}



	@Override
	public ItemBean getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder=null;
		//易错点总结1：复制粘贴结果名字没改
		ItemBean bean=mList.get(position);
		if(convertView==null){
			convertView=mInflater.inflate(R.layout.item, parent, false);
			holder=new ViewHolder();
			holder.textView1=(TextView)convertView.findViewById(R.id.textview1);
			holder.textView2=(TextView)convertView.findViewById(R.id.textview2);
			holder.textView3=(TextView)convertView.findViewById(R.id.textview3);
			holder.textView4=(TextView)convertView.findViewById(R.id.textview4);
			convertView.setTag(holder);
			
		}else{
			holder=(ViewHolder)convertView.getTag();
			
		}
		if(bean!=null){
		holder.textView1.setText(String.valueOf(bean.getmId()));
		holder.textView2.setText(String.valueOf(bean.getmRed()));
		holder.textView3.setText(String.valueOf(bean.getmGreen()));
		holder.textView4.setText(String.valueOf(bean.getmYellow()));
		
		}
		return convertView;
	}
	private class ViewHolder{
		TextView textView1;
		TextView textView2;
		TextView textView3;
		TextView textView4;
		
	}
	

}
