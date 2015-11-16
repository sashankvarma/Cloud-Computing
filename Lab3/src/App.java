
        
import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
        
public class App {
        
 public static class Map extends Mapper<LongWritable, Text, Text, Text> {
    
        
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
    	if(value.toString().length()>0){
    	String geturl = "http://finance.yahoo.com/webservice/v1/symbols/"+value.toString()+"/quote?format=json";
		 JSONObject jo = null;
		try {
			jo = (JSONObject) new JSONTokener(IOUtils.toString(new URL(geturl))).nextValue();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Text s = null;
		try {
			s = new Text(jo.getJSONObject("list").getJSONArray("resources").getJSONObject(0).getJSONObject("resource").getJSONObject("fields").getString("price"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//Text s= new Text(geturl);
		context.write(value,s);
        }
 }
    }
  
        
 
        
 public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
        
        @SuppressWarnings("deprecation")
		Job job = new Job(conf, "wordcount");
   /*
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
        
    job.setMapOutputValueClass(Map.class);
   // job.setReducerClass(Reduce.class);
        
    job.setInputFormatClass(TextInputFormat.class);
    job.setOutputFormatClass(TextOutputFormat.class);
     */
    job.setJarByClass(App.class);
	job.setMapperClass(Map.class);
	job.setOutputKeyClass(Text.class);
	job.setOutputValueClass(Text.class);
	job.setInputFormatClass(TextInputFormat.class);
	job.setOutputFormatClass(TextOutputFormat.class);
		
	// Sets reducer tasks to 0
	job.setNumReduceTasks(0);
    
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
    job.waitForCompletion(true);
 }
        
}
