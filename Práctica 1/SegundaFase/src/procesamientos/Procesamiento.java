package procesamientos;

import programa.Programa.Error;
import programa.Programa.*;

public class Procesamiento {
   public void procesa(CteInt exp) {} 
   public void procesa(CteBool exp) {} 
   public void procesa(CteChar exp) {}
   public void procesa(CteReal exp) {}
   public void procesa(CteString exp) {}
   public void procesa(Var exp) {} 
   public void procesa(DRef exp) {} 
   public void procesa(Suma exp) {} 
   public void procesa(Resta exp) {}
   public void procesa(Multiplicacion exp) {}
   public void procesa(Division exp) {}
   public void procesa(And exp) {} 
   public void procesa(Or exp) {}
   public void procesa(Not exp) {}
   public void procesa(RestoEntero exp) {}
   public void procesa(CambioSigno exp) {}
   public void procesa(ElementoCadena exp) {}
   public void procesa(Concatena exp) {}
   public void procesa(ToInt exp) {}
   public void procesa(ToReal exp) {}
   public void procesa(ToChar exp) {}
   public void procesa(ToBool exp) {}
   public void procesa(ToString exp) {}
   public void procesa(Mayor exp) {}
   public void procesa(Menor exp) {}
   public void procesa(Igual exp) {}
   public void procesa(Distinto exp) {}
   public void procesa(MayorIgual exp) {}
   public void procesa(MenorIgual exp) {}
   public void procesa(Leer exp) {}
   public void procesa(Escribir exp) {}
   public void procesa(Int t) {}     
   public void procesa(Bool t) {}  
   public void procesa(Char t) {}
   public void procesa(Real t) {}
   public void procesa(TString t) {}
   public void procesa(Ok t) {}   
   public void procesa(Error t) {}   
   public void procesa(TRef t) {}     
   public void procesa(TPointer t) {}   
   public void procesa(Prog p) {}     
   public void procesa(DecVar d) {}     
   public void procesa(IAsig i) {}  
   public void procesa(INew i) {}     
   public void procesa(IFree i) {}     
   public void procesa(IBloque i) {}
   public void procesa(IWhile i) {}
   public void procesa(IIfThen i) {}
   public void procesa(IIfThenElse i) {}
   public void procesa(IDoWhile i) {}
   public void procesa(ISwitch i) {}

}
