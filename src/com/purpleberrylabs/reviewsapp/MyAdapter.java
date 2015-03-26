package com.purpleberrylabs.reviewsapp;

import java.util.List;
import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter
{

	private Context context;

	private List<Review> reviews;

	private LayoutInflater inflater;

	View convertView1;

	ViewHolder holder;

	private Review item;

	int length;

	int a = 10;

	int clickedPosition;

	private ViewTreeObserver mViewTreeObserver;

	TextView arr[] = new TextView[100];

	View layouts[] = new View[100];

	int index;
	
	private boolean flag=true;

	int k = 200, j = 100;

	public MyAdapter(Context ctx, List<Review> reviews)
	{
		this.context = ctx;
		this.reviews = reviews;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount()
	{

		return reviews.size();
	}

	@Override
	public Review getItem(int position)
	{

		return reviews.get(position);
	}

	@Override
	public long getItemId(int position)
	{

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{

		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();
			holder.review = (TextView) convertView.findViewById(R.id.text);
			convertView.setTag(holder);
			layouts[position] = convertView;

		}
		
		holder = (ViewHolder) convertView.getTag();
		item = reviews.get(position);
		length = position;
		Log.e("inside getView",""+holder);
		Log.e("item",""+item.getReview());
		if(flag)
		{	
		holder.review.setText(item.getReview());
		}
		arr[position] = holder.review;
		mViewTreeObserver = arr[position].getViewTreeObserver();
		mViewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener);
        return convertView;
	}

	OnGlobalLayoutListener onGlobalLayoutListener = new OnGlobalLayoutListener()
	{

		@SuppressWarnings("deprecation")
		@Override
		public void onGlobalLayout()
		{
            Log.e("inside globallayout","");
			ViewTreeObserver obs = holder.review.getViewTreeObserver();
			obs.removeGlobalOnLayoutListener(this);
			for (; index <= length; index++)
			{
				Layout l = arr[index].getLayout();
				if (l != null)
				{
					int lines = l.getLineCount();
					Log.d("lines", "" + lines);
					if (lines > 3)
					{

						System.out.println("at index" + index + "is arr[index]" + arr[index]);
						int lineEndIndex = arr[index].getLayout().getLineEnd(2);
						String text = arr[index].getText().subSequence(0, lineEndIndex - 3) + "...";
						holder = (ViewHolder) layouts[index].getTag();
						holder.review.setText(text);
						holder.mReadMoreButton = new Button(context);
						RelativeLayout ll = (RelativeLayout) layouts[index].findViewById(R.id.layout);
						LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
						lp.addRule(RelativeLayout.BELOW, arr[index].getId());
						System.out.println("holder.review.getId()" + arr[index].getId());
						ll.addView(holder.mReadMoreButton, lp);
						System.out.println("ll " + ll + "lp " + lp);
						holder.mReadMoreButton.setText("Readmore");
						holder.mReadMoreButton.setId(j++);
						holder.mReadMoreButton.setTag(index);
						holder.mReadMoreButton.setOnClickListener(onReadMoreClickListener);
                        
					}
				}

			}
		}
	};

	OnClickListener onReadMoreClickListener = new OnClickListener()
	{

		@Override
		public void onClick(View v)
		{

			
			v.setVisibility(View.GONE);
			clickedPosition = (Integer) v.getTag();
			item = reviews.get(clickedPosition);
			//Log.d("item",""+item);
			Log.d("layout[clickedPosition]",""+layouts[clickedPosition]);
			holder = (ViewHolder) layouts[clickedPosition].getTag();
			holder.review.setText(item.getReview());
			holder.mReadLessButton = new Button(context);
			RelativeLayout ll = (RelativeLayout) layouts[clickedPosition].findViewById(R.id.layout);
			LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.BELOW, v.getId());
			ll.addView(holder.mReadLessButton, lp);
			holder.mReadLessButton.setText("Readless");
			holder.mReadLessButton.setId(k++);
			holder.mReadLessButton.setTag(clickedPosition);
			holder.mReadMoreButton.setVisibility(View.GONE);
			holder.mReadLessButton.setOnClickListener(onReadLessClickListener);
			
			

		}
	};

	OnClickListener onReadLessClickListener = new OnClickListener()
	{

		

		@Override
		public void onClick(final View v)
		{
			
			v.setVisibility(View.GONE);
			clickedPosition = (Integer) v.getTag();
			Layout l = arr[clickedPosition].getLayout();
			if (l != null)
			{
				int lines = l.getLineCount();
				Log.d("lines", "" + lines);
				if (lines > 3)
				{
					holder = (ViewHolder) layouts[clickedPosition].getTag();
					int lineEndIndex = arr[clickedPosition].getLayout().getLineEnd(2);
					String text = arr[clickedPosition].getText().subSequence(0, lineEndIndex - 3) + "...";
					flag=false;
					holder.review.setText(text);
					holder.mReadMoreButton = new Button(context);
					RelativeLayout ll = (RelativeLayout) layouts[clickedPosition].findViewById(R.id.layout);
					LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
					lp.addRule(RelativeLayout.BELOW, holder.review.getId());
					ll.addView(holder.mReadMoreButton, lp);
					holder.mReadMoreButton.setText("Readmore");
					holder.mReadMoreButton.setId(a++);
					holder.mReadMoreButton.setTag(clickedPosition);
					holder.mReadLessButton.setVisibility(View.GONE);
					holder.mReadMoreButton.setOnClickListener(onReadMoreClickListener);
					
				}
			}

		}
	};

	private class ViewHolder
	{
		TextView review;

		Button mReadMoreButton;

		Button mReadLessButton;
	}

	
}
