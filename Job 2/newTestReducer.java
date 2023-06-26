package ProgettiBigData.CovidMapReduce2;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.hadoop.io.Text;
//import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class newTestReducer extends Reducer<Text,Text,NullWritable,Text>{
	
	private ArrayList<Oggetto> oggetti_array= new ArrayList<Oggetto>();
	private StringBuffer temp;
	private Text result = new Text();
	
	public void reduce(Text key, Iterable<Text> values,Context context) throws IOException, InterruptedException
	{
		int n=0;
		double new_tests=0;
		Oggetto oggetto= new Oggetto();
		
		for(Text value:values) 
		{
			if (value.toString().isEmpty()) //perchè in molti paesi mancano i dati sui NEW_TEST soprattutto a Marzo/Aprile 
			{
				n++;
			}
			else  
			{
			new_tests=new_tests+Double.parseDouble(value.toString());
			n++;
			}
		}
		
		oggetto.put(key.toString(), new_tests/n);
		oggetti_array.add(oggetto);			
	}
	
	
	
	//Override del metodo cleanup del reducer per poter stampare i risultati 
	protected void cleanup(Context context) throws IOException,InterruptedException
	{
		temp = new StringBuffer("Location");
		temp.append("\t\t\t");
		temp.append(new StringBuffer("Average N° test per day"));
		result.set(temp.toString());
		context.write(NullWritable.get(), result);
		
		for(int i=0; i<oggetti_array.size();i++) 
		{
			temp = new StringBuffer((new Text(oggetti_array.get(i).getKey())).toString());
			temp.append("\t\t\t\t");
			
			temp.append(new StringBuffer((new IntWritable((int)oggetti_array.get(i).getValue()).toString())));
			result.set(temp.toString());
			context.write(NullWritable.get(), result);
		}	
	}	
}
