package com.example.idnpproyectogrupo07.ui.overview;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.idnpproyectogrupo07.classes.Plastic;
import com.example.idnpproyectogrupo07.classes.ScanCodePlastic;
import com.example.idnpproyectogrupo07.classes.ScanItemsPlastic;
import com.example.idnpproyectogrupo07.classes.User;
import com.example.idnpproyectogrupo07.database.DBCode;
import com.example.idnpproyectogrupo07.database.DBPlastic;
import com.example.idnpproyectogrupo07.database.DBType;
import com.example.idnpproyectogrupo07.database.DBUser;
import com.example.idnpproyectogrupo07.databinding.FragmentOverviewBinding;
import com.example.idnpproyectogrupo07.ui.overview.Graphs.Circular.PieHelper;
import com.example.idnpproyectogrupo07.ui.overview.Graphs.Circular.PieView;


import java.util.ArrayList;

public class OverviewFragment extends Fragment {
    private TextView leyendaGraficoCircular1;
    private TextView leyendaGraficoCircular2;

    private final int[] DEFAULT_COLOR_LIST = {
            Color.parseColor("#33B5E5"),
            Color.parseColor("#AA66CC"),
            Color.parseColor("#99CC00"),
            Color.parseColor("#FFBB33"),
            Color.parseColor("#FF4444"),
            Color.parseColor("#FF00FB"),

            Color.parseColor("#46FF33"),
            Color.parseColor("#FF3333"),
            Color.parseColor("#B4E5E2"),
            Color.parseColor("#CDB4E5"),
            Color.parseColor("#ED18F0"),
            Color.parseColor("#F0FF00"),
            Color.parseColor("#04AC4B"),
            Color.parseColor("#E8FFAB"),
            Color.parseColor("#CB1212"),
    };
    ArrayList<Plastic> plastics;
    ArrayList<ScanCodePlastic> codes;
    ArrayList<ScanItemsPlastic> types;
    User user;
    DBCode dbCode;
    DBPlastic dbPlastic;
    DBUser dbUser;
    DBType dbType;
    private FragmentOverviewBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        dbUser = new DBUser(getContext());
        dbUser.OpenDb();
        user = dbUser.getPreference();

        dbPlastic = new DBPlastic(getContext());
        dbPlastic.OpenDb();

        dbCode = new DBCode(getContext());
        dbCode.OpenDb();
        dbType = new DBType(getContext());
        dbType.OpenDb();

        types = dbType.getAllType();
        codes = dbCode.getAllCode();
        plastics = dbPlastic.getAllPlastic((int) user.getId_user());


        binding = FragmentOverviewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        leyendaGraficoCircular1 = binding.leyendaGraficoCircular1;
        leyendaGraficoCircular2 = binding.leyendaGraficoCircular2;

        // Grafico de barras cantidad de items escaneados por codigo
        binding.graficoBarrasVista.setTitulo("Items escaneados por codigo");
        binding.graficoBarrasVista.setLeyenda("Numero de escaneos");
        ArrayList<String> tagsCodes = new ArrayList<String>();
        ArrayList<Double> amountCodes = new ArrayList<Double>();
        for (int i = 0; i < codes.size(); i++) {
            double aux = (double) dbPlastic.getCountCodeUser((int) user.getId_user(), codes.get(i).getId_code());
            if (aux != 0) {
                tagsCodes.add(String.valueOf(codes.get(i).getNombre()));
                amountCodes.add(aux);
            }
        }
        binding.graficoBarrasVista.setListTags(tagsCodes);
        binding.graficoBarrasVista.setListValues(amountCodes);

        // Grafico de barras cantidad de items escaneados por codigo
        binding.graficoBarrasVista2.setTitulo("Cantidad de items escaneados por codigo");
        binding.graficoBarrasVista2.setLeyenda("Cantidad de items");
        tagsCodes = new ArrayList<String>();
        amountCodes = new ArrayList<Double>();
        for (int i = 0; i < codes.size(); i++) {
            double aux = (double) dbPlastic.getSumtCodeUser((int) user.getId_user(), codes.get(i).getId_code());
            if (aux != 0) {
                tagsCodes.add(String.valueOf(codes.get(i).getNombre()));
                amountCodes.add(aux);
            }
        }
        binding.graficoBarrasVista2.setListTags(tagsCodes);
        binding.graficoBarrasVista2.setListValues(amountCodes);

        // Grafico circular
        binding.tituloGraficoCircular1.setText("Items escaneados por tipo de plastico");
        grafico1(binding.pieView);
        //
        binding.tituloGraficoCircular2.setText("Cantidad de items escaneados por tipo");
        grafico2(binding.pieView2);

    }
    private void grafico1(PieView pieView) {

        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();
        ArrayList<Integer> intList = new ArrayList<Integer>();
        ArrayList<String> test = new ArrayList<String>();
        for (int i = 0; i < types.size(); i++) {
            int aux = dbPlastic.getCountTypeUser((int) user.getId_user(), types.get(i).getId_type());
            if (aux != 0) {
                test.add(String.valueOf(types.get(i).getNombre()));
                intList.add(aux);
            }
        }
        float totalInt = 0;
        for (int number : intList)
            totalInt += (float)number;

        int totalNum = intList.size();

        for (int i = 0; i < totalNum; i++) {
            pieHelperArrayList.add(new PieHelper(100 * intList.get(i) / totalInt,test.get(i),DEFAULT_COLOR_LIST[i]));
            leyendaGraficoCircular1.append((pieHelperArrayList.get(i).getTitle()+" - "+pieHelperArrayList.get(i).getPercentStr()+"\n"));
        }
        pieView.selectedPie(PieView.NO_SELECTED_INDEX);
        pieView.showPercentLabel(true);
        pieView.setDate(pieHelperArrayList);

        pieView.setOnPieClickListener(new PieView.OnPieClickListener() {
            @Override public void onPieClick(int index) {
                if (index != PieView.NO_SELECTED_INDEX) {
                    PieHelper aux = pieView.getPieHelperList().get(index);
                    leyendaGraficoCircular1.setText(aux.getTitle() +" - "+aux.getPercentStr() + " - ");
                }
            }
        });
    }

    private void grafico2(PieView pieView) {

        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();
        ArrayList<Integer> intList = new ArrayList<Integer>();
        ArrayList<String> test = new ArrayList<String>();
        for (int i = 0; i < types.size(); i++) {
            int aux = dbPlastic.getSumtTypeUser((int) user.getId_user(), types.get(i).getId_type());
            if (aux != 0) {
                test.add(String.valueOf(types.get(i).getNombre()));
                intList.add(aux);
            }
        }
        float totalInt = 0;
        for (int number : intList)
            totalInt += (float)number;

        int totalNum = intList.size();

        for (int i = 0; i < totalNum; i++) {
            pieHelperArrayList.add(new PieHelper(100 * intList.get(i) / totalInt,test.get(i),DEFAULT_COLOR_LIST[i]));
            leyendaGraficoCircular2.append((pieHelperArrayList.get(i).getTitle()+" - "+pieHelperArrayList.get(i).getPercentStr()+"\n"));
        }
        pieView.selectedPie(PieView.NO_SELECTED_INDEX);
        pieView.showPercentLabel(true);
        pieView.setDate(pieHelperArrayList);

        pieView.setOnPieClickListener(new PieView.OnPieClickListener() {
            @Override public void onPieClick(int index) {
                if (index != PieView.NO_SELECTED_INDEX) {
                    PieHelper aux = pieView.getPieHelperList().get(index);
                    leyendaGraficoCircular2.setText(aux.getTitle() +" - "+aux.getPercentStr() + " - ");
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}