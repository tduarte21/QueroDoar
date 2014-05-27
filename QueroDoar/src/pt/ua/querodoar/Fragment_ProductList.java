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

public class Fragment_ProductList extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";
	public static ArrayList<ClassProduct> products = new ArrayList<ClassProduct>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		loadListView();
		loadProductList();
		
	}
	
	
	public static Fragment_ProductList newInstance(int sectionNumber) {
		Fragment_ProductList fragment = new Fragment_ProductList();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;  
	}

	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragment_productlist, container, false);
		ArrayAdapter<ClassProduct> adapter = new MyListAdapter();
		ListView listView = (ListView) v.findViewById(R.id.lstViewProducts);
		listView.setAdapter(adapter);
		
		return v;
	}


	
	private void loadListView(){
		
		// ArrayAdapter<Product> adapter;
		new MyListAdapter();
	}

	private void loadProductList(){
		products.add(new ClassProduct("Máquina Café",R.drawable.coffee_machine,1,10.2f,"Descrição Máquina Café"));
		products.add(new ClassProduct("Lata de Atum",R.drawable.veinctor_001,1,10.2f,"Descrição Lata de Atum"));
		products.add(new ClassProduct("Água 0.5L",R.drawable.veinctor_002,1,10.2f,"Descrição Água 0.5L"));
		products.add(new ClassProduct("Água 5L",R.drawable.veinctor_003,1,10.2f,"Descrição Água 5L"));
		products.add(new ClassProduct("Hamburguer",R.drawable.hamburger,1,10.2f,"Descrição Hamburguer"));
		products.add(new ClassProduct("Chá",R.drawable.veinctor_005,1,10.2f,"Descrição Chá"));
		products.add(new ClassProduct("Pizza",R.drawable.pizza_slice,1,10.2f,"Descrição Pizza"));
		products.add(new ClassProduct("Cenoura",R.drawable.carrot,1,.5f,"Descrição Cenoura"));
	}
	
	private class MyListAdapter extends ArrayAdapter<ClassProduct>{

		public MyListAdapter(){
			super(getActivity(), R.layout.fragment_productlist, products);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View itemView = convertView;
			
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
			
			if (itemView == null) {
				itemView = inflater.inflate(R.layout.item_view, parent, false);
			}
			
			//Find the product to work with
			ClassProduct currentProduct = products.get(position);
			
			
			ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView1);
			imageView.setImageResource(currentProduct.getImage());
			
			
			TextView txtPrice = (TextView) itemView.findViewById(R.id.viewPrice);
			txtPrice.setText(Float.toString(currentProduct.getPrice()) + "€");
			
			TextView txtDescription = (TextView) itemView.findViewById(R.id.viewProdDescr);
			txtDescription.setText(currentProduct.getDescription());
			
			TextView txtInst = (TextView) itemView.findViewById(R.id.viewInstitution);
			txtInst.setText(Integer.toString(currentProduct.getInstID()));
			
			TextView txtName = (TextView) itemView.findViewById(R.id.viewProduct);
			txtName.setText(currentProduct.getName());
			
			
			return itemView;
			//return super.getView(position, convertView, parent);
		}
		
		
		
	}
	
}
