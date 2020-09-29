/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ilhem
 */
public class JsonParser {
    private double[][] arraycount;
    private String[] arraytime;
    private boolean low_performance=false;

    public void setArraycount(double[][] arraycount) {
        this.arraycount = arraycount;
    }

    public void setArraytime(String[] arraytime) {
        this.arraytime = arraytime;
    }

    public void setLow_performance(boolean low_performance) {
        this.low_performance = low_performance;
    }

    public double[][] getArraycount() {
        return arraycount;
    }

    public String[] getArraytime() {
        return arraytime;
    }

    public boolean isLow_performance() {
        return low_performance;
    }
    
    //Constructor
       public JsonParser() {
        
    }
    
    //execute the aggregation query on elastic search database and convert json result to timearray and countarray
    public void JsonToArray(String index_name) throws IOException{
         ElasticsearchQuery eq= new ElasticsearchQuery();
        String s1= new String();
        s1 = eq.AggQuery(index_name);
        JSONParser jsonParser = new JSONParser();
		Object object;
                

		try {

			object = jsonParser.parse(s1);
			JSONObject jsonObject = (JSONObject) object;

			long took = (long) jsonObject.get("took");
			System.out.println("took: " + took);

			Boolean timeout = (Boolean) jsonObject.get("timed_out");
                        System.out.println("timed_out: " + timeout);
			JSONObject aggregations = (JSONObject) jsonObject.get("aggregations");
			System.out.println("aggregations: " + aggregations);
                      
                        JSONObject agg1 = (JSONObject) aggregations.get("agg1");
			System.out.println("agg1: " + agg1);
			
			JSONArray buckets = (JSONArray) agg1.get("buckets");
			System.out.println("\tbuckets: " + buckets);

			Object composeObj = jsonObject.get("compose");
			JSONObject jsonObject1 = (JSONObject) composeObj;
			Iterator itr1 = buckets.iterator();
                        int size=0;
                        while (itr1.hasNext()) {
                            System.out.println("entrer dans la boucle");
                        size++;
                        
                        Object obj=itr1.next();
                        }
                        
                        System.out.println("size" + size);
                       
                         Iterator itr2 = buckets.iterator();
      arraycount = new double[1][size];
       
       arraytime = new String[size];
int i=0;
			while (itr2.hasNext()) {

				Object slide = itr2.next();
				JSONObject jsonObject2 = (JSONObject) slide;
				
                               long doc_count = (long) jsonObject2.get("doc_count");

                               String key = (String) jsonObject2.get("key");
				//String date_of_birth = (String) info.get("date_of_birth");
				//String name_id = (String) info.get("name_id");

				System.out.println("\t\tcount: " + doc_count);
                                arraycount[0][i]=doc_count;
                                
				System.out.println("\t\tkey: " + key);
                                arraytime[i]=key;
                                System.out.println("key" + arraytime[i]);
                                System.out.println("count" + arraycount[0][i]);
                                i++;
                                System.out.println("i" +i);
                                
                            
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
   
    }
 
    
    
    public double[] MaxMinArraycount(){
        double[] MaxMin = new double[2];
        low_performance=false;
        double max = arraycount[0][0];
    double min = arraycount[0][0];
    // iterate and compare from array index 1
    for(int i = 1; i < arraycount[0].length; i++){
        if(max < arraycount[0][i]){
        max = arraycount[0][i];
        
        }else if(min > arraycount[0][i]){
        min = arraycount[0][i];
           }		   		   
    }
    
    MaxMin[0]=max;
        MaxMin[1]=min;

    if (min < 200.0){
        System.out.println("low performance ");
        low_performance=true;
    }
    return MaxMin;
    }
    
    public boolean IsPerformant(){
        return low_performance;
    }
    
    
    public double calculate_average(){
        int i;
        double s=0.0;
        for( i=0;i<arraycount[0].length;i++){
            s=s+arraycount[0][i];
        }
        double a=s / arraycount[0].length;
        return a ;
        
    }
   
}
