package com.purpleberrylabs.reviewsapp;

import java.util.ArrayList;

import android.media.TimedText;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.widget.TintEditText;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity
{

	static ListView listview;

	static MyAdapter reviewArrayAdapter;

	ArrayList<Review> reviewsArray = new ArrayList<Review>();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		reviewsArray
				.add(new Review(
						"QBurst, is a design-driven web and mobile application development company [1] with offices worldwide. They focus on web, mobile, cloud computing and business intelligence services across multiple platforms."));
		reviewsArray
				.add(new Review(
						"They have delivered websites, iOS and Android apps for small businesses as well as clients that include National Geographic, Dell, Burberry, Peugeot, Airtel, Alamy and Tata Elxsi."));
		reviewsArray.add(new Review("Understand web, mobile, and cloud based application development from real world examples. Discover new trends, learn best practices, and find out how these can be applied to build successful business apps."));
		reviewsArray
				.add(new Review(
						"QBurst was founded by Prathapan Sethu, Binu Dasappan, and Ansar Shahabudeen in 2004. Since 2005, their professional strength has grown in scale from 25 to 800+ employees."));
		reviewArrayAdapter = new MyAdapter(MainActivity.this, reviewsArray);
		listview = (ListView) findViewById(R.id.listView);
		listview.setItemsCanFocus(false);
		listview.setAdapter(reviewArrayAdapter);
       
		//listview.setOnItemClickListener(onItemClickListener);
	}

}

