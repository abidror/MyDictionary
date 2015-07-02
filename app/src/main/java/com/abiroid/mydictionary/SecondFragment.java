package com.abiroid.mydictionary;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import model.MyDictionaryDatabaseHelper;
import model.Word;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SecondFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {

    private Button btnSearch, btnPronounce;
    private EditText edWord, edEngMeaning, edUrduMeaning, edEngUsage, edUrduUsage;
    private MyDictionaryDatabaseHelper db;

    private TextToSpeech tts;
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
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SecondFragment() {
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
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        btnSearch = (Button)view.findViewById(R.id.btnSearch);
        btnPronounce = (Button)view.findViewById(R.id.btnPronounce);

        edWord = (EditText)view.findViewById(R.id.edWord);
        //edWord.setText("wow");
        edEngMeaning = (EditText)view.findViewById(R.id.edEngMeaning);
        edUrduMeaning = (EditText)view.findViewById(R.id.edUrduMeaning);
        edEngUsage = (EditText)view.findViewById(R.id.edEngUsage);
        edUrduUsage = (EditText)view.findViewById(R.id.edUrduUsage);

        btnSearch.setOnClickListener(new View.OnClickListener() {
                                         @Override
              public void onClick(View view) {
                    String searchWord = edWord.getText().toString().trim();
                    db = new MyDictionaryDatabaseHelper(getActivity());
                    Word word = db.findWord(searchWord);

                    if(word != null)
                    {
                        Toast.makeText(getActivity(), "Word Found", Toast.LENGTH_LONG).show();
                        edEngMeaning.setText(word.getEngMeaning());
                        edUrduMeaning.setText(word.getUrduMeaning());
                        edEngUsage.setText(word.getEngUsage());
                        edUrduUsage.setText(word.getUrduUsage());
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "Word not found", Toast.LENGTH_LONG).show();
                    }
              }
            }
        );

        tts = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR)
                    tts.setLanguage(Locale.UK);
            }
        });

        btnPronounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = edWord.getText().toString();

                if( word.equals(""))
                {
                    Toast.makeText(getActivity(), "Nothing to Pronounce", Toast.LENGTH_LONG).show();
                }
                else
                {
                    tts.speak(word, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
        return view;
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
     /*   try {
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
