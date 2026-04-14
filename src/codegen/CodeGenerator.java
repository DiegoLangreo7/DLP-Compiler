package codegen;

import ast.type.CharType;
import ast.type.NumberType;
import ast.type.IntType;
import ast.type.Type;

import java.io.IOException;
import java.io.PrintWriter;

public class CodeGenerator {

    private int labels = 0;
    private PrintWriter out;

    // en el constructor, inicializa el PrintWriter y luego IOException si PrintWriter(sourceFilename) no lo encontró
    // los parámetros son String outputFilename y String sourceFilename

    //En cada metodo: out.println("\t...");
    //                out.flush();

    //call main y halt en una funcion "mainInvocation()"

    // una para varDefinition que haga this.comment()
    // el comment printea ' * ...

    // el metodo convertTo convierte un tipo origen en un tipo destino.
    // instanceOf a dolor.
    // Si el tipo 1 es == al 2, "return;", esto por ejemplo para las condiciones del if, ya sabemos que es valido pero nos la pela que tipo sea, convertTo y si ya era if que no haga nada.

    public CodeGenerator(String outputFilename, String sourceFilename){
        try{
            this.out = new PrintWriter(outputFilename);
        }
        catch(IOException e){
            System.err.println("Error opening the file "+outputFilename+".");
            System.exit(-1);
        }
        this.source(sourceFilename);
    }

    // Control para MAPL

    public void source(String sourceFilename){
        out.println("#source \""+sourceFilename+"\"");
        out.flush();
    }

    public void line(int line){
        out.println("#line "+line);
        out.flush();
    }

    // Métodos simples

    public void convertTo(Type from, Type to) {
        if(from == to) return;
        if(from instanceof CharType){
            out.println("\tc2i");
            if(to instanceof NumberType){
                out.println("\ti2f");
            }
        }
        // falta por acabar
    }

    public void store(Type type){
        out.println("store"+type.suffix());
        out.flush();
    }

    // Métodos especiales

    public void mainInvocation() {
        comment("Invocation to the main function");
        out.println("call main");
        out.println("halt");
        out.flush();
    }

    public void comment(String text){
        out.println("' "+text);
        out.flush();
    }
}
