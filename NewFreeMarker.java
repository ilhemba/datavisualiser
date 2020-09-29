/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

/**
 *
 * @author ilhem
 */
public class NewFreeMarker {
    public NewFreeMarker(String encode1,String encode2,String encode3,String logoEncoding, double min1, double max1, double avr1,boolean low_perf1, double min2, double max2, double avr2,boolean low_perf2,double min3, double max3, double avr3,boolean low_perf3,String perf_threshold_trrecep,String perf_threshold_opreq,String perf_threshold_track,String Test_Type1,String Test_Type2,String Test_Type3) throws IOException, TemplateException{
/*    BufferedImage img = c.image;
File f = new File("MyFile.png");
ImageIO.write(img, "PNG", f);
  
  */  
   //--------------------1             
                // Create your Configuration instance, and specify if up to what FreeMarker
// version (here 2.3.29) do you want to apply the fixes that are not 100%
// backward-compatible. See the Configuration JavaDoc for details.


Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
System.out.println("cfg "+ cfg);
//System.out.println("ggg");
// Specify the source where the template files come from. Here I set a
// plain directory for it, but non-file-system sources are possible too:
//cfg.setDirectoryForTemplateLoading(new File("OtherSources"));
cfg.setDirectoryForTemplateLoading(new File("src\\main\\resources"));


// From here we will set the settings recommended for new projects. These
// aren't the defaults for backward compatibilty.

// Set the preferred charset template files are stored in. UTF-8 is
// a good choice in most applications:
cfg.setDefaultEncoding("UTF-8");

// Sets how errors will appear.
// During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

// Don't log exceptions inside FreeMarker that it will thrown at you anyway:
cfg.setLogTemplateExceptions(false);

// Wrap unchecked exceptions thrown during template processing into TemplateException-s:
cfg.setWrapUncheckedExceptions(true);

// Do not fall back to higher scopes when reading a null loop variable:
cfg.setFallbackOnNullLoopVariable(false);

//----------------2

// Create the root hash. We use a Map here, but it could be a JavaBean too.
Map<String, Object> root = new HashMap<>();
if (low_perf1 == true){
  root.put("interpretation1", "StreamGateway performance is low. she is below the normal threshold which is " + perf_threshold_trrecep + " trades processed per second");  
}
else {
    root.put("interpretation1", "StreamGateway performance is above the normal threshold which is " + perf_threshold_trrecep + " trades processed per second");  
}
if (low_perf1 == true){
  root.put("interpretation2", "StreamGateway performance is low. she is below the normal threshold which is " + perf_threshold_opreq + " trades processed per second");  
}
else {
    root.put("interpretation2", "StreamGateway performance is above the normal threshold which is " + perf_threshold_opreq + " trades processed per second");  

}
if (low_perf3 == true){
  root.put("interpretation3", "StreamGateway performance is low. she is below the normal threshold which is " + perf_threshold_track + " trades processed per second");  
}
else {
    root.put("interpretation3", "StreamGateway performance is above the normal threshold which is " + perf_threshold_track + " trades processed per second");  

}
root.put("test_type1",Test_Type1);
root.put("test_type2",Test_Type2);
root.put("test_type3",Test_Type3);
System.out.print("root" +root);

  root.put("max1", max1);
  root.put("avr1", avr1);
  root.put("min1", min1);
  root.put("image1",encode1);
  
  root.put("max2", max2);
  root.put("avr2", avr2);
  root.put("min2", min2);
  root.put("image2",encode2);
  
  root.put("max3", max3);
  root.put("avr3", avr3);
  root.put("min3", min3);
  root.put("image3",encode3);
System.out.print("root" +root);
// Put string "user" into the root
//root.put("user", "Big Joehhh"); 
//root.put("image","C:/Users/ilhem/Downloads/f3.jpg");

//root.put("image",f);
/*root.put("image",c.image.getGraphics());
//System.out.println("f " + f);
*/
/*ImageIO.write(c.image,"png",new File("tmpImage.png"));
byte[] imageBytes = Files.readAllBytes(Paths.get("tmpImage.png"));

Base64.Encoder encoder = Base64.getEncoder();

String encoding = "data:image/png;base64," + encoder.encodeToString(imageBytes);
*/
root.put("fislogo",logoEncoding);
 


// Create the "latestProduct" hggggyhyash. We use a JavaBean here, but it could be a Map too.
/*Product latest = new Product();
latest.setUrl("products/greenmouse.html");
latest.setName("green mouse");
// and put it into the root
root.put("latestProduct", latest);*/



//--------------------3
//si nn nbadlouha ftlh :p
Template temp = cfg.getTemplate("newfile.ftlh");
//Template temp = cfg.getTemplate("report.ftlh");
System.out.println("template  " + temp);

//-----------------------4
Writer out = new OutputStreamWriter(System.out);
temp.process(root, out);



//--------------------------5

Writer file = new FileWriter (new File("C:\\Users\\ilhem\\Documents\\NetBeansProjects\\mavenproject88\\target\\htmlReport.html"));
temp.process(root, file); 



//System.out.println(out);
    
}
}