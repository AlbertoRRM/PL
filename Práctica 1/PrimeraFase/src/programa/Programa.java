package programa;

import procesamientos.Procesamiento;

public abstract class Programa {
	// Tipos que puedo encontrar en mi �rbol de sint�xis abstracta
	private final Tipo TENT;
	private final Tipo TBOOL;
	private final Tipo TCHAR;
	private final Tipo TREAL;
	private final Tipo TSTRING;
	private final Tipo TOK;
	private final Tipo TERROR;

	public Programa() {
		TENT = new Int();
		TBOOL = new Bool();
		TCHAR = new Char();
		TREAL = new Real();
		TSTRING = new TString();
		TOK = new Ok();
		TERROR = new Error();
	}

	public interface Tipo {
		void acepta(Procesamiento p);
	}

	// Tipo entero
	public class Int implements Tipo {
		public void acepta(Procesamiento p) {
			p.procesa(this);
		}

		public String toString() {
			return "INT";
		}
	}

	// Tipo booleano
	public class Bool implements Tipo {
		public void acepta(Procesamiento p) {
			p.procesa(this);
		}

		public String toString() {
			return "BOOL";
		}
	}

	// Tipo car�cter
	public class Char implements Tipo {
		public void acepta(Procesamiento p) {
			p.procesa(this);
		}
		
		public String toString() {
			return "CHAR";
		}
	}
	
	// Tipo real
	public class Real implements Tipo {
		public void acepta(Procesamiento p) {
			p.procesa(this);
		}
		
		public String toString() {
			return "REAL";
		}
	}
	
	// Tipo string
	public class TString implements Tipo {
		public void acepta(Procesamiento p) {
			p.procesa(this);
		}
		
		public String toString() {
			return "STRING";
		}
	}
	
	// ?
	public class Ok implements Tipo {
		public void acepta(Procesamiento p) {
			p.procesa(this);
		}

		public String toString() {
			return "OK";
		}
	}

	// Tipo error
	public class Error implements Tipo {
		public void acepta(Procesamiento p) {
			p.procesa(this);
		}

		public String toString() {
			return "ERROR";
		}
	}

	public class Prog {
		private Dec[] decs; // Declaraciones
		private Inst i; // Instrucciones
		private Tipo tipo; // Tipos

		public Prog(Dec[] decs, Inst i) {
			this.decs = decs;
			this.i = i;
			this.tipo = null;
		}

		public Dec[] decs() {
			return decs;
		}

		public Inst inst() {
			return i;
		}

		public Tipo tipo() {
			return tipo;
		}

		public void ponTipo(Tipo tipo) {
			this.tipo = tipo;
		}

		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}

	public abstract class Dec {
		public abstract void procesaCon(Procesamiento p);
	}

	public class DecVar extends Dec {
		private String enlaceFuente; // Indica en qu� l�nea hay un error en el caso de que lo haya
		private String var;
		private Tipo tipoDec;
		private int dir;

		public DecVar(Tipo tipo, String var) {
			this(tipo, var, null);
		}

		public DecVar(Tipo tipo, String var, String enlaceFuente) {
			this.tipoDec = tipo;
			this.enlaceFuente = enlaceFuente;
			this.var = var;
		}

		public Tipo tipoDec() {
			return tipoDec;
		}

		public String var() {
			return var;
		}

		public int dir() {
			return dir;
		}

		public void ponDir(int dir) {
			this.dir = dir;
		}

		public String enlaceFuente() {
			return enlaceFuente;
		}

		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}

	public abstract class Inst {
		private Tipo tipo;

		public Inst() {
			tipo = null;
		}

		public Tipo tipo() {
			return tipo;
		}

		public void ponTipo(Tipo tipo) {
			this.tipo = tipo;
		}

		public abstract void procesaCon(Procesamiento p);
	}

	// Instrucci�n de asignaci�n
	public class IAsig extends Inst { 
		private String var;
		private Exp exp;
		private String enlaceFuente;
		private DecVar declaracion;

		public IAsig(String var, Exp exp, String enlaceFuente) {
			this.var = var;
			this.exp = exp;
			this.declaracion = null;
			this.enlaceFuente = enlaceFuente;
		}

		public IAsig(String var, Exp exp) {
			this(var, exp, null);
		}

		public String var() {
			return var;
		}

		public Exp exp() {
			return exp;
		}

		public DecVar declaracion() {
			return declaracion;
		}

		public String enlaceFuente() {
			return enlaceFuente;
		}

		public void ponDeclaracion(DecVar d) {
			declaracion = d;
		}

		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}

	// Instrucci�n de bloque
	public class IBloque extends Inst {
		private Inst[] is;

		public IBloque(Inst[] is) {
			this.is = is;
		}

		public Inst[] is() {
			return is;
		}

		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	

	// Clase expresi�n
	public abstract class Exp {
		private Tipo tipo; // Tipo de la expresi�n
		private int dirPrimeraInstruccion;
	    private int dirInstruccionSiguiente;

		public Exp() {
			tipo = null;
		}

		public void ponTipo(Tipo tipo) {
			this.tipo = tipo;
		}

		public Tipo tipo() {
			return tipo;
		}
		
		 public int dirPrimeraInstruccion() {
	         return dirPrimeraInstruccion;
	     }
	     public void ponDirPrimeraInstruccion(int dir) {
	        dirPrimeraInstruccion = dir;
	     }
	     public int dirInstruccionSiguiente() {
	         return dirInstruccionSiguiente;
	     }
	     public void ponDirInstruccionSiguiente(int dir) {
	        dirInstruccionSiguiente = dir;
	     }

		public abstract void procesaCon(Procesamiento p);
	}

	// Clase
	public class Var extends Exp {
		private String var;
		private DecVar declaracion;
		private String enlaceFuente;

		public Var(String var) {
			this(var, null);
		}

		public Var(String var, String enlaceFuente) {
			this.var = var;
			declaracion = null;
			this.enlaceFuente = enlaceFuente;
		}

		public String var() {
			return var;
		}

		public DecVar declaracion() {
			return declaracion;
		}

		public void ponDeclaracion(DecVar dec) {
			declaracion = dec;
		}

		public String enlaceFuente() {
			return enlaceFuente;
		}

		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}

	// Expresi�n constante entera
	public class CteInt extends Exp {
		private int val;

		public CteInt(int val) {
			this.val = val;
		}

		public int valEntero() {
			return val;
		}

		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}

	// Expresi�n constante booleana
	public class CteBool extends Exp {
		private boolean val;

		public CteBool(boolean val) {
			this.val = val;
		}

		public boolean valBool() {
			return val;
		}

		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	// Expresi�n constante car�cter
	public class CteChar extends Exp {
		private char val;
		
		public CteChar(char val) {
			this.val = val;
		}
		
		public char valChar() {
			return val;
		}
		
		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	// Expresi�n constante real
	public class CteReal extends Exp {
		private double val;
		
		public CteReal(double val) {
			this.val = val;
		}
		
		public double valReal() {
			return val;
		}
		
		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	// Expresi�n constante string
	public class CteString extends Exp {
		private String val;
		
		public CteString(String val) {
			this.val = val;
		}
		
		public String valString() {
			return val;
		}
		
		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}

	// Expresi�n binaria (gen�rico)
	private abstract class ExpBin extends Exp {
		private Exp opnd1;
		private Exp opnd2;
		private String enlaceFuente;

		public ExpBin(Exp opnd1, Exp opnd2) {
			this(opnd1, opnd2, null);
		}

		public ExpBin(Exp opnd1, Exp opnd2, String enlaceFuente) {
			this.enlaceFuente = enlaceFuente;
			this.opnd1 = opnd1;
			this.opnd2 = opnd2;
		}

		public Exp opnd1() {
			return opnd1;
		}

		public Exp opnd2() {
			return opnd2;
		}

		public String enlaceFuente() {
			return enlaceFuente;
		}
	}
	
	// Expresi�n unaria
	private abstract class ExpUn extends Exp {
		private Exp opnd;
		private String enlaceFuente;
		
		public ExpUn(Exp opnd) {
			this(opnd, null);
		}
		
		public ExpUn(Exp opnd1, String enlaceFuente) {
			this.opnd = opnd;
			this.enlaceFuente = enlaceFuente;
		}
		
		public Exp opnd() {
			return opnd;
		}
		
		public String enlaceFuente() {
			return enlaceFuente;
		}
	}

	// Expresi�n suma
	public class Suma extends ExpBin {
		public Suma(Exp opnd1, Exp opnd2) {
			this(opnd1, opnd2, null);
		}

		public Suma(Exp opnd1, Exp opnd2, String enlaceFuente) {
			super(opnd1, opnd2, enlaceFuente);
		}

		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	// Expresi�n resta
	public class Resta extends ExpBin {
		public Resta(Exp opnd1, Exp opnd2) {
			this(opnd1, opnd2, null);
		}
		
		public Resta(Exp opnd1, Exp opnd2, String enlaceFuente) {
			super(opnd1, opnd2, enlaceFuente);
		}

		public void procesaCon(Procesamiento p) {
			p.procesa(this);
			
		}
	}
	
	// Expresi�n multiplicaci�n
	public class Multiplicacion extends ExpBin {
		public Multiplicacion(Exp opnd1, Exp opnd2) {
			this(opnd1, opnd2, null);
		}
		
		public Multiplicacion(Exp opnd1, Exp opnd2, String enlaceFuente) {
			super(opnd1, opnd2, enlaceFuente);
		}

		public void procesaCon(Procesamiento p) {
			p.procesa(this);
			
		}
	}
	
	// Expresi�n divisi�n
	public class Division extends ExpBin {
		public Division(Exp opnd1, Exp opnd2) {
			this(opnd1, opnd2, null);
		}
		
		public Division(Exp opnd1, Exp opnd2, String enlaceFuente) {
			super(opnd1, opnd2, enlaceFuente);
		}

		public void procesaCon(Procesamiento p) {
			p.procesa(this);
			
		}
	}

	// Expresi�n resto entero
	public class RestoEntero extends ExpBin {
		public RestoEntero(Exp opnd1, Exp opnd2) {
			this(opnd1, opnd2, null);
		}
		
		public RestoEntero(Exp opnd1, Exp opnd2, String enlaceFuente) {
			super(opnd1, opnd2, enlaceFuente);
		}
		
		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	// Expresi�n cambio de signo entero
	public class CambioSigno extends ExpUn {
		
		public CambioSigno(Exp opnd) {
			this(opnd, null);
		}
		
		public CambioSigno(Exp opnd, String enlaceFuente) {
			super(opnd, enlaceFuente);
		}
		
		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public class ElementoCadena extends ExpBin {

		public ElementoCadena(Exp opnd1, Exp opnd2) {
			this(opnd1, opnd2, null);
		}
		public ElementoCadena(Exp opnd1, Exp opnd2, String enlaceFuente) {
			super(opnd1, opnd2, enlaceFuente);
		}

		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	// Expresi�n and
	public class And extends ExpBin {

		public And(Exp opnd1, Exp opnd2) {
			this(opnd1, opnd2, null);
		}

		public And(Exp opnd1, Exp opnd2, String enlaceFuente) {
			super(opnd1, opnd2, enlaceFuente);
		}

		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	// Expresi�n or
	public class Or extends ExpBin {

		public Or(Exp opnd1, Exp opnd2) {
			this(opnd1, opnd2, null);
		}

		public Or(Exp opnd1, Exp opnd2, String enlaceFuente) {
			super(opnd1, opnd2, enlaceFuente);
		}

		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	// Expresi�n not
	public class Not extends ExpUn {

		public Not(Exp opnd1) {
			this(opnd1, null);
		}

		public Not(Exp opnd1, String enlaceFuente) {
			super(opnd1, enlaceFuente);
		}

		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public class ToInt extends ExpUn {

		public ToInt(Exp opnd1) {
			this(opnd1, null);
		}

		public ToInt(Exp opnd1, String enlaceFuente) {
			super(opnd1, enlaceFuente);
		}

		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public class ToReal extends ExpUn {
		public ToReal(Exp opnd1) {
			this(opnd1, null);
		}

		public ToReal(Exp opnd1, String enlaceFuente) {
			super(opnd1, enlaceFuente);
		}

		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public class ToChar extends ExpUn {

		public ToChar(Exp opnd1) {
			this(opnd1, null);
		}

		public ToChar(Exp opnd1, String enlaceFuente) {
			super(opnd1, enlaceFuente);
		}

		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public class ToBool extends ExpUn {

		public ToBool(Exp opnd1) {
			this(opnd1, null);
		}

		public ToBool(Exp opnd1, String enlaceFuente) {
			super(opnd1, enlaceFuente);
		}

		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public class ToString extends ExpUn {

		public ToString(Exp opnd1) {
			this(opnd1, null);
		}

		public ToString(Exp opnd1, String enlaceFuente) {
			super(opnd1, enlaceFuente);
		}

		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public class Mayor extends ExpBin {
		public Mayor(Exp opnd1, Exp opnd2) {
			this(opnd1, opnd2, null);
		}
		
		public Mayor(Exp opnd1, Exp opnd2, String enlaceFuente) {
			super(opnd1, opnd2, enlaceFuente);
		}
		
		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public class Menor extends ExpBin {
		public Menor(Exp opnd1, Exp opnd2) {
			this(opnd1, opnd2, null);
		}
		
		public Menor(Exp opnd1, Exp opnd2, String enlaceFuente) {
			super(opnd1, opnd2, enlaceFuente);
		}
		
		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public class Igual extends ExpBin {
		public Igual(Exp opnd1, Exp opnd2) {
			this(opnd1, opnd2, null);
		}
		
		public Igual(Exp opnd1, Exp opnd2, String enlaceFuente) {
			super(opnd1, opnd2, enlaceFuente);
		}
		
		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public class Distinto extends ExpBin {
		public Distinto(Exp opnd1, Exp opnd2) {
			this(opnd1, opnd2, null);
		}
		
		public Distinto(Exp opnd1, Exp opnd2, String enlaceFuente) {
			super(opnd1, opnd2, enlaceFuente);
		}
		
		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public class MayorIgual extends ExpBin {
		public  MayorIgual(Exp opnd1, Exp opnd2) {
			this(opnd1, opnd2, null);
		}
		
		public MayorIgual(Exp opnd1, Exp opnd2, String enlaceFuente) {
			super(opnd1, opnd2, enlaceFuente);
		}
		
		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public class MenorIgual extends ExpBin {
		public  MenorIgual(Exp opnd1, Exp opnd2) {
			this(opnd1, opnd2, null);
		}
		
		public MenorIgual(Exp opnd1, Exp opnd2, String enlaceFuente) {
			super(opnd1, opnd2, enlaceFuente);
		}
		
		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	// Expresi�n concatena
	public class Concatena extends ExpBin {

		public Concatena(Exp opnd1, Exp opnd2) {
			this(opnd1, opnd2, null);
		}
		
		public Concatena(Exp opnd1, Exp opnd2, String enlaceFuente) {
			super(opnd1, opnd2, enlaceFuente);
		}

		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
		
	}
	 	
	public class Leer extends Var {

		public Leer(String var) {
			super(var);
		}
		
	}
	
	public class Escribir extends ExpUn {
		public Escribir(Exp exp) {
			this(exp, null);
		}
		
		public Escribir(Exp exp, String enlaceFuente) {
			super(exp, enlaceFuente);
		}

		public void procesaCon(Procesamiento p) {
			p.procesa(this);
		}
	}

	// M�todos que devuelven el programa, declaraciones, instrucciones, expresiones y tipos.
	public Prog prog(Dec[] decs, Inst i) {
		return new Prog(decs, i);
	}

	public Dec decvar(Tipo t, String v) {
		return new DecVar(t, v);
	}

	public Dec decvar(Tipo t, String v, String enlaceFuente) {
		return new DecVar(t, v, enlaceFuente);
	}

	public Inst iasig(String v, Exp e) {
		return new IAsig(v, e);
	}

	public Inst iasig(String v, Exp e, String enlaceFuente) {
		return new IAsig(v, e, enlaceFuente);
	}

	public Inst ibloque(Inst[] is) {
		return new IBloque(is);
	}

	public Exp var(String id) {
		return new Var(id);
	}

	public Exp var(String id, String enlaceFuente) {
		return new Var(id, enlaceFuente);
	}

	public Exp cteint(int val) {
		return new CteInt(val);
	}

	public Exp ctebool(boolean val) {
		return new CteBool(val);
	}
	
	public Exp ctechar(char val) {
		return new CteChar(val);
	}
	
	public Exp ctereal(double val) {
		return new CteReal(val);
	}
	
	public Exp ctestring(String val) {
		return new CteString(val);
	}

	public Exp suma(Exp exp1, Exp exp2) {
		return new Suma(exp1, exp2);
	}

	public Exp and(Exp exp1, Exp exp2) {
		return new And(exp1, exp2);
	}

	public Exp suma(Exp exp1, Exp exp2, String enlaceFuente) {
		return new Suma(exp1, exp2, enlaceFuente);
	}

	public Exp and(Exp exp1, Exp exp2, String enlaceFuente) {
		return new And(exp1, exp2, enlaceFuente);
	}

	public Tipo tipoInt() {
		return TENT;
	}

	public Tipo tipoBool() {
		return TBOOL;
	}
	
	public Tipo tipoChar() {
		return TCHAR;
	}
	
	public Tipo tipoReal() {
		return TREAL;
	}
	
	public Tipo tipoString() {
		return TSTRING;
	}

	public Tipo tipoOk() {
		return TOK;
	}

	public Tipo tipoError() {
		return TERROR;
	}

	public abstract Prog raiz();

}
