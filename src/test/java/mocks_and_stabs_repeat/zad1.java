package mocks_and_stabs_repeat;
import mocks_and_stabs_repeat.Cat;
import mocks_and_stabs_repeat.CatDog;
import mocks_and_stabs_repeat.Dog;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class zad1 {

    @Mock
    private Cat cat;

    @Mock
    private Dog dog;

    @Test
    public void test() {
        Mockito.when(cat.createHalfCat()).thenReturn(new Cat(2, "Я самый умный"));
        Mockito.when(dog.createHalfDog()).thenReturn(new Dog(2, "Я самый весёлый"));
        Cat halfCat = cat.createHalfCat();
        Dog halfDog = dog.createHalfDog();
        Mockito.verify(cat, Mockito.times(1)).createHalfCat();
        Mockito.verify(dog, Mockito.times(1)).createHalfDog();
        CatDog catDog = new CatDog(halfCat, halfDog);
        Assert.assertEquals(4, catDog.getLegsCount());
        Assert.assertEquals("Единственный в мире малыш Котопёс", catDog.getSound());
    }
}


