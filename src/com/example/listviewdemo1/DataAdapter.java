package com.example.listviewdemo1;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class DataAdapter extends ArrayAdapter<ItemBean>{
	private List<ItemBean> mList;
	private Context mContext;
	private LayoutInflater mInflater;
	private ViewHolder holder;
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
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		 holder=null;
		//易错点总结1：复制粘贴结果名字没改
		ItemBean bean=mList.get(position);
		if(convertView==null){
			convertView=mInflater.inflate(R.layout.item, parent, false);
			holder=new ViewHolder();
			holder.textView1=(TextView)convertView.findViewById(R.id.textview1);
			holder.textView2=(TextView)convertView.findViewById(R.id.textview2);
			holder.textView3=(TextView)convertView.findViewById(R.id.textview3);
			holder.textView4=(TextView)convertView.findViewById(R.id.textview4);
			holder.c1=(CheckBox)convertView.findViewById(R.id.checkbox);
			holder.b1=(Button)convertView.findViewById(R.id.button);
			convertView.setTag(holder);
			
		}else{
			holder=(ViewHolder)convertView.getTag();
			
		}
		if(bean!=null){
		holder.textView1.setText(String.valueOf(bean.getmId()));
		holder.textView2.setText(String.valueOf(bean.getmRed()));
		holder.textView3.setText(String.valueOf(bean.getmGreen()));
		holder.textView4.setText(String.valueOf(bean.getmYellow()));
		holder.b1.setText("按钮"+position);
		
		holder.b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				boolean flag=holder.c1.isChecked();
				// TODO Auto-generated method stub
				//integer b = (integer)view.getTag(R.id.btn);
				settingDialog();
				Toast.makeText(mContext, "选了第"+position+"个", Toast.LENGTH_SHORT).show();
				//int item=(Integer) holder.b1.getTag();
				ItemBean bean=mList.get(position);
				bean.getmId();
				Toast.makeText(mContext, "选了第"+bean.getmId()+"号", Toast.LENGTH_SHORT).show();
				Toast.makeText(mContext, flag+"", Toast.LENGTH_SHORT).show();
			}
		});
		
		}
		return convertView;
	}
	private class ViewHolder{
		TextView textView1;
		TextView textView2;
		TextView textView3;
		TextView textView4;
		CheckBox c1;
		Button b1;
		
	}
	public void settingDialog() {
		final SetDialog mSetDialog=new SetDialog(mContext);
		mSetDialog.setCallBack(new SetDialog.MyCallBack() {
			
			@Override
			public void ok() {
				// TODO Auto-generated method stub
				mSetDialog.dismiss();
			}
			
			@Override
			public void cancel() {
				// TODO Auto-generated method stub
				mSetDialog.dismiss();
			}
		});
		mSetDialog.setTitle("红绿灯设置");
		mSetDialog.setCancelable(true);
		mSetDialog.show();
	
	}
	

}
