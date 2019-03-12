package com.takip.takip.scenes;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.takip.takip.R;
import com.takip.takip.reader.BarcodeCaptureActivity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProducerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProducerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProducerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private static final int PR_BARCODE_CAPTURE = 9001;


    private EditText barcodeProduct;
    private EditText editTextProductCount;
    private EditText editTextProductName;
    private Button buttonAddNewProduct,buttonProductAdd,scanProductButton;
    private Context context;

    public ProducerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProducerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProducerFragment newInstance(String param1, String param2) {
        ProducerFragment fragment = new ProducerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        this.context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_producer, container, false);
        View view = inflater.inflate(R.layout.fragment_producer, container, false);

        barcodeProduct = view.findViewById(R.id.fragment_product_editTextBarcode);
        editTextProductCount = view.findViewById(R.id.editText_add_product_quantity);
        editTextProductName = view.findViewById(R.id.editText_new_product_name);

        scanProductButton = view.findViewById(R.id.fragment_product_bardcode_scan_iv);
        buttonProductAdd = view.findViewById(R.id.producer_btn_add);
        buttonAddNewProduct = view.findViewById(R.id.fragment_product_doneButton);



        if (scanProductButton != null){
            scanProductButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context.getApplicationContext(), BarcodeCaptureActivity.class);
                    intent.putExtra(BarcodeCaptureActivity.AutoFocus, true);
                    intent.putExtra(BarcodeCaptureActivity.UseFlash,false);

                    startActivityForResult(intent, PR_BARCODE_CAPTURE);
                }
            });
        }

        buttonProductAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProduct();
            }
        });

        buttonAddNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewProduct();
            }
        });





        return view;
    }



    private void addNewProduct() {
        Integer productCount = null;
        String producerName ="";

        if (editTextProductCount != null && editTextProductCount.getText() != null && android.text.TextUtils.isDigitsOnly(editTextProductCount.getText())){
            productCount = Integer.parseInt(editTextProductCount.getText().toString());
        }
        if(editTextProductName != null && editTextProductName.getText() != null ){
            producerName = String.valueOf(editTextProductName.getText());
        }

        String productCode = null;
        if (barcodeProduct != null && barcodeProduct.getText() != null){
            productCode = barcodeProduct.getText().toString();
        }

        if(productCode != null && productCount != null && !producerName.isEmpty()){
            //TODO send new productFIREBASE
        }
    }


    private void addProduct() {
        Integer productCount = null;
        if (editTextProductCount != null && editTextProductCount.getText() != null && android.text.TextUtils.isDigitsOnly(editTextProductCount.getText())){
            productCount = Integer.parseInt(editTextProductCount.getText().toString());
        }

        String productCode = null;
        if (barcodeProduct != null && barcodeProduct.getText() != null){
            productCode = barcodeProduct.getText().toString();
        }

        if(productCode != null && productCount != null){
            //TODO FIREBASE
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void onScanPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
