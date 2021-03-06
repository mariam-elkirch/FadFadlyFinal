package Self;


import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.MNM.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import Family.Family1;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

/**
 * A simple {@link Fragment} subclass.
 */
public class Self5 extends Fragment {
View view;

    public Self5() {
        // Required empty public constructor
    }
    private Classifier mClassifier = null;

 public   static final List<String> list = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view= inflater.inflate(R.layout.fragment_self5, container, false);
        RadioGroup radioGroup = (RadioGroup)view.findViewById(R.id.radios);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new Family1());
                fr.commit();
                if(checkedId==R.id.first)
                {
                    Self1.list.add(2.0);
                }
                else if(checkedId==R.id.second){
                    Self1.list.add(3.0);
                }
                else if(checkedId==R.id.third){
                    Self1.list.add(1.0);
                }
                        //print List
                for (int i=0;i < Self1.list.size();i++)
                {
                    Log.i("Value of self element "+i, String.valueOf(Self1.list.get(i)));

                }

                double a=Self1.list.get(0);
                double b=Self1.list.get(1);
                double c=Self1.list.get(2);
                double d=Self1.list.get(3);
                double m=Self1.list.get(4);
                Random mRandom = new Random();
                //Convert List to array
             //   Double[] arrSelf = Self1.list.toArray(new Double[0]);
                //print Array
                // for (D x : arrSelf)
                // Log.i("Array", x+"");

                 Sample[] mSamples = new Sample[]{
                         new Sample( new double[]{a, b, c, d,m}), // should be in the setosa domain

                 };
                StringBuilder sb = new StringBuilder("Samples:\n");
                for(Sample s : mSamples) {
                    sb.append(s.toString() + "\n");
                }


                AssetManager assetManager =getContext().getAssets() ;
                try {
                    mClassifier = (Classifier) weka.core.SerializationHelper.read(assetManager.open("self3.model"));

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    // Weka "catch'em all!"
                    e.printStackTrace();
                }
              Log.i("model self load","Model self Loaded");
                if(mClassifier==null){
                    Log.i("model not load","Model not Loaded");
                    return;
                }
                final Attribute attributeSepalLength = new Attribute("self1");
                final Attribute attributeSepalWidth = new Attribute("self2");
                final Attribute attributePetalLength = new Attribute("self3");
                final Attribute attributePetalWidth = new Attribute("self4");
                final Attribute attributePetalWidth2 = new Attribute("self5");
                final List<String> classes = new ArrayList<String>() {
                    {
                        add("bad"); // cls nr 1
                        add("good"); // cls nr 2
                        add("excellent"); // cls nr 3
                    }
                };
                ArrayList<Attribute> attributeList = new ArrayList<Attribute>(2) {
                    {
                        add(attributeSepalLength);
                        add(attributeSepalWidth);
                        add(attributePetalLength);
                        add(attributePetalWidth);
                        add(attributePetalWidth2);

                        Attribute attributeClass = new Attribute("target", classes);
                        add(attributeClass);
                    }
                };
                Instances dataUnpredicted = new Instances("TestInstances",
                        attributeList, 1);
                // last feature is target variable
                dataUnpredicted.setClassIndex(dataUnpredicted.numAttributes() - 1);
                // create new instance: this one should fall into the setosa domain
                final Sample s = mSamples[mRandom.nextInt(mSamples.length)];
                DenseInstance newInstance = new DenseInstance(dataUnpredicted.numAttributes()) {
                    {
                        setValue(attributeSepalLength, s.features[0]);
                        setValue(attributeSepalWidth, s.features[1]);
                        setValue(attributePetalLength, s.features[2]);
                        setValue(attributePetalWidth, s.features[3]);
                        setValue(attributePetalWidth2, s.features[4]);
                    }
                };
                // reference to dataset
                newInstance.setDataset(dataUnpredicted);


                try {
                    double result = mClassifier.classifyInstance(newInstance);
                    String className = classes.get(new Double(result).intValue());

                    String msg =  ", self predicted: " + className ;
                    Log.i("WEKA_TEST", msg);
                    list.add(0,className);
                } catch (Exception e) {
                    e.printStackTrace();
                }

















            }
        });


        return view;

    }
    public class Sample {

        public double [] features;

        public Sample(double[] _features) {

            //  this.label = _label;
            this.features = _features;
        }

        @Override
        public String toString() {
            return

                    ", feat: " + Arrays.toString(features);
        }
    }

}