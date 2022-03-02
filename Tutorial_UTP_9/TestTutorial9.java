import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Calendar;
import org.junit.Assert;
import org.junit.Test;

public class TestTutorial9 {
    
    @Test
    public void test() throws Throwable{
        Calendar calendar = Calendar.getInstance();
        calendar.set(1997, 7, 20);
        String PESEL = PeselGenerator.generatePesel(calendar.getTime());
        System.out.println(PESEL);
        Assert.assertNotNull(PESEL);
        Assert.assertEquals(11, PESEL.length());
    }

    @Test
    public void validate() throws Throwable {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1996, 10, 29);
        PeselGenerator peselGenerator = new PeselGenerator();
        String PESEL = PeselGenerator.generatePesel(calendar.getTime());
        try{
            Method method = peselGenerator.getClass().getDeclaredMethod("validate", String.class);
            method.setAccessible(true);
            Assert.assertTrue((boolean)method.invoke(peselGenerator,PESEL)); 
        }catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void extractBirthDate1() throws Throwable {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1997, 7, 20);
        PeselGenerator peselGenerator = new PeselGenerator();
        String PESEL = PeselGenerator.generatePesel(calendar.getTime());
        
        // Method[] methods = peselGenerator.getClass().getDeclaredMethods();
        // for (Method method : methods) {
        //     System.out.println("Method: " + method.getName());
        // }
        try{
            Method method = peselGenerator.getClass().getDeclaredMethod("extractBirthDate", String.class);
            method.setAccessible(true);
            Assert.assertEquals(calendar.getTime().toString(), method.invoke(peselGenerator,PESEL).toString()); ;  
        }catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void extractBirthDate() throws Throwable {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1997, 7, 20);
        String PESEL = PeselGenerator.generatePesel(calendar.getTime());
        
        try{
            Constructor<?> constructor = PeselGenerator.class.getConstructor();
            PeselGenerator instance = (PeselGenerator)constructor.newInstance();
            Method method = PeselGenerator.class.getDeclaredMethod("extractBirthDate", String.class);
            method.getName();
            method.setAccessible(true);
            Assert.assertEquals(calendar.getTime().toString(), method.invoke(instance,PESEL).toString()); ;  
        }catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void extractSex() throws Throwable {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1997, 7, 20);
        PeselGenerator peselGenerator = new PeselGenerator();
        String PESEL = PeselGenerator.generatePesel(calendar.getTime());
        try{
            Method method = peselGenerator.getClass().getDeclaredMethod("extractSex",String.class);
            method.setAccessible(true);
            // System.out.println(PESEL);
            Assert.assertEquals(Sex.getSex(PESEL), method.invoke(peselGenerator,PESEL)); 
        }catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

