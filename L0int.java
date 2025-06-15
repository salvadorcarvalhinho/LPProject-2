import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class L0int {

    public static void main(String args[]) {
		InputStream input;
        try {
            if (args.length > 0) {
                input = new FileInputStream(args[0]);
            } else {
                input = System.in;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + args[0]);
            return;
        }
		
		Parser parser = new Parser(input);
		ASTNode exp;
		
		System.out.println("X++ interpreter PL MEIC 2024/25 (v0.0)\n");

		while (true) {
		    try {
				System.out.print("# ");
				exp = parser.Start();
				if (exp==null) System.exit(0);
				
				ASTType type = exp.typeCheck(new Environment<ASTType>());
				
				IValue v = exp.eval(new Environment<IValue>());
				System.out.println(v.toStr());

		    } catch (ParseException e) {
				System.out.println("Syntax Error.");
				e.printStackTrace();
				parser.ReInit(input);

		    } catch (Exception e) {
				e.printStackTrace();
				parser.ReInit(input);
		    }
		}
    }
    
}
