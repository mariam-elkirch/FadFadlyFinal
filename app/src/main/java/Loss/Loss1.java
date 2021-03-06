package Loss;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.MNM.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Loss1 extends Fragment {

View view;
    public Loss1() {
        // Required empty public constructor
    }

    static final List<Double> list = new ArrayList<Double>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_loss1, container, false);
        RadioGroup radioGroup = (RadioGroup)view.findViewById(R.id.radios);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new Loss2());
                fr.commit();
                if(checkedId==R.id.first)
                {
                    list.add(1.0);
                }
                else if(checkedId==R.id.second){
                     list.add(2.0);
                }
                else if(checkedId==R.id.third){
                    list.add(3.0);
                }
            }
        });

        return view;
    }

}