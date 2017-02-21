/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upload;

/**
 *
 * @author joaosantos
 */
public class JSonCreate {

    public static void main(String a[]) throws JSONException {

        JSONObject obj = new JSONObject();
        JSONObject obj2 = new JSONObject();
        JSONArray jsa = new JSONArray();


        obj.put("name", "uu");
        obj.put("size", "678887");
        obj.put("url", "upload?getfile=uu");
        obj.put("thumbnail_url", "upload?getthumb=uu");
        obj.put("delete_url", "upload?delfile=uu");
        obj.put("delete_type", "GET");

        obj2.put("name", "uu");
        obj2.put("size", "678887");
        obj2.put("url", "upload?getfile=uu");
        obj2.put("thumbnail_url", "upload?getthumb=uu");
        obj2.put("delete_url", "upload?delfile=uu");
        obj2.put("delete_type", "GET");
        jsa.put(obj);
        jsa.put(obj2);
        System.out.println(jsa.toString());

    }

}
