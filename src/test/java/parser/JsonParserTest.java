package parser;

import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import shop.Cart;
import supportClasses.CartCreator;
import supportClasses.FileCreator;
import supportClasses.MyFileReader;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {

    private JsonParser jsonParser;

    private final String NAME_PREFIX = "test_";
    private final String NAME_POSTFIX = "_test";
    private final String BASE_PATH = "src/main/resources/";

    @BeforeEach
    void beforeEachSetUp() {
        jsonParser = new JsonParser();
    }

    @Test
    void parserCreatesNewFileWhenWriteCartToFile() {

        Cart cart = new CartCreator().plainCart(NAME_PREFIX + "plainCart" + NAME_POSTFIX);

        File file = new File(BASE_PATH + cart.getCartName() + ".json");
        assertFalse(file.isFile());
        jsonParser.writeToFile(cart);
        assertTrue(file.isFile());
    }

    @Test
    void parserRewritesFileDuringWritingCartToFileWhichAlreadyExists() throws IOException {

        Cart cart = new CartCreator().plainCart(NAME_PREFIX + "plainCart" + NAME_POSTFIX);

        File file = new File(BASE_PATH + cart.getCartName() + ".json");
        assertFalse(file.isFile());

        String textInFile = "message";
        new FileCreator().createFile(BASE_PATH, cart.getCartName() + ".json", textInFile);
        assertTrue(file.isFile());

        jsonParser.writeToFile(cart);
        assertTrue(file.isFile());
        String actualTextInFile = new MyFileReader().readFile(BASE_PATH, cart.getCartName() + ".json");

        assertNotEquals(textInFile, actualTextInFile);
    }

    @Test()
    void writeNullCartToFile() {

        Cart cart = null;

        assertThrows(NullPointerException.class, () -> jsonParser.writeToFile(cart));
    }

    @Test
    void writeEmptyCartToFile() throws IOException {
        Cart cart = new CartCreator().emptyCart(NAME_PREFIX + "emptyCart" + NAME_POSTFIX);

        jsonParser.writeToFile(cart);
        String expectedText = new Gson().toJson(cart);

        String actualText = new MyFileReader().readFile(BASE_PATH, cart.getCartName() + ".json");

        assertEquals(expectedText, actualText);
    }

    @Test()
    void writePlainCartToFile() throws IOException {

        Cart cart = new CartCreator().plainCart(NAME_PREFIX + "plainCart" + NAME_POSTFIX);

        jsonParser.writeToFile(cart);
        String expectedText = new Gson().toJson(cart);

        String actualText = new MyFileReader().readFile(BASE_PATH, cart.getCartName() + ".json");

        assertEquals(expectedText, actualText);
    }

    @Test()
    void writeBigCartToFile() throws IOException {

        Cart cart = new CartCreator().bigCart(NAME_PREFIX + "bigCart" + NAME_POSTFIX);

        jsonParser.writeToFile(cart);
        String expectedText = new Gson().toJson(cart);

        String actualText = new MyFileReader().readFile(BASE_PATH, cart.getCartName() + ".json");

        assertEquals(expectedText, actualText);
    }

    @Test
    void readFromFileNoFile() {

        File file = new File("random_path");
        assertThrows(FileNotFoundException.class, () -> jsonParser.readFromFile(file));
    }

    @Disabled("Disabled until the expected narrative is figured out")
    @Test
    void readFromFileWithUnexpectedStructure() throws IOException {

        String fileName = NAME_PREFIX + "randomFileName" + NAME_POSTFIX + ".txt";
        String text = "random text";
        new FileCreator().createFile(BASE_PATH, fileName, text);

        File file = new File(BASE_PATH + fileName);

        Cart cart = jsonParser.readFromFile(file);

        //I expect that in this case cart should be null.
        //But maybe it should be one more Exception in try/catch block
        assertNull(cart);
    }

    @Test
    void readEmptyCartFromFile() throws IOException {

        String emptyCartName = NAME_PREFIX + "emptyCart" + NAME_POSTFIX;
        File file = new FileCreator().createAndReturnFileWithEmptyCart(BASE_PATH, emptyCartName + ".json");

        Cart cart = jsonParser.readFromFile(file);

        assertAll(
                () -> assertEquals(cart.getCartName(), emptyCartName),
                () -> assertEquals(cart.getTotalPrice(), 0)
        );
    }

    @Test
    void readPlainCartFromFile() throws IOException {

        String plainCartName = NAME_PREFIX + "plainCart" + NAME_POSTFIX;
        File file = new FileCreator().createAndReturnFileWithPlainCart(BASE_PATH, plainCartName + ".json");

        Cart cart = jsonParser.readFromFile(file);

        assertEquals(cart.getCartName(), plainCartName);
        assertEquals(cart.getTotalPrice(), 122.256);
    }

    @Test
    void readBigCartFromFile() throws IOException {

        String bigCartName = NAME_PREFIX + "bigCart" + NAME_POSTFIX;
        File file = new FileCreator().createAndReturnFileWithBigCart(BASE_PATH, bigCartName + ".json");

        Cart cart = jsonParser.readFromFile(file);

        assertEquals(cart.getCartName(), bigCartName);
        assertEquals(cart.getTotalPrice(), 1930.44);
    }

    @AfterEach
    void afterEachTearDown() {

        new FileCreator().delAllTestFiles(BASE_PATH);
    }
}