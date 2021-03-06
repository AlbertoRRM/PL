package procesamientos.impresion;

import procesamientos.Procesamiento;

import programa.Programa.*;

public class Impresion extends Procesamiento {
	private boolean atributos;
	private int identacion;

	public Impresion(boolean atributos) {
		this.atributos = atributos;
		identacion = 0;
	}

	public Impresion() {
		this(false);
	}

	private void imprimeAtributos(Exp exp) {
		if (atributos) {
			System.out.print("@{t:" + exp.tipo() + "}");
		}
	}

	private void imprimeAtributos(Prog prog) {
		if (atributos) {
			System.out.print("@{t:" + prog.tipo() + "}");
		}
	}

	private void imprimeAtributos(Inst i) {
		if (atributos) {
			System.out.print("@{t:" + i.tipo() + "}");
		}
	}

	private void identa() {
		for (int i = 0; i < identacion; i++)
			System.out.print(" ");
	}

	public void procesa(CteInt exp) {
		System.out.print(exp.valEntero());
		imprimeAtributos(exp);
	}

	public void procesa(CteBool exp) {
		System.out.print(exp.valBool());
		imprimeAtributos(exp);
	}
	
	public void procesa(CteChar exp) {
		System.out.println(exp.valChar());
		imprimeAtributos(exp);
	}
	
	public void procesa(CteReal exp) {
		System.out.println(exp.valReal());
		imprimeAtributos(exp);
	}
	
	public void procesa(CteString exp) {
		System.out.println(exp.valString());
		imprimeAtributos(exp);
	}

	public void procesa(Var exp) {
		System.out.print(exp.var());
		imprimeAtributos(exp);
	}

	public void procesa(Suma exp) {
		System.out.print('(');
		exp.opnd1().procesaCon(this);
		System.out.print('+');
		imprimeAtributos(exp);
		exp.opnd2().procesaCon(this);
		System.out.print(')');
	}
	
	public void procesa(Resta exp) {
		System.out.print('(');
		exp.opnd1().procesaCon(this);
		System.out.print('-');
		imprimeAtributos(exp);
		exp.opnd2().procesaCon(this);
		System.out.print(')');
	}
	
	public void procesa(Multiplicacion exp) {
		System.out.print('(');
		exp.opnd1().procesaCon(this);
		System.out.print('*');
		imprimeAtributos(exp);
		exp.opnd2().procesaCon(this);
		System.out.print(')');
	}
	
	public void procesa(Division exp) {
		System.out.print('(');
		exp.opnd1().procesaCon(this);
		System.out.print('/');
		imprimeAtributos(exp);
		exp.opnd2().procesaCon(this);
		System.out.print(')');
	}

	public void procesa(And exp) {
		System.out.print('(');
		exp.opnd1().procesaCon(this);
		System.out.print("&&");
		imprimeAtributos(exp);
		exp.opnd2().procesaCon(this);
		System.out.print(')');
	}
	
	public void procesa(Or exp) {
		System.out.print('(');
		exp.opnd1().procesaCon(this);
		System.out.print("||");
		imprimeAtributos(exp);
		exp.opnd2().procesaCon(this);
		System.out.print(')');
	}
	
	public void procesa(Not exp) {
		System.out.print('(');
		System.out.print("!");
		exp.opnd().procesaCon(this);
		System.out.print(')');
	}
	
	public void procesa(Concatena exp) {
		System.out.print('(');
		exp.opnd1().procesaCon(this);
		System.out.print("++");
		imprimeAtributos(exp);
		exp.opnd2().procesaCon(this);
		System.out.print(')');
	}
	
	public void procesa(ElementoCadena exp) {
		System.out.print('(');
		exp.opnd1().procesaCon(this);
		System.out.print("[");
		imprimeAtributos(exp);
		exp.opnd2().procesaCon(this);
		System.out.print("]");
		System.out.print(')');
	}
	
	public void procesa(RestoEntero exp) {
		System.out.print('(');
		exp.opnd1().procesaCon(this);
		System.out.print("%");
		imprimeAtributos(exp);
		exp.opnd2().procesaCon(this);
		System.out.print(')');
	}

	public void procesa(Prog p) {
		for (Dec d : p.decs())
			d.procesaCon(this);
		p.inst().procesaCon(this);
		imprimeAtributos(p);
		System.out.println();
	}

	public void procesa(DecVar t) {
		System.out.print(t.tipoDec() + " " + t.var());
		System.out.println();
	}

	public void procesa(IAsig i) {
		identa();
		System.out.print(i.var() + "=");
		i.exp().procesaCon(this);
		imprimeAtributos(i);
		System.out.println();
	}

	public void procesa(IBloque b) {
		identa();
		System.out.println("{");
		identacion += 3;
		for (Inst i : b.is())
			i.procesaCon(this);
		identacion -= 3;
		identa();
		System.out.print("}");
		imprimeAtributos(b);
		System.out.println();
	}
	   
}