package procesamientos.generacionCodigo;

import maquinaP.MaquinaP;
import procesamientos.Procesamiento;
import programa.Programa;
import programa.Programa.*;

public class GeneracionDeCodigo extends Procesamiento {
	private MaquinaP maquina;

	public GeneracionDeCodigo(MaquinaP maquina) {
		this.maquina = maquina;
	}

	public void procesa(Var exp) {
		maquina.addInstruccion(maquina.apilaInt(exp.declaracion().dir()));
	}

	public void procesa(DRef exp) {
		exp.mem().procesaCon(this);
		maquina.addInstruccion(maquina.apilaInd());
	}

	public void procesa(CteInt exp) {
		maquina.addInstruccion(maquina.apilaInt(exp.valEntero()));
	}

	public void procesa(CteBool exp) {
		maquina.addInstruccion(maquina.apilaBool(exp.valBool()));
	}

	public void procesa(CteChar exp) {
		maquina.addInstruccion(maquina.apilaChar(exp.valChar()));
	}

	public void procesa(CteReal exp) {
		maquina.addInstruccion(maquina.apilaReal(exp.valReal()));
	}

	public void procesa(CteString exp) {
		maquina.addInstruccion(maquina.apilaString(exp.valString()));
	}

	public void procesa(Suma exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if (exp.opnd1().tipo().equals(programa.tipoInt()) && exp.opnd2().tipo().equals(programa.tipoInt()))
			maquina.addInstruccion(maquina.sumaInt());
		else if (exp.opnd1().tipo().equals(programa.tipoReal()) || exp.opnd2().tipo().equals(programa.tipoReal()))
			maquina.addInstruccion(maquina.sumaReal());
		else
			maquina.addInstruccion(maquina.concatena());
	}

	public void procesa(Resta exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if (exp.opnd1().tipo().equals(programa.tipoInt()) && exp.opnd2().tipo().equals(programa.tipoInt()))
			maquina.addInstruccion(maquina.restaInt());
		else
			maquina.addInstruccion(maquina.restaReal());
	}

	public void procesa(Multiplicacion exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if (exp.opnd1().tipo().equals(programa.tipoInt()) && exp.opnd2().tipo().equals(programa.tipoInt()))
			maquina.addInstruccion(maquina.multiplicacionInt());
		else
			maquina.addInstruccion(maquina.multiplicacionReal());
	}

	public void procesa(Division exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if (exp.opnd1().tipo().equals(programa.tipoInt()) && exp.opnd2().tipo().equals(programa.tipoInt()))
			maquina.addInstruccion(maquina.divisionInt());
		else
			maquina.addInstruccion(maquina.divisionReal());
	}

	public void procesa(RestoEntero exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		maquina.addInstruccion(maquina.restoEntero());
	}

	public void procesa(CambioSigno exp) {
		exp.opnd().procesaCon(this);

		if (exp.opnd().equals(programa.tipoInt()))
			maquina.addInstruccion(maquina.cambioSignoInt());
		else
			maquina.addInstruccion(maquina.cambioSignoReal());
	}

	public void procesa(And exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		maquina.addInstruccion(maquina.and());
	}

	public void procesa(Or exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		maquina.addInstruccion(maquina.or());
	}

	public void procesa(Not exp) {
		exp.opnd().procesaCon(this);

		maquina.addInstruccion(maquina.not());
	}

	public void procesa(ToInt exp) {
		exp.opnd().procesaCon(this);

		if (exp.opnd().equals(programa.tipoBool()))
			maquina.addInstruccion(maquina.bool2int());
		else if (exp.opnd().equals(programa.tipoChar()))
			maquina.addInstruccion(maquina.char2int());
		else
			maquina.addInstruccion(maquina.int2int());
	}

	public void procesa(ToReal exp) {
		exp.opnd().procesaCon(this);

		if (exp.opnd().equals(programa.tipoBool()))
			maquina.addInstruccion(maquina.bool2real());
		else if (exp.opnd().equals(programa.tipoChar()))
			maquina.addInstruccion(maquina.char2real());
		else
			maquina.addInstruccion(maquina.int2real());
	}

	public void procesa(ToChar exp) {
		exp.opnd().procesaCon(this);

		maquina.addInstruccion(maquina.int2char());
	}

	public void procesa(ToBool exp) {
		exp.opnd().procesaCon(this);

		maquina.addInstruccion(maquina.int2bool());
	}

	public void procesa(ToString exp) {
		exp.opnd().procesaCon(this);

		maquina.addInstruccion(maquina.char2string());
	}

	public void procesa(Mayor exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if ((exp.opnd1().equals(programa.tipoInt()) || exp.opnd1().equals(programa.tipoReal()))
				&& (exp.opnd2().equals(programa.tipoInt()) || exp.opnd2().equals(programa.tipoReal())))
			maquina.addInstruccion(maquina.mayorNum());
		else if (exp.opnd1().equals(programa.tipoBool()) && exp.opnd2().equals(programa.tipoBool()))
			maquina.addInstruccion(maquina.mayorBool());
		else if (exp.opnd1().equals(programa.tipoChar()) && exp.opnd2().equals(programa.tipoChar()))
			maquina.addInstruccion(maquina.mayorChar());
		else
			maquina.addInstruccion(maquina.mayorString());
	}

	public void procesa(Menor exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if ((exp.opnd1().equals(programa.tipoInt()) || exp.opnd1().equals(programa.tipoReal()))
				&& (exp.opnd2().equals(programa.tipoInt()) || exp.opnd2().equals(programa.tipoReal())))
			maquina.addInstruccion(maquina.menorNum());
		else if (exp.opnd1().equals(programa.tipoBool()) && exp.opnd2().equals(programa.tipoBool()))
			maquina.addInstruccion(maquina.menorBool());
		else if (exp.opnd1().equals(programa.tipoChar()) && exp.opnd2().equals(programa.tipoChar()))
			maquina.addInstruccion(maquina.menorChar());
		else
			maquina.addInstruccion(maquina.menorString());
	}

	public void procesa(MayorIgual exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if ((exp.opnd1().equals(programa.tipoInt()) || exp.opnd1().equals(programa.tipoReal()))
				&& (exp.opnd2().equals(programa.tipoInt()) || exp.opnd2().equals(programa.tipoReal())))
			maquina.addInstruccion(maquina.mayorIgualNum());
		else if (exp.opnd1().equals(programa.tipoBool()) && exp.opnd2().equals(programa.tipoBool()))
			maquina.addInstruccion(maquina.mayorIgualBool());
		else if (exp.opnd1().equals(programa.tipoChar()) && exp.opnd2().equals(programa.tipoChar()))
			maquina.addInstruccion(maquina.mayorIgualChar());
		else
			maquina.addInstruccion(maquina.mayorIgualString());
	}

	public void procesa(MenorIgual exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if ((exp.opnd1().equals(programa.tipoInt()) || exp.opnd1().equals(programa.tipoReal()))
				&& (exp.opnd2().equals(programa.tipoInt()) || exp.opnd2().equals(programa.tipoReal())))
			maquina.addInstruccion(maquina.menorIgualNum());
		else if (exp.opnd1().equals(programa.tipoBool()) && exp.opnd2().equals(programa.tipoBool()))
			maquina.addInstruccion(maquina.menorIgualBool());
		else if (exp.opnd1().equals(programa.tipoChar()) && exp.opnd2().equals(programa.tipoChar()))
			maquina.addInstruccion(maquina.menorIgualChar());
		else
			maquina.addInstruccion(maquina.menorIgualString());
	}

	public void procesa(Distinto exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if ((exp.opnd1().equals(programa.tipoInt()) || exp.opnd1().equals(programa.tipoReal()))
				&& (exp.opnd2().equals(programa.tipoInt()) || exp.opnd2().equals(programa.tipoReal())))
			maquina.addInstruccion(maquina.distintoNum());
		else if (exp.opnd1().equals(programa.tipoBool()) && exp.opnd2().equals(programa.tipoBool()))
			maquina.addInstruccion(maquina.distintoBool());
		else if (exp.opnd1().equals(programa.tipoChar()) && exp.opnd2().equals(programa.tipoChar()))
			maquina.addInstruccion(maquina.distintoChar());
		else
			maquina.addInstruccion(maquina.distintoString());
	}

	public void procesa(Igual exp) {
		exp.opnd1().procesaCon(this);
		exp.opnd2().procesaCon(this);

		if ((exp.opnd1().equals(programa.tipoInt()) || exp.opnd1().equals(programa.tipoReal()))
				&& (exp.opnd2().equals(programa.tipoInt()) || exp.opnd2().equals(programa.tipoReal())))
			maquina.addInstruccion(maquina.igualNum());
		else if (exp.opnd1().equals(programa.tipoBool()) && exp.opnd2().equals(programa.tipoBool()))
			maquina.addInstruccion(maquina.igualBool());
		else if (exp.opnd1().equals(programa.tipoChar()) && exp.opnd2().equals(programa.tipoChar()))
			maquina.addInstruccion(maquina.igualChar());
		else
			maquina.addInstruccion(maquina.igualString());
	}

	public void procesa(Prog p) {
		p.inst().procesaCon(this);
	}

	public void procesa(IAsig i) {
		i.exp().procesaCon(this);

		maquina.addInstruccion(maquina.desapilaDir(i.declaracion().dir()));
	}

	public void procesa(IBloque b) {
		for (Programa.Inst i : b.is())
			i.procesaCon(this);
	}

	public void procesa(IWhile i) {
		i.exp().procesaCon(this);
		maquina.addInstruccion(maquina.irF(i.dirInstruccionSiguiente()));
		i.cuerpo().procesaCon(this);
		maquina.addInstruccion(maquina.irA(i.dirPrimeraInstruccion()));
	}

	public void procesa(IIfThen i) {
		i.exp().procesaCon(this);
		maquina.addInstruccion(maquina.irF(i.dirInstruccionSiguiente()));
		i.cuerpo().procesaCon(this);
		maquina.addInstruccion(maquina.irA(i.dirPrimeraInstruccion()));
	}

	public void procesa(IIfThenElse i) {
		i.exp().procesaCon(this);
		maquina.addInstruccion(maquina.irF(i.dirInstruccionSiguiente()));
		i.cuerpo().procesaCon(this);
		maquina.addInstruccion(maquina.irA(i.dirPrimeraInstruccion()));
	}

	public void procesa(IDoWhile i) {
		i.exp().procesaCon(this);
		maquina.addInstruccion(maquina.irF(i.dirInstruccionSiguiente()));
		i.cuerpo().procesaCon(this);
		maquina.addInstruccion(maquina.irA(i.dirPrimeraInstruccion()));
	}

	public void procesa(ISwitch i) {
		i.exp().procesaCon(this);
		maquina.addInstruccion(maquina.irF(i.dirInstruccionSiguiente()));
		i.cuerpo().procesaCon(this);
		maquina.addInstruccion(maquina.irA(i.dirPrimeraInstruccion()));
	}

	public void procesa(INew i) {
		i.mem().procesaCon(this);
		maquina.addInstruccion(maquina.alloc(((TPointer) i.mem().tipo()).tbase().tamanio()));
		maquina.addInstruccion(maquina.desapilaInd());
	}

	public void procesa(IFree i) {
		i.mem().procesaCon(this);
		maquina.addInstruccion(maquina.apilaInd());
		maquina.addInstruccion(maquina.dealloc(((TPointer) i.mem().tipo()).tbase().tamanio()));
	}
}
