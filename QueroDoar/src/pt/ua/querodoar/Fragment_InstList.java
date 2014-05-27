package pt.ua.querodoar;

import java.util.ArrayList;

import pt.ua.querodoar.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class Fragment_InstList extends Fragment{
	
	private static final String ARG_SECTION_NUMBER = "section_number";
	private ArrayList<ClassInstitution> institutions = new ArrayList<ClassInstitution>();
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		loadInstList();
	}
	
	
	public static Fragment_InstList newInstance(int sectionNumber) {
		Fragment_InstList fragment = new Fragment_InstList();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragment_instlist, container, false);
		ArrayAdapter<ClassInstitution> adapter = new MyListAdapter();
		ListView listView = (ListView) v.findViewById(R.id.lstViewInst);
		listView.setAdapter(adapter);
		
		return v;
	}


	
	private void loadInstList(){
		institutions.add(new ClassInstitution("Centro Social",R.drawable.hd,"Aveiro",1996,"Descrição Máquina Café"));
		institutions.add(new ClassInstitution("Associação",R.drawable.hd,"Aveiro",2000,"Descrição Lata de Atum"));
		institutions.add(new ClassInstitution("Instituição",R.drawable.hd,"Aveiro",1960,"Descrição Água 0.5L"));
		institutions.add(new ClassInstitution("Santa Casa",R.drawable.hd,"Aveiro",2010,"Descrição Água 5L"));
		institutions.add(new ClassInstitution("APPACDM",R.drawable.hd,"Aveiro",2002,"Descrição Hamburguer"));
		institutions.add(new ClassInstitution("Centro Cultural",R.drawable.hd,"Aveiro",1963,"Descrição Chá"));
		institutions.add(new ClassInstitution("Liga dos Amigos",R.drawable.hd,"Aveiro",2001,"Descrição Pizza"));
		institutions.add(new ClassInstitution("C.S.S.C.M.",R.drawable.hd,"Aveiro",2005,"Descrição Cenoura"));
	}
	
	private class MyListAdapter extends ArrayAdapter<ClassInstitution>{

		public MyListAdapter(){
			super(getActivity(), R.layout.fragment_instlist, institutions);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View itemView = convertView;
			
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
			
			if (itemView == null) {
				itemView = inflater.inflate(R.layout.inst_view, parent, false);
			}
			
			//Find the product to work with
			ClassInstitution currentInst = institutions.get(position);
			
			
			ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView1);
			imageView.setImageResource(currentInst.getImage());
			
			
			TextView txtYear = (TextView) itemView.findViewById(R.id.viewPrice);
			txtYear.setText(Integer.toString(currentInst.getYear()));
			
			TextView txtDescription = (TextView) itemView.findViewById(R.id.viewProdDescr);
			txtDescription.setText(currentInst.getDescription());
			
			TextView txtCity = (TextView) itemView.findViewById(R.id.viewInstitution);
			txtCity.setText(currentInst.getLocation());
			
			TextView txtName = (TextView) itemView.findViewById(R.id.viewProduct);
			txtName.setText(currentInst.getName());
			
			
			//Find the view
			
			
			return itemView;
			//return super.getView(position, convertView, parent);
		}
		
		
		
	}

}
