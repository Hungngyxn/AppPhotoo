package com.example.appphoto;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PhotoData {
    /*public static ArrayList<Photo> generatePhotoData(){
        ArrayList<Photo> photos= new ArrayList<>();
         photos.add(new Photo(0,"https://thuviendohoa.vn/2020/upload/images/items/hinh-anh-qua-dua-dep-2363_9.jpg", "qua du'a","qua dua' ne`"));
        photos.add(new Photo(1,"https://xebanhmithonhiky.vn/wp-content/uploads/2020/06/qua-dua.png", "qua du`a","qua dua` ne`"));
        photos.add(new Photo(2,"https://www.hyponex.co.jp/yasai_daijiten/websys/wp-content/uploads/2020/08/%E3%82%B9%E3%82%A4%E3%82%ABA-676x448.jpg", "qua dua","qua dua ne`"));
        photos.add(new Photo(3,"https://3.bp.blogspot.com/-lbuTZ1zgAnA/VoSZkp9LxeI/AAAAAAAAC68/vwe6s_BU52Y/s1600/fresas-en-una-cesta-156329.jpg", "qua dau","qua dau ne`"));
        photos.add(new Photo(4,"https://www.shokuzai-miru.net/files/user/202002281105_1.jpg", "qua quat","qua quat ne`"));
        photos.add(new Photo(5,"https://thuviendohoa.vn/2020/upload/images/items/hinh-anh-qua-dua-dep-2363_9.jpg", "qua dua","qua dua' ne`"));
        photos.add(new Photo(6,"https://thuviendohoa.vn/2020/upload/images/items/hinh-anh-qua-dua-dep-2363_9.jpg", "qua dua","qua dua' ne`"));

        return photos;
    }
    public static Photo getphotoFromId(int id){
        ArrayList<Photo> phs = generatePhotoData();
        for (int i=0; i< phs.size();i++){
            if(phs.get(i).getId() == id){
                return phs.get(i);
            }
        }
        return null;
    }*/
    private static Context context;

    public static void init(Context context) {
        PhotoData.context = context;
    }

    public static ArrayList<Photo> getPhotos() {
        ArrayList<Photo> photos = new ArrayList<>();

        try {
            String jsonString = loadJSONFromAsset("Photodata.json");
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String source = jsonObject.getString("source_photo");
                String title = jsonObject.getString("title");
                String description = jsonObject.getString("description");
                photos.add(new Photo(id, source, title, description));
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return photos;
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
    public static Photo getPhotoById(int id) {
        Photo photo = null;

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
                    photo = new Photo(id, source, title, description);
                    break;
                }
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return photo;
    }
}
