package com.example.fetch;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new FetchJsonTask().execute("https://fetch-hiring.s3.amazonaws.com/hiring.json");
    }

    private class FetchJsonTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String url = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        if (!line.equals("[") && !line.equals("]")) {
                            if (line.charAt(line.length() - 1) != ',') {
                                continue;
                            } else {
                                line = (line.substring(0, line.length() - 1));
                            }
                            stringBuilder.append(line+'\n');
                        }
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String jsonString) {
            String[] jsonArr = jsonString.split("\n");
            ArrayList<User> users = new ArrayList<User>();
            ArrayList<User> oldUser = new ArrayList<User>();

            for (int i = 0; i < jsonArr.length; i++) {
                String json = jsonArr[i];
                try {
                    JSONObject jsonObj = new JSONObject(json);

                    String newID = jsonObj.get("id").toString();
                    String newListID = jsonObj.get("listId").toString();
                    String newName = jsonObj.get("name").toString();
                    User user = new User(newID, newListID, newName);

                    users.add(user);
                    oldUser.add(user);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
            RecyclerView recyclerView = findViewById(R.id.recycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            UserAdapter adapter = new UserAdapter(users);
            recyclerView.setAdapter(adapter);

            CheckBox sort = findViewById(R.id.sorting);
            CheckBox filter = findViewById(R.id.filtering);
            CheckBox group1 = findViewById(R.id.group1);
            CheckBox group2 = findViewById(R.id.group2);
            CheckBox group3 = findViewById(R.id.group3);
            CheckBox group4 = findViewById(R.id.group4);

            sort.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        RecyclerView recyclerView = findViewById(R.id.recycler);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        UserAdapter adapter = new UserAdapter(filterOut(users,filter.isChecked(),sort.isChecked(),group1.isChecked(),group2.isChecked(),group3.isChecked(),group4.isChecked()));
                        recyclerView.setAdapter(adapter);
                }
            });

            filter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    RecyclerView recyclerView = findViewById(R.id.recycler);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    UserAdapter adapter = new UserAdapter(filterOut(users,filter.isChecked(),sort.isChecked(),group1.isChecked(),group2.isChecked(),group3.isChecked(),group4.isChecked()));
                    recyclerView.setAdapter(adapter);
                }
            });

            group1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    RecyclerView recyclerView = findViewById(R.id.recycler);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    UserAdapter adapter = new UserAdapter(filterOut(users,filter.isChecked(),sort.isChecked(),group1.isChecked(),group2.isChecked(),group3.isChecked(),group4.isChecked()));
                    recyclerView.setAdapter(adapter);
                }
            });

            group2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    RecyclerView recyclerView = findViewById(R.id.recycler);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    UserAdapter adapter = new UserAdapter(filterOut(users,filter.isChecked(),sort.isChecked(),group1.isChecked(),group2.isChecked(),group3.isChecked(),group4.isChecked()));
                    recyclerView.setAdapter(adapter);
                }
            });

            group3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    RecyclerView recyclerView = findViewById(R.id.recycler);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    UserAdapter adapter = new UserAdapter(filterOut(users,filter.isChecked(),sort.isChecked(),group1.isChecked(),group2.isChecked(),group3.isChecked(),group4.isChecked()));
                    recyclerView.setAdapter(adapter);
                }
            });

            group4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    RecyclerView recyclerView = findViewById(R.id.recycler);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    UserAdapter adapter = new UserAdapter(filterOut(users,filter.isChecked(),sort.isChecked(),group1.isChecked(),group2.isChecked(),group3.isChecked(),group4.isChecked()));
                    recyclerView.setAdapter(adapter);
                }
            });
        }

        public ArrayList<User> filterOut(ArrayList<User> users, boolean filter, boolean sort, boolean group1, boolean group2, boolean group3, boolean group4) {
            ArrayList<User> ret_1 = new ArrayList<>();
            ArrayList<User> ret_2 = new ArrayList<>();
            if (filter) {
                for (int i=0; i<users.size(); i++) {
                    if (!users.get(i).getName().equals("null") && !users.get(i).getName().equals("")) {
                        ret_1.add(users.get(i));
                    }
                }
                if (sort) {
                    Collections.sort(ret_1);
                    if (group1 || group2 || group3 || group4) {
                        if (group1 && !group2 && !group3 && !group4) {
                            for (int i=0; i<ret_1.size(); i++) {
                                Log.i("MainActivity",ret_1.get(i).getListID());
                                if (ret_1.get(i).getListID().equals("1")) {
                                    ret_2.add(ret_1.get(i));
                                }
                            }
                        } else if (group2 && !group1 && !group3 && !group4) {
                            for (int i=0; i<ret_1.size(); i++) {
                                Log.i("MainActivity",ret_1.get(i).getListID());
                                if (ret_1.get(i).getListID().equals("2")) {
                                    ret_2.add(ret_1.get(i));
                                }
                            }
                        } else if (group3 && !group1 && !group2 && !group4) {
                            for (int i=0; i<ret_1.size(); i++) {
                                Log.i("MainActivity",ret_1.get(i).getListID());
                                if (ret_1.get(i).getListID().equals("3")) {
                                    ret_2.add(ret_1.get(i));
                                }
                            }
                        } else if (group4 && !group1 && !group2 && !group3){
                            for (int i=0; i<ret_1.size(); i++) {
                                Log.i("MainActivity",ret_1.get(i).getListID());
                                if (ret_1.get(i).getListID().equals("4")) {
                                    ret_2.add(ret_1.get(i));
                                }
                            }
                        }
                        else {
                            return ret_2;
                        }
                    } else {
                        ret_2.addAll(ret_1);
                    }
                }
                else {
                    if (group1 || group2 || group3 || group4) {
                        if (group1 && !group2 && !group3 && !group4) {
                            for (int i=0; i<ret_1.size(); i++) {
                                Log.i("MainActivity",ret_1.get(i).getListID());
                                if (ret_1.get(i).getListID().equals("1")) {
                                    ret_2.add(ret_1.get(i));
                                }
                            }
                        } else if (group2 && !group1 && !group3 && !group4) {
                            for (int i=0; i<ret_1.size(); i++) {
                                Log.i("MainActivity",ret_1.get(i).getListID());
                                if (ret_1.get(i).getListID().equals("2")) {
                                    ret_2.add(ret_1.get(i));
                                }
                            }
                        } else if (group3 && !group1 && !group2 && !group4) {
                            for (int i=0; i<ret_1.size(); i++) {
                                Log.i("MainActivity",ret_1.get(i).getListID());
                                if (ret_1.get(i).getListID().equals("3")) {
                                    ret_2.add(ret_1.get(i));
                                }
                            }
                        } else if (group4 && !group1 && !group2 && !group3){
                            for (int i=0; i<ret_1.size(); i++) {
                                Log.i("MainActivity",ret_1.get(i).getListID());
                                if (ret_1.get(i).getListID().equals("4")) {
                                    ret_2.add(ret_1.get(i));
                                }
                            }
                        } else {
                            return ret_2;
                        }
                    } else {
                        ret_2.addAll(ret_1);
                    }
                }
            } else {
                ret_1.addAll(users);
                if (sort) {
                    Collections.sort(ret_1);
                    if (group1 || group2 || group3 || group4) {
                        if (group1 && !group2 && !group3 && !group4) {
                            for (int i = 0; i < ret_1.size(); i++) {
                                Log.i("MainActivity", ret_1.get(i).getListID());
                                if (ret_1.get(i).getListID().equals("1")) {
                                    ret_2.add(ret_1.get(i));
                                }
                            }
                        } else if (group2 && !group1 && !group3 && !group4) {
                            for (int i = 0; i < ret_1.size(); i++) {
                                Log.i("MainActivity", ret_1.get(i).getListID());
                                if (ret_1.get(i).getListID().equals("2")) {
                                    ret_2.add(ret_1.get(i));
                                }
                            }
                        } else if (group3 && !group1 && !group2 && !group4) {
                            for (int i = 0; i < ret_1.size(); i++) {
                                Log.i("MainActivity", ret_1.get(i).getListID());
                                if (ret_1.get(i).getListID().equals("3")) {
                                    ret_2.add(ret_1.get(i));
                                }
                            }
                        } else if (group4 && !group1 && !group2 && !group3) {
                            for (int i = 0; i < ret_1.size(); i++) {
                                Log.i("MainActivity", ret_1.get(i).getListID());
                                if (ret_1.get(i).getListID().equals("4")) {
                                    ret_2.add(ret_1.get(i));
                                }
                            }
                        }
                        else {
                            return ret_2;
                        }
                    } else {
                        ret_2.addAll(ret_1);
                    }
                } else {
                    if (group1 || group2 || group3 || group4) {
                        if (group1 && !group2 && !group3 && !group4) {
                            for (int i = 0; i < ret_1.size(); i++) {
                                Log.i("MainActivity", ret_1.get(i).getListID());
                                if (ret_1.get(i).getListID().equals("1")) {
                                    ret_2.add(ret_1.get(i));
                                }
                            }
                        } else if (group2 && !group1 && !group3 && !group4) {
                            for (int i = 0; i < ret_1.size(); i++) {
                                Log.i("MainActivity", ret_1.get(i).getListID());
                                if (ret_1.get(i).getListID().equals("2")) {
                                    ret_2.add(ret_1.get(i));
                                }
                            }
                        } else if (group3 && !group1 && !group2 && !group4) {
                            for (int i = 0; i < ret_1.size(); i++) {
                                Log.i("MainActivity", ret_1.get(i).getListID());
                                if (ret_1.get(i).getListID().equals("3")) {
                                    ret_2.add(ret_1.get(i));
                                }
                            }
                        } else if (group4 && !group1 && !group2 && !group3){
                            for (int i = 0; i < ret_1.size(); i++) {
                                Log.i("MainActivity", ret_1.get(i).getListID());
                                if (ret_1.get(i).getListID().equals("4")) {
                                    ret_2.add(ret_1.get(i));
                                }
                            }
                        } else {
                            return ret_2;
                        }
                    } else {
                        ret_2.addAll(ret_1);
                    }
                }
            }
           return ret_2;
        }
    }
}