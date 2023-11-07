
import java.text.DecimalFormat;
public class Empregado {
	private double salario_emp;
	private double desc_INSS;
	private double baseCalc;
	private String nome_emp = "";
	DecimalFormat formato = new DecimalFormat("#.##");
	
	public Empregado(String nome, double salario) {
    	this.salario_emp = salario;
    	setNome_emp(nome);
    	this.desc_INSS = calcularDescontoINSS(salario);
    	this.baseCalc = (this.desc_INSS - this.salario_emp)*(-1);
    	
	}

    private static double calcularDescontoINSS(double salarioBruto) {
        double desconto = 0.0;
        if (salarioBruto <= 1100.0) {
            desconto = salarioBruto * 0.075;
        } else if (salarioBruto <= 2203.48) {
            desconto = salarioBruto * 0.09;
        } else if (salarioBruto <= 3305.22) {
            desconto = salarioBruto * 0.12;
        } else if (salarioBruto <= 6433.57) {
            desconto = salarioBruto * 0.14;
        } else {
            desconto = 751.99;
        }
        return desconto;
    }	
	
    private static double calcDev(double baseCalculo) {
        double imposto = 0.0;
        if (baseCalculo <= 1903.98) {
            imposto = 0.0;
        } else if (baseCalculo <= 2826.65) {
            imposto = baseCalculo * 0.075 - 142.8;
        } else if (baseCalculo <= 3751.05) {
            imposto = baseCalculo * 0.15 - 354.8;
        } else if (baseCalculo <= 4664.68) {
            imposto = baseCalculo * 0.225 - 636.13;
        } else {
            imposto = baseCalculo * 0.275 - 869.36;
        }
        return imposto;
    }
    
	public String calcIR() {
    	double sal = this.salario_emp;
    	double base = getBase();
    	double devido = calcDev(baseCalc);
    	
    	if (base <= 1903.98) {
        	return "ISENTO Para o Salario:" + sal;
    	} else if ((base >= 1903.99) || (base <= 2826.65)) {
        	return "Imposto R$ :" + formato.format(devido)+ " Salario ref: "+ sal ;
    	} else if ((base >= 2826.66) || (base <= 3751.05)) {
        	return "Imposto R$ :" + formato.format(devido)+ " Salario ref: "+ sal ;
    	} else if ((base >= 3751.06) || (base <= 4664.68)) {
        	return "Imposto R$ :" + formato.format(devido)+ " Salario ref: "+ sal ;
    	} else {
        	return "Imposto R$ :" + formato.format(devido)+ " Salario ref: "+ sal ;
    	}
	}

	public String getNome_emp() {
		return nome_emp;
	}

	public void setNome_emp(String nome_emp) {
		this.nome_emp = nome_emp;
	}
	
	
	public void setBase(double b) {
		this.baseCalc = b;
		
	}
	public double getBase() {
		return baseCalc;
	}
}
