package View;
import Controller.JsonParser;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
 

 
public class WritingCSV
{
    public WritingCSV(){
    
}
   public void CSVWriter(double max, double min, double avr, boolean low_perf,JsonParser j,String Test_Type) throws IOException
   {System.out.println("Test_Type csv" + Test_Type);
      String csv = "data.csv";
      String csv2= "C:\\Users\\ilhem\\Documents\\NetBeansProjects\\mavenproject88\\target\\" + Test_Type + ".csv";
      
      CSVWriter writer = new CSVWriter(new FileWriter(csv2));
        
      //Create record
     
          
      String s1=new String();
            s1="time "+" , " + " nb of treated trade "+" max "+","+"min" + "," + "avr";
       String [] record1 = s1.split(",");
        writer.writeNext(record1);
      double[][] arraycount;
     String[] arraytime;
     arraycount=j.getArraycount();
     arraytime=j.getArraytime();
        String s2=new String();
              s2=arraytime [0] + "," +  arraycount[0][0] + max + " , " + "min" + "," + avr ;
                      String [] record2 = s2.split(",");
        writer.writeNext(record2);
       /* 
        String s3=new String();
              s3="avr"+ ","+avr;
       String [] record3 = s3.split(",");
        writer.writeNext(record3);
        
           String s4=new String();
              s4="low_perf"+ ","+low_perf;
       String [] record4 = s4.split(",");
        writer.writeNext(record4);
        */
        String s5=new String();
        s5="";
       
        for (int i=1; i<arraycount[0].length;i++){
            s5=arraytime [i] +arraycount[0][i];
            String [] record5 = s5.split(",");
        writer.writeNext(record5);
        }
          
      //close the writer
      writer.close();
   }
}