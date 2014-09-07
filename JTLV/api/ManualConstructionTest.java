
import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDException;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.JTLVIllegalVariableException;
import edu.wis.jtlv.env.module.FDSModule;
import edu.wis.jtlv.env.module.ModuleException;

/**
 * @author yaniv
 * 
 */
public class ManualConstructionTest {
	public static void main(String[] args) {
		try {
			FDSModule env = new FDSModule("env", new String[] { "x", "y", "z" });
			FDSModule sys = new FDSModule("sys", new String[] { "x", "y", "z" });

			System.out.print("===========================\n");
			System.out.println(Env.get_var("sys", "x").toBDD().toStringWithDomains(Env.stringer));
			System.out.println(Env.get_var("sys", "y").toBDD().toStringWithDomains(Env.stringer));
			BDD b = sys.trans().andWith(
					Env.get_var("sys", "x").primeBDD().imp(
							Env.get_var("sys", "y'").toBDD()));
			System.out.println(b.toStringWithDomains(Env.stringer));
			System.out.print("===========================\n");
			
			System.out.print("=============================================\n");
			System.out.println(env);
			System.out.print("=============================================\n");
			System.out.println(sys);
			System.out.print("=============================================\n");
			System.out.println("");
			
			
			BDD t = Env.get_var("sys", "x").toBDD().or(Env.get_var("sys", "y").toBDD());
			System.out.println(t.toStringWithDomains(Env.stringer));
			BDD i = Env.get_var("sys", "x").toBDD().imp(Env.get_var("sys", "y").toBDD());
			System.out.println(i.toStringWithDomains(Env.stringer));
			
		} catch (BDDException e) {
			e.printStackTrace();
		} catch (ModuleException e) {
			e.printStackTrace();
		} catch (JTLVIllegalVariableException e) {
			e.printStackTrace();
		}
	}

}
