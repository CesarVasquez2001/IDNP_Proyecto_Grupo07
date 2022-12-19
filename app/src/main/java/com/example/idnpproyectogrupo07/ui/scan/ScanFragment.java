package com.example.idnpproyectogrupo07.ui.scan;

import androidx.lifecycle.ViewModelProvider;

import static android.app.Activity.RESULT_OK;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Session2Command;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;

import androidx.activity.result.contract.ActivityResultContracts;
import com.example.idnpproyectogrupo07.R;


public class ScanFragment extends Fragment {
    private static final String TAG = "Scanfragment";
    private static final int CAMERA_REQUEST_CODE = 1999;
    private ScanViewModel mViewModel;
    ImageView imageView;
    Button btnplastic;
    RecyclerView recyclerView;
    View view;

    private Bitmap imgBitmap;


    @NonNull
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    // plastico  //x //id-user foto codigo cateogria

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scan, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View viewFrag, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(viewFrag, savedInstanceState);
        imageView = viewFrag.findViewById(R.id.ImageViewScan);
        Button button= viewFrag.findViewById(R.id.btnplastic);
        Button camera= viewFrag.findViewById(R.id.btnCamera);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camaraLauncher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imgBitmap ==null){
                    Toast.makeText(getContext(),"Choose an image for scanning",Toast.LENGTH_SHORT).show();
                }else{
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("BitmapImage", imgBitmap);
                    Navigation.findNavController(view).navigate(R.id.plastic_list,bundle);
                }



                /*Bundle dato2 = new Bundle();
                dato2.putParcelableArray("ImagenPlasticConfirmation", imageView);
                getParentFragmentManager().setFragmentResult("dato2", dato2);*/
            }
        });
    }


    private void abrirCamara() {
        Intent intent = new Intent(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
        //if(intent.resolveActivity(getPackageManager()) !=null ){
        startActivityForResult(intent, 1);
        //}
    }



    ActivityResultLauncher<Intent>camaraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() ==RESULT_OK) {
                Bundle extras = result.getData().getExtras();
                Log.e("Test Err!", "a");
                imgBitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imgBitmap);
        }
    }
});


}