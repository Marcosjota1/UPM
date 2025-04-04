package aed.recursion;

import es.upm.aedlib.map.*;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.*;
import es.upm.aedlib.lifo.*;

public class StackMachine {
  Map<String, PositionList<Instruction>> code; // código a ejecutar
  LIFO<Integer> stack; // pila de enteros

  public StackMachine(Map<String, PositionList<Instruction>> code) {
    this.stack = new LIFOList<Integer>();
    this.code = code;
  }

  public void run(String name) {
    if (!code.containsKey(name)) {
      throw new RuntimeException("Function not found: " + name);
    }
    PositionList<Instruction> instrucciones = code.get(name);
    Position<Instruction> cursor = instrucciones.first();
    ejecutar(instrucciones, cursor);
  }
  
  private void ejecutar(PositionList<Instruction> instrucciones, Position<Instruction> cursor) {
    Instruction ins = cursor.element();
    Integer e1=null;
    Integer e2=null;
    switch (ins.getInstType()) {
      case PUSH:
        stack.push(ins.getIntParm());
        break;
      case ADD: {
         e1 = stack.pop();
         e2 = stack.pop();
        stack.push(e1 + e2);
        break;
      }
      case SUB: {
         e1 = stack.pop();
         e2 = stack.pop();
        stack.push(e1 - e2);
        break;
      }
      case MULT: {
         e1 = stack.pop();
         e2 = stack.pop();
        stack.push(e2 * e1);
        break;
      }
      case DUP: {
        Integer top = stack.top();
        stack.push(top);
        break;
      }
      case SWAP: {
         e1 = stack.pop();
         e2 = stack.pop();
        stack.push(e1);
        stack.push(e2);
        break;
      }
      case DROP:
        stack.pop();
        break;
      case CALL:
        run(ins.getNameParm());
        break;
      case IF_SKIP:
    	    if (stack.pop()!=0) {
    	    	int n = ins.getIntParm();
    	    	int i = 1;
    	    	while (cursor != null && i < n) {
    		        cursor = instrucciones.next(cursor);
    		        i++;
    		    }
    	    }
    	    break;

      case RET:
        return;
      case EQ: {
         e1 = stack.pop();
         e2 = stack.pop();
        stack.push(e1.equals(e2) ? 1 : 0);
        break;
      }
      case PRINT:
        System.out.println("Imprimiendo: " + stack.pop());
        break;
    }
    ejecutar(instrucciones, instrucciones.next(cursor));
  }
  
}
