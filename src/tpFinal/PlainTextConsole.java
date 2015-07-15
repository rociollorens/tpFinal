package tpFinal;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

public class PlainTextConsole {
    
	static ARNToADN aux = new ARNToADN();
	
	
	public static void testOpciones() {
		 List<String> sequences = new LinkedList<>();
		 sequences.add("ADCGUU");
		 sequences.add("ADCGACUUAAUU");
		 sequences.add("ADCGUAAUGCU");
		 sequences.add("ACGUACGUACGU");
		 sequences.add("ACGGGACCUUUU");
		 sequences.add("AGGCUUAUCGACCC");
		 option1(sequences);
		 option2();
		 option3();
		 option4();
	}
	
    public static void option1(List<String> sequences) {
           aux.comprobanteARN(sequences);
    }
    
    public static boolean avisarSiNoHaySecuenias() {
    	if(aux.getSecuenciasIngresadas()==null || aux.getSecuenciasIngresadas().isEmpty())
    	{
    		System.out.println("No se ingresaron secuencias en la opción 1");
    		return true;
    	}
    	return false;
    }
    
    public static void option2() {
    	if(avisarSiNoHaySecuenias())return;
    	List<String> cDNA = aux.getADN();
    	if(cDNA.isEmpty()) {
    		System.out.println("No hay secuencias válidas");
    		return;
    	}
        System.out.println("Imprimiendo las secuencias validas en formato cDNA (Opcion 2)");
        for(int i = 0; i < cDNA.size(); i++ ){
        	String posibleGen = cDNA.get(i);
        	if(aux.esGen(posibleGen))
        		System.out.println(posibleGen);
        }
    }
    
    public static void option3() {
    	if(avisarSiNoHaySecuenias())return;
    	Queue<String> noGenes = new LinkedList<>();
    	
    	noGenes.addAll(aux.getColaSecuenciasNoGenes());
        if(noGenes.isEmpty()) {
    		System.out.println("No hay secuencias inválidas que respeten el lenguaje {A, C, G, U}");
    		return;
    	}
        System.out.println("Imprimiendo las secuencias inválidas (Opcion 3)");
        while(!noGenes.isEmpty()){
        	System.out.println(noGenes.poll());
        }
    }
    
    public static void option4() {
    	if(avisarSiNoHaySecuenias())return;
        Stack<String> invalidos = new Stack<>();
        invalidos.addAll(aux.getPilaDeInvalidos());
        if(invalidos.isEmpty()) {
    		System.out.println("No hay secuencias inválidas que respeten no el lenguaje {A, C, G, U}");
    		return;
    	}
        System.out.println("Imprimiendo las secuencias inválidas en orden inverso  (Opcion 4)");
        while(!invalidos.isEmpty()){
        	System.out.println(invalidos.pop());
        }
    }
    
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in); //Scanner para obtener el input del usuario

        sc.useDelimiter(Pattern.compile("\r?\n")); //Setea el ENTER como fin de linea para el input del usuario   
        										   //Funciona en windows y en linux
     //   testOpciones();
     
        System.out.println("Bienvenidos al TP Final de 71.44!!"); //Mensaje de bienvenida

        while(true) {
            //Imprimimos el men� ante cada input del usuario
            System.out.println("\tMenú del programa:");
            System.out.println("\tIngrese 1 para comenzar a ingresar secuencias de nucleótidos separadas por la tecla ENTER. Al finalizar, ingrese q o Q seguido de ENTER");
            System.out.println("\tIngrese 2 para imprimir en pantalla todas las secuencias válidas ingresadas previa conversión mediante complemento y reversa.");
            System.out.println("\tIngrese 3 para imprimir en pantalla todas las secuencias inválidas en pantalla en el mismo orden en que fueron ingresadas por el usuario.");
            System.out.println("\tIngrese 4 para imprimir en pantalla todas las secuencias inválidas y que no estén en formato de código genético en el orden inverso al que las ingresó el usuario.");
            System.out.println("\tIngrese Q para salir.");

            String opcion = sc.next(); //Lee una linea de input del usuario
            
            if (opcion.equalsIgnoreCase("1")) { //eliji� la opci�n 1
            	 List<String> sequences = new LinkedList<>();
            	 System.out.println("Ingrese las secuencias ARN (una por linea):");
            	 System.out.println("");
                while (true) {
                    String sequence = sc.next(); //Lee una linea de input del usuario
                    if (sequence.equalsIgnoreCase("q")) {
                        option1(sequences);
                        break;
                    } else {
                        sequences.add(sequence);
                    }
                }   
            } else if (opcion.equalsIgnoreCase("2")) {
                option2();
            } else if (opcion.equalsIgnoreCase("3")) {
                option3();
            } else if (opcion.equalsIgnoreCase("4")) {
                option4();
            } else if (opcion.equalsIgnoreCase("q")) {
                break;
            } else {
                System.out.println("No ingresó una opción correcta");
            }
        }
        
        System.out.println("Gracias por utilizar este programa!");
        System.out.println("Hasta Luego!");
        
        sc.close();
        
    }
}
	

