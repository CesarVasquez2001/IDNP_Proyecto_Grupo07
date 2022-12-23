package com.example.idnpproyectogrupo07.ui.overview.Graphs.Circular;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class GraficoCircularVista extends View {



    private ArrayList<String> listaPais = new ArrayList<>();
    private ArrayList<Double> listaTNatalidad = new ArrayList<>();
    private ArrayList<Color> listaColores = new ArrayList();
    private int ancho;
    private int alto;
    private float radio;
    private String titulo;

    public void setListaPais(ArrayList<String> listaPais) {
        this.listaPais = listaPais;
    }

    public void setListaTNatalidad(ArrayList<Double> listaTNatalidad) {
        this.listaTNatalidad = listaTNatalidad;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public GraficoCircularVista(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.radio = 0.3f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //recibiendo el ancho y alto
        this.ancho = getWidth();
        this.alto = getHeight();

        //pintar fondo
        canvas.drawRGB(255, 255, 255);
        //pincel negro
        Paint pincelNegro = new Paint();
        pincelNegro.setColor(Color.BLACK);
        //pincel de pais
        Paint colorPais = new Paint();
        //leyenda
        leyendaCirculo(canvas, colorPais, pincelNegro);

        //viendo tamaño de circulo
        //canvas.drawCircle((0.10f+ radio)*ancho, (0.25f+ radio)*alto, radio*ancho, pincel1);

        //requiere de una API 26(el minimo es 21) - Android 8.0 para arriba
        creandoGraficoCircular(canvas, pincelNegro, colorPais);
    }

    private void creandoGraficoCircular(Canvas canvas, Paint pincelNegro, Paint colorPais){
        //el tamanio del segemnto del circulo medido con un rectangulo
        RectF rectangulo = new RectF(0.1f*ancho, 0.25f*alto,
                0.7f*ancho, 0.85f*alto);

        //true: segmento contando el centro
        //false: segmento sin contar el centro
        //canvas.drawArc(rectangulo, 0.0f, 90.0f, true, pincel1);

        float sumaAngulos = 0.0f;
        float sweepAngle = 0.0f;
        float [] xy;

        pincelNegro.setFakeBoldText(true);

        for(int i = 0; i < listaPais.size(); i++){
            sweepAngle = listaTNatalidad.get(i).floatValue()*360.0f/100.0f;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                colorPais.setColor(listaColores.get(i).toArgb());
            }
            //pintando grafico
            canvas.drawArc(rectangulo,
                    sumaAngulos, sweepAngle,true, colorPais);
            //porcentajes(texto) dentro del grafico
            xy = textoPorcentajeGrafico(sumaAngulos, sweepAngle);

            canvas.drawText(listaTNatalidad.get(i)+"%", xy[0], xy[1],pincelNegro);
            //contador
            sumaAngulos += listaTNatalidad.get(i).floatValue()*360.0f/100.0f;
        }
        //requiere de una API 26(el minimo es 21) - Android 8.0 para arriba
        circuloInteriorGrafico(canvas);
    }

    private void leyendaCirculo(Canvas canvas, Paint colorPais,Paint pincelNegro) {
        //grosor de pincel. No funciona con el texto
        pincelNegro.setStrokeWidth(3);
        //solo texto
        pincelNegro.setTextSize(40);

        canvas.drawText(titulo, 0.45f*ancho,
                0.15f*alto, pincelNegro);

        //cambiando pincel
        pincelNegro.setTextSize(22);
        pincelNegro.setStrokeWidth(1);

        //leyenda
        for(int i = 0; i < listaPais.size(); i++){
            colorPais.setColor(listaColores.get(i).toArgb());
            canvas.drawRect(0.75f*ancho,(0.25f + 0.04f * i)*alto,
                    0.77f*ancho, (0.27f + 0.04f * i)*alto, colorPais);
            canvas.drawText(listaPais.get(i), 0.79f*ancho,
                    (0.27f + 0.04f * i)*alto, pincelNegro);
        }
    }

    private void circuloInteriorGrafico(Canvas canvas){
        //dibujando circulo del interior del grafico
        Paint colorCirculoInterior = new Paint();
        //requiere de una API 26(el minimo es 21) - Android 8.0 para arriba

        Color color = Color.valueOf(Color.rgb(240, 240, 240));
        colorCirculoInterior.setColor(color.toArgb());

        canvas.drawCircle((0.10f+ radio)*ancho, (0.25f+ radio)*alto,
                (radio/2.5f)*ancho, colorCirculoInterior);
    }

    private float[] textoPorcentajeGrafico(float startAngle, float sweepAngle) {
        float[] xy = new float[2];
        float currentAngle = (startAngle + sweepAngle / 2) * (float) Math.PI / 180.0f;
        float reajusteX = 126.0f;
        float reajusteY = 72.0f;

        //el 0.7f es del tamaño de circunferencia que toman los textos
        xy[0] = (float) Math.cos(currentAngle) * 0.75f * (this. radio * this.ancho)
                + this.ancho / 2.0f - reajusteX; //eje x
        xy[1] = (float) Math.sin(currentAngle) * 0.75f * (this. radio * this.ancho)
                + this.alto / 2.0f + reajusteY;  //eje y
        return xy;
    }



    public void colorDePaises() {
        int r = 240;
        int g = 240;
        int b = 240;
        Color color;
        //r, g, b >= 15 para que no sea de color negro
        for (int i = 0; i < listaPais.size(); i++){
            r = (int) (Math.random()*240) + 15;
            g = (int) (Math.random()*240) + 15;
            b = (int) (Math.random()*240) + 15;
            color = Color.valueOf(Color.rgb(r, g, b));
            listaColores.add(color);
        }
    }
}