package ProgettiBigData.CovidMapReduce2;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class newTestMapper extends Mapper<LongWritable,Text,Text,Text> {
	
	public void map(LongWritable key,Text value, Context context) throws IOException, InterruptedException
	{
		try 
		{
			String line= value.toString();
			
			if (!line.isEmpty()) 
			{
				String [] tok= line.split(",");
				
				if((!tok[1].isEmpty()) && (tok[3].contains("2020-")))   //prendi in considerazione solo i continenti (non i dati su tutto il mondo) 
				{ 
					context.write(new Text(tok[2]),new Text(tok[26]));
				}
			}
			else 
				return;
		}		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
