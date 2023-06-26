package ProgettiBigData.CovidMapReduce3;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class hospitalizedMapper extends Mapper<LongWritable,Text,Text,IntWritable> {

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
	{
		try 
		{
			String line= value.toString();
			int n=0;
			int icu_patients=0;
			int hosp_patients=0;
			
			if (!line.isEmpty()) 
			{
				String [] tok = line.split(",");
				
				if ((!tok[1].isEmpty()) && (tok[3].contains("2020-"))) //prendi in considerazione solo i continenti (non i dati su tutto il mondo) 
				{
					if (!tok[17].isEmpty()) {
						icu_patients=(int)Double.parseDouble(tok[17].toString());
						} else
							icu_patients=0;
						if(!tok[19].isEmpty()) {
							hosp_patients=(int)Double.parseDouble(tok[19].toString());
						}else
							hosp_patients=0;
						
						n=icu_patients+hosp_patients;
						
						context.write(new Text(tok[3]), new IntWritable(n));
				}
				else
					return;
			}
			else 
				return;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}	
}
