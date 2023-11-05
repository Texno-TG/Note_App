package air.texnodev.noteapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import air.texnodev.noteapp.Models.Node;
import air.texnodev.noteapp.R;
import air.texnodev.noteapp.databinding.FragmentAddNodeBinding;
import air.texnodev.noteapp.tools.MyShared;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddNodeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNodeFragment extends Fragment {
    FragmentManager manager;
    FragmentTransaction transaction;
    FragmentAddNodeBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddNodeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddNodeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddNodeFragment newInstance(String param1, String param2) {
        AddNodeFragment fragment = new AddNodeFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        manager = getParentFragmentManager();
        transaction = manager.beginTransaction();
        // Inflate the layout for this fragment
        binding = FragmentAddNodeBinding.inflate(inflater, container, false);
        binding.saveOn.setOnClickListener(view -> {
            String name = binding.nameEdit.getText().toString();
            String full = binding.fullscreenDesc.getText().toString();
            long time = Calendar.getInstance().getTimeInMillis();
            MyShared shared = new MyShared(requireContext(), new Gson());

            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(full)){
                List<Node> nodes = shared.getList("nodes", Node.class);
                if (nodes.size() > 0){
                    nodes.add(new Node(name, String.valueOf(time), full));
                    shared.putList("nodes", nodes);

                }else {

                    List<Node> nodeList = new ArrayList<>();
                    nodeList.add(new Node(name, String.valueOf(time), full));
                    shared.putList("nodes", nodeList);


                }


                transaction.remove(AddNodeFragment.this);
                manager.popBackStack();
                transaction.commit();

                Log.d("MY_NEW",  shared.getList("nodes", Node.class).toString());

            }else {

                Toast.makeText(requireContext(), "Wrong", Toast.LENGTH_SHORT).show();
            }
        });

        //BACK
        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return binding.getRoot();
    }
}