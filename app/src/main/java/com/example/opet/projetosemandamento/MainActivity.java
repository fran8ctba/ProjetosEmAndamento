package com.example.opet.projetosemandamento;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends Activity {

    private EditText editNome;
    private TextView textDataInicionInput;
    private TextView textDataEntregaInput;
    private CheckBox checkBoxFinalizar;
    private TextView textNome;
    private TextView textDataInicio;
    private TextView textDataEntrega;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNome = findViewById(R.id.editNomeProjeto);
        textDataInicionInput = findViewById(R.id.textEntregaInputProjeto);
        textDataEntregaInput = findViewById(R.id.textEntregaInputProjeto);
        checkBoxFinalizar = findViewById(R.id.checkBoxFinalizarProjeto);
        textNome = findViewById(R.id.textNomeProjeto);
        textDataInicio = findViewById(R.id.textInicioProjeto);
        textDataEntrega = findViewById(R.id.textEntregaProjeto);

        final Calendar inicio = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                inicio.set(Calendar.YEAR,year);
                inicio.set(Calendar.MONTH,monthOfYear);
                inicio.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                textDataInicionInput.setText(format.format(inicio.getTime()));

            }
        };

        final Calendar inicio = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                inicio.set(Calendar.YEAR,year);
                inicio.set(Calendar.MONTH,monthOfYear);
                inicio.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                textDataInicionInput.setText(format.format(inicio.getTime()));

            }
        };
        
        textVencimentoInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this,date,vencimento.get(Calendar.YEAR),vencimento.get(Calendar.MONTH),vencimento.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public void salvar(View v){
        Produto produto = new Produto();

        String nomeProduto = editNome.getText().toString();
        String vencimentoProduto = textVencimentoInput.getText().toString();

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            produto.setVencimento(format.parse(vencimentoProduto));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        produto.setNome(nomeProduto);

        Calendar vencimentoCalendar = Calendar.getInstance();
        vencimentoCalendar.setTime(produto.getVencimento());
        Calendar alerta = Calendar.getInstance();
        alerta.add(Calendar.DATE,10);

        if(alerta.after(vencimentoCalendar))
            textVencimento.setTextColor(Color.RED);
        else
            textVencimento.setTextColor(Color.GREEN);

        textVencimento.setText(vencimentoProduto);
        textNome.setText(nomeProduto);
    }
}
