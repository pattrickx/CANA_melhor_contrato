package Sort;

public class Contrato {
    
	int fornecedor; 
	int mesIni, mesFim; 
	float valorTotal; 
	 
	 
	public Contrato() {} 
 
 
	public int getFornecedor() { 
		return fornecedor; 
	}  
 
	public void setFornecedor(int fornecedor) { 
		this.fornecedor = fornecedor; 
	} 
 
 
	public int getMesIni() { 
		return mesIni; 
	} 
 
 
	public void setMesIni(int mesIni) { 
		this.mesIni = mesIni; 
	} 
 
 
	public int getMesFim() { 
		return mesFim; 
	} 
 
 
	public void setMesFim(int mesFim) { 
		this.mesFim = mesFim; 
	} 
 
 
	public float getValorTotal() { 
		return valorTotal; 
	} 
 
 
	public void setValorTotal(float valorTotal) { 
		this.valorTotal = valorTotal; 
	} 
}