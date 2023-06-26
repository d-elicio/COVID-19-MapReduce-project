package ProgettiBigData.CovidMapReduce3;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;


public class hospitalizedReducer extends Reducer<Text,IntWritable,NullWritable,Text> {
	
	private ArrayList<Oggetto> obj_array=new ArrayList<Oggetto>();
	private StringBuffer temp;
	private Text result= new Text();
	
	public void reduce(Text key, Iterable<IntWritable>values,Context context) throws IOException, InterruptedException{
		
		int count=0;
		Oggetto obj = new Oggetto();

		for(IntWritable value:values) {
			count=count+value.get();
		}
		
		obj.put(key.toString(), count);
		obj_array.add(obj);
	}
	
	
	//Override del metodo cleanup del reducer per poter stampare i risultati 
	protected void cleanup(Context context) throws IOException,InterruptedException{
		//Bubble sort per ordinare i risuatati in ordine decrescente
		for(int i=0;i<obj_array.size();i++) {
			boolean flag=false;
			for(int j=0;j<obj_array.size()-1;j++) {
				if(obj_array.get(j).getValue()<obj_array.get(j+1).getValue()) {
					Oggetto k= obj_array.get(j);
					obj_array.set(j, obj_array.get(j+1));
					obj_array.set(j+1,k);
					flag=true;
				}
			}
			if(!flag) break;
		}
		
		//Stampa dei primi 5 risultati
		temp= new StringBuffer("\t");
		temp.append("Date");
		temp.append("\t\t");
		temp.append(new StringBuffer("Total no. of patients"));
		result.set(temp.toString());
		context.write(NullWritable.get(),result);
		
		for(int i=0;i<5;i++) {
			temp= new StringBuffer((new IntWritable(i+1)).toString());
			temp.append("\t");
			temp.append(new StringBuffer((new Text(obj_array.get(i).getKey())).toString()));
			temp.append("\t\t");
			temp.append(new StringBuffer((new IntWritable(obj_array.get(i).getValue()).toString())));
			result.set(temp.toString());
			context.write(NullWritable.get(), result);
		}		
	}
}
