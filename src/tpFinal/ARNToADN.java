package tpFinal;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ARNToADN {
	private List<String> ADN = new LinkedList<String>();
	private List<String> ARN = new LinkedList<String>();
	private Stack<String> pilaDeInvalidos = new Stack<String>();
	private arbolBinarioAlfabetico arbolDeGenes = new arbolBinarioAlfabetico();

	private Queue<String> colaDeNoGenes = new LinkedList<String>();
	
	
	
	public List<String> getADN() {
		return ADN;
	}

	public List<String> getARN() {
		return ARN;
	}

	public Stack<String> getPilaDeInvalidos() {
		return pilaDeInvalidos;
	}

	public arbolBinarioAlfabetico getArbolDeGenes() {
		return arbolDeGenes;
	}

	public Queue<String> getColaDeNoGenes() {
		return colaDeNoGenes;
	}

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
	     
	 	//Pongo genes en "listaGenes"
	    private void chequearGen(List<String> listaADN) 
		{
			String regexpGEN = "ATG([ACGT]{3})+(TAA|TAG|TGA)";
		    Pattern gen = Pattern.compile(regexpGEN);
		    arbolDeGenes = new arbolBinarioAlfabetico();
		     colaDeNoGenes = new LinkedList<String>();
		    for (int i = 0 ; i < listaADN.size() ; ++i) 
		    {
		    	String cur = listaADN.get(i);
		        Matcher m = gen.matcher(cur);
		        if (m.matches()) 
		        { 
		        	arbolDeGenes.agregar(cur); 
		        } else {
		        	colaDeNoGenes.add(cur);
		        }
		    }
		    
		}
		
	
		
		
		
		
}
	
	
	

