package pt.ua.querodoar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import pt.ua.querodoar.R;

public class Fragment_Friends extends Fragment{
	
	private static final String ARG_SECTION_NUMBER = "section_number";


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	public static Fragment_Friends newInstance(int sectionNumber) {
		Fragment_Friends fragment = new Fragment_Friends();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_friends, container, false);

		return v;
	}

}
