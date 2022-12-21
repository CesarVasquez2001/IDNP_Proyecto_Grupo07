package com.example.idnpproyectogrupo07.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import com.example.idnpproyectogrupo07.R;
import com.example.idnpproyectogrupo07.classes.EducationItems;
import com.example.idnpproyectogrupo07.classes.Plastic;
import com.example.idnpproyectogrupo07.classes.ScanCodePlastic;
import com.example.idnpproyectogrupo07.classes.ScanItemsPlastic;
import com.example.idnpproyectogrupo07.database.DBCode;
import com.example.idnpproyectogrupo07.database.DBPlastic;
import com.example.idnpproyectogrupo07.database.DBType;
import com.example.idnpproyectogrupo07.database.DBUser;
import com.example.idnpproyectogrupo07.databinding.FragmentHomeBinding;
import com.example.idnpproyectogrupo07.databinding.RecyclerViewItemHistoryBinding;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ImageCarousel carousel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        carousel = binding.imageCarousel;

        carousel.registerLifecycle(getLifecycle());

        List<CarouselItem> list = new ArrayList<>();

        list.add(new CarouselItem(R.drawable.overview));//1  0
        list.add(new CarouselItem(R.drawable.education));//2
        list.add(new CarouselItem(R.drawable.scan));//0



        TextView title = binding.title;
        Button button = binding.button;
        Button history = binding.history;
        carousel.setData(list);
        carousel.setCarouselListener(new CarouselListener() {
            @Nullable
            @Override
            public ViewBinding onCreateViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
                return null;
            }

            @Override
            public void onBindViewHolder(@NonNull ViewBinding viewBinding, @NonNull CarouselItem carouselItem, int i) {
                switch (i) {
                    case 0:
                        title.setText("You will be able to see the graphs according to use");
                        button.setText("Overview");
                        button.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_baseline_insert_chart_24,0);
                        break;
                    case 1:
                        title.setText("Learn more about how to classify plastics");
                        button.setText("Education");
                        button.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_baseline_book_24,0);
                        break;
                    case 2:
                        title.setText("Scan the items you want");
                        button.setText("Scan");
                        button.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_baseline_camera_alt_24,0);
                        break;
                    default:
                        break;
                };

            }

            @Override
            public void onClick(int i, @NonNull CarouselItem carouselItem) {
                //Toast.makeText(getContext(), "Slide" + i, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(int i, @NonNull CarouselItem carouselItem) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button.getText().equals("Overview")){
                    Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_overview);
                }else if(button.getText().equals("Education")){
                    Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_education);
                }else{
                    Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_scan);
                }
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_history);

            }
        });
        DBPlastic dbPlastic = new DBPlastic(getContext());
        dbPlastic.OpenDb();

        DBUser dbUser= new DBUser(getContext());
        dbUser.OpenDb();

        RecyclerViewItemHistoryBinding item = binding.lastPlastic;
        int size = dbPlastic.getSize((int) dbUser.getPreference().getId_user());
        if (size == 0){
             item.textViewItemStatus.setText("SIN ITEMS");
            item.textViewItemName.setText("");
            item.textViewItemDescription.setText("");
        }else{
            ArrayList<Plastic> plastics = dbPlastic.getAllPlastic((int) dbUser.getPreference().getId_user());
            Plastic plastic = plastics.get(plastics.size()-1);
            DBCode dbCode = new DBCode(getContext());
            DBType dbType = new DBType(getContext());
            dbCode.OpenDb();
            dbType.OpenDb();
            ScanCodePlastic code = dbCode.getCode(plastic.getId_code_column());
            ScanItemsPlastic type = dbType.getType(plastic.getId_type_column());

            item.imageViewHistoryItem.setImageBitmap(plastic.getPlastic_picture());
            item.textViewItemStatus.setText(plastic.getAmount_plastic()+"");
            item.textViewItemName.setText(type.getNombre());
            item.textViewItemDescription.setText(code.getNombre());

        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}