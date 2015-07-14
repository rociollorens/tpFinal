package tpFinal;

public class arbolBinarioAlfabetico {
	private arbolBinarioAlfabetico izq;
	private arbolBinarioAlfabetico der;
	private String secuencia;
	
	public arbolBinarioAlfabetico getIzq() {
		return izq;
	}
//	public void setIzq(arbolBinarioAlfabetico izq) {
//		this.izq = izq;
//	}
	public arbolBinarioAlfabetico getDer() {
		return der;
	}
//	public void setDer(arbolBinarioAlfabetico der) {
//		this.der = der;
//	}
	public String getSecuencia() {
		return secuencia;
	}
	
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}
	
	public void agregar(String s){
		if (secuencia.isEmpty()){
			secuencia = s;
		} else {
			if (secuencia.compareTo(s)>=0){ // s es menor o igual qeu secuencia
			  if (izq== null ){ 
				  izq = new arbolBinarioAlfabetico();
			  }
			  izq.agregar(s);
			}
			else
			{  
				if ( der== null ){
					der = new arbolBinarioAlfabetico();
				}
				der.agregar(s);
			}
		}
	}
	
	public void imprimir(){
		if(izq != null){
			izq.imprimir();
		}
		System.out.println(secuencia);
		if(der != null){
			der.imprimir();
		}	
	}
	
	
	
}
