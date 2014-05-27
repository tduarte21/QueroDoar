package pt.ua.querodoar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import pt.ua.querodoar.R;

public class Fragment_News extends Fragment{

	private static final String ARG_SECTION_NUMBER = "section_number";


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	public static Fragment_News newInstance() {
		Fragment_News fragment = new Fragment_News();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_news, container, false);
		return v;
	}
	
}



