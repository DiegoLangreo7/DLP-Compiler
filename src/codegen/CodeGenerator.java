package codegen;

import ast.type.CharType;
import ast.type.NumberType;
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

    // Push Instructions

    public void push(Type type, String value) {
        out.println("\tpush"+type.suffix()+"\t"+value);
        out.flush();
    }

    public void pusha(int offset) {
        out.println("\tpusha\t"+offset);
        out.flush();
    }

    public void pushbp() {
        out.println("\tpusha\tbp");
        out.flush();
    }

    // Load and Store Instructions

    public void load(Type type) {
        out.println("\tload"+type.suffix());
        out.flush();
    }

    public void store(Type type) {
        out.println("\tstore"+type.suffix());
        out.flush();
    }

    // Arithmetic operations

    public void arithmetic(Type type, String operator) {
        switch (operator) {
            case "+": add(type); break;
            case "-": sub(type); break;
            case "*": mul(type); break;
            case "/": div(type); break;
            case "%": mod(type); break;
        }
    }

    public void add(Type type) {
        out.println("add"+type.suffix());
        out.flush();
    }

    public void sub(Type type) {
        out.println("sub"+type.suffix());
        out.flush();
    }

    public void mul(Type type) {
        out.println("mul"+type.suffix());
        out.flush();
    }

    public void div(Type type) {
        out.println("div"+type.suffix());
        out.flush();
    }

    public void mod(Type type) {
        out.println("mod"+type.suffix());
        out.flush();
    }

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
