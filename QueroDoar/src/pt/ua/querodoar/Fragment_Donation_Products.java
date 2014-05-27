package pt.ua.querodoar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_Donation_Products extends Fragment {
	
	private static final String ARG_SECTION_NUMBER = "section_number";


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	public static Fragment_Donation_Products newInstance(int sectionNumber) {
		Fragment_Donation_Products fragment = new Fragment_Donation_Products();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_donation_products, container, false);

		return v;
	}

}
