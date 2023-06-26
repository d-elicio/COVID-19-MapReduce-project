package ProgettiBigData.CovidMapReduce;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class totalCasesReducer extends Reducer<Text,Text,NullWritable,Text> {
	
	private ArrayList<Elements> ele_array= new ArrayList<Elements>();
	private StringBuffer temp;
	private Text result= new Text();
	
	
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		
		double totAmo=0;
		Elements ele= new Elements();
		
		for (Text i:values) {
			
			if(!i.toString().isEmpty()) {
			
			totAmo= totAmo+ Double.parseDouble(i.toString()); 
			}
		
		}
		ele.put(key.toString(), totAmo);
		ele_array.add(ele);
	}
		
		
	//Override del metodo cleanup del reducer per poter stampare i risultati 
		protected void cleanup(Context context) throws IOException, InterruptedException{
			//Bubble sort per ordinare i risuatati in ordine decrescente
			for(int i=0;i<ele_array.size();i++) {
				boolean flag= false;
				for(int j=0;j<ele_array.size()-1;j++) {
					if(ele_array.get(j).getValue()<ele_array.get(j+1).getValue()) {
						Elements k= ele_array.get(j);
						ele_array.set(j,ele_array.get(j+1));
						ele_array.set(j+1,k);
						flag=true;
					}
				}
				if(!flag) break;
			}
			
			
			
			//Stampa dei primi 5 risultati
			temp= new StringBuffer("Continent");
			temp.append("\t\t");
			temp.append(new StringBuffer("Total cases"));
			result.set(temp.toString());
			context.write(NullWritable.get(),result);
			
			for(int i =0; i<ele_array.size();i++) {
				int j=i+1;
				temp= new StringBuffer((new IntWritable(j)).toString());
				temp.append("\t");
				temp.append(new StringBuffer((new Text(ele_array.get(i).getKey())).toString()));
				temp.append("\t\t");
				temp.append(new StringBuffer((new IntWritable((int)ele_array.get(i).getValue()).toString())));
				result.set(temp.toString());
				context.write(NullWritable.get(), result);
			}			
	}
}
