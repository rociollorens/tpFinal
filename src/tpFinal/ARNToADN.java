package tpFinal;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ARNToADN {
	
	private List<String> secuenciasIngresadas;
	
	private arbolBinarioAlfabetico arbolSecuenciasADN = new arbolBinarioAlfabetico();
	private List<String> ARN = new LinkedList<String>();
	private Stack<String> pilaDeInvalidos = new Stack<String>();
	
	private Queue<String> colaSecuenciasNoGenes = new LinkedList<String>();
	
	
	public List<String> getADN() {
		return arbolSecuenciasADN.list();
	}

	public List<String> getARN() {
		return ARN;
	}

	public Stack<String> getPilaDeInvalidos() {
		return pilaDeInvalidos;
	}

	public arbolBinarioAlfabetico getArbolSecuenciasADN() {
		return arbolSecuenciasADN;
	}

	public Queue<String> getColaSecuenciasNoGenes() {
		return colaSecuenciasNoGenes;
	}

	public List<String> getSecuenciasIngresadas() {
		return secuenciasIngresadas;
	}

	public void comprobanteARN(List<String> lista) 
	{	 
		secuenciasIngresadas = lista;
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
	    complementoADN(ARN); 
	}
	
	public String getcDNA(String ARN) {
		Stack<String> pilaC = new Stack<String>();
		for (int j=0; j< ARN.length(); j++)
		{
			String c = ARN.substring(j, j+1);
			
			if (c.equals("A"))	pilaC.push("T");
			if (c.equals("C"))	pilaC.push("G");
			if (c.equals("G"))	pilaC.push("C");
			if (c.equals("U"))	pilaC.push("A");
		}
		
		String inv="";
		while(!pilaC.isEmpty())
		{
			inv= inv.concat(pilaC.pop());
		}
		return inv;
	}
	     
	//Busco complemento/inverso de ARN y lo pongo en lista
	public void complementoADN(List<String> ARN)
	{	
	 	arbolSecuenciasADN =  new arbolBinarioAlfabetico();
	 	colaSecuenciasNoGenes = new LinkedList<>();
	 	for (int i = 0 ; i < ARN.size() ; ++i) 
	 	{	
 		    String cur = ARN.get(i);
 		    String inv = getcDNA(cur);
 			if(!esGen(inv))
				colaSecuenciasNoGenes.add(cur);
 			arbolSecuenciasADN.agregar(inv);
	 	}	
	 }
	
		public boolean esGen(String secuencia) {
			String regexpGEN = "ATG([ACGT]{3})+(TAA|TAG|TGA)";
		    Pattern gen = Pattern.compile(regexpGEN);
		    Matcher m = gen.matcher(secuencia);
	        return m.matches();
		}
}
	
	
	

