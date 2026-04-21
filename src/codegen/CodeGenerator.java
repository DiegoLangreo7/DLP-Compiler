package codegen;

import ast.type.CharType;
import ast.type.IntType;
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
        out.println();
        out.println("#source\t\"" + sourceFilename + "\"");
        out.println();
        out.flush();
    }

    public void line(int line){
        out.println();
        out.println("#line\t" + line);
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
        out.println("\tpush\tbp");
        out.flush();
    }

    // ==========================================
    //           Load and store
    // ==========================================

    public void load(Type type) {
        out.println("\tload"+type.suffix());
        out.flush();
    }

    public void store(Type type) {
        out.println("\tstore"+type.suffix());
        out.flush();
    }

    // ==========================================
    //           Arithmetic operations. They pop two operands, perform the operation and push the result.
    // ==========================================

    public void arithmetic(Type type, String operator) {
        switch (operator) {
            case "+": add(type); break;
            case "-": sub(type); break;
            case "*": mul(type); break;
            case "/": div(type); break;
            case "%": mod(type); break;
        }
    }

    /**
     * add[i], addf 	For addition
     */
    public void add(Type type) {
        out.println("\tadd"+type.suffix());
        out.flush();
    }

    /**
     * sub[i], subf 	For subtraction
     */
    public void sub(Type type) {
        out.println("\tsub"+type.suffix());
        out.flush();
    }

    /**
     *  mul[i], mulf 	For multiplication
     */
    public void mul(Type type) {
        out.println("\tmul"+type.suffix());
        out.flush();
    }

    /**
     * div[i], divf 	For division
     */
    public void div(Type type) {
        out.println("\tdiv"+type.suffix());
        out.flush();
    }

    /**
     * mod[i]       For modulus
     */
    public void mod(Type type) {
        out.println("\tmod"+type.suffix());
        out.flush();
    }

    // ==========================================
    //          Comparison operations. They pop two operands, perform the operation and push the result.
    // ==========================================

    public void comparison(Type type, String operator) {
        switch (operator) {
            case ">": gt(type); break;
            case "<": lt(type); break;
            case ">=": ge(type); break;
            case "<=": le(type); break;
            case "==": eq(type); break;
            case "!=": ne(type); break;
        }
    }

    /**
     *  gt[i], gtf 	For "greater than" comparison
     */
    public void gt(Type type) {
        out.println("\tgt"+type.suffix());
        out.flush();
    }

    /**
     * lt[i], ltf 	For "lower than" comparison
     */
    public void lt(Type type) {
        out.println("\tlt"+type.suffix());
        out.flush();
    }

    /**
     * ge[i], gef 	For "greater or equal" comparison
     */
    public void ge(Type type) {
        out.println("\tge"+type.suffix());
        out.flush();
    }

    /**
     *  le[i], lef 	For "lower or equal than" comparison
     */
    public void le(Type type) {
        out.println("\tle"+type.suffix());
        out.flush();
    }

    /**
     *  eq[i], eqf 	For "equal to" comparison
     */
    public void eq(Type type) {
        out.println("\teq"+type.suffix());
        out.flush();
    }

    /**
     *  ne[i], nef 	For "not equal" comparison
     */
    public void ne(Type type) {
        out.println("\tne"+type.suffix());
        out.flush();
    }

    // ==========================================
    //          Logical operations. Pop one or two operands, perform the operation and push the result.
    // ==========================================

    public void logical(String operator){
        switch (operator) {
            case "&&": and(); break;
            case "||": or(); break;
            case "!": not(); break;
        }
    }

    /**
     * and 	For the "and" logical operation
     */
    public void and() {
        out.println("\tand");
        out.flush();
    }

    /**
     * or 	For the "or" logical operation
     */
    public void or() {
        out.println("\tor");
        out.flush();
    }

    /**
     * not 	For the unary "not" logical operation
      */
    public void not() {
        out.println("\tnot");
        out.flush();
    }

    // ==========================================
    //           Input / Output
    // ==========================================

    /**
     *  inb, in[i], inf Read a value from the keyboard and pushes it onto the stack
     */
    public void in(Type type) {
        out.println("\tin"+type.suffix());
        out.flush();
    }

    /**
     * outb, out[i], outf Pop one value off the stack and shows it in the console
     */
    public void out(Type type) {
        out.println("\tout"+type.suffix());
        out.flush();
    }

    // ==========================================
    //           Conversions
    // ==========================================

    /**
     *  b2i Pops one character and pushes it as an integer
     * 	i2f Pops one integer and pushes it as a real number
     * 	f2i Pops one real number and pushes it as an integer
     * 	i2b Pops one integer and pushes it as a character
     */
    public void convertTo(Type from, Type to) {
        if(from == to) return;
        if(from instanceof CharType){
            out.println("\tb2i");
            if(to instanceof NumberType){
                out.println("\ti2f");
            }
        }
        else if(from instanceof IntType){
            if(to instanceof NumberType){
                out.println("\ti2f");
            }
            else if(to instanceof CharType){
                out.println("\ti2b");
            }
        }
        else if(from instanceof NumberType){
            out.println("\tf2i");
            if(to instanceof CharType){
                out.println("\ti2b");
            }
        }
        out.flush();
    }

    // ==========================================
    //           Jumps
    // ==========================================

    /**
     * <id>: Defines one label for jumps and invocations (functions).
     */
    public void labelID(String name) {
        out.println();
        out.println(" " + name + ":");
        out.flush();
    }

    /**
     * jmp <label>		Jumps (unconditionally) to the label specified as a parameter.
     */
    public void jmp(String label){
        out.println("\tjmp "+label);
        out.flush();
    }

    /**
     * jz <label> 		Pops one integer and jumps to the label if the popped integer is zero.
     */
    public void jz(String label){
        out.println("\tjz "+label);
        out.flush();
    }

    /**
     * jnz <label> 	    Pops one integer and jumps to the label if the popped integer is not zero.
     */
    public void jnz(String label){
        out.println("\tjnz "+label);
        out.flush();
    }

    // ==========================================
    //           Functions
    // ==========================================

    /**
     * <id>: Defines a label for jumps and invocations (functions)
     */
    public void functionID(String name) {
        out.println();
        out.println(" " + name + ":");
        out.flush();
    }

    /**
     *	enter <int_constant> 	Allocates <int_constant> bytes on the top of the stack
     */
    public void enter(int localBytesSum) {
        out.println("\tenter\t"+localBytesSum);
        out.flush();
    }

    /**
     * ret Returns from a function invocation.
     * 		<int_constant>,	The first constant represents the bytes to return;
     * 	    <int_constant>, the second one, the bytes of all the local variables;
     *      <int_constant> 	and the last one, the bytes of all the parameters.
     */
    public void ret(int numberOfBytes, int localBytesSum, int paramBytesSum) {
        out.println("\tret\t" + numberOfBytes + ", " + localBytesSum + ", " + paramBytesSum);
        out.flush();
    }

    // Métodos especiales

    public void mainInvocation() {
        out.println();
        out.println("' Invocation to the main function");
        out.println("call main");
        out.println("halt");
        out.println();
        out.flush();
    }

    public String getLabel(){
        return "label"+this.labels++;
    }

    public void comment(String text){
        out.println("\t' "+text);
        out.flush();
    }

}