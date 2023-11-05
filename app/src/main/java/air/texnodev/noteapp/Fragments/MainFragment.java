package air.texnodev.noteapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import air.texnodev.noteapp.Adapter.AdapterRecycler;
import air.texnodev.noteapp.Adapter.OnItemsClick;
import air.texnodev.noteapp.Models.Node;
import air.texnodev.noteapp.R;
import air.texnodev.noteapp.databinding.FragmentMainBinding;
import air.texnodev.noteapp.tools.MyShared;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements OnItemsClick {

    AdapterRecycler adapterRecycler;
    FragmentMainBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);



        binding.addNote.setOnClickListener(view -> {
            FragmentManager manager = getParentFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.container, new AddNodeFragment());
            transaction.addToBackStack("");
            transaction.commit();


        });

        return binding.getRoot();

    }
    List<Node> nodes;
    MyShared shared;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shared = new MyShared(requireContext(), new Gson());
        nodes = shared.getList("nodes", Node.class);


        if (nodes.size() > 0) {

            adapterRecycler = new AdapterRecycler(requireContext(), nodes, this);
            binding.newRecycler.setAdapter(adapterRecycler);
        }

    }

    @Override
    public void onClick(View view, int position) {
        if (adapterRecycler != null){
            nodes.remove(position);
            adapterRecycler.notifyItemRemoved(position);
            shared.putList("nodes", nodes);
        }

    }

    @Override
    public void onClickItem(View view, int position) {
        OpenNoteFragment noteFragment = new OpenNoteFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("poz", position);
        noteFragment.setArguments(bundle);
        FragmentManager manager = getParentFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, noteFragment);
        transaction.addToBackStack("");
        transaction.commit();

        Toast.makeText(requireContext(), "Item", Toast.LENGTH_SHORT).show();
    }
}