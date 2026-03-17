package errorhandler;

import ast.type.ErrorType;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ErrorHandler {

    private static ErrorHandler instance;
    private List<ErrorType> errors = new ArrayList<>();

    private ErrorHandler(){
        //SINGLETON
    }

    public static ErrorHandler getInstance(){

        if(instance == null){
            instance = new ErrorHandler();
        }
        return instance;
    }

    public boolean anyError(){
        return !this.errors.isEmpty();
    }

    public void showErrors(PrintStream printStream){
        for(ErrorType error : errors)
            printStream.println(error);
    }

    public void addError(ErrorType error){
        this.errors.add(error);
    }

}
