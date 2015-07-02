package com.symphodia.sample.personallist2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    private PersonFragment mPersonFragment;
    private EditText mEtName;
    private EditText mEtAge;
    private EditText mEtCompany;
    private Button mBtnAdd;
    private Button mBtnShowAll;
    private Button mBtnSearchByName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            mPersonFragment = new PersonFragment();
            getFragmentManager().beginTransaction()
                    .add(R.id.container, mPersonFragment)
                    .commit();
        }

        mEtName = (EditText)findViewById(R.id.et_name);
        mEtAge = (EditText) findViewById(R.id.et_age);
        mEtCompany = (EditText) findViewById(R.id.et_company);
        mBtnAdd = (Button)findViewById(R.id.btn_add);
        mBtnAdd.setOnClickListener(this);
        mBtnShowAll = (Button) findViewById(R.id.btn_show_all);
        mBtnShowAll.setOnClickListener(this);
        mBtnSearchByName = (Button) findViewById(R.id.btn_search_by_name);
        mBtnSearchByName.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                String name = mEtName.getText().toString();
                String company = mEtCompany.getText().toString();
                int age = -1;
                try{
                    age = Integer.parseInt(mEtAge.getText().toString());
                }catch (NumberFormatException e) {
                    Log.e("MainActivity", e.getMessage());
                    break;
                }
                if(!name.equals("") && age >= 0 && !company.equals("")){
                    mPersonFragment.add(name, age, company);
                }
                break;
            case R.id.btn_show_all:
                mPersonFragment.showAll();
                break;
            case R.id.btn_search_by_name:
                mPersonFragment.searchByName(mEtName.getText().toString());
                break;
            default:
                break;
        }
    }
}
