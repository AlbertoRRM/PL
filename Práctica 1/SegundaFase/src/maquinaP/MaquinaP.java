package maquinaP;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

// M�quina a pila
public class MaquinaP {
	public static class EAccesoIlegitimo extends RuntimeException {} 
	public static class EAccesoAMemoriaNoInicializada extends RuntimeException {} 
	public static class EAccesoFueraDeRango extends RuntimeException {} 
	   
	private GestorMemoriaDinamica gestorMemoriaDinamica;

	// Valores de la m�quina
	private class Valor {
		public int valorInt() {
			throw new EAccesoIlegitimo();
		}

		public boolean valorBool() {
			throw new EAccesoIlegitimo();
		}
		
		public char valorChar() {
			throw new EAccesoIlegitimo();
		}
		
		public double valorReal() {
			throw new EAccesoIlegitimo();
		}
		
		public String valorString() {
			throw new EAccesoIlegitimo();
		}
	}

	private class ValorInt extends Valor {
		private int valor;

		public ValorInt(int valor) {
			this.valor = valor;
		}

		public int valorInt() {
			return valor;
		}
		
		public double valorReal() {
			return valor;
		}

		public String toString() {
			return String.valueOf(valor);
		}
	}

	private class ValorBool extends Valor {
		private boolean valor;

		public ValorBool(boolean valor) {
			this.valor = valor;
		}

		public boolean valorBool() {
			return valor;
		}

		public String toString() {
			return String.valueOf(valor);
		}
	}
	
	private class ValorChar extends Valor {
		private char valor;
		
		public ValorChar(char valor) {
			this.valor = valor;
		}
		
		public char valorChar() {
			return valor;
		}
		
		public String toString() {
			return String.valueOf(valor);
		}
	}
	
	private class ValorReal extends Valor {
		private double valor;
		
		public ValorReal(double valor) {
			this.valor = valor;
		}
		
		public double valorReal() {
			return valor;
		}
		
		public String toString() {
			return String.valueOf(valor);
		}
	}
	
	private class ValorString extends Valor {
		private String valor;
		
		public ValorString(String valor) {
			this.valor = valor;
		}
		
		public String valorString() {
			return valor;
		}
	}

	private class ValorUnknown extends Valor {
		public String toString() {
			return "?";
		}
	}

	private List<Instruccion> codigoP; // Lista de instrucciones
	private Stack<Valor> pilaEvaluacion; // Pila de valores
	private Valor[] datos; //
	private int pc; // Contador de programa

	// Interfaz de las instrucciones
	public interface Instruccion {
		void ejecuta();
	}

	// Instrucciones
	private ISumaInt ISUMAINT;
	private ISumaReal ISUMAREAL;
	private IConcatena ICONCATENA;
	private IRestaInt IRESTAINT;
	private IRestaReal IRESTAREAL;
	private IMultInt IMULTINT;
	private IMultReal IMULTREAL;
	private IDivInt IDIVINT;
	private IDivReal IDIVREAL;
	private IRestoEntero IRESTOENTERO;
	private ICambioSignoInt ICAMBIOSIGNOINT;
	private ICambioSignoReal ICAMBIOSIGNOREAL;
	private IElementoCadena IELEMENTOCADENA;
	private IInt2Int IINT2INT;
	private IReal2Int IREAL2INT;
	private IBool2Int IBOOL2INT;
	private IChar2Int ICHAR2INT;
	private IInt2Real IINT2REAL;
	private IReal2Real IREAL2REAL;
	private IBool2Real IBOOL2REAL;
	private IChar2Real ICHAR2REAL;
	private IInt2Char IINT2CHAR;
	private IInt2Bool IINT2BOOL;
	private IChar2String ICHAR2STRING;
	private IIgualNum IIGUALNUM;
	private IIgualChar IIGUALCHAR;
	private IIgualBool IIGUALBOOL;
	private IIgualString IIGUALSTRING;
	private IMayorNum IMAYORNUM;
	private IMayorChar IMAYORCHAR;
	private IMayorBool IMAYORBOOL;
	private IMayorString IMAYORSTRING;
	private IMenorNum IMENORNUM;
	private IMenorChar IMENORCHAR;
	private IMenorBool IMENORBOOL;
	private IMenorString IMENORSTRING;
	private IMayorIgualNum IMAYORIGUALNUM;
	private IMayorIgualChar IMAYORIGUALCHAR;
	private IMayorIgualBool IMAYORIGUALBOOL;
	private IMayorIgualString IMAYORIGUALSTRING;
	private IMenorIgualNum IMENORIGUALNUM;
	private IMenorIgualChar IMENORIGUALCHAR;
	private IMenorIgualBool IMENORIGUALBOOL;
	private IMenorIgualString IMENORIGUALSTRING;
	private IDistintoNum IDISTINTONUM;
	private IDistintoChar IDISTINTOCHAR;
	private IDistintoBool IDISTINTOBOOL;
	private IDistintoString IDISTINTOSTRING;
	private IAnd IAND;
	private IOr IOR;
	private INot INOT;
	private ILeer ILEER;
	private IEscribir IESCRIBIR;
    private IApilaind IAPILAIND;
    private IDesapilaind IDESAPILAIND;
	
	// Implementaci�n de instrucciones
	private class ISumaInt implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorInt(opnd1.valorInt() + opnd2.valorInt()));
			pc++;
		}
		
		public String toString() {
			return "suma entera";
		};
	}
	
	private class ISumaReal implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorReal(opnd1.valorReal() + opnd2.valorReal()));
			pc++;
		}
		
		public String toString() {
			return "suma real";
		};
	}
	
	private class IRestaInt implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorInt(opnd1.valorInt() - opnd2.valorInt()));
			pc++;
		}
		
		public String toString() {
			return "resta entera";
		};
	}

	private class IRestaReal implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorReal(opnd1.valorReal() - opnd2.valorReal()));
			pc++;
		}
		
		public String toString() {
			return "resta real";
		};
	}

	private class IMultInt implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorInt(opnd1.valorInt() * opnd2.valorInt()));
			pc++;
		}
		
		public String toString() {
			return "multiplicacion entera";
		};
	}
	
	private class IMultReal implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorReal(opnd1.valorReal() * opnd2.valorReal()));
			pc++;
		}
		
		public String toString() {
			return "multiplicacion real";
		};
	}
	
	private class IDivInt implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorInt(opnd1.valorInt() / opnd2.valorInt()));
			pc++;
		}
		
		public String toString() {
			return "division entera";
		};
	}
	
	private class IDivReal implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorReal(opnd1.valorReal() / opnd2.valorReal()));
			pc++;
		}
		
		public String toString() {
			return "division real";
		};
	}
	
	private class IConcatena implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorString(opnd1.valorString() + opnd2.valorString()));
			pc++;
		}
		
		public String toString() {
			return "concatenacion";
		};
	}
	
	private class IRestoEntero implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorInt(opnd1.valorInt() % opnd2.valorInt()));
			pc++;
		}
		
		public String toString() {
			return "concatenacion";
		};
	}
	
	private class ICambioSignoInt implements Instruccion {
		public void ejecuta() {
			Valor opnd1 = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorInt(-opnd1.valorInt()));
			pc++;
		}
		
		public String toString() {
			return "cambio signo entero";
		};
	}
	
	private class ICambioSignoReal implements Instruccion {
		public void ejecuta() {
			Valor opnd1 = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorReal(-opnd1.valorReal()));
			pc++;
		}
		
		public String toString() {
			return "cambio signo real";
		};
	}
	
	private class IElementoCadena implements Instruccion {
		public void ejecuta() {
			Valor indice = pilaEvaluacion.pop();
			Valor cadena = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorChar(cadena.valorString().charAt(indice.valorInt())));
			pc++;
		}
		
		public String toString() {
			return "elemento cadena";
		};
	}
	
	private class IInt2Int implements Instruccion {
		public void ejecuta() {
			Valor elemento = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(elemento);
			pc++;
		}
		
		public String toString() {
			return "int a int";
		};
	}
	
	private class IReal2Int implements Instruccion {
		public void ejecuta() {
			Valor elemento = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorInt((Integer)elemento.valorInt()));
			pc++;
		}
		
		public String toString() {
			return "real a int";
		};
	}
	
	private class IBool2Int implements Instruccion {
		public void ejecuta() {
			Valor elemento = pilaEvaluacion.pop();
			Valor resul;
			
			if(elemento.valorBool() == true)
				resul = new ValorInt(1);
			else 
				resul = new ValorInt(0);
		
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "bool a int";
		};
	}
	
	private class IChar2Int implements Instruccion {
		public void ejecuta() {
			Valor elemento = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorInt((Integer) elemento.valorInt()));
			pc++;
		}
		
		public String toString() {
			return "char a int";
		};
	}
	
	private class IInt2Real implements Instruccion {
		public void ejecuta() {
			Valor elemento = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorReal((Double) elemento.valorReal()));
			pc++;
		}
		
		public String toString() {
			return "int a real";
		};
	}
	
	private class IReal2Real implements Instruccion {
		public void ejecuta() {
			Valor elemento = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorReal( elemento.valorReal()));
			pc++;
		}
		
		public String toString() {
			return "real a real";
		};
	}
	
	private class IBool2Real implements Instruccion {
		public void ejecuta() {
			Valor elemento = pilaEvaluacion.pop();
			Valor resul;
			
			if(elemento.valorBool() == true)
				resul = new ValorReal(1.0);
			else 
				resul = new ValorReal(0.0);
			
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "bool a real";
		};
	}
	
	private class IChar2Real implements Instruccion {
		public void ejecuta() {
			Valor elemento = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorReal((Double) elemento.valorReal()));
			pc++;
		}
		
		public String toString() {
			return "char a real";
		};
	}
	
	private class IInt2Char implements Instruccion {
		public void ejecuta() {
			Valor elemento = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorChar((char) elemento.valorChar()));
			pc++;
		}
		
		public String toString() {
			return "int a char";
		};
	}
	
	private class IInt2Bool implements Instruccion {
		public void ejecuta() {
			Valor elemento = pilaEvaluacion.pop();
			Valor resul;
			
			if(elemento.valorInt() == 0)
				resul = new ValorBool(false);
			else
				resul = new ValorBool(true);
			
			pilaEvaluacion.push(resul);
			pc++;
		}	
		
		public String toString() {
			return "int a bool";
		};
	}
	
	private class IChar2String implements Instruccion {
		public void ejecuta() {
			Valor elemento = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorString((String) elemento.valorString()));
			pc++;
		}
		
		public String toString() {
			return "char a string";
		};
	}
	
	private class IAnd implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorBool(opnd1.valorBool() && opnd2.valorBool()));
			pc++;
		}

		public String toString() {
			return "and";
		};
	}
	
	private class IOr implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorBool(opnd1.valorBool() || opnd2.valorBool()));
			pc++;
		}

		public String toString() {
			return "or";
		};
	}
	
	private class INot implements Instruccion {
		public void ejecuta() {
			Valor opnd1 = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorBool(!opnd1.valorBool()));
			pc++;
		}

		public String toString() {
			return "or";
		};
	}
	
	private class IMayorNum implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;
			
			if((opnd1.valorInt() > opnd2.valorInt()) || (opnd1.valorReal() > opnd2.valorInt()) ||
					(opnd1.valorInt() > opnd2.valorReal()) || (opnd1.valorReal() > opnd2.valorReal()))
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);
			
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "mayor   (num�rico)";
		};
	}
	
	private class IMayorChar implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;
			
			if((Integer)opnd1.valorInt() > (Integer)opnd2.valorInt())
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);
			
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "mayor   (char)";
		};
	}
	
	private class IMayorBool implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;
			
			if(opnd1.valorBool() == true && opnd2.valorBool() == false)
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);
			
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "mayor   (booleano)";
		};
	}
	
	private class IMayorString implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;
			
			if(opnd1.valorString().compareTo(opnd2.valorString()) < 0)
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);
			
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "mayor   (string)";
		};
	}
	
	private class IMenorNum implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;
			
			if((opnd1.valorInt() < opnd2.valorInt()) || (opnd1.valorReal() < opnd2.valorInt()) ||
					(opnd1.valorInt() < opnd2.valorReal()) || (opnd1.valorReal() < opnd2.valorReal()))
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);
		
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "menor   (num�rico)";
		};
	}
	
	private class IMenorChar implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;
			
			if((Integer)opnd1.valorInt() < (Integer)opnd2.valorInt())
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);
			
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "menor   (char)";
		};
	}
	
	private class IMenorBool implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;
			

			if(opnd1.valorBool() == false && opnd2.valorBool() == true)
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);
			
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "menor   (booleano)";
		};
	}
	
	private class IMenorString implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;
			
			if(opnd1.valorString().compareTo(opnd2.valorString()) > 0)
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);
			
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "menor   (string)";
		};
	}
	
	private class IIgualNum implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;
			

			if((opnd1.valorInt() == opnd2.valorInt()) || (opnd1.valorReal() == opnd2.valorInt()) ||
					(opnd1.valorInt() == opnd2.valorReal()) || (opnd1.valorReal() == opnd2.valorReal()))
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);
			
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "igual   (num�rico)";
		};
	}
	
	private class IIgualChar implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;
			
			if((Integer)opnd1.valorInt() == (Integer)opnd2.valorInt())
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);

			
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "igual   (char)";
		};
	}
	
	private class IIgualBool implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;

			if(opnd1.valorBool() == opnd2.valorBool())
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);
			
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "igual   (booleano)";
		};
	}
	
	private class IIgualString implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;

			if(opnd1.valorString().compareTo(opnd2.valorString()) == 0)
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);

			
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "igual   (string)";
		};
	}
	
	private class IMayorIgualNum implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;
			
			if((opnd1.valorInt() >= opnd2.valorInt()) || (opnd1.valorReal() >= opnd2.valorInt()) ||
					(opnd1.valorInt() >= opnd2.valorReal()) || (opnd1.valorReal() >= opnd2.valorReal()))
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);
			
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "mayor o igual   (num�rico)";
		};
	}
	
	private class IMayorIgualChar implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;
			
			if((Integer)opnd1.valorInt() >= (Integer)opnd2.valorInt())
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);
			
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "mayor o igual   (char)";
		};
	}
	
	private class IMayorIgualBool implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;
			
			if((opnd1.valorBool() == true && opnd2.valorBool() == false) ||
					(opnd1.valorBool() == true && opnd2.valorBool() == true) ||
						(opnd1.valorBool() == false && opnd2.valorBool() == false))
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);
			
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "mayor o igual   (booleano)";
		};
	}
	
	private class IMayorIgualString implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;
			
			if(opnd1.valorString().compareTo(opnd2.valorString()) <= 0)
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);

			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "mayor o igual   (string)";
		};
	}
	
	private class IMenorIgualNum implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;
			
			if((opnd1.valorInt() <= opnd2.valorInt()) || (opnd1.valorReal() <= opnd2.valorInt()) ||
					(opnd1.valorInt() <= opnd2.valorReal()) || (opnd1.valorReal() <= opnd2.valorReal()))
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);
			
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "menor o igual   (num�rico)";
		};
	}
	
	private class IMenorIgualChar implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;
			
			if((Integer)opnd1.valorInt() <= (Integer)opnd2.valorInt())
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);
			
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "menor o igual   (char)";
		};
	}
	
	private class IMenorIgualBool implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;
			
			if((opnd1.valorBool() == false && opnd2.valorBool() == true) ||
					(opnd1.valorBool() == true && opnd2.valorBool() == true) ||
						(opnd1.valorBool() == false && opnd2.valorBool() == false))
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);
			
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "menor o igual   (bool)";
		};
	}
	
	private class IMenorIgualString implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;
	
			if(opnd1.valorString().compareTo(opnd2.valorString()) >= 0)
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);
			
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "menor o igual   (string)";
		};
	}
	
	private class IDistintoNum implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;
			
			if((opnd1.valorInt() != opnd2.valorInt()) || (opnd1.valorReal() != opnd2.valorInt()) ||
					(opnd1.valorInt() != opnd2.valorReal()) || (opnd1.valorReal() != opnd2.valorReal()))
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);
			
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "distinto (num�rico)";
		};
	}
	
	private class IDistintoChar implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;
			
			if((Integer)opnd1.valorInt() != (Integer)opnd2.valorInt())
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);
			
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "distinto (char)";
		};
	}
	
	private class IDistintoBool implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;
			
			if(opnd1.valorBool() != opnd2.valorBool())
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);
			
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "distinto (bool)";
		};
	}
	
	private class IDistintoString implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			Valor resul;
			
			if(opnd1.valorString().compareTo(opnd2.valorString()) != 0)
				resul = new ValorBool(true);
			else
				resul = new ValorBool(false);
			
			pilaEvaluacion.push(resul);
			pc++;
		}
		
		public String toString() {
			return "distinto (string)";
		};
	}
	
	private class ILeer implements Instruccion {
		public void ejecuta() {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
	        Valor valor;
	        
	        if(scan.hasNextInt())
	        	valor = new ValorInt(scan.nextInt());
	        else if(scan.hasNextDouble())
	        	valor = new ValorReal(scan.nextDouble());
	        else if(scan.hasNextByte())
	        	valor = new ValorChar((char)scan.nextByte());
	        else if(scan.hasNextBoolean())
	        	valor = new ValorBool(scan.nextBoolean());
	        else 
	        	valor = new ValorString(scan.next());
	        

			pilaEvaluacion.push(valor);
			pc++;
			
		}
		
		public String toString() {
			return "leer";
		}
	}
	
	private class IEscribir implements Instruccion {
		public void ejecuta() {
			Valor valor = pilaEvaluacion.pop();
			
			System.out.println(valor);
		}
		
		public String toString() {
			return "escribir";
		}
	}

	private class IApilaInt implements Instruccion {
		private int valor;

		public IApilaInt(int valor) {
			this.valor = valor;
		}

		public void ejecuta() {
			pilaEvaluacion.push(new ValorInt(valor));
			pc++;
		}

		public String toString() {
			return "apilaInt(" + valor + ")";
		};
	}

	private class IApilaBool implements Instruccion {
		private boolean valor;

		public IApilaBool(boolean valor) {
			this.valor = valor;
		}

		public void ejecuta() {
			pilaEvaluacion.push(new ValorBool(valor));
			pc++;
		}

		public String toString() {
			return "apilaBool(" + valor + ")";
		};
	}
	
	private class IApilaChar implements Instruccion {
		private char valor;
		
		public IApilaChar(char valor) {
			this.valor = valor;
		}
		
		public void ejecuta() {
			pilaEvaluacion.push(new ValorChar(valor));
		}
		
		public String toString() {
			return "apilaChar(" + valor + ")";
		};
	}
	
	private class IApilaReal implements Instruccion {
		private double valor;
		
		public IApilaReal(double valor) {
			this.valor = valor;
		}
		
		public void ejecuta() {
			pilaEvaluacion.push(new ValorReal(valor));
		}
		
		public String toString() {
			return "apilaReal(" + valor + ")";
		};
	}
	
	private class IApilaString implements Instruccion {
		private String valor;
		
		public IApilaString(String valor) {
			this.valor = valor;
		}
		
		public void ejecuta() {
			pilaEvaluacion.push(new ValorString(valor));
		}
		
		public String toString() {
			return "apilaString (" + valor + ")";
		};
	}
	
	 private class IIrA implements Instruccion {
	      private int dir;
	      public IIrA(int dir) {
	        this.dir = dir;  
	      }
	      public void ejecuta() {
	            pc=dir;
	      } 
	      public String toString() {return "ira("+dir+")";};
	   }

	      private class IIrF implements Instruccion {
	      private int dir;
	      public IIrF(int dir) {
	        this.dir = dir;  
	      }
	      public void ejecuta() {
	         if(! pilaEvaluacion.pop().valorBool()) { 
	            pc=dir;
	         }   
	         else {
	            pc++; 
	         }
	      } 
	      public String toString() {return "irf("+dir+")";};
	   }
	      
      private class IMueve implements Instruccion {
          private int tam;
          public IMueve(int tam) {
            this.tam = tam;  
          }
          public void ejecuta() {
                int dirOrigen = pilaEvaluacion.pop().valorInt();
                int dirDestino = pilaEvaluacion.pop().valorInt();
                if ((dirOrigen + (tam-1)) >= datos.length)
                    throw new EAccesoFueraDeRango();
                if ((dirDestino + (tam-1)) >= datos.length)
                    throw new EAccesoFueraDeRango();
                for (int i=0; i < tam; i++) 
                    datos[dirDestino+i] = datos[dirOrigen+i]; 
                pc++;
          } 
          public String toString() {return "mueve("+tam+")";};
       }
       

       private class IApilaind implements Instruccion {
          public void ejecuta() {
            int dir = pilaEvaluacion.pop().valorInt();
            if (dir >= datos.length) throw new EAccesoFueraDeRango();
            if (datos[dir] == null) throw new EAccesoAMemoriaNoInicializada();
            pilaEvaluacion.push(datos[dir]);
            pc++;
          } 
          public String toString() {return "apilaind";};
       }

 
       private class IDesapilaind implements Instruccion {
          public void ejecuta() {
            Valor valor = pilaEvaluacion.pop();
            int dir = pilaEvaluacion.pop().valorInt();
            if (dir >= datos.length) throw new EAccesoFueraDeRango();
            datos[dir] = valor;
            pc++;
          } 
          public String toString() {return "desapilaind";};
       }

       private class IAlloc implements Instruccion {
          private int tam;
          public IAlloc(int tam) {
            this.tam = tam;  
          }
          public void ejecuta() {
            int inicio = gestorMemoriaDinamica.alloc(tam);
            pilaEvaluacion.push(new ValorInt(inicio));
            pc++;
          } 
          public String toString() {return "alloc("+tam+")";};
       }

       private class IDealloc implements Instruccion {
          private int tam;
          public IDealloc(int tam) {
            this.tam = tam;  
          }
          public void ejecuta() {
            int inicio = pilaEvaluacion.pop().valorInt();
            gestorMemoriaDinamica.free(inicio,tam);
            pc++;
          } 
          public String toString() {return "dealloc("+tam+")";};
       }


	public Instruccion sumaInt() {
		return ISUMAINT;
	}
	
	public Instruccion sumaReal() {
		return ISUMAREAL;
	}

	public Instruccion restaInt() {
		return IRESTAINT;
	}
	
	public Instruccion restaReal() {
		return IRESTAREAL;
	}
	
	public Instruccion multiplicacionInt() {
		return IMULTINT;
	}
	
	public Instruccion multiplicacionReal() {
		return IMULTREAL;
	}
	
	public Instruccion divisionInt() {
		return IDIVINT;
	}
	
	public Instruccion divisionReal() {
		return IDIVREAL;
	}
	
	public Instruccion concatena() {
		return ICONCATENA;
	}
	
	public Instruccion restoEntero() {
		return IRESTOENTERO;
	}
	
	public Instruccion cambioSignoInt() {
		return ICAMBIOSIGNOINT;
	}
	
	public Instruccion cambioSignoReal() {
		return ICAMBIOSIGNOREAL; 
	}
	
	public Instruccion elementoCadena() {
		return IELEMENTOCADENA;
	}
	
	public Instruccion int2int() {
		return IINT2INT;
	}
	
	public Instruccion real2int() {
		return IREAL2INT;
	}
	
	public Instruccion bool2int() {
		return IBOOL2INT;
	}
	
	public Instruccion char2int() {
		return ICHAR2INT;
	}
	
	public Instruccion int2real() {
		return IINT2REAL;
	}
	
	public Instruccion real2real() {
		return IREAL2REAL;
	}
	
	public Instruccion bool2real() {
		return IBOOL2REAL;
	}
	
	public Instruccion char2real() {
		return ICHAR2REAL;
	}
	
	public Instruccion int2char() {
		return IINT2CHAR;
	}
	
	public Instruccion  int2bool() {
		return IINT2BOOL;
	}
	
	public Instruccion char2string() {
		return ICHAR2STRING;
	}
	
	public Instruccion mayorNum() {
		return IMAYORNUM;
	}
	
	public Instruccion mayorChar() {
		return IMAYORCHAR;
	}
	
	public Instruccion mayorBool() {
		return IMAYORBOOL;
	}
	
	public Instruccion mayorString() {
		return IMAYORSTRING;
	}
	
	public Instruccion menorNum() {
		return IMENORNUM;
	}
	
	public Instruccion menorChar() {
		return IMENORCHAR;
	}
	
	public Instruccion menorBool() {
		return IMENORBOOL;
	}
	
	public Instruccion menorString() {
		return IMENORSTRING;
	}
	
	public Instruccion igualNum() {
		return IIGUALNUM;
	}
	
	public Instruccion igualChar() {
		return IIGUALCHAR;
	}
	
	public Instruccion igualBool() {
		return IIGUALBOOL;
	}
	
	public Instruccion igualString() {
		return IIGUALSTRING;
	}
	
	public Instruccion distintoNum() {
		return IDISTINTONUM;
	}
	
	public Instruccion distintoChar() {
		return IDISTINTOCHAR;
	}
	
	public Instruccion distintoBool() {
		return IDISTINTOBOOL;
	}
	
	public Instruccion distintoString() {
		return IDISTINTOSTRING;
	}
	
	public Instruccion mayorIgualNum() {
		return IMAYORIGUALNUM;
	}
	
	public Instruccion mayorIgualChar() {
		return IMAYORIGUALCHAR;
	}
	
	public Instruccion mayorIgualBool() {
		return IMAYORIGUALBOOL;
	}
	
	public Instruccion mayorIgualString() {
		return IMAYORIGUALSTRING;
	}
	
	public Instruccion menorIgualNum() {
		return IMENORIGUALNUM;
	}
	
	public Instruccion menorIgualChar() {
		return IMENORIGUALCHAR;
	}
	
	public Instruccion menorIgualBool() {
		return IMENORIGUALBOOL;
	}
	
	public Instruccion menorIgualString() {
		return IMENORIGUALSTRING;
	}
	
	public Instruccion and() {
		return IAND;
	}
	
	public Instruccion or() {
		return IOR;
	}
	
	public Instruccion not() {
		return INOT;
	}
	
	public Instruccion leer() {
		return ILEER;
	}
	
	public Instruccion escribir() {
		return IESCRIBIR;
	}

	public Instruccion apilaInt(int val) {
		return new IApilaInt(val);
	}

	public Instruccion apilaBool(boolean val) {
		return new IApilaBool(val);
	}

	public Instruccion apilaChar(char val) {
		return new IApilaChar(val);
	}
	
	public Instruccion apilaReal(double val) {
		return new IApilaReal(val);
	}
	
	public Instruccion apilaString(String val) {
		return new IApilaString(val);
	}
	
	public Instruccion apilaInd() {
		return IAPILAIND;
	}
	public Instruccion desapilaInd() {
		return IDESAPILAIND;
	}
	public Instruccion mueve(int tam) {
		return new IMueve(tam);
	}
	
	public Instruccion irA(int dir) {
		return new IIrA(dir);
	}
	
	public Instruccion irF(int dir) {
		return new IIrF(dir);
	}
	
	 public Instruccion alloc(int tam) {
		 return new IAlloc(tam);
	}
	 
	 public Instruccion dealloc(int tam) {
	   return new IDealloc(tam);
    } 

	public void addInstruccion(Instruccion i) {
		codigoP.add(i);
	}

	public MaquinaP(int tamdatos, int tamheap) {
		this.codigoP = new ArrayList<>();
		pilaEvaluacion = new Stack<>();
		datos = new Valor[tamdatos];
		this.pc = 0;
		ISUMAINT = new ISumaInt();
		ISUMAREAL = new ISumaReal();
		IRESTAINT = new IRestaInt();
		IRESTAREAL = new IRestaReal();
		IMULTINT = new IMultInt();
		IMULTREAL = new IMultReal();
		IDIVINT = new IDivInt();
		IDIVREAL = new IDivReal();
		ICONCATENA = new IConcatena();
		IRESTOENTERO = new IRestoEntero();
		ICAMBIOSIGNOINT = new ICambioSignoInt();
		ICAMBIOSIGNOREAL = new ICambioSignoReal();
		IELEMENTOCADENA = new IElementoCadena();
		IINT2INT = new IInt2Int();
		IREAL2INT = new IReal2Int();
		IBOOL2INT = new IBool2Int();
		ICHAR2INT = new IChar2Int();
		IINT2REAL = new IInt2Real();
		IREAL2REAL = new IReal2Real();
		IBOOL2REAL = new IBool2Real();
		ICHAR2REAL = new IChar2Real();
		IINT2CHAR = new IInt2Char();
		IINT2BOOL = new IInt2Bool();
		ICHAR2STRING = new IChar2String();
		IIGUALNUM = new IIgualNum();
		IIGUALCHAR = new IIgualChar();
		IIGUALBOOL = new IIgualBool();
		IIGUALSTRING = new IIgualString();
		IMAYORNUM = new IMayorNum();
		IMAYORCHAR = new IMayorChar();
		IMAYORBOOL = new IMayorBool();
		IMAYORSTRING = new IMayorString();
		IMENORNUM = new IMenorNum();
		IMENORCHAR = new IMenorChar();
		IMENORBOOL = new IMenorBool();
		IMENORSTRING = new IMenorString();
		IMAYORIGUALNUM = new IMayorIgualNum();
		IMAYORIGUALCHAR = new IMayorIgualChar();
		IMAYORIGUALBOOL = new IMayorIgualBool();
		IMAYORIGUALSTRING = new IMayorIgualString();
		IMENORIGUALNUM = new IMenorIgualNum();
		IMENORIGUALCHAR = new IMenorIgualChar();
		IMENORIGUALBOOL = new IMenorIgualBool();
		IMENORIGUALSTRING = new IMenorIgualString();
		IDISTINTONUM = new IDistintoNum();
		IDISTINTOBOOL = new IDistintoBool();
		IDISTINTOCHAR = new IDistintoChar();
		IDISTINTOSTRING = new IDistintoString();
		IAND = new IAnd();
		IOR = new IOr();
		INOT = new INot();
		IAPILAIND = new IApilaind();
		IDESAPILAIND = new IDesapilaind();
		gestorMemoriaDinamica = new GestorMemoriaDinamica(tamdatos, (tamdatos + tamheap) - 1);
	}

	public void ejecuta() {
		while (pc != codigoP.size()) {
			codigoP.get(pc).ejecuta();
		}
	}

	public void muestraCodigo() {
		System.out.println("CodigoP");
		for (int i = 0; i < codigoP.size(); i++) {
			System.out.println(" " + i + ":" + codigoP.get(i));
		}
	}

	public void muestraEstado() {
		System.out.println("Pila de evaluacion");
		for (int i = 0; i < pilaEvaluacion.size(); i++) {
			System.out.println(" " + i + ":" + pilaEvaluacion.get(i));
		}
		System.out.println("Datos");
		for (int i = 0; i < datos.length; i++) {
			System.out.println(" " + i + ":" + datos[i]);
		}
		System.out.println("PC:" + pc);
	}
}
