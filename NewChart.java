package View;
import Controller.JsonParser;

import com.itextpdf.text.DocumentException;

import freemarker.template.TemplateException;
import java.awt.Font;
import java.awt.Graphics2D;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.AreaRendererEndType;
import org.jfree.chart.renderer.category.AreaRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;


import java.awt.geom.Rectangle2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import javax.imageio.ImageIO;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;





public  class NewChart{
      public static final double DEFAULT_AXIS_MARGIN=0.00;
      public  double CategoryMargin=0.1;
      //public String encoding;
      //public String interpretation;
      public BufferedImage image1;
      public BufferedImage image2;
      public BufferedImage image3;
      private String interpretation1,interpretation2,interpretation3;
    public NewChart(){
        
    }
    public NewChart(String index_name_trrecep, String index_name_opreq, String index_name_track, String perf_threshold_trrecep, String perf_threshold_opreq, String perf_threshold_track, String Test_Type1,String Test_Type2,String Test_Type3) throws IOException, FileNotFoundException, DocumentException, TemplateException {

  
        CategoryDataset dataset1 = createDataset(index_name_trrecep);
        CategoryDataset dataset2 = createDataset(index_name_opreq);
        CategoryDataset dataset3 = createDataset(index_name_track);
        System.out.println("index_name_trrecep" + index_name_trrecep);
        JFreeChart chart1 = createChart(dataset1);
        JFreeChart chart2 = createChart(dataset2);
        JFreeChart chart3 = createChart(dataset3);
        
        image1=draw(chart1,1200,1200);  
        image2=draw(chart2,1200,1200);  
        image3=draw(chart3,1200,1200); 
        
        JsonParser j1=new JsonParser();
   
        JsonParser j2=new JsonParser();
        JsonParser j3=new JsonParser();
  
        j1.JsonToArray(index_name_trrecep);  
        j2.JsonToArray(index_name_opreq);
        j3.JsonToArray(index_name_track);
        
        double avr1=j1.calculate_average();
        System.out.println("avr11111" + avr1);
        double max1=j1.MaxMinArraycount()[0];
        System.out.println("max1111" + max1);
        double min1=j1.MaxMinArraycount()[1];
        System.out.println("min1111" + min1);
        boolean low_perf1=j1.IsPerformant();
        
        double avr2=j2.calculate_average();
        double max2=j2.MaxMinArraycount()[0];
        double min2=j2.MaxMinArraycount()[1];
        boolean low_perf2=j2.IsPerformant();
        
        double avr3=j3.calculate_average();
        double max3=j3.MaxMinArraycount()[0];
        double min3=j3.MaxMinArraycount()[1];
        boolean low_perf3=j3.IsPerformant();
        
      
        generatePDFReport(min1,max1,avr1,low_perf1,min2,max2,avr2,low_perf2,min3,max3,avr3,low_perf3,perf_threshold_trrecep,perf_threshold_opreq,perf_threshold_track,Test_Type1,Test_Type2,Test_Type3);


      generateHTMLReport(min1,max1,avr1,low_perf1,min2,max2,avr2,low_perf2,min3,max3,avr3,low_perf3,perf_threshold_trrecep,perf_threshold_opreq,perf_threshold_track,Test_Type1,Test_Type2,Test_Type3);
      WritingCSV w1=new WritingCSV();
w1.CSVWriter(max1, min1, avr1, low_perf1, j1, Test_Type1);
 w1.CSVWriter(max2, min2, avr2, low_perf2, j2, Test_Type2);
 w1.CSVWriter(max3, min3, avr3, low_perf3, j3, Test_Type3);
    /*     
        ChartPanel chartPanel = new ChartPanel(chart);
        //chartPanel.setSize(5000, 5000);
       // The first step is to avoid scaling up a small chart.
// The value of "MaximumDrawWidth" should be large, for instance 4000
chartPanel.setMaximumDrawWidth(4000);  
// The second step is to set the width of a chart panel.
chartPanel.setPreferredSize(new Dimension(1200,500));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        chartPanel.setBackground(Color.WHITE);
        
        JPanel jp1=new JPanel();
        JPanel jp2=new JPanel();
         tf=new TextField(160); 
        b=new JButton("Play");    
		b.setBounds(100,100,140, 40); 
 /*               b.addActionListener(new ActionListener()
{
  public void actionPerformed(ActionEvent e)
  {
      interpretation=tf.getText();
      System.out.print("interpretation1 " + interpretation);
    // display/center the jdialog when the button is pressed
    JDialog d = new JDialog(jf, "Hello", true);
    d.setLocationRelativeTo(jf);
    d.setVisible(true);
  }
});*/
   
    /*    System.out.println("layout"+this.getLayout());
        //jf.setLayout(new BorderLayout());
       //jf.setLayout(new BorderLayout());
       jf.setLayout(new FlowLayout());
       
        jp1.add(tf);
         jp1.add(b);
        
        
        //jf.add(chartPanel);
        //chartPanel.setSize(10000, 10000);
      jp2.add(chartPanel);
      ///jp2.setSize(1000, 1000);
        
        //
        jf.add(jp2);
        jf.add(jp1);      
        tb=new TextBox();
        
          //add(chartPanel,BorderLayout.NORTH);


/*JScrollBar jsb ; 
          jsb = new JScrollBar(HORIZONTAL, 1, 1, 10, 30);
          jsb.add(ta);
          jsb.setSize(200, 200);
//jsp.setComponentOrientation(HORIZONTAL);
add(jsb, BorderLayout.NORTH);
*/

/*

       jf.pack();
        jf.setSize(1500, 1200);
        jf.setTitle("Area chart");
        jf.setLocationRelativeTo(null);
        
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        */
        // image
         
 
  }
    public String encodelogo() throws IOException{
    BufferedImage fislogo = ImageIO.read(new File("C:/Users/ilhem/Documents/NetBeansProjects/mavenproject88/OtherSources/images/fis.png"));
ImageIO.write(fislogo,"png",new File("tmpImage.png"));
byte[] imageBytes = Files.readAllBytes(Paths.get("tmpImage.png"));

Base64.Encoder encoder = Base64.getEncoder();

String encoding2 = "data:image/png;base64," + encoder.encodeToString(imageBytes);
          return encoding2;
    }
    public String encodechart(BufferedImage image) throws IOException{
         
 ImageIO.write(image,"png",new File("tmpImage.png"));
byte[] imageBytes = Files.readAllBytes(Paths.get("tmpImage.png"));

Base64.Encoder encoder = Base64.getEncoder();

String encoding = "data:image/png;base64," + encoder.encodeToString(imageBytes);
//getIntrepretation();


          return encoding;
    }
    
    
    public void generatePDFReport(double min1,double max1,double avr1,boolean low_perf1,double min2,double max2,double avr2,boolean low_perf2,double min3,double max3,double avr3,boolean low_perf3,String perf_threshold_trrecep,String perf_threshold_opreq, String perf_threshold_track,String Test_Type1,String Test_Type2,String Test_Type3) throws IOException, FileNotFoundException, DocumentException{
         String encode1=encodechart(image1);
         String encode2=encodechart(image2);
         String encode3=encodechart(image3);
         String logoEncoding=encodelogo();
                      
         NewThymeleaf nt=new NewThymeleaf(encode1,encode2,encode3,logoEncoding,min1,max1,avr1,low_perf1,min2,max2,avr2,low_perf2,min3,max3,avr3,low_perf3,perf_threshold_trrecep,perf_threshold_opreq,perf_threshold_track,Test_Type1,Test_Type2, Test_Type3);
             
    }
      public void generateHTMLReport(double min1,double max1,double avr1,boolean low_perf1,double min2,double max2,double avr2,boolean low_perf2,double min3,double max3,double avr3,boolean low_perf3,String perf_threshold_trrecep,String perf_threshold_opreq, String perf_threshold_track,String Test_Type1,String Test_Type2,String Test_Type3) throws IOException, FileNotFoundException, DocumentException, TemplateException{
         String encode1=encodechart(image1);
         String encode2=encodechart(image2);
         String encode3=encodechart(image3);
         String logoEncoding=encodelogo();
         
         NewFreeMarker nf=new NewFreeMarker(encode1,encode2,encode3,logoEncoding,min1,max1,avr1,low_perf1,min2,max2,avr2,low_perf2,min3,max3,avr3,low_perf3,perf_threshold_trrecep,perf_threshold_opreq,perf_threshold_track,Test_Type1,Test_Type2, Test_Type3);
                       
   
    }
      
   
   /* public void getIntrepretation(){
         b.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        try {
            try {
                // delegate to event handler method
                buttonActionPerformed(evt);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NewChart.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(NewChart.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(NewChart.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TemplateException ex) {
            Logger.getLogger(NewChart.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

           private void buttonActionPerformed(ActionEvent evt) throws IOException, TemplateException, FileNotFoundException, DocumentException {
               interpretation=tf.getText();
               System.out.println("interpretation 0 " + interpretation);
                       System.out.println("buttonactionperformed2 "+tf.getText());
                       tf.setText(interpretation+"hh");
                         String encode=encodechart(image);
    

                         String logoEncoding=encodelogo();
                      NewFreeMarker nf=new NewFreeMarker(encode,interpretation,logoEncoding,min,max,avr,low_perf);
                       
                       //
                    NewThymeleaf nt=new NewThymeleaf(encode,interpretation,logoEncoding,min,max,avr,low_perf);
              // throw new UnsupportedOperationException("Not supported yet."); 
//To change body of generated methods, choose Tools | Templates.

           }
       }); 
             System.out.println("interpretation 1 " + tf.getText());            
          
    }*/

    /*private NewChart initUI() nnnnnnnnnthrows IOException {
       jf=new JFrame();
        
        //System.out.println("hi");

        CategoryDataset dataset = createDataset();

        JFreeChart chart = createChart(dataset);
        
         
        ChartPanel chartPanel = new ChartPanel(chart);
        //chartPanel.setSize(5000, 5000);
       // The first step is to avoid scaling up a small chart.
// The value of "MaximumDrawWidth" should be large, for instance 4000
chartPanel.setMaximumDrawWidth(4000);  
// The second step is to set the width of a chart panel.
chartPanel.setPreferredSize(new Dimension(1200,500));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        chartPanel.setBackground(Color.WHITE);
        
        JPanel jp1=new JPanel();
        JPanel jp2=new JPanel();
        TextField tf=new TextField(160); 
        JButton b=new JButton("Play");    
		b.setBounds(100,100,140, 40); 
 /*               b.addActionListener(new ActionListener()
{
  public void actionPerformed(ActionEvent e)
  {
      interpretation=tf.getText();
      System.out.print("interpretation1 " + interpretation);
    // display/center the jdialog when the button is pressed
    JDialog d = new JDialog(jf, "Hello", true);
    d.setLocationRelativeTo(jf);
    d.setVisible(true);
  }
});*/
/*		b.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        // delegate to event handler method
        buttonActionPerformed(evt);
       
    }

           private void buttonActionPerformed(ActionEvent evt) {
               interpretation=tf.getText();
                       System.out.println("buttonactionperformed2 "+tf.getText());
               throw new UnsupportedOperationException("Not supported yet."); 
//To change body of generated methods, choose Tools | Templates.

           }
       });    
        System.out.println("layout"+this.getLayout());
        //jf.setLayout(new BorderLayout());
       //jf.setLayout(new BorderLayout());
       jf.setLayout(new FlowLayout());
       
        jp1.add(tf);
         jp1.add(b);
        
        
        //jf.add(chartPanel);
        //chartPanel.setSize(10000, 10000);
      jp2.add(chartPanel);
      ///jp2.setSize(1000, 1000);
        
        //
        jf.add(jp2);
        jf.add(jp1);
      
        tb=new TextBox();
        
          //add(chartPanel,BorderLayout.NORTH);

JTextArea ta=new JTextArea(50,200);
ta.setText("hbhec cfevefv rvrtvrt EVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAANNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNcervcrt");

/*JScrollBar jsb ; 
          jsb = new JScrollBar(HORIZONTAL, 1, 1, 10, 30);
          jsb.add(ta);
          jsb.setSize(200, 200);
//jsp.setComponentOrientation(HORIZONTAL);
add(jsb, BorderLayout.NORTH);
*/



 /*       jf.pack();
        jf.setSize(1500, 1200);
        jf.setTitle("Area chart");
        jf.setLocationRelativeTo(null);
        
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // image
         
 /*image=draw(chart,1200,1200);
 
 
 
 ImageIO.write(image,"png",new File("tmpImage.png"));
byte[] imageBytes = Files.readAllBytes(Paths.get("tmpImage.png"));

Base64.Encoder encoder = Base64.getEncoder();

encoding = "data:image/png;base64," + encoder.encodeToString(imageBytes);
        return this;
    }
*/
    private CategoryDataset createDataset(String index_name) throws IOException {
JsonParser j=new JsonParser();

j.JsonToArray(index_name);

        double[][] data = j.getArraycount();
        CategoryDataset dataset;
        String[] arraytime=j.getArraytime();
        int k=0;
       System.out.println(" hello" + arraytime[9]);
       System.out.println(" data" + data[0][1]);
       System.out.println("data lenght" + data.length);
       System.out.println("time leght" + arraytime[0]);
        dataset = DatasetUtilities.createCategoryDataset(
                new String[]{"time"},arraytime,data
        );
      
        System.out.println("dataset" + dataset);
        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) throws IOException, FileNotFoundException, DocumentException, TemplateException {




        JFreeChart chart = ChartFactory.createAreaChart(
                "SGW Performance Chart",
                "Time",
                "Trade count",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                true
        );
        
  


         


        CategoryPlot plot = (CategoryPlot) chart.getPlot();
   
        CategoryAxis axis = plot.getDomainAxis();
//System.out.println(axis.getMaximumCategoryLabelLines());
axis.setMaximumCategoryLabelLines(10000);
//System.out.println(axis.isVisible());
axis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

    CategoryPlot p = chart.getCategoryPlot(); 
         ValueAxis axis2 = p.getRangeAxis();

        Font font = new Font("Dialog", Font.PLAIN, 10);
        axis.setTickLabelFont(font);
        
        Font font2 = new Font("Dialog", Font.PLAIN, 15);
        //axis2.setTickLabelFont(font2);
        plot.setForegroundAlpha(0.3f);
        AreaRenderer renderer = (AreaRenderer) plot.getRenderer();
        
        renderer.setEndType(AreaRendererEndType.LEVEL);
       
        //renderer.setBaseVisible(false);
        System.out.println(renderer.getBaseShape());
        //renderer.seteBaseShape(-3.0,-3.0,6.0,6.0);
        chart.setTitle(new TextTitle("SGW Performance",
                new Font("Serif", java.awt.Font.BOLD, 22))
        );
        

 
 

 return chart;

    }
    
    
    
       // Export the chart to an image. ------2
      protected static BufferedImage draw(JFreeChart chart, int width, int height) 
    { 
        BufferedImage img = 
        new BufferedImage(width , height, 
        BufferedImage.TYPE_INT_RGB); 
        Graphics2D g2 = img.createGraphics(); 
                        
        chart.draw(g2, new Rectangle2D.Double(0, 0, width, height)); 
 
        g2.dispose(); 
        return img; 
    }
   /* public static void writeChartAsPDF(OutputStream out, JFreeChart chart, int width, int height, FontMapper mapper) throws IOException, DocumentException {
Rectangle pagesize = new Rectangle(width, height); 
Document document = new Document(pagesize, 50, 50, 50, 50);

try { PdfWriter writer = PdfWriter.getInstance(document, out);
document.addAuthor("JFreeChart");
document.addSubject("Demonstration");
document.open();
PdfContentByte cb = writer.getDirectContent(); 
PdfTemplate tp = cb.createTemplate(width, height);
Graphics2D g2 = tp.createGraphics(width, height, mapper);
Rectangle2D r2D = new Rectangle2D.Double(0, 0, width, height); 
chart.draw(g2, r2D, null); 
g2.dispose(); 
cb.addTemplate(tp, 0, 0);
} catch(DocumentException de) { System.err.println(de.getMessage()); }
document.close();
}
 */   
  /*  public static void saveChartAsPDF(File file, JFreeChart chart, int width, int height, FontMapper mapper) throws IOException, DocumentException {
OutputStream out = new BufferedOutputStream(new FileOutputStream(file)); 
writeChartAsPDF(out, chart, width, height, mapper); 
out.close();
    
}*/


/*    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new NewChart();
            ex.setVisible(true);
      
        });
        
    }*/
}
 