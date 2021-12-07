package org.esei.moneygest;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.esei.moneygest.model.Gasto;
import org.esei.moneygest.model.GastoMapper;

import java.util.ArrayList;

public class FiltrosActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros);

    }
}