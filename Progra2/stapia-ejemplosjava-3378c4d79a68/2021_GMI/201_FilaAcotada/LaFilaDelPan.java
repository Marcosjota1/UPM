
/* 
  Este ejemplo se ha realizado para visualizar
  la ejecución del programa en pythontutor.com
  Para verlo, 
  1) Entrar en http://pythontutor.com/java.html#mode=edit
  2) Pegar el código
  3) Pulsar el botón Visualize
  4) Ejecutar paso a paso
*/

public class LaFilaDelPan {
  
  private Person[] fila;
  private int tam;
  private static final int CAPACIDAD = 3;
  
  private LaFilaDelPan() {
    tam = 0;
    fila = new Person[CAPACIDAD];
  }
  
  public void push(Person p) {
    if ( tam >= CAPACIDAD ) {
      System.out.println("La fila esta llena");
      return;
    }
    fila[tam] = p;
    ++tam;
  }
  
  public Person pull() {
    if ( tam == 0 ) {
      System.out.println("La fila esta vacia");
      return null; 
    }
    Person primero = fila[0];
    for ( int i = 1; i < CAPACIDAD; ++i ) {
      fila[i-1] = fila[i];
    }
    fila[CAPACIDAD-1] = null;
    --tam;
    return primero;
  }
  
  public static void main(String[] args) {
    System.out.println("Empezando");
    
    LaFilaDelPan f = new LaFilaDelPan();
    
    f.push(new Person(10, "Luis"));
    f.push(new Person(7, "Celia"));
    f.push(new Person(48, "Santiago"));
    
    Person conPan = f.pull();
    System.out.print("La persona: ");
    conPan.printName();
    System.out.println(" ya tiene pan");
    
    Person p = new Person(5, "El vecinito");
    f.push(p);
    
    /*
    int[] numeros = new int[3];
    Person[] personas = new Person[3];
    personas[1] = new Person(10, "Luis");
    personas[1] = new Person(7, "Celia");
    */
    System.out.println("Fin");
  }
}

// demonstrates static/non-static fields and methods
// simulates a person (not in the Blade Runner sense)
class Person {
   // instance variable: age of this person
   private int age;                    

   // another instance variable: name of this person
   private String name;                

   // static variable (shared by all instances): global population
   private static int population = 0;
    
   // constructor
   public Person(int a, String n) {
      // copy arguments of constructor to instance variables
      age = a;
      name = n;

      // increase the static counter
      population++;
   }

   // static method (not per-instance)
   public static void printPop() {
      System.out.println(population);
   }

   // instance method
   public void printName() {
      System.out.println(name);
   }

   // another instance method
   public void printInfo() {                 
      System.out.println(age);
 
      // calling an instance method without a period
      // (uses same instance as what printInfo was called on)
      printName();
   }
}