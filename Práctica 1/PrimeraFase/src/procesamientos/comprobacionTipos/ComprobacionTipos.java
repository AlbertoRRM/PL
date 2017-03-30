package procesamientos.comprobacionTipos;

import errores.Errores;
import procesamientos.Procesamiento;
import programa.Programa;
import programa.Programa.*;

public class ComprobacionTipos extends Procesamiento {
	private final static String ERROR_TIPO_OPERANDOS = "Los tipos de los operandos no son correctos";
	private final static String ERROR_ASIG = "Tipos no compatibles en asignacion";
	private final static String ERROR_COND = "Tipo erroneo en condicion";
	private Programa programa;
	private Errores errores;

	public ComprobacionTipos(Programa programa, Errores errores) {
		this.programa = programa;
		this.errores = errores;
	}

	public void procesa(Var exp) {
		exp.ponTipo(exp.declaracion().tipoDec());
	}

	public void procesa(CteInt exp) {
		exp.ponTipo(programa.tipoInt());
	}

	public void procesa(CteBool exp) {
		exp.ponTipo(programa.tipoBool());
	}

	public void procesa(CteChar exp) {
		exp.ponTipo(programa.tipoChar());
	}

	public void procesa(CteReal exp) {
		exp.ponTipo(programa.tipoReal());
	}

	public void procesa(CteString exp) {
		exp.ponTipo(programa.tipoString());
	}

	public void procesa(Suma exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if (exp.opnd1().tipo().equals(programa.tipoInt()) && exp.opnd2().tipo().equals(programa.tipoInt())) {
			exp.ponTipo(programa.tipoInt());
		} else if (exp.opnd1().tipo().equals(programa.tipoReal()) && exp.opnd2().tipo().equals(programa.tipoReal())) {
			exp.ponTipo(programa.tipoReal());
		} else {
			if (!exp.opnd1().tipo().equals(programa.tipoError()) && !exp.opnd2().tipo().equals(programa.tipoError()))
				errores.msg(exp.enlaceFuente() + ":" + ERROR_TIPO_OPERANDOS);
			exp.ponTipo(programa.tipoError());
		}
	}

	public void procesa(Resta exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if (exp.opnd1().tipo().equals(programa.tipoInt()) && exp.opnd2().tipo().equals(programa.tipoInt())) {
			exp.ponTipo(programa.tipoInt());
		} else if (exp.opnd1().tipo().equals(programa.tipoReal()) && exp.opnd2().tipo().equals(programa.tipoReal())) {
			exp.ponTipo(programa.tipoReal());
		} else {
			if (!exp.opnd1().tipo().equals(programa.tipoError()) && !exp.opnd2().tipo().equals(programa.tipoError()))
				errores.msg(exp.enlaceFuente() + ":" + ERROR_TIPO_OPERANDOS);
			exp.ponTipo(programa.tipoError());
		}
	}

	public void procesa(Multiplicacion exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if (exp.opnd1().tipo().equals(programa.tipoInt()) && exp.opnd2().tipo().equals(programa.tipoInt())) {
			exp.ponTipo(programa.tipoInt());
		} else if (exp.opnd1().tipo().equals(programa.tipoReal()) && exp.opnd2().tipo().equals(programa.tipoReal())) {
			exp.ponTipo(programa.tipoReal());
		} else {
			if (!exp.opnd1().tipo().equals(programa.tipoError()) && !exp.opnd2().tipo().equals(programa.tipoError()))
				errores.msg(exp.enlaceFuente() + ":" + ERROR_TIPO_OPERANDOS);
			exp.ponTipo(programa.tipoError());
		}
	}

	public void procesa(Division exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if (exp.opnd1().tipo().equals(programa.tipoInt()) && exp.opnd2().tipo().equals(programa.tipoInt()))
			exp.ponTipo(programa.tipoInt());
		else if (exp.opnd1().tipo().equals(programa.tipoReal()) && exp.opnd2().tipo().equals(programa.tipoReal()))
			exp.ponTipo(programa.tipoReal());
		else {
			if (!exp.opnd1().tipo().equals(programa.tipoError()) && !exp.opnd2().tipo().equals(programa.tipoError()))
				errores.msg(exp.enlaceFuente() + ":" + ERROR_TIPO_OPERANDOS);
			exp.ponTipo(programa.tipoError());
		}
	}

	public void procesa(RestoEntero exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if (exp.opnd1().tipo().equals(programa.tipoInt()) && exp.opnd2().tipo().equals(programa.tipoInt()))
			exp.ponTipo(programa.tipoInt());
		else {
			if (!exp.opnd1().tipo().equals(programa.tipoError()) && !exp.opnd2().tipo().equals(programa.tipoError()))
				errores.msg(exp.enlaceFuente() + ":" + ERROR_TIPO_OPERANDOS);
			exp.ponTipo(programa.tipoError());
		}
	}

	public void procesa(CambioSigno exp) {
		exp.opnd().procesaCon(this);

		if (exp.opnd().equals(programa.tipoInt()))
			exp.ponTipo(programa.tipoInt());
		else if (exp.opnd().equals(programa.tipoReal()))
			exp.ponTipo(programa.tipoReal());
		else
			exp.ponTipo(programa.tipoError());
	}

	public void procesa(Mayor exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if (exp.opnd1().equals(programa.tipoInt()) && exp.opnd2().equals(programa.tipoInt()))
			exp.ponTipo(programa.tipoInt());
		else if (exp.opnd1().equals(programa.tipoReal()) && exp.opnd2().equals(programa.tipoReal()))
			exp.opnd1().ponTipo(programa.tipoReal());
		else if (exp.opnd1().equals(programa.tipoBool()) && exp.opnd2().equals(programa.tipoBool()))
			exp.ponTipo(programa.tipoBool());
		else if (exp.opnd1().equals(programa.tipoChar()) && exp.opnd2().equals(programa.tipoChar()))
			exp.ponTipo(programa.tipoChar());
		else if (exp.opnd1().equals(programa.tipoString()) && exp.opnd2().equals(programa.tipoString()))
			exp.ponTipo(programa.tipoString());
		else
			exp.ponTipo(programa.tipoError());
	}

	public void procesa(Menor exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if (exp.opnd1().equals(programa.tipoInt()) && exp.opnd2().equals(programa.tipoInt()))
			exp.ponTipo(programa.tipoInt());
		else if (exp.opnd1().equals(programa.tipoReal()) && exp.opnd2().equals(programa.tipoReal()))
			exp.opnd1().ponTipo(programa.tipoReal());
		else if (exp.opnd1().equals(programa.tipoBool()) && exp.opnd2().equals(programa.tipoBool()))
			exp.ponTipo(programa.tipoBool());
		else if (exp.opnd1().equals(programa.tipoChar()) && exp.opnd2().equals(programa.tipoChar()))
			exp.ponTipo(programa.tipoChar());
		else if (exp.opnd1().equals(programa.tipoString()) && exp.opnd2().equals(programa.tipoString()))
			exp.ponTipo(programa.tipoString());
		else
			exp.ponTipo(programa.tipoError());
	}

	public void procesa(MayorIgual exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if (exp.opnd1().equals(programa.tipoInt()) && exp.opnd2().equals(programa.tipoInt()))
			exp.ponTipo(programa.tipoInt());
		else if (exp.opnd1().equals(programa.tipoReal()) && exp.opnd2().equals(programa.tipoReal()))
			exp.opnd1().ponTipo(programa.tipoReal());
		else if (exp.opnd1().equals(programa.tipoBool()) && exp.opnd2().equals(programa.tipoBool()))
			exp.ponTipo(programa.tipoBool());
		else if (exp.opnd1().equals(programa.tipoChar()) && exp.opnd2().equals(programa.tipoChar()))
			exp.ponTipo(programa.tipoChar());
		else if (exp.opnd1().equals(programa.tipoString()) && exp.opnd2().equals(programa.tipoString()))
			exp.ponTipo(programa.tipoString());
		else
			exp.ponTipo(programa.tipoError());
	}

	public void procesa(MenorIgual exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if (exp.opnd1().equals(programa.tipoInt()) && exp.opnd2().equals(programa.tipoInt()))
			exp.ponTipo(programa.tipoInt());
		else if (exp.opnd1().equals(programa.tipoReal()) && exp.opnd2().equals(programa.tipoReal()))
			exp.opnd1().ponTipo(programa.tipoReal());
		else if (exp.opnd1().equals(programa.tipoBool()) && exp.opnd2().equals(programa.tipoBool()))
			exp.ponTipo(programa.tipoBool());
		else if (exp.opnd1().equals(programa.tipoChar()) && exp.opnd2().equals(programa.tipoChar()))
			exp.ponTipo(programa.tipoChar());
		else if (exp.opnd1().equals(programa.tipoString()) && exp.opnd2().equals(programa.tipoString()))
			exp.ponTipo(programa.tipoString());
		else
			exp.ponTipo(programa.tipoError());
	}

	public void procesa(Igual exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if (exp.opnd1().equals(programa.tipoInt()) && exp.opnd2().equals(programa.tipoInt()))
			exp.ponTipo(programa.tipoInt());
		else if (exp.opnd1().equals(programa.tipoReal()) && exp.opnd2().equals(programa.tipoReal()))
			exp.opnd1().ponTipo(programa.tipoReal());
		else if (exp.opnd1().equals(programa.tipoBool()) && exp.opnd2().equals(programa.tipoBool()))
			exp.ponTipo(programa.tipoBool());
		else if (exp.opnd1().equals(programa.tipoChar()) && exp.opnd2().equals(programa.tipoChar()))
			exp.ponTipo(programa.tipoChar());
		else if (exp.opnd1().equals(programa.tipoString()) && exp.opnd2().equals(programa.tipoString()))
			exp.ponTipo(programa.tipoString());
		else
			exp.ponTipo(programa.tipoError());
	}

	public void procesa(Distinto exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if (exp.opnd1().equals(programa.tipoInt()) && exp.opnd2().equals(programa.tipoInt()))
			exp.ponTipo(programa.tipoInt());
		else if (exp.opnd1().equals(programa.tipoReal()) && exp.opnd2().equals(programa.tipoReal()))
			exp.opnd1().ponTipo(programa.tipoReal());
		else if (exp.opnd1().equals(programa.tipoBool()) && exp.opnd2().equals(programa.tipoBool()))
			exp.ponTipo(programa.tipoBool());
		else if (exp.opnd1().equals(programa.tipoChar()) && exp.opnd2().equals(programa.tipoChar()))
			exp.ponTipo(programa.tipoChar());
		else if (exp.opnd1().equals(programa.tipoString()) && exp.opnd2().equals(programa.tipoString()))
			exp.ponTipo(programa.tipoString());
		else
			exp.ponTipo(programa.tipoError());
	}

	public void procesa(ToInt exp) {
		exp.opnd().procesaCon(this);
		
		if(exp.opnd().equals(programa.tipoInt()))
			exp.ponTipo(programa.tipoInt());
		else if (exp.opnd().equals(programa.tipoReal()))
			exp.ponTipo(programa.tipoReal());
		else if (exp.opnd().equals(programa.tipoBool()))
			exp.ponTipo(programa.tipoBool());
		else if (exp.opnd().equals(programa.tipoChar()))
			exp.ponTipo(programa.tipoChar());
		else
			exp.ponTipo(programa.tipoError());
	}

	public void procesa(ToReal exp) {
		exp.opnd().procesaCon(this);
		
		if(exp.opnd().equals(programa.tipoInt()))
			exp.ponTipo(programa.tipoInt());
		else if (exp.opnd().equals(programa.tipoReal()))
			exp.ponTipo(programa.tipoReal());
		else if (exp.opnd().equals(programa.tipoBool()))
			exp.ponTipo(programa.tipoBool());
		else if (exp.opnd().equals(programa.tipoChar()))
			exp.ponTipo(programa.tipoChar());
		else
			exp.ponTipo(programa.tipoError());
	}

	public void procesa(ToBool exp) {
		exp.opnd().procesaCon(this);
		
		if(exp.opnd().equals(programa.tipoInt()))
			exp.ponTipo(programa.tipoInt());
		else
			exp.ponTipo(programa.tipoError());
	}

	public void procesa(ToChar exp) {
		exp.opnd().procesaCon(this);
		
		if(exp.opnd().equals(programa.tipoInt()))
			exp.ponTipo(programa.tipoInt());
		else
			exp.ponTipo(programa.tipoError());
	}

	public void procesa(ToString exp) {
		exp.opnd().procesaCon(this);
		
		if (exp.opnd().equals(programa.tipoChar()))
			exp.ponTipo(programa.tipoChar());
		else
			exp.ponTipo(programa.tipoError());
	}

	public void procesa(And exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if (exp.opnd1().tipo().equals(programa.tipoBool()) && exp.opnd2().tipo().equals(programa.tipoBool())) {
			exp.ponTipo(programa.tipoBool());
		} else {
			if (!exp.opnd1().tipo().equals(programa.tipoError()) && !exp.opnd2().tipo().equals(programa.tipoError()))
				errores.msg(exp.enlaceFuente() + ":" + ERROR_TIPO_OPERANDOS);
			exp.ponTipo(programa.tipoError());
		}
	}

	public void procesa(Or exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if (exp.opnd1().tipo().equals(programa.tipoBool()) && exp.opnd2().tipo().equals(programa.tipoBool())) {
			exp.ponTipo(programa.tipoBool());
		} else {
			if (!exp.opnd1().tipo().equals(programa.tipoError()) && !exp.opnd2().tipo().equals(programa.tipoError()))
				errores.msg(exp.enlaceFuente() + ":" + ERROR_TIPO_OPERANDOS);
			exp.ponTipo(programa.tipoError());
		}
	}

	public void procesa(Not exp) {
		exp.opnd().procesaCon(this);

		if (exp.opnd().tipo().equals(programa.tipoBool())) {
			exp.ponTipo(programa.tipoBool());
		} else {
			if (!exp.opnd().tipo().equals(programa.tipoError()))
				errores.msg(exp.enlaceFuente() + ":" + ERROR_TIPO_OPERANDOS);
			exp.ponTipo(programa.tipoError());
		}
	}

	public void procesa(Concatena exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if (exp.opnd1().tipo().equals(programa.tipoString()) && exp.opnd2().tipo().equals(programa.tipoString())) {
			exp.ponTipo(programa.tipoString());
		} else {
			if (!exp.opnd1().tipo().equals(programa.tipoError()) && !exp.opnd2().tipo().equals(programa.tipoError()))
				errores.msg(exp.enlaceFuente() + ":" + ERROR_TIPO_OPERANDOS);
			exp.ponTipo(programa.tipoError());
		}
	}

	public void procesa(Prog p) {
		p.inst().procesaCon(this);
		p.ponTipo(p.inst().tipo());
	}

	public void procesa(IAsig i) {
		i.exp().procesaCon(this);
		if (!i.declaracion().tipoDec().equals(i.exp().tipo())) {
			if (!i.exp().tipo().equals(programa.tipoError()))
				errores.msg(i.enlaceFuente() + ":" + ERROR_ASIG);
			i.ponTipo(programa.tipoError());
		} else {
			i.ponTipo(programa.tipoOk());
		}
	}

	public void procesa(IBloque b) {
		boolean ok = true;
		for (Inst i : b.is()) {
			i.procesaCon(this);
			ok = ok && i.tipo().equals(programa.tipoOk());
		}
		if (ok)
			b.ponTipo(programa.tipoOk());
		else
			b.ponTipo(programa.tipoError());
	}
	
}
