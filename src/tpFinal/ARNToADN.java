package tpFinal;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tpFinal.auxiliar;

public class ARNToADN {
	List<String> ADN = new LinkedList<String>();
	List<String> lista = new LinkedList<String>();
	List<String> ARN = new LinkedList<String>();
	List<String> listaADN = new LinkedList<String>();
	List<String> complementoADN = new LinkedList<String>();
	private Stack<String> pilaDeInvalidos = new Stack<String>();
			
	public void comprobanteARN(List<String> lista) 
	{	 
		 String regexp = "[ACGU]+";
	     Pattern p = Pattern.compile(regexp);
	     ARN = new LinkedList<String>();
	     pilaDeInvalidos = new Stack<String>();
	     for (int i = 0 ; i < lista.size() ; ++i) 
	     {
	    	 String cur = lista.get(i);
	         Matcher m = p.matcher(cur);
	         if (!m.matches()) 
	         {  
	        	  pilaDeInvalidos.push(cur);
	         }
	         else
	         {
	        	 ARN.add(cur);
	         }
	      }	
	     
	     
	     ADN = complementoADN(ARN); 
	     chequearGen(ADN);
	     
	}

	private List<String> chequearGen(LinkedList<String> ADN) 
	{
		String regexp = "ATG([ACGT]{3})+(TAA|TAG|TGA)";
	    Pattern gen = Pattern.compile(regexp);
	    List<String> GEN = new LinkedList<String>();
	    Queue<String> cola = new LinkedList<String>();
	     
	     for (int i = 0 ; i < ADN.size() ; ++i) 
	     {
	    	 String cur = ADN.get(i);
	         Matcher m = gen.matcher(cur);
	         if (!m.matches()) 
	         { 
	        	 cola.add(cur); 
	         }
	         else
	         {
	        	 GEN.add(cur);
	         }
	      }
	}  
	
	//Busco complemento/inverso de ARN y lo pongo en lista
	public List<String> complementoADN(List<String> ARN)
	{	
	 	List<String> inverso = new LinkedList<String>();
	 	for (int i = 0 ; i < ARN.size() ; ++i) 
	 	{	
	 		    String cur = ARN.get(i);
	 		    Stack<String> pilaC = new Stack<String>();
	 			for (int j=0; j< cur.length(); j++)
	 			{
	 				String c = cur.substring(j, j++);
	 				
	 				if(c.equals("A"))
	 				{
	 					pilaC.push("T");
	 				}
	 				if (c.equals("C"))
	 				{
	 					pilaC.push("G");
	 				}
	 				if (c.equals("G"))
	 				{
	 					pilaC.push("C");
	 				}
	 				if (c.equals("U"))
	 				{
	 					pilaC.push("A");
	 				}	
	 			}
	 			String inv="";
	 			while(!pilaC.isEmpty())
	 			{
	 				inv= inv.concat(pilaC.pop());
	 			}
	 			inverso.add(inv);
	 		 }	
	 		return inverso;
	 	}

	     
	    //Encolo ADN que no es gen
		private Queue<String> filtroCola(List<String> listaADNc) 
		{
			String regexpGENc = "ATG([ACGT]{3})+(TAA|TAG|TGA)";
			Pattern gen = Pattern.compile(regexpGENc);
			Queue<String> cola = new LinkedList<String>();
			     
			for (int i = 0 ; i < listaADNc.size() ; ++i) 
			{
				String cur = listaADNc.get(i);
			    Matcher m = gen.matcher(cur);
			    if (!m.matches()) 
			    {
			    	cola.add(cur); 
			    }
			 }
			
			return cola;
		}	
		
		
		
		
}
	
	
	

