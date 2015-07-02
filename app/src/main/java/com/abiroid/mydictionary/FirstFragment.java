package com.abiroid.mydictionary;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import model.MyDictionaryDatabaseHelper;
import model.Word;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    Button btnAdd, btnClear;
    EditText edWord, edEngMeaning, edUrduMeaning, edEngUsage, edUrduUsage;

    MyDictionaryDatabaseHelper db;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        btnAdd = (Button)view.findViewById(R.id.btnAddWord);
        btnClear = (Button)view.findViewById(R.id.btnClear);
        edWord = (EditText)view.findViewById(R.id.edWord);
        edEngMeaning = ( EditText)view.findViewById(R.id.edEngMeaning);
        edUrduMeaning = (EditText)view.findViewById(R.id.edUrduMeaning);
        edEngUsage = (EditText)view.findViewById(R.id.edEngUsage);
        edUrduUsage = (EditText)view.findViewById(R.id.edUrduUsage);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edWord.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Nothing to Save", Toast.LENGTH_LONG).show();
                } else {
                    Word word = new Word(edWord.getText().toString(), edEngUsage.getText().toString(), edUrduMeaning.getText().toString(), edEngUsage.getText().toString(), edUrduUsage.getText().toString());
                    db = new MyDictionaryDatabaseHelper(getActivity());

                    long result = db.addWord(word);

                    if (result != -1) {
                        Toast.makeText(getActivity(), "Word added", Toast.LENGTH_LONG).show();
                        System.out.println("**********************Added****************");
                    } else {
                        Toast.makeText(getActivity(), "Word not added", Toast.LENGTH_LONG).show();
                        System.out.println("*********Not added******");
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edWord.setText("");
                edEngMeaning.setText("");
                edUrduMeaning.setText("");
                edEngUsage.setText("");
                edUrduUsage.setText("");
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    /*    try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
