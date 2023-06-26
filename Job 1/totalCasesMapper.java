package ProgettiBigData.CovidMapReduce;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class totalCasesMapper extends Mapper<LongWritable,Text, Text, Text> {
		
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
		
		try 
		{	String line= value.toString();
			String continent=null;
			String cases=null;
			
			if(!line.isEmpty()) {
				String[] tok =line.split(","); //splitta l'input ad ogni "," (delimitatore di colonne del file CSV)
				
				if(!tok[1].isEmpty())   //prendi in considerazione solo i continenti (non i dati su tutto il mondo)
				{    
//					if(tok[3].contains("2020-04-30"))
//					{
//						continent=tok[1];
//						cases=tok[4];
//					}
					
//					if(tok[3].contains("2020-08-31"))
//					{
//						continent=tok[1];
//						cases=tok[4];
//					}
//					
					if(tok[3].contains("2020-10-31"))
					{
						continent=tok[1];
						cases=tok[4];
					}
					context.write(new Text(continent),new Text(cases));
				}
				
			} else 
					return;				
		}		
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
   }
}