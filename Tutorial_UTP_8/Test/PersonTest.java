package Test;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import PersonUtility.*;
import org.junit.Assert;
import org.junit.Test;

public class PersonTest {

	private File out = new File("C:/PJAIT/UTP/persons/out.txt");
	private Date d = new Date();

	@Test
	public void serializeAndDeserialize() throws Throwable{
	
		Person person = new Person("PersonN", "PerosnS",d);
		
		OutputStream output = new FileOutputStream(out);
		DataOutputStream dataOut = new DataOutputStream(output);
		person.serialize(dataOut);
		dataOut.close();		

		InputStream input = new FileInputStream(out);
		DataInputStream dataIn = new DataInputStream(input);
		Person desirialise = Person.deserialize(dataIn);
		dataIn.close();
		Assert.assertNotNull(desirialise);
		Assert.assertEquals(person.toString(), desirialise.toString());
	}
}