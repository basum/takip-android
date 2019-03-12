package com.takip.takip.scenes;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.util.NumberUtils;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.takip.takip.R;
import com.takip.takip.reader.BarcodeCaptureActivity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecycleManagerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecycleManagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecycleManagerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final int RC_BARCODE_CAPTURE = 9001;
    private static final String TAG = "BarcodeMain";

    private CompoundButton autoFocus;
    private CompoundButton useFlash;
    private TextView statusMessage;
    private EditText barcodeValue;
    private EditText editTextProductCount;
    private Button buttonDone;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button scanButton;
    private Context context;

    private OnFragmentInteractionListener mListener;

    public RecycleManagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecycleManagerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecycleManagerFragment newInstance(String param1, String param2) {
        RecycleManagerFragment fragment = new RecycleManagerFragment();
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
        View view = inflater.inflate(R.layout.fragment_recycle_manager, container, false);

        barcodeValue = view.findViewById(R.id.fragment_recycle_manager_editTextBarcode);
        editTextProductCount = view.findViewById(R.id.editTextProductCount);

        scanButton = (Button) view.findViewById(R.id.fragment_recycle_manager_bardcode_scan_iv);
        if (scanButton != null){
            scanButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context.getApplicationContext(), BarcodeCaptureActivity.class);
                    intent.putExtra(BarcodeCaptureActivity.AutoFocus, true);
                    intent.putExtra(BarcodeCaptureActivity.UseFlash,false);

                    startActivityForResult(intent, RC_BARCODE_CAPTURE);
                }
            });
        }

        buttonDone = (Button) view.findViewById(R.id.fragment_recycle_manager_doneButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performDone();
            }
        });

        return view;
    }

    private void performDone() {
        Integer productCount = null;
        if (editTextProductCount != null && editTextProductCount.getText() != null && android.text.TextUtils.isDigitsOnly(editTextProductCount.getText())){
            productCount = Integer.parseInt(editTextProductCount.getText().toString());
        }

        String productCode = null;
        if (barcodeValue != null && barcodeValue.getText() != null){
            productCode = barcodeValue.getText().toString();
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

    // TODO: Rename method, update argument and hook method into UI event
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    //statusMessage.setText(R.string.barcode_success);
                    barcodeValue.setText(barcode.displayValue);
                    Log.d(TAG, "Barcode read: " + barcode.displayValue);
                } else {
                    barcodeValue.setText(R.string.barcode_failure);
                    Log.d(TAG, "No barcode captured, intent data is null");
                }
            } else {
                barcodeValue.setText(String.format(getString(R.string.barcode_error),
                        CommonStatusCodes.getStatusCodeString(resultCode)));
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
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
