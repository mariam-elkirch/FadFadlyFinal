package Emotional;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.MNM.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SingleLove2 extends Fragment {

View view;
    public SingleLove2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_single_love2, container, false);
        RadioGroup radioGroup = (RadioGroup)view.findViewById(R.id.radios);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new SingleLove3());
                fr.commit();
                if(checkedId==R.id.first)
                {
                    SingleOrNotOutLayer.list.add(1,3.0);
                }
                else if(checkedId==R.id.second){
                    SingleOrNotOutLayer.list.add(1,2.0);
                }
                else if(checkedId==R.id.third){
                    SingleOrNotOutLayer.list.add(1,1.0);
                }

            }
        });

        return view;
    }

}