package com.example.appphoto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.widget.GridView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class ArticleData extends AsyncTask<String, String, String> {
    private static Context context;
    private GridView gridView;
    public static ArticleList data;

    public ArticleData(Context context, GridView gridView){
        this.context = context;
        this.gridView=gridView;
    }
    public static Article getphotoFromId(int id){
        for (int i=0; i< data.getArticles().size();i++){
            if(data.getArticles().get(i).getArticle_id() == id){
                return data.getArticles().get(i);
            }
        }
        return null;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("https://raw.githubusercontent.com/thanhdnh/json/main/products.json");
            connection  = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line ="";
            while ((line = reader.readLine())!=null){
                buffer.append(line+"\n");
            }
            return buffer.toString();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (connection != null)
                    connection.disconnect();
                if (reader != null)
                    reader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        Gson gson = new Gson();
        data = gson.fromJson(result, (Type) ArticleList.class);
        ArticleAdapter adapter = new ArticleAdapter(data.getArticles(),context);
        gridView.setAdapter(adapter);
    }
   /* public static void init(Context context) {
        ArticleData.context = context;
    }

    public static ArrayList<Article> getPhotos() {
        ArrayList<Article> articles = new ArrayList<>();

        try {
            String jsonString = loadJSONFromAsset("Photodata.json");
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String source = jsonObject.getString("source_photo");
                String title = jsonObject.getString("title");
                String description = jsonObject.getString("description");
                articles.add(new Article(id, source, title, description));
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return articles;
    }

    private static String loadJSONFromAsset(String fileName) throws IOException {
        String jsonString;
        InputStream inputStream = context.getAssets().open(fileName);
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        jsonString = new String(buffer, "UTF-8");
        return jsonString;
    }
    public static Article getPhotoById(int id) {
        Article article = null;

        try {
            String jsonString = loadJSONFromAsset("Photodata.json");
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int photoId = jsonObject.getInt("id");

                if (photoId == id) {
                    String source = jsonObject.getString("source_photo");
                    String title = jsonObject.getString("title");
                    String description = jsonObject.getString("description");
                    article = new Article(id, source, title, description);
                    break;
                }
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return article;
    }*/
}
