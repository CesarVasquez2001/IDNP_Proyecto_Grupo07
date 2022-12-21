package com.example.idnpproyectogrupo07.ui.overview.Graphs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class GraficoBarrasVista extends View {



    private ArrayList<String> listTags = new ArrayList<>();
    private ArrayList<Double> listValues = new ArrayList<>();
    private int limiteMaximo;
    private String titulo;



    private String leyenda;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setLeyenda(String leyenda) {
        this.leyenda = leyenda;
    }
    public void setListTags(ArrayList<String> listTags) {
        this.listTags = listTags;
    }

    public static int calcularDecena(int decena){
        return decena - (decena%10) + 10;
    }
    public void setListValues(ArrayList<Double> listValues) {
        this.listValues = listValues;
        limiteMaximo= calcularDecena(Collections.max(listValues).intValue());
    }
    public GraficoBarrasVista(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.limiteMaximo = Integer.MAX_VALUE;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //pintar fondo
        canvas.drawRGB(255, 255, 255);

        //recibiendo el ancho y alto
        int ancho = getWidth();
        int alto = getHeight();

        Paint pincel1 = new Paint();
        pincel1.setColor(Color.RED);

        Paint pincel2 = new Paint();
        pincel2.setColor(Color.BLACK);
        pincel2.setStrokeWidth(3);

        //solo texto
        pincel2.setTextSize(40);

        canvas.drawText(titulo, 0.25f * ancho,
                0.1f * alto, pincel2);

        //solo texto
        pincel2.setTextSize(22);

        //lineas de grafico de barras***************************************************
        canvas.drawLine(0.15f * ancho, 0.2f * alto, 0.15f * ancho, 0.8f * alto, pincel2);
        canvas.drawLine(0.15f * ancho, 0.8f * alto, 0.85f * ancho, 0.8f * alto, pincel2);

        //lineas de medicion de la grafica***********************************************
        pincel2.setStrokeWidth(1);
        for (int i = 0; i < 10; i++) {
            float yLine = 0.2f * alto + (0.06f) * alto * (i);
            //numeros de la eje y
            canvas.drawText("" + (limiteMaximo / 10) * (10 - i), 0.1f * ancho, yLine, pincel2);

            canvas.drawLine(0.15f * ancho, yLine,
                    0.85f * ancho, yLine,
                    pincel2);
        }

        //leyenda***********************************************
        canvas.drawRect(0.40f * ancho, 0.92f * alto,
                0.42f * ancho, 0.94f * alto, pincel1);
        canvas.drawText(leyenda, 0.44f * ancho,
                0.94f * alto, pincel2);

        if (listTags.size() != 0 || listValues.size()!=0) {
            //****************************************************
            //creando las barras y etiquetas
            Log.d("VIEW",listTags.size()+"");
            Log.d("VIEW",listValues.size()+"");

            float parts = (0.70f * ancho) / ((float) listTags.size() * 2 + 1f);

            float espacios = parts;

            for (int i = 0; i < listTags.size(); i++) {

                String[] words = listTags.get(i).split("\\s");
                //Texto-Primeras tres letras
                canvas.drawText(words[0], 0, words[0].length(), 0.15f * ancho + espacios, 0.85f * alto, pincel2);
                //barras
                canvas.drawRect(0.15f * ancho + espacios,
                        0.8f * alto - (0.6f * alto * listValues.get(i).floatValue() / limiteMaximo),
                        0.15f * ancho + espacios + parts, 0.8f * alto, pincel1);
                espacios += parts * 2;
            }
            //****************************************************
        }


    }



/*
    private void readExcel(String fileName) {
        try {
            InputStream myFile = new FileInputStream(new File(fileName));
            HSSFWorkbook wb = new HSSFWorkbook(myFile);
            HSSFSheet sheet = wb.getSheetAt(1);
            HSSFCell cell;
            HSSFRow row;
            //Contar filas
            // System.out.println("" + sheet.getLastRowNum());
            //empieza en 1 para no leer las cabeceras
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                row = sheet.getRow(i);
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    if(j % 2 == 0){
                        listaPais.add(cell.toString());
                    }else{
                        listaTNatalidad.add(Double.parseDouble(cell.toString()));
                    }
                }
            }
        } catch (Exception e) {
            Log.d("Debug ",e.getMessage());
        }
    }*/
}