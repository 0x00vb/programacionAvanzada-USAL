import vista.Pantalla;

public class Main{
	public static void main(String[] args){
		int arg = 0;
		if(args.length > 0){
			arg = Integer.parseInt(args[0]);

		}
		new Pantalla(arg);
	}
}
