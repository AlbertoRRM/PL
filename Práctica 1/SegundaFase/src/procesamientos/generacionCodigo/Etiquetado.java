package procesamientos.generacionCodigo;

import procesamientos.Procesamiento;
import programa.Programa;
import programa.Programa.CteInt;
import programa.Programa.CteBool;
import programa.Programa.Suma;
import programa.Programa.And;
import programa.Programa.DRef;
import programa.Programa.Prog;
import programa.Programa.IBloque;
import programa.Programa.IWhile;
import programa.Programa.IAsig;
import programa.Programa.IFree;
import programa.Programa.INew;
import programa.Programa.Var;


public class Etiquetado extends Procesamiento {
   private int etq; 
   public Etiquetado() {
       etq = 0;
   }
   public void procesa(Var exp) {
      exp.ponDirPrimeraInstruccion(etq);
      // apila(...dir variable...)
      exp.ponDirInstruccionSiguiente(++etq);
   } 
   
    public void procesa(DRef exp) {
     exp.ponDirPrimeraInstruccion(etq);
     exp.mem().procesaCon(this);
     // apilaind
     etq++;
     exp.ponDirInstruccionSiguiente(etq);
   }
    
   public void procesa(CteInt exp) {
      exp.ponDirPrimeraInstruccion(etq);
      // apilaInt(...)
      exp.ponDirInstruccionSiguiente(++etq);
   } 
   public void procesa(CteBool exp) {
      exp.ponDirPrimeraInstruccion(etq);
      // apilaBool(...)
      exp.ponDirInstruccionSiguiente(++etq);
   } 
   public void procesa(Suma exp) {
       exp.ponDirPrimeraInstruccion(etq);
       exp.opnd1().procesaCon(this);
       if (exp.opnd1().esMen()) /* apilaind*/ etq++;
       exp.opnd2().procesaCon(this);
       if (exp.opnd2().esMen()) /* apilaind*/ etq++;
       // suma
       exp.ponDirInstruccionSiguiente(++etq);
   } 
   public void procesa(And exp) {
       exp.ponDirPrimeraInstruccion(etq);
       exp.opnd1().procesaCon(this);
       if (exp.opnd1().esMen()) /* apilaind*/ etq++;
       exp.opnd2().procesaCon(this);
       if (exp.opnd1().esMen()) /* apilaind*/ etq++;
       // and
       exp.ponDirInstruccionSiguiente(++etq);
   }   
   public void procesa(Prog p) {
      p.inst().procesaCon(this);
   }     
   public void procesa(IAsig i) {
      i.ponDirPrimeraInstruccion(etq);
      i.mem().procesaCon(this);
      i.exp().procesaCon(this);
      // desapilaind o mueve
      i.ponDirInstruccionSiguiente(++etq);
   }     
   public void procesa(IBloque b) {
      b.ponDirPrimeraInstruccion(etq);
      for(Programa.Inst i: b.is())
          i.procesaCon(this);
      b.ponDirInstruccionSiguiente(etq);
   }     
   public void procesa(IWhile i) {
      i.ponDirPrimeraInstruccion(etq);
      i.exp().procesaCon(this);
      // ir_f(...)
      etq++;
      i.cuerpo().procesaCon(this);
      // ir_a(...)
      etq++;
      i.ponDirInstruccionSiguiente(etq);
   }     
   public void procesa(INew i) {
     i.ponDirPrimeraInstruccion(etq);
     i.mem().procesaCon(this);  
     // alloc desapilaind
     etq +=2;
     i.ponDirInstruccionSiguiente(etq);     
   }
   public void procesa(IFree i) {
     i.ponDirPrimeraInstruccion(etq);
     i.mem().procesaCon(this);  
     // apilaind dealloc 
     etq +=2;
     i.ponDirInstruccionSiguiente(etq);     
   }
}
