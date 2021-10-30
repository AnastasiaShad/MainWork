package com.example.mainwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button mainbt;

    EditText down_puls;
    EditText up_puls;

    Spinner sp_day;
    Spinner sp_month;
    Spinner sp_year;
    Spinner sp_sex;

    String[] results = {"good","okey", "bad"};
    Integer[] days = new Integer[31];
    String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    Integer[] years = new Integer[100];
    String[] sexs = {"Ж", "М"};
    int day, year;
    String sex, result, month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // вызываем функцию для заполнения
        addVariables();
        //  переменные
        mainbt = findViewById(R.id.mainbt);
        mainbt.setOnClickListener(this);

        down_puls = findViewById(R.id.down_puls);
        up_puls = findViewById(R.id.up_puls);


        ArrayAdapter<Integer> days_adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, days);
        days_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_day = (Spinner) findViewById(R.id.sp_day);
        sp_day.setAdapter(days_adapter);

        ArrayAdapter<String> months_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, months);
        months_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_month = (Spinner) findViewById(R.id.sp_month);
        sp_month.setAdapter(months_adapter);

        ArrayAdapter<Integer> years_adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, years);
        years_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_year = (Spinner) findViewById(R.id.sp_year);
        sp_year.setAdapter(years_adapter);

        ArrayAdapter<String> sex_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sexs);
        sex_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_sex = (Spinner) findViewById(R.id.sp_sex);
        sp_sex.setAdapter(sex_adapter);

    }

    // Заполняем наши массивы числами, чтобы не делать это вручную
    public void addVariables() {
        for (int i = 0; i < years.length; i++) {
            years[i] = 2021 - i;
        }
        for (int i = 0; i < days.length; i++) {
            days[i] = i + 1;
        }

    }
    // для месяцев-30 дней и февраля
    public boolean checkInf() {
        switch (month) {
            case "Сентябрь":
                if (day <= 30) {
                    return true;
                }
                break;
            case "Ноябрь":
                if (day <= 30) {
                    return true;
                }
                break;
            case "Апрель":
                if (day <= 30) {
                    return true;
                }
                break;
            case "Июнь":
                if (day <= 30) {
                    return true;
                }
                break;
            case "Февраль":
                if (year % 4 == 0 && day <= 29 || day <= 28) {
                    return true;
                }
                break;
            default:
                return true;
        }
        return false;
    }
    @Override
    public void onClick(View view) {
        // проверяем на пустые значения
        if (up_puls.getText().toString().equals("") || down_puls.getText().toString().equals("")) {
            Toast.makeText(this, "Отсутствие значений!", Toast.LENGTH_SHORT).show();
        } else {
            day = Integer.parseInt(sp_day.getSelectedItem().toString());
            month = sp_month.getSelectedItem().toString();
            year = Integer.parseInt(sp_year.getSelectedItem().toString());
            sex = sp_sex.getSelectedItem().toString();
            // проверяем корректность данных
            if (!checkInf()){
                Toast.makeText(this, "Проверьте корректность даты!", Toast.LENGTH_SHORT).show();
            } else {
                // проверяем сам результат
                int firstPuls = Integer.parseInt(String.valueOf(down_puls.getText()));
                int seconfPuls = Integer.parseInt(String.valueOf(up_puls.getText()));
                int age = 2021-year;
                result = results[(int)(Math.random()*3)];
                double percent = Math.abs(seconfPuls - firstPuls);
                int main_pulse = (firstPuls + seconfPuls)/2;
                // int diffPulse = Math.abs(seconfPuls - firstPuls);


                // переходим на другое Activity
                Intent intent = new Intent(this, Result.class);
                intent.putExtra("first", firstPuls);
                intent.putExtra("second", seconfPuls);
                intent.putExtra("result", result);
//                intent.putExtra("percent", percent);
                startActivity(intent);
            }
        }

    }
}