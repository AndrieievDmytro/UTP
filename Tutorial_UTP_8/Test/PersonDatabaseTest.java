package Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import PersonUtility.*;
import org.junit.Assert;
import org.junit.Test;

public class PersonDatabaseTest {

	private File file = new File("C:/PJAIT/UTP/persons/date.txt");
	private File out = new File("C:/PJAIT/UTP/persons/personDatabaseOut.txt");

	@Test
	public void serializeAndDeserialize() throws Throwable {

		PersonDatabase database = new PersonDatabase(file);
		
		OutputStream output = new FileOutputStream(out);
		DataOutputStream dataOut = new DataOutputStream(output);
		database.serialize(dataOut);
		dataOut.close();		

		InputStream input = new FileInputStream(out);
		DataInputStream dataIn = new DataInputStream(input);
		PersonDatabase deserialize = PersonDatabase.deserialize(dataIn);
		dataIn.close();
		Assert.assertNotNull(deserialize);
		Assert.assertEquals(database.listSize(), deserialize.listSize());
	}
}